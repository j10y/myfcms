 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCVideoRelaSchema extends Schema
 {
   private Long ID;
   private Long RelaID;
   private String RelaType;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("RelaID", 7, 1, 0, 0, true, true), 
     new SchemaColumn("RelaType", 1, 2, 20, 0, true, true), 
     new SchemaColumn("BackupNo", 1, 3, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 4, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 5, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 6, 50, 0, false, false) };
   public static final String _TableCode = "BZCVideoRela";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCVideoRela values(?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCVideoRela set ID=?,RelaID=?,RelaType=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and RelaID=? and RelaType=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCVideoRela  where ID=? and RelaID=? and RelaType=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCVideoRela  where ID=? and RelaID=? and RelaType=? and BackupNo=?";
 
   public BZCVideoRelaSchema()
   {
     this.TableCode = "BZCVideoRela";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCVideoRela values(?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCVideoRela set ID=?,RelaID=?,RelaType=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and RelaID=? and RelaType=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCVideoRela  where ID=? and RelaID=? and RelaType=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCVideoRela  where ID=? and RelaID=? and RelaType=? and BackupNo=?";
     this.HasSetFlag = new boolean[7];
   }
 
   protected Schema newInstance() {
     return new BZCVideoRelaSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCVideoRelaSet();
   }
 
   public BZCVideoRelaSet query() {
     return query(null, -1, -1);
   }
 
   public BZCVideoRelaSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCVideoRelaSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCVideoRelaSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCVideoRelaSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.RelaID = null; else this.RelaID = new Long(v.toString()); return; }
     if (i == 2) { this.RelaType = ((String)v); return; }
     if (i == 3) { this.BackupNo = ((String)v); return; }
     if (i == 4) { this.BackupOperator = ((String)v); return; }
     if (i == 5) { this.BackupTime = ((Date)v); return; }
     if (i != 6) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.RelaID;
     if (i == 2) return this.RelaType;
     if (i == 3) return this.BackupNo;
     if (i == 4) return this.BackupOperator;
     if (i == 5) return this.BackupTime;
     if (i == 6) return this.BackupMemo;
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
 
   public long getRelaID()
   {
     if (this.RelaID == null) return 0L;
     return this.RelaID.longValue();
   }
 
   public void setRelaID(long relaID)
   {
     this.RelaID = new Long(relaID);
   }
 
   public void setRelaID(String relaID)
   {
     if (relaID == null) {
       this.RelaID = null;
       return;
     }
     this.RelaID = new Long(relaID);
   }
 
   public String getRelaType()
   {
     return this.RelaType;
   }
 
   public void setRelaType(String relaType)
   {
     this.RelaType = relaType;
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
 * Qualified Name:     com.zving.schema.BZCVideoRelaSchema
 * JD-Core Version:    0.5.3
 */