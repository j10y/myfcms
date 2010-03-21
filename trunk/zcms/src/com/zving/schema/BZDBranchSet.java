 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDBranchSet extends SchemaSet
 {
   public BZDBranchSet()
   {
     this(10, 0);
   }
 
   public BZDBranchSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDBranchSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDBranch";
     this.Columns = BZDBranchSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDBranch values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDBranch set BranchInnerCode=?,BranchCode=?,ParentInnerCode=?,Type=?,OrderFlag=?,Name=?,TreeLevel=?,IsLeaf=?,Phone=?,Fax=?,Manager=?,Leader1=?,Leader2=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where BranchInnerCode=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDBranch  where BranchInnerCode=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDBranch  where BranchInnerCode=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDBranchSet();
   }
 
   public boolean add(BZDBranchSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDBranchSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDBranchSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDBranchSchema get(int index) {
     BZDBranchSchema tSchema = (BZDBranchSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDBranchSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDBranchSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDBranchSet
 * JD-Core Version:    0.5.3
 */