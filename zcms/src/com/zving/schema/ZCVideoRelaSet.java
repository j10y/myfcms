 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCVideoRelaSet extends SchemaSet
 {
   public ZCVideoRelaSet()
   {
     this(10, 0);
   }
 
   public ZCVideoRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCVideoRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCVideoRela";
     this.Columns = ZCVideoRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCVideoRela values(?,?,?)";
     this.UpdateAllSQL = "update ZCVideoRela set ID=?,RelaID=?,RelaType=? where ID=? and RelaID=? and RelaType=?";
     this.FillAllSQL = "select * from ZCVideoRela  where ID=? and RelaID=? and RelaType=?";
     this.DeleteSQL = "delete from ZCVideoRela  where ID=? and RelaID=? and RelaType=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCVideoRelaSet();
   }
 
   public boolean add(ZCVideoRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCVideoRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCVideoRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCVideoRelaSchema get(int index) {
     ZCVideoRelaSchema tSchema = (ZCVideoRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCVideoRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCVideoRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCVideoRelaSet
 * JD-Core Version:    0.5.3
 */