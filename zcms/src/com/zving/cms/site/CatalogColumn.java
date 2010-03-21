package com.zving.cms.site;

import com.zving.cms.dataservice.ColumnUtil;
import com.zving.cms.pub.CatalogUtil;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.platform.pub.OrderUtil;
import com.zving.schema.ZDColumnRelaSchema;
import com.zving.schema.ZDColumnRelaSet;
import com.zving.schema.ZDColumnSchema;
import com.zving.schema.ZDColumnValueSet;
import java.util.Date;

public class CatalogColumn extends Page {
	public static String Extend_Self = "1";

	public static String Extend_Children = "2";

	public static String Extend_All = "3";

	public static String Extend_SameLevel = "4";

	public static Mapx ExtendMap = new Mapx();

	public static Mapx initDialog(Mapx params) {
		String ID = params.getString("ColumnID");
		if (StringUtil.isEmpty(ID)) {
			params.put("VerifyType", HtmlUtil.mapxToOptions(ColumnUtil.VerifyTypeMap));
			params.put("InputType", HtmlUtil.mapxToOptions(ColumnUtil.InputTypeMap));
			params.put("IsMandatory", HtmlUtil.codeToRadios("IsMandatory", "YesOrNo", "N"));
			params.put("MaxLength", "100");
			params.put("Cols", "265");
			params.put("Rows", "90");
		} else {
			ZDColumnSchema column = new ZDColumnSchema();
			column.setID(ID);
			column.fill();
			params = column.toMapx();
			params.put("VerifyType", HtmlUtil.mapxToOptions(ColumnUtil.VerifyTypeMap, column
					.getVerifyType()));
			params.put("InputType", HtmlUtil.mapxToOptions(ColumnUtil.InputTypeMap, column
					.getInputType()));
			params.put("IsMandatory", HtmlUtil.codeToRadios("IsMandatory", "YesOrNo", column
					.getIsMandatory()));
		}
		return params;
	}

	public static void dg1DataBind(DataGridAction dga) {
		String CatalogID = dga.getParam("CatalogID");
		String sql = "select * from ZDColumn where  exists (select ColumnID from ZDColumnRela where ZDColumnRela.ColumnID=ZDColumn.ID and ZDColumnRela.RelaType='1' and RelaID =?) order by ID asc";
		QueryBuilder qb = new QueryBuilder();
		qb.add(CatalogID);
		qb.setSQL(sql);
		DataTable dt = qb.executeDataTable();
		dt.decodeColumn("VerifyType", ColumnUtil.VerifyTypeMap);
		dt.decodeColumn("InputType", ColumnUtil.InputTypeMap);
		dga.bindData(dt);
	}

