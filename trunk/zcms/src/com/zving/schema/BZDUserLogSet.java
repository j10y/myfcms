 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDUserLogSet extends SchemaSet
 {
   public BZDUserLogSet()
   {
     this(10, 0);
   }
 
   public BZDUserLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDUserLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDUserLog";
     this.Columns = BZDUserLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDUserLog values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDUserLog set UserName=?,LogID=?,IP=?,LogType=?,SubType=?,LogMessage=?,Memo=?,AddTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and LogID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDUserLog  where UserName=? and LogID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDUserLog  where UserName=? and LogID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDUserLogSet();
   }
 
   public boolean add(BZDUserLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDUserLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDUserLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDUserLogSchema get(int index) {
     BZDUserLogSchema tSchema = (BZDUserLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDUserLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDUserLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDUserLogSet
 * JD-Core Version:    0.5.3
 */