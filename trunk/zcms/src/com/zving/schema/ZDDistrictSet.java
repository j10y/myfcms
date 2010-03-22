 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDDistrictSet extends SchemaSet
 {
   public ZDDistrictSet()
   {
     this(10, 0);
   }
 
   public ZDDistrictSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDDistrictSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDDistrict";
     this.Columns = ZDDistrictSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDDistrict values(?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDDistrict set Code=?,Name=?,CodeOrder=?,TreeLevel=?,Type=? where Code=?";
     this.FillAllSQL = "select * from ZDDistrict  where Code=?";
     this.DeleteSQL = "delete from ZDDistrict  where Code=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDDistrictSet();
   }
 
   public boolean add(ZDDistrictSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDDistrictSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDDistrictSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDDistrictSchema get(int index) {
     ZDDistrictSchema tSchema = (ZDDistrictSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDDistrictSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDDistrictSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDDistrictSet
 * JD-Core Version:    0.5.3
 */