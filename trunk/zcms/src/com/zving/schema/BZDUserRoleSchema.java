 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDUserRoleSchema extends Schema
 {
   private String UserName;
   private String RoleCode;
   private String Prop1;
   private String Prop2;
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
     new SchemaColumn("RoleCode", 1, 1, 200, 0, true, true), 
     new SchemaColumn("Prop1", 1, 2, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 3, 50, 0, false, false), 
     new SchemaColumn("Memo", 1, 4, 100, 0, false, false), 
     new SchemaColumn("AddTime", 0, 5, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 6, 200, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 7, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 8, 200, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 9, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 10, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 11, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 12, 50, 0, false, false) };
   public static final String _TableCode = "BZDUserRole";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDUserRole values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDUserRole set UserName=?,RoleCode=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and RoleCode=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDUserRole  where UserName=? and RoleCode=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDUserRole  where UserName=? and RoleCode=? and BackupNo=?";
 
   public BZDUserRoleSchema()
   {
     this.TableCode = "BZDUserRole";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDUserRole values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDUserRole set UserName=?,RoleCode=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and RoleCode=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDUserRole  where UserName=? and RoleCode=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDUserRole  where UserName=? and RoleCode=? and BackupNo=?";
     this.HasSetFlag = new boolean[13];
   }
 
   protected Schema newInstance() {
     return new BZDUserRoleSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDUserRoleSet();
   }
 
   public BZDUserRoleSet query() {
     return query(null, -1, -1);
   }
 
   public BZDUserRoleSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDUserRoleSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDUserRoleSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDUserRoleSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.UserName = ((String)v); return; }
     if (i == 1) { this.RoleCode = ((String)v); return; }
     if (i == 2) { this.Prop1 = ((String)v); return; }
     if (i == 3) { this.Prop2 = ((String)v); return; }
     if (i == 4) { this.Memo = ((String)v); return; }
     if (i == 5) { this.AddTime = ((Date)v); return; }
     if (i == 6) { this.AddUser = ((String)v); return; }
     if (i == 7) { this.ModifyTime = ((Date)v); return; }
     if (i == 8) { this.ModifyUser = ((String)v); return; }
     if (i == 9) { this.BackupNo = ((String)v); return; }
     if (i == 10) { this.BackupOperator = ((String)v); return; }
     if (i == 11) { this.BackupTime = ((Date)v); return; }
     if (i != 12) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.UserName;
     if (i == 1) return this.RoleCode;
     if (i == 2) return this.Prop1;
     if (i == 3) return this.Prop2;
     if (i == 4) return this.Memo;
     if (i == 5) return this.AddTime;
     if (i == 6) return this.AddUser;
     if (i == 7) return this.ModifyTime;
     if (i == 8) return this.ModifyUser;
     if (i == 9) return this.BackupNo;
     if (i == 10) return this.BackupOperator;
     if (i == 11) return this.BackupTime;
     if (i == 12) return this.BackupMemo;
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
 
   public String getRoleCode()
   {
     return this.RoleCode;
   }
 
   public void setRoleCode(String roleCode)
   {
     this.RoleCode = roleCode;
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
 * Qualified Name:     com.zving.schema.BZDUserRoleSchema
 * JD-Core Version:    0.5.3
 */