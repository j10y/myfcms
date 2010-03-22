package com.zving.bbs;

import com.zving.bbs.admin.ForumScore;
import com.zving.bbs.admin.ForumUtil;
import com.zving.framework.Ajax;
import com.zving.framework.Config;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataListAction;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.UserLog;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCForumAttachmentSchema;
import com.zving.schema.ZCForumMemberSchema;
import com.zving.schema.ZCForumSchema;
import com.zving.schema.ZCPostSchema;
import com.zving.schema.ZCThemeSchema;
import java.text.DecimalFormat;
import java.util.Date;

public class Post extends Ajax {
	public static void getList(DataListAction dla) {
		String ThemeID = dla.getParams().getString("ThemeID");
		String ForumID = dla.getParam("ForumID");
		String SiteID = dla.getParam("SiteID");

		String sqlData = "select a.*,b.ThemeCount,b.ReplyCount,b.ForumScore, b.NickName,b.HeadImage,b.UseSelfImage,b.ForumSign,c.RegTime RegisterTime,c.LastLoginTime,d.Name GroupName,d.Image,d.Color from ZCPost a,ZCForumMember b,ZDMember c,ZCForumGroup d where a.AddUser=c.UserName and a.AddUser=b.UserName and VerifyFlag='Y' and Invisible='Y' and b.UserGroupID=d.ID and ThemeID="
				+ ThemeID + " order by layer";
		DataTable dt = new QueryBuilder(sqlData).executePagedDataTable(dla.getPageSize(), dla
				.getPageIndex());
		String sqlTotal = "select count(*)from ZCPost a,ZCForumMember b,ZDMember c,ZCForumGroup d where a.AddUser=c.UserName and a.AddUser=b.UserName and VerifyFlag='Y' and Invisible='Y' and b.UserGroupID=d.ID and ThemeID="
				+ ThemeID + " order by layer";
		int total = new QueryBuilder(sqlTotal).executeInt();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			String imagePath = dt.getString(i, "Image");
			if (imagePath.indexOf("../") == 0) {
				dt.set(i, "Image", imagePath.substring(3));
			}
		}
		loadFile(dt);
		dt = addColumns(SiteID, ForumID, dt);
		dla.setTotal(total);
		dla.bindData(dt);
	}

	public static void loadFile(DataTable dt) {
		dt.insertColumn("file");
		for (int i = 0; i < dt.getRowCount(); ++i) {
			String ID = dt.getString(i, "ID");
			String sql = "select * from ZCForumAttachment where PostID=" + ID;
			DataTable dtFile = new QueryBuilder(sql).executeDataTable();
			StringBuffer sb = new StringBuffer();
			if (dt.getRowCount() > 0) {
				for (int j = 0; j < dtFile.getRowCount(); ++j) {
					float size = (float) Long.parseLong(dtFile.getString(j, "AttSize"));
					DecimalFormat df = new DecimalFormat("#0.0");
					String fileSize = "";
					if ((size < 1000000.0F) && (size >= 1000.0F))
						fileSize = df.format(size / 1024.0F) + "KB";
					else if (size >= 1000000.0F)
						fileSize = df.format(size / 1024000.0F) + "MB";
					else if (size < 1000.0F) {
						fileSize = "1KB";
					}
					String[] ext = { "jpg", "gif", "zip", "rar", "bmp", "png", "doc", "xls",
							"html", "js", "mov", "mp4", "flv", "rm", "wmv", "swf", "txt", "mp3",
							"avi", "ppt", "pdf", "pptx", "xlsx", "docx" };
					String suffix = (String) dtFile.get(j, "Suffix");
					for (int n = 0; n < ext.length; ++n) {
						if (ext[n].equalsIgnoreCase(suffix)) {
							sb.append("<img src='Images/FileType/" + ext[n]
									+ ".gif' width='16' height='16' title='" + suffix
									+ "'/>&nbsp;&nbsp;");
							break;
						}
					}
					sb.append("<a href='#' onclick='downLoad(" + dtFile.getString(j, "ID") + ")'>"
							+ dtFile.getString(j, "Name") + "</a>(" + fileSize + ") 已下载："
							+ dtFile.getString(j, "DownCount") + "次<br/><br/>");
					if (dtFile.getString(j, "Type").equals("image")) {
						sb.append("<img  onload='resetSize(this)' src='/" + Config.getAppCode()
								+ dtFile.getString(j, "Path") + "'><br/><br/>");
					}
				}
				dt.set(i, "file", sb.toString());
			} else {
				dt.set(i, "file", "&nbsp;");
			}
		}
	}

	public void add() {
		if (ForumUtil.isNotReplyPost($V("SiteID"), $V("ForumID"))) {
			this.Response.setLogInfo(0, "您没有权利回复！");
			return;
		}
		ForumScore forumScore = new ForumScore($V("SiteID"));
		ForumPriv priv = new ForumPriv($V("SiteID"));
		Transaction trans = new Transaction();
		ZCPostSchema post = new ZCPostSchema();
		ZCThemeSchema theme = new ZCThemeSchema();
		ZCForumSchema forum = new ZCForumSchema();
		ZCForumMemberSchema user = new ZCForumMemberSchema();

		post.setValue(this.Request);
		post.setFirst("N");
		post.setID(NoUtil.getMaxID("PostID"));
		post.fill();
		post.setAddUser(User.getUserName());
		post.setAddTime(new Date());
		post.setInvisible("Y");
		post.setSiteID($V("SiteID"));
		theme.setID(post.getThemeID());
		theme.fill();
		forum.setID(theme.getForumID());
		forum.fill();
		post.setVerifyFlag((forum.getVerify().equals("Y")) ? "N" : "Y");
		user.setUserName(post.getAddUser());
		user.fill();
		if (priv.getPriv("Verify")) {
			post.setVerifyFlag("Y");
		}
		if (post.getVerifyFlag().equals("Y")) {
			post.setLayer(getMAXLayer(post.getThemeID()) + 1L);
			forum.setPostCount(forum.getPostCount() + 1);
			theme.setReplyCount(theme.getReplyCount() + 1);
			theme.setLastPostTime(new Date());
			theme.setOrderTime(new Date());
			user.setReplyCount(user.getReplyCount() + 1L);
			user.setForumScore(user.getForumScore() + forumScore.PublishPost);
			ForumUtil.userGroupChange(user);
			trans.add(user, 2);
		}
		trans.add(post, 1);
		trans.add(theme, 2);
		trans.add(forum, 2);
		if ((StringUtil.isNotEmpty($V("file"))) && ($V("file").length() > 0)) {
			String[] Attachments = $V("file").split(",");
			String[] indexs = $V("indexs").split(",");
			for (int i = 0; i < Attachments.length; ++i) {
				ZCForumAttachmentSchema attachment = new ZCForumAttachmentSchema();
				attachment.setID(NoUtil.getMaxID("ForumAttachmentID"));
				attachment.setPostID(post.getID());
				attachment.setSiteID($V("StieID"));
				String suffix = $V("file" + indexs[i]).substring(
						$V("file" + indexs[i]).lastIndexOf(".") + 1);
				if ((suffix.equalsIgnoreCase("jpg")) || (suffix.equalsIgnoreCase("gif"))
						|| (suffix.equalsIgnoreCase("jpeg")) || (suffix.equalsIgnoreCase("png")))
					attachment.setType("image");
				else if (suffix.equalsIgnoreCase("txt"))
					attachment.setType("file");
				else {
					attachment.setType("application");
				}
				attachment.setSuffix(suffix);
				attachment.setName($V("file" + indexs[i]).substring(
						$V("file" + indexs[i]).lastIndexOf("\\") + 1));
				String[] file = Attachments[i].split("#");
				attachment.setPath(file[0]);
				attachment.setAttSize(file[1]);
				attachment.setDownCount(0L);
				attachment.setAddUser(User.getUserName());
				attachment.setAddTime(new Date());
				trans.add(attachment, 1);
			}
		}
		if (trans.commit()) {
			if (post.getVerifyFlag().equals("Y"))
				this.Response.setLogInfo(1, "回复成功");
			else
				this.Response.setLogInfo(1, "管理员设置了审核机制，请等待审核!");
		} else
			this.Response.setLogInfo(0, "回复失败!");
	}

	public void addViewCount() {
		ZCThemeSchema theme = new ZCThemeSchema();
		theme.setID($V("ThemeID"));
		theme.fill();
		theme.setViewCount(theme.getViewCount() + 1);
		if (theme.update())
			this.Response.setLogInfo(1, "");
		else
			this.Response.setLogInfo(0, "");
	}

	public static Mapx init(Mapx params) {
		String ForumID = params.getString("ForumID");
		String ThemeID = params.getString("ThemeID");
		String user = params.getString("User");
		String LastPoster = params.getString("LastPoster");
		String SiteID = params.getString("SiteID");
		String condition = "";
		if ((!(StringUtil.isEmpty(user))) && (user.equals("current")))
			condition = " and AddUser='" + User.getUserName() + "'";
		else if ((!(StringUtil.isEmpty(user))) && (user.equals("reply")))
			condition = " and ThemeID in (select ThemeID from ZCPost where first='N' and AddUser='"
					+ User.getUserName() + "')";
		else if (!(StringUtil.isEmpty(LastPoster)))
			condition = " and AddUser='" + LastPoster + "'";
		else {
			condition = " and ForumID=" + ForumID;
		}

		String sqlNext = "select ThemeID,ForumID from ZCPost where Invisible='Y' and VerifyFlag='Y' and ThemeID>"
				+ ThemeID + condition + " and first='Y' order by ThemeID";
		String sqlPrevious = "select ThemeID,ForumID from ZCPost where Invisible='Y' and VerifyFlag='Y' and ThemeID<"
				+ ThemeID + condition + " and first='Y' order by ThemeID desc";

		DataTable dtNext = new QueryBuilder(sqlNext).executePagedDataTable(1, 0);
		DataTable dtPrevious = new QueryBuilder(sqlPrevious).executePagedDataTable(1, 0);

		String ThemeIDNext = (dtNext.getRowCount() > 0) ? dtNext.getString(0, "ThemeID") : null;
		String ThemeIDPrevious = (dtPrevious.getRowCount() > 0) ? dtPrevious
				.getString(0, "ThemeID") : null;
		String ForumIDNext = (dtNext.getRowCount() > 0) ? dtNext.getString(0, "ForumID") : null;
		String ForumIDPrevious = (dtPrevious.getRowCount() > 0) ? dtPrevious
				.getString(0, "ForumID") : null;
		ZCThemeSchema theme = new ZCThemeSchema();
		theme.setID(ThemeID);
		theme.fill();
		Mapx map = theme.toMapx();
		ZCForumSchema forum = new ZCForumSchema();
		forum.setID(ForumID);
		forum.fill();

		ForumPriv priv = new ForumPriv(SiteID);
		ForumRule rule = new ForumRule(ForumID);
		if ((ForumUtil.isAdmin(ForumID, SiteID) > 0) && (priv.getPriv("AllowEdit"))) {
			map.put("editPriv", "Y");
		}

		if (ForumUtil.isNotReplyPost(SiteID, ForumID)) {
			map.put("RuleReply", "N");
		}

		if (ForumUtil.isReplyPost(SiteID, ForumID)) {
			map.put("NewReplyButton", "<a href='#' onclick='reply()'>回复本话题</a>");
		}
		if ((((rule.getRule("AllowTheme")) || (ForumUtil.isAdmin(ForumID, SiteID) > 0)))
				&& (priv.getPriv("AllowTheme"))) {
			map.put("NewThemeButton", "<a href='ThemeAdd.jsp?ForumID=" + ForumID + "&SiteID="
					+ SiteID + "'>发表新话题</a>");
		}

		map.put("Name", forum.getName());
		map.put("AddUser", User.getUserName());
		map.put("ThemeIDPrevious", ThemeIDPrevious);
		map.put("ThemeIDNext", ThemeIDNext);
		map.put("ForumIDPrevious", ForumIDPrevious);
		map.put("ForumIDNext", ForumIDNext);
		map.put("Priv", ForumUtil.initPriv(ForumID, SiteID));
		map.put("StieID", SiteID);
		return map;
	}

	public void del() {
		String ID = $V("ID");
		ForumScore forumScore = new ForumScore($V("SiteID"));
		Transaction trans = new Transaction();
		ZCPostSchema post = new ZCPostSchema();
		post.setID(ID);
		post.fill();
		post.setInvisible("N");
		trans.add(post, 2);

		boolean flag = post.getFirst().equals("Y");
		String sql = "";
		if (flag)
			sql = "update ZCpost set Invisible='N' where ThemeID=" + post.getThemeID();
		else {
			sql = "update ZCpost set layer=layer-1 where layer>" + post.getLayer();
		}
		trans.add(new QueryBuilder(sql));
		ZCThemeSchema theme = new ZCThemeSchema();
		theme.setID(post.getThemeID());
		theme.fill();
		if (flag)
			theme.setStatus("N");
		else {
			theme.setReplyCount(theme.getReplyCount() - 1);
		}
		trans.add(theme, 2);
		ZCForumSchema forum = new ZCForumSchema();
		forum.setID(theme.getForumID());
		forum.fill();
		if (flag) {
			forum.setThemeCount(forum.getThemeCount() - 1);
			forum.setPostCount(forum.getPostCount() - theme.getReplyCount());
			String sqlNext = "select count(*) from ZCTheme where status='Y' and VerifyFlag='Y' and ForumID="
					+ forum.getID() + " and ID>" + theme.getID();
			int count = new QueryBuilder(sqlNext).executeInt();
			if (count == 0) {
				String sqlPrevious = "select Subject,AddUser from ZCTheme where status='Y' and VerifyFlag='Y' and ForumID="
						+ forum.getID() + " and ID<" + theme.getID() + " order by ID desc";
				DataTable dt = new QueryBuilder(sqlPrevious).executePagedDataTable(1, 0);
				if (dt.getRowCount() > 0) {
					forum.setLastPost(dt.getString(0, "Subject"));
					forum.setLastPoster(dt.getString(0, "AddUser"));
				} else {
					forum.setLastPost("");
					forum.setLastPoster("");
				}
			}
		}
		forum.setPostCount(forum.getPostCount() - 1);
		trans.add(forum, 2);
		ZCForumMemberSchema user = new ZCForumMemberSchema();
		user.setUserName(post.getAddUser());
		user.fill();

		if (flag) {
			user.setForumScore(user.getForumScore() + forumScore.DeleteTheme);
			user.setThemeCount(user.getThemeCount() - 1L);
			ForumUtil.userGroupChange(user);
		} else {
			user.setForumScore(user.getForumScore() + forumScore.DeletePost);
			user.setReplyCount(user.getReplyCount() - 1L);
		}
		trans.add(user, 2);
		if (trans.commit()) {
			UserLog.log("Forum", "DelPost", "删除用户" + post.getAddUser() + "的帖子成功", this.Request
					.getClientIP());
			if (flag) {
				this.Response.setLogInfo(2, "删除成功");
				return;
			}
			this.Response.setLogInfo(1, "删除成功");
		} else {
			UserLog.log("Forum", "DelPost", "删除用户" + post.getAddUser() + "的帖子失败", this.Request
					.getClientIP());
			this.Response.setLogInfo(0, "删除失败!");
		}
	}

	public void edit() {
		ZCPostSchema post = new ZCPostSchema();
		post.setID($V("ID"));

		if (post.fill()) {
			if (!(ForumUtil.isOperateMember(post.getAddUser()))) {
				this.Response.setLogInfo(0, "您不能编辑该用户的发帖！");
				return;
			}
			if (post.getInvisible().equals("N")) {
				this.Response.setLogInfo(0, "该帖已被删除");
				return;
			}
			this.Response.setStatus(1);
			this.Response.put("Message", post.getMessage());
		} else {
			this.Response.setLogInfo(0, "该帖不存在");
		}
	}

	public void editSave() {
		Transaction trans = new Transaction();
		ZCPostSchema post = new ZCPostSchema();
		post.setID($V("ID"));
		if (post.fill()) {
			if (!(ForumUtil.isEditPost(String.valueOf(post.getSiteID()), String.valueOf(post
					.getForumID()), post.getAddUser()))) {
				this.Response.setLogInfo(0, "您没有编辑帖子的权限");
				return;
			}
			if (post.getInvisible().equals("N")) {
				this.Response.setLogInfo(0, "该帖已被删除");
				return;
			}
			post.setValue(this.Request);
			trans.add(post, 2);
			if (trans.commit()) {
				UserLog.log("Forum", "EditPost", "编辑用户" + post.getAddUser() + "的帖子成功", this.Request
						.getClientIP());
				this.Response.setLogInfo(1, "修改成功");
			} else {
				UserLog.log("Forum", "EditPost", "编辑用户" + post.getAddUser() + "的帖子失败", this.Request
						.getClientIP());
				this.Response.setLogInfo(0, "修改失败");
			}
		} else {
			this.Response.setLogInfo(0, "该帖不存在");
		}
	}

	public void hide() {
		ZCPostSchema post = new ZCPostSchema();
		post.setID($V("ID"));
		post.fill();
		if ((StringUtil.isNotEmpty(post.getIsHidden())) && (post.getIsHidden().equals("Y")))
			post.setIsHidden("N");
		else {
			post.setIsHidden("Y");
		}
		if (post.update()) {
			UserLog.log("Forum", "HidePost", "解除/屏蔽用户" + post.getAddUser() + "被屏蔽的帖子成功",
					this.Request.getClientIP());
			this.Response.setLogInfo(1, "操作成功！");
		} else {
			UserLog.log("Forum", "HidePost", "解除/屏蔽用户" + post.getAddUser() + "被屏蔽的帖子失败",
					this.Request.getClientIP());
			this.Response.setLogInfo(0, "操作失败！");
		}
	}

	public void isOperaterMember() {
		ZCPostSchema post = new ZCPostSchema();
		post.setID($V("ID"));
		post.fill();
		if (!(ForumUtil.isOperateMember(post.getAddUser())))
			this.Response.setStatus(0);
		else
			this.Response.setStatus(1);
	}

	private long getMAXLayer(long ThemeID) {
		String sql = "select Layer from ZCPost where Invisible='Y' and ThemeID=" + ThemeID
				+ " order by Layer desc";
		long layer = new QueryBuilder(sql).executePagedDataTable(1, 0).getLong(0, 0);
		return layer;
	}

	private static DataTable addColumns(String SiteID, String ForumID, DataTable dt) {
		dt.insertColumn("FirstSubject");
		dt.set(0, "FirstSubject", "<h4>" + dt.getString(0, "Subject") + "</h4>");

		dt.insertColumn("Button");
		ForumPriv priv = new ForumPriv(SiteID);
		ForumRule rule = new ForumRule(ForumID);
		int flag = ForumUtil.isAdmin(ForumID, SiteID);
		for (int i = 0; i < dt.getRowCount(); ++i) {
			StringBuffer sb = new StringBuffer();

			if ((((rule.getRule("ReplyPost")) || (flag > 0))) && (priv.getPriv("AllowReply"))) {
				sb.append("<a href='#' onclick='quote(" + dt.get(i, "ID")
						+ ")'>引用</a>|<a href='javascript:void(0)' onclick=\"fastreply("
						+ dt.get(i, "Layer") + ",'" + dt.get(i, "AddUser") + "')\">回复</a>");
			}

			if ((flag > 0) && (priv.getPriv("AllowEdit")))
				sb.append("|<a href='javascript:void(0)' onclick='isOperaterMember("
						+ dt.get(i, "ID") + ",1)'>编辑</a>");
			else if ((rule.getRule("EditPost")) && (StringUtil.isNotEmpty(User.getUserName()))
					&& (User.getUserName().equals(dt.getString(i, "AddUser")))) {
				sb.append("|<a href='javascript:void(0)' onclick='isOperaterMember("
						+ dt.get(i, "ID") + ",1)'>编辑</a>");
			}

			if ((flag > 0) && (priv.getPriv("AllowDel"))) {
				sb.append("|<a href='javascript:void(0)' onclick='isOperaterMember("
						+ dt.get(i, "ID") + ",2)'>删除</a>");
			}

			if ((flag > 0) && (priv.getPriv("Hide"))) {
				if ((StringUtil.isNotEmpty(dt.getString(i, "IsHidden")))
						&& (dt.getString(i, "IsHidden").equals("Y")))
					sb.append("|<a href='javascript:void(0)' onclick='isOperaterMember("
							+ dt.get(i, "ID") + ",3)'>解除屏蔽</a>");
				else {
					sb.append("|<a href='javascript:void(0)' onclick='isOperaterMember("
							+ dt.get(i, "ID") + ",3)'>屏蔽</a>");
				}
			}
			dt.set(i, "Button", sb.toString());
		}

		for (int i = 0; i < dt.getRowCount(); ++i) {
			if (dt.getString(i, "UseSelfImage").equals("N")) {
				if (StringUtil.isNotEmpty(dt.getString(i, "Image")))
					dt.set(i, "HeadImage", dt.getString(i, "Image"));
				else {
					dt.set(i, "HeadImage", "Images/HeadImage01.gif");
				}
			}

			if ((StringUtil.isNotEmpty(dt.getString(i, "IsHidden")))
					&& (dt.getString(i, "IsHidden").equals("Y"))) {
				if (ForumUtil.isAdmin(ForumID, SiteID) > 0) {
					dt.set(i, "Message",
							"<div class='shield_post'><span>提示：</span>该帖已被屏蔽,只有版主与管理员可见</div><br/>"
									+ dt.getString(i, "Message"));
				} else {
					dt.set(i, "Message", "<div class='shield_post'><span>提示：</span>该帖已被屏蔽</div>");
					dt.set(i, "File", "");
				}
			}
		}
		return dt;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.bbs.Post JD-Core Version: 0.5.3
 */