 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCQuestionSchema extends Schema
 {
   private Long ID;
   private String QuestionInnerCode;
   private String Title;
   private String Content;
   private Integer ReplyCount;
   private String Status;
   private String IsCommend;
   private Date EndTime;
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
     new SchemaColumn("QuestionInnerCode", 1, 1, 100, 0, false, false), 
     new SchemaColumn("Title", 1, 2, 250, 0, false, false), 
     new SchemaColumn("Content", 10, 3, 0, 0, false, false), 
     new SchemaColumn("ReplyCount", 8, 4, 11, 0, false, false), 
     new SchemaColumn("Status", 1, 5, 1, 0, false, false), 
     new SchemaColumn("IsCommend", 1, 6, 1, 0, false, false), 
     new SchemaColumn("EndTime", 0, 7, 0, 0, false, false), 
     new SchemaColumn("Memo", 1, 8, 100, 0, false, false), 
     new SchemaColumn("Prop1", 1, 9, 100, 0, false, false), 
     new SchemaColumn("Prop2", 1, 10, 100, 0, false, false), 
     new SchemaColumn("Prop3", 1, 11, 100, 0, false, false), 
     new SchemaColumn("Prop4", 1, 12, 100, 0, false, false), 
     new SchemaColumn("AddTime", 0, 13, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 14, 100, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 15, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 16, 100, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 17, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 18, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 19, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 20, 50, 0, false, false) };
   public static final String _TableCode = "BZCQuestion";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCQuestion values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCQuestion set ID=?,QuestionInnerCode=?,Title=?,Content=?,ReplyCount=?,Status=?,IsCommend=?,EndTime=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCQuestion  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCQuestion  where ID=? and BackupNo=?";
 
   public BZCQuestionSchema()
   {
     this.TableCode = "BZCQuestion";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCQuestion values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCQuestion set ID=?,QuestionInnerCode=?,Title=?,Content=?,ReplyCount=?,Status=?,IsCommend=?,EndTime=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCQuestion  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCQuestion  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[21];
   }
 
   protected Schema newInstance() {
     return new BZCQuestionSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCQuestionSet();
   }
 
   public BZCQuestionSet query() {
     return query(null, -1, -1);
   }
 
   public BZCQuestionSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCQuestionSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCQuestionSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCQuestionSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.QuestionInnerCode = ((String)v); return; }
     if (i == 2) { this.Title = ((String)v); return; }
     if (i == 3) { this.Content = ((String)v); return; }
     if (i == 4) { if (v == null) this.ReplyCount = null; else this.ReplyCount = new Integer(v.toString()); return; }
     if (i == 5) { this.Status = ((String)v); return; }
     if (i == 6) { this.IsCommend = ((String)v); return; }
     if (i == 7) { this.EndTime = ((Date)v); return; }
     if (i == 8) { this.Memo = ((String)v); return; }
     if (i == 9) { this.Prop1 = ((String)v); return; }
     if (i == 10) { this.Prop2 = ((String)v); return; }
     if (i == 11) { this.Prop3 = ((String)v); return; }
     if (i == 12) { this.Prop4 = ((String)v); return; }
     if (i == 13) { this.AddTime = ((Date)v); return; }
     if (i == 14) { this.AddUser = ((String)v); return; }
     if (i == 15) { this.ModifyTime = ((Date)v); return; }
     if (i == 16) { this.ModifyUser = ((String)v); return; }
     if (i == 17) { this.BackupNo = ((String)v); return; }
     if (i == 18) { this.BackupOperator = ((String)v); return; }
     if (i == 19) { this.BackupTime = ((Date)v); return; }
     if (i != 20) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.QuestionInnerCode;
     if (i == 2) return this.Title;
     if (i == 3) return this.Content;
     if (i == 4) return this.ReplyCount;
     if (i == 5) return this.Status;
     if (i == 6) return this.IsCommend;
     if (i == 7) return this.EndTime;
     if (i == 8) return this.Memo;
     if (i == 9) return this.Prop1;
     if (i == 10) return this.Prop2;
     if (i == 11) return this.Prop3;
     if (i == 12) return this.Prop4;
     if (i == 13) return this.AddTime;
     if (i == 14) return this.AddUser;
     if (i == 15) return this.ModifyTime;
     if (i == 16) return this.ModifyUser;
     if (i == 17) return this.BackupNo;
     if (i == 18) return this.BackupOperator;
     if (i == 19) return this.BackupTime;
     if (i == 20) return this.BackupMemo;
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
 
   public String getQuestionInnerCode()
   {
     return this.QuestionInnerCode;
   }
 
   public void setQuestionInnerCode(String questionInnerCode)
   {
     this.QuestionInnerCode = questionInnerCode;
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
 
   public int getReplyCount()
   {
     if (this.ReplyCount == null) return 0;
     return this.ReplyCount.intValue();
   }
 
   public void setReplyCount(int replyCount)
   {
     this.ReplyCount = new Integer(replyCount);
   }
 
   public void setReplyCount(String replyCount)
   {
     if (replyCount == null) {
       this.ReplyCount = null;
       return;
     }
     this.ReplyCount = new Integer(replyCount);
   }
 
   public String getStatus()
   {
     return this.Status;
   }
 
   public void setStatus(String status)
   {
     this.Status = status;
   }
 
   public String getIsCommend()
   {
     return this.IsCommend;
   }
 
   public void setIsCommend(String isCommend)
   {
     this.IsCommend = isCommend;
   }
 
   public Date getEndTime()
   {
     return this.EndTime;
   }
 
   public void setEndTime(Date endTime)
   {
     this.EndTime = endTime;
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
 * Qualified Name:     com.zving.schema.BZCQuestionSchema
 * JD-Core Version:    0.5.3
 */