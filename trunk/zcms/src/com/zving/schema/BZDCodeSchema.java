 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDCodeSchema extends Schema
 {
   private String CodeType;
   private String ParentCode;
   private String CodeValue;
   private String CodeName;
   private Long CodeOrder;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private String Memo;
   private Date AddTime;
   private String AddUser;
   private Date ModifyTime;
   private String ModifyUser;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("CodeType", 1, 0, 40, 0, true, true), 
     new SchemaColumn("ParentCode", 1, 1, 40, 0, true, true), 
     new SchemaColumn("CodeValue", 1, 2, 40, 0, true, true), 
     new SchemaColumn("CodeName", 1, 3, 40, 0, true, false), 
     new SchemaColumn("CodeOrder", 7, 4, 0, 0, true, false), 
     new SchemaColumn("Prop1", 1, 5, 40, 0, false, false), 
     new SchemaColumn("Prop2", 1, 6, 40, 0, false, false), 
     new SchemaColumn("Prop3", 1, 7, 40, 0, false, false), 
     new SchemaColumn("Prop4", 1, 8, 40, 0, false, false), 
     new SchemaColumn("Memo", 1, 9, 40, 0, false, false), 
     new SchemaColumn("AddTime", 0, 10, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 11, 200, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 12, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 13, 200, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 14, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 15, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 16, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 17, 50, 0, false, false) };
   public static final String _TableCode = "BZDCode";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDCode values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDCode set CodeType=?,ParentCode=?,CodeValue=?,CodeName=?,CodeOrder=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where CodeType=? and ParentCode=? and CodeValue=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDCode  where CodeType=? and ParentCode=? and CodeValue=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDCode  where CodeType=? and ParentCode=? and CodeValue=? and BackupNo=?";
 
   public BZDCodeSchema()
   {
     this.TableCode = "BZDCode";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDCode values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDCode set CodeType=?,ParentCode=?,CodeValue=?,CodeName=?,CodeOrder=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where CodeType=? and ParentCode=? and CodeValue=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDCode  where CodeType=? and ParentCode=? and CodeValue=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDCode  where CodeType=? and ParentCode=? and CodeValue=? and BackupNo=?";
     this.HasSetFlag = new boolean[18];
   }
 
   protected Schema newInstance() {
     return new BZDCodeSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDCodeSet();
   }
 
   public BZDCodeSet query() {
     return query(null, -1, -1);
   }
 
   public BZDCodeSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDCodeSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDCodeSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDCodeSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.CodeType = ((String)v); return; }
     if (i == 1) { this.ParentCode = ((String)v); return; }
     if (i == 2) { this.CodeValue = ((String)v); return; }
     if (i == 3) { this.CodeName = ((String)v); return; }
     if (i == 4) { if (v == null) this.CodeOrder = null; else this.CodeOrder = new Long(v.toString()); return; }
     if (i == 5) { this.Prop1 = ((String)v); return; }
     if (i == 6) { this.Prop2 = ((String)v); return; }
     if (i == 7) { this.Prop3 = ((String)v); return; }
     if (i == 8) { this.Prop4 = ((String)v); return; }
     if (i == 9) { this.Memo = ((String)v); return; }
     if (i == 10) { this.AddTime = ((Date)v); return; }
     if (i == 11) { this.AddUser = ((String)v); return; }
     if (i == 12) { this.ModifyTime = ((Date)v); return; }
     if (i == 13) { this.ModifyUser = ((String)v); return; }
     if (i == 14) { this.BackupNo = ((String)v); return; }
     if (i == 15) { this.BackupOperator = ((String)v); return; }
     if (i == 16) { this.BackupTime = ((Date)v); return; }
     if (i != 17) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.CodeType;
     if (i == 1) return this.ParentCode;
     if (i == 2) return this.CodeValue;
     if (i == 3) return this.CodeName;
     if (i == 4) return this.CodeOrder;
     if (i == 5) return this.Prop1;
     if (i == 6) return this.Prop2;
     if (i == 7) return this.Prop3;
     if (i == 8) return this.Prop4;
     if (i == 9) return this.Memo;
     if (i == 10) return this.AddTime;
     if (i == 11) return this.AddUser;
     if (i == 12) return this.ModifyTime;
     if (i == 13) return this.ModifyUser;
     if (i == 14) return this.BackupNo;
     if (i == 15) return this.BackupOperator;
     if (i == 16) return this.BackupTime;
     if (i == 17) return this.BackupMemo;
     return null;
   }
 
   public String getCodeType()
   {
     return this.CodeType;
   }
 
   public void setCodeType(String codeType)
   {
     this.CodeType = codeType;
   }
 
   public String getParentCode()
   {
     return this.ParentCode;
   }
 
   public void setParentCode(String parentCode)
   {
     this.ParentCode = parentCode;
   }
 
   public String getCodeValue()
   {
     return this.CodeValue;
   }
 
   public void setCodeValue(String codeValue)
   {
     this.CodeValue = codeValue;
   }
 
   public String getCodeName()
   {
     return this.CodeName;
   }
 
   public void setCodeName(String codeName)
   {
     this.CodeName = codeName;
   }
 
   public long getCodeOrder()
   {
     if (this.CodeOrder == null) return 0L;
     return this.CodeOrder.longValue();
   }
 
   public void setCodeOrder(long codeOrder)
   {
     this.CodeOrder = new Long(codeOrder);
   }
 
   public void setCodeOrder(String codeOrder)
   {
     if (codeOrder == null) {
       this.CodeOrder = null;
       return;
     }
     this.CodeOrder = new Long(codeOrder);
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
 * Qualified Name:     com.zving.schema.BZDCodeSchema
 * JD-Core Version:    0.5.3
 */