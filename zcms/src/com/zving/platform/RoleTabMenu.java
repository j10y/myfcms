package com.zving.platform;

import com.zving.framework.Page;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.Filter;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.schema.ZDPrivilegeSchema;

public class RoleTabMenu extends Page {
	public static Mapx init(Mapx params) {
		String roleCode = params.getString("RoleCode");
		DataTable dt = new QueryBuilder(
				"select name,id from zcsite order by BranchInnerCode ,orderflag ,id")
				.executeDataTable();
		dt = dt.filter(new Filter(roleCode) {
			public boolean filter(Object obj) {
				DataRow dr = (DataRow) obj;
				return RolePriv.getRolePriv(new String[] { (String) this.Param }, "site", dr
						.getString("ID"), "site_browse");
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
		return params;
	}

	public static void dg1DataBind(DataGridAction dga) {
		String sql = "select ID ,Name,Icon,Type,'' as TreeLevel  from ZDMenu where (parentid in (select id from ZDMenu where parentid=0 and visiable='Y') or parentid=0) and visiable='Y'order by OrderFlag";
		DataTable dt = new QueryBuilder(sql).executeDataTable();
		if (!("admin".equals(User.getUserName()))) {
			dt = dt.filter(new Filter() {
				public boolean filter(Object obj) {
					DataRow dr = (DataRow) obj;
					return Priv.getPriv(User.getUserName(), "menu", dr.getString("id"),
							"menu_browse");
				}
			});
		}
		for (int i = 0; i < dt.getRowCount(); ++i) {
			if ("2".equals(dt.get(i, "Type")))
				dt.set(i, "TreeLevel", "1");
			else {
				dt.set(i, "TreeLevel", "0");
			}
		}
		dga.bindData(dt);
	}

	public void getCheckedMenu() {
		String RoleCode = $V("RoleCode");
		if (StringUtil.isEmpty(RoleCode)) {
			RoleCode = $V("Role.LastRoleCode");
			if (StringUtil.isEmpty(RoleCode)) {
				this.Response.put("checkedMenu", "");
				return;
			}
		}
		String SiteID = $V("SiteID");
		String sql = "select ID from ZDPrivilege where OwnerType=? and Owner=? and PrivType='menu' and ID like '"
				+ SiteID + "-%' and Value='1'";
		DataTable dt = new QueryBuilder(sql, "R", RoleCode).executeDataTable();
		this.Response.put("checkedMenu", StringUtil.join(dt.getColumnValues(0)));
	}

	public void save() {
		String RoleCode = $V("RoleCode");
		String SiteID = $V("SiteID");
		DataTable dt = (DataTable) this.Request.get("dt");
		Transaction trans = new Transaction();
		QueryBuilder qb = new QueryBuilder(
				"where OwnerType=? and Owner=? and PrivType='menu' and ID like '" + SiteID + "-%'",
				"R", RoleCode);
		trans.add(new ZDPrivilegeSchema().query(qb), 5);
		for (int i = 0; i < dt.getRowCount(); ++i) {
			if ("1".equals(dt.getString(i, "menu_browse"))) {
				ZDPrivilegeSchema priv = new ZDPrivilegeSchema();
				priv.setOwnerType("R");
				priv.setOwner(RoleCode);
				priv.setID(SiteID + "-" + dt.getString(i, "ID"));
				priv.setPrivType("menu");
				priv.setCode("menu_browse");
				priv.setValue(dt.getString(i, "menu_browse"));
				trans.add(priv, 1);
			}
		}

		if (trans.commit()) {
			RolePriv.updatePriv(RoleCode, "menu");
			this.Response.setLogInfo(1, "修改成功!");
		} else {
			this.Response.setLogInfo(0, "修改失败!");
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.platform.RoleTabMenu JD-Core Version: 0.5.3
 */