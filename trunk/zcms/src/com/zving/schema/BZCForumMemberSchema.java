 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCForumMemberSchema extends Schema
 {
   private String UserName;
   private Long SiteID;
   private Long AdminID;
   private Long UserGroupID;
   private String NickName;
   private Long ThemeCount;
   private Long ReplyCount;
   private String HeadImage;
   private String UseSelfImage;
   private String Status;
   private Long ForumScore;
   private String ForumSign;
   private Date LastLoginTime;
   private Date LastLogoutTime;
   private String prop1;
   private String prop2;
   private String prop3;
   private String prop4;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("UserName", 1, 0, 50, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, false, false), 
     new SchemaColumn("AdminID", 7, 2, 0, 0, false, false), 
     new SchemaColumn("UserGroupID", 7, 3, 0, 0, false, false), 
     new SchemaColumn("NickName", 1, 4, 100, 0, false, false), 
     new SchemaColumn("ThemeCount", 7, 5, 0, 0, false, false), 
     new SchemaColumn("ReplyCount", 7, 6, 0, 0, false, false), 
     new SchemaColumn("HeadImage", 1, 7, 500, 0, false, false), 
     new SchemaColumn("UseSelfImage", 1, 8, 2, 0, false, false), 
     new SchemaColumn("Status", 1, 9, 2, 0, false, false), 
     new SchemaColumn("ForumScore", 7, 10, 0, 0, false, false), 
     new SchemaColumn("ForumSign", 1, 11, 1000, 0, false, false), 
     new SchemaColumn("LastLoginTime", 0, 12, 0, 0, false, false), 
     new SchemaColumn("LastLogoutTime", 0, 13, 0, 0, false, false), 
     new SchemaColumn("prop1", 1, 14, 50, 0, false, false), 
     new SchemaColumn("prop2", 1, 15, 50, 0, false, false), 
     new SchemaColumn("prop3", 1, 16, 50, 0, false, false), 
     new SchemaColumn("prop4", 1, 17, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 18, 100, 0, true, false), 
     new SchemaColumn("AddTime", 0, 19, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 20, 100, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 21, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 22, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 23, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 24, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 25, 50, 0, false, false) };
   public static final String _TableCode = "BZCForumMember";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCForumMember values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCForumMember set UserName=?,SiteID=?,AdminID=?,UserGroupID=?,NickName=?,ThemeCount=?,ReplyCount=?,HeadImage=?,UseSelfImage=?,Status=?,ForumScore=?,ForumSign=?,LastLoginTime=?,LastLogoutTime=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCForumMember  where UserName=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCForumMember  where UserName=? and BackupNo=?";
 
   public BZCForumMemberSchema()
   {
     this.TableCode = "BZCForumMember";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCForumMember values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCForumMember set UserName=?,SiteID=?,AdminID=?,UserGroupID=?,NickName=?,ThemeCount=?,ReplyCount=?,HeadImage=?,UseSelfImage=?,Status=?,ForumScore=?,ForumSign=?,LastLoginTime=?,LastLogoutTime=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCForumMember  where UserName=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCForumMember  where UserName=? and BackupNo=?";
     this.HasSetFlag = new boolean[26];
   }
 
   protected Schema newInstance() {
     return new BZCForumMemberSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCForumMemberSet();
   }
 
   public BZCForumMemberSet query() {
     return query(null, -1, -1);
   }
 
   public BZCForumMemberSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCForumMemberSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCForumMemberSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCForumMemberSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.UserName = ((String)v); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.AdminID = null; else this.AdminID = new Long(v.toString()); return; }
     if (i == 3) { if (v == null) this.UserGroupID = null; else this.UserGroupID = new Long(v.toString()); return; }
     if (i == 4) { this.NickName = ((String)v); return; }
     if (i == 5) { if (v == null) this.ThemeCount = null; else this.ThemeCount = new Long(v.toString()); return; }
     if (i == 6) { if (v == null) this.ReplyCount = null; else this.ReplyCount = new Long(v.toString()); return; }
     if (i == 7) { this.HeadImage = ((String)v); return; }
     if (i == 8) { this.UseSelfImage = ((String)v); return; }
     if (i == 9) { this.Status = ((String)v); return; }
     if (i == 10) { if (v == null) this.ForumScore = null; else this.ForumScore = new Long(v.toString()); return; }
     if (i == 11) { this.ForumSign = ((String)v); return; }
     if (i == 12) { this.LastLoginTime = ((Date)v); return; }
     if (i == 13) { this.LastLogoutTime = ((Date)v); return; }
     if (i == 14) { this.prop1 = ((String)v); return; }
     if (i == 15) { this.prop2 = ((String)v); return; }
     if (i == 16) { this.prop3 = ((String)v); return; }
     if (i == 17) { this.prop4 = ((String)v); return; }
     if (i == 18) { this.AddUser = ((String)v); return; }
     if (i == 19) { this.AddTime = ((Date)v); return; }
     if (i == 20) { this.ModifyUser = ((String)v); return; }
     if (i == 21) { this.ModifyTime = ((Date)v); return; }
     if (i == 22) { this.BackupNo = ((String)v); return; }
     if (i == 23) { this.BackupOperator = ((String)v); return; }
     if (i == 24) { this.BackupTime = ((Date)v); return; }
     if (i != 25) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.UserName;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.AdminID;
     if (i == 3) return this.UserGroupID;
     if (i == 4) return this.NickName;
     if (i == 5) return this.ThemeCount;
     if (i == 6) return this.ReplyCount;
     if (i == 7) return this.HeadImage;
     if (i == 8) return this.UseSelfImage;
     if (i == 9) return this.Status;
     if (i == 10) return this.ForumScore;
     if (i == 11) return this.ForumSign;
     if (i == 12) return this.LastLoginTime;
     if (i == 13) return this.LastLogoutTime;
     if (i == 14) return this.prop1;
     if (i == 15) return this.prop2;
     if (i == 16) return this.prop3;
     if (i == 17) return this.prop4;
     if (i == 18) return this.AddUser;
     if (i == 19) return this.AddTime;
     if (i == 20) return this.ModifyUser;
     if (i == 21) return this.ModifyTime;
     if (i == 22) return this.BackupNo;
     if (i == 23) return this.BackupOperator;
     if (i == 24) return this.BackupTime;
     if (i == 25) return this.BackupMemo;
     return null;
   }
 
   public String getUserName()
   {
     return this.UserName;
   }
 
   public void setUserName(String userName)
   {
     this.UserName = userName;
   }
 
   public long getSiteID()
   {
     if (this.SiteID == null) return 0L;
     return this.SiteID.longValue();
   }
 
   public void setSiteID(long siteID)
   {
     this.SiteID = new Long(siteID);
   }
 
   public void setSiteID(String siteID)
   {
     if (siteID == null) {
       this.SiteID = null;
       return;
     }
     this.SiteID = new Long(siteID);
   }
 
   public long getAdminID()
   {
     if (this.AdminID == null) return 0L;
     return this.AdminID.longValue();
   }
 
   public void setAdminID(long adminID)
   {
     this.AdminID = new Long(adminID);
   }
 
   public void setAdminID(String adminID)
   {
     if (adminID == null) {
       this.AdminID = null;
       return;
     }
     this.AdminID = new Long(adminID);
   }
 
   public long getUserGroupID()
   {
     if (this.UserGroupID == null) return 0L;
     return this.UserGroupID.longValue();
   }
 
   public void setUserGroupID(long userGroupID)
   {
     this.UserGroupID = new Long(userGroupID);
   }
 
   public void setUserGroupID(String userGroupID)
   {
     if (userGroupID == null) {
       this.UserGroupID = null;
       return;
     }
     this.UserGroupID = new Long(userGroupID);
   }
 
   public String getNickName()
   {
     return this.NickName;
   }
 
   public void setNickName(String nickName)
   {
     this.NickName = nickName;
   }
 
   public long getThemeCount()
   {
     if (this.ThemeCount == null) return 0L;
     return this.ThemeCount.longValue();
   }
 
   public void setThemeCount(long themeCount)
   {
     this.ThemeCount = new Long(themeCount);
   }
 
   public void setThemeCount(String themeCount)
   {
     if (themeCount == null) {
       this.ThemeCount = null;
       return;
     }
     this.ThemeCount = new Long(themeCount);
   }
 
   public long getReplyCount()
   {
     if (this.ReplyCount == null) return 0L;
     return this.ReplyCount.longValue();
   }
 
   public void setReplyCount(long replyCount)
   {
     this.ReplyCount = new Long(replyCount);
   }
 
   public void setReplyCount(String replyCount)
   {
     if (replyCount == null) {
       this.ReplyCount = null;
       return;
     }
     this.ReplyCount = new Long(replyCount);
   }
 
   public String getHeadImage()
   {
     return this.HeadImage;
   }
 
   public void setHeadImage(String headImage)
   {
     this.HeadImage = headImage;
   }
 
   public String getUseSelfImage()
   {
     return this.UseSelfImage;
   }
 
   public void setUseSelfImage(String useSelfImage)
   {
     this.UseSelfImage = useSelfImage;
   }
 
   public String getStatus()
   {
     return this.Status;
   }
 
   public void setStatus(String status)
   {
     this.Status = status;
   }
 
   public long getForumScore()
   {
     if (this.ForumScore == null) return 0L;
     return this.ForumScore.longValue();
   }
 
   public void setForumScore(long forumScore)
   {
     this.ForumScore = new Long(forumScore);
   }
 
   public void setForumScore(String forumScore)
   {
     if (forumScore == null) {
       this.ForumScore = null;
       return;
     }
     this.ForumScore = new Long(forumScore);
   }
 
   public String getForumSign()
   {
     return this.ForumSign;
   }
 
   public void setForumSign(String forumSign)
   {
     this.ForumSign = forumSign;
   }
 
   public Date getLastLoginTime()
   {
     return this.LastLoginTime;
   }
 
   public void setLastLoginTime(Date lastLoginTime)
   {
     this.LastLoginTime = lastLoginTime;
   }
 
   public Date getLastLogoutTime()
   {
     return this.LastLogoutTime;
   }
 
   public void setLastLogoutTime(Date lastLogoutTime)
   {
     this.LastLogoutTime = lastLogoutTime;
   }
 
   public String getProp1()
   {
     return this.prop1;
   }
 
   public void setProp1(String prop1)
   {
     this.prop1 = prop1;
   }
 
   public String getProp2()
   {
     return this.prop2;
   }
 
   public void setProp2(String prop2)
   {
     this.prop2 = prop2;
   }
 
   public String getProp3()
   {
     return this.prop3;
   }
 
   public void setProp3(String prop3)
   {
     this.prop3 = prop3;
   }
 
   public String getProp4()
   {
     return this.prop4;
   }
 
   public void setProp4(String prop4)
   {
     this.prop4 = prop4;
   }
 
   public String getAddUser()
   {
     return this.AddUser;
   }
 
   public void setAddUser(String addUser)
   {
     this.AddUser = addUser;
   }
 
   public Date getAddTime()
   {
     return this.AddTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.AddTime = addTime;
   }
 
   public String getModifyUser()
   {
     return this.ModifyUser;
   }
 
   public void setModifyUser(String modifyUser)
   {
     this.ModifyUser = modifyUser;
   }
 
   public Date getModifyTime()
   {
     return this.ModifyTime;
   }
 
   public void setModifyTime(Date modifyTime)
   {
     this.ModifyTime = modifyTime;
   }
 
   public String getBackupNo()
   {
     return this.BackupNo;
   }
 
   public void setBackupNo(String backupNo)
   {
     this.BackupNo = backupNo;
   }
 
   public String getBackupOperator()
   {
     return this.BackupOperator;
   }
 
   public void setBackupOperator(String backupOperator)
   {
     this.BackupOperator = backupOperator;
   }
 
   public Date getBackupTime()
   {
     return this.BackupTime;
   }
 
   public void setBackupTime(Date backupTime)
   {
     this.BackupTime = backupTime;
   }
 
   public String getBackupMemo()
   {
     return this.BackupMemo;
   }
 
   public void setBackupMemo(String backupMemo)
   {
     this.BackupMemo = backupMemo;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCForumMemberSchema
 * JD-Core Version:    0.5.3
 */