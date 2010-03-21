 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCCatalogSet extends SchemaSet
 {
   public BZCCatalogSet()
   {
     this(10, 0);
   }
 
   public BZCCatalogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCCatalogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCCatalog";
     this.Columns = BZCCatalogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCCatalog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCCatalog set ID=?,ParentID=?,SiteID=?,Name=?,InnerCode=?,BranchInnerCode=?,Alias=?,URL=?,ImagePath=?,Type=?,IndexTemplate=?,ListTemplate=?,ListNameRule=?,DetailTemplate=?,DetailNameRule=?,RssTemplate=?,RssNameRule=?,Workflow=?,TreeLevel=?,ChildCount=?,IsLeaf=?,IsDirty=?,Total=?,OrderFlag=?,Logo=?,ListPageSize=?,PublishFlag=?,SingleFlag=?,HitCount=?,Meta_Keywords=?,Meta_Description=?,OrderColumn=?,Integral=?,KeywordFlag=?,KeywordSetting=?,AllowContribute=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCCatalog  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCCatalog  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCCatalogSet();
   }
 
   public boolean add(BZCCatalogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCCatalogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCCatalogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCCatalogSchema get(int index) {
     BZCCatalogSchema tSchema = (BZCCatalogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCCatalogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCCatalogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCCatalogSet
 * JD-Core Version:    0.5.3
 */