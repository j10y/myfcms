 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCImageRelaSet extends SchemaSet
 {
   public BZCImageRelaSet()
   {
     this(10, 0);
   }
 
   public BZCImageRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCImageRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCImageRela";
     this.Columns = BZCImageRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCImageRela values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCImageRela set ID=?,RelaID=?,RelaType=?,OrderFlag=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and RelaID=? and RelaType=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCImageRela  where ID=? and RelaID=? and RelaType=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCImageRela  where ID=? and RelaID=? and RelaType=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCImageRelaSet();
   }
 
   public boolean add(BZCImageRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCImageRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCImageRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCImageRelaSchema get(int index) {
     BZCImageRelaSchema tSchema = (BZCImageRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCImageRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCImageRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCImageRelaSet
 * JD-Core Version:    0.5.3
 */