	public void add() {
		String catalogID = $V("CatalogID");
		String columnCode = $V("Code");
		int cType = new QueryBuilder("select type from zccatalog where id = ?", catalogID)
				.executeInt();
		int CatalogType;
		if (cType == 9)
			CatalogType = 9;
		else {
			CatalogType = 1;
		}
		long count = new QueryBuilder(
				"select count(*) from ZDColumnRela where RelaType='1' and RelaID = ? and ColumnCode =? ",
				catalogID, columnCode).executeLong();
		if (count > 0L) {
			this.Response.setLogInfo(0, "已经存在相同的字段");
			return;
		}
		Transaction trans = new Transaction();
		ZDColumnSchema column = new ZDColumnSchema();
		column.setValue(this.Request);

		String defaultValue = column.getDefaultValue();
		defaultValue = defaultValue.replaceAll("　　", ",");
		defaultValue = defaultValue.replaceAll("　", ",");
		defaultValue = defaultValue.replaceAll("  ", ",");
		defaultValue = defaultValue.replaceAll(" ", ",");
		defaultValue = defaultValue.replaceAll(",,", ",");
		defaultValue = defaultValue.replaceAll("，，", ",");
		defaultValue = defaultValue.replaceAll("，", ",");
		column.setDefaultValue(defaultValue);

		column.setID(NoUtil.getMaxID("ColumnID"));
		column.setSiteID(Application.getCurrentSiteID());
		column.setOrderFlag(OrderUtil.getDefaultOrder());
		column.setAddTime(new Date());
		column.setAddUser(User.getUserName());

		if (ColumnUtil.Input.equals(column.getInputType())) {
			column.setColSize(null);
			column.setRowSize(null);
			column.setListOption("");
		} else if (ColumnUtil.Text.equals(column.getInputType())) {
			column.setListOption("");
		} else if (ColumnUtil.Select.equals(column.getInputType())) {
			column.setColSize(null);
			column.setRowSize(null);
			column.setMaxLength(null);
			column.setVerifyType(ColumnUtil.STRING);
		} else if (ColumnUtil.Radio.equals(column.getInputType())) {
			column.setIsMandatory("N");
			column.setColSize(null);
			column.setRowSize(null);
			column.setMaxLength(null);
			column.setVerifyType(ColumnUtil.STRING);
		} else if (ColumnUtil.Checkbox.equals(column.getInputType())) {
			column.setIsMandatory("N");
			column.setColSize(null);
			column.setRowSize(null);
			column.setMaxLength(null);
			column.setVerifyType(ColumnUtil.STRING);
		} else if ((ColumnUtil.DateInput.equals(column.getInputType()))
				|| (ColumnUtil.TimeInput.equals(column.getInputType()))) {
			column.setColSize(null);
			column.setRowSize(null);
			column.setMaxLength(null);
			column.setListOption("");
			column.setVerifyType(ColumnUtil.STRING);
		} else if (ColumnUtil.ImageInput.equals(column.getInputType())) {
			column.setIsMandatory("N");
			column.setColSize(null);
			column.setRowSize(null);
			column.setMaxLength(null);
			column.setListOption("");
			column.setVerifyType(ColumnUtil.STRING);
		} else if (ColumnUtil.HTMLInput.equals(column.getInputType())) {
			column.setIsMandatory("N");
			column.setColSize(null);
			column.setRowSize(null);
			column.setMaxLength(null);
			column.setListOption("");
			column.setVerifyType(ColumnUtil.STRING);
		}

		String extend = $V("Extend");
		if (Extend_Self.equals(extend)) {
			ZDColumnRelaSchema rela = new ZDColumnRelaSchema();
			rela.setID(NoUtil.getMaxID("ColumnRelaID"));
			rela.setColumnID(column.getID());
			rela.setColumnCode(column.getCode());
			rela.setRelaType("1");
			rela.setRelaID(catalogID);
			rela.setAddTime(column.getAddTime());
			rela.setAddUser(column.getAddUser());
			trans.add(rela, 1);
		} else {
			ZDColumnRelaSchema rela;
			if (Extend_Children.equals(extend)) {
				String innerCode = CatalogUtil.getInnerCode(catalogID);
				DataTable childCatalogDT = new QueryBuilder(
						"select id from zccatalog where innercode like '" + innerCode + "%'")
						.executeDataTable();
				for (int i = 0; i < childCatalogDT.getRowCount(); ++i) {
					rela = new ZDColumnRelaSchema();
					rela.setID(NoUtil.getMaxID("ColumnRelaID"));
					rela.setColumnID(column.getID());
					rela.setColumnCode(column.getCode());
					rela.setRelaType("1");
					rela.setRelaID(childCatalogDT.getString(i, 0));
					rela.setAddTime(column.getAddTime());
					rela.setAddUser(column.getAddUser());
					trans.add(rela, 1);
				}
			} else if (Extend_All.equals(extend)) {
				DataTable allCatalogDT = new QueryBuilder("select id from zccatalog where Type="
						+ CatalogType + " and siteID =" + Application.getCurrentSiteID())
						.executeDataTable();
				for (int i = 0; i < allCatalogDT.getRowCount(); ++i) {
					rela = new ZDColumnRelaSchema();
					rela.setID(NoUtil.getMaxID("ColumnRelaID"));
					rela.setColumnID(column.getID());
					rela.setColumnCode(column.getCode());
					rela.setRelaType("1");
					rela.setRelaID(allCatalogDT.getString(i, 0));
					rela.setAddTime(column.getAddTime());
					rela.setAddUser(column.getAddUser());
					trans.add(rela, 1);
				}
			} else if (Extend_SameLevel.equals(extend)) {
				int level = new QueryBuilder("select treelevel from zccatalog where id ="
						+ catalogID).executeInt();
				DataTable levelCatalogDT = new QueryBuilder(
						"select id from zccatalog where siteID =" + Application.getCurrentSiteID()
								+ " and Type=" + CatalogType + " and treelevel=" + level)
						.executeDataTable();
				for (int i = 0; i < levelCatalogDT.getRowCount(); ++i) {
					rela = new ZDColumnRelaSchema();
					rela.setID(NoUtil.getMaxID("ColumnRelaID"));
					rela.setColumnID(column.getID());
					rela.setColumnCode(column.getCode());
					rela.setRelaType("1");
					rela.setRelaID(levelCatalogDT.getString(i, 0));
					rela.setAddTime(column.getAddTime());
					rela.setAddUser(column.getAddUser());
					trans.add(rela, 1);
				}
			}
		}
		trans.add(column, 1);
		if (trans.commit())
			this.Response.setLogInfo(1, "新建成功");
		else
			this.Response.setLogInfo(0, "新建失败");
	}

