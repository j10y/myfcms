 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCAudioRelaSet extends SchemaSet
 {
   public ZCAudioRelaSet()
   {
     this(10, 0);
   }
 
   public ZCAudioRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCAudioRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCAudioRela";
     this.Columns = ZCAudioRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCAudioRela values(?,?,?)";
     this.UpdateAllSQL = "update ZCAudioRela set ID=?,RelaID=?,RelaType=? where ID=? and RelaID=? and RelaType=?";
     this.FillAllSQL = "select * from ZCAudioRela  where ID=? and RelaID=? and RelaType=?";
     this.DeleteSQL = "delete from ZCAudioRela  where ID=? and RelaID=? and RelaType=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCAudioRelaSet();
   }
 
   public boolean add(ZCAudioRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCAudioRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCAudioRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCAudioRelaSchema get(int index) {
     ZCAudioRelaSchema tSchema = (ZCAudioRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCAudioRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCAudioRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCAudioRelaSet
 * JD-Core Version:    0.5.3
 */