 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCAdvertisementSet extends SchemaSet
 {
   public BZCAdvertisementSet()
   {
     this(10, 0);
   }
 
   public BZCAdvertisementSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCAdvertisementSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCAdvertisement";
     this.Columns = BZCAdvertisementSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCAdvertisement values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAdvertisement set ID=?,PositionID=?,PositionCode=?,SiteID=?,AdName=?,AdType=?,AdContent=?,OrderFlag=?,IsHitCount=?,HitCount=?,StickTime=?,IsOpen=?,StartTime=?,EndTime=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAdvertisement  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAdvertisement  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCAdvertisementSet();
   }
 
   public boolean add(BZCAdvertisementSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCAdvertisementSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCAdvertisementSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCAdvertisementSchema get(int index) {
     BZCAdvertisementSchema tSchema = (BZCAdvertisementSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCAdvertisementSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCAdvertisementSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCAdvertisementSet
 * JD-Core Version:    0.5.3
 */