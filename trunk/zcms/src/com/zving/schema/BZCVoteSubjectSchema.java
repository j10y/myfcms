 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCVoteSubjectSchema extends Schema
 {
   private Long ID;
   private Long VoteID;
   private String Type;
   private String Subject;
   private String Prop1;
   private String Prop2;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("VoteID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Type", 1, 2, 1, 0, true, false), 
     new SchemaColumn("Subject", 1, 3, 100, 0, true, false), 
     new SchemaColumn("Prop1", 1, 4, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 5, 50, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 6, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 7, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 8, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 9, 50, 0, false, false) };
   public static final String _TableCode = "BZCVoteSubject";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCVoteSubject values(?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCVoteSubject set ID=?,VoteID=?,Type=?,Subject=?,Prop1=?,Prop2=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCVoteSubject  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCVoteSubject  where ID=? and BackupNo=?";
 
   public BZCVoteSubjectSchema()
   {
     this.TableCode = "BZCVoteSubject";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCVoteSubject values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCVoteSubject set ID=?,VoteID=?,Type=?,Subject=?,Prop1=?,Prop2=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCVoteSubject  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCVoteSubject  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[10];
   }
 
   protected Schema newInstance() {
     return new BZCVoteSubjectSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCVoteSubjectSet();
   }
 
   public BZCVoteSubjectSet query() {
     return query(null, -1, -1);
   }
 
   public BZCVoteSubjectSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCVoteSubjectSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCVoteSubjectSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCVoteSubjectSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.VoteID = null; else this.VoteID = new Long(v.toString()); return; }
     if (i == 2) { this.Type = ((String)v); return; }
     if (i == 3) { this.Subject = ((String)v); return; }
     if (i == 4) { this.Prop1 = ((String)v); return; }
     if (i == 5) { this.Prop2 = ((String)v); return; }
     if (i == 6) { this.BackupNo = ((String)v); return; }
     if (i == 7) { this.BackupOperator = ((String)v); return; }
     if (i == 8) { this.BackupTime = ((Date)v); return; }
     if (i != 9) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.VoteID;
     if (i == 2) return this.Type;
     if (i == 3) return this.Subject;
     if (i == 4) return this.Prop1;
     if (i == 5) return this.Prop2;
     if (i == 6) return this.BackupNo;
     if (i == 7) return this.BackupOperator;
     if (i == 8) return this.BackupTime;
     if (i == 9) return this.BackupMemo;
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
 
   public long getVoteID()
   {
     if (this.VoteID == null) return 0L;
     return this.VoteID.longValue();
   }
 
   public void setVoteID(long voteID)
   {
     this.VoteID = new Long(voteID);
   }
 
   public void setVoteID(String voteID)
   {
     if (voteID == null) {
       this.VoteID = null;
       return;
     }
     this.VoteID = new Long(voteID);
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
   }
 
   public String getSubject()
   {
     return this.Subject;
   }
 
   public void setSubject(String subject)
   {
     this.Subject = subject;
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
 * Qualified Name:     com.zving.schema.BZCVoteSubjectSchema
 * JD-Core Version:    0.5.3
 */