 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDColumnRelaSet extends SchemaSet
 {
   public ZDColumnRelaSet()
   {
     this(10, 0);
   }
 
   public ZDColumnRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDColumnRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDColumnRela";
     this.Columns = ZDColumnRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDColumnRela values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDColumnRela set ID=?,ColumnID=?,ColumnCode=?,RelaType=?,RelaID=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZDColumnRela  where ID=?";
     this.DeleteSQL = "delete from ZDColumnRela  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDColumnRelaSet();
   }
 
   public boolean add(ZDColumnRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDColumnRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDColumnRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDColumnRelaSchema get(int index) {
     ZDColumnRelaSchema tSchema = (ZDColumnRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDColumnRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDColumnRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDColumnRelaSet
 * JD-Core Version:    0.5.3
 */