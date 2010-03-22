 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDRoleSchema extends Schema
 {
   private String RoleCode;
   private String RoleName;
   private String BranchInnerCode;
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
     new SchemaColumn("RoleCode", 1, 0, 200, 0, true, true), 
     new SchemaColumn("RoleName", 1, 1, 100, 0, true, false), 
     new SchemaColumn("BranchInnerCode", 1, 2, 100, 0, true, false), 
     new SchemaColumn("Prop1", 1, 3, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 4, 50, 0, false, false), 
     new SchemaColumn("Memo", 1, 5, 100, 0, false, false), 
     new SchemaColumn("AddTime", 0, 6, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 7, 200, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 8, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 9, 200, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 10, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 11, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 12, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 13, 50, 0, false, false) };
   public static final String _TableCode = "BZDRole";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDRole values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDRole set RoleCode=?,RoleName=?,BranchInnerCode=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where RoleCode=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDRole  where RoleCode=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDRole  where RoleCode=? and BackupNo=?";
 
   public BZDRoleSchema()
   {
     this.TableCode = "BZDRole";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDRole values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDRole set RoleCode=?,RoleName=?,BranchInnerCode=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where RoleCode=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDRole  where RoleCode=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDRole  where RoleCode=? and BackupNo=?";
     this.HasSetFlag = new boolean[14];
   }
 
   protected Schema newInstance() {
     return new BZDRoleSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDRoleSet();
   }
 
   public BZDRoleSet query() {
     return query(null, -1, -1);
   }
 
   public BZDRoleSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDRoleSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDRoleSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDRoleSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.RoleCode = ((String)v); return; }
     if (i == 1) { this.RoleName = ((String)v); return; }
     if (i == 2) { this.BranchInnerCode = ((String)v); return; }
     if (i == 3) { this.Prop1 = ((String)v); return; }
     if (i == 4) { this.Prop2 = ((String)v); return; }
     if (i == 5) { this.Memo = ((String)v); return; }
     if (i == 6) { this.AddTime = ((Date)v); return; }
     if (i == 7) { this.AddUser = ((String)v); return; }
     if (i == 8) { this.ModifyTime = ((Date)v); return; }
     if (i == 9) { this.ModifyUser = ((String)v); return; }
     if (i == 10) { this.BackupNo = ((String)v); return; }
     if (i == 11) { this.BackupOperator = ((String)v); return; }
     if (i == 12) { this.BackupTime = ((Date)v); return; }
     if (i != 13) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.RoleCode;
     if (i == 1) return this.RoleName;
     if (i == 2) return this.BranchInnerCode;
     if (i == 3) return this.Prop1;
     if (i == 4) return this.Prop2;
     if (i == 5) return this.Memo;
     if (i == 6) return this.AddTime;
     if (i == 7) return this.AddUser;
     if (i == 8) return this.ModifyTime;
     if (i == 9) return this.ModifyUser;
     if (i == 10) return this.BackupNo;
     if (i == 11) return this.BackupOperator;
     if (i == 12) return this.BackupTime;
     if (i == 13) return this.BackupMemo;
     return null;
   }
 
   public String getRoleCode()
   {
     return this.RoleCode;
   }
 
   public void setRoleCode(String roleCode)
   {
     this.RoleCode = roleCode;
   }
 
   public String getRoleName()
   {
     return this.RoleName;
   }
 
   public void setRoleName(String roleName)
   {
     this.RoleName = roleName;
   }
 
   public String getBranchInnerCode()
   {
     return this.BranchInnerCode;
   }
 
   public void setBranchInnerCode(String branchInnerCode)
   {
     this.BranchInnerCode = branchInnerCode;
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
 * Qualified Name:     com.zving.schema.BZDRoleSchema
 * JD-Core Version:    0.5.3
 */