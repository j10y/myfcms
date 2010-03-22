 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCDatabaseSet extends SchemaSet
 {
   public ZCDatabaseSet()
   {
     this(10, 0);
   }
 
   public ZCDatabaseSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCDatabaseSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCDatabase";
     this.Columns = ZCDatabaseSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCDatabase values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCDatabase set ID=?,SiteID=?,Name=?,ServerType=?,Address=?,Port=?,UserName=?,Password=?,DBName=?,TestTable=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCDatabase  where ID=?";
     this.DeleteSQL = "delete from ZCDatabase  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCDatabaseSet();
   }
 
   public boolean add(ZCDatabaseSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCDatabaseSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCDatabaseSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCDatabaseSchema get(int index) {
     ZCDatabaseSchema tSchema = (ZCDatabaseSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCDatabaseSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCDatabaseSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCDatabaseSet
 * JD-Core Version:    0.5.3
 */