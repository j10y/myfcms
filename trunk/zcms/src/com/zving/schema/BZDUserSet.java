 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDUserSet extends SchemaSet
 {
   public BZDUserSet()
   {
     this(10, 0);
   }
 
   public BZDUserSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDUserSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDUser";
     this.Columns = BZDUserSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDUser values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDUser set UserName=?,RealName=?,Password=?,BranchInnerCode=?,IsBranchAdmin=?,Status=?,Type=?,Email=?,Tel=?,Mobile=?,Prop1=?,Prop2=?,Prop6=?,Prop5=?,Prop4=?,Prop3=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDUser  where UserName=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDUser  where UserName=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDUserSet();
   }
 
   public boolean add(BZDUserSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDUserSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDUserSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDUserSchema get(int index) {
     BZDUserSchema tSchema = (BZDUserSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDUserSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDUserSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDUserSet
 * JD-Core Version:    0.5.3
 */