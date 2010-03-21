 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDIPRangeSet extends SchemaSet
 {
   public ZDIPRangeSet()
   {
     this(10, 0);
   }
 
   public ZDIPRangeSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDIPRangeSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDIPRange";
     this.Columns = ZDIPRangeSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDIPRange values(?,?)";
     this.UpdateAllSQL = "update ZDIPRange set DistrictCode=?,IPRanges=? where DistrictCode=?";
     this.FillAllSQL = "select * from ZDIPRange  where DistrictCode=?";
     this.DeleteSQL = "delete from ZDIPRange  where DistrictCode=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDIPRangeSet();
   }
 
   public boolean add(ZDIPRangeSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDIPRangeSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDIPRangeSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDIPRangeSchema get(int index) {
     ZDIPRangeSchema tSchema = (ZDIPRangeSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDIPRangeSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDIPRangeSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDIPRangeSet
 * JD-Core Version:    0.5.3
 */