 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCMessageSchema extends Schema
 {
   private Long ID;
   private String FromUser;
   private String ToUser;
   private String Box;
   private Long ReadFlag;
   private String Subject;
   private String Content;
   private Date AddTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("FromUser", 1, 1, 50, 0, false, false), 
     new SchemaColumn("ToUser", 1, 2, 50, 0, false, false), 
     new SchemaColumn("Box", 1, 3, 10, 0, false, false), 
     new SchemaColumn("ReadFlag", 7, 4, 0, 0, false, false), 
     new SchemaColumn("Subject", 1, 5, 100, 0, false, false), 
     new SchemaColumn("Content", 10, 6, 0, 0, false, false), 
     new SchemaColumn("AddTime", 0, 7, 0, 0, false, false) };
   public static final String _TableCode = "ZCMessage";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCMessage values(?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCMessage set ID=?,FromUser=?,ToUser=?,Box=?,ReadFlag=?,Subject=?,Content=?,AddTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCMessage  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCMessage  where ID=?";
 
   public ZCMessageSchema()
   {
     this.TableCode = "ZCMessage";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCMessage values(?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCMessage set ID=?,FromUser=?,ToUser=?,Box=?,ReadFlag=?,Subject=?,Content=?,AddTime=? where ID=?";
     this.DeleteSQL = "delete from ZCMessage  where ID=?";
     this.FillAllSQL = "select * from ZCMessage  where ID=?";
     this.HasSetFlag = new boolean[8];
   }
 
   protected Schema newInstance() {
     return new ZCMessageSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCMessageSet();
   }
 
   public ZCMessageSet query() {
     return query(null, -1, -1);
   }
 
   public ZCMessageSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCMessageSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCMessageSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCMessageSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.FromUser = ((String)v); return; }
     if (i == 2) { this.ToUser = ((String)v); return; }
     if (i == 3) { this.Box = ((String)v); return; }
     if (i == 4) { if (v == null) this.ReadFlag = null; else this.ReadFlag = new Long(v.toString()); return; }
     if (i == 5) { this.Subject = ((String)v); return; }
     if (i == 6) { this.Content = ((String)v); return; }
     if (i != 7) return; this.AddTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.FromUser;
     if (i == 2) return this.ToUser;
     if (i == 3) return this.Box;
     if (i == 4) return this.ReadFlag;
     if (i == 5) return this.Subject;
     if (i == 6) return this.Content;
     if (i == 7) return this.AddTime;
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
 
   public String getFromUser()
   {
     return this.FromUser;
   }
 
   public void setFromUser(String fromUser)
   {
     this.FromUser = fromUser;
   }
 
   public String getToUser()
   {
     return this.ToUser;
   }
 
   public void setToUser(String toUser)
   {
     this.ToUser = toUser;
   }
 
   public String getBox()
   {
     return this.Box;
   }
 
   public void setBox(String box)
   {
     this.Box = box;
   }
 
   public long getReadFlag()
   {
     if (this.ReadFlag == null) return 0L;
     return this.ReadFlag.longValue();
   }
 
   public void setReadFlag(long readFlag)
   {
     this.ReadFlag = new Long(readFlag);
   }
 
   public void setReadFlag(String readFlag)
   {
     if (readFlag == null) {
       this.ReadFlag = null;
       return;
     }
     this.ReadFlag = new Long(readFlag);
   }
 
   public String getSubject()
   {
     return this.Subject;
   }
 
   public void setSubject(String subject)
   {
     this.Subject = subject;
   }
 
   public String getContent()
   {
     return this.Content;
   }
 
   public void setContent(String content)
   {
     this.Content = content;
   }
 
   public Date getAddTime()
   {
     return this.AddTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.AddTime = addTime;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCMessageSchema
 * JD-Core Version:    0.5.3
 */