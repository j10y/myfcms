package com.zving.bbs.admin;

import com.zving.bbs.ForumPriv;
import com.zving.bbs.ForumRule;
import com.zving.framework.Config;
import com.zving.framework.User;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.schema.ZCForumGroupSchema;
import com.zving.schema.ZCForumMemberSchema;
import com.zving.schema.ZCForumMemberSet;
import com.zving.schema.ZCForumSchema;
import com.zving.schema.ZCForumSet;
import com.zving.schema.ZCThemeSchema;
import com.zving.schema.ZCThemeSet;
import java.util.Date;

public class ForumUtil {
	public static void allowVisit(String SiteID) {
		String sql = "select count(*) from ZCForumConfig where SiteID=" + SiteID;
		int count = new QueryBuilder(sql).executeInt();
		if (count == 0) {
			return;
		}
		ForumPriv priv = new ForumPriv(SiteID);
		if (priv.getPriv("AllowVisit"))
			User.setValue("AllowMemberVisit", "Y");
		else
			User.setValue("AllowMemberVisit", "N");
	}

	public static boolean isOperateMember(String userName) {
		if ((StringUtil.isNotEmpty(userName)) && (isExistMember(userName))) {
			if (User.getUserName().equals("admin")) {
				return true;
			}
			if (User.getUserName().equals(userName)) {
				return true;
			}
			ZCForumMemberSchema member = new ZCForumMemberSchema();
			ZCForumGroupSchema group = new ZCForumGroupSchema();
			ZCForumMemberSchema currentMember = new ZCForumMemberSchema();
			ZCForumGroupSchema currentGroup = new ZCForumGroupSchema();
			member.setUserName(userName);
			member.fill();
			currentMember.setUserName(User.getUserName());
			currentMember.fill();
			group.setID(member.getAdminID());
			group.fill();
			currentGroup.setID(currentMember.getAdminID());
			currentGroup.fill();
			if (currentGroup.getSystemName().equals("系统管理员")) {
				return true;
			}
			if (member.getAdminID() == 0L)
				return true;
			if ((StringUtil.isNotEmpty(currentGroup.getSystemName()))
					&& (currentGroup.getSystemName().equals("超级版主"))
					&& (!(group.getSystemName().equals("超级版主")))
					&& (!(group.getSystemName().equals("系统管理员")))) {
				return true;
			}
		}

		return false;
	}

	public static void userGroupChange(ZCForumMemberSchema member) {
		String sqlSiteID = "SiteID=" + member.getSiteID();
		long ForumScore = member.getForumScore();
		String sql = "select ID from ZCForumGroup where " + sqlSiteID + " and LowerScore<="
				+ ForumScore + " and UpperScore>" + ForumScore;
		DataTable dt = new QueryBuilder(sql).executeDataTable();
		if (dt.getRowCount() <= 0)
			return;
		if (member.getUserGroupID() == 0L) {
			member.setUserGroupID(dt.getString(0, "ID"));
		} else {
			long groupID = member.getUserGroupID();
			DataTable dtType = new QueryBuilder("select type from ZCForumGroup where ID=" + groupID)
					.executeDataTable();

			if (dtType.getString(0, "type").equals("1")) {
				member.setUserGroupID(dt.getString(0, "ID"));
			} else {
				if ((!(dtType.getString(0, "type").equals("2"))) || (member.getAdminID() != 0L))
					return;
				member.setUserGroupID(dt.getString(0, "ID"));
			}
		}
	}

	public static void userGroupChange(ZCForumMemberSet memberSet) {
		for (int i = 0; i < memberSet.size(); ++i)
			userGroupChange(memberSet.get(i));
	}

	public static void createBBSUser(Transaction trans, String userName, String SiteID) {
		String sql = "select count(*) from ZCForumConfig where SiteID=" + SiteID;
		int count = new QueryBuilder(sql).executeInt();
		if (count == 0) {
			return;
		}
		ZCForumMemberSchema forumMember = new ZCForumMemberSchema();
		forumMember.setUserName(userName);
		forumMember.setThemeCount(0L);
		forumMember.setReplyCount(0L);
		forumMember.setAdminID(0L);
		forumMember.setUserGroupID(0L);
		if ("Y".equalsIgnoreCase(Config.getValue("PublicUseBBS"))) {
			forumMember.setSiteID(0L);
			forumMember.setForumScore(new ForumScore(0L).InitScore);
		} else {
			forumMember.setSiteID(SiteID);
			forumMember.setForumScore(new ForumScore(SiteID).InitScore);
		}
		forumMember.setUseSelfImage("N");
		forumMember.setHeadImage("Images/SystemHeadImage/HeadImage01.gif");
		forumMember.setAddTime(new Date());
		forumMember.setAddUser(userName);

		userGroupChange(forumMember);
		trans.add(forumMember, 1);
	}

	public static String[] getAdmins(String ForumID) {
		String sql = "select ForumAdmin from ZCForum where ID=" + ForumID;
		DataTable dta = new QueryBuilder(sql).executeDataTable();
		String sqlParent = "select ForumAdmin from ZCForum where ID=(select ParentID from ZCForum where ID="
				+ ForumID + ")";
		DataTable dtb = new QueryBuilder(sqlParent).executeDataTable();
		String forumAdmin = "";
		if (dta.getRowCount() > 0) {
			forumAdmin = dta.getString(0, "ForumAdmin");
		}
		if (dtb.getRowCount() > 0) {
			if (StringUtil.isNotEmpty(forumAdmin))
				forumAdmin = forumAdmin + "," + dtb.getString(0, "ForumAdmin");
			else {
				forumAdmin = dtb.getString(0, "ForumAdmin");
			}
		}
		String[] forumAdmins = forumAdmin.split(",");
		return forumAdmins;
	}

	public static int isAdmin(String ForumID, String SiteID) {
		String sqlSiteID = "SiteID=" + SiteID;
		if (StringUtil.isNotEmpty(User.getUserName())) {
			if (User.getUserName().equals("admin")) {
				return 1;
			}
			String sqlAdmin = "select count(*) from ZCForumMember a,ZCForumGroup b where a."
					+ sqlSiteID + " and b." + sqlSiteID + " and a.UserName='" + User.getUserName()
					+ "' and a.AdminID=b.ID and b.SystemName='系统管理员'";
			int countAdmin = new QueryBuilder(sqlAdmin).executeInt();
			if (countAdmin > 0) {
				return 1;
			}
			String sqlSpecail = "select count(*) from ZCForumMember a,ZCforumgroup b,ZCAdminGroup c,ZCforumgroup d where a."
					+ sqlSiteID
					+ " and b."
					+ sqlSiteID
					+ " and c."
					+ sqlSiteID
					+ " and d."
					+ sqlSiteID
					+ " and a.UserName='lolo' and a.userGroupID=b.ID and b.ID=c.groupID and b.RadminID=d.ID and d.SystemName='超级版主'";
			int countSpecail = new QueryBuilder(sqlSpecail).executeInt();
			if (countSpecail > 0) {
				return 2;
			}
			String sql = "select count(*) from ZCForumMember a,ZCForumGroup b where a." + sqlSiteID
					+ " and b." + sqlSiteID + " and a.UserName='" + User.getUserName()
					+ "' and a.AdminID=b.ID and b.SystemName='超级版主'";
			int count = new QueryBuilder(sql).executeInt();
			if (count > 0) {
				return 3;
			}
			String[] forumAdmins = getAdmins(ForumID);
			for (int i = 0; i < forumAdmins.length; ++i) {
				if (forumAdmins[i].equals(User.getUserName())) {
					return 4;
				}
			}
		}
		return 0;
	}

	public static boolean isExistMember(String members) {
		String[] ForumAdmins = members.trim().split(",");
		StringBuffer sql = new StringBuffer("select count(*) from ZDMember where UserName='"
				+ ForumAdmins[0] + "'");
		for (int i = 1; i < ForumAdmins.length; ++i) {
			sql.append(" or UserName='" + ForumAdmins[i] + "'");
		}
		int count = new QueryBuilder(sql.toString()).executeInt();

		return (count == ForumAdmins.length);
	}

	public static void addAdminID(Transaction trans, String ForumID, String ForumAdmin) {
		String[] ForumAdmins = ForumAdmin.trim().split(",");
		ZCForumSchema forum = new ZCForumSchema();
		forum.setID(ForumID);
		forum.fill();
		checkDelAdmin(trans, ForumAdmins, forum);

		DataTable dt = new QueryBuilder("select ID from ZCForumGroup where SystemName='版主'")
				.executeDataTable();
		for (int i = 0; i < ForumAdmins.length; ++i) {
			ZCForumMemberSchema member = new ZCForumMemberSchema();
			member.setUserName(ForumAdmins[i]);
			member.fill();
			if (member.getAdminID() == 0L) {
				ZCForumGroupSchema group = new ZCForumGroupSchema();
				group.setID(member.getUserGroupID());
				group.fill();
				if (!(group.getType().equals("3"))) {
					member.setUserGroupID(dt.getLong(0, "ID"));
				}
				member.setAdminID(dt.getLong(0, "ID"));
				trans.add(member, 2);
			}
		}
	}

	public static String initPriv(String ForumID, String SiteID) {
		StringBuffer sb = new StringBuffer();
		ForumPriv priv = new ForumPriv(SiteID);
		if (priv.getPriv("AllowSearch")) {
			sb.append("<a href='ThemeSearch.jsp?SiteID=" + SiteID + "'>搜索</a>");
		}
		if (priv.getPriv("AllowPanel")) {
			sb.append(" |<a href='ControlPanel.jsp?SiteID=" + SiteID
					+ "'>控制面板</a> | <a href='MyArticles.jsp?SiteID=" + SiteID + "'>我的话题</a> ");
		}
		if (priv.getPriv("Admin")) {
			sb.append(" |<a href='MasterPanel.jsp?SiteID=" + SiteID + "'>版主管理面版</a>");
		}
		if (StringUtil.isNotEmpty(ForumID)) {
			ForumRule rule = new ForumRule(ForumID);
			if ((((rule.getRule("AllowTheme")) || (isAdmin(ForumID, SiteID) > 0)))
					&& (priv.getPriv("AllowTheme"))) {
				sb.append(" |<a href='ThemeAdd.jsp?SiteID=" + SiteID + "&ForumID=" + ForumID
						+ "'>发表新话题</a>");
			}
		}

		return sb.toString();
	}

	public static String initPriv(String SiteID) {
		return initPriv(null, SiteID);
	}

	public static void adminPriv(Mapx map) {
		String SiteID = map.getString("SiteID");
		ForumPriv priv = new ForumPriv(SiteID);
		if (priv.getPriv("AllowEditUser")) {
			map.put("AllowEditUser", "<a href='MasterPanel.jsp?SiteID=" + SiteID + "'>编辑用户</a>");
		}

		if (priv.getPriv("AllowEditForum")) {
			map.put("AllowEditForum", "<a href='ForumEdit.jsp?SiteID=" + SiteID + "'>板块编辑</a>");
		}
		if (priv.getPriv("AllowVerfyPost"))
			map.put("AllowVerfyPost", "<a href='PostAudit.jsp?SiteID=" + SiteID + "'>帖子审核</a>");
	}

	public static void changeLastTheme(ZCForumSchema originalForum, ZCForumSchema targetForum,
			String ids) {
		String sqlMin = "select ID from ZCTheme where status='Y' and ForumID="
				+ originalForum.getID() + " and ID in(" + ids + ") order by ID desc";
		DataTable dtMin = new QueryBuilder(sqlMin).executePagedDataTable(1, 0);
		String sqlNext = "select count(*) from ZCTheme where status='Y' and VerifyFlag='Y' and ForumID="
				+ originalForum.getID()
				+ " and ID>"
				+ dtMin.getString(0, "ID")
				+ " and ID not in("
				+ ids + ")";
		int count = new QueryBuilder(sqlNext).executeInt();
		if (count == 0) {
			String sqlOriginal = "select Subject,AddUser,ID from ZCTheme where status='Y' and VerifyFlag='Y' and ForumID="
					+ originalForum.getID() + " and ID not in(" + ids + ") order by ID desc";
			DataTable dt = new QueryBuilder(sqlOriginal).executePagedDataTable(1, 0);
			if (dt.getRowCount() > 0) {
				originalForum.setLastPost(dt.getString(0, "Subject"));
				originalForum.setLastPoster(dt.getString(0, "AddUser"));
				originalForum.setLastThemeID(dt.getString(0, "ID"));
			} else {
				originalForum.setLastPost("");
				originalForum.setLastPoster("");
				originalForum.setLastThemeID("");
			}
		}
		if (targetForum != null) {
			String sqlTarget = "select count(*) from ZCTheme where status='Y' and VerifyFlag='Y' and ForumID="
					+ targetForum.getID() + " and ID>" + dtMin.getString(0, "ID");
			int countTarget = new QueryBuilder(sqlTarget).executeInt();
			if (countTarget == 0) {
				String sql = "select Subject,AddUser,ID from ZCTheme where ID="
						+ dtMin.getString(0, "ID");
				DataTable dt = new QueryBuilder(sql).executePagedDataTable(1, 0);
				if (dt.getRowCount() > 0) {
					targetForum.setLastPost(dt.getString(0, "Subject"));
					targetForum.setLastPoster(dt.getString(0, "AddUser"));
					targetForum.setLastThemeID(dt.getString(0, "ID"));
				} else {
					targetForum.setLastPost("");
					targetForum.setLastPoster("");
					targetForum.setLastThemeID("");
				}
			}
		}
	}

	public static void changeLastTheme(ZCForumSchema originalForum, String ids) {
		changeLastTheme(originalForum, null, ids);
	}

	public static long getCurrentBBSSiteID() {
		if ("Y".equalsIgnoreCase(Config.getValue("PublicUseBBS"))) {
			return 0L;
		}
		int count = new QueryBuilder("select count(*) from ZCSite").executeInt();
		if (count > 0) {
			return Application.getCurrentSiteID();
		}
		return 0L;
	}

	public static boolean isNotSendTheme(String SiteID, String ForumID) {
		return (!(isSendTheme(SiteID, ForumID)));
	}

	public static boolean isSendTheme(String SiteID, String ForumID) {
		ForumPriv priv = new ForumPriv(SiteID);
		ForumRule rule = new ForumRule(ForumID);

		return ((((rule.getRule("AllowTheme")) || (isAdmin(ForumID, SiteID) > 0))) && (priv
				.getPriv("AllowTheme")));
	}

	public static boolean isNotReplyPost(String SiteID, String ForumID) {
		return (!(isReplyPost(SiteID, ForumID)));
	}

	public static boolean isReplyPost(String SiteID, String ForumID) {
		ForumPriv priv = new ForumPriv(SiteID);
		ForumRule rule = new ForumRule(ForumID);

		return ((((rule.getRule("ReplyPost")) || (isAdmin(ForumID, SiteID) > 0))) && (priv
				.getPriv("AllowReply")));
	}

	public static boolean isEditPost(String SiteID, String ForumID, String UserName) {
		ForumPriv priv = new ForumPriv(SiteID);
		ForumRule rule = new ForumRule(ForumID);
		if ((isAdmin(ForumID, SiteID) > 0) && (priv.getPriv("AllowEdit"))) {
			return true;
		}

		return ((rule.getRule("EditPost")) && (StringUtil.isNotEmpty(User.getUserName())) && (User
				.getUserName().equals(UserName)));
	}

	public static int getValueOfMemberSet(ZCForumMemberSet memberSet, ZCForumMemberSchema member) {
		for (int j = 0; j < memberSet.size(); ++j) {
			if (member.getUserName().equals(memberSet.get(j).getUserName())) {
				return j;
			}
		}
		return -1;
	}

	public static int getValueOfForumSet(ZCForumSet forumSet, ZCForumSchema forum) {
		for (int j = 0; j < forumSet.size(); ++j) {
			if (forum.getID() == forumSet.get(j).getID()) {
				return j;
			}
		}
		return -1;
	}

	public static int getValueOfThemeSet(ZCThemeSet themeSet, ZCThemeSchema theme) {
		for (int j = 0; j < themeSet.size(); ++j) {
			if (theme.getID() == themeSet.get(j).getID()) {
				return j;
			}
		}
		return -1;
	}

	private static void checkDelAdmin(Transaction trans, String[] ForumAdmins, ZCForumSchema forum) {
		String admins = (StringUtil.isEmpty(forum.getForumAdmin())) ? "" : forum.getForumAdmin();
		String[] oldAdmins = admins.split(",");
		String checkAdmin = "";
		for (int i = 0; i < oldAdmins.length; ++i) {
			for (int j = 0; j < ForumAdmins.length; ++j) {
				if (oldAdmins[i].equals(ForumAdmins[j])) {
					checkAdmin = checkAdmin + oldAdmins[i] + ",";
				}
			}
		}
		if (checkAdmin.length() == 0) {
			return;
		}
		String[] checkAdmins = checkAdmin.split(",");
		for (int i = 0; i < checkAdmins.length; ++i) {
			String sql = "select count(*) from ZCForum where ForumAdmin like '%" + checkAdmins[i]
					+ "," + "%'";
			int count = new QueryBuilder(sql).executeInt();
			if (count == 1) {
				ZCForumMemberSchema member = new ZCForumMemberSchema();
				member.setUserName(checkAdmins[i]);
				member.fill();
				String sqlType = "select SystemName from ZCForumGroup where ID="
						+ member.getAdminID();
				DataTable dtType = new QueryBuilder(sqlType).executeDataTable();
				if ((dtType.getString(0, "SystemName").equals("超级版主"))
						|| (dtType.getString(0, "SystemName").equals("系统管理员"))) {
					return;
				}
				member.setAdminID(0L);
				userGroupChange(member);
				trans.add(member, 2);
			}
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.bbs.admin.ForumUtil JD-Core Version: 0.5.3
 */