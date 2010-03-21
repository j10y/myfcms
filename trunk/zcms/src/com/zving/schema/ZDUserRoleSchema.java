 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZDUserRoleSchema extends Schema
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
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("UserName", 1, 0, 200, 0, true, true), 
     new SchemaColumn("RoleCode", 1, 1, 200, 0, true, true), 
     new SchemaColumn("Prop1", 1, 2, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 3, 50, 0, false, false), 
     new SchemaColumn("Memo", 1, 4, 100, 0, false, false), 
     new SchemaColumn("AddTime", 0, 5, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 6, 200, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 7, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 8, 200, 0, false, false) };
   public static final String _TableCode = "ZDUserRole";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDUserRole values(?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDUserRole set UserName=?,RoleCode=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where UserName=? and RoleCode=?";
   protected static final String _DeleteSQL = "delete from ZDUserRole  where UserName=? and RoleCode=?";
   protected static final String _FillAllSQL = "select * from ZDUserRole  where UserName=? and RoleCode=?";
 
   public ZDUserRoleSchema()
   {
     this.TableCode = "ZDUserRole";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDUserRole values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDUserRole set UserName=?,RoleCode=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where UserName=? and RoleCode=?";
     this.DeleteSQL = "delete from ZDUserRole  where UserName=? and RoleCode=?";
     this.FillAllSQL = "select * from ZDUserRole  where UserName=? and RoleCode=?";
     this.HasSetFlag = new boolean[9];
   }
 
   protected Schema newInstance() {
     return new ZDUserRoleSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDUserRoleSet();
   }
 
   public ZDUserRoleSet query() {
     return query(null, -1, -1);
   }
 
   public ZDUserRoleSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDUserRoleSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDUserRoleSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDUserRoleSet)querySet(qb, pageSize, pageIndex));
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
     if (i != 8) return; this.ModifyUser = ((String)v); return;
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
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDUserRoleSchema
 * JD-Core Version:    0.5.3
 */