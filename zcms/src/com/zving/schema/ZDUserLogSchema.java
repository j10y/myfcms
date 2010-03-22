 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZDUserLogSchema extends Schema
 {
   private String UserName;
   private Long LogID;
   private String IP;
   private String LogType;
   private String SubType;
   private String LogMessage;
   private String Memo;
   private Date AddTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("UserName", 1, 0, 200, 0, true, true), 
     new SchemaColumn("LogID", 7, 1, 0, 0, true, true), 
     new SchemaColumn("IP", 1, 2, 40, 0, false, false), 
     new SchemaColumn("LogType", 1, 3, 20, 0, true, false), 
     new SchemaColumn("SubType", 1, 4, 20, 0, false, false), 
     new SchemaColumn("LogMessage", 1, 5, 400, 0, false, false), 
     new SchemaColumn("Memo", 1, 6, 40, 0, false, false), 
     new SchemaColumn("AddTime", 0, 7, 0, 0, true, false) };
   public static final String _TableCode = "ZDUserLog";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDUserLog values(?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDUserLog set UserName=?,LogID=?,IP=?,LogType=?,SubType=?,LogMessage=?,Memo=?,AddTime=? where UserName=? and LogID=?";
   protected static final String _DeleteSQL = "delete from ZDUserLog  where UserName=? and LogID=?";
   protected static final String _FillAllSQL = "select * from ZDUserLog  where UserName=? and LogID=?";
 
   public ZDUserLogSchema()
   {
     this.TableCode = "ZDUserLog";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDUserLog values(?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDUserLog set UserName=?,LogID=?,IP=?,LogType=?,SubType=?,LogMessage=?,Memo=?,AddTime=? where UserName=? and LogID=?";
     this.DeleteSQL = "delete from ZDUserLog  where UserName=? and LogID=?";
     this.FillAllSQL = "select * from ZDUserLog  where UserName=? and LogID=?";
     this.HasSetFlag = new boolean[8];
   }
 
   protected Schema newInstance() {
     return new ZDUserLogSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDUserLogSet();
   }
 
   public ZDUserLogSet query() {
     return query(null, -1, -1);
   }
 
   public ZDUserLogSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDUserLogSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDUserLogSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDUserLogSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.UserName = ((String)v); return; }
     if (i == 1) { if (v == null) this.LogID = null; else this.LogID = new Long(v.toString()); return; }
     if (i == 2) { this.IP = ((String)v); return; }
     if (i == 3) { this.LogType = ((String)v); return; }
     if (i == 4) { this.SubType = ((String)v); return; }
     if (i == 5) { this.LogMessage = ((String)v); return; }
     if (i == 6) { this.Memo = ((String)v); return; }
     if (i != 7) return; this.AddTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.UserName;
     if (i == 1) return this.LogID;
     if (i == 2) return this.IP;
     if (i == 3) return this.LogType;
     if (i == 4) return this.SubType;
     if (i == 5) return this.LogMessage;
     if (i == 6) return this.Memo;
     if (i == 7) return this.AddTime;
     return null;
   }
 
   public String getUserName()
   {
     return this.UserName;
   }
 
   public void setUserName(String userName)
   {
     this.UserName = userName;
   }
 
   public long getLogID()
   {
     if (this.LogID == null) return 0L;
     return this.LogID.longValue();
   }
 
   public void setLogID(long logID)
   {
     this.LogID = new Long(logID);
   }
 
   public void setLogID(String logID)
   {
     if (logID == null) {
       this.LogID = null;
       return;
     }
     this.LogID = new Long(logID);
   }
 
   public String getIP()
   {
     return this.IP;
   }
 
   public void setIP(String iP)
   {
     this.IP = iP;
   }
 
   public String getLogType()
   {
     return this.LogType;
   }
 
   public void setLogType(String logType)
   {
     this.LogType = logType;
   }
 
   public String getSubType()
   {
     return this.SubType;
   }
 
   public void setSubType(String subType)
   {
     this.SubType = subType;
   }
 
   public String getLogMessage()
   {
     return this.LogMessage;
   }
 
   public void setLogMessage(String logMessage)
   {
     this.LogMessage = logMessage;
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
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
 * Qualified Name:     com.zving.schema.ZDUserLogSchema
 * JD-Core Version:    0.5.3
 */