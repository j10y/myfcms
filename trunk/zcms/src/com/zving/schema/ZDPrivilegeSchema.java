 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDPrivilegeSchema extends Schema
 {
   private String OwnerType;
   private String Owner;
   private String PrivType;
   private String ID;
   private String Code;
   private String Value;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("OwnerType", 1, 0, 1, 0, true, true), 
     new SchemaColumn("Owner", 1, 1, 100, 0, true, true), 
     new SchemaColumn("PrivType", 1, 2, 40, 0, true, true), 
     new SchemaColumn("ID", 1, 3, 100, 0, true, true), 
     new SchemaColumn("Code", 1, 4, 40, 0, true, true), 
     new SchemaColumn("Value", 1, 5, 2, 0, true, false) };
   public static final String _TableCode = "ZDPrivilege";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDPrivilege values(?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDPrivilege set OwnerType=?,Owner=?,PrivType=?,ID=?,Code=?,Value=? where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=?";
   protected static final String _DeleteSQL = "delete from ZDPrivilege  where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=?";
   protected static final String _FillAllSQL = "select * from ZDPrivilege  where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=?";
 
   public ZDPrivilegeSchema()
   {
     this.TableCode = "ZDPrivilege";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDPrivilege values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDPrivilege set OwnerType=?,Owner=?,PrivType=?,ID=?,Code=?,Value=? where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=?";
     this.DeleteSQL = "delete from ZDPrivilege  where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=?";
     this.FillAllSQL = "select * from ZDPrivilege  where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=?";
     this.HasSetFlag = new boolean[6];
   }
 
   protected Schema newInstance() {
     return new ZDPrivilegeSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDPrivilegeSet();
   }
 
   public ZDPrivilegeSet query() {
     return query(null, -1, -1);
   }
 
   public ZDPrivilegeSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDPrivilegeSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDPrivilegeSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDPrivilegeSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.OwnerType = ((String)v); return; }
     if (i == 1) { this.Owner = ((String)v); return; }
     if (i == 2) { this.PrivType = ((String)v); return; }
     if (i == 3) { this.ID = ((String)v); return; }
     if (i == 4) { this.Code = ((String)v); return; }
     if (i != 5) return; this.Value = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.OwnerType;
     if (i == 1) return this.Owner;
     if (i == 2) return this.PrivType;
     if (i == 3) return this.ID;
     if (i == 4) return this.Code;
     if (i == 5) return this.Value;
     return null;
   }
 
   public String getOwnerType()
   {
     return this.OwnerType;
   }
 
   public void setOwnerType(String ownerType)
   {
     this.OwnerType = ownerType;
   }
 
   public String getOwner()
   {
     return this.Owner;
   }
 
   public void setOwner(String owner)
   {
     this.Owner = owner;
   }
 
   public String getPrivType()
   {
     return this.PrivType;
   }
 
   public void setPrivType(String privType)
   {
     this.PrivType = privType;
   }
 
   public String getID()
   {
     return this.ID;
   }
 
   public void setID(String iD)
   {
     this.ID = iD;
   }
 
   public String getCode()
   {
     return this.Code;
   }
 
   public void setCode(String code)
   {
     this.Code = code;
   }
 
   public String getValue()
   {
     return this.Value;
   }
 
   public void setValue(String value)
   {
     this.Value = value;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDPrivilegeSchema
 * JD-Core Version:    0.5.3
 */