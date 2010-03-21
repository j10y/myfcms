 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCVoteSet extends SchemaSet
 {
   public BZCVoteSet()
   {
     this(10, 0);
   }
 
   public BZCVoteSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCVoteSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCVote";
     this.Columns = BZCVoteSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCVote values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCVote set ID=?,Code=?,SiteID=?,Title=?,Total=?,StartTime=?,EndTime=?,IPLimit=?,VerifyFlag=?,Width=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCVote  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCVote  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCVoteSet();
   }
 
   public boolean add(BZCVoteSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCVoteSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCVoteSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCVoteSchema get(int index) {
     BZCVoteSchema tSchema = (BZCVoteSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCVoteSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCVoteSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCVoteSet
 * JD-Core Version:    0.5.3
 */