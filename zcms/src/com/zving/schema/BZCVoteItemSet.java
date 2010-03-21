 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCVoteItemSet extends SchemaSet
 {
   public BZCVoteItemSet()
   {
     this(10, 0);
   }
 
   public BZCVoteItemSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCVoteItemSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCVoteItem";
     this.Columns = BZCVoteItemSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCVoteItem values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCVoteItem set ID=?,SubjectID=?,VoteID=?,Item=?,Score=?,ItemType=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCVoteItem  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCVoteItem  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCVoteItemSet();
   }
 
   public boolean add(BZCVoteItemSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCVoteItemSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCVoteItemSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCVoteItemSchema get(int index) {
     BZCVoteItemSchema tSchema = (BZCVoteItemSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCVoteItemSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCVoteItemSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCVoteItemSet
 * JD-Core Version:    0.5.3
 */