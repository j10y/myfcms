 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCQuestionSet extends SchemaSet
 {
   public BZCQuestionSet()
   {
     this(10, 0);
   }
 
   public BZCQuestionSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCQuestionSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCQuestion";
     this.Columns = BZCQuestionSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCQuestion values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCQuestion set ID=?,QuestionInnerCode=?,Title=?,Content=?,ReplyCount=?,Status=?,IsCommend=?,EndTime=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCQuestion  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCQuestion  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCQuestionSet();
   }
 
   public boolean add(BZCQuestionSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCQuestionSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCQuestionSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCQuestionSchema get(int index) {
     BZCQuestionSchema tSchema = (BZCQuestionSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCQuestionSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCQuestionSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCQuestionSet
 * JD-Core Version:    0.5.3
 */