 package com.zving.bbs;
 
 import com.zving.bbs.admin.ForumScore;
 import com.zving.bbs.admin.ForumUtil;
 import com.zving.framework.Ajax;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCForumAttachmentSchema;
 import com.zving.schema.ZCForumMemberSchema;
 import com.zving.schema.ZCForumSchema;
 import com.zving.schema.ZCPostSchema;
 import com.zving.schema.ZCThemeSchema;
 import java.util.Date;
 
 public class ThemeAdd extends Ajax
 {
   public void add()
   {
     if (ForumUtil.isNotSendTheme($V("SiteID"), $V("ForumID"))) {
       this.Response.setLogInfo(0, "您没有发帖的权限");
       return;
     }
     ForumScore forumScore = new ForumScore($V("SiteID"));
     ZCThemeSchema theme = new ZCThemeSchema();
     ZCForumMemberSchema user = new ZCForumMemberSchema();
     ZCForumSchema forum = new ZCForumSchema();
     ForumPriv priv = new ForumPriv($V("SiteID"));
     Transaction trans = new Transaction();
 
     theme.setValue(this.Request);
     theme.setID(NoUtil.getMaxID("ThemeID"));
     theme.fill();
     forum.setID(theme.getForumID());
     forum.fill();
     theme.setSiteID($V("SiteID"));
     theme.setAddUser(User.getUserName());
     theme.setOrderFlag(0L);
     theme.setViewCount(0);
     theme.setReplyCount(0);
     theme.setTopTheme("N");
     theme.setBest("N");
     theme.setType("0");
     theme.setAddTime(new Date());
     theme.setOrderTime(new Date());
     theme.setStatus("Y");
     theme.setVerifyFlag((forum.getVerify().equals("N")) ? "Y" : "N");
     trans.add(theme, 1);
 
     ZCPostSchema post = new ZCPostSchema();
     post.setValue(this.Request);
     post.setFirst("Y");
     post.setID(NoUtil.getMaxID("PostID"));
     post.setThemeID(theme.getID());
     post.setAddUser(User.getUserName());
     post.setAddTime(theme.getAddTime());
     post.setVerifyFlag((forum.getVerify().equals("N")) ? "Y" : "N");
     post.setInvisible("Y");
     post.setLayer(1L);
     trans.add(post, 1);
     user.setUserName(theme.getAddUser());
     user.fill();
 
     if (priv.getPriv("Verify")) {
       theme.setVerifyFlag("Y");
       post.setVerifyFlag("Y");
     }
 
     if (theme.getVerifyFlag().equals("Y")) {
       user.setForumScore(user.getForumScore() + forumScore.PublishTheme);
       ForumUtil.userGroupChange(user);
       user.setThemeCount(user.getThemeCount() + 1L);
       forum.setThemeCount(forum.getThemeCount() + 1);
       forum.setLastThemeID(theme.getID());
       forum.setLastPost(theme.getSubject());
       forum.setLastPoster(theme.getAddUser());
     }
     if (post.getVerifyFlag().equals("Y")) {
       forum.setPostCount(forum.getPostCount() + 1);
     }
     trans.add(user, 2);
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
       if (theme.getVerifyFlag().equals("Y"))
         this.Response.setLogInfo(1, "新增成功");
       else
         this.Response.setLogInfo(1, "论坛管理员设置了审核机制，请等待审核！");
     }
     else
       this.Response.setLogInfo(0, "新增失败!");
   }
 
   public static Mapx initAddDialog(Mapx params)
   {
     String ForumID = params.getString("ForumID");
     String SiteID = params.getString("SiteID");
     params.put("Priv", ForumUtil.initPriv(ForumID, SiteID));
     return params; }
 
   public static Mapx init(Mapx params) {
     String ForumID = params.getString("ForumID");
     String ThemeID = params.getString("ThemeID");
     String SiteID = params.getString("SiteID");
     ZCThemeSchema theme = new ZCThemeSchema();
     theme.setID(ThemeID);
     theme.setForumID(ForumID);
     Mapx map = theme.toMapx();
     ZCForumSchema forum = new ZCForumSchema();
     forum.setID(ForumID);
     forum.fill();
     map.put("Name", forum.getName());
     map.put("AddUser", User.getUserName());
     map.put("SiteID", SiteID);
     map.put("Priv", ForumUtil.initPriv(ForumID));
 
     return map;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.ThemeAdd
 * JD-Core Version:    0.5.3
 */