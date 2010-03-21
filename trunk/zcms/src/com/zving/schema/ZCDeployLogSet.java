 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCDeployLogSet extends SchemaSet
 {
   public ZCDeployLogSet()
   {
     this(10, 0);
   }
 
   public ZCDeployLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCDeployLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCDeployLog";
     this.Columns = ZCDeployLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCDeployLog values(?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCDeployLog set ID=?,SiteID=?,JobID=?,Message=?,Memo=?,BeginTime=?,EndTime=? where ID=?";
     this.FillAllSQL = "select * from ZCDeployLog  where ID=?";
     this.DeleteSQL = "delete from ZCDeployLog  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCDeployLogSet();
   }
 
   public boolean add(ZCDeployLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCDeployLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCDeployLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCDeployLogSchema get(int index) {
     ZCDeployLogSchema tSchema = (ZCDeployLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCDeployLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCDeployLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCDeployLogSet
 * JD-Core Version:    0.5.3
 */