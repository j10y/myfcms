 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCDeployConfigSet extends SchemaSet
 {
   public BZCDeployConfigSet()
   {
     this(10, 0);
   }
 
   public BZCDeployConfigSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCDeployConfigSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCDeployConfig";
     this.Columns = BZCDeployConfigSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCDeployConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCDeployConfig set ID=?,SiteID=?,SourceDir=?,TargetDir=?,Method=?,Host=?,Port=?,UserName=?,Password=?,UseFlag=?,BeginTime=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCDeployConfig  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCDeployConfig  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCDeployConfigSet();
   }
 
   public boolean add(BZCDeployConfigSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCDeployConfigSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCDeployConfigSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCDeployConfigSchema get(int index) {
     BZCDeployConfigSchema tSchema = (BZCDeployConfigSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCDeployConfigSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCDeployConfigSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCDeployConfigSet
 * JD-Core Version:    0.5.3
 */