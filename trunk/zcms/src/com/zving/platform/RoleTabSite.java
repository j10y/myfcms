package com.zving.platform;

import com.zving.framework.Page;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataColumn;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.schema.ZDPrivilegeSchema;
import java.util.Map;

public class RoleTabSite extends Page {
	public static void dg1DataBind(DataGridAction dga) {
		String RoleCode = dga.getParam("RoleCode");
		if (StringUtil.isEmpty(RoleCode)) {
			RoleCode = dga.getParam("Role.LastRoleCode");
			if (StringUtil.isEmpty(RoleCode)) {
				dga.bindData(new DataTable());
				return;
			}
		}
		String PrivType = dga.getParam("PrivType");
		StringBuffer sb = new StringBuffer();
		sb.append(",'" + RoleCode + "' as RoleCode");
		Object[] ks = Priv.SITE_MAP.keyArray();
		for (int i = 0; i < Priv.SITE_MAP.size(); ++i) {
			sb.append(",'' as " + ks[i]);
		}

		String sql = "select ID,Name,0 as TreeLevel ,'site' as PrivType" + sb.toString()
				+ " from ZCSite a order by BranchInnerCode ,orderflag ,id";
		dga.setTotal(new QueryBuilder("select count(*) from ZCSite a"));
		DataTable siteDT = new QueryBuilder(sql).executePagedDataTable(dga.getPageSize(), dga
				.getPageIndex());
		Map PrivTypeMap = RolePriv.getPrivTypeMap(RoleCode, PrivType);
		DataRow dr = null;
		for (int i = 0; i < siteDT.getRowCount(); ++i) {
			dr = siteDT.getDataRow(i);
			String ID = dr.getString("ID");
			Map mapRow = (Map) PrivTypeMap.get(ID);
			if (mapRow != null) {
				for (int j = 0; j < dr.getColumnCount(); ++j) {
					if (dr.getDataColumn(j).getColumnName().toLowerCase().indexOf("_") > 0) {
						dr.set(j, ("0".equals(mapRow.get(dr.getDataColumn(j).getColumnName()
								.toLowerCase()))) ? "" : "√");
					}
				}
			}
		}
		dga.bindData(siteDT);
	}

	public void dg1Edit() {
		DataTable dt = (DataTable) this.Request.get("DT");
		String RoleCode = $V("RoleCode");

		Transaction trans = new Transaction();
		ZDPrivilegeSchema p = new ZDPrivilegeSchema();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			for (int j = 0; j < dt.getColCount(); ++j) {
				if (dt.getDataColumn(j).getColumnName().indexOf("_") > 0) {
					trans.add(p.query(new QueryBuilder("where OwnerType='R' and Owner = '"
							+ RoleCode + "' and PrivType = '" + dt.getString(i, "PrivType")
							+ "' and ID = '" + dt.getString(i, "ID") + "' and Code = '"
							+ dt.getDataColumn(j).getColumnName() + "' ")), 5);
				}
			}
		}

		for (int i = 0; i < dt.getRowCount(); ++i) {
			DataRow dr = dt.getDataRow(i);
			for (int j = 0; j < dr.getColumnCount(); ++j) {
				if (dr.getDataColumn(j).getColumnName().indexOf("_") > 0) {
					ZDPrivilegeSchema priv = new ZDPrivilegeSchema();
					priv.setOwnerType("R");
					priv.setOwner(dr.getString("RoleCode"));
					priv.setID(dr.getString("ID"));
					priv.setPrivType(dr.getString("PrivType"));
					priv.setCode(dr.getDataColumn(j).getColumnName());
					priv.setValue(("".equals(dr.getString(j))) ? "0" : "1");
					trans.add(priv, 1);
				}
			}
		}
		if (trans.commit()) {
			RolePriv.updateAllPriv(RoleCode);
			this.Response.setLogInfo(1, "修改成功!");
		} else {
			this.Response.setLogInfo(0, "修改失败!");
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.platform.RoleTabSite JD-Core Version: 0.5.3
 */