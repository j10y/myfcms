 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDUserSchema extends Schema
 {
   private String UserName;
   private String RealName;
   private String Password;
   private String BranchInnerCode;
   private String IsBranchAdmin;
   private String Status;
   private String Type;
   private String Email;
   private String Tel;
   private String Mobile;
   private String Prop1;
   private String Prop2;
   private String Prop6;
   private String Prop5;
   private String Prop4;
   private String Prop3;
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
     new SchemaColumn("UserName", 1, 0, 200, 0, true, true), 
     new SchemaColumn("RealName", 1, 1, 200, 0, false, false), 
     new SchemaColumn("Password", 1, 2, 100, 0, true, false), 
     new SchemaColumn("BranchInnerCode", 1, 3, 100, 0, true, false), 
     new SchemaColumn("IsBranchAdmin", 1, 4, 1, 0, true, false), 
     new SchemaColumn("Status", 1, 5, 50, 0, true, false), 
     new SchemaColumn("Type", 1, 6, 1, 0, true, false), 
     new SchemaColumn("Email", 1, 7, 100, 0, false, false), 
     new SchemaColumn("Tel", 1, 8, 20, 0, false, false), 
     new SchemaColumn("Mobile", 1, 9, 50, 0, false, false), 
     new SchemaColumn("Prop1", 1, 10, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 11, 50, 0, false, false), 
     new SchemaColumn("Prop6", 1, 12, 50, 0, false, false), 
     new SchemaColumn("Prop5", 1, 13, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 14, 100, 0, false, false), 
     new SchemaColumn("Prop3", 1, 15, 100, 0, false, false), 
     new SchemaColumn("Memo", 1, 16, 100, 0, false, false), 
     new SchemaColumn("AddTime", 0, 17, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 18, 200, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 19, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 20, 200, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 21, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 22, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 23, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 24, 50, 0, false, false) };
   public static final String _TableCode = "BZDUser";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDUser values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDUser set UserName=?,RealName=?,Password=?,BranchInnerCode=?,IsBranchAdmin=?,Status=?,Type=?,Email=?,Tel=?,Mobile=?,Prop1=?,Prop2=?,Prop6=?,Prop5=?,Prop4=?,Prop3=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDUser  where UserName=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDUser  where UserName=? and BackupNo=?";
 
   public BZDUserSchema()
   {
     this.TableCode = "BZDUser";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDUser values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDUser set UserName=?,RealName=?,Password=?,BranchInnerCode=?,IsBranchAdmin=?,Status=?,Type=?,Email=?,Tel=?,Mobile=?,Prop1=?,Prop2=?,Prop6=?,Prop5=?,Prop4=?,Prop3=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDUser  where UserName=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDUser  where UserName=? and BackupNo=?";
     this.HasSetFlag = new boolean[25];
   }
 
   protected Schema newInstance() {
     return new BZDUserSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDUserSet();
   }
 
   public BZDUserSet query() {
     return query(null, -1, -1);
   }
 
   public BZDUserSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDUserSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDUserSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDUserSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.UserName = ((String)v); return; }
     if (i == 1) { this.RealName = ((String)v); return; }
     if (i == 2) { this.Password = ((String)v); return; }
     if (i == 3) { this.BranchInnerCode = ((String)v); return; }
     if (i == 4) { this.IsBranchAdmin = ((String)v); return; }
     if (i == 5) { this.Status = ((String)v); return; }
     if (i == 6) { this.Type = ((String)v); return; }
     if (i == 7) { this.Email = ((String)v); return; }
     if (i == 8) { this.Tel = ((String)v); return; }
     if (i == 9) { this.Mobile = ((String)v); return; }
     if (i == 10) { this.Prop1 = ((String)v); return; }
     if (i == 11) { this.Prop2 = ((String)v); return; }
     if (i == 12) { this.Prop6 = ((String)v); return; }
     if (i == 13) { this.Prop5 = ((String)v); return; }
     if (i == 14) { this.Prop4 = ((String)v); return; }
     if (i == 15) { this.Prop3 = ((String)v); return; }
     if (i == 16) { this.Memo = ((String)v); return; }
     if (i == 17) { this.AddTime = ((Date)v); return; }
     if (i == 18) { this.AddUser = ((String)v); return; }
     if (i == 19) { this.ModifyTime = ((Date)v); return; }
     if (i == 20) { this.ModifyUser = ((String)v); return; }
     if (i == 21) { this.BackupNo = ((String)v); return; }
     if (i == 22) { this.BackupOperator = ((String)v); return; }
     if (i == 23) { this.BackupTime = ((Date)v); return; }
     if (i != 24) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.UserName;
     if (i == 1) return this.RealName;
     if (i == 2) return this.Password;
     if (i == 3) return this.BranchInnerCode;
     if (i == 4) return this.IsBranchAdmin;
     if (i == 5) return this.Status;
     if (i == 6) return this.Type;
     if (i == 7) return this.Email;
     if (i == 8) return this.Tel;
     if (i == 9) return this.Mobile;
     if (i == 10) return this.Prop1;
     if (i == 11) return this.Prop2;
     if (i == 12) return this.Prop6;
     if (i == 13) return this.Prop5;
     if (i == 14) return this.Prop4;
     if (i == 15) return this.Prop3;
     if (i == 16) return this.Memo;
     if (i == 17) return this.AddTime;
     if (i == 18) return this.AddUser;
     if (i == 19) return this.ModifyTime;
     if (i == 20) return this.ModifyUser;
     if (i == 21) return this.BackupNo;
     if (i == 22) return this.BackupOperator;
     if (i == 23) return this.BackupTime;
     if (i == 24) return this.BackupMemo;
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
 
   public String getRealName()
   {
     return this.RealName;
   }
 
   public void setRealName(String realName)
   {
     this.RealName = realName;
   }
 
   public String getPassword()
   {
     return this.Password;
   }
 
   public void setPassword(String password)
   {
     this.Password = password;
   }
 
   public String getBranchInnerCode()
   {
     return this.BranchInnerCode;
   }
 
   public void setBranchInnerCode(String branchInnerCode)
   {
     this.BranchInnerCode = branchInnerCode;
   }
 
   public String getIsBranchAdmin()
   {
     return this.IsBranchAdmin;
   }
 
   public void setIsBranchAdmin(String isBranchAdmin)
   {
     this.IsBranchAdmin = isBranchAdmin;
   }
 
   public String getStatus()
   {
     return this.Status;
   }
 
   public void setStatus(String status)
   {
     this.Status = status;
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
   }
 
   public String getEmail()
   {
     return this.Email;
   }
 
   public void setEmail(String email)
   {
     this.Email = email;
   }
 
   public String getTel()
   {
     return this.Tel;
   }
 
   public void setTel(String tel)
   {
     this.Tel = tel;
   }
 
   public String getMobile()
   {
     return this.Mobile;
   }
 
   public void setMobile(String mobile)
   {
     this.Mobile = mobile;
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
 
   public String getProp6()
   {
     return this.Prop6;
   }
 
   public void setProp6(String prop6)
   {
     this.Prop6 = prop6;
   }
 
   public String getProp5()
   {
     return this.Prop5;
   }
 
   public void setProp5(String prop5)
   {
     this.Prop5 = prop5;
   }
 
   public String getProp4()
   {
     return this.Prop4;
   }
 
   public void setProp4(String prop4)
   {
     this.Prop4 = prop4;
   }
 
   public String getProp3()
   {
     return this.Prop3;
   }
 
   public void setProp3(String prop3)
   {
     this.Prop3 = prop3;
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
 * Qualified Name:     com.zving.schema.BZDUserSchema
 * JD-Core Version:    0.5.3
 */