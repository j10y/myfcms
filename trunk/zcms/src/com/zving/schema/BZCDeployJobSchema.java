 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCDeployJobSchema extends Schema
 {
   private Long ID;
   private Long ConfigID;
   private Long SiteID;
   private String Source;
   private String Target;
   private String Method;
   private String Host;
   private Long Port;
   private String UserName;
   private String Password;
   private Long Status;
   private Long RetryCount;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("ConfigID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("SiteID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Source", 1, 3, 255, 0, false, false), 
     new SchemaColumn("Target", 1, 4, 255, 0, false, false), 
     new SchemaColumn("Method", 1, 5, 50, 0, false, false), 
     new SchemaColumn("Host", 1, 6, 255, 0, false, false), 
     new SchemaColumn("Port", 7, 7, 0, 0, false, false), 
     new SchemaColumn("UserName", 1, 8, 100, 0, false, false), 
     new SchemaColumn("Password", 1, 9, 100, 0, false, false), 
     new SchemaColumn("Status", 7, 10, 0, 0, false, false), 
     new SchemaColumn("RetryCount", 7, 11, 0, 0, false, false), 
     new SchemaColumn("Prop1", 1, 12, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 13, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 14, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 15, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 16, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 17, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 18, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 19, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 20, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 21, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 22, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 23, 50, 0, false, false) };
   public static final String _TableCode = "BZCDeployJob";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCDeployJob values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCDeployJob set ID=?,ConfigID=?,SiteID=?,Source=?,Target=?,Method=?,Host=?,Port=?,UserName=?,Password=?,Status=?,RetryCount=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCDeployJob  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCDeployJob  where ID=? and BackupNo=?";
 
   public BZCDeployJobSchema()
   {
     this.TableCode = "BZCDeployJob";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCDeployJob values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCDeployJob set ID=?,ConfigID=?,SiteID=?,Source=?,Target=?,Method=?,Host=?,Port=?,UserName=?,Password=?,Status=?,RetryCount=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCDeployJob  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCDeployJob  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[24];
   }
 
   protected Schema newInstance() {
     return new BZCDeployJobSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCDeployJobSet();
   }
 
   public BZCDeployJobSet query() {
     return query(null, -1, -1);
   }
 
   public BZCDeployJobSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCDeployJobSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCDeployJobSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCDeployJobSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.ConfigID = null; else this.ConfigID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 3) { this.Source = ((String)v); return; }
     if (i == 4) { this.Target = ((String)v); return; }
     if (i == 5) { this.Method = ((String)v); return; }
     if (i == 6) { this.Host = ((String)v); return; }
     if (i == 7) { if (v == null) this.Port = null; else this.Port = new Long(v.toString()); return; }
     if (i == 8) { this.UserName = ((String)v); return; }
     if (i == 9) { this.Password = ((String)v); return; }
     if (i == 10) { if (v == null) this.Status = null; else this.Status = new Long(v.toString()); return; }
     if (i == 11) { if (v == null) this.RetryCount = null; else this.RetryCount = new Long(v.toString()); return; }
     if (i == 12) { this.Prop1 = ((String)v); return; }
     if (i == 13) { this.Prop2 = ((String)v); return; }
     if (i == 14) { this.Prop3 = ((String)v); return; }
     if (i == 15) { this.Prop4 = ((String)v); return; }
     if (i == 16) { this.AddUser = ((String)v); return; }
     if (i == 17) { this.AddTime = ((Date)v); return; }
     if (i == 18) { this.ModifyUser = ((String)v); return; }
     if (i == 19) { this.ModifyTime = ((Date)v); return; }
     if (i == 20) { this.BackupNo = ((String)v); return; }
     if (i == 21) { this.BackupOperator = ((String)v); return; }
     if (i == 22) { this.BackupTime = ((Date)v); return; }
     if (i != 23) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.ConfigID;
     if (i == 2) return this.SiteID;
     if (i == 3) return this.Source;
     if (i == 4) return this.Target;
     if (i == 5) return this.Method;
     if (i == 6) return this.Host;
     if (i == 7) return this.Port;
     if (i == 8) return this.UserName;
     if (i == 9) return this.Password;
     if (i == 10) return this.Status;
     if (i == 11) return this.RetryCount;
     if (i == 12) return this.Prop1;
     if (i == 13) return this.Prop2;
     if (i == 14) return this.Prop3;
     if (i == 15) return this.Prop4;
     if (i == 16) return this.AddUser;
     if (i == 17) return this.AddTime;
     if (i == 18) return this.ModifyUser;
     if (i == 19) return this.ModifyTime;
     if (i == 20) return this.BackupNo;
     if (i == 21) return this.BackupOperator;
     if (i == 22) return this.BackupTime;
     if (i == 23) return this.BackupMemo;
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
 
   public long getConfigID()
   {
     if (this.ConfigID == null) return 0L;
     return this.ConfigID.longValue();
   }
 
   public void setConfigID(long configID)
   {
     this.ConfigID = new Long(configID);
   }
 
   public void setConfigID(String configID)
   {
     if (configID == null) {
       this.ConfigID = null;
       return;
     }
     this.ConfigID = new Long(configID);
   }
 
   public long getSiteID()
   {
     if (this.SiteID == null) return 0L;
     return this.SiteID.longValue();
   }
 
   public void setSiteID(long siteID)
   {
     this.SiteID = new Long(siteID);
   }
 
   public void setSiteID(String siteID)
   {
     if (siteID == null) {
       this.SiteID = null;
       return;
     }
     this.SiteID = new Long(siteID);
   }
 
   public String getSource()
   {
     return this.Source;
   }
 
   public void setSource(String source)
   {
     this.Source = source;
   }
 
   public String getTarget()
   {
     return this.Target;
   }
 
   public void setTarget(String target)
   {
     this.Target = target;
   }
 
   public String getMethod()
   {
     return this.Method;
   }
 
   public void setMethod(String method)
   {
     this.Method = method;
   }
 
   public String getHost()
   {
     return this.Host;
   }
 
   public void setHost(String host)
   {
     this.Host = host;
   }
 
   public long getPort()
   {
     if (this.Port == null) return 0L;
     return this.Port.longValue();
   }
 
   public void setPort(long port)
   {
     this.Port = new Long(port);
   }
 
   public void setPort(String port)
   {
     if (port == null) {
       this.Port = null;
       return;
     }
     this.Port = new Long(port);
   }
 
   public String getUserName()
   {
     return this.UserName;
   }
 
   public void setUserName(String userName)
   {
     this.UserName = userName;
   }
 
   public String getPassword()
   {
     return this.Password;
   }
 
   public void setPassword(String password)
   {
     this.Password = password;
   }
 
   public long getStatus()
   {
     if (this.Status == null) return 0L;
     return this.Status.longValue();
   }
 
   public void setStatus(long status)
   {
     this.Status = new Long(status);
   }
 
   public void setStatus(String status)
   {
     if (status == null) {
       this.Status = null;
       return;
     }
     this.Status = new Long(status);
   }
 
   public long getRetryCount()
   {
     if (this.RetryCount == null) return 0L;
     return this.RetryCount.longValue();
   }
 
   public void setRetryCount(long retryCount)
   {
     this.RetryCount = new Long(retryCount);
   }
 
   public void setRetryCount(String retryCount)
   {
     if (retryCount == null) {
       this.RetryCount = null;
       return;
     }
     this.RetryCount = new Long(retryCount);
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
 
   public String getAddUser()
   {
     return this.AddUser;
   }
 
   public void setAddUser(String addUser)
   {
     this.AddUser = addUser;
   }
 
   public Date getAddTime()
   {
     return this.AddTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.AddTime = addTime;
   }
 
   public String getModifyUser()
   {
     return this.ModifyUser;
   }
 
   public void setModifyUser(String modifyUser)
   {
     this.ModifyUser = modifyUser;
   }
 
   public Date getModifyTime()
   {
     return this.ModifyTime;
   }
 
   public void setModifyTime(Date modifyTime)
   {
     this.ModifyTime = modifyTime;
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
 * Qualified Name:     com.zving.schema.BZCDeployJobSchema
 * JD-Core Version:    0.5.3
 */