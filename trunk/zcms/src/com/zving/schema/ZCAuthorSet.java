 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCAuthorSet extends SchemaSet
 {
   public ZCAuthorSet()
   {
     this(10, 0);
   }
 
   public ZCAuthorSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCAuthorSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCAuthor";
     this.Columns = ZCAuthorSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCAuthor values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAuthor set ID=?,Author=?,RealName=?,Sex=?,Email=?,Tel=?,Mobile=?,Address=?,Zipcode=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCAuthor  where ID=?";
     this.DeleteSQL = "delete from ZCAuthor  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCAuthorSet();
   }
 
   public boolean add(ZCAuthorSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCAuthorSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCAuthorSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCAuthorSchema get(int index) {
     ZCAuthorSchema tSchema = (ZCAuthorSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCAuthorSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCAuthorSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCAuthorSet
 * JD-Core Version:    0.5.3
 */