 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDUserRoleSet extends SchemaSet
 {
   public ZDUserRoleSet()
   {
     this(10, 0);
   }
 
   public ZDUserRoleSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDUserRoleSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDUserRole";
     this.Columns = ZDUserRoleSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDUserRole values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDUserRole set UserName=?,RoleCode=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where UserName=? and RoleCode=?";
     this.FillAllSQL = "select * from ZDUserRole  where UserName=? and RoleCode=?";
     this.DeleteSQL = "delete from ZDUserRole  where UserName=? and RoleCode=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDUserRoleSet();
   }
 
   public boolean add(ZDUserRoleSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDUserRoleSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDUserRoleSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDUserRoleSchema get(int index) {
     ZDUserRoleSchema tSchema = (ZDUserRoleSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDUserRoleSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDUserRoleSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDUserRoleSet
 * JD-Core Version:    0.5.3
 */