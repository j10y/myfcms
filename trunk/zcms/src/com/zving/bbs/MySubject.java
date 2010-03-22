package com.zving.bbs;

import com.zving.framework.Ajax;
import com.zving.framework.User;
import com.zving.framework.controls.DataListAction;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import java.util.Date;

public class MySubject extends Ajax {
	public static void getList(DataListAction dla) {
		String addTime = dla.getParams().getString("addtime");
		String orderBy = dla.getParams().getString("orderby");
		String ascdesc = dla.getParams().getString("ascdesc");
		StringBuffer sb = new StringBuffer();

		if ((!(StringUtil.isEmpty(addTime))) && (!("0".equals(addTime)))) {
			Date date = new Date();
			date.setTime(new Date().getTime() - Long.parseLong(addTime));
			addTime = DateUtil.toDateTimeString(date);
			sb.append(" and t.addTime > '" + addTime + "'");
		}

		if (!(StringUtil.isEmpty(orderBy)))
			sb.append(" order by " + orderBy);
		else {
			sb.append(" order by OrderTime desc");
		}

		if ((!(StringUtil.isEmpty(ascdesc))) && ("DESC".equals(ascdesc))) {
			sb.append(" desc ");
		}

		String sqlData = "select t.*, f.Name, p.ID PID, p.ApplyDel from ZCTheme t, ZCForum f, ZCPost p where p.ThemeID=t.ID and p.first='Y' and t.status='Y' and t.ForumID=f.ID and t.AddUser='"
				+ User.getUserName() + "'" + sb;
		String sqlTotal = "select count(*) from ZCTheme t where status='Y' and AddUser='"
				+ User.getUserName() + "'" + sb;
		DataTable dt = new QueryBuilder(sqlData).executePagedDataTable(dla.getPageSize(), dla
				.getPageIndex());
		dt.insertColumn("AuditStatus");
		dt.insertColumn("Operation");
		for (int i = 0; i < dt.getRowCount(); ++i) {
			if (dt.get(i, "VerifyFlag").equals("Y")) {
				dt.set(i, "AuditStatus", "正常");
				if (dt.get(i, "ApplyDel") == null)
					dt.set(i, "Operation", "<a href='javascript:applyDel(" + dt.get(i, "PID")
							+ ")'>申请删除</a>");
				else
					dt.set(i, "Operation", "已申请删除");
			} else {
				dt.set(i, "AuditStatus", "待审核");
				dt.set(i, "Operation", "<cite><a href='javascript:editTheme(" + dt.get(i, "PID")
						+ ")'>修改</a></cite> <em><a href='javascript:del(" + dt.get(i, "PID")
						+ ")' >删除</a></em>");
			}
		}
		int total = new QueryBuilder(sqlTotal).executeInt();
		dla.setTotal(total);
		dla.bindData(dt);
	}

	public static Mapx init(Mapx params) {
		params.put("AddUser", User.getUserName());
		return params;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.bbs.MySubject JD-Core Version: 0.5.3
 */