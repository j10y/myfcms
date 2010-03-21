 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCBadWordSet extends SchemaSet
 {
   public ZCBadWordSet()
   {
     this(10, 0);
   }
 
   public ZCBadWordSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCBadWordSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCBadWord";
     this.Columns = ZCBadWordSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCBadWord values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCBadWord set ID=?,TreeLevel=?,Word=?,ReplaceWord=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCBadWord  where ID=?";
     this.DeleteSQL = "delete from ZCBadWord  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCBadWordSet();
   }
 
   public boolean add(ZCBadWordSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCBadWordSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCBadWordSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCBadWordSchema get(int index) {
     ZCBadWordSchema tSchema = (ZCBadWordSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCBadWordSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCBadWordSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCBadWordSet
 * JD-Core Version:    0.5.3
 */