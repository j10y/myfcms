 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCCatalogConfigSet extends SchemaSet
 {
   public BZCCatalogConfigSet()
   {
     this(10, 0);
   }
 
   public BZCCatalogConfigSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCCatalogConfigSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCCatalogConfig";
     this.Columns = BZCCatalogConfigSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCCatalogConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCCatalogConfig set ID=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,CronExpression=?,PlanType=?,StartTime=?,IsUsing=?,HotWordFlag=?,AllowStatus=?,AfterEditStatus=?,Encoding=?,ArchiveTime=?,AttachDownFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCCatalogConfig  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCCatalogConfig  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCCatalogConfigSet();
   }
 
   public boolean add(BZCCatalogConfigSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCCatalogConfigSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCCatalogConfigSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCCatalogConfigSchema get(int index) {
     BZCCatalogConfigSchema tSchema = (BZCCatalogConfigSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCCatalogConfigSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCCatalogConfigSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCCatalogConfigSet
 * JD-Core Version:    0.5.3
 */