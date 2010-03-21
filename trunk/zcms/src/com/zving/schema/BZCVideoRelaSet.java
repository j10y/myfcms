 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCVideoRelaSet extends SchemaSet
 {
   public BZCVideoRelaSet()
   {
     this(10, 0);
   }
 
   public BZCVideoRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCVideoRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCVideoRela";
     this.Columns = BZCVideoRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCVideoRela values(?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCVideoRela set ID=?,RelaID=?,RelaType=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and RelaID=? and RelaType=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCVideoRela  where ID=? and RelaID=? and RelaType=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCVideoRela  where ID=? and RelaID=? and RelaType=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCVideoRelaSet();
   }
 
   public boolean add(BZCVideoRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCVideoRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCVideoRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCVideoRelaSchema get(int index) {
     BZCVideoRelaSchema tSchema = (BZCVideoRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCVideoRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCVideoRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCVideoRelaSet
 * JD-Core Version:    0.5.3
 */