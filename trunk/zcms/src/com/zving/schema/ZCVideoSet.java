 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCVideoSet extends SchemaSet
 {
   public ZCVideoSet()
   {
     this(10, 0);
   }
 
   public ZCVideoSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCVideoSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCVideo";
     this.Columns = ZCVideoSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCVideo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCVideo set ID=?,Name=?,OldName=?,SiteID=?,Tag=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Path=?,FileName=?,SrcFileName=?,Suffix=?,IsOriginal=?,Info=?,System=?,FileSize=?,PublishDate=?,ImageName=?,Count=?,Width=?,Height=?,Duration=?,ProduceTime=?,Author=?,Integral=?,OrderFlag=?,HitCount=?,StickTime=?,Status=?,SourceURL=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCVideo  where ID=?";
     this.DeleteSQL = "delete from ZCVideo  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCVideoSet();
   }
 
   public boolean add(ZCVideoSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCVideoSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCVideoSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCVideoSchema get(int index) {
     ZCVideoSchema tSchema = (ZCVideoSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCVideoSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCVideoSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCVideoSet
 * JD-Core Version:    0.5.3
 */