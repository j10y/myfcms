 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDConfigSet extends SchemaSet
 {
   public ZDConfigSet()
   {
     this(10, 0);
   }
 
   public ZDConfigSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDConfigSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDConfig";
     this.Columns = ZDConfigSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDConfig values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDConfig set Type=?,Name=?,Value=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where Type=?";
     this.FillAllSQL = "select * from ZDConfig  where Type=?";
     this.DeleteSQL = "delete from ZDConfig  where Type=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDConfigSet();
   }
 
   public boolean add(ZDConfigSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDConfigSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDConfigSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDConfigSchema get(int index) {
     ZDConfigSchema tSchema = (ZDConfigSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDConfigSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDConfigSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDConfigSet
 * JD-Core Version:    0.5.3
 */