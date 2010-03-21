package com.zving.bbs;

import com.zving.bbs.admin.ForumUtil;
import com.zving.framework.Ajax;
import com.zving.framework.Config;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.schema.ZCForumSchema;

public class Forum extends Ajax {
	public static DataTable getList1(Mapx params, DataRow parentDR) {
		String SiteID = params.getString("SiteID");
		if (StringUtil.isEmpty(SiteID)) {
			if ("Y".equalsIgnoreCase(Config.getValue("PublicUseBBS"))) {
				SiteID = "0";
			} else if (StringUtil.isNotEmpty(User.getUserName())) {
				if ((User.isManager()) && (User.isLogin()))
					SiteID = String.valueOf(ForumUtil.getCurrentBBSSiteID());
				else
					SiteID = new QueryBuilder("select SiteID from ZCForumMember where UserName='"
							+ User.getUserName() + "'").executeString();
			} else {
				String sql = "select ID from ZCSite";
				DataTable dt = new QueryBuilder(sql).executePagedDataTable(1, 0);
				if (dt.getRowCount() > 0)
					SiteID = dt.getString(0, "ID");
				else {
					SiteID = "0";
				}
			}
		}

		String ForumID = params.getString("ForumID");
		String sql = "select * from zcforum where SiteID=" + SiteID + " and ParentID=0";
		if (StringUtil.isNotEmpty(ForumID)) {
			sql = sql + " and ID=" + ForumID;
		}
		DataTable dt = new QueryBuilder(sql).executeDataTable();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			String forumadmin = dt.getString(i, "ForumAdmin");
			if ((StringUtil.isEmpty(forumadmin)) || (forumadmin.charAt(0) == ',')) {
				dt.set(i, "ForumAdmin", "&nbsp;");
			} else {
				int index = forumadmin.lastIndexOf(",");
				dt.set(i, "ForumAdmin", "分区版主：" + forumadmin.substring(0, index));
			}
		}
		return dt;
	}

	public static DataTable getList2(Mapx params, DataRow parentDR) {
		DataTable dt = new QueryBuilder("select * from zcforum where SiteID="
				+ parentDR.getString("SiteID") + " and ParentID=" + parentDR.getString("ID"))
				.executeDataTable();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			String forumadmin = dt.getString(i, "ForumAdmin");
			if ((StringUtil.isEmpty(forumadmin)) || (forumadmin.charAt(0) == ',')) {
				dt.set(i, "ForumAdmin", "暂无版主");
			} else {
				int index = forumadmin.lastIndexOf(",");
				dt.set(i, "ForumAdmin", forumadmin.substring(0, index));
			}
		}
		return dt;
	}

	public void check() {
		ZCForumSchema forum = new ZCForumSchema();
		forum.setID($V("ID"));
		forum.fill();
		if (forum.getLocked().equals("Y"))
			this.Response.setLogInfo(2, "该板块已被锁定");
		else if (StringUtil.isNotEmpty(forum.getPassword()))
			this.Response.setStatus(0);
		else
			this.Response.setStatus(1);
	}

	public void checkPassword() {
		ZCForumSchema forum = new ZCForumSchema();
		forum.setID($V("ID"));
		forum.fill();
		if ($V("Password").equals(forum.getPassword())) {
			User.setValue("pass_" + forum.getID(), "Y");
			this.Response.setStatus(1);
		} else {
			this.Response.setLogInfo(0, "密码错误");
		}
	}

	public static Mapx initPassword(Mapx params) {
		params.put("ID", params.getString("ID"));
		return params;
	}

	public static Mapx init(Mapx params) {
		String SiteID = params.getString("SiteID");
		if (StringUtil.isEmpty(SiteID)) {
			if (StringUtil.isNotEmpty(User.getUserName())) {
				if ((User.isManager()) && (User.isLogin()))
					SiteID = String.valueOf(ForumUtil.getCurrentBBSSiteID());
				else
					SiteID = new QueryBuilder("select SiteID from ZCForumMember where UserName='"
							+ User.getUserName() + "'").executeString();
			} else {
				String sql = "select ID from ZCSite";
				DataTable dt = new QueryBuilder(sql).executePagedDataTable(1, 0);
				if (dt.getRowCount() > 0)
					SiteID = dt.getString(0, "ID");
				else {
					SiteID = "0";
				}
			}
		}
		if (StringUtil.isEmpty(User.getUserName())) {
			params.put("AddUser", "游客");
			params.put("button", "<a href='../Member/Register.jsp?SiteID=" + SiteID
					+ "'>注册</a>&nbsp;&nbsp;<a href='../Member/Login.jsp?SiteID=" + SiteID
					+ "'>登录</a>");
		} else {
			params.put("AddUser", User.getUserName());
		}

		if ("Y".equalsIgnoreCase(Config.getValue("PublicUseBBS")))
			params.put("SiteID", "0");
		else {
			params.put("SiteID", SiteID);
		}
		params.put("Priv", ForumUtil.initPriv(SiteID));
		return params;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.bbs.Forum JD-Core Version: 0.5.3
 */