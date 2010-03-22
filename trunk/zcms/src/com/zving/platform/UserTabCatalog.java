package com.zving.platform;

import com.zving.cms.pub.PubFun;
import com.zving.framework.Page;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataColumn;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.Filter;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.schema.ZDPrivilegeSchema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class UserTabCatalog extends Page {
	public static Mapx init(Mapx params) {
		String userName = params.getString("UserName");
		DataTable dt = new QueryBuilder(
				"select name,id from zcsite order by BranchInnerCode ,orderflag ,id")
				.executeDataTable();
		dt = dt.filter(new Filter(userName) {
			public boolean filter(Object obj) {
				DataRow dr = (DataRow) obj;
				return Priv.getPriv((String) this.Param, "site", dr.getString("ID"), "site_browse");
			}
		});
		String SiteID = params.getString("SiteID");
		if (StringUtil.isEmpty(SiteID)) {
			SiteID = params.getString("OldSiteID");
			if (StringUtil.isEmpty(SiteID)) {
				SiteID = String.valueOf(Application.getCurrentSiteID());
			}
		}
		params.put("SiteID", HtmlUtil.dataTableToOptions(dt, SiteID));

		String PrivType = params.getString("PrivType");
		if (StringUtil.isEmpty(PrivType)) {
			PrivType = params.getString("OldPrivType");
			if (StringUtil.isEmpty(PrivType)) {
				PrivType = "article";
			}
		}
		params.put("PrivType", HtmlUtil.mapxToOptions(RoleTabCatalog.PrivTypeMap, PrivType));
		return params;
	}

	public static void dg1DataBind(DataGridAction dga) {
		String userName = dga.getParam("UserName");
		String siteID = dga.getParam("SiteID");
		if (StringUtil.isEmpty(siteID)) {
			siteID = dga.getParam("OldSiteID");
			if (StringUtil.isEmpty(siteID)) {
				siteID = String.valueOf(Application.getCurrentSiteID());
			}
			if ((StringUtil.isNotEmpty(siteID))
					&& (!(Priv.getPriv(userName, "site", siteID, "site_browse")))) {
				siteID = "";
			}
		}

		if (StringUtil.isEmpty(siteID)) {
			DataTable dt = new QueryBuilder(
					"select name,id from zcsite order by BranchInnerCode ,orderflag ,id")
					.executeDataTable();
			dt = dt.filter(new Filter(userName) {
				public boolean filter(Object obj) {
					DataRow dr = (DataRow) obj;
					return Priv.getPriv((String) this.Param, "site", dr.getString("ID"),
							"site_browse");
				}
			});
			if (dt.getRowCount() > 0) {
				siteID = dt.getString(0, "ID");
			} else {
				dga.bindData(new DataTable());
				return;
			}
		}

		String PrivType = dga.getParam("PrivType");
		if (StringUtil.isEmpty(PrivType)) {
			PrivType = dga.getParam("OldPrivType");
			if (StringUtil.isEmpty(PrivType)) {
				PrivType = "article";
			}
		}

		StringBuffer sb = new StringBuffer();
		sb.append(",'" + userName + "' as UserName");
		Object[] ks = ((Mapx) Priv.PRIV_MAP.get(PrivType)).keyArray();
		for (int i = 0; i < ((Mapx) Priv.PRIV_MAP.get(PrivType)).size(); ++i) {
			sb.append(",'' as " + ks[i]);
		}

		String sql = "select ID,Name,0 as TreeLevel ,'site' as PrivType"
				+ sb.toString().replaceAll("''", "''") + " from ZCSite a where a.ID = ?";
		DataTable siteDT = new QueryBuilder(sql, siteID).executeDataTable();

		String catalogType = RoleTabCatalog.CatalogTypeMap.getString(PrivType);
		sql = "select InnerCode as ID,Name,TreeLevel ,'" + PrivType + "' as PrivType"
				+ sb.toString() + " from ZCCatalog a where Type =" + catalogType
				+ " and a.SiteID = " + siteID + " order by orderflag,innercode ";
		DataTable catalogDT = new QueryBuilder(sql).executeDataTable();

		DataRow dr = null;
		int j;
		String columnName;
		for (int i = 0; i < siteDT.getRowCount(); ++i) {
			dr = siteDT.getDataRow(i);
			for (j = 0; j < dr.getColumnCount(); ++j) {
				columnName = dr.getDataColumn(j).getColumnName().toLowerCase();
				if (columnName.indexOf("_") > 0) {
					dr.set(j,
							(Priv.getPriv(userName, "site", dr.getString("ID"), columnName)) ? "√"
									: "");
				}
			}
		}

		for (int i = 0; i < catalogDT.getRowCount(); ++i) {
			dr = catalogDT.getDataRow(i);
			for (j = 0; j < dr.getColumnCount(); ++j) {
				columnName = dr.getDataColumn(j).getColumnName().toLowerCase();
				if (columnName.indexOf("_") > 0) {
					dr
							.set(j, (Priv.getPriv(userName, PrivType, dr.getString("ID"),
									columnName)) ? "√" : "");
				}
			}
		}

		catalogDT.insertRow(siteDT.getDataRow(0), 0);
		dga.bindData(catalogDT);
	}

	public void dg1Edit() {
		DataTable resultDT = (DataTable) this.Request.get("DT");
		Transaction trans = new Transaction();
		String UserName = $V("UserName");
		String PrivType = $V("PrivType");
		ZDPrivilegeSchema p = new ZDPrivilegeSchema();
		for (int i = 0; i < resultDT.getRowCount(); ++i) {
			for (int j = 0; j < resultDT.getColCount(); ++j) {
				if (resultDT.getDataColumn(j).getColumnName().indexOf("_") > 0) {
					if ("√".equals(resultDT.getString(i, j)))
						resultDT.set(i, j, "1");
					else {
						resultDT.set(i, j, "0");
					}
					trans.add(p.query(new QueryBuilder("where OwnerType='U' and Owner = '"
							+ UserName + "' and PrivType = '" + resultDT.getString(i, "PrivType")
							+ "' and ID = '" + resultDT.getString(i, "ID") + "' and Code = '"
							+ resultDT.getDataColumn(j).getColumnName() + "' ")), 5);
				}
			}
		}

		String[] RoleCodes = new String[0];
		List roleCodeList = PubFun.getRoleCodesByUserName(UserName);
		if ((roleCodeList != null) && (roleCodeList.size() != 0)) {
			RoleCodes = (String[]) roleCodeList.toArray(new String[roleCodeList.size()]);
		}
		DataColumn[] types = resultDT.getDataColumns();
		DataColumn[] copyTypes = new DataColumn[types.length];
		System.arraycopy(types, 0, copyTypes, 0, types.length);
		Object[][] copyValues = new Object[resultDT.getRowCount()][types.length];
		for (int i = 0; i < copyValues.length; ++i) {
			System.arraycopy(resultDT.getDataRow(i).getDataValues(), 0, copyValues[i], 0,
					types.length);
		}
		DataTable rolePrivDT = new DataTable(copyTypes, copyValues);
		int j;
		for (int i = 0; (i < rolePrivDT.getRowCount()) && (i < 1); ++i) {
			for (j = 0; j < rolePrivDT.getColCount(); ++j) {
				if (rolePrivDT.getDataColumn(j).getColumnName().indexOf("_") > 0) {
					rolePrivDT.set(i, j,
							(RolePriv.getRolePriv(RoleCodes, "site", rolePrivDT.getString(i, "ID"),
									rolePrivDT.getDataColumn(j).getColumnName())) ? "1" : "0");
				}
			}
		}
		for (int i = 1; i < rolePrivDT.getRowCount(); ++i) {
			for (j = 0; j < rolePrivDT.getColCount(); ++j) {
				if (rolePrivDT.getDataColumn(j).getColumnName().indexOf("_") > 0) {
					rolePrivDT.set(i, j,
							(RolePriv.getRolePriv(RoleCodes, PrivType, rolePrivDT
									.getString(i, "ID"), rolePrivDT.getDataColumn(j)
									.getColumnName())) ? "1" : "0");
				}
			}

		}

		String v1 = null;
		String v2 = null;
		for (int i = 0; i < rolePrivDT.getRowCount(); ++i) {
			for (j = 0; j < rolePrivDT.getColCount(); ++j) {
				if (rolePrivDT.getDataColumn(j).getColumnName().indexOf("_") > 0) {
					v1 = rolePrivDT.getString(i, j);
					v2 = resultDT.getString(i, j);
					if (v1.equals(v2)) {
						resultDT.set(i, j, "0");
					} else if ("0".equals(v1)) {
						if ("1".equals(v2))
							resultDT.set(i, j, "1");
					} else {
						if ((!("1".equals(v1))) || (!("0".equals(v2))))
							continue;
						resultDT.set(i, j, "-1");
					}
				}
			}

		}

		Stack stack = new Stack();
		Map map = new HashMap();
		int lastLevel = 0;
		int level = 0;
		for (int i = 0; i < resultDT.getRowCount(); ++i) {
			DataRow dr = resultDT.getDataRow(i);
			if (i == 0) {
				stack.push(dr);
				StringBuffer privSB = new StringBuffer();
				for (j = 0; j < dr.getColumnCount(); ++j) {
					if (dr.getDataColumn(j).getColumnName().indexOf("_") > 0) {
						privSB.append(dr.getString(j));
					}
				}
				map.put(dr, privSB.toString());
				for (j = 0; j < dr.getColumnCount(); ++j)
					if (dr.getDataColumn(j).getColumnName().indexOf("_") > 0) {
						ZDPrivilegeSchema priv = new ZDPrivilegeSchema();
						priv.setOwnerType("U");
						priv.setOwner(UserName);
						priv.setID(dr.getString("ID"));
						priv.setPrivType("site");
						priv.setCode(dr.getDataColumn(j).getColumnName());
						priv.setValue(dr.getString(j));
						trans.add(priv, 1);
					}
			} else {
				DataRow parentDR = (DataRow) stack.peek();
				level = Integer.parseInt(dr.getString("treelevel"));
				while (Integer.parseInt(parentDR.getString("treelevel")) >= level) {
					stack.pop();
					parentDR = (DataRow) stack.peek();
				}
				if (level != lastLevel) {
					stack.push(dr);
				}
				lastLevel = level;
				StringBuffer privSB = new StringBuffer();
				for (j = 0; j < dr.getColumnCount(); ++j) {
					if (dr.getDataColumn(j).getColumnName().indexOf("_") > 0) {
						privSB.append(dr.getString(j));
					}
				}
				map.put(dr, privSB.toString());

				if (map.get(dr).equals(map.get(parentDR))) {
					continue;
				}
				for (j = 0; j < dr.getColumnCount(); ++j) {
					if (dr.getDataColumn(j).getColumnName().indexOf("_") > 0) {
						ZDPrivilegeSchema priv = new ZDPrivilegeSchema();
						priv.setOwnerType("U");
						priv.setOwner(UserName);
						priv.setID(dr.getString("ID"));
						priv.setPrivType(PrivType);
						priv.setCode(dr.getDataColumn(j).getColumnName());
						priv.setValue(dr.getString(j));
						trans.add(priv, 1);
					}
				}
			}
		}

		if (trans.commit()) {
			Priv.updateAllPriv(UserName);
			this.Response.setLogInfo(1, "修改成功!");
		} else {
			this.Response.setLogInfo(0, "修改失败!");
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.platform.UserTabCatalog JD-Core Version: 0.5.3
 */