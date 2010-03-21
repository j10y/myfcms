 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZWCurrentStepSet extends SchemaSet
 {
   public BZWCurrentStepSet()
   {
     this(10, 0);
   }
 
   public BZWCurrentStepSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZWCurrentStepSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZWCurrentStep";
     this.Columns = BZWCurrentStepSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZWCurrentStep values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZWCurrentStep set ID=?,EntryID=?,StepID=?,ActionID=?,Owner=?,StartDate=?,FinishDate=?,DueDate=?,Status=?,Caller=?,Memo=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZWCurrentStep  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZWCurrentStep  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZWCurrentStepSet();
   }
 
   public boolean add(BZWCurrentStepSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZWCurrentStepSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZWCurrentStepSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZWCurrentStepSchema get(int index) {
     BZWCurrentStepSchema tSchema = (BZWCurrentStepSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZWCurrentStepSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZWCurrentStepSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZWCurrentStepSet
 * JD-Core Version:    0.5.3
 */