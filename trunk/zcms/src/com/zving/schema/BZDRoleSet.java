 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDRoleSet extends SchemaSet
 {
   public BZDRoleSet()
   {
     this(10, 0);
   }
 
   public BZDRoleSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDRoleSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDRole";
     this.Columns = BZDRoleSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDRole values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDRole set RoleCode=?,RoleName=?,BranchInnerCode=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where RoleCode=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDRole  where RoleCode=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDRole  where RoleCode=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDRoleSet();
   }
 
   public boolean add(BZDRoleSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDRoleSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDRoleSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDRoleSchema get(int index) {
     BZDRoleSchema tSchema = (BZDRoleSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDRoleSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDRoleSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDRoleSet
 * JD-Core Version:    0.5.3
 */