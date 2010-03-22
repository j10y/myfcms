 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDIPSet extends SchemaSet
 {
   public ZDIPSet()
   {
     this(10, 0);
   }
 
   public ZDIPSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDIPSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDIP";
     this.Columns = ZDIPSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDIP values(?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDIP set IP1=?,IP2=?,IP3=?,IP4=?,Address=?,Memo=?,DistrictCode=? where IP1=? and IP2=?";
     this.FillAllSQL = "select * from ZDIP  where IP1=? and IP2=?";
     this.DeleteSQL = "delete from ZDIP  where IP1=? and IP2=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDIPSet();
   }
 
   public boolean add(ZDIPSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDIPSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDIPSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDIPSchema get(int index) {
     ZDIPSchema tSchema = (ZDIPSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDIPSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDIPSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDIPSet
 * JD-Core Version:    0.5.3
 */