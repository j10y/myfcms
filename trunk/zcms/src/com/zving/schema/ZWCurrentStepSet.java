 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZWCurrentStepSet extends SchemaSet
 {
   public ZWCurrentStepSet()
   {
     this(10, 0);
   }
 
   public ZWCurrentStepSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZWCurrentStepSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZWCurrentStep";
     this.Columns = ZWCurrentStepSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZWCurrentStep values(?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZWCurrentStep set ID=?,EntryID=?,StepID=?,ActionID=?,Owner=?,StartDate=?,FinishDate=?,DueDate=?,Status=?,Caller=?,Memo=? where ID=?";
     this.FillAllSQL = "select * from ZWCurrentStep  where ID=?";
     this.DeleteSQL = "delete from ZWCurrentStep  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZWCurrentStepSet();
   }
 
   public boolean add(ZWCurrentStepSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZWCurrentStepSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZWCurrentStepSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZWCurrentStepSchema get(int index) {
     ZWCurrentStepSchema tSchema = (ZWCurrentStepSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZWCurrentStepSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZWCurrentStepSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZWCurrentStepSet
 * JD-Core Version:    0.5.3
 */