package com.zving.cms.dataservice;

import com.zving.framework.Config;
import com.zving.framework.Constant;
import com.zving.framework.Page;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.controls.HtmlTD;
import com.zving.framework.controls.HtmlTR;
import com.zving.framework.controls.HtmlTable;
import com.zving.framework.data.DBConnConfig;
import com.zving.framework.data.DBConnPool;
import com.zving.framework.data.DBUtil;
import com.zving.framework.data.DataAccess;
import com.zving.framework.data.DataColumn;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.DataTableUtil;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.orm.SchemaColumn;
import com.zving.framework.orm.TableCreator;
import com.zving.framework.utility.CaseIgnoreMapx;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.LogUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCCustomTableColumnSchema;
import com.zving.schema.ZCCustomTableColumnSet;
import com.zving.schema.ZCCustomTableSchema;
import com.zving.schema.ZCCustomTableSet;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CustomTable extends Page {
	public static final Mapx DBTypeMap = new Mapx();

	static {
		DBTypeMap.put("1", "文本型");
		DBTypeMap.put("8", "整型");
		DBTypeMap.put("7", "长整型");
		DBTypeMap.put("0", "日期");
		DBTypeMap.put("5", "浮点型");
		DBTypeMap.put("6", "双精度");
		DBTypeMap.put("10", "长文本");
	}

	public static Mapx init(Mapx map) {
		DataTable dt = new QueryBuilder("select name,id from ZCDatabase where SiteID=?",
				Application.getCurrentSiteID()).executeDataTable();
		String html = HtmlUtil.dataTableToOptions(dt);
		map.put("Databases", html);
		return map;
	}

	public static Mapx initColumn(Mapx map) {
		String html = HtmlUtil.mapxToOptions(DBTypeMap);
		map.put("DataTypeOptions", html);

		Mapx imap = new Mapx();
		imap.put("T", "文本框");
		imap.put("S", "下拉框");
		imap.put("R", "单选框");
		imap.put("C", "多选框");
		imap.put("A", "多行文本框");

		map.put("InputTypeOptions", HtmlUtil.mapxToOptions(imap));
		return map;
	}

	public static void dg1DataBind(DataGridAction dga) {
		Mapx dbMap = new QueryBuilder("select ID,Name from ZCDataBase where SiteID=?", Application
				.getCurrentSiteID()).executeDataTable().toMapx("ID", "Name");
		DataTable dt = new QueryBuilder("select * from ZCCustomTable where SiteID=?", Application
				.getCurrentSiteID()).executeDataTable();
		Mapx map = new Mapx();
		map.put("Link", "外部挂载表");
		map.put("Custom", "自定义表");
		dt.decodeColumn("Type", map);
		dt.decodeColumn("DataBaseID", dbMap);
		dga.bindData(dt);
	}

	public static void columnDataBind(DataGridAction dga) {
		String TableID = dga.getParam("TableID");
		DataTable dt = null;
		dt = new QueryBuilder(
				"select Code,Code as OldCode,Name,DataType,ListOptions,isAutoID,InputType,Length,isMandatory,isPrimaryKey from ZCCustomTableColumn where TableID=?",
				TableID).executeDataTable();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			String isMandatory = dt.getString(i, "isMandatory");
			String isPrimaryKey = dt.getString(i, "isPrimaryKey");
			String isAutoID = dt.getString(i, "isAutoID");
			if ("Y".equals(isMandatory)) {
				dt.set(i, "isMandatory", "checked='true'");
			}
			if ("Y".equals(isPrimaryKey)) {
				dt.set(i, "isPrimaryKey", "checked='true'");
			}
			if ("Y".equals(isAutoID)) {
				dt.set(i, "isAutoID", "checked='true'");
			}
		}
		dga.bindData(dt);
	}

	public void del() {
		String ids = $V("IDs");
		if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}
		Transaction tran = new Transaction();
		ZCCustomTableSet set = new ZCCustomTableSchema().query(new QueryBuilder(
				"where SiteID=? and ID in (" + ids + ")", Application.getCurrentSiteID()));
		tran.add(new QueryBuilder("delete from ZCCustomTable where SiteID=? and ID in (" + ids
				+ ")", Application.getCurrentSiteID()));
		tran
				.add(new QueryBuilder("delete from ZCCustomTableColumn where TableID in (" + ids
						+ ")"));
		for (int i = 0; i < set.size(); ++i) {
			if (set.get(i).getType().equals("Custom")) {
				tran.add(new QueryBuilder("drop table if exists " + set.get(i).getCode()));
			}
		}
		if (tran.commit())
			this.Response.setMessage("删除成功");
		else
			this.Response.setError("发生错误,删除失败");
	}

	public void add() {
		Transaction tran = new Transaction();
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		if (StringUtil.isEmpty($V("ID"))) {
			QueryBuilder qb = new QueryBuilder("select count(1) from ZCCustomTable where Code=?",
					$V("Code"));
			if (qb.executeInt() > 0) {
				this.Response.setError("已经存在相同的自定义表：" + $V("Code"));
				return;
			}
			table.setValue(this.Request);
			table.setID(NoUtil.getMaxID("CustomTableID"));
			table.setAddTime(new Date());
			table.setAddUser(User.getUserName());
			table.setSiteID(Application.getCurrentSiteID());
			tran.add(table, 1);
		} else {
			table.setID(Long.parseLong($V("ID")));
			table.fill();
			table.setValue(this.Request);
			table.setModifyTime(new Date());
			table.setModifyUser(User.getUserName());
			tran.add(table, 2);
		}
		ZCCustomTableColumnSet set = new ZCCustomTableColumnSet();
		if ($V("Type").equals("Link")) {
			DBConnConfig dcc = OuterDatabase.getDBConnConfig(table.getDatabaseID());
			DataTable dt = DBUtil.getColumnInfo(dcc, table.getOldCode());
			for (int i = 0; i < dt.getRowCount(); ++i) {
				ZCCustomTableColumnSchema column = new ZCCustomTableColumnSchema();
				DataRow dr = dt.getDataRow(i);
				column.setID(NoUtil.getMaxID("CustomTableColumnID"));
				column.setAddTime(new Date());
				column.setAddUser(User.getUserName());
				column.setTableID(table.getID());
				column.setCode(dr.getString("Column_Name"));
				column.setName(dr.getString("Column_Name"));
				column.setDataType(dr.getString("Type_Name"));
				column.setInputType("Input");
				column.setIsAutoID("N");
				column.setLength(Integer.parseInt(dr.getString("Column_Size")));
				column.setIsMandatory((dr.getString("Nullable").equals("0")) ? "Y" : "N");
				column.setIsPrimaryKey(dr.getString("isKey"));
				set.add(column);
			}
		} else {
			DataTable dt = this.Request.getDataTable("Data");
			for (int i = 0; i < dt.getRowCount(); ++i) {
				ZCCustomTableColumnSchema column = new ZCCustomTableColumnSchema();
				DataRow dr = dt.getDataRow(i);
				column.setID(NoUtil.getMaxID("CustomTableColumnID"));
				column.setAddTime(new Date());
				column.setAddUser(User.getUserName());
				column.setTableID(table.getID());
				column.setCode(dr.getString("Code"));
				column.setName(dr.getString("Name"));
				column.setDataType(dr.getString("DataType"));
				column.setInputType(dr.getString("InputType"));
				if (StringUtil.isNotEmpty(dr.getString("Length"))) {
					column.setLength(Integer.parseInt(dr.getString("Length")));
				}
				if (Integer.parseInt(column.getDataType()) != 1) {
					column.setLength(null);
				}
				column.setIsMandatory(("Y".equals(dr.getString("isMandatory"))) ? "Y" : "N");
				column.setIsPrimaryKey(("Y".equals(dr.getString("isPrimaryKey"))) ? "Y" : "N");
				column.setIsAutoID(("Y".equals(dr.getString("isAutoID"))) ? "Y" : "N");
				column.setListOptions(dr.getString("ListOptions"));
				set.add(column);
			}
			if (StringUtil.isEmpty($V("ID"))) {
				createTable(tran, table.getCode(), set);
			} else {
				ZCCustomTableColumnSchema ctc = new ZCCustomTableColumnSchema();
				ctc.setTableID(table.getID());

				CaseIgnoreMapx map = new CaseIgnoreMapx();
				for (int i = 0; i < dt.getRowCount(); ++i) {
					if (StringUtil.isNotEmpty(dt.getString(i, "OldCode"))) {
						map.put(dt.getString(i, "Code"), dt.getString(i, "OldCode"));
					}
				}

				ArrayList deletedList = new ArrayList();
				for (int i = 0; i < set.size(); ++i) {
					boolean flag = false;
					for (int j = 0; j < dt.getRowCount(); ++j) {
						if (set.get(i).getCode().equals(dt.getString(j, "OldCode"))) {
							flag = true;
							break;
						}
					}
					if (!(flag))
						deletedList.add(set.get(i).getCode());
				}
				try {
					modifyTable(tran, table.getCode(), map, deletedList, set);
				} catch (Exception e) {
					e.printStackTrace();
					this.Response.setError("修改表时发生错误,保存失败:" + e.getMessage());
					return;
				}
			}
		}
		tran
				.add(new QueryBuilder("delete from ZCCustomTableColumn where TableID=?", table
						.getID()));
		tran.add(set, 1);
		if (tran.commit())
			this.Response.setMessage("保存成功");
		else
			this.Response.setError("发生错误,保存失败:" + tran.getExceptionMessage());
	}

	public static void modifyTable(Transaction tran, String tableName, Mapx map,
			ArrayList deletedList, ZCCustomTableColumnSet set) throws Exception {
		TableCreator tc = new TableCreator(DBConnPool.getDBConnConfig().DBType);
		tc.modifyTable(tran, map, deletedList, toSchemaColumns(set), tableName);
		tc.executeAndClear(tran);
	}

	public static void createTable(Transaction tran, String tableName, ZCCustomTableColumnSet set) {
		TableCreator tc = new TableCreator(DBConnPool.getDBConnConfig().DBType);
		try {
			tc.createTable(toSchemaColumns(set), tableName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tc.executeAndClear(tran);
	}

	private static SchemaColumn[] toSchemaColumns(ZCCustomTableColumnSet set) {
		SchemaColumn[] scs = new SchemaColumn[set.size()];
		for (int i = 0; i < scs.length; ++i) {
			ZCCustomTableColumnSchema ctc = set.get(i);
			int type = Integer.parseInt(ctc.getDataType());
			if (type == 12) {
				type = 0;
			}
			SchemaColumn sc = new SchemaColumn(ctc.getCode(), type, i, (int) ctc.getLength(), 0,
					"Y".equals(ctc.getIsMandatory()), "Y".equals(ctc.getIsPrimaryKey()));
			scs[i] = sc;
		}
		return scs;
	}

	public static Mapx initDataEditGrid(Mapx map) {
		ZCCustomTableColumnSet set = new ZCCustomTableColumnSchema().query(new QueryBuilder(
				"where TableID=?", map.getString("ID")));

		StringBuffer sb = new StringBuffer();
		sb.append("<table width='100%' cellpadding=\"2\" cellspacing=\"0\" class=\"dataTable\" >");
		sb.append("<tr ztype=\"head\" class=\"dataTableHead\">");
		sb.append("<td  width=\"40\" ztype=\"rowno\">&nbsp;</td>");
		sb.append("<td  width=\"40\" ztype=\"Selector\" field='Code' align='center'>&nbsp;</td>");
		ZCCustomTableColumnSchema column;
		for (int i = 0; i < set.size(); ++i) {
			column = set.get(i);
			sb.append("<td  width=\"120\">" + column.getName() + "</td>");
		}
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td align=\"center\">&nbsp;</td>");
		sb.append("<td align=\"center\">&nbsp;</td>");
		for (int i = 0; i < set.size(); ++i) {
			sb.append("<td>");
			column = set.get(i);
			String verify = "";
			if ("Y".equals(column.getIsMandatory())) {
				verify = "NotNull";
			}
			if ("Y".equals(column.getIsPrimaryKey())) {
				sb.append("<input type='hidden' value=\"${" + column.getCode() + "}\" name='"
						+ column.getCode() + "_Old'>");
			}
			int type = Integer.parseInt(column.getDataType());
			if (type == 0) {
				if (StringUtil.isNotEmpty(verify))
					verify = verify + "&&DateTime";
				else
					verify = "DateTime";
			} else if ((type == 8) || (type == 7) || (type == 9)) {
				if (StringUtil.isNotEmpty(verify))
					verify = verify + "&&Int";
				else
					verify = "Int";
			} else if ((type == 5) || (type == 4) || (type == 6) || (type == 3)) {
				if (StringUtil.isNotEmpty(verify))
					verify = verify + "&&Number";
				else {
					verify = "Number";
				}
			}
			sb.append("<input class='inputText' verify='" + verify + "' value=\"${"
					+ column.getCode() + "}\" name='" + column.getCode() + "'></td>");
		}
		sb.append("</tr>");
		sb.append("<tr ztype=\"pagebar\">");
		sb.append("<td height=\"25\" colspan=\"" + (set.size() + 2)
				+ "\" align=\"center\">${PageBar}</td>");
		sb.append("</tr>");
		sb.append("</table>");

		DataGridAction dga = new DataGridAction();

		dga.setTagBody(sb.toString());
		String method = "com.zving.cms.dataservice.CustomTable.dataEditGridBind";
		dga.setMethod(method);

		dga.setID("dg1");
		dga.setPageFlag(true);
		dga.setPageSize(15);
		dga.setPageIndex(0);
		dga.setParams(map);
		try {
			HtmlTable table = new HtmlTable();
			table.parseHtml(dga.getTagBody());
			dga.setTemplate(table);
			dga.parse();
			dataEditGridBind(dga);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("HTML", dga.getHtml());
		return map;
	}

	public static void dataEditGridBind(DataGridAction dga) {
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setID(Long.parseLong(dga.getParam("ID")));
		table.fill();
		if (table.getSiteID() != Application.getCurrentSiteID()) {
			return;
		}
		ZCCustomTableColumnSet set = new ZCCustomTableColumnSchema().query(new QueryBuilder(
				"where TableID=?", table.getID()));
		DataAccess da = null;
		DataTable dt = null;
		String code = table.getCode();
		if (table.getType().equals("Link")) {
			da = new DataAccess(OuterDatabase.getConnection(table.getDatabaseID()));
			code = table.getOldCode();
		} else {
			da = new DataAccess();
		}
		try {
			int total = Integer.parseInt(da.executeOneValue(
					new QueryBuilder("select count(1) from " + code)).toString());
			dga.setTotal(total);
			String sql = "select * from " + code + " order by ";
			for (int i = 0; i < set.size(); ++i) {
				if (set.get(i).getIsPrimaryKey().equals("Y")) {
					sql = sql + set.get(i).getCode() + ",";
				}
			}
			sql = sql.substring(0, sql.length() - 1);
			QueryBuilder qb = new QueryBuilder(sql);
			dt = da.executePagedDataTable(qb, dga.getPageSize(), dga.getPageIndex());
			for (int i = 0; i < dt.getRowCount(); ++i)
				for (int j = 0; j < dt.getColCount(); ++j)
					if (dt.getDataColumn(j).getColumnType() != 0) {
						String v = dt.getString(i, j);
						if (StringUtil.isEmpty(v)) {
							v = "";
						}
						v = StringUtil.javaEncode(v);
						dt.set(i, j, v);
					}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				da.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dga.bindData(dt);
	}

	public void saveData() {
		DataTable dt = (DataTable) this.Request.get("Data");
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setID(Long.parseLong($V("ID")));
		table.fill();
		if (table.getSiteID() != Application.getCurrentSiteID()) {
			return;
		}
		if (!(CustomTableUtil.updateData(table, dt)))
			this.Response.setError("保存失败!");
		else
			this.Response.setMessage("保存成功!");
	}

	public void delData() {
		DataTable dt = (DataTable) this.Request.get("Data");
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setID(Long.parseLong($V("ID")));
		table.fill();
		if (table.getSiteID() != Application.getCurrentSiteID()) {
			return;
		}
		DataAccess da = null;
		String code = table.getCode();
		if (table.getType().equals("Link")) {
			da = new DataAccess(OuterDatabase.getConnection(table.getDatabaseID()));
			code = table.getOldCode();
		} else {
			da = new DataAccess();
		}
		try {
			da.setAutoCommit(false);
			ZCCustomTableColumnSet set = new ZCCustomTableColumnSchema().query(new QueryBuilder(
					"where TableID=?", $V("ID")));
			QueryBuilder qb = new QueryBuilder("delete from " + code + " where 1=1 ");
			qb.setBatchMode(true);
			ArrayList list = new ArrayList(4);
			for (int i = 0; i < set.size(); ++i) {
				if ("Y".equals(set.get(i).getIsPrimaryKey())) {
					qb.appendSQLPart(" and " + set.get(i).getCode() + "=?");
					list.add(set.get(i).getCode());
				}
			}
			for (int i = 0; i < dt.getRowCount(); ++i) {
				for (int j = 0; j < list.size(); ++j) {
					String v = dt.getString(i, list.get(j).toString());
					if (StringUtil.isEmpty(v)) {
						v = null;
					}
					qb.add(v);
				}
				qb.addBatch();
			}
			da.executeNoQuery(qb);
			da.commit();
			this.Response.setMessage("删除成功!");
		} catch (Exception e) {
			this.Response.setError("删除失败:" + e.getMessage());
			try {
				da.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				da.setAutoCommit(true);
				da.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void exportExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding(Constant.GlobalCharset);
		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=CustomData_"
				+ DateUtil.getCurrentDateTime("yyyyMMddhhmmss") + ".xls");

		String ID = request.getParameter("ID");
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setID(Long.parseLong(ID));
		table.fill();
		if (table.getSiteID() != Application.getCurrentSiteID()) {
			return;
		}
		int total = CustomTableUtil.getTotal(table, "where 1=1");
		if (total > 60000) {
			total = 60000;
		}
		ZCCustomTableColumnSet set = new ZCCustomTableColumnSchema().query(new QueryBuilder(
				"where TableID=?", ID));
		HtmlTable ht = new HtmlTable();
		HtmlTR tr = new HtmlTR();
		ht.addTR(tr);
		for (int i = 0; i < set.size(); ++i) {
			HtmlTD td = new HtmlTD();
			td.InnerHTML = set.get(i).getName();
			tr.addTD(td);
		}
		for (int i = 0; i * 500 < total; ++i) {
			DataTable dt = CustomTableUtil.getData(table, new QueryBuilder("where 1=1"), 500, i);
			for (int k = 0; k < dt.getRowCount(); ++k) {
				tr = new HtmlTR();
				ht.addTR(tr);
				for (int j = 0; j < set.size(); ++j) {
					HtmlTD td = new HtmlTD();
					td.InnerHTML = dt.getString(k, j);
					tr.addTD(td);
				}
			}
		}
		String[] widths = new String[set.size()];
		for (int i = 0; i < set.size(); ++i) {
			widths[i] = "150";
		}
		String[] indexes = new String[set.size()];
		for (int i = 0; i < set.size(); ++i) {
			indexes[i] = String.valueOf(i);
		}
		HtmlUtil.htmlTableToExcel(response.getOutputStream(), ht, widths, indexes, null);
	}

	public static void uploadData(HttpServletRequest request, HttpServletResponse response) {
		try {
			DiskFileItemFactory fileFactory = new DiskFileItemFactory();
			ServletFileUpload fu = new ServletFileUpload(fileFactory);
			List fileItems = fu.parseRequest(request);
			fu.setHeaderEncoding("UTF-8");
			Iterator iter = fileItems.iterator();
			FileItem fileItem = null;
			FileItem idItem = null;
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.getFieldName().equals("ID")) {
					idItem = item;
				}
				if (!(item.isFormField())) {
					String OldFileName = item.getName();
					if ((OldFileName != null) && (!(OldFileName.equals("")))
							&& (item.getSize() != 0L)) {
						fileItem = item;
					}
				}
			}
			String OldFileName = fileItem.getName();
			LogUtil.info("Upload CustomData FileName:" + OldFileName);
			OldFileName = OldFileName.substring(OldFileName.lastIndexOf("\\") + 1);
			String ext = OldFileName.substring(OldFileName.lastIndexOf("."));
			if (!(ext.toLowerCase().equals(".xls"))) {
				response.sendRedirect("CustomTableDataImportStep1.jsp?Error=1");
				return;
			}
			String FileName = "CustomTableUpload_" + DateUtil.getCurrentDate("yyyyMMddHHmmss")
					+ ".xls";
			String Path = Config.getContextRealPath() + "WEB-INF/data/backup/";
			fileItem.write(new File(Path + FileName));
			response.sendRedirect("CustomTableDataImportStep2.jsp?FileName=" + FileName + "&ID="
					+ idItem.getString(Constant.GlobalCharset));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Mapx initImportStep2(Mapx params) {
		String FileName = params.getString("FileName");
		String ID = params.getString("ID");
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setID(Long.parseLong(ID));
		table.fill();
		if (table.getSiteID() != Application.getCurrentSiteID())
			return null;
		try {
			ArrayList list = getErrorInfo(ID, FileName);
			if (list.size() == 0) {
				params.put("Message", "文件检查未发现错误，确认要导入?");
			}
			params.put("Message", "文件检查中发现错误 <font color='red'>" + list.size()
					+ "</font> 处。<br><a href='CustomTableImportError.jsp?ID=" + ID + "&FileName="
					+ FileName + "'>点击此处</a>下载错误信息表，确认要导入?");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	public static ArrayList getErrorInfo(String ID, String FileName) {
		DataTable dt = null;
		try {
			String Path = Config.getContextRealPath() + "WEB-INF/data/backup/";
			dt = DataTableUtil.xlsToDataTable(Path + FileName);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("读取数据导入文件时发生错误:" + e.getMessage());
			return null;
		}
		ArrayList list = new ArrayList();
		try {
			ZCCustomTableColumnSet set = new ZCCustomTableColumnSchema().query(new QueryBuilder(
					"where TableID=?", ID));
			int ErrorCount = 0;
			for (int i = 0; i < dt.getRowCount(); ++i)
				for (int j = 0; j < dt.getColCount(); ++j) {
					ZCCustomTableColumnSchema ctc = set.get(j);
					String v = dt.getString(i, j);
					int dataType = Integer.parseInt(ctc.getDataType());
					if (((("Y".equals(ctc.getIsMandatory())) || ("Y".equals(ctc.getIsPrimaryKey()))))
							&& (StringUtil.isEmpty(v))) {
						++ErrorCount;
						list.add("第" + (i + 1) + "行，第" + (j + 1) + "列：字段不能为空!");
					}

					if (v != null) {
						if ((ctc.getMaxLength() != 0) && (v.length() < ctc.getMaxLength())) {
							list.add("第" + (i + 1) + "行，第" + (j + 1) + "列：数据超长，最大允许"
									+ ctc.getMaxLength() + "个字符!");
							++ErrorCount;
						}
						try {
							if (dataType == 0) {
								v = DateUtil.toDateTimeString(DateUtil.parseDateTime(v));
								if (v == null) {
									throw new SQLException("日期时间错误");
								}
							}
							if ((dataType == 8) || (dataType == 9)) {
								v = String.valueOf(new Double(Double.parseDouble(v)).intValue());
							}
							if (dataType == 7) {
								v = String.valueOf(new Double(Double.parseDouble(v)).longValue());
							}
							if (dataType == 5) {
								v = String.valueOf(new Double(Double.parseDouble(v)).floatValue());
							}
							if ((dataType == 4) || (dataType == 6) || (dataType == 3))
								v = String.valueOf(Double.parseDouble(v));
						} catch (Exception e) {
							list.add("第" + (i + 1) + "行，第" + (j + 1) + "列：不符合数据类型!");
							++ErrorCount;
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void downloadErrorList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding(Constant.GlobalCharset);
		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=Error_"
				+ DateUtil.getCurrentDateTime("yyyyMMddhhmmss") + ".txt");

		String ID = request.getParameter("ID");
		String FileName = request.getParameter("FileName");
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setID(Long.parseLong(ID));
		table.fill();
		if (table.getSiteID() != Application.getCurrentSiteID()) {
			return;
		}
		ArrayList list = getErrorInfo(ID, FileName);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); ++i) {
			sb.append(list.get(i));
			sb.append("\n");
		}
		response.getOutputStream().write(sb.toString().getBytes());
	}

	public void importData() {
		String FileName = $V("FileName");
		String ID = $V("ID");
		ZCCustomTableSchema table = new ZCCustomTableSchema();
		table.setID(Long.parseLong(ID));
		table.fill();
		if (table.getSiteID() != Application.getCurrentSiteID()) {
			return;
		}
		DataTable dt = null;
		try {
			String Path = Config.getContextRealPath() + "WEB-INF/data/backup/";
			dt = DataTableUtil.xlsToDataTable(Path + FileName);
		} catch (Exception e) {
			e.printStackTrace();
			this.Response.setError("读取数据导入文件时发生错误:" + e.getMessage());
			return;
		}
		CustomTableUtil.updateData(table, dt);
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.dataservice.CustomTable JD-Core Version: 0.5.3
 */