 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCVoteSubjectSet extends SchemaSet
 {
   public BZCVoteSubjectSet()
   {
     this(10, 0);
   }
 
   public BZCVoteSubjectSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCVoteSubjectSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCVoteSubject";
     this.Columns = BZCVoteSubjectSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCVoteSubject values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCVoteSubject set ID=?,VoteID=?,Type=?,Subject=?,Prop1=?,Prop2=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCVoteSubject  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCVoteSubject  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCVoteSubjectSet();
   }
 
   public boolean add(BZCVoteSubjectSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCVoteSubjectSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCVoteSubjectSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCVoteSubjectSchema get(int index) {
     BZCVoteSubjectSchema tSchema = (BZCVoteSubjectSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCVoteSubjectSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCVoteSubjectSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCVoteSubjectSet
 * JD-Core Version:    0.5.3
 */