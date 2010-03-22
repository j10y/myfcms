package com.zving.bbs.admin;

import com.zving.framework.Page;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.schema.ZCAdminGroupSchema;
import com.zving.schema.ZCForumGroupSchema;
import com.zving.schema.ZCForumMemberSchema;
import com.zving.schema.ZCForumMemberSet;

public class ForumUserGroupOption extends Page {
	public static Mapx init(Mapx params) {
		return params;
	}

	public static Mapx initBasic(Mapx params) {
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setID(params.getString("ID"));
		userGroup.fill();
		params = userGroup.toMapx();
		Mapx map = new Mapx();
		map.put("Y", "允许");
		map.put("N", "不允许");
		if ((StringUtil.isEmpty(userGroup.getSystemName()))
				|| (!(userGroup.getSystemName().equals("游客")))) {
			params.put("AllowHeadImage", HtmlUtil.mapxToRadios("AllowHeadImage", map, userGroup
					.getAllowHeadImage()));
			params.put("AllowNickName", HtmlUtil.mapxToRadios("AllowNickName", map, userGroup
					.getAllowNickName()));
			params.put("AllowPanel", HtmlUtil.mapxToRadios("AllowPanel", map, userGroup
					.getAllowPanel()));
		} else {
			params.put("AllowHeadImage", "不允许");
			params.put("AllowNickName", "不允许");
			params.put("AllowPanel", "不允许");
		}
		params.put("AllowUserInfo", HtmlUtil.mapxToRadios("AllowUserInfo", map, userGroup
				.getAllowUserInfo()));
		params.put("AllowVisit", HtmlUtil
				.mapxToRadios("AllowVisit", map, userGroup.getAllowVisit()));
		params.put("AllowSearch", HtmlUtil.mapxToRadios("AllowSearch", map, userGroup
				.getAllowSearch()));
		return params;
	}

	public static Mapx initPostOption(Mapx params) {
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setID(params.getString("ID"));
		userGroup.fill();
		params = userGroup.toMapx();
		Mapx map = new Mapx();
		map.put("Y", "允许");
		map.put("N", "不允许");
		if ((StringUtil.isEmpty(userGroup.getSystemName()))
				|| (!(userGroup.getSystemName().equals("游客")))) {
			params.put("AllowTheme", HtmlUtil.mapxToRadios("AllowTheme", map, userGroup
					.getAllowTheme()));
			params.put("AllowReply", HtmlUtil.mapxToRadios("AllowReply", map, userGroup
					.getAllowReply()));
			params.put("Verify", HtmlUtil.mapxToRadios("Verify", map, userGroup.getVerify()));
		} else {
			params.put("AllowTheme", "不允许");
			params.put("AllowReply", "不允许");
			params.put("Verify", "不允许");
		}
		return params;
	}

	public void editSave() {
		Transaction trans = new Transaction();
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setID($V("ID"));
		userGroup.fill();
		userGroup.setValue(this.Request);
		trans.add(userGroup, 2);
		if (trans.commit())
			this.Response.setLogInfo(1, "设置成功!");
		else
			this.Response.setLogInfo(0, "操作失败！");
	}

	public static Mapx initSpecailOption(Mapx params) {
		long SiteID = ForumUtil.getCurrentBBSSiteID();
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setID(params.getString("ID"));
		userGroup.fill();
		params = userGroup.toMapx();
		Mapx map = new Mapx();
		map.put("Y", "允许");
		map.put("N", "不允许");
		params.put("AllowVisit", HtmlUtil
				.mapxToRadios("AllowVisit", map, userGroup.getAllowVisit()));
		params.put("AllowSearch", HtmlUtil.mapxToRadios("AllowSearch", map, userGroup
				.getAllowSearch()));
		params.put("AllowHeadImage", HtmlUtil.mapxToRadios("AllowHeadImage", map, userGroup
				.getAllowHeadImage()));
		params.put("AllowUserInfo", HtmlUtil.mapxToRadios("AllowUserInfo", map, userGroup
				.getAllowUserInfo()));
		params.put("AllowNickName", HtmlUtil.mapxToRadios("AllowNickName", map, userGroup
				.getAllowNickName()));
		String sql = "select a.Name,b.GroupID from ZCForumGroup a,ZCAdminGroup b where a.SiteID="
				+ SiteID + " and b.SiteID=" + SiteID
				+ " and a.ID=b.GroupID and a.type='2' and a.SystemName<>'系统管理员'";
		DataTable dt = new QueryBuilder(sql).executeDataTable();
		params.put("AdminGroup", HtmlUtil.dataTableToOptions(dt, userGroup.getRadminID()));
		return params;
	}

	public void editSpecialSave() {
		String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
		Transaction trans = new Transaction();
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setID($V("ID"));
		userGroup.fill();
		userGroup.setValue(this.Request);
		ZCAdminGroupSchema newGroup = new ZCAdminGroupSchema();
		newGroup.setGroupID(userGroup.getID());
		ZCAdminGroupSchema adminGroup;
		if (newGroup.fill()) {
			if ($V("RadminID").equals("0")) {
				trans.add(newGroup, 5);
				ZCForumMemberSet memberSet = new ZCForumMemberSchema().query(new QueryBuilder(
						"where " + sqlSiteID + " and UserGroupID=" + newGroup.getGroupID()));
				for (int i = 0; i < memberSet.size(); ++i) {
					memberSet.get(i).setAdminID(0L);
				}
				trans.add(memberSet, 2);
			} else {
				adminGroup = new ZCAdminGroupSchema();
				adminGroup.setGroupID($V("RadminID"));
				adminGroup.fill();

				newGroup = adminGroup;
				newGroup.setGroupID(userGroup.getID());
				trans.add(newGroup, 2);

				ZCForumMemberSet memberSet = new ZCForumMemberSchema().query(new QueryBuilder(
						"where " + sqlSiteID + " and UserGroupID=" + newGroup.getGroupID()));
				for (int i = 0; i < memberSet.size(); ++i) {
					memberSet.get(i).setAdminID($V("RadminID"));
				}
				trans.add(memberSet, 2);
			}
		} else if (!($V("RadminID").equals("0"))) {
			adminGroup = new ZCAdminGroupSchema();
			adminGroup.setGroupID($V("RadminID"));
			adminGroup.fill();

			newGroup = adminGroup;
			newGroup.setGroupID(userGroup.getID());
			trans.add(newGroup, 1);

			ZCForumMemberSet memberSet = new ZCForumMemberSchema().query(new QueryBuilder("where "
					+ sqlSiteID + " and UserGroupID=" + newGroup.getGroupID()));
			for (int i = 0; i < memberSet.size(); ++i) {
				memberSet.get(i).setAdminID($V("RadminID"));
			}
			trans.add(memberSet, 2);
		}

		trans.add(userGroup, 2);
		if (trans.commit())
			this.Response.setLogInfo(1, "设置成功!");
		else
			this.Response.setLogInfo(0, "操作失败！");
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.bbs.admin.ForumUserGroupOption JD-Core Version: 0.5.3
 */