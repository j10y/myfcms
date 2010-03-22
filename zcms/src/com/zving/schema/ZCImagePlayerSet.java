 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCImagePlayerSet extends SchemaSet
 {
   public ZCImagePlayerSet()
   {
     this(10, 0);
   }
 
   public ZCImagePlayerSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCImagePlayerSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCImagePlayer";
     this.Columns = ZCImagePlayerSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCImagePlayer values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCImagePlayer set ID=?,Name=?,Code=?,SiteID=?,DisplayType=?,ImageSource=?,Height=?,Width=?,DisplayCount=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCImagePlayer  where ID=?";
     this.DeleteSQL = "delete from ZCImagePlayer  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCImagePlayerSet();
   }
 
   public boolean add(ZCImagePlayerSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCImagePlayerSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCImagePlayerSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCImagePlayerSchema get(int index) {
     ZCImagePlayerSchema tSchema = (ZCImagePlayerSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCImagePlayerSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCImagePlayerSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCImagePlayerSet
 * JD-Core Version:    0.5.3
 */