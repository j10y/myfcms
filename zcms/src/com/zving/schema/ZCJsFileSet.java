 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCJsFileSet extends SchemaSet
 {
   public ZCJsFileSet()
   {
     this(10, 0);
   }
 
   public ZCJsFileSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCJsFileSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCJsFile";
     this.Columns = ZCJsFileSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCJsFile values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCJsFile set ID=?,SiteID=?,JsName=?,JsDesc=?,JsFileName=?,Param=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCJsFile  where ID=?";
     this.DeleteSQL = "delete from ZCJsFile  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCJsFileSet();
   }
 
   public boolean add(ZCJsFileSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCJsFileSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCJsFileSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCJsFileSchema get(int index) {
     ZCJsFileSchema tSchema = (ZCJsFileSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCJsFileSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCJsFileSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCJsFileSet
 * JD-Core Version:    0.5.3
 */