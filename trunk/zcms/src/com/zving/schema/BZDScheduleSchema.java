 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDScheduleSchema extends Schema
 {
   private Long ID;
   private Long SourceID;
   private String TypeCode;
   private String CronExpression;
   private String PlanType;
   private Date StartTime;
   private String Description;
   private String IsUsing;
   private String OrderFlag;
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
     new SchemaColumn("SourceID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("TypeCode", 1, 2, 30, 0, true, false), 
     new SchemaColumn("CronExpression", 1, 3, 100, 0, true, false), 
     new SchemaColumn("PlanType", 1, 4, 10, 0, true, false), 
     new SchemaColumn("StartTime", 0, 5, 0, 0, false, false), 
     new SchemaColumn("Description", 1, 6, 255, 0, false, false), 
     new SchemaColumn("IsUsing", 1, 7, 2, 0, true, false), 
     new SchemaColumn("OrderFlag", 1, 8, 50, 0, false, false), 
     new SchemaColumn("Prop1", 1, 9, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 10, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 11, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 12, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 13, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 14, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 15, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 16, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 17, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 18, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 19, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 20, 50, 0, false, false) };
   public static final String _TableCode = "BZDSchedule";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDSchedule values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDSchedule set ID=?,SourceID=?,TypeCode=?,CronExpression=?,PlanType=?,StartTime=?,Description=?,IsUsing=?,OrderFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDSchedule  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDSchedule  where ID=? and BackupNo=?";
 
   public BZDScheduleSchema()
   {
     this.TableCode = "BZDSchedule";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDSchedule values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDSchedule set ID=?,SourceID=?,TypeCode=?,CronExpression=?,PlanType=?,StartTime=?,Description=?,IsUsing=?,OrderFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDSchedule  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDSchedule  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[21];
   }
 
   protected Schema newInstance() {
     return new BZDScheduleSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDScheduleSet();
   }
 
   public BZDScheduleSet query() {
     return query(null, -1, -1);
   }
 
   public BZDScheduleSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDScheduleSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDScheduleSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDScheduleSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SourceID = null; else this.SourceID = new Long(v.toString()); return; }
     if (i == 2) { this.TypeCode = ((String)v); return; }
     if (i == 3) { this.CronExpression = ((String)v); return; }
     if (i == 4) { this.PlanType = ((String)v); return; }
     if (i == 5) { this.StartTime = ((Date)v); return; }
     if (i == 6) { this.Description = ((String)v); return; }
     if (i == 7) { this.IsUsing = ((String)v); return; }
     if (i == 8) { this.OrderFlag = ((String)v); return; }
     if (i == 9) { this.Prop1 = ((String)v); return; }
     if (i == 10) { this.Prop2 = ((String)v); return; }
     if (i == 11) { this.Prop3 = ((String)v); return; }
     if (i == 12) { this.Prop4 = ((String)v); return; }
     if (i == 13) { this.AddUser = ((String)v); return; }
     if (i == 14) { this.AddTime = ((Date)v); return; }
     if (i == 15) { this.ModifyUser = ((String)v); return; }
     if (i == 16) { this.ModifyTime = ((Date)v); return; }
     if (i == 17) { this.BackupNo = ((String)v); return; }
     if (i == 18) { this.BackupOperator = ((String)v); return; }
     if (i == 19) { this.BackupTime = ((Date)v); return; }
     if (i != 20) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SourceID;
     if (i == 2) return this.TypeCode;
     if (i == 3) return this.CronExpression;
     if (i == 4) return this.PlanType;
     if (i == 5) return this.StartTime;
     if (i == 6) return this.Description;
     if (i == 7) return this.IsUsing;
     if (i == 8) return this.OrderFlag;
     if (i == 9) return this.Prop1;
     if (i == 10) return this.Prop2;
     if (i == 11) return this.Prop3;
     if (i == 12) return this.Prop4;
     if (i == 13) return this.AddUser;
     if (i == 14) return this.AddTime;
     if (i == 15) return this.ModifyUser;
     if (i == 16) return this.ModifyTime;
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
 
   public long getSourceID()
   {
     if (this.SourceID == null) return 0L;
     return this.SourceID.longValue();
   }
 
   public void setSourceID(long sourceID)
   {
     this.SourceID = new Long(sourceID);
   }
 
   public void setSourceID(String sourceID)
   {
     if (sourceID == null) {
       this.SourceID = null;
       return;
     }
     this.SourceID = new Long(sourceID);
   }
 
   public String getTypeCode()
   {
     return this.TypeCode;
   }
 
   public void setTypeCode(String typeCode)
   {
     this.TypeCode = typeCode;
   }
 
   public String getCronExpression()
   {
     return this.CronExpression;
   }
 
   public void setCronExpression(String cronExpression)
   {
     this.CronExpression = cronExpression;
   }
 
   public String getPlanType()
   {
     return this.PlanType;
   }
 
   public void setPlanType(String planType)
   {
     this.PlanType = planType;
   }
 
   public Date getStartTime()
   {
     return this.StartTime;
   }
 
   public void setStartTime(Date startTime)
   {
     this.StartTime = startTime;
   }
 
   public String getDescription()
   {
     return this.Description;
   }
 
   public void setDescription(String description)
   {
     this.Description = description;
   }
 
   public String getIsUsing()
   {
     return this.IsUsing;
   }
 
   public void setIsUsing(String isUsing)
   {
     this.IsUsing = isUsing;
   }
 
   public String getOrderFlag()
   {
     return this.OrderFlag;
   }
 
   public void setOrderFlag(String orderFlag)
   {
     this.OrderFlag = orderFlag;
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
 * Qualified Name:     com.zving.schema.BZDScheduleSchema
 * JD-Core Version:    0.5.3
 */