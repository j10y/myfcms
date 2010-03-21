 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDHelpItemSet extends SchemaSet
 {
   public ZDHelpItemSet()
   {
     this(10, 0);
   }
 
   public ZDHelpItemSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDHelpItemSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDHelpItem";
     this.Columns = ZDHelpItemSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDHelpItem values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDHelpItem set ID=?,Code=?,Name=?,Type=?,Content=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
     this.FillAllSQL = "select * from ZDHelpItem  where ID=?";
     this.DeleteSQL = "delete from ZDHelpItem  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDHelpItemSet();
   }
 
   public boolean add(ZDHelpItemSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDHelpItemSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDHelpItemSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDHelpItemSchema get(int index) {
     ZDHelpItemSchema tSchema = (ZDHelpItemSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDHelpItemSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDHelpItemSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDHelpItemSet
 * JD-Core Version:    0.5.3
 */