 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCAudioRelaSet extends SchemaSet
 {
   public BZCAudioRelaSet()
   {
     this(10, 0);
   }
 
   public BZCAudioRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCAudioRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCAudioRela";
     this.Columns = BZCAudioRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCAudioRela values(?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAudioRela set ID=?,RelaID=?,RelaType=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and RelaID=? and RelaType=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAudioRela  where ID=? and RelaID=? and RelaType=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAudioRela  where ID=? and RelaID=? and RelaType=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCAudioRelaSet();
   }
 
   public boolean add(BZCAudioRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCAudioRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCAudioRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCAudioRelaSchema get(int index) {
     BZCAudioRelaSchema tSchema = (BZCAudioRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCAudioRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCAudioRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCAudioRelaSet
 * JD-Core Version:    0.5.3
 */