 package com.zving.bbs;
 
 import com.zving.framework.User;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZCAdminGroupSchema;
 import com.zving.schema.ZCForumGroupSchema;
 import com.zving.schema.ZCForumMemberSchema;
 
 public class ForumPriv
 {
   static final String NO = "N";
   static final String YES = "Y";
   Mapx map;
 
   public ForumPriv(long SiteID)
   {
     this(User.getUserName(), SiteID); }
 
   public ForumPriv(String SiteID) {
     this(User.getUserName(), SiteID);
   }
 
   public ForumPriv(String UserName, String SiteID)
   {
     this.map = new Mapx();
 
     initPriv(UserName, SiteID);
   }
 
   public ForumPriv(String UserName, long SiteID)
   {
     this.map = new Mapx();
 
     initPriv(UserName, SiteID);
   }
 
   public boolean getPriv(String PrivType)
   {
     return this.map.get(PrivType).equals("Y");
   }
 
   private void initPriv(String UserName, String SiteID)
   {
     initPriv(UserName, Long.parseLong(SiteID));
   }
 
   private void initPriv(String UserName, long SiteID) {
     String sqlSiteID = "SiteID=" + SiteID;
     if (StringUtil.isNotEmpty(UserName)) {
       ZCForumMemberSchema member = new ZCForumMemberSchema();
       member.setUserName(UserName);
       member.fill();
       if (member.getUserName().equals("admin")) {
         long groupID = new QueryBuilder("select ID from ZCForumGroup where " + sqlSiteID + " and SystemName='系统管理员'").executeLong();
         member.setUserGroupID(groupID);
         member.setAdminID(groupID);
       }
 
       ZCForumGroupSchema group = new ZCForumGroupSchema();
       group.setID(member.getUserGroupID());
       group.fill();
       userGroupPriv(group);
 
       if (group.getType().equals("3")) {
         ZCAdminGroupSchema groupSpecial = new ZCAdminGroupSchema();
         groupSpecial.setGroupID(member.getUserGroupID());
         if (groupSpecial.fill()) {
           addAdminPriv(groupSpecial);
           return;
         }
       }
 
       ZCAdminGroupSchema groupAdmin = new ZCAdminGroupSchema();
       groupAdmin.setGroupID(member.getAdminID());
       if (groupAdmin.fill())
         addAdminPriv(groupAdmin);
       else
         addAdminPriv(null);
     }
     else {
       String sql = "select ID from ZCForumGroup where " + sqlSiteID + " and SystemName='游客'";
       DataTable dt = new QueryBuilder(sql).executeDataTable();
       if (dt.getRowCount() > 0) {
         ZCForumGroupSchema group = new ZCForumGroupSchema();
         group.setID(dt.getString(0, "ID"));
         group.fill();
         userGroupPriv(group);
         addAdminPriv(null);
       } else {
         cleanPriv();
       }
     }
   }
 
   private void userGroupPriv(ZCForumGroupSchema group)
   {
     this.map.put("AllowTheme", group.getAllowTheme());
     this.map.put("AllowReply", group.getAllowReply());
     this.map.put("AllowSearch", group.getAllowSearch());
     this.map.put("AllowUserInfo", group.getAllowUserInfo());
     this.map.put("AllowPanel", group.getAllowPanel());
     this.map.put("AllowNickName", group.getAllowTheme());
     this.map.put("AllowVisit", group.getAllowVisit());
     this.map.put("AllowHeadImage", group.getAllowHeadImage());
     this.map.put("AllowFace", group.getAllowFace());
     this.map.put("AllowAutograph", group.getAllowAutograph());
     this.map.put("Verify", group.getVerify());
   }
 
   private void addAdminPriv(ZCAdminGroupSchema group)
   {
     if (group != null) {
       this.map.put("Admin", "Y");
       this.map.put("AllowEditUser", group.getAllowEditUser());
       this.map.put("AllowForbidUser", group.getAllowForbidUser());
       this.map.put("AllowEditForum", group.getAllowEditForum());
       this.map.put("AllowVerfyPost", group.getAllowVerfyPost());
       this.map.put("AllowDel", group.getAllowDel());
       this.map.put("AllowEdit", group.getAllowEdit());
       this.map.put("Hide", group.getHide());
       this.map.put("MoveTheme", group.getMoveTheme());
       this.map.put("RemoveTheme", group.getRemoveTheme());
       this.map.put("TopTheme", group.getRemoveTheme());
       this.map.put("BrightTheme", group.getBrightTheme());
       this.map.put("UpOrDownTheme", group.getUpOrDownTheme());
       this.map.put("BestTheme", group.getBestTheme());
     }
     else {
       this.map.put("Admin", "N");
       this.map.put("AllowEditPost", "N");
       this.map.put("AllowForbidUser", "N");
       this.map.put("AllowEditForum", "N");
       this.map.put("AllowVerfyPost", "N");
       this.map.put("AllowDel", "N");
       this.map.put("AllowEdit", "N");
       this.map.put("Hide", "N");
       this.map.put("MoveTheme", "N");
       this.map.put("RemoveTheme", "N");
       this.map.put("TopTheme", "N");
       this.map.put("BrightTheme", "N");
       this.map.put("UpOrDownTheme", "N");
       this.map.put("BestTheme", "N"); }
   }
 
   private void cleanPriv() {
     this.map.put("AllowTheme", "N");
     this.map.put("AllowReply", "N");
     this.map.put("AllowDel", "N");
     this.map.put("AllowEdit", "N");
     this.map.put("AllowSearch", "N");
     this.map.put("AllowUserInfo", "N");
     this.map.put("AllowPanel", "N");
     this.map.put("AllowNickName", "N");
     this.map.put("AllowVisit", "N");
     this.map.put("AllowHeadImage", "N");
     this.map.put("AllowFace", "N");
     this.map.put("AllowAutograph", "N");
     this.map.put("Verify", "N");
     this.map.put("RemoveTheme", "N");
     this.map.put("TopTheme", "N");
     this.map.put("BrightTheme", "N");
     this.map.put("UpOrDownTheme", "N");
     this.map.put("BestTheme", "N");
     this.map.put("Admin", "N");
     this.map.put("Hide", "N");
     this.map.put("MoveTheme", "N");
     this.map.put("AllowEditPost", "N");
     this.map.put("AllowForbidUser", "N");
     this.map.put("AllowEditForum", "N");
     this.map.put("AllowVerfyPost", "N");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.ForumPriv
 * JD-Core Version:    0.5.3
 */