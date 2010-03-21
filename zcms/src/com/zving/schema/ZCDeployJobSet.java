 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCDeployJobSet extends SchemaSet
 {
   public ZCDeployJobSet()
   {
     this(10, 0);
   }
 
   public ZCDeployJobSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCDeployJobSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCDeployJob";
     this.Columns = ZCDeployJobSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCDeployJob values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCDeployJob set ID=?,ConfigID=?,SiteID=?,Source=?,Target=?,Method=?,Host=?,Port=?,UserName=?,Password=?,Status=?,RetryCount=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCDeployJob  where ID=?";
     this.DeleteSQL = "delete from ZCDeployJob  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCDeployJobSet();
   }
 
   public boolean add(ZCDeployJobSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCDeployJobSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCDeployJobSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCDeployJobSchema get(int index) {
     ZCDeployJobSchema tSchema = (ZCDeployJobSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCDeployJobSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCDeployJobSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCDeployJobSet
 * JD-Core Version:    0.5.3
 */