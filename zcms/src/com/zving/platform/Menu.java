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
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZDMenuSchema;
import com.zving.schema.ZDMenuSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Menu extends Page {
	public static Mapx MenuCacheMap = new Mapx();

	static {
		updateCache();
	}

	private static void updateCache() {
		String sql = "select * from ZDMenu where (parentid in(select id from ZDMenu where parentid=0 and visiable='Y') or parentid=0) and visiable='Y' order by OrderFlag,id";
		DataTable dt = new QueryBuilder(sql).executeDataTable();
		for (int i = 0; i < dt.getRowCount(); ++i)
			MenuCacheMap.put(dt.getString(i, "URL"), dt.getString(i, "ID"));
	}

	public static void dg1DataBind(DataGridAction dga) {
		String sql = "select ID,ParentID,Name,Icon,URL,Visiable,Addtime,Memo,Type,'' as Expand,'' as TreeLevel from ZDMenu order by OrderFlag,id";
		DataTable dt = new QueryBuilder(sql).executeDataTable();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			if ("1".equals(dt.get(i, "Type")))
				dt.set(i, "Expand", "Y");
			else {
				dt.set(i, "Expand", "N");
			}
			if ("2".equals(dt.get(i, "Type")))
				dt.set(i, "TreeLevel", "1");
			else {
				dt.set(i, "TreeLevel", "0");
			}
		}
		dga.bindData(dt);
	}

	public void dg1Edit() {
		DataTable dt = (DataTable) this.Request.get("DT");
		ZDMenuSet set = new ZDMenuSet();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			ZDMenuSchema menu = new ZDMenuSchema();
			menu.setID(Integer.parseInt(dt.getString(i, "ID")));
			menu.fill();
			menu.setName(dt.getString(i, "Name"));
			menu.setURL(dt.getString(i, "URL"));
			menu.setMemo(dt.getString(i, "Memo"));
			menu.setIcon(dt.getString(i, "Icon"));
			menu.setVisiable(dt.getString(i, "Visiable"));
			if (menu.getParentID() == 0L) {
				if (dt.getString(i, "Expand").equals("Y"))
					menu.setType("1");
				else {
					menu.setType("3");
				}
			}
			set.add(menu);
		}
		if (set.update()) {
			updateCache();
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("发生错误!");
		}
	}

	public static Mapx init(Mapx params) {
		Mapx map = new Mapx();
		DataTable dt = new QueryBuilder(
				"select name,id from zdmenu where ParentID=0 order by OrderFlag,id")
				.executeDataTable();
		map.put("ParentMenu", HtmlUtil.dataTableToOptions(dt));
		return map;
	}

	public void add() {
		ZDMenuSchema menu = new ZDMenuSchema();
		menu.setIcon($V("Icon").substring($V("Icon").indexOf("Icons/")));
		menu.setID(NoUtil.getMaxID("MenuID"));
		menu.setAddTime(new Date());
		menu.setAddUser(User.getUserName());
		menu.setMemo($V("Memo"));
		menu.setName($V("Name"));
		menu.setURL($V("URL"));
		menu.setVisiable($V("Visiable"));
		menu.setParentID(Long.parseLong($V("ParentID")));
		DataTable parentDT = new QueryBuilder(
				"select * from zdmenu where parentID = ? order by orderflag,id", $V("ParentID"))
				.executeDataTable();
		if ("0".equals($V("ParentID"))) {
			parentDT = new QueryBuilder("select * from zdmenu order by orderflag,id")
					.executeDataTable();
		}
		long orderflag = 0L;
		if ((parentDT != null) && (parentDT.getRowCount() > 0)) {
			orderflag = Long.parseLong(parentDT.getString(parentDT.getRowCount() - 1, "OrderFlag"));
		} else {
			orderflag = Long.parseLong(new QueryBuilder(
					"select OrderFlag from zdmenu where ID = ?", $V("ParentID")).executeString());
			if ("0".equals($V("ParentID"))) {
				orderflag = 0L;
			}
		}
		menu.setOrderFlag(orderflag + 1L);
		Transaction trans = new Transaction();
		if (menu.getParentID() == 0L)
			menu.setType("1");
		else {
			menu.setType("2");
		}

		trans.add(new QueryBuilder(
				"update zdmenu set orderflag = orderflag + 1 where orderflag > ?", orderflag));
		trans.add(menu, 1);
		if (trans.commit()) {
			updateCache();
			this.Response.setStatus(1);
			this.Response.setMessage("添加成功!");
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("添加失败，操作数据库时发生错误!");
		}
	}

	public void del() {
		String ids = $V("IDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}
		ZDMenuSchema menu = new ZDMenuSchema();
		ZDMenuSet set = menu.query(new QueryBuilder("where id in (" + ids + ")"));
		StringBuffer menuLog = new StringBuffer("删除菜单：");
		for (int i = 0; i < set.size(); ++i) {
			menu = set.get(i);
			if (menu.getParentID() == 0L) {
				long count = new QueryBuilder("select count(*) from zdmenu where parentid="
						+ menu.getID() + " and id not in (" + ids + ")").executeLong();
				if (count > 0L) {
					this.Response.setStatus(0);
					UserLog.log("System", "DelMenu", "删除菜单" + menu.getName() + "失败", this.Request
							.getClientIP());
					this.Response.setMessage("不能删除菜单\"" + menu.getName() + "\",该菜单下还有子菜单未被删除!");
					return;
				}
			}
			menuLog.append(menu.getName() + ",");
		}
		if (set.delete()) {
			updateCache();
			this.Response.setStatus(1);
			UserLog.log("System", "DelMenu", menuLog + "成功", this.Request.getClientIP());
			this.Response.setMessage("删除成功!");
		} else {
			this.Response.setStatus(0);
			UserLog.log("System", "DelMenu", menuLog + "失败", this.Request.getClientIP());
			this.Response.setMessage("删除失败，操作数据库时发生错误!");
		}
	}

	public void sortMenu() {
		String orderMenu = $V("OrderMenu");
		String nextMenu = $V("NextMenu");
		String ordertype = $V("OrderType");
		if (StringUtil.isEmpty(orderMenu) || StringUtil.isEmpty(nextMenu)
				|| StringUtil.isEmpty(ordertype)) {
			Response.setLogInfo(0, "\u4F20\u9012\u6570\u636E\u6709\u8BEF\uFF01");
			return;
		}
		Transaction trans = new Transaction();
		DataTable DT = new DataTable();
		DataTable parentDT = (new QueryBuilder(
				"select * from zdMenu where parentID = 0 order by orderflag,id"))
				.executeDataTable();
		for (int i = 0; i < parentDT.getRowCount(); i++) {
			DT.insertRow(parentDT.getDataRow(i));
			DataTable childDT = (new QueryBuilder(
					"select * from zdMenu where parentID = ? order by orderflag,id", parentDT.get(
							i, "ID"))).executeDataTable();
			for (int j = 0; j < childDT.getRowCount(); j++)
				DT.insertRow(childDT.getDataRow(j));

		}

		List branchList = new ArrayList();
		DataTable orderDT = (new QueryBuilder(
				"select * from zdMenu where parentID = ? or id = ? order by orderflag,id",
				orderMenu, orderMenu)).executeDataTable();
		DataTable nextDT = (new QueryBuilder(
				"select * from zdMenu where parentID = ? or id = ? order by orderflag,id",
				nextMenu, nextMenu)).executeDataTable();
		if ("before".equalsIgnoreCase(ordertype)) {
			for (int i = 0; DT != null && i < DT.getRowCount(); i++) {
				if (DT.getString(i, "ID").equals(nextMenu)) {
					for (int m = 0; orderDT != null && m < orderDT.getRowCount(); m++)
						branchList.add(orderDT.getDataRow(m));

				} else if (DT.getString(i, "ID").equals(orderMenu)) {
					i = (i - 1) + orderDT.getRowCount();
					continue;
				}
				branchList.add(DT.getDataRow(i));
			}

		} else if ("after".equalsIgnoreCase(ordertype)) {
			for (int i = 0; DT != null && i < DT.getRowCount(); i++)
				if (DT.getString(i, "ID").equals(orderMenu))
					i = (i - 1) + orderDT.getRowCount();
				else if (DT.getString(i, "ID").equals(nextMenu)) {
					for (int m = 0; nextDT != null && m < nextDT.getRowCount(); m++)
						branchList.add(nextDT.getDataRow(m));

					for (int j = 0; orderDT != null && j < orderDT.getRowCount(); j++)
						branchList.add(orderDT.getDataRow(j));

					i = (i - 1) + nextDT.getRowCount();
				} else {
					branchList.add(DT.getDataRow(i));
				}

		}
		for (int i = 0; i < branchList.size(); i++) {
			DataRow dr = (DataRow) branchList.get(i);
			trans.add(new QueryBuilder("update zdmenu set orderflag = ? where ID = ?", i, dr
					.getString("ID")));
		}

		if (trans.commit())
			Response.setLogInfo(1, "\u6392\u5E8F\u6210\u529F\uFF01");
		else
			Response.setLogInfo(0, "\u6392\u5E8F\u5931\u8D25\uFF01");
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.platform.Menu JD-Core Version: 0.5.3
 */