 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCImageRelaSet extends SchemaSet
 {
   public ZCImageRelaSet()
   {
     this(10, 0);
   }
 
   public ZCImageRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCImageRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCImageRela";
     this.Columns = ZCImageRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCImageRela values(?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCImageRela set ID=?,RelaID=?,RelaType=?,OrderFlag=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=? and RelaID=? and RelaType=?";
     this.FillAllSQL = "select * from ZCImageRela  where ID=? and RelaID=? and RelaType=?";
     this.DeleteSQL = "delete from ZCImageRela  where ID=? and RelaID=? and RelaType=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCImageRelaSet();
   }
 
   public boolean add(ZCImageRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCImageRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCImageRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCImageRelaSchema get(int index) {
     ZCImageRelaSchema tSchema = (ZCImageRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCImageRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCImageRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCImageRelaSet
 * JD-Core Version:    0.5.3
 */