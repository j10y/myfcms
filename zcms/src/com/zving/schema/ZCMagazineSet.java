 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCMagazineSet extends SchemaSet
 {
   public ZCMagazineSet()
   {
     this(10, 0);
   }
 
   public ZCMagazineSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCMagazineSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCMagazine";
     this.Columns = ZCMagazineSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCMagazine values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCMagazine set ID=?,SiteID=?,Name=?,Alias=?,CoverImage=?,CoverTemplate=?,OpenFlag=?,Period=?,Total=?,CurrentYear=?,CurrentPeriodNum=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCMagazine  where ID=?";
     this.DeleteSQL = "delete from ZCMagazine  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCMagazineSet();
   }
 
   public boolean add(ZCMagazineSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCMagazineSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCMagazineSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCMagazineSchema get(int index) {
     ZCMagazineSchema tSchema = (ZCMagazineSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCMagazineSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCMagazineSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCMagazineSet
 * JD-Core Version:    0.5.3
 */