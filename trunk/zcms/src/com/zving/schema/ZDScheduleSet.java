 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDScheduleSet extends SchemaSet
 {
   public ZDScheduleSet()
   {
     this(10, 0);
   }
 
   public ZDScheduleSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDScheduleSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDSchedule";
     this.Columns = ZDScheduleSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDSchedule values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDSchedule set ID=?,SourceID=?,TypeCode=?,CronExpression=?,PlanType=?,StartTime=?,Description=?,IsUsing=?,OrderFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZDSchedule  where ID=?";
     this.DeleteSQL = "delete from ZDSchedule  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDScheduleSet();
   }
 
   public boolean add(ZDScheduleSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDScheduleSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDScheduleSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDScheduleSchema get(int index) {
     ZDScheduleSchema tSchema = (ZDScheduleSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDScheduleSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDScheduleSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDScheduleSet
 * JD-Core Version:    0.5.3
 */