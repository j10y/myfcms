 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCImageSet extends SchemaSet
 {
   public ZCImageSet()
   {
     this(10, 0);
   }
 
   public ZCImageSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCImageSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCImage";
     this.Columns = ZCImageSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCImage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCImage set ID=?,Name=?,OldName=?,Tag=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Path=?,FileName=?,SrcFileName=?,Suffix=?,Width=?,Height=?,Count=?,Info=?,IsOriginal=?,FileSize=?,System=?,LinkURL=?,LinkText=?,ProduceTime=?,Author=?,PublishDate=?,Integral=?,OrderFlag=?,HitCount=?,StickTime=?,SourceURL=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCImage  where ID=?";
     this.DeleteSQL = "delete from ZCImage  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCImageSet();
   }
 
   public boolean add(ZCImageSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCImageSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCImageSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCImageSchema get(int index) {
     ZCImageSchema tSchema = (ZCImageSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCImageSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCImageSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCImageSet
 * JD-Core Version:    0.5.3
 */