	public void save() {
		String catalogID = $V("CatalogID");
		String columnCode = $V("Code");
		long count = new QueryBuilder(
				"select count(*) from ZDColumnRela where RelaType='1' and RelaID = ? and ColumnCode =? and ColumnID!="
						+ $V("ColumnID"), catalogID, columnCode).executeLong();
		if (count > 0L) {
			this.Response.setLogInfo(0, "已经存在相同的字段");
			return;
		}
		ZDColumnSchema column = new ZDColumnSchema();
		column.setID($V("ColumnID"));
		column.fill();
		column.setValue(this.Request);

		String defaultValue = column.getDefaultValue();
		defaultValue = defaultValue.replaceAll("　　", ",");
		defaultValue = defaultValue.replaceAll("　", ",");
		defaultValue = defaultValue.replaceAll("  ", ",");
		defaultValue = defaultValue.replaceAll(" ", ",");
		defaultValue = defaultValue.replaceAll(",,", ",");
		defaultValue = defaultValue.replaceAll("，，", ",");
		defaultValue = defaultValue.replaceAll("，", ",");
		column.setDefaultValue(defaultValue);

		if (ColumnUtil.Input.equals(column.getInputType())) {
			column.setColSize(null);
			column.setRowSize(null);
			column.setListOption("");
		} else if (ColumnUtil.Text.equals(column.getInputType())) {
			column.setListOption("");
		} else if (ColumnUtil.Select.equals(column.getInputType())) {
			column.setColSize(null);
			column.setRowSize(null);
			column.setMaxLength(null);
			column.setVerifyType(ColumnUtil.STRING);
		} else if (ColumnUtil.Radio.equals(column.getInputType())) {
			column.setIsMandatory("N");
			column.setColSize(null);
			column.setRowSize(null);
			column.setMaxLength(null);
			column.setVerifyType(ColumnUtil.STRING);
		} else if (ColumnUtil.Checkbox.equals(column.getInputType())) {
			column.setIsMandatory("N");
			column.setColSize(null);
			column.setRowSize(null);
			column.setMaxLength(null);
			column.setVerifyType(ColumnUtil.STRING);
		} else if (ColumnUtil.DateInput.equals(column.getInputType())) {
			column.setColSize(null);
			column.setRowSize(null);
			column.setMaxLength(null);
			column.setListOption("");
			column.setVerifyType(ColumnUtil.STRING);
		} else if (ColumnUtil.ImageInput.equals(column.getInputType())) {
			column.setIsMandatory("N");
			column.setColSize(null);
			column.setRowSize(null);
			column.setMaxLength(null);
			column.setListOption("");
			column.setVerifyType(ColumnUtil.STRING);
		} else if (ColumnUtil.HTMLInput.equals(column.getInputType())) {
			column.setIsMandatory("N");
			column.setColSize(null);
			column.setRowSize(null);
			column.setMaxLength(null);
			column.setListOption("");
			column.setVerifyType(ColumnUtil.STRING);
		}
		Transaction trans = new Transaction();
		trans.add(column, 2);
		trans.add(new QueryBuilder("update zdcolumnrela set ColumnCode=? where ColumnID=?", column
				.getCode(), column.getID()));
		trans.add(new QueryBuilder("update zdcolumnvalue set ColumnCode=? where ColumnID=?", column
				.getCode(), column.getID()));

		int cType = new QueryBuilder("select type from zccatalog where id = ?", catalogID)
				.executeInt();
		int CatalogType;
		if (cType == 9)
			CatalogType = 9;
		else {
			CatalogType = 1;
		}
		String extend = $V("Extend");
		if (!(Extend_Self.equals(extend))) {
			ZDColumnRelaSchema rela;
			if (Extend_Children.equals(extend)) {
				String innerCode = CatalogUtil.getInnerCode(catalogID);
				DataTable childCatalogDT = new QueryBuilder(
						"select id from zccatalog where innercode like '"
								+ innerCode
								+ "%' and not exists (select 'x' from zdcolumnrela b where b.ColumnID="
								+ column.getID() + " and b.RelaType='" + "1"
								+ "' and b.RelaID = zccatalog.ID)").executeDataTable();
				for (int i = 0; i < childCatalogDT.getRowCount(); ++i) {
					rela = new ZDColumnRelaSchema();
					rela.setID(NoUtil.getMaxID("ColumnRelaID"));
					rela.setColumnID(column.getID());
					rela.setColumnCode(column.getCode());
					rela.setRelaType("1");
					rela.setRelaID(childCatalogDT.getString(i, 0));
					rela.setAddTime(column.getAddTime());
					rela.setAddUser(column.getAddUser());
					trans.add(rela, 1);
				}
			} else if (Extend_All.equals(extend)) {
				DataTable allCatalogDT = new QueryBuilder("select id from zccatalog where Type="
						+ CatalogType + " and siteID =" + Application.getCurrentSiteID()
						+ " and not exists (select 'x' from zdcolumnrela b where b.ColumnID="
						+ column.getID() + " and b.RelaType='" + "1"
						+ "' and b.RelaID = zccatalog.ID)").executeDataTable();
				for (int i = 0; i < allCatalogDT.getRowCount(); ++i) {
					rela = new ZDColumnRelaSchema();
					rela.setID(NoUtil.getMaxID("ColumnRelaID"));
					rela.setColumnID(column.getID());
					rela.setColumnCode(column.getCode());
					rela.setRelaType("1");
					rela.setRelaID(allCatalogDT.getString(i, 0));
					rela.setAddTime(column.getAddTime());
					rela.setAddUser(column.getAddUser());
					trans.add(rela, 1);
				}
			} else if (Extend_SameLevel.equals(extend)) {
				int level = new QueryBuilder("select treelevel from zccatalog where id ="
						+ catalogID).executeInt();
				DataTable levelCatalogDT = new QueryBuilder(
						"select id from zccatalog where siteID ="
								+ Application.getCurrentSiteID()
								+ " and Type="
								+ CatalogType
								+ " and treelevel="
								+ level
								+ " and not exists (select 'x' from zdcolumnrela b where b.ColumnID="
								+ column.getID() + " and b.RelaType='" + "1"
								+ "' and b.RelaID = zccatalog.ID)").executeDataTable();
				for (int i = 0; i < levelCatalogDT.getRowCount(); ++i) {
					rela = new ZDColumnRelaSchema();
					rela.setID(NoUtil.getMaxID("ColumnRelaID"));
					rela.setColumnID(column.getID());
					rela.setColumnCode(column.getCode());
					rela.setRelaType("1");
					rela.setRelaID(levelCatalogDT.getString(i, 0));
					rela.setAddTime(column.getAddTime());
					rela.setAddUser(column.getAddUser());
					trans.add(rela, 1);
				}
			}
		}
		if (trans.commit())
			this.Response.setLogInfo(1, "保存成功!");
		else
			this.Response.setLogInfo(0, "保存失败!");
	}

	public void del() {
		String columnID = $V("ColumnID");
		String catalogID = $V("CatalogID");
		Transaction trans = new Transaction();

		int cType = new QueryBuilder("select type from zccatalog where id = ?", catalogID)
				.executeInt();
		int CatalogType;
		if (cType == 9)
			CatalogType = 9;
		else {
			CatalogType = 1;
		}
		String extend = $V("Extend");
		ZDColumnRelaSet relaSet = null;
		ZDColumnValueSet valueSet = null;
		if (Extend_Self.equals(extend)) {
			relaSet = new ZDColumnRelaSchema().query(new QueryBuilder("where columnID =" + columnID
					+ " and RelaType='" + "1" + "' and RelaID='" + catalogID + "'"));
		} else if (Extend_Children.equals(extend)) {
			String innerCode = CatalogUtil.getInnerCode(catalogID);
			relaSet = new ZDColumnRelaSchema()
					.query(new QueryBuilder(
							"where columnID ="
									+ columnID
									+ " and RelaType='"
									+ "1"
									+ "' and exists (select '' from ZCCatalog b where b.ID=RelaID and b.InnerCode like '"
									+ innerCode + "%')"));
		} else if (Extend_All.equals(extend)) {
			relaSet = new ZDColumnRelaSchema().query(new QueryBuilder("where columnID =" + columnID
					+ " and RelaType='" + "1"
					+ "' and exists (select '' from ZCCatalog b where b.ID=RelaID and b.SiteID = "
					+ Application.getCurrentSiteID() + ")"));
		} else if (Extend_SameLevel.equals(extend)) {
			int level = new QueryBuilder("select treelevel from zccatalog where id =" + catalogID)
					.executeInt();
			relaSet = new ZDColumnRelaSchema().query(new QueryBuilder("where columnID =" + columnID
					+ " and RelaType='" + "1"
					+ "' and exists (select '' from ZCCatalog b where b.ID=RelaID and b.SiteID = "
					+ Application.getCurrentSiteID() + " and b.Type=" + CatalogType
					+ " and b.TreeLevel=" + level + ")"));
		}

		trans.add(relaSet, 5);
		trans.add(valueSet, 5);
		if (trans.commit())
			this.Response.setLogInfo(1, "删除成功");
		else
			this.Response.setLogInfo(0, "删除失败");
	}

	public static Mapx initCopyTo(Mapx params) {
		Mapx map = new Mapx();
		String tIDs = params.get("IDs").toString();
		map.put("IDs", tIDs);
		String tCatalogID = params.get("CatalogID").toString();
		map.put("CatalogID", tCatalogID);
		String tSiteID = new QueryBuilder("select siteid from ZCCatalog where id=?", tCatalogID)
				.executeString();
		map.put("SiteID", tSiteID);
		DataTable dt = new QueryBuilder(
				"select name,id from ZCCatalog where siteid=? and id<>? order by id", tSiteID,
				tCatalogID).executeDataTable();
		map.put("optCatalog", HtmlUtil.dataTableToOptions(dt));
		return map;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.site.CatalogColumn JD-Core Version: 0.5.3
 */