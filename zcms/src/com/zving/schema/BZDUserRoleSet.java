 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDUserRoleSet extends SchemaSet
 {
   public BZDUserRoleSet()
   {
     this(10, 0);
   }
 
   public BZDUserRoleSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDUserRoleSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDUserRole";
     this.Columns = BZDUserRoleSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDUserRole values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDUserRole set UserName=?,RoleCode=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and RoleCode=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDUserRole  where UserName=? and RoleCode=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDUserRole  where UserName=? and RoleCode=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDUserRoleSet();
   }
 
   public boolean add(BZDUserRoleSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDUserRoleSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDUserRoleSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDUserRoleSchema get(int index) {
     BZDUserRoleSchema tSchema = (BZDUserRoleSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDUserRoleSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDUserRoleSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDUserRoleSet
 * JD-Core Version:    0.5.3
 */