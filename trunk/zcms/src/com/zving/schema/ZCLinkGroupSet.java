 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCLinkGroupSet extends SchemaSet
 {
   public ZCLinkGroupSet()
   {
     this(10, 0);
   }
 
   public ZCLinkGroupSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCLinkGroupSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCLinkGroup";
     this.Columns = ZCLinkGroupSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCLinkGroup values(?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCLinkGroup set ID=?,Name=?,OrderFlag=?,SiteID=?,Type=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCLinkGroup  where ID=?";
     this.DeleteSQL = "delete from ZCLinkGroup  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCLinkGroupSet();
   }
 
   public boolean add(ZCLinkGroupSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCLinkGroupSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCLinkGroupSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCLinkGroupSchema get(int index) {
     ZCLinkGroupSchema tSchema = (ZCLinkGroupSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCLinkGroupSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCLinkGroupSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCLinkGroupSet
 * JD-Core Version:    0.5.3
 */