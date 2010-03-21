package com.zving.bbs.admin;

import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.pub.NoUtil;
import com.zving.platform.pub.OrderUtil;
import com.zving.schema.ZCForumSchema;
import com.zving.schema.ZCForumSet;
import com.zving.schema.ZCPostSchema;
import com.zving.schema.ZCPostSet;
import com.zving.schema.ZCThemeSchema;
import com.zving.schema.ZCThemeSet;
import java.util.Date;

public class ForumAdmin extends Page {
	public static Mapx init(Mapx params) {
		return params;
	}

	public static Mapx initAddDialog(Mapx params) {
		String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
		DataTable dt = new QueryBuilder("select Name,ID from ZCForum where " + sqlSiteID
				+ " and ParentID=0 order by orderflag").executeDataTable();
		params.put("ParentForum", HtmlUtil.dataTableToOptions(dt));
		return params;
	}

	public static Mapx initEditDialog(Mapx params) {
		String ID = params.getString("ID");
		String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
		DataTable dt1 = new QueryBuilder("select ParentID from ZCForum where " + sqlSiteID
				+ " and ID=" + ID).executeDataTable();
		DataTable dt = new QueryBuilder("select Name,ID from ZCForum where " + sqlSiteID
				+ " and ParentID=0 order by orderflag").executeDataTable();
		params.put("ParentForum", HtmlUtil.dataTableToOptions(dt, dt1.getString(0, "ParentID")));
		return params;
	}

	public static void dg1DataBind(DataGridAction dga) {
		String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
		String sql = "select ID,ParentID,Name,Addtime,Type,ForumAdmin,'' as Expand,'' as TreeLevel,'' as EditButton from ZCForum where "
				+ sqlSiteID + " order by OrderFlag";
		DataTable dt = new QueryBuilder(sql).executeDataTable();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			if ("group".equals(dt.get(i, "Type"))) {
				dt.set(i, "Expand", "Y");
				dt.set(i, "TreeLevel", "0");
				dt.set(i, "EditButton", "");
			} else {
				dt.set(i, "Expand", "N");
				dt.set(i, "TreeLevel", "1");
				dt.set(i, "EditButton", "<a href='#' onclick='editForum(" + dt.getString(i, "ID")
						+ ")'>编辑</a>&nbsp;&nbsp;<a href='#' onclick='del(" + dt.getString(i, "ID")
						+ ")'>删除</a>");
			}
			if (dt.getString(i, "ForumAdmin").endsWith(",")) {
				int index = dt.getString(i, "ForumAdmin").lastIndexOf(",");
				dt.set(i, "ForumAdmin", dt.getString(i, "ForumAdmin").substring(0, index));
			}
		}
		dga.bindData(dt);
	}

	public void add() {
		String name = $V("Name");
		ZCForumSchema forum = new ZCForumSchema();
		forum.setName(name);
		forum.setValue(this.Request);
		forum.setID(NoUtil.getMaxID("ForumID"));
		if (forum.getParentID() == 0L)
			forum.setType("group");
		else {
			forum.setType("forum");
		}
		forum.setSiteID(ForumUtil.getCurrentBBSSiteID());
		forum.setStatus("0");
		forum.setThemeCount(0);
		forum.setPostCount(0);
		forum.setTodayPostCount(0);
		forum.setVisible("Y");
		forum.setLocked("N");
		forum.setReplyPost("Y");
		forum.setVerify("N");
		forum.setAllowTheme("Y");
		forum.setAnonyPost("N");
		forum.setEditPost("Y");
		forum.setRecycle("N");
		forum.setAllowHTML("N");
		forum.setAllowFace("Y");

		forum.setOrderFlag(OrderUtil.getDefaultOrder());
		forum.setAddTime(new Date());
		forum.setAddUser(User.getUserName());
		Transaction trans = new Transaction();
		trans.add(forum, 1);
		if (trans.commit())
			this.Response.setLogInfo(1, "操作成功!");
		else
			this.Response.setLogInfo(0, "操作失败!");
	}

	public void del() {
		String ids = $V("IDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}
		String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();

		ZCForumSchema forumCheck = new ZCForumSchema();
		ZCForumSet set = forumCheck.query(new QueryBuilder("where id in(" + ids + ")"));
		for (int i = 0; i < set.size(); ++i) {
			forumCheck = set.get(i);
			if (forumCheck.getParentID() == 0L) {
				long count = new QueryBuilder("select count(*) from ZCForum where " + sqlSiteID
						+ " and ParentID=" + forumCheck.getID() + " and ID not in (" + ids + ")")
						.executeLong();
				if (count > 0L) {
					this.Response.setStatus(0);
					this.Response.setMessage("不能删除分区\"" + forumCheck.getName()
							+ "\",该分区下还有未被选中的板块!");
					return;
				}
			}
		}

		String[] IDs = getForumIDs(ids);
		Transaction trans = new Transaction();
		for (int n = 0; n < IDs.length; ++n) {
			ZCForumSchema forum = new ZCForumSchema();
			forum.setID(IDs[n]);
			forum.fill();
			trans.add(forum, 5);
			if (forum.getParentID() == 0L) {
				ZCForumSet forumSet = forum.query(new QueryBuilder("where " + sqlSiteID
						+ " and ParentID=" + forum.getID()));
				trans.add(forumSet, 5);

				for (int i = 0; i < forumSet.size(); ++i) {
					ZCThemeSchema theme = new ZCThemeSchema();
					theme.setID(forumSet.get(i).getID());
					ZCThemeSet themeSet = theme.query();
					trans.add(themeSet, 5);
					ZCPostSchema post = new ZCPostSchema();

					for (int j = 0; j < themeSet.size(); ++j) {
						post.setID(themeSet.get(i).getID());
						ZCPostSet postSet = post.query();
						trans.add(postSet, 5);
					}
				}
			} else {
				ZCThemeSchema theme = new ZCThemeSchema();
				theme.setForumID(forum.getID());
				ZCThemeSet themeSet = theme.query();
				trans.add(themeSet, 5);
				ZCPostSchema post = new ZCPostSchema();

				for (int i = 0; i < themeSet.size(); ++i) {
					post.setThemeID(themeSet.get(i).getID());
					ZCPostSet postSet = post.query();
					trans.add(postSet, 5);
				}
			}
		}
		if (trans.commit())
			this.Response.setLogInfo(1, "删除成功!");
		else
			this.Response.setLogInfo(0, "操作失败!");
	}

	public void dg1Edit() {
		Transaction trans = new Transaction();
		ZCForumSchema forum = new ZCForumSchema();
		forum.setID($V("ID"));
		forum.fill();
		forum.setName($V("Name"));

		if ($V("ForumAdmin").trim().length() == 0 || ForumUtil.isExistMember($V("ForumAdmin"))) {
			if ($V("ForumAdmin").trim().length() == 0
					|| isExistAdmin(trans, $V("ForumAdmin"), forum)) {
				forum.setForumAdmin($V("ForumAdmin") + ",");
				ForumUtil.addAdminID(trans, (String.valueOf(forum.getID())), forum.getForumAdmin());
			} else {
				Response.setLogInfo(0, "请勿将分区版主重复设置在板块下");
				return;
			}
		} else {
			Response.setLogInfo(0, "输入了不存在的用户名");
			return;
		}

		trans.add(forum, 2);
		if (trans.commit())
			this.Response.setLogInfo(1, "操作成功！");
		else
			this.Response.setLogInfo(0, "操作失败！");
	}

	public void editSave() {
		Transaction trans = new Transaction();
		ZCForumSchema forum = new ZCForumSchema();
		forum.setID($V("ID"));
		forum.fill();
		if (forum.getParentID() == 0L) {
			ZCForumSet set = forum.query(new QueryBuilder("where ParentID=" + $V("ID")));
			for (int i = 0; i < set.size(); ++i) {
				forum = set.get(i);
				String name = forum.getName();
				forum.setValue(this.Request);
				forum.setName(name);
				forum.setParentID($V("ParentID"));
				forum.setType("forum");
				trans.add(forum, 2);
			}
		} else {
			forum.setValue(this.Request);
			trans.add(forum, 2);
		}
		if (trans.commit())
			this.Response.setLogInfo(1, "操作成功！");
		else
			this.Response.setLogInfo(0, "操作失败");
	}

	private String[] getForumIDs(String ids) {
		String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
		String sql = "select ID from ZCForum where " + sqlSiteID + " and (ID in (" + ids
				+ ") and ParentID not in (" + ids + ")) or (ID in (" + ids + ") and ParentID=0)";
		DataTable dt = new QueryBuilder(sql).executeDataTable();
		String[] IDs = new String[dt.getRowCount()];
		for (int i = 0; i < dt.getRowCount(); ++i) {
			IDs[i] = dt.getString(i, "ID");
		}
		return IDs;
	}

	private boolean isExistAdmin(Transaction trans, String forumAdmins, ZCForumSchema forum) {
		String[] admins = forumAdmins.split(",");
		if (forum.getParentID() == 0L) {
			DataTable dt = new QueryBuilder("select ForumAdmin,ID from ZCForum where ParentID="
					+ forum.getID()).executeDataTable();
			for (int i = 0; i < dt.getRowCount(); ++i)
				for (int j = 0; j < admins.length; ++j) {
					String ParentAdmin = dt.getString(i, "ForumAdmin");
					if (ParentAdmin.indexOf(admins[j] + ",") >= 0) {
						ParentAdmin = ParentAdmin.replaceAll(admins[j] + ",", "");
						trans.add(new QueryBuilder("update ZCForum set ForumAdmin='" + ParentAdmin
								+ "' where ID=" + dt.getString(i, "ID")));
					}
				}
		} else {
			String sql = "select ForumAdmin from ZCForum where ID = " + forum.getParentID();
			String groupAdmin = new QueryBuilder(sql).executeString();
			if (StringUtil.isEmpty(groupAdmin)) {
				return true;
			}
			for (int i = 0; i < admins.length; ++i) {
				if (groupAdmin.indexOf(admins[i] + ",") >= 0) {
					return false;
				}
			}
		}
		return true;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.bbs.admin.ForumAdmin JD-Core Version: 0.5.3
 */