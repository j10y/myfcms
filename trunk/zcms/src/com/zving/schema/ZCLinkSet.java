 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCLinkSet extends SchemaSet
 {
   public ZCLinkSet()
   {
     this(10, 0);
   }
 
   public ZCLinkSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCLinkSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCLink";
     this.Columns = ZCLinkSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCLink values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCLink set ID=?,SiteID=?,LinkGroupID=?,Name=?,URL=?,ImagePath=?,OrderFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCLink  where ID=?";
     this.DeleteSQL = "delete from ZCLink  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCLinkSet();
   }
 
   public boolean add(ZCLinkSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCLinkSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCLinkSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCLinkSchema get(int index) {
     ZCLinkSchema tSchema = (ZCLinkSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCLinkSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCLinkSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCLinkSet
 * JD-Core Version:    0.5.3
 */