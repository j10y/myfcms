 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZWHistoryStepPrevSet extends SchemaSet
 {
   public BZWHistoryStepPrevSet()
   {
     this(10, 0);
   }
 
   public BZWHistoryStepPrevSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZWHistoryStepPrevSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZWHistoryStepPrev";
     this.Columns = BZWHistoryStepPrevSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZWHistoryStepPrev values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZWHistoryStepPrev set ID=?,PreviousID=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and PreviousID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZWHistoryStepPrev  where ID=? and PreviousID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZWHistoryStepPrev  where ID=? and PreviousID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZWHistoryStepPrevSet();
   }
 
   public boolean add(BZWHistoryStepPrevSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZWHistoryStepPrevSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZWHistoryStepPrevSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZWHistoryStepPrevSchema get(int index) {
     BZWHistoryStepPrevSchema tSchema = (BZWHistoryStepPrevSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZWHistoryStepPrevSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZWHistoryStepPrevSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZWHistoryStepPrevSet
 * JD-Core Version:    0.5.3
 */