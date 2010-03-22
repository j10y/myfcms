 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCQuestionSchema extends Schema
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
     new SchemaColumn("ModifyUser", 1, 16, 100, 0, false, false) };
   public static final String _TableCode = "ZCQuestion";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCQuestion values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCQuestion set ID=?,QuestionInnerCode=?,Title=?,Content=?,ReplyCount=?,Status=?,IsCommend=?,EndTime=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCQuestion  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCQuestion  where ID=?";
 
   public ZCQuestionSchema()
   {
     this.TableCode = "ZCQuestion";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCQuestion values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCQuestion set ID=?,QuestionInnerCode=?,Title=?,Content=?,ReplyCount=?,Status=?,IsCommend=?,EndTime=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
     this.DeleteSQL = "delete from ZCQuestion  where ID=?";
     this.FillAllSQL = "select * from ZCQuestion  where ID=?";
     this.HasSetFlag = new boolean[17];
   }
 
   protected Schema newInstance() {
     return new ZCQuestionSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCQuestionSet();
   }
 
   public ZCQuestionSet query() {
     return query(null, -1, -1);
   }
 
   public ZCQuestionSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCQuestionSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCQuestionSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCQuestionSet)querySet(qb, pageSize, pageIndex));
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
     if (i != 16) return; this.ModifyUser = ((String)v); return;
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
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCQuestionSchema
 * JD-Core Version:    0.5.3
 */