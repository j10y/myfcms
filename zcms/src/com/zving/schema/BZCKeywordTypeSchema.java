 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCKeywordTypeSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private String TypeName;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
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
     new SchemaColumn("SiteID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("TypeName", 1, 2, 100, 0, true, false), 
     new SchemaColumn("Prop1", 1, 3, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 4, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 5, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 6, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 7, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 8, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 9, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 10, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 11, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 12, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 13, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 14, 50, 0, false, false) };
   public static final String _TableCode = "BZCKeywordType";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCKeywordType values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCKeywordType set ID=?,SiteID=?,TypeName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCKeywordType  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCKeywordType  where ID=? and BackupNo=?";
 
   public BZCKeywordTypeSchema()
   {
     this.TableCode = "BZCKeywordType";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCKeywordType values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCKeywordType set ID=?,SiteID=?,TypeName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCKeywordType  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCKeywordType  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[15];
   }
 
   protected Schema newInstance() {
     return new BZCKeywordTypeSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCKeywordTypeSet();
   }
 
   public BZCKeywordTypeSet query() {
     return query(null, -1, -1);
   }
 
   public BZCKeywordTypeSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCKeywordTypeSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCKeywordTypeSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCKeywordTypeSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { this.TypeName = ((String)v); return; }
     if (i == 3) { this.Prop1 = ((String)v); return; }
     if (i == 4) { this.Prop2 = ((String)v); return; }
     if (i == 5) { this.Prop3 = ((String)v); return; }
     if (i == 6) { this.Prop4 = ((String)v); return; }
     if (i == 7) { this.AddUser = ((String)v); return; }
     if (i == 8) { this.AddTime = ((Date)v); return; }
     if (i == 9) { this.ModifyUser = ((String)v); return; }
     if (i == 10) { this.ModifyTime = ((Date)v); return; }
     if (i == 11) { this.BackupNo = ((String)v); return; }
     if (i == 12) { this.BackupOperator = ((String)v); return; }
     if (i == 13) { this.BackupTime = ((Date)v); return; }
     if (i != 14) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.TypeName;
     if (i == 3) return this.Prop1;
     if (i == 4) return this.Prop2;
     if (i == 5) return this.Prop3;
     if (i == 6) return this.Prop4;
     if (i == 7) return this.AddUser;
     if (i == 8) return this.AddTime;
     if (i == 9) return this.ModifyUser;
     if (i == 10) return this.ModifyTime;
     if (i == 11) return this.BackupNo;
     if (i == 12) return this.BackupOperator;
     if (i == 13) return this.BackupTime;
     if (i == 14) return this.BackupMemo;
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
 
   public String getTypeName()
   {
     return this.TypeName;
   }
 
   public void setTypeName(String typeName)
   {
     this.TypeName = typeName;
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
 
   public String getProp3()
   {
     return this.Prop3;
   }
 
   public void setProp3(String prop3)
   {
     this.Prop3 = prop3;
   }
 
   public String getProp4()
   {
     return this.Prop4;
   }
 
   public void setProp4(String prop4)
   {
     this.Prop4 = prop4;
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
 * Qualified Name:     com.zving.schema.BZCKeywordTypeSchema
 * JD-Core Version:    0.5.3
 */