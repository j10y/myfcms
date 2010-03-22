 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCCatalogConfigSet extends SchemaSet
 {
   public ZCCatalogConfigSet()
   {
     this(10, 0);
   }
 
   public ZCCatalogConfigSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCCatalogConfigSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCCatalogConfig";
     this.Columns = ZCCatalogConfigSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCCatalogConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCCatalogConfig set ID=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,CronExpression=?,PlanType=?,StartTime=?,IsUsing=?,HotWordFlag=?,AllowStatus=?,AfterEditStatus=?,Encoding=?,ArchiveTime=?,AttachDownFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCCatalogConfig  where ID=?";
     this.DeleteSQL = "delete from ZCCatalogConfig  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCCatalogConfigSet();
   }
 
   public boolean add(ZCCatalogConfigSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCCatalogConfigSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCCatalogConfigSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCCatalogConfigSchema get(int index) {
     ZCCatalogConfigSchema tSchema = (ZCCatalogConfigSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCCatalogConfigSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCCatalogConfigSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCCatalogConfigSet
 * JD-Core Version:    0.5.3
 */