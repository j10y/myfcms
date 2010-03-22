 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCForumSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private Long ParentID;
   private String Type;
   private String Name;
   private String Status;
   private String Pic;
   private String Visible;
   private String Info;
   private Integer ThemeCount;
   private String Verify;
   private String Locked;
   private String AllowTheme;
   private String EditPost;
   private String ReplyPost;
   private String Recycle;
   private String AllowHTML;
   private String AllowFace;
   private String AnonyPost;
   private String URL;
   private String Image;
   private String Password;
   private String Word;
   private Integer PostCount;
   private String ForumAdmin;
   private Integer TodayPostCount;
   private Long LastThemeID;
   private String LastPost;
   private String LastPoster;
   private Long OrderFlag;
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
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, false, false), 
     new SchemaColumn("ParentID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Type", 1, 3, 20, 0, true, false), 
     new SchemaColumn("Name", 1, 4, 100, 0, true, false), 
     new SchemaColumn("Status", 1, 5, 2, 0, true, false), 
     new SchemaColumn("Pic", 1, 6, 100, 0, false, false), 
     new SchemaColumn("Visible", 1, 7, 2, 0, false, false), 
     new SchemaColumn("Info", 1, 8, 1000, 0, false, false), 
     new SchemaColumn("ThemeCount", 8, 9, 0, 0, true, false), 
     new SchemaColumn("Verify", 1, 10, 2, 0, false, false), 
     new SchemaColumn("Locked", 1, 11, 2, 0, false, false), 
     new SchemaColumn("AllowTheme", 1, 12, 2, 0, false, false), 
     new SchemaColumn("EditPost", 1, 13, 2, 0, false, false), 
     new SchemaColumn("ReplyPost", 1, 14, 2, 0, false, false), 
     new SchemaColumn("Recycle", 1, 15, 2, 0, false, false), 
     new SchemaColumn("AllowHTML", 1, 16, 2, 0, false, false), 
     new SchemaColumn("AllowFace", 1, 17, 2, 0, false, false), 
     new SchemaColumn("AnonyPost", 1, 18, 2, 0, false, false), 
     new SchemaColumn("URL", 1, 19, 200, 0, false, false), 
     new SchemaColumn("Image", 1, 20, 200, 0, false, false), 
     new SchemaColumn("Password", 1, 21, 100, 0, false, false), 
     new SchemaColumn("Word", 1, 22, 200, 0, false, false), 
     new SchemaColumn("PostCount", 8, 23, 0, 0, true, false), 
     new SchemaColumn("ForumAdmin", 1, 24, 100, 0, false, false), 
     new SchemaColumn("TodayPostCount", 8, 25, 0, 0, false, false), 
     new SchemaColumn("LastThemeID", 7, 26, 0, 0, false, false), 
     new SchemaColumn("LastPost", 1, 27, 100, 0, false, false), 
     new SchemaColumn("LastPoster", 1, 28, 100, 0, false, false), 
     new SchemaColumn("OrderFlag", 7, 29, 0, 0, true, false), 
     new SchemaColumn("prop1", 1, 30, 50, 0, false, false), 
     new SchemaColumn("prop2", 1, 31, 50, 0, false, false), 
     new SchemaColumn("prop3", 1, 32, 50, 0, false, false), 
     new SchemaColumn("prop4", 1, 33, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 34, 100, 0, true, false), 
     new SchemaColumn("AddTime", 0, 35, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 36, 100, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 37, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 38, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 39, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 40, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 41, 50, 0, false, false) };
   public static final String _TableCode = "BZCForum";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCForum values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCForum set ID=?,SiteID=?,ParentID=?,Type=?,Name=?,Status=?,Pic=?,Visible=?,Info=?,ThemeCount=?,Verify=?,Locked=?,AllowTheme=?,EditPost=?,ReplyPost=?,Recycle=?,AllowHTML=?,AllowFace=?,AnonyPost=?,URL=?,Image=?,Password=?,Word=?,PostCount=?,ForumAdmin=?,TodayPostCount=?,LastThemeID=?,LastPost=?,LastPoster=?,OrderFlag=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCForum  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCForum  where ID=? and BackupNo=?";
 
   public BZCForumSchema()
   {
     this.TableCode = "BZCForum";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCForum values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCForum set ID=?,SiteID=?,ParentID=?,Type=?,Name=?,Status=?,Pic=?,Visible=?,Info=?,ThemeCount=?,Verify=?,Locked=?,AllowTheme=?,EditPost=?,ReplyPost=?,Recycle=?,AllowHTML=?,AllowFace=?,AnonyPost=?,URL=?,Image=?,Password=?,Word=?,PostCount=?,ForumAdmin=?,TodayPostCount=?,LastThemeID=?,LastPost=?,LastPoster=?,OrderFlag=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCForum  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCForum  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[42];
   }
 
   protected Schema newInstance() {
     return new BZCForumSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCForumSet();
   }
 
   public BZCForumSet query() {
     return query(null, -1, -1);
   }
 
   public BZCForumSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCForumSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCForumSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCForumSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.ParentID = null; else this.ParentID = new Long(v.toString()); return; }
     if (i == 3) { this.Type = ((String)v); return; }
     if (i == 4) { this.Name = ((String)v); return; }
     if (i == 5) { this.Status = ((String)v); return; }
     if (i == 6) { this.Pic = ((String)v); return; }
     if (i == 7) { this.Visible = ((String)v); return; }
     if (i == 8) { this.Info = ((String)v); return; }
     if (i == 9) { if (v == null) this.ThemeCount = null; else this.ThemeCount = new Integer(v.toString()); return; }
     if (i == 10) { this.Verify = ((String)v); return; }
     if (i == 11) { this.Locked = ((String)v); return; }
     if (i == 12) { this.AllowTheme = ((String)v); return; }
     if (i == 13) { this.EditPost = ((String)v); return; }
     if (i == 14) { this.ReplyPost = ((String)v); return; }
     if (i == 15) { this.Recycle = ((String)v); return; }
     if (i == 16) { this.AllowHTML = ((String)v); return; }
     if (i == 17) { this.AllowFace = ((String)v); return; }
     if (i == 18) { this.AnonyPost = ((String)v); return; }
     if (i == 19) { this.URL = ((String)v); return; }
     if (i == 20) { this.Image = ((String)v); return; }
     if (i == 21) { this.Password = ((String)v); return; }
     if (i == 22) { this.Word = ((String)v); return; }
     if (i == 23) { if (v == null) this.PostCount = null; else this.PostCount = new Integer(v.toString()); return; }
     if (i == 24) { this.ForumAdmin = ((String)v); return; }
     if (i == 25) { if (v == null) this.TodayPostCount = null; else this.TodayPostCount = new Integer(v.toString()); return; }
     if (i == 26) { if (v == null) this.LastThemeID = null; else this.LastThemeID = new Long(v.toString()); return; }
     if (i == 27) { this.LastPost = ((String)v); return; }
     if (i == 28) { this.LastPoster = ((String)v); return; }
     if (i == 29) { if (v == null) this.OrderFlag = null; else this.OrderFlag = new Long(v.toString()); return; }
     if (i == 30) { this.prop1 = ((String)v); return; }
     if (i == 31) { this.prop2 = ((String)v); return; }
     if (i == 32) { this.prop3 = ((String)v); return; }
     if (i == 33) { this.prop4 = ((String)v); return; }
     if (i == 34) { this.AddUser = ((String)v); return; }
     if (i == 35) { this.AddTime = ((Date)v); return; }
     if (i == 36) { this.ModifyUser = ((String)v); return; }
     if (i == 37) { this.ModifyTime = ((Date)v); return; }
     if (i == 38) { this.BackupNo = ((String)v); return; }
     if (i == 39) { this.BackupOperator = ((String)v); return; }
     if (i == 40) { this.BackupTime = ((Date)v); return; }
     if (i != 41) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.ParentID;
     if (i == 3) return this.Type;
     if (i == 4) return this.Name;
     if (i == 5) return this.Status;
     if (i == 6) return this.Pic;
     if (i == 7) return this.Visible;
     if (i == 8) return this.Info;
     if (i == 9) return this.ThemeCount;
     if (i == 10) return this.Verify;
     if (i == 11) return this.Locked;
     if (i == 12) return this.AllowTheme;
     if (i == 13) return this.EditPost;
     if (i == 14) return this.ReplyPost;
     if (i == 15) return this.Recycle;
     if (i == 16) return this.AllowHTML;
     if (i == 17) return this.AllowFace;
     if (i == 18) return this.AnonyPost;
     if (i == 19) return this.URL;
     if (i == 20) return this.Image;
     if (i == 21) return this.Password;
     if (i == 22) return this.Word;
     if (i == 23) return this.PostCount;
     if (i == 24) return this.ForumAdmin;
     if (i == 25) return this.TodayPostCount;
     if (i == 26) return this.LastThemeID;
     if (i == 27) return this.LastPost;
     if (i == 28) return this.LastPoster;
     if (i == 29) return this.OrderFlag;
     if (i == 30) return this.prop1;
     if (i == 31) return this.prop2;
     if (i == 32) return this.prop3;
     if (i == 33) return this.prop4;
     if (i == 34) return this.AddUser;
     if (i == 35) return this.AddTime;
     if (i == 36) return this.ModifyUser;
     if (i == 37) return this.ModifyTime;
     if (i == 38) return this.BackupNo;
     if (i == 39) return this.BackupOperator;
     if (i == 40) return this.BackupTime;
     if (i == 41) return this.BackupMemo;
     return null;
   }
 
   public long getID()
   {
     if (this.ID == null) return 0L;
     return this.ID.longValue();
   }
 
   public void setID(long iD)
   {
     this.ID = new Long(iD);
   }
 
   public void setID(String iD)
   {
     if (iD == null) {
       this.ID = null;
       return;
     }
     this.ID = new Long(iD);
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
 
   public long getParentID()
   {
     if (this.ParentID == null) return 0L;
     return this.ParentID.longValue();
   }
 
   public void setParentID(long parentID)
   {
     this.ParentID = new Long(parentID);
   }
 
   public void setParentID(String parentID)
   {
     if (parentID == null) {
       this.ParentID = null;
       return;
     }
     this.ParentID = new Long(parentID);
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
   }
 
   public String getName()
   {
     return this.Name;
   }
 
   public void setName(String name)
   {
     this.Name = name;
   }
 
   public String getStatus()
   {
     return this.Status;
   }
 
   public void setStatus(String status)
   {
     this.Status = status;
   }
 
   public String getPic()
   {
     return this.Pic;
   }
 
   public void setPic(String pic)
   {
     this.Pic = pic;
   }
 
   public String getVisible()
   {
     return this.Visible;
   }
 
   public void setVisible(String visible)
   {
     this.Visible = visible;
   }
 
   public String getInfo()
   {
     return this.Info;
   }
 
   public void setInfo(String info)
   {
     this.Info = info;
   }
 
   public int getThemeCount()
   {
     if (this.ThemeCount == null) return 0;
     return this.ThemeCount.intValue();
   }
 
   public void setThemeCount(int themeCount)
   {
     this.ThemeCount = new Integer(themeCount);
   }
 
   public void setThemeCount(String themeCount)
   {
     if (themeCount == null) {
       this.ThemeCount = null;
       return;
     }
     this.ThemeCount = new Integer(themeCount);
   }
 
   public String getVerify()
   {
     return this.Verify;
   }
 
   public void setVerify(String verify)
   {
     this.Verify = verify;
   }
 
   public String getLocked()
   {
     return this.Locked;
   }
 
   public void setLocked(String locked)
   {
     this.Locked = locked;
   }
 
   public String getAllowTheme()
   {
     return this.AllowTheme;
   }
 
   public void setAllowTheme(String allowTheme)
   {
     this.AllowTheme = allowTheme;
   }
 
   public String getEditPost()
   {
     return this.EditPost;
   }
 
   public void setEditPost(String editPost)
   {
     this.EditPost = editPost;
   }
 
   public String getReplyPost()
   {
     return this.ReplyPost;
   }
 
   public void setReplyPost(String replyPost)
   {
     this.ReplyPost = replyPost;
   }
 
   public String getRecycle()
   {
     return this.Recycle;
   }
 
   public void setRecycle(String recycle)
   {
     this.Recycle = recycle;
   }
 
   public String getAllowHTML()
   {
     return this.AllowHTML;
   }
 
   public void setAllowHTML(String allowHTML)
   {
     this.AllowHTML = allowHTML;
   }
 
   public String getAllowFace()
   {
     return this.AllowFace;
   }
 
   public void setAllowFace(String allowFace)
   {
     this.AllowFace = allowFace;
   }
 
   public String getAnonyPost()
   {
     return this.AnonyPost;
   }
 
   public void setAnonyPost(String anonyPost)
   {
     this.AnonyPost = anonyPost;
   }
 
   public String getURL()
   {
     return this.URL;
   }
 
   public void setURL(String uRL)
   {
     this.URL = uRL;
   }
 
   public String getImage()
   {
     return this.Image;
   }
 
   public void setImage(String image)
   {
     this.Image = image;
   }
 
   public String getPassword()
   {
     return this.Password;
   }
 
   public void setPassword(String password)
   {
     this.Password = password;
   }
 
   public String getWord()
   {
     return this.Word;
   }
 
   public void setWord(String word)
   {
     this.Word = word;
   }
 
   public int getPostCount()
   {
     if (this.PostCount == null) return 0;
     return this.PostCount.intValue();
   }
 
   public void setPostCount(int postCount)
   {
     this.PostCount = new Integer(postCount);
   }
 
   public void setPostCount(String postCount)
   {
     if (postCount == null) {
       this.PostCount = null;
       return;
     }
     this.PostCount = new Integer(postCount);
   }
 
   public String getForumAdmin()
   {
     return this.ForumAdmin;
   }
 
   public void setForumAdmin(String forumAdmin)
   {
     this.ForumAdmin = forumAdmin;
   }
 
   public int getTodayPostCount()
   {
     if (this.TodayPostCount == null) return 0;
     return this.TodayPostCount.intValue();
   }
 
   public void setTodayPostCount(int todayPostCount)
   {
     this.TodayPostCount = new Integer(todayPostCount);
   }
 
   public void setTodayPostCount(String todayPostCount)
   {
     if (todayPostCount == null) {
       this.TodayPostCount = null;
       return;
     }
     this.TodayPostCount = new Integer(todayPostCount);
   }
 
   public long getLastThemeID()
   {
     if (this.LastThemeID == null) return 0L;
     return this.LastThemeID.longValue();
   }
 
   public void setLastThemeID(long lastThemeID)
   {
     this.LastThemeID = new Long(lastThemeID);
   }
 
   public void setLastThemeID(String lastThemeID)
   {
     if (lastThemeID == null) {
       this.LastThemeID = null;
       return;
     }
     this.LastThemeID = new Long(lastThemeID);
   }
 
   public String getLastPost()
   {
     return this.LastPost;
   }
 
   public void setLastPost(String lastPost)
   {
     this.LastPost = lastPost;
   }
 
   public String getLastPoster()
   {
     return this.LastPoster;
   }
 
   public void setLastPoster(String lastPoster)
   {
     this.LastPoster = lastPoster;
   }
 
   public long getOrderFlag()
   {
     if (this.OrderFlag == null) return 0L;
     return this.OrderFlag.longValue();
   }
 
   public void setOrderFlag(long orderFlag)
   {
     this.OrderFlag = new Long(orderFlag);
   }
 
   public void setOrderFlag(String orderFlag)
   {
     if (orderFlag == null) {
       this.OrderFlag = null;
       return;
     }
     this.OrderFlag = new Long(orderFlag);
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
 * Qualified Name:     com.zving.schema.BZCForumSchema
 * JD-Core Version:    0.5.3
 */