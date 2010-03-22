package com.zving.cms.pub;

import com.zving.cms.resource.ConfigImageLib;
import com.zving.cms.site.Site;
import com.zving.framework.Config;
import com.zving.framework.Constant;
import com.zving.framework.User;
import com.zving.framework.data.DBConn;
import com.zving.framework.data.DBConnPool;
import com.zving.framework.data.DataAccess;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.license.LicenseInfo;
import com.zving.framework.messages.LongTimeTask;
import com.zving.framework.orm.Schema;
import com.zving.framework.orm.SchemaColumn;
import com.zving.framework.orm.SchemaSet;
import com.zving.framework.orm.SchemaUtil;
import com.zving.framework.orm.TableCreator;
import com.zving.framework.utility.CharsetConvert;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.LogUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.NumberUtil;
import com.zving.framework.utility.StringUtil;
import com.zving.framework.utility.ZipUtil;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCSiteSchema;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class SiteImporter {
	private long siteID;
	private long newSiteID;
	private DataAccess da;
	private String ExportCharset;
	private String file;
	private LongTimeTask task;
	private String OldAlias;
	private Mapx map = new Mapx();

	private Mapx customTableMap = new Mapx();
	private boolean isNewSite;
	private SiteTableRela.NoType[] NoRelas;
	private SiteTableRela.TableRela[] TableRelas;
	private QueryBuilder insertQB = null;

	private Mapx idMap = new Mapx();

	public SiteImporter(String file) {
		this.file = file;
		this.task = LongTimeTask.createEmptyInstance();
	}

	public SiteImporter(String file, LongTimeTask task) {
		this.file = file;
		this.task = task;
	}

	public boolean importSite() {
		return importSite(null);
	}

	public boolean importSite(String poolName) {
		User user = new User();
		user.UserName = "admin";
		user.BranchInnerCode = "86";
		user.isLogin = true;
		user.isManager = true;
		User.setCurrent(user);

		DBConn conn = DBConnPool.getConnection(poolName);
		this.isNewSite = (("0".equals(this.map.getString("ID"))) || (StringUtil.isEmpty(this.map
				.getString("ID"))));
		this.NoRelas = SiteTableRela.getNoRelaArray();
		this.TableRelas = SiteTableRela.getRelas();
		FileInputStream fin = null;
		this.da = new DataAccess(conn);
		try {
			fin = new FileInputStream(this.file);
			this.da.setAutoCommit(false);
			this.ExportCharset = ((fin.read() == 1) ? "GBK" : "UTF-8");
			byte[] bs = new byte[8];
			fin.read(bs);
			this.siteID = NumberUtil.toLong(bs);
			boolean flag = true;
			int i = 0;
			while (true) {
				bs = new byte[4];
				if (!(bufferRead(bs, fin))) {
					break;
				}
				int len = NumberUtil.toInt(bs);
				bs = new byte[len];
				if (!(bufferRead(bs, fin))) {
					flag = false;
					break;
				}
				Object obj = FileUtil.unserialize(bs);

				bs = new byte[4];
				if (!(bufferRead(bs, fin))) {
					flag = false;
					break;
				}
				len = NumberUtil.toInt(bs);
				bs = new byte[len];
				if (!(bufferRead(bs, fin))) {
					flag = false;
					break;
				}
				bs = ZipUtil.unzip(bs);
				this.task.setPercent(i * 100 / 200);
				dealOneEntry(bs, obj);
				++i;
			}
			if (flag) {
				this.da.commit();
			} else {
				LogUtil.error("读取站点导出文件时发生错误!");
				this.da.rollback();
			}
			this.da.setAutoCommit(true);
			if (this.isNewSite) {
				Site.updatePrivAndFile(this.map.getString("Alias"));
			} else {
				String root = Config.getContextRealPath() + Config.getValue("Statical.TargetDir")
						+ "/" + this.map.getInt("Alias");
				if (Config.getOSName().toLowerCase().indexOf("windows") >= 0) {
					if (!(this.map.getString("Alias").equalsIgnoreCase(this.OldAlias))) {
						FileUtil.delete(root);
					}
				} else if (!(this.map.getString("Alias").equals(this.OldAlias))) {
					FileUtil.delete(root);
				}

			}

			return flag;
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				this.da.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		} finally {
			try {
				this.da.setAutoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				this.da.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				fin.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean bufferRead(byte[] bs, FileInputStream fin) {
		try {
			return (fin.read(bs) != -1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void dealOneEntry(byte[] bs, Object obj) throws Exception {
		if ((bs == null) || (obj == null)) {
			return;
		}
		if (obj instanceof String) {
			String name = obj.toString();
			if (name.startsWith("File:")) {
				dealFile(name, bs);
			} else if (name.startsWith("CustomTable:")) {
				name = name.substring("CustomTable:".length());
				this.task.setCurrentInfo("正在导入自定义表:" + name);
				DataTable dt = (DataTable) FileUtil.unserialize(bs);
				try {
					if (!(this.customTableMap.containsKey(name))) {
						QueryBuilder qb = new QueryBuilder(
								"select * from ZCCustomTableColumn where TableID in (select ID from ZCCustomTable where SiteID=? and Code=? and Type='Custom')",
								this.newSiteID, name);
						DataTable cdt = this.da.executeDataTable(qb);
						SchemaColumn[] scs = new SchemaColumn[cdt.getRowCount()];
						for (int j = 0; j < scs.length; ++j) {
							DataRow cdr = cdt.getDataRow(j);
							int type = Integer.parseInt(cdr.getString("DataType"));
							SchemaColumn sc = new SchemaColumn(cdr.getString("Code"), type, j, cdr
									.getInt("Length"), 0, "Y".equals(cdr.getString("isMandatory")),
									"Y".equals(cdr.getString("isPrimaryKey")));
							scs[j] = sc;
						}
						TableCreator tc = new TableCreator(this.da.getConnection().getDBType());
						tc.createTable(scs, name, true);
						tc.executeAndClear(this.da.getConnection());
						this.customTableMap.put(name, "");
						StringBuffer sb = new StringBuffer("insert into " + name + "(");
						for (int j = 0; j < cdt.getRowCount(); ++j) {
							if (j != 0) {
								sb.append(",");
							}
							sb.append(cdt.get(j, "Code"));
						}
						sb.append(") values (");
						for (int j = 0; j < cdt.getRowCount(); ++j) {
							if (j != 0) {
								sb.append(",");
							}
							sb.append("?");
						}
						sb.append(")");
						this.insertQB = new QueryBuilder(sb.toString());
						this.insertQB.setBatchMode(true);
					}
					dealCustomTable(dt, this.insertQB);
				} catch (Exception e) {
					LogUtil.warn("未成功导入表" + name);
					e.printStackTrace();
				}
			} else {
				dealSiteIDTable(name, (DataTable) FileUtil.unserialize(bs));
			}
		} else {
			dealRelaTable((SiteTableRela.TableRela) obj, (DataTable) FileUtil.unserialize(bs));
		}
	}

	private void dealCustomTable(DataTable dt, QueryBuilder qb) throws Exception {
		try {
			qb.clearBatches();
			qb.getParams().clear();
			for (int i = 0; i < dt.getRowCount(); ++i) {
				for (int j = 0; j < dt.getColCount() - 1; ++j) {
					String v = dt.getString(i, j);
					if (StringUtil.isEmpty(v)) {
						v = null;
					}
					qb.add(v);
				}
				qb.addBatch();
			}
			this.da.executeNoQuery(qb);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void dealFile(String fileName, byte[] bs) {
		try {
			bs = convertCharset(bs);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		fileName = fileName.substring("File:".length());
		this.task.setCurrentInfo("正在导入站点文件:" + fileName);
		String root = Config.getContextRealPath() + Config.getValue("Statical.TargetDir") + "/"
				+ this.map.getString("Alias");
		root = root + "/";
		fileName = root + fileName;
		fileName = fileName.replaceAll("[\\\\/]+", "/");
		String dirName = fileName.substring(0, fileName.lastIndexOf(47));
		File dir = new File(dirName);
		if (!(dir.exists())) {
			dir.mkdirs();
		}
		FileUtil.writeByte(fileName, bs);
	}

	public void dealZCSite(DataRow dr) {
		ZCSiteSchema site;
		if (this.isNewSite) {
			if ((LicenseInfo.getName().equals("TrailUser"))
					&& (new QueryBuilder("select count(*) from ZCSite").executeInt() >= 1)) {
				throw new RuntimeException("站点数超出限制，请联系泽元软件更换License！");
			}
			this.newSiteID = NoUtil.getMaxID("SiteID");
			site = new ZCSiteSchema();
			site.setValue(dr);
			site.setID(this.newSiteID);
			site.setName(this.map.getString("Name"));
			site.setAlias(this.map.getString("Alias"));
			site.setURL(this.map.getString("URL"));
			site.setHitCount(0L);
			site.setChannelCount(0L);
			site.setSpecialCount(0L);
			site.setMagzineCount(0L);
			site.setArticleCount(0L);
			site.setImageLibCount(1L);
			site.setVideoLibCount(1L);
			site.setAudioLibCount(1L);
			site.setAttachmentLibCount(1L);
			site.setBranchInnerCode(User.getBranchInnerCode());
			site.setAddTime(new Date());
			site.setAddUser(User.getUserName());
			site.setConfigXML(ConfigImageLib.imageLibConfigDefault);
			this.da.insert(site);
			Transaction trans = new Transaction();
			Site.addDefaultPriv(this.newSiteID, trans);
			trans.setDataAccess(this.da);
			trans.commit(false);
			addIDMapping("ZCSite", String.valueOf(this.siteID), String.valueOf(this.newSiteID));
		} else {
			site = new ZCSiteSchema();
			site.setID(this.map.getString("ID"));
			this.newSiteID = site.getID();
			site.fill();
			this.OldAlias = site.getAlias();
			site.setValue(dr);
			site.setID(this.map.getString("ID"));
			site.setName(this.map.getString("Name"));
			site.setAlias(this.map.getString("Alias"));
			site.setURL(this.map.getString("URL"));
			this.da.update(site);
			addIDMapping("ZCSite", String.valueOf(site.getID()), String.valueOf(site.getID()));
		}
	}

	public void dealSiteIDTable(String tableName, DataTable dt) throws Exception {
		this.task.setCurrentInfo("正在向表" + tableName + "插入站点数据");
		if (tableName.equalsIgnoreCase("ZCSite")) {
			dealZCSite(dt.getDataRow(0));
		} else {
			SchemaSet set = (SchemaSet) Class.forName("com.zving.schema." + tableName + "Set")
					.newInstance();
			if ((this.isNewSite) && (!(tableName.equalsIgnoreCase("ZDMember")))) {
				for (int i = 0; i < dt.getRowCount(); ++i) {
					Schema schema = (Schema) Class.forName(
							"com.zving.schema." + tableName + "Schema").newInstance();
					DataRow dr = dt.getDataRow(i);
					setSiteIDTableMaxNo(tableName, dr, true);
					schema.setValue(dt.getDataRow(i));
					set.add(schema);
				}

				this.da.insert(set);
			} else {
				Schema schema = (Schema) Class.forName("com.zving.schema." + tableName + "Schema")
						.newInstance();
				SchemaColumn[] scs = SchemaUtil.getColumns(schema);
				QueryBuilder qb = new QueryBuilder("select count(1) from " + tableName
						+ " where 1=1 ");
				for (int i = 0; i < scs.length; ++i) {
					if (scs[i].isPrimaryKey()) {
						qb.appendSQLPart(" and " + scs[i].getColumnName() + "=?");
					}
				}
				for (int i = 0; i < dt.getRowCount(); ++i) {
					schema = (Schema) Class.forName("com.zving.schema." + tableName + "Schema")
							.newInstance();
					DataRow dr = dt.getDataRow(i);

					qb.getParams().clear();
					for (int j = 0; j < scs.length; ++j) {
						if (scs[j].isPrimaryKey()) {
							qb.add(dr.get(scs[j].getColumnName()));
						}
					}
					int count = qb.executeInt();
					if (count > 0)
						setSiteIDTableMaxNo(tableName, dr, true);
					else {
						setSiteIDTableMaxNo(tableName, dr, false);
					}
					schema.setValue(dt.getDataRow(i));
					set.add(schema);
				}

				this.da.deleteAndInsert(set);
			}
		}
	}

	public void setSiteIDTableMaxNo(String tableName, DataRow dr, boolean newIDFlag) {
		try {
			if (dr.getDataColumn("SiteID") != null)
				dr.set("SiteID", getIDMapping("ZCSite", String.valueOf(this.siteID)));
		} catch (Throwable t) {
			t.printStackTrace();
			LogUtil.warn(tableName);
		}
		String id;
		for (int i = 0; i < this.NoRelas.length; ++i) {
			SiteTableRela.NoType nr = this.NoRelas[i];
			if (nr.TableCode.equalsIgnoreCase(tableName)) {
				id = dr.getString(nr.FieldName);
				if (newIDFlag) {
					if ((nr.TableCode.equals("ZCCatalog")) && (nr.FieldName.equals("InnerCode")))
						if (id.length() == 6) {
							id = CatalogUtil.createCatalogInnerCode(null);
						} else {
							String parent = id.substring(0, id.length() - 6);
							parent = getIDMapping(nr.TableCode + "." + nr.FieldName, parent);
							id = CatalogUtil.createCatalogInnerCode(parent);
						}
					else {
						id = String.valueOf(NoUtil.getMaxID(nr.NoType));
					}
				}
				String type = nr.TableCode;
				if ((((type.equals("ZCArticle")) || (type.equals("ZCAttachment"))
						|| (type.equals("ZCImage")) || (type.equals("ZCAudio")) || (type
						.equals("ZCVideo"))))
						&& (nr.NoType.equals("DocID"))) {
					type = "Document";
				}

				addIDMapping(type + "." + nr.FieldName, dr.getString(nr.FieldName), id);
				dr.set(nr.FieldName, id);
			}
		}
		for (int i = 0; i < this.TableRelas.length; ++i)
			if (this.TableRelas[i].TableCode.equals(tableName)) {
				String type = this.TableRelas[i].RelaTable;
				if ((((type.equals("ZCArticle")) || (type.equals("ZCAttachment"))
						|| (type.equals("ZCImage")) || (type.equals("ZCAudio")) || (type
						.equals("ZCVideo"))))
						&& (this.TableRelas[i].RelaField.equals("ID"))) {
					type = "Document";
				}

				id = getIDMapping(type + "." + this.TableRelas[i].RelaField, dr
						.getString(this.TableRelas[i].KeyField));
				if (StringUtil.isNotEmpty(id))
					dr.set(this.TableRelas[i].KeyField, id);
			}
	}

	public void setRelaTableMaxNo(SiteTableRela.TableRela tr, DataRow dr, boolean newIDFlag) {
		String id;
		for (int i = 0; i < this.NoRelas.length; ++i) {
			SiteTableRela.NoType nr = this.NoRelas[i];
			if (nr.TableCode.equalsIgnoreCase(tr.TableCode)) {
				id = dr.getString(nr.FieldName);
				if (newIDFlag) {
					id = String.valueOf(NoUtil.getMaxID(nr.NoType));
				}
				addIDMapping(nr.TableCode + "." + nr.FieldName, dr.getString(nr.FieldName), id);
				dr.set(nr.FieldName, id);
			}
		}
		for (int i = 0; i < this.TableRelas.length; ++i)
			if (this.TableRelas[i].TableCode.equals(tr.TableCode)) {
				String type = this.TableRelas[i].RelaTable;
				if ((((type.equals("ZCArticle")) || (type.equals("ZCAttachment"))
						|| (type.equals("ZCImage")) || (type.equals("ZCAudio")) || (type
						.equals("ZCVideo"))))
						&& (this.TableRelas[i].RelaField.equals("ID"))) {
					type = "Document";
				}

				id = getIDMapping(type + "." + this.TableRelas[i].RelaField, dr
						.getString(this.TableRelas[i].KeyField));
				if (StringUtil.isNotEmpty(id))
					dr.set(this.TableRelas[i].KeyField, id);
			}
	}

	public void dealRelaTable(SiteTableRela.TableRela tr, DataTable dt) throws Exception {
		System.out.println("正在向表" + tr.TableCode + "插入站点数据");
		if (!(tr.isExportData)) {
			return;
		}
		SchemaSet set = (SchemaSet) Class.forName("com.zving.schema." + tr.TableCode + "Set")
				.newInstance();
		if (this.isNewSite) {
			for (int i = 0; i < dt.getRowCount(); ++i) {
				Schema schema = (Schema) Class.forName(
						"com.zving.schema." + tr.TableCode + "Schema").newInstance();
				DataRow dr = dt.getDataRow(i);
				setRelaTableMaxNo(tr, dr, true);
				schema.setValue(dt.getDataRow(i));
				set.add(schema);
			}
			this.da.insert(set);
		} else {
			Schema schema = (Schema) Class.forName("com.zving.schema." + tr.TableCode + "Schema")
					.newInstance();
			SchemaColumn[] scs = SchemaUtil.getColumns(schema);
			QueryBuilder qb = new QueryBuilder("select count(1) from " + tr.TableCode
					+ " where 1=1 ");
			for (int i = 0; i < scs.length; ++i) {
				if (scs[i].isPrimaryKey()) {
					qb.appendSQLPart(" and " + scs[i].getColumnName() + "=?");
				}
			}
			for (int i = 0; i < dt.getRowCount(); ++i) {
				schema = (Schema) Class.forName("com.zving.schema." + tr.TableCode + "Schema")
						.newInstance();
				DataRow dr = dt.getDataRow(i);

				qb.getParams().clear();
				for (int j = 0; j < scs.length; ++j) {
					if (scs[j].isPrimaryKey()) {
						qb.add(dr.get(scs[j].getColumnName()));
					}
				}
				int count = qb.executeInt();
				if (count > 0)
					setRelaTableMaxNo(tr, dr, true);
				else {
					setRelaTableMaxNo(tr, dr, false);
				}
				schema.setValue(dt.getDataRow(i));
				set.add(schema);
			}
			this.da.deleteAndInsert(set);
		}
	}

	public static Mapx getSiteInfo(String file) throws Exception {
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			fin.read();
			byte[] bs = new byte[8];
			fin.read(bs);

			bs = new byte[4];
			if (!(bufferRead(bs, fin))) {
				return null;
			}
			int len = NumberUtil.toInt(bs);
			bs = new byte[len];
			if (!(bufferRead(bs, fin))) {
				return null;
			}

			bs = new byte[4];
			if (!(bufferRead(bs, fin))) {
				return null;
			}
			len = NumberUtil.toInt(bs);
			bs = new byte[len];
			if (!(bufferRead(bs, fin))) {
				return null;
			}
			bs = ZipUtil.unzip(bs);
			DataTable dt = (DataTable) FileUtil.unserialize(bs);
			return dt.getDataRow(0).toCaseIgnoreMapx();
		} finally {
			if (fin != null)
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public void setSiteInfo(Mapx map) {
		this.map = map;
	}

	public byte[] convertCharset(byte[] bs) throws UnsupportedEncodingException {
		if ((Constant.GlobalCharset.equals("GBK")) && (this.ExportCharset.equals("UTF-8"))) {
			return CharsetConvert.webFileUTF8ToGBK(bs);
		}
		if ((Constant.GlobalCharset.equals("UTF-8")) && (this.ExportCharset.equals("GBK"))) {
			return CharsetConvert.webFileGBKToUTF8(bs);
		}
		return bs;
	}

	public String getIDMapping(String type, String oldID) {
		Object obj = this.idMap.get(type);
		if (StringUtil.isEmpty(oldID)) {
			return null;
		}
		if (obj == null) {
			LogUtil.info("站点导入时未找到ID映射关系：Type=" + type + ",OldID=" + oldID);
			return null;
		}
		Mapx map = (Mapx) obj;
		return map.getString(oldID);
	}

	public void addIDMapping(String type, String oldID, String newID) {
		Object obj = this.idMap.get(type);
		if (obj == null) {
			obj = new Mapx();
			this.idMap.put(type, obj);
		}
		Mapx map = (Mapx) obj;
		map.put(oldID, newID);
	}

	public static void main(String[] args) {
		Mapx map = new Mapx();
		map.put("ID", "0");
		map.put("Name", "导入测试");
		map.put("Alias", "ImportTest");
		map.put("URL", "http://import.test.com");
		SiteImporter si = new SiteImporter("G:/zving.dat");
		si.setSiteInfo(map);
		si.importSite();
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.pub.SiteImporter JD-Core Version: 0.5.3
 */