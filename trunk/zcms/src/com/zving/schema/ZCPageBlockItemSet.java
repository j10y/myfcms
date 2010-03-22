 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCPageBlockItemSet extends SchemaSet
 {
   public ZCPageBlockItemSet()
   {
     this(10, 0);
   }
 
   public ZCPageBlockItemSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCPageBlockItemSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCPageBlockItem";
     this.Columns = ZCPageBlockItemSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCPageBlockItem values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCPageBlockItem set ID=?,BlockID=?,Title=?,URL=?,Image=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCPageBlockItem  where ID=?";
     this.DeleteSQL = "delete from ZCPageBlockItem  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCPageBlockItemSet();
   }
 
   public boolean add(ZCPageBlockItemSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCPageBlockItemSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCPageBlockItemSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCPageBlockItemSchema get(int index) {
     ZCPageBlockItemSchema tSchema = (ZCPageBlockItemSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCPageBlockItemSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCPageBlockItemSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCPageBlockItemSet
 * JD-Core Version:    0.5.3
 */