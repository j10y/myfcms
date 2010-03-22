 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCAudioSet extends SchemaSet
 {
   public ZCAudioSet()
   {
     this(10, 0);
   }
 
   public ZCAudioSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCAudioSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCAudio";
     this.Columns = ZCAudioSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCAudio values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAudio set ID=?,Name=?,OldName=?,SiteID=?,Tag=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Path=?,FileName=?,SrcFileName=?,Suffix=?,IsOriginal=?,Info=?,System=?,FileSize=?,PublishDate=?,Duration=?,ProduceTime=?,Author=?,Integral=?,SourceURL=?,OrderFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCAudio  where ID=?";
     this.DeleteSQL = "delete from ZCAudio  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCAudioSet();
   }
 
   public boolean add(ZCAudioSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCAudioSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCAudioSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCAudioSchema get(int index) {
     ZCAudioSchema tSchema = (ZCAudioSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCAudioSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCAudioSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCAudioSet
 * JD-Core Version:    0.5.3
 */