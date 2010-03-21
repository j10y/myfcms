 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCAnswerCommentSchema extends Schema
 {
   private Long ID;
   private Long QuestionID;
   private String Content;
   private String Memo;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private Date AddTime;
   private String AddUser;
   private Date ModifyTime;
   private String ModifyUser;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("QuestionID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Content", 10, 2, 0, 0, false, false), 
     new SchemaColumn("Memo", 1, 3, 100, 0, false, false), 
     new SchemaColumn("Prop1", 1, 4, 100, 0, false, false), 
     new SchemaColumn("Prop2", 1, 5, 100, 0, false, false), 
     new SchemaColumn("Prop3", 1, 6, 100, 0, false, false), 
     new SchemaColumn("Prop4", 1, 7, 100, 0, false, false), 
     new SchemaColumn("AddTime", 0, 8, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 9, 100, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 10, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 11, 100, 0, false, false) };
   public static final String _TableCode = "ZCAnswerComment";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCAnswerComment values(?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCAnswerComment set ID=?,QuestionID=?,Content=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCAnswerComment  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCAnswerComment  where ID=?";
 
   public ZCAnswerCommentSchema()
   {
     this.TableCode = "ZCAnswerComment";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCAnswerComment values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAnswerComment set ID=?,QuestionID=?,Content=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
     this.DeleteSQL = "delete from ZCAnswerComment  where ID=?";
     this.FillAllSQL = "select * from ZCAnswerComment  where ID=?";
     this.HasSetFlag = new boolean[12];
   }
 
   protected Schema newInstance() {
     return new ZCAnswerCommentSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCAnswerCommentSet();
   }
 
   public ZCAnswerCommentSet query() {
     return query(null, -1, -1);
   }
 
   public ZCAnswerCommentSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCAnswerCommentSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCAnswerCommentSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCAnswerCommentSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.QuestionID = null; else this.QuestionID = new Long(v.toString()); return; }
     if (i == 2) { this.Content = ((String)v); return; }
     if (i == 3) { this.Memo = ((String)v); return; }
     if (i == 4) { this.Prop1 = ((String)v); return; }
     if (i == 5) { this.Prop2 = ((String)v); return; }
     if (i == 6) { this.Prop3 = ((String)v); return; }
     if (i == 7) { this.Prop4 = ((String)v); return; }
     if (i == 8) { this.AddTime = ((Date)v); return; }
     if (i == 9) { this.AddUser = ((String)v); return; }
     if (i == 10) { this.ModifyTime = ((Date)v); return; }
     if (i != 11) return; this.ModifyUser = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.QuestionID;
     if (i == 2) return this.Content;
     if (i == 3) return this.Memo;
     if (i == 4) return this.Prop1;
     if (i == 5) return this.Prop2;
     if (i == 6) return this.Prop3;
     if (i == 7) return this.Prop4;
     if (i == 8) return this.AddTime;
     if (i == 9) return this.AddUser;
     if (i == 10) return this.ModifyTime;
     if (i == 11) return this.ModifyUser;
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
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCAnswerCommentSchema
 * JD-Core Version:    0.5.3
 */