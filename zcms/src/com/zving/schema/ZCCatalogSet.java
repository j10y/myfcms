 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCCatalogSet extends SchemaSet
 {
   public ZCCatalogSet()
   {
     this(10, 0);
   }
 
   public ZCCatalogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCCatalogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCCatalog";
     this.Columns = ZCCatalogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCCatalog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCCatalog set ID=?,ParentID=?,SiteID=?,Name=?,InnerCode=?,BranchInnerCode=?,Alias=?,URL=?,ImagePath=?,Type=?,IndexTemplate=?,ListTemplate=?,ListNameRule=?,DetailTemplate=?,DetailNameRule=?,RssTemplate=?,RssNameRule=?,Workflow=?,TreeLevel=?,ChildCount=?,IsLeaf=?,IsDirty=?,Total=?,OrderFlag=?,Logo=?,ListPageSize=?,PublishFlag=?,SingleFlag=?,HitCount=?,Meta_Keywords=?,Meta_Description=?,OrderColumn=?,Integral=?,KeywordFlag=?,KeywordSetting=?,AllowContribute=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCCatalog  where ID=?";
     this.DeleteSQL = "delete from ZCCatalog  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCCatalogSet();
   }
 
   public boolean add(ZCCatalogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCCatalogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCCatalogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCCatalogSchema get(int index) {
     ZCCatalogSchema tSchema = (ZCCatalogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCCatalogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCCatalogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCCatalogSet
 * JD-Core Version:    0.5.3
 */