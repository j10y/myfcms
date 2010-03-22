 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZWHistoryStepSet extends SchemaSet
 {
   public ZWHistoryStepSet()
   {
     this(10, 0);
   }
 
   public ZWHistoryStepSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZWHistoryStepSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZWHistoryStep";
     this.Columns = ZWHistoryStepSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZWHistoryStep values(?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZWHistoryStep set ID=?,EntryID=?,StepID=?,ActionID=?,Owner=?,StartDate=?,FinishDate=?,DueDate=?,Status=?,Caller=?,Memo=? where ID=?";
     this.FillAllSQL = "select * from ZWHistoryStep  where ID=?";
     this.DeleteSQL = "delete from ZWHistoryStep  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZWHistoryStepSet();
   }
 
   public boolean add(ZWHistoryStepSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZWHistoryStepSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZWHistoryStepSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZWHistoryStepSchema get(int index) {
     ZWHistoryStepSchema tSchema = (ZWHistoryStepSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZWHistoryStepSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZWHistoryStepSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZWHistoryStepSet
 * JD-Core Version:    0.5.3
 */