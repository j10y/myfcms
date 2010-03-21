 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDPrivilegeSet extends SchemaSet
 {
   public ZDPrivilegeSet()
   {
     this(10, 0);
   }
 
   public ZDPrivilegeSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDPrivilegeSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDPrivilege";
     this.Columns = ZDPrivilegeSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDPrivilege values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDPrivilege set OwnerType=?,Owner=?,PrivType=?,ID=?,Code=?,Value=? where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=?";
     this.FillAllSQL = "select * from ZDPrivilege  where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=?";
     this.DeleteSQL = "delete from ZDPrivilege  where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDPrivilegeSet();
   }
 
   public boolean add(ZDPrivilegeSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDPrivilegeSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDPrivilegeSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDPrivilegeSchema get(int index) {
     ZDPrivilegeSchema tSchema = (ZDPrivilegeSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDPrivilegeSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDPrivilegeSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDPrivilegeSet
 * JD-Core Version:    0.5.3
 */