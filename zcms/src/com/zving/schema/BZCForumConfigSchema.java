 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCForumConfigSchema extends Schema
 {
   private Long ID;
   private String Name;
   private Long SiteID;
   private String Subdomains;
   private String Des;
   private String TempCloseFlag;
   private String prop1;
   private String prop2;
   private String prop3;
   private String prop4;
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
     new SchemaColumn("Name", 1, 1, 50, 0, false, false), 
     new SchemaColumn("SiteID", 7, 2, 0, 0, false, false), 
     new SchemaColumn("Subdomains", 1, 3, 50, 0, false, false), 
     new SchemaColumn("Des", 1, 4, 1024, 0, false, false), 
     new SchemaColumn("TempCloseFlag", 1, 5, 2, 0, false, false), 
     new SchemaColumn("prop1", 1, 6, 50, 0, false, false), 
     new SchemaColumn("prop2", 1, 7, 50, 0, false, false), 
     new SchemaColumn("prop3", 1, 8, 50, 0, false, false), 
     new SchemaColumn("prop4", 1, 9, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 10, 100, 0, true, false), 
     new SchemaColumn("AddTime", 0, 11, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 12, 100, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 13, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 14, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 15, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 16, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 17, 50, 0, false, false) };
   public static final String _TableCode = "BZCForumConfig";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCForumConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCForumConfig set ID=?,Name=?,SiteID=?,Subdomains=?,Des=?,TempCloseFlag=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCForumConfig  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCForumConfig  where ID=? and BackupNo=?";
 
   public BZCForumConfigSchema()
   {
     this.TableCode = "BZCForumConfig";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCForumConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCForumConfig set ID=?,Name=?,SiteID=?,Subdomains=?,Des=?,TempCloseFlag=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCForumConfig  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCForumConfig  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[18];
   }
 
   protected Schema newInstance() {
     return new BZCForumConfigSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCForumConfigSet();
   }
 
   public BZCForumConfigSet query() {
     return query(null, -1, -1);
   }
 
   public BZCForumConfigSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCForumConfigSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCForumConfigSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCForumConfigSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.Name = ((String)v); return; }
     if (i == 2) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 3) { this.Subdomains = ((String)v); return; }
     if (i == 4) { this.Des = ((String)v); return; }
     if (i == 5) { this.TempCloseFlag = ((String)v); return; }
     if (i == 6) { this.prop1 = ((String)v); return; }
     if (i == 7) { this.prop2 = ((String)v); return; }
     if (i == 8) { this.prop3 = ((String)v); return; }
     if (i == 9) { this.prop4 = ((String)v); return; }
     if (i == 10) { this.AddUser = ((String)v); return; }
     if (i == 11) { this.AddTime = ((Date)v); return; }
     if (i == 12) { this.ModifyUser = ((String)v); return; }
     if (i == 13) { this.ModifyTime = ((Date)v); return; }
     if (i == 14) { this.BackupNo = ((String)v); return; }
     if (i == 15) { this.BackupOperator = ((String)v); return; }
     if (i == 16) { this.BackupTime = ((Date)v); return; }
     if (i != 17) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.Name;
     if (i == 2) return this.SiteID;
     if (i == 3) return this.Subdomains;
     if (i == 4) return this.Des;
     if (i == 5) return this.TempCloseFlag;
     if (i == 6) return this.prop1;
     if (i == 7) return this.prop2;
     if (i == 8) return this.prop3;
     if (i == 9) return this.prop4;
     if (i == 10) return this.AddUser;
     if (i == 11) return this.AddTime;
     if (i == 12) return this.ModifyUser;
     if (i == 13) return this.ModifyTime;
     if (i == 14) return this.BackupNo;
     if (i == 15) return this.BackupOperator;
     if (i == 16) return this.BackupTime;
     if (i == 17) return this.BackupMemo;
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
 
   public String getName()
   {
     return this.Name;
   }
 
   public void setName(String name)
   {
     this.Name = name;
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
 
   public String getSubdomains()
   {
     return this.Subdomains;
   }
 
   public void setSubdomains(String subdomains)
   {
     this.Subdomains = subdomains;
   }
 
   public String getDes()
   {
     return this.Des;
   }
 
   public void setDes(String des)
   {
     this.Des = des;
   }
 
   public String getTempCloseFlag()
   {
     return this.TempCloseFlag;
   }
 
   public void setTempCloseFlag(String tempCloseFlag)
   {
     this.TempCloseFlag = tempCloseFlag;
   }
 
   public String getProp1()
   {
     return this.prop1;
   }
 
   public void setProp1(String prop1)
   {
     this.prop1 = prop1;
   }
 
   public String getProp2()
   {
     return this.prop2;
   }
 
   public void setProp2(String prop2)
   {
     this.prop2 = prop2;
   }
 
   public String getProp3()
   {
     return this.prop3;
   }
 
   public void setProp3(String prop3)
   {
     this.prop3 = prop3;
   }
 
   public String getProp4()
   {
     return this.prop4;
   }
 
   public void setProp4(String prop4)
   {
     this.prop4 = prop4;
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
 * Qualified Name:     com.zving.schema.BZCForumConfigSchema
 * JD-Core Version:    0.5.3
 */