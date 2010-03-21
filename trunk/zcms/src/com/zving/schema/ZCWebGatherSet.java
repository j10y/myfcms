 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCWebGatherSet extends SchemaSet
 {
   public ZCWebGatherSet()
   {
     this(10, 0);
   }
 
   public ZCWebGatherSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCWebGatherSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCWebGather";
     this.Columns = ZCWebGatherSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCWebGather values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCWebGather set ID=?,SiteID=?,Name=?,Intro=?,Type=?,ConfigXML=?,ProxyFlag=?,ProxyHost=?,ProxyPort=?,ProxyUserName=?,ProxyPassword=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCWebGather  where ID=?";
     this.DeleteSQL = "delete from ZCWebGather  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCWebGatherSet();
   }
 
   public boolean add(ZCWebGatherSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCWebGatherSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCWebGatherSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCWebGatherSchema get(int index) {
     ZCWebGatherSchema tSchema = (ZCWebGatherSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCWebGatherSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCWebGatherSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCWebGatherSet
 * JD-Core Version:    0.5.3
 */