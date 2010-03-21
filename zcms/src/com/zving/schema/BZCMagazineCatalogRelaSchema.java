 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCMagazineCatalogRelaSchema extends Schema
 {
   private Long MagazineID;
   private Long CatalogID;
   private Long IssueID;
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
     new SchemaColumn("MagazineID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("CatalogID", 7, 1, 0, 0, true, true), 
     new SchemaColumn("IssueID", 7, 2, 0, 0, false, false), 
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
   public static final String _TableCode = "BZCMagazineCatalogRela";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCMagazineCatalogRela values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCMagazineCatalogRela set MagazineID=?,CatalogID=?,IssueID=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where MagazineID=? and CatalogID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCMagazineCatalogRela  where MagazineID=? and CatalogID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCMagazineCatalogRela  where MagazineID=? and CatalogID=? and BackupNo=?";
 
   public BZCMagazineCatalogRelaSchema()
   {
     this.TableCode = "BZCMagazineCatalogRela";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCMagazineCatalogRela values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCMagazineCatalogRela set MagazineID=?,CatalogID=?,IssueID=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where MagazineID=? and CatalogID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCMagazineCatalogRela  where MagazineID=? and CatalogID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCMagazineCatalogRela  where MagazineID=? and CatalogID=? and BackupNo=?";
     this.HasSetFlag = new boolean[15];
   }
 
   protected Schema newInstance() {
     return new BZCMagazineCatalogRelaSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCMagazineCatalogRelaSet();
   }
 
   public BZCMagazineCatalogRelaSet query() {
     return query(null, -1, -1);
   }
 
   public BZCMagazineCatalogRelaSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCMagazineCatalogRelaSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCMagazineCatalogRelaSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCMagazineCatalogRelaSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.MagazineID = null; else this.MagazineID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.CatalogID = null; else this.CatalogID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.IssueID = null; else this.IssueID = new Long(v.toString()); return; }
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
     if (i == 0) return this.MagazineID;
     if (i == 1) return this.CatalogID;
     if (i == 2) return this.IssueID;
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
 
   public long getMagazineID()
   {
     if (this.MagazineID == null) return 0L;
     return this.MagazineID.longValue();
   }
 
   public void setMagazineID(long magazineID)
   {
     this.MagazineID = new Long(magazineID);
   }
 
   public void setMagazineID(String magazineID)
   {
     if (magazineID == null) {
       this.MagazineID = null;
       return;
     }
     this.MagazineID = new Long(magazineID);
   }
 
   public long getCatalogID()
   {
     if (this.CatalogID == null) return 0L;
     return this.CatalogID.longValue();
   }
 
   public void setCatalogID(long catalogID)
   {
     this.CatalogID = new Long(catalogID);
   }
 
   public void setCatalogID(String catalogID)
   {
     if (catalogID == null) {
       this.CatalogID = null;
       return;
     }
     this.CatalogID = new Long(catalogID);
   }
 
   public long getIssueID()
   {
     if (this.IssueID == null) return 0L;
     return this.IssueID.longValue();
   }
 
   public void setIssueID(long issueID)
   {
     this.IssueID = new Long(issueID);
   }
 
   public void setIssueID(String issueID)
   {
     if (issueID == null) {
       this.IssueID = null;
       return;
     }
     this.IssueID = new Long(issueID);
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
 * Qualified Name:     com.zving.schema.BZCMagazineCatalogRelaSchema
 * JD-Core Version:    0.5.3
 */