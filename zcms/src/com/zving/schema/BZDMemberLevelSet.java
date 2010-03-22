 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDMemberLevelSet extends SchemaSet
 {
   public BZDMemberLevelSet()
   {
     this(10, 0);
   }
 
   public BZDMemberLevelSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDMemberLevelSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDMemberLevel";
     this.Columns = BZDMemberLevelSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDMemberLevel values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDMemberLevel set ID=?,Name=?,Type=?,Discount=?,IsDefault=?,TreeLevel=?,Score=?,IsValidate=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDMemberLevel  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDMemberLevel  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDMemberLevelSet();
   }
 
   public boolean add(BZDMemberLevelSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDMemberLevelSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDMemberLevelSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDMemberLevelSchema get(int index) {
     BZDMemberLevelSchema tSchema = (BZDMemberLevelSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDMemberLevelSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDMemberLevelSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDMemberLevelSet
 * JD-Core Version:    0.5.3
 */