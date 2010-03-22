 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDMaxNoSet extends SchemaSet
 {
   public BZDMaxNoSet()
   {
     this(10, 0);
   }
 
   public BZDMaxNoSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDMaxNoSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDMaxNo";
     this.Columns = BZDMaxNoSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDMaxNo values(?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDMaxNo set NoType=?,NoSubType=?,MaxValue=?,Length=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where NoType=? and NoSubType=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDMaxNo  where NoType=? and NoSubType=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDMaxNo  where NoType=? and NoSubType=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDMaxNoSet();
   }
 
   public boolean add(BZDMaxNoSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDMaxNoSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDMaxNoSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDMaxNoSchema get(int index) {
     BZDMaxNoSchema tSchema = (BZDMaxNoSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDMaxNoSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDMaxNoSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDMaxNoSet
 * JD-Core Version:    0.5.3
 */