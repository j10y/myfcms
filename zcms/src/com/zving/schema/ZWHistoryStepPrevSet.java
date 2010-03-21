 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZWHistoryStepPrevSet extends SchemaSet
 {
   public ZWHistoryStepPrevSet()
   {
     this(10, 0);
   }
 
   public ZWHistoryStepPrevSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZWHistoryStepPrevSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZWHistoryStepPrev";
     this.Columns = ZWHistoryStepPrevSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZWHistoryStepPrev values(?,?)";
     this.UpdateAllSQL = "update ZWHistoryStepPrev set ID=?,PreviousID=? where ID=? and PreviousID=?";
     this.FillAllSQL = "select * from ZWHistoryStepPrev  where ID=? and PreviousID=?";
     this.DeleteSQL = "delete from ZWHistoryStepPrev  where ID=? and PreviousID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZWHistoryStepPrevSet();
   }
 
   public boolean add(ZWHistoryStepPrevSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZWHistoryStepPrevSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZWHistoryStepPrevSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZWHistoryStepPrevSchema get(int index) {
     ZWHistoryStepPrevSchema tSchema = (ZWHistoryStepPrevSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZWHistoryStepPrevSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZWHistoryStepPrevSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZWHistoryStepPrevSet
 * JD-Core Version:    0.5.3
 */