 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCForumAttachmentSchema extends Schema
 {
   private Long ID;
   private Long PostID;
   private Long SiteID;
   private String Name;
   private String Path;
   private String Type;
   private String Suffix;
   private Long AttSize;
   private Long DownCount;
   private String Note;
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
     new SchemaColumn("PostID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("SiteID", 7, 2, 0, 0, false, false), 
     new SchemaColumn("Name", 1, 3, 200, 0, true, false), 
     new SchemaColumn("Path", 1, 4, 200, 0, true, false), 
     new SchemaColumn("Type", 1, 5, 100, 0, true, false), 
     new SchemaColumn("Suffix", 1, 6, 50, 0, false, false), 
     new SchemaColumn("AttSize", 7, 7, 0, 0, false, false), 
     new SchemaColumn("DownCount", 7, 8, 0, 0, false, false), 
     new SchemaColumn("Note", 1, 9, 500, 0, false, false), 
     new SchemaColumn("prop1", 1, 10, 50, 0, false, false), 
     new SchemaColumn("prop2", 1, 11, 50, 0, false, false), 
     new SchemaColumn("prop3", 1, 12, 50, 0, false, false), 
     new SchemaColumn("prop4", 1, 13, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 14, 100, 0, true, false), 
     new SchemaColumn("AddTime", 0, 15, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 16, 100, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 17, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 18, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 19, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 20, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 21, 50, 0, false, false) };
   public static final String _TableCode = "BZCForumAttachment";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCForumAttachment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCForumAttachment set ID=?,PostID=?,SiteID=?,Name=?,Path=?,Type=?,Suffix=?,AttSize=?,DownCount=?,Note=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCForumAttachment  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCForumAttachment  where ID=? and BackupNo=?";
 
   public BZCForumAttachmentSchema()
   {
     this.TableCode = "BZCForumAttachment";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCForumAttachment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCForumAttachment set ID=?,PostID=?,SiteID=?,Name=?,Path=?,Type=?,Suffix=?,AttSize=?,DownCount=?,Note=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCForumAttachment  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCForumAttachment  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[22];
   }
 
   protected Schema newInstance() {
     return new BZCForumAttachmentSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCForumAttachmentSet();
   }
 
   public BZCForumAttachmentSet query() {
     return query(null, -1, -1);
   }
 
   public BZCForumAttachmentSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCForumAttachmentSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCForumAttachmentSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCForumAttachmentSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.PostID = null; else this.PostID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 3) { this.Name = ((String)v); return; }
     if (i == 4) { this.Path = ((String)v); return; }
     if (i == 5) { this.Type = ((String)v); return; }
     if (i == 6) { this.Suffix = ((String)v); return; }
     if (i == 7) { if (v == null) this.AttSize = null; else this.AttSize = new Long(v.toString()); return; }
     if (i == 8) { if (v == null) this.DownCount = null; else this.DownCount = new Long(v.toString()); return; }
     if (i == 9) { this.Note = ((String)v); return; }
     if (i == 10) { this.prop1 = ((String)v); return; }
     if (i == 11) { this.prop2 = ((String)v); return; }
     if (i == 12) { this.prop3 = ((String)v); return; }
     if (i == 13) { this.prop4 = ((String)v); return; }
     if (i == 14) { this.AddUser = ((String)v); return; }
     if (i == 15) { this.AddTime = ((Date)v); return; }
     if (i == 16) { this.ModifyUser = ((String)v); return; }
     if (i == 17) { this.ModifyTime = ((Date)v); return; }
     if (i == 18) { this.BackupNo = ((String)v); return; }
     if (i == 19) { this.BackupOperator = ((String)v); return; }
     if (i == 20) { this.BackupTime = ((Date)v); return; }
     if (i != 21) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.PostID;
     if (i == 2) return this.SiteID;
     if (i == 3) return this.Name;
     if (i == 4) return this.Path;
     if (i == 5) return this.Type;
     if (i == 6) return this.Suffix;
     if (i == 7) return this.AttSize;
     if (i == 8) return this.DownCount;
     if (i == 9) return this.Note;
     if (i == 10) return this.prop1;
     if (i == 11) return this.prop2;
     if (i == 12) return this.prop3;
     if (i == 13) return this.prop4;
     if (i == 14) return this.AddUser;
     if (i == 15) return this.AddTime;
     if (i == 16) return this.ModifyUser;
     if (i == 17) return this.ModifyTime;
     if (i == 18) return this.BackupNo;
     if (i == 19) return this.BackupOperator;
     if (i == 20) return this.BackupTime;
     if (i == 21) return this.BackupMemo;
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
 
   public long getPostID()
   {
     if (this.PostID == null) return 0L;
     return this.PostID.longValue();
   }
 
   public void setPostID(long postID)
   {
     this.PostID = new Long(postID);
   }
 
   public void setPostID(String postID)
   {
     if (postID == null) {
       this.PostID = null;
       return;
     }
     this.PostID = new Long(postID);
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
 
   public String getName()
   {
     return this.Name;
   }
 
   public void setName(String name)
   {
     this.Name = name;
   }
 
   public String getPath()
   {
     return this.Path;
   }
 
   public void setPath(String path)
   {
     this.Path = path;
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
   }
 
   public String getSuffix()
   {
     return this.Suffix;
   }
 
   public void setSuffix(String suffix)
   {
     this.Suffix = suffix;
   }
 
   public long getAttSize()
   {
     if (this.AttSize == null) return 0L;
     return this.AttSize.longValue();
   }
 
   public void setAttSize(long attSize)
   {
     this.AttSize = new Long(attSize);
   }
 
   public void setAttSize(String attSize)
   {
     if (attSize == null) {
       this.AttSize = null;
       return;
     }
     this.AttSize = new Long(attSize);
   }
 
   public long getDownCount()
   {
     if (this.DownCount == null) return 0L;
     return this.DownCount.longValue();
   }
 
   public void setDownCount(long downCount)
   {
     this.DownCount = new Long(downCount);
   }
 
   public void setDownCount(String downCount)
   {
     if (downCount == null) {
       this.DownCount = null;
       return;
     }
     this.DownCount = new Long(downCount);
   }
 
   public String getNote()
   {
     return this.Note;
   }
 
   public void setNote(String note)
   {
     this.Note = note;
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
 * Qualified Name:     com.zving.schema.BZCForumAttachmentSchema
 * JD-Core Version:    0.5.3
 */