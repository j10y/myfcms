 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZWCurrentStepPrevSet extends SchemaSet
 {
   public ZWCurrentStepPrevSet()
   {
     this(10, 0);
   }
 
   public ZWCurrentStepPrevSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZWCurrentStepPrevSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZWCurrentStepPrev";
     this.Columns = ZWCurrentStepPrevSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZWCurrentStepPrev values(?,?)";
     this.UpdateAllSQL = "update ZWCurrentStepPrev set ID=?,PreviousID=? where ID=? and PreviousID=?";
     this.FillAllSQL = "select * from ZWCurrentStepPrev  where ID=? and PreviousID=?";
     this.DeleteSQL = "delete from ZWCurrentStepPrev  where ID=? and PreviousID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZWCurrentStepPrevSet();
   }
 
   public boolean add(ZWCurrentStepPrevSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZWCurrentStepPrevSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZWCurrentStepPrevSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZWCurrentStepPrevSchema get(int index) {
     ZWCurrentStepPrevSchema tSchema = (ZWCurrentStepPrevSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZWCurrentStepPrevSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZWCurrentStepPrevSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZWCurrentStepPrevSet
 * JD-Core Version:    0.5.3
 */