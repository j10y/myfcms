 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCTagSet extends SchemaSet
 {
   public ZCTagSet()
   {
     this(10, 0);
   }
 
   public ZCTagSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCTagSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCTag";
     this.Columns = ZCTagSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCTag values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCTag set ID=?,Code=?,Name=?,SiteID=?,Type=?,TagText=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCTag  where ID=?";
     this.DeleteSQL = "delete from ZCTag  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCTagSet();
   }
 
   public boolean add(ZCTagSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCTagSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCTagSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCTagSchema get(int index) {
     ZCTagSchema tSchema = (ZCTagSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCTagSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCTagSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCTagSet
 * JD-Core Version:    0.5.3
 */