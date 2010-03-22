 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCVoteLogSet extends SchemaSet
 {
   public BZCVoteLogSet()
   {
     this(10, 0);
   }
 
   public BZCVoteLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCVoteLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCVoteLog";
     this.Columns = BZCVoteLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCVoteLog values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCVoteLog set ID=?,VoteID=?,IP=?,Result=?,Prop1=?,Prop2=?,addUser=?,addTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCVoteLog  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCVoteLog  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCVoteLogSet();
   }
 
   public boolean add(BZCVoteLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCVoteLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCVoteLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCVoteLogSchema get(int index) {
     BZCVoteLogSchema tSchema = (BZCVoteLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCVoteLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCVoteLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCVoteLogSet
 * JD-Core Version:    0.5.3
 */