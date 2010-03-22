package com.zving.bbs.admin;

import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataListAction;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.Mapx;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCAdminGroupSchema;
import com.zving.schema.ZCForumGroupSchema;
import com.zving.schema.ZCForumGroupSet;
import com.zving.schema.ZCForumMemberSchema;
import com.zving.schema.ZCForumMemberSet;
import java.util.Date;

public class ForumUserGroup extends Page {
	public static Mapx init(Mapx params) {
		String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
		String ID = params.getString("GroupID");
		String sql = "select Image from ZCForumGroup where " + sqlSiteID + " and ID=" + ID;
		String originalImage = new QueryBuilder(sql).executeString();
		params.put("ID", ID);
		params.put("originalImage", originalImage);
		return params;
	}

	public static void getList(DataListAction dla) {
		String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
		String sql = "select * from ZCForumGroup where " + sqlSiteID
				+ " and type='1' order by OrderFlag";
		DataTable dt = new QueryBuilder(sql).executeDataTable();
		dla.bindData(dt);
	}

	public void add() {
		Transaction trans = new Transaction();
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setType("1");
		userGroup.setSiteID(ForumUtil.getCurrentBBSSiteID());
		ZCForumGroupSet set = userGroup.query();
		set.sort("OrderFlag");
		int index = -1;
		for (int i = 0; i < set.size(); ++i) {
			if (Long.parseLong($V("LowerScore")) < set.get(i).getLowerScore()) {
				set.get(i).setOrderFlag(set.get(i).getOrderFlag() + 1L);
				if (i == set.size() - 1) {
					userGroup.setOrderFlag(set.get(i).getOrderFlag() - 1L);
					index = i;
				}
			} else {
				if (Long.parseLong($V("LowerScore")) == set.get(i).getLowerScore()) {
					this.Response.setLogInfo(1, "最少到达积分已存在");
					return;
				}
				if (i != 0) {
					index = i - 1;
				}
				set.get(i).setUpperScore($V("LowerScore"));
				userGroup.setOrderFlag(set.get(i).getOrderFlag() + 1L);
				break;
			}
		}
		trans.add(set, 2);
		userGroup.setID(NoUtil.getMaxID("ForumGroupID"));
		userGroup.setValue(this.Request);
		if (set.size() == 0) {
			userGroup.setOrderFlag(1L);
		}
		if (index == -1)
			userGroup.setUpperScore(999999L);
		else {
			userGroup.setUpperScore(set.get(index).getLowerScore());
		}
		userGroup.setSiteID(ForumUtil.getCurrentBBSSiteID());
		userGroup.setAllowAutograph("Y");
		userGroup.setAllowFace("Y");
		userGroup.setAllowHeadImage("N");
		userGroup.setAllowNickName("Y");
		userGroup.setAllowPanel("Y");
		userGroup.setAllowReply("Y");
		userGroup.setAllowSearch("Y");
		userGroup.setAllowTheme("Y");
		userGroup.setAllowUserInfo("Y");
		userGroup.setAllowVisit("Y");
		userGroup.setImage("../Images/SystemHeadImage/HeadImage01.gif");
		userGroup.setAddUser(User.getUserName());
		userGroup.setAddTime(new Date());
		userGroup.setVerify("N");
		trans.add(userGroup, 1);
		if (trans.commit())
			this.Response.setLogInfo(1, "添加成功");
		else
			this.Response.setLogInfo(0, "操作失败");
	}

	public void del() {
		String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
		Transaction trans = new Transaction();
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setID($V("ID"));
		userGroup.fill();

		ZCForumGroupSet set = userGroup.query(new QueryBuilder("where " + sqlSiteID
				+ " and OrderFlag>" + userGroup.getOrderFlag()));
		for (int i = 0; i < set.size(); ++i) {
			set.get(i).setOrderFlag(set.get(i).getOrderFlag() - 1L);
		}
		trans.add(set, 2);

		DataTable dt = new QueryBuilder("select ID from ZCForumGroup where " + sqlSiteID
				+ " and UpperScore=" + userGroup.getLowerScore()).executeDataTable();
		ZCForumMemberSet memberSet = new ZCForumMemberSchema().query(new QueryBuilder("where "
				+ sqlSiteID + " and UserGroupID=" + userGroup.getID()));
		if (dt.getRowCount() > 0) {
			trans.add(new QueryBuilder("update ZCForumGroup set UpperScore="
					+ userGroup.getUpperScore() + " where " + sqlSiteID + " and ID="
					+ dt.getString(0, "ID")));

			for (int i = 0; i < memberSet.size(); ++i)
				memberSet.get(i).setUserGroupID(dt.getString(0, "ID"));
		} else {
			DataTable dtNext = new QueryBuilder("select ID from ZCForumGroup where " + sqlSiteID
					+ " and LowerScore=" + userGroup.getUpperScore()).executeDataTable();
			trans.add(new QueryBuilder("update ZCForumGroup set LowerScore=-99999 where "
					+ sqlSiteID + " and ID=" + dtNext.getString(0, "ID")));

			if (dtNext.getRowCount() > 0) {
				for (int i = 0; i < memberSet.size(); ++i)
					memberSet.get(i).setUserGroupID(dtNext.getString(0, "ID"));
			} else {
				this.Response.setLogInfo(0, "您至少要留下一个用户组");
				return;
			}
		}
		trans.add(userGroup, 5);
		trans.add(memberSet, 2);
		if (trans.commit()) {
			this.Response.setLogInfo(1, "删除成功");
		} else
			this.Response.setLogInfo(0, "操作失败");
	}

	public void save() {
		Transaction trans = new Transaction();
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setType("1");
		userGroup.setSiteID(ForumUtil.getCurrentBBSSiteID());
		ZCForumGroupSet set = userGroup.query();
		for (int i = 0; i < set.size(); ++i) {
			String LowerScore = $V("LowerScore_" + set.get(i).getID()).trim();
			String Color = $V("Color_" + set.get(i).getID()).trim();
			set.get(i).setLowerScore(LowerScore);
			set.get(i).setColor(Color);
		}
		set.sort("LowerScore", "asc");
		for (int i = 0; i < set.size(); ++i) {
			if ((i > 0) && (set.get(i).getLowerScore() == set.get(i - 1).getLowerScore())) {
				this.Response.setLogInfo(0, "最少到达积分不能相同");
				return;
			}
			set.get(i).setOrderFlag(i + 1);
			if (i == set.size() - 1) {
				set.get(i).setUpperScore(99999L);
				break;
			}
			set.get(i).setUpperScore(set.get(i + 1).getLowerScore());
		}
		trans.add(set, 2);
		if (trans.commit())
			this.Response.setLogInfo(1, "更新成功");
		else
			this.Response.setLogInfo(0, "操作失败");
	}

	public static void getListSystemGroup(DataListAction dla) {
		String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
		String sql = "select * from ZCForumGroup where " + sqlSiteID + " and type='2'";
		DataTable dt = new QueryBuilder(sql).executeDataTable();
		dla.bindData(dt);
	}

	public static void getListSpecialGroup(DataListAction dla) {
		String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
		String sql = "select * from ZCForumGroup where " + sqlSiteID + " and type='3'";
		DataTable dt = new QueryBuilder(sql).executeDataTable();
		dla.bindData(dt);
	}

	public void addSpecialGroup() {
		Transaction trans = new Transaction();
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setID(NoUtil.getMaxID("ForumGroupID"));
		userGroup.setType("3");
		userGroup.setValue(this.Request);
		userGroup.setSiteID(ForumUtil.getCurrentBBSSiteID());
		userGroup.setRadminID(0L);
		userGroup.setAllowAutograph("Y");
		userGroup.setAllowFace("Y");
		userGroup.setAllowHeadImage("N");
		userGroup.setAllowNickName("Y");
		userGroup.setAllowPanel("Y");
		userGroup.setAllowReply("Y");
		userGroup.setAllowSearch("Y");
		userGroup.setAllowTheme("Y");
		userGroup.setAllowUserInfo("Y");
		userGroup.setAllowVisit("Y");
		userGroup.setVerify("N");
		userGroup.setImage("../Images/SystemHeadImage/HeadImage01.gif");
		userGroup.setAddUser(User.getUserName());
		userGroup.setAddTime(new Date());
		trans.add(userGroup, 1);
		if (trans.commit())
			this.Response.setLogInfo(1, "添加成功");
		else
			this.Response.setLogInfo(0, "操作失败");
	}

	public void imageSave() {
		Transaction trans = new Transaction();
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setID($V("ID"));
		userGroup.fill();
		userGroup.setImage($V("select"));

		trans.add(userGroup, 2);
		if (trans.commit())
			this.Response.setLogInfo(1, "添加成功");
		else
			this.Response.setLogInfo(0, "操作失败");
	}

	public void editSpecialGroup() {
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setType("3");
		userGroup.setSiteID(ForumUtil.getCurrentBBSSiteID());
		ZCForumGroupSet set = userGroup.query();
		for (int i = 0; i < set.size(); ++i) {
			set.get(i).setColor($V("Color_" + set.get(i).getID()));
		}
		if (set.update())
			this.Response.setLogInfo(1, "添加成功");
		else
			this.Response.setLogInfo(0, "操作失败");
	}

	public void editSystemGroup() {
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setType("2");
		userGroup.setSiteID(ForumUtil.getCurrentBBSSiteID());
		ZCForumGroupSet set = userGroup.query();
		for (int i = 0; i < set.size(); ++i) {
			set.get(i).setColor($V("Color_" + set.get(i).getID()));
		}
		if (set.update())
			this.Response.setLogInfo(1, "添加成功");
		else
			this.Response.setLogInfo(0, "操作失败");
	}

	public void delSpecailGroup() {
		Transaction trans = new Transaction();
		ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
		userGroup.setID($V("ID"));
		userGroup.fill();
		ZCForumMemberSet memberSet = new ZCForumMemberSchema().query(new QueryBuilder(
				"where UserGroupID=" + userGroup.getID()));

		if (userGroup.getRadminID() != 0L) {
			ZCAdminGroupSchema adminGroup = new ZCAdminGroupSchema();
			adminGroup.setGroupID(userGroup.getID());
			adminGroup.fill();
			trans.add(adminGroup, 5);
			for (int i = 0; i < memberSet.size(); ++i) {
				memberSet.get(i).setAdminID(0L);
				memberSet.get(i).setUserGroupID(0L);
			}
		} else {
			for (int i = 0; i < memberSet.size(); ++i) {
				if (memberSet.get(i).getAdminID() != 0L)
					memberSet.get(i).setUserGroupID(memberSet.get(i).getAdminID());
				else {
					memberSet.get(i).setUserGroupID(0L);
				}
			}
		}
		ForumUtil.userGroupChange(memberSet);
		trans.add(userGroup, 5);
		trans.add(memberSet, 2);
		if (trans.commit())
			this.Response.setLogInfo(1, "删除成功");
		else
			this.Response.setLogInfo(0, "操作失败");
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.bbs.admin.ForumUserGroup JD-Core Version: 0.5.3
 */