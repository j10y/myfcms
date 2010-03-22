 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCDeployConfigSet extends SchemaSet
 {
   public ZCDeployConfigSet()
   {
     this(10, 0);
   }
 
   public ZCDeployConfigSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCDeployConfigSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCDeployConfig";
     this.Columns = ZCDeployConfigSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCDeployConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCDeployConfig set ID=?,SiteID=?,SourceDir=?,TargetDir=?,Method=?,Host=?,Port=?,UserName=?,Password=?,UseFlag=?,BeginTime=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCDeployConfig  where ID=?";
     this.DeleteSQL = "delete from ZCDeployConfig  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCDeployConfigSet();
   }
 
   public boolean add(ZCDeployConfigSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCDeployConfigSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCDeployConfigSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCDeployConfigSchema get(int index) {
     ZCDeployConfigSchema tSchema = (ZCDeployConfigSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCDeployConfigSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCDeployConfigSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCDeployConfigSet
 * JD-Core Version:    0.5.3
 */