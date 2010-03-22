 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZWWorkflowEntrySchema extends Schema
 {
   private Long ID;
   private Long WorkflowDefID;
   private Integer State;
   private String Memo;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("WorkflowDefID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("State", 8, 2, 0, 0, false, false), 
     new SchemaColumn("Memo", 1, 3, 100, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 4, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 5, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 6, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 7, 50, 0, false, false) };
   public static final String _TableCode = "BZWWorkflowEntry";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZWWorkflowEntry values(?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZWWorkflowEntry set ID=?,WorkflowDefID=?,State=?,Memo=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZWWorkflowEntry  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZWWorkflowEntry  where ID=? and BackupNo=?";
 
   public BZWWorkflowEntrySchema()
   {
     this.TableCode = "BZWWorkflowEntry";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZWWorkflowEntry values(?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZWWorkflowEntry set ID=?,WorkflowDefID=?,State=?,Memo=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZWWorkflowEntry  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZWWorkflowEntry  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[8];
   }
 
   protected Schema newInstance() {
     return new BZWWorkflowEntrySchema();
   }
 
   protected SchemaSet newSet() {
     return new BZWWorkflowEntrySet();
   }
 
   public BZWWorkflowEntrySet query() {
     return query(null, -1, -1);
   }
 
   public BZWWorkflowEntrySet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZWWorkflowEntrySet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZWWorkflowEntrySet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZWWorkflowEntrySet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.WorkflowDefID = null; else this.WorkflowDefID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.State = null; else this.State = new Integer(v.toString()); return; }
     if (i == 3) { this.Memo = ((String)v); return; }
     if (i == 4) { this.BackupNo = ((String)v); return; }
     if (i == 5) { this.BackupOperator = ((String)v); return; }
     if (i == 6) { this.BackupTime = ((Date)v); return; }
     if (i != 7) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.WorkflowDefID;
     if (i == 2) return this.State;
     if (i == 3) return this.Memo;
     if (i == 4) return this.BackupNo;
     if (i == 5) return this.BackupOperator;
     if (i == 6) return this.BackupTime;
     if (i == 7) return this.BackupMemo;
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
 
   public long getWorkflowDefID()
   {
     if (this.WorkflowDefID == null) return 0L;
     return this.WorkflowDefID.longValue();
   }
 
   public void setWorkflowDefID(long workflowDefID)
   {
     this.WorkflowDefID = new Long(workflowDefID);
   }
 
   public void setWorkflowDefID(String workflowDefID)
   {
     if (workflowDefID == null) {
       this.WorkflowDefID = null;
       return;
     }
     this.WorkflowDefID = new Long(workflowDefID);
   }
 
   public int getState()
   {
     if (this.State == null) return 0;
     return this.State.intValue();
   }
 
   public void setState(int state)
   {
     this.State = new Integer(state);
   }
 
   public void setState(String state)
   {
     if (state == null) {
       this.State = null;
       return;
     }
     this.State = new Integer(state);
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
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
 * Qualified Name:     com.zving.schema.BZWWorkflowEntrySchema
 * JD-Core Version:    0.5.3
 */