 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCAnswerSchema extends Schema
 {
   private Long ID;
   private Long QuestionID;
   private String Content;
   private String IsBest;
   private String Memo;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private Date AddTime;
   private String AddUser;
   private Date ModifyTime;
   private String ModifyUser;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("QuestionID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Content", 10, 2, 0, 0, false, false), 
     new SchemaColumn("IsBest", 1, 3, 1, 0, false, false), 
     new SchemaColumn("Memo", 1, 4, 100, 0, false, false), 
     new SchemaColumn("Prop1", 1, 5, 100, 0, false, false), 
     new SchemaColumn("Prop2", 1, 6, 100, 0, false, false), 
     new SchemaColumn("Prop3", 1, 7, 100, 0, false, false), 
     new SchemaColumn("Prop4", 1, 8, 100, 0, false, false), 
     new SchemaColumn("AddTime", 0, 9, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 10, 100, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 11, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 12, 100, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 13, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 14, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 15, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 16, 50, 0, false, false) };
   public static final String _TableCode = "BZCAnswer";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCAnswer values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCAnswer set ID=?,QuestionID=?,Content=?,IsBest=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCAnswer  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCAnswer  where ID=? and BackupNo=?";
 
   public BZCAnswerSchema()
   {
     this.TableCode = "BZCAnswer";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCAnswer values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAnswer set ID=?,QuestionID=?,Content=?,IsBest=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAnswer  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAnswer  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[17];
   }
 
   protected Schema newInstance() {
     return new BZCAnswerSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCAnswerSet();
   }
 
   public BZCAnswerSet query() {
     return query(null, -1, -1);
   }
 
   public BZCAnswerSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCAnswerSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCAnswerSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCAnswerSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.QuestionID = null; else this.QuestionID = new Long(v.toString()); return; }
     if (i == 2) { this.Content = ((String)v); return; }
     if (i == 3) { this.IsBest = ((String)v); return; }
     if (i == 4) { this.Memo = ((String)v); return; }
     if (i == 5) { this.Prop1 = ((String)v); return; }
     if (i == 6) { this.Prop2 = ((String)v); return; }
     if (i == 7) { this.Prop3 = ((String)v); return; }
     if (i == 8) { this.Prop4 = ((String)v); return; }
     if (i == 9) { this.AddTime = ((Date)v); return; }
     if (i == 10) { this.AddUser = ((String)v); return; }
     if (i == 11) { this.ModifyTime = ((Date)v); return; }
     if (i == 12) { this.ModifyUser = ((String)v); return; }
     if (i == 13) { this.BackupNo = ((String)v); return; }
     if (i == 14) { this.BackupOperator = ((String)v); return; }
     if (i == 15) { this.BackupTime = ((Date)v); return; }
     if (i != 16) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.QuestionID;
     if (i == 2) return this.Content;
     if (i == 3) return this.IsBest;
     if (i == 4) return this.Memo;
     if (i == 5) return this.Prop1;
     if (i == 6) return this.Prop2;
     if (i == 7) return this.Prop3;
     if (i == 8) return this.Prop4;
     if (i == 9) return this.AddTime;
     if (i == 10) return this.AddUser;
     if (i == 11) return this.ModifyTime;
     if (i == 12) return this.ModifyUser;
     if (i == 13) return this.BackupNo;
     if (i == 14) return this.BackupOperator;
     if (i == 15) return this.BackupTime;
     if (i == 16) return this.BackupMemo;
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
 
   public long getQuestionID()
   {
     if (this.QuestionID == null) return 0L;
     return this.QuestionID.longValue();
   }
 
   public void setQuestionID(long questionID)
   {
     this.QuestionID = new Long(questionID);
   }
 
   public void setQuestionID(String questionID)
   {
     if (questionID == null) {
       this.QuestionID = null;
       return;
     }
     this.QuestionID = new Long(questionID);
   }
 
   public String getContent()
   {
     return this.Content;
   }
 
   public void setContent(String content)
   {
     this.Content = content;
   }
 
   public String getIsBest()
   {
     return this.IsBest;
   }
 
   public void setIsBest(String isBest)
   {
     this.IsBest = isBest;
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
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
 
   public Date getAddTime()
   {
     return this.AddTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.AddTime = addTime;
   }
 
   public String getAddUser()
   {
     return this.AddUser;
   }
 
   public void setAddUser(String addUser)
   {
     this.AddUser = addUser;
   }
 
   public Date getModifyTime()
   {
     return this.ModifyTime;
   }
 
   public void setModifyTime(Date modifyTime)
   {
     this.ModifyTime = modifyTime;
   }
 
   public String getModifyUser()
   {
     return this.ModifyUser;
   }
 
   public void setModifyUser(String modifyUser)
   {
     this.ModifyUser = modifyUser;
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
 * Qualified Name:     com.zving.schema.BZCAnswerSchema
 * JD-Core Version:    0.5.3
 */