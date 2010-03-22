 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDColumnValueSet extends SchemaSet
 {
   public ZDColumnValueSet()
   {
     this(10, 0);
   }
 
   public ZDColumnValueSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDColumnValueSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDColumnValue";
     this.Columns = ZDColumnValueSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDColumnValue values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDColumnValue set ID=?,ColumnID=?,ColumnCode=?,TextValue=?,RelaType=?,RelaID=? where ID=?";
     this.FillAllSQL = "select * from ZDColumnValue  where ID=?";
     this.DeleteSQL = "delete from ZDColumnValue  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDColumnValueSet();
   }
 
   public boolean add(ZDColumnValueSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDColumnValueSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDColumnValueSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDColumnValueSchema get(int index) {
     ZDColumnValueSchema tSchema = (ZDColumnValueSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDColumnValueSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDColumnValueSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDColumnValueSet
 * JD-Core Version:    0.5.3
 */