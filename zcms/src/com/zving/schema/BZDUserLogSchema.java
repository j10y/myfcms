 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDUserLogSchema extends Schema
 {
   private String UserName;
   private Long LogID;
   private String IP;
   private String LogType;
   private String SubType;
   private String LogMessage;
   private String Memo;
   private Date AddTime;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("UserName", 1, 0, 200, 0, true, true), 
     new SchemaColumn("LogID", 7, 1, 0, 0, true, true), 
     new SchemaColumn("IP", 1, 2, 40, 0, false, false), 
     new SchemaColumn("LogType", 1, 3, 20, 0, true, false), 
     new SchemaColumn("SubType", 1, 4, 20, 0, false, false), 
     new SchemaColumn("LogMessage", 1, 5, 400, 0, false, false), 
     new SchemaColumn("Memo", 1, 6, 40, 0, false, false), 
     new SchemaColumn("AddTime", 0, 7, 0, 0, true, false), 
     new SchemaColumn("BackupNo", 1, 8, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 9, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 10, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 11, 50, 0, false, false) };
   public static final String _TableCode = "BZDUserLog";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDUserLog values(?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDUserLog set UserName=?,LogID=?,IP=?,LogType=?,SubType=?,LogMessage=?,Memo=?,AddTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and LogID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDUserLog  where UserName=? and LogID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDUserLog  where UserName=? and LogID=? and BackupNo=?";
 
   public BZDUserLogSchema()
   {
     this.TableCode = "BZDUserLog";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDUserLog values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDUserLog set UserName=?,LogID=?,IP=?,LogType=?,SubType=?,LogMessage=?,Memo=?,AddTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and LogID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDUserLog  where UserName=? and LogID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDUserLog  where UserName=? and LogID=? and BackupNo=?";
     this.HasSetFlag = new boolean[12];
   }
 
   protected Schema newInstance() {
     return new BZDUserLogSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDUserLogSet();
   }
 
   public BZDUserLogSet query() {
     return query(null, -1, -1);
   }
 
   public BZDUserLogSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDUserLogSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDUserLogSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDUserLogSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.UserName = ((String)v); return; }
     if (i == 1) { if (v == null) this.LogID = null; else this.LogID = new Long(v.toString()); return; }
     if (i == 2) { this.IP = ((String)v); return; }
     if (i == 3) { this.LogType = ((String)v); return; }
     if (i == 4) { this.SubType = ((String)v); return; }
     if (i == 5) { this.LogMessage = ((String)v); return; }
     if (i == 6) { this.Memo = ((String)v); return; }
     if (i == 7) { this.AddTime = ((Date)v); return; }
     if (i == 8) { this.BackupNo = ((String)v); return; }
     if (i == 9) { this.BackupOperator = ((String)v); return; }
     if (i == 10) { this.BackupTime = ((Date)v); return; }
     if (i != 11) return; this.BackupMemo = ((String)v); return;
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
     if (i == 8) return this.BackupNo;
     if (i == 9) return this.BackupOperator;
     if (i == 10) return this.BackupTime;
     if (i == 11) return this.BackupMemo;
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
 * Qualified Name:     com.zving.schema.BZDUserLogSchema
 * JD-Core Version:    0.5.3
 */