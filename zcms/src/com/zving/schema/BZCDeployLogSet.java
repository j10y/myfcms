 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCDeployLogSet extends SchemaSet
 {
   public BZCDeployLogSet()
   {
     this(10, 0);
   }
 
   public BZCDeployLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCDeployLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCDeployLog";
     this.Columns = BZCDeployLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCDeployLog values(?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCDeployLog set ID=?,SiteID=?,JobID=?,Message=?,Memo=?,BeginTime=?,EndTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCDeployLog  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCDeployLog  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCDeployLogSet();
   }
 
   public boolean add(BZCDeployLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCDeployLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCDeployLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCDeployLogSchema get(int index) {
     BZCDeployLogSchema tSchema = (BZCDeployLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCDeployLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCDeployLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCDeployLogSet
 * JD-Core Version:    0.5.3
 */