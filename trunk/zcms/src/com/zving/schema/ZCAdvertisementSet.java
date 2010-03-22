 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCAdvertisementSet extends SchemaSet
 {
   public ZCAdvertisementSet()
   {
     this(10, 0);
   }
 
   public ZCAdvertisementSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCAdvertisementSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCAdvertisement";
     this.Columns = ZCAdvertisementSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCAdvertisement values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAdvertisement set ID=?,PositionID=?,PositionCode=?,SiteID=?,AdName=?,AdType=?,AdContent=?,OrderFlag=?,IsHitCount=?,HitCount=?,StickTime=?,IsOpen=?,StartTime=?,EndTime=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCAdvertisement  where ID=?";
     this.DeleteSQL = "delete from ZCAdvertisement  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCAdvertisementSet();
   }
 
   public boolean add(ZCAdvertisementSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCAdvertisementSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCAdvertisementSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCAdvertisementSchema get(int index) {
     ZCAdvertisementSchema tSchema = (ZCAdvertisementSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCAdvertisementSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCAdvertisementSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCAdvertisementSet
 * JD-Core Version:    0.5.3
 */