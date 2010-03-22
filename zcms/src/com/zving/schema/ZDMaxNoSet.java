 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDMaxNoSet extends SchemaSet
 {
   public ZDMaxNoSet()
   {
     this(10, 0);
   }
 
   public ZDMaxNoSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDMaxNoSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDMaxNo";
     this.Columns = ZDMaxNoSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDMaxNo values(?,?,?,?)";
     this.UpdateAllSQL = "update ZDMaxNo set NoType=?,NoSubType=?,MaxValue=?,Length=? where NoType=? and NoSubType=?";
     this.FillAllSQL = "select * from ZDMaxNo  where NoType=? and NoSubType=?";
     this.DeleteSQL = "delete from ZDMaxNo  where NoType=? and NoSubType=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDMaxNoSet();
   }
 
   public boolean add(ZDMaxNoSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDMaxNoSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDMaxNoSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDMaxNoSchema get(int index) {
     ZDMaxNoSchema tSchema = (ZDMaxNoSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDMaxNoSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDMaxNoSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDMaxNoSet
 * JD-Core Version:    0.5.3
 */