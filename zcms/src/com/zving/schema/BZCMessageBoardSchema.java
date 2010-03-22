 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCMessageBoardSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private String Type;
   private String Title;
   private String Content;
   private String PublishFlag;
   private String ReplyFlag;
   private String ReplyContent;
   private String Email;
   private String Tel;
   private String Mobile;
   private String Address;
   private String AttachPath;
   private String Prop1;
   private String Prop2;
   private String UserName;
   private String AddUser;
   private String AddUserIP;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Type", 1, 2, 20, 0, true, false), 
     new SchemaColumn("Title", 1, 3, 100, 0, true, false), 
     new SchemaColumn("Content", 10, 4, 0, 0, true, false), 
     new SchemaColumn("PublishFlag", 1, 5, 1, 0, true, false), 
     new SchemaColumn("ReplyFlag", 1, 6, 1, 0, false, false), 
     new SchemaColumn("ReplyContent", 1, 7, 4000, 0, false, false), 
     new SchemaColumn("Email", 1, 8, 100, 0, false, false), 
     new SchemaColumn("Tel", 1, 9, 20, 0, false, false), 
     new SchemaColumn("Mobile", 1, 10, 20, 0, false, false), 
     new SchemaColumn("Address", 1, 11, 100, 0, false, false), 
     new SchemaColumn("AttachPath", 1, 12, 100, 0, false, false), 
     new SchemaColumn("Prop1", 1, 13, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 14, 50, 0, false, false), 
     new SchemaColumn("UserName", 1, 15, 200, 0, false, false), 
     new SchemaColumn("AddUser", 1, 16, 200, 0, true, false), 
     new SchemaColumn("AddUserIP", 1, 17, 50, 0, true, false), 
     new SchemaColumn("AddTime", 0, 18, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 19, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 20, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 21, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 22, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 23, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 24, 50, 0, false, false) };
   public static final String _TableCode = "BZCMessageBoard";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCMessageBoard values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCMessageBoard set ID=?,SiteID=?,Type=?,Title=?,Content=?,PublishFlag=?,ReplyFlag=?,ReplyContent=?,Email=?,Tel=?,Mobile=?,Address=?,AttachPath=?,Prop1=?,Prop2=?,UserName=?,AddUser=?,AddUserIP=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCMessageBoard  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCMessageBoard  where ID=? and BackupNo=?";
 
   public BZCMessageBoardSchema()
   {
     this.TableCode = "BZCMessageBoard";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCMessageBoard values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCMessageBoard set ID=?,SiteID=?,Type=?,Title=?,Content=?,PublishFlag=?,ReplyFlag=?,ReplyContent=?,Email=?,Tel=?,Mobile=?,Address=?,AttachPath=?,Prop1=?,Prop2=?,UserName=?,AddUser=?,AddUserIP=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCMessageBoard  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCMessageBoard  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[25];
   }
 
   protected Schema newInstance() {
     return new BZCMessageBoardSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCMessageBoardSet();
   }
 
   public BZCMessageBoardSet query() {
     return query(null, -1, -1);
   }
 
   public BZCMessageBoardSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCMessageBoardSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCMessageBoardSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCMessageBoardSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { this.Type = ((String)v); return; }
     if (i == 3) { this.Title = ((String)v); return; }
     if (i == 4) { this.Content = ((String)v); return; }
     if (i == 5) { this.PublishFlag = ((String)v); return; }
     if (i == 6) { this.ReplyFlag = ((String)v); return; }
     if (i == 7) { this.ReplyContent = ((String)v); return; }
     if (i == 8) { this.Email = ((String)v); return; }
     if (i == 9) { this.Tel = ((String)v); return; }
     if (i == 10) { this.Mobile = ((String)v); return; }
     if (i == 11) { this.Address = ((String)v); return; }
     if (i == 12) { this.AttachPath = ((String)v); return; }
     if (i == 13) { this.Prop1 = ((String)v); return; }
     if (i == 14) { this.Prop2 = ((String)v); return; }
     if (i == 15) { this.UserName = ((String)v); return; }
     if (i == 16) { this.AddUser = ((String)v); return; }
     if (i == 17) { this.AddUserIP = ((String)v); return; }
     if (i == 18) { this.AddTime = ((Date)v); return; }
     if (i == 19) { this.ModifyUser = ((String)v); return; }
     if (i == 20) { this.ModifyTime = ((Date)v); return; }
     if (i == 21) { this.BackupNo = ((String)v); return; }
     if (i == 22) { this.BackupOperator = ((String)v); return; }
     if (i == 23) { this.BackupTime = ((Date)v); return; }
     if (i != 24) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.Type;
     if (i == 3) return this.Title;
     if (i == 4) return this.Content;
     if (i == 5) return this.PublishFlag;
     if (i == 6) return this.ReplyFlag;
     if (i == 7) return this.ReplyContent;
     if (i == 8) return this.Email;
     if (i == 9) return this.Tel;
     if (i == 10) return this.Mobile;
     if (i == 11) return this.Address;
     if (i == 12) return this.AttachPath;
     if (i == 13) return this.Prop1;
     if (i == 14) return this.Prop2;
     if (i == 15) return this.UserName;
     if (i == 16) return this.AddUser;
     if (i == 17) return this.AddUserIP;
     if (i == 18) return this.AddTime;
     if (i == 19) return this.ModifyUser;
     if (i == 20) return this.ModifyTime;
     if (i == 21) return this.BackupNo;
     if (i == 22) return this.BackupOperator;
     if (i == 23) return this.BackupTime;
     if (i == 24) return this.BackupMemo;
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
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
   }
 
   public String getTitle()
   {
     return this.Title;
   }
 
   public void setTitle(String title)
   {
     this.Title = title;
   }
 
   public String getContent()
   {
     return this.Content;
   }
 
   public void setContent(String content)
   {
     this.Content = content;
   }
 
   public String getPublishFlag()
   {
     return this.PublishFlag;
   }
 
   public void setPublishFlag(String publishFlag)
   {
     this.PublishFlag = publishFlag;
   }
 
   public String getReplyFlag()
   {
     return this.ReplyFlag;
   }
 
   public void setReplyFlag(String replyFlag)
   {
     this.ReplyFlag = replyFlag;
   }
 
   public String getReplyContent()
   {
     return this.ReplyContent;
   }
 
   public void setReplyContent(String replyContent)
   {
     this.ReplyContent = replyContent;
   }
 
   public String getEmail()
   {
     return this.Email;
   }
 
   public void setEmail(String email)
   {
     this.Email = email;
   }
 
   public String getTel()
   {
     return this.Tel;
   }
 
   public void setTel(String tel)
   {
     this.Tel = tel;
   }
 
   public String getMobile()
   {
     return this.Mobile;
   }
 
   public void setMobile(String mobile)
   {
     this.Mobile = mobile;
   }
 
   public String getAddress()
   {
     return this.Address;
   }
 
   public void setAddress(String address)
   {
     this.Address = address;
   }
 
   public String getAttachPath()
   {
     return this.AttachPath;
   }
 
   public void setAttachPath(String attachPath)
   {
     this.AttachPath = attachPath;
   }
 
   public String getProp1()
   {
     return this.Prop1;
   }
 
   public void setProp1(String prop1)
   {
     this.Prop1 = prop1;
   }
 
   public String getProp2()
   {
     return this.Prop2;
   }
 
   public void setProp2(String prop2)
   {
     this.Prop2 = prop2;
   }
 
   public String getUserName()
   {
     return this.UserName;
   }
 
   public void setUserName(String userName)
   {
     this.UserName = userName;
   }
 
   public String getAddUser()
   {
     return this.AddUser;
   }
 
   public void setAddUser(String addUser)
   {
     this.AddUser = addUser;
   }
 
   public String getAddUserIP()
   {
     return this.AddUserIP;
   }
 
   public void setAddUserIP(String addUserIP)
   {
     this.AddUserIP = addUserIP;
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
 * Qualified Name:     com.zving.schema.BZCMessageBoardSchema
 * JD-Core Version:    0.5.3
 */