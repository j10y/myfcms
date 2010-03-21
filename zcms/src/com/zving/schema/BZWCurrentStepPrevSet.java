 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZWCurrentStepPrevSet extends SchemaSet
 {
   public BZWCurrentStepPrevSet()
   {
     this(10, 0);
   }
 
   public BZWCurrentStepPrevSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZWCurrentStepPrevSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZWCurrentStepPrev";
     this.Columns = BZWCurrentStepPrevSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZWCurrentStepPrev values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZWCurrentStepPrev set ID=?,PreviousID=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and PreviousID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZWCurrentStepPrev  where ID=? and PreviousID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZWCurrentStepPrev  where ID=? and PreviousID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZWCurrentStepPrevSet();
   }
 
   public boolean add(BZWCurrentStepPrevSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZWCurrentStepPrevSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZWCurrentStepPrevSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZWCurrentStepPrevSchema get(int index) {
     BZWCurrentStepPrevSchema tSchema = (BZWCurrentStepPrevSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZWCurrentStepPrevSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZWCurrentStepPrevSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZWCurrentStepPrevSet
 * JD-Core Version:    0.5.3
 */