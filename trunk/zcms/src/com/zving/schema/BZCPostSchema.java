 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCPostSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private Long ForumID;
   private Long ThemeID;
   private String First;
   private String Subject;
   private String Message;
   private String IP;
   private String Status;
   private String IsHidden;
   private String Invisible;
   private String VerifyFlag;
   private Long Layer;
   private String ApplyDel;
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
     new SchemaColumn("ForumID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("ThemeID", 7, 3, 0, 0, true, false), 
     new SchemaColumn("First", 1, 4, 2, 0, false, false), 
     new SchemaColumn("Subject", 1, 5, 100, 0, false, false), 
     new SchemaColumn("Message", 10, 6, 0, 0, false, false), 
     new SchemaColumn("IP", 1, 7, 20, 0, false, false), 
     new SchemaColumn("Status", 1, 8, 2, 0, false, false), 
     new SchemaColumn("IsHidden", 1, 9, 2, 0, false, false), 
     new SchemaColumn("Invisible", 1, 10, 2, 0, false, false), 
     new SchemaColumn("VerifyFlag", 1, 11, 2, 0, false, false), 
     new SchemaColumn("Layer", 7, 12, 0, 0, false, false), 
     new SchemaColumn("ApplyDel", 1, 13, 2, 0, false, false), 
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
   public static final String _TableCode = "BZCPost";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCPost values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCPost set ID=?,SiteID=?,ForumID=?,ThemeID=?,First=?,Subject=?,Message=?,IP=?,Status=?,IsHidden=?,Invisible=?,VerifyFlag=?,Layer=?,ApplyDel=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCPost  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCPost  where ID=? and BackupNo=?";
 
   public BZCPostSchema()
   {
     this.TableCode = "BZCPost";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCPost values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCPost set ID=?,SiteID=?,ForumID=?,ThemeID=?,First=?,Subject=?,Message=?,IP=?,Status=?,IsHidden=?,Invisible=?,VerifyFlag=?,Layer=?,ApplyDel=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCPost  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCPost  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[26];
   }
 
   protected Schema newInstance() {
     return new BZCPostSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCPostSet();
   }
 
   public BZCPostSet query() {
     return query(null, -1, -1);
   }
 
   public BZCPostSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCPostSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCPostSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCPostSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.ForumID = null; else this.ForumID = new Long(v.toString()); return; }
     if (i == 3) { if (v == null) this.ThemeID = null; else this.ThemeID = new Long(v.toString()); return; }
     if (i == 4) { this.First = ((String)v); return; }
     if (i == 5) { this.Subject = ((String)v); return; }
     if (i == 6) { this.Message = ((String)v); return; }
     if (i == 7) { this.IP = ((String)v); return; }
     if (i == 8) { this.Status = ((String)v); return; }
     if (i == 9) { this.IsHidden = ((String)v); return; }
     if (i == 10) { this.Invisible = ((String)v); return; }
     if (i == 11) { this.VerifyFlag = ((String)v); return; }
     if (i == 12) { if (v == null) this.Layer = null; else this.Layer = new Long(v.toString()); return; }
     if (i == 13) { this.ApplyDel = ((String)v); return; }
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
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.ForumID;
     if (i == 3) return this.ThemeID;
     if (i == 4) return this.First;
     if (i == 5) return this.Subject;
     if (i == 6) return this.Message;
     if (i == 7) return this.IP;
     if (i == 8) return this.Status;
     if (i == 9) return this.IsHidden;
     if (i == 10) return this.Invisible;
     if (i == 11) return this.VerifyFlag;
     if (i == 12) return this.Layer;
     if (i == 13) return this.ApplyDel;
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
 
   public long getForumID()
   {
     if (this.ForumID == null) return 0L;
     return this.ForumID.longValue();
   }
 
   public void setForumID(long forumID)
   {
     this.ForumID = new Long(forumID);
   }
 
   public void setForumID(String forumID)
   {
     if (forumID == null) {
       this.ForumID = null;
       return;
     }
     this.ForumID = new Long(forumID);
   }
 
   public long getThemeID()
   {
     if (this.ThemeID == null) return 0L;
     return this.ThemeID.longValue();
   }
 
   public void setThemeID(long themeID)
   {
     this.ThemeID = new Long(themeID);
   }
 
   public void setThemeID(String themeID)
   {
     if (themeID == null) {
       this.ThemeID = null;
       return;
     }
     this.ThemeID = new Long(themeID);
   }
 
   public String getFirst()
   {
     return this.First;
   }
 
   public void setFirst(String first)
   {
     this.First = first;
   }
 
   public String getSubject()
   {
     return this.Subject;
   }
 
   public void setSubject(String subject)
   {
     this.Subject = subject;
   }
 
   public String getMessage()
   {
     return this.Message;
   }
 
   public void setMessage(String message)
   {
     this.Message = message;
   }
 
   public String getIP()
   {
     return this.IP;
   }
 
   public void setIP(String iP)
   {
     this.IP = iP;
   }
 
   public String getStatus()
   {
     return this.Status;
   }
 
   public void setStatus(String status)
   {
     this.Status = status;
   }
 
   public String getIsHidden()
   {
     return this.IsHidden;
   }
 
   public void setIsHidden(String isHidden)
   {
     this.IsHidden = isHidden;
   }
 
   public String getInvisible()
   {
     return this.Invisible;
   }
 
   public void setInvisible(String invisible)
   {
     this.Invisible = invisible;
   }
 
   public String getVerifyFlag()
   {
     return this.VerifyFlag;
   }
 
   public void setVerifyFlag(String verifyFlag)
   {
     this.VerifyFlag = verifyFlag;
   }
 
   public long getLayer()
   {
     if (this.Layer == null) return 0L;
     return this.Layer.longValue();
   }
 
   public void setLayer(long layer)
   {
     this.Layer = new Long(layer);
   }
 
   public void setLayer(String layer)
   {
     if (layer == null) {
       this.Layer = null;
       return;
     }
     this.Layer = new Long(layer);
   }
 
   public String getApplyDel()
   {
     return this.ApplyDel;
   }
 
   public void setApplyDel(String applyDel)
   {
     this.ApplyDel = applyDel;
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
 * Qualified Name:     com.zving.schema.BZCPostSchema
 * JD-Core Version:    0.5.3
 */