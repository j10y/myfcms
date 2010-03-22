 package com.zving.bbs;
 
 import com.zving.bbs.admin.ForumScore;
 import com.zving.bbs.admin.ForumUtil;
 import com.zving.framework.Ajax;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.UserLog;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCForumAttachmentSchema;
 import com.zving.schema.ZCForumGroupSchema;
 import com.zving.schema.ZCForumMemberSchema;
 import com.zving.schema.ZCForumSchema;
 import com.zving.schema.ZCPostSchema;
 import com.zving.schema.ZCThemeSchema;
 import java.util.Date;
 
 public class PostAdd extends Ajax
 {
   public void add()
   {
     if (ForumUtil.isNotReplyPost($V("SiteID"), $V("ForumID"))) {
       this.Response.setLogInfo(0, "您没有权利回复！");
       return;
     }
     ForumScore forumScore = new ForumScore($V("SiteID"));
     ForumPriv priv = new ForumPriv($V("SiteID"));
     Transaction trans = new Transaction();
     ZCPostSchema post = new ZCPostSchema();
     ZCThemeSchema theme = new ZCThemeSchema();
     ZCForumMemberSchema user = new ZCForumMemberSchema();
     ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
     ZCForumSchema forum = new ZCForumSchema();
 
     post.setFirst("N");
     post.setValue(this.Request);
     post.setID(NoUtil.getMaxID("PostID"));
     post.setSiteID($V("SiteID"));
     post.setFirst("N");
     post.setMessage(getQuoteContent($V("PostID")) + $V("Message"));
     theme.setID(post.getThemeID());
     theme.fill();
     forum.setID(theme.getForumID());
     forum.fill();
     post.setAddUser(User.getUserName());
     post.setAddTime(new Date());
     post.setInvisible("Y");
     post.setVerifyFlag((forum.getVerify().equals("Y")) ? "N" : "Y");
     user.setUserName(post.getAddUser());
     user.fill();
     userGroup.setID(user.getUserGroupID());
     userGroup.fill();
     if (userGroup.getVerify().equals("Y")) {
       post.setVerifyFlag("Y");
     }
 
     trans.add(user, 2);
 
     if (priv.getPriv("Verify")) {
       post.setVerifyFlag("Y");
     }
     if (post.getVerifyFlag().equals("Y")) {
       post.setLayer(getMAXLayer(post.getThemeID()) + 1L);
       forum.setPostCount(forum.getPostCount() + 1);
       user.setReplyCount(user.getReplyCount() + 1L);
       user.setForumScore(user.getForumScore() + forumScore.PublishPost);
       ForumUtil.userGroupChange(user);
       theme.setReplyCount(theme.getReplyCount() + 1);
       theme.setLastPostTime(new Date());
       theme.setOrderTime(new Date());
     }
     trans.add(post, 1);
     trans.add(theme, 2);
     trans.add(forum, 2);
     if ($V("file").length() > 0) {
       String[] Attachments = $V("file").split(",");
       String[] indexs = $V("indexs").split(",");
       for (int i = 0; i < Attachments.length; ++i) {
         ZCForumAttachmentSchema attachment = new ZCForumAttachmentSchema();
         attachment.setID(NoUtil.getMaxID("ForumAttachmentID"));
         attachment.setPostID(post.getID());
         attachment.setSiteID($V("SiteID"));
         String suffix = $V("file" + indexs[i]).substring($V("file" + indexs[i]).lastIndexOf(".") + 1);
         if ((suffix.equalsIgnoreCase("jpg")) || (suffix.equalsIgnoreCase("gif")) || (suffix.equalsIgnoreCase("jpeg")) || (suffix.equalsIgnoreCase("png")))
           attachment.setType("image");
         else if ((suffix.equalsIgnoreCase("txt")) || (suffix.equalsIgnoreCase("doc")) || (suffix.equalsIgnoreCase("xls")))
           attachment.setType("file");
         else if ((suffix.equalsIgnoreCase("mp3")) || (suffix.equalsIgnoreCase("wma")) || (suffix.equalsIgnoreCase("mp4")))
           attachment.setType("audio");
         else {
           attachment.setType("application");
         }
         attachment.setSuffix(suffix);
         attachment.setName($V("file" + indexs[i]).substring($V("file" + indexs[i]).lastIndexOf("\\") + 1));
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
     }
     else
       this.Response.setLogInfo(0, "回复失败!");
   }
 
   public static Mapx init(Mapx params) {
     String ForumID = params.getString("ForumID");
     String ThemeID = params.getString("ThemeID");
     String SiteID = params.getString("SiteID");
     ZCThemeSchema theme = new ZCThemeSchema();
     theme.setID(ThemeID);
     theme.setForumID(ForumID);
     theme.fill();
     Mapx map = theme.toMapx();
     ZCForumSchema forum = new ZCForumSchema();
     forum.setID(ForumID);
     forum.fill();
 
     map.put("SiteID", params.getString("SiteID"));
     map.put("Name", forum.getName());
     map.put("ForumID", forum.getID());
     map.put("AddUser", User.getUserName());
     map.put("Priv", ForumUtil.initPriv(ForumID, SiteID));
     return map; }
 
   public static Mapx initAddDialog(Mapx params) {
     String PostID = params.getString("ID");
     String SiteID = params.getString("SiteID");
     if (!(StringUtil.isEmpty(PostID))) {
       ZCPostSchema post = new ZCPostSchema();
       post.setID(PostID);
       post.fill();
       params = post.toMapx();
       String subject = "引用于" + post.getAddUser() + "的回复";
       params.put("SiteID", SiteID);
       params.put("AddUser", User.getUserName());
       params.put("subject", subject);
       params.put("Priv", ForumUtil.initPriv(params.getString("SiteID")));
     }
     return params; }
 
   public static Mapx initEdit(Mapx params) {
     String PostID = params.getString("ID");
 
     if (!(StringUtil.isEmpty(PostID))) {
       ZCPostSchema post = new ZCPostSchema();
       post.setID(PostID);
       post.fill();
       String message = post.getMessage();
       if (message.startsWith("<div class='quote'>")) {
         String subMessage = message.substring(message.lastIndexOf("</div>"));
         post.setMessage(subMessage);
       }
       params = post.toMapx();
     }
     return params;
   }
 
   public void editPost()
   {
     Transaction trans = new Transaction();
     ZCPostSchema post = new ZCPostSchema();
     ZCThemeSchema theme = new ZCThemeSchema();
     post.setID($V("ID"));
     post.fill();
 
     if (!(ForumUtil.isEditPost(String.valueOf(post.getSiteID()), String.valueOf(post.getForumID()), post.getAddUser()))) {
       this.Response.setLogInfo(0, "您没有编辑帖子的权限");
       return;
     }
     theme.setID(post.getThemeID());
     theme.fill();
     String message = post.getMessage();
     post.setValue(this.Request);
 
     if (message.startsWith("<div class='quote'>")) {
       StringBuffer newMessage = new StringBuffer(message.substring(0, message.lastIndexOf("</div>") + 6));
       newMessage.append(post.getMessage());
       post.setMessage(newMessage.toString());
     }
     if (post.getFirst().equals("Y")) {
       theme.setSubject(post.getSubject());
     }
     trans.add(post, 2);
     trans.add(theme, 2);
     if (trans.commit()) {
       UserLog.log("Forum", "EditPost", "编辑用户" + post.getAddUser() + "的帖子成功", this.Request.getClientIP());
       this.Response.setLogInfo(1, "修改成功");
     } else {
       UserLog.log("Forum", "EditPost", "编辑用户" + post.getAddUser() + "的帖子失败", this.Request.getClientIP());
       this.Response.setLogInfo(0, "修改失败");
     }
   }
 
   private long getMAXLayer(long ThemeID)
   {
     String sql = "select Layer from ZCPost where ThemeID=" + ThemeID + " order by Layer desc";
     long layer = new QueryBuilder(sql).executePagedDataTable(1, 0).getLong(0, 0);
     return layer;
   }
 
   private String getQuoteContent(String PostID)
   {
     ZCPostSchema post = new ZCPostSchema();
     post.setID(PostID);
     post.fill();
     String quote = post.getMessage();
     if (quote.indexOf("<h5>引用</h5>") >= 0) {
       quote = StringUtil.replaceAllIgnoreCase(quote, "<h5>引用</h5>", "");
     }
     String content = "<div class='quote'><div class='blockquote'><h5>引用</h5>";
     content = content + "<i>" + post.getAddUser() + "</i>发表于" + post.getAddTime() + "<br>";
     quote = content + quote + "</div></div>";
     return quote;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.PostAdd
 * JD-Core Version:    0.5.3
 */