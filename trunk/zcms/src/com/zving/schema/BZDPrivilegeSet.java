 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDPrivilegeSet extends SchemaSet
 {
   public BZDPrivilegeSet()
   {
     this(10, 0);
   }
 
   public BZDPrivilegeSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDPrivilegeSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDPrivilege";
     this.Columns = BZDPrivilegeSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDPrivilege values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDPrivilege set OwnerType=?,Owner=?,PrivType=?,ID=?,Code=?,Value=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDPrivilege  where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDPrivilege  where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDPrivilegeSet();
   }
 
   public boolean add(BZDPrivilegeSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDPrivilegeSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDPrivilegeSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDPrivilegeSchema get(int index) {
     BZDPrivilegeSchema tSchema = (BZDPrivilegeSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDPrivilegeSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDPrivilegeSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDPrivilegeSet
 * JD-Core Version:    0.5.3
 */