 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCDeployJobSet extends SchemaSet
 {
   public BZCDeployJobSet()
   {
     this(10, 0);
   }
 
   public BZCDeployJobSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCDeployJobSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCDeployJob";
     this.Columns = BZCDeployJobSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCDeployJob values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCDeployJob set ID=?,ConfigID=?,SiteID=?,Source=?,Target=?,Method=?,Host=?,Port=?,UserName=?,Password=?,Status=?,RetryCount=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCDeployJob  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCDeployJob  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCDeployJobSet();
   }
 
   public boolean add(BZCDeployJobSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCDeployJobSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCDeployJobSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCDeployJobSchema get(int index) {
     BZCDeployJobSchema tSchema = (BZCDeployJobSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCDeployJobSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCDeployJobSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCDeployJobSet
 * JD-Core Version:    0.5.3
 */