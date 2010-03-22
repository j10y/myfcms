 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZWHistoryStepSet extends SchemaSet
 {
   public BZWHistoryStepSet()
   {
     this(10, 0);
   }
 
   public BZWHistoryStepSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZWHistoryStepSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZWHistoryStep";
     this.Columns = BZWHistoryStepSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZWHistoryStep values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZWHistoryStep set ID=?,EntryID=?,StepID=?,ActionID=?,Owner=?,StartDate=?,FinishDate=?,DueDate=?,Status=?,Caller=?,Memo=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZWHistoryStep  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZWHistoryStep  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZWHistoryStepSet();
   }
 
   public boolean add(BZWHistoryStepSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZWHistoryStepSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZWHistoryStepSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZWHistoryStepSchema get(int index) {
     BZWHistoryStepSchema tSchema = (BZWHistoryStepSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZWHistoryStepSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZWHistoryStepSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZWHistoryStepSet
 * JD-Core Version:    0.5.3
 */