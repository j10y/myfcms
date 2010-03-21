 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZWCurrentStepPrevSchema extends Schema
 {
   private Long ID;
   private Long PreviousID;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("PreviousID", 7, 1, 0, 0, true, true), 
     new SchemaColumn("BackupNo", 1, 2, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 3, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 4, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 5, 50, 0, false, false) };
   public static final String _TableCode = "BZWCurrentStepPrev";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZWCurrentStepPrev values(?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZWCurrentStepPrev set ID=?,PreviousID=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and PreviousID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZWCurrentStepPrev  where ID=? and PreviousID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZWCurrentStepPrev  where ID=? and PreviousID=? and BackupNo=?";
 
   public BZWCurrentStepPrevSchema()
   {
     this.TableCode = "BZWCurrentStepPrev";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZWCurrentStepPrev values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZWCurrentStepPrev set ID=?,PreviousID=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and PreviousID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZWCurrentStepPrev  where ID=? and PreviousID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZWCurrentStepPrev  where ID=? and PreviousID=? and BackupNo=?";
     this.HasSetFlag = new boolean[6];
   }
 
   protected Schema newInstance() {
     return new BZWCurrentStepPrevSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZWCurrentStepPrevSet();
   }
 
   public BZWCurrentStepPrevSet query() {
     return query(null, -1, -1);
   }
 
   public BZWCurrentStepPrevSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZWCurrentStepPrevSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZWCurrentStepPrevSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZWCurrentStepPrevSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.PreviousID = null; else this.PreviousID = new Long(v.toString()); return; }
     if (i == 2) { this.BackupNo = ((String)v); return; }
     if (i == 3) { this.BackupOperator = ((String)v); return; }
     if (i == 4) { this.BackupTime = ((Date)v); return; }
     if (i != 5) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.PreviousID;
     if (i == 2) return this.BackupNo;
     if (i == 3) return this.BackupOperator;
     if (i == 4) return this.BackupTime;
     if (i == 5) return this.BackupMemo;
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
 
   public long getPreviousID()
   {
     if (this.PreviousID == null) return 0L;
     return this.PreviousID.longValue();
   }
 
   public void setPreviousID(long previousID)
   {
     this.PreviousID = new Long(previousID);
   }
 
   public void setPreviousID(String previousID)
   {
     if (previousID == null) {
       this.PreviousID = null;
       return;
     }
     this.PreviousID = new Long(previousID);
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
 * Qualified Name:     com.zving.schema.BZWCurrentStepPrevSchema
 * JD-Core Version:    0.5.3
 */