 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCAnswerCommentSet extends SchemaSet
 {
   public BZCAnswerCommentSet()
   {
     this(10, 0);
   }
 
   public BZCAnswerCommentSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCAnswerCommentSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCAnswerComment";
     this.Columns = BZCAnswerCommentSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCAnswerComment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAnswerComment set ID=?,QuestionID=?,Content=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAnswerComment  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAnswerComment  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCAnswerCommentSet();
   }
 
   public boolean add(BZCAnswerCommentSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCAnswerCommentSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCAnswerCommentSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCAnswerCommentSchema get(int index) {
     BZCAnswerCommentSchema tSchema = (BZCAnswerCommentSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCAnswerCommentSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCAnswerCommentSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCAnswerCommentSet
 * JD-Core Version:    0.5.3
 */