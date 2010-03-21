 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDRoleSet extends SchemaSet
 {
   public ZDRoleSet()
   {
     this(10, 0);
   }
 
   public ZDRoleSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDRoleSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDRole";
     this.Columns = ZDRoleSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDRole values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDRole set RoleCode=?,RoleName=?,BranchInnerCode=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where RoleCode=?";
     this.FillAllSQL = "select * from ZDRole  where RoleCode=?";
     this.DeleteSQL = "delete from ZDRole  where RoleCode=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDRoleSet();
   }
 
   public boolean add(ZDRoleSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDRoleSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDRoleSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDRoleSchema get(int index) {
     ZDRoleSchema tSchema = (ZDRoleSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDRoleSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDRoleSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDRoleSet
 * JD-Core Version:    0.5.3
 */