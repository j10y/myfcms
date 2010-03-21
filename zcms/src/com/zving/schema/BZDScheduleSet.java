 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDScheduleSet extends SchemaSet
 {
   public BZDScheduleSet()
   {
     this(10, 0);
   }
 
   public BZDScheduleSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDScheduleSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDSchedule";
     this.Columns = BZDScheduleSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDSchedule values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDSchedule set ID=?,SourceID=?,TypeCode=?,CronExpression=?,PlanType=?,StartTime=?,Description=?,IsUsing=?,OrderFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDSchedule  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDSchedule  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDScheduleSet();
   }
 
   public boolean add(BZDScheduleSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDScheduleSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDScheduleSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDScheduleSchema get(int index) {
     BZDScheduleSchema tSchema = (BZDScheduleSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDScheduleSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDScheduleSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDScheduleSet
 * JD-Core Version:    0.5.3
 */