 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCFullTextSet extends SchemaSet
 {
   public ZCFullTextSet()
   {
     this(10, 0);
   }
 
   public ZCFullTextSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCFullTextSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCFullText";
     this.Columns = ZCFullTextSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCFullText values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCFullText set ID=?,SiteID=?,Code=?,Name=?,Type=?,Memo=?,RelaText=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCFullText  where ID=?";
     this.DeleteSQL = "delete from ZCFullText  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCFullTextSet();
   }
 
   public boolean add(ZCFullTextSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCFullTextSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCFullTextSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCFullTextSchema get(int index) {
     ZCFullTextSchema tSchema = (ZCFullTextSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCFullTextSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCFullTextSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCFullTextSet
 * JD-Core Version:    0.5.3
 */