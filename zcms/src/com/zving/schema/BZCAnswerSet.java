 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCAnswerSet extends SchemaSet
 {
   public BZCAnswerSet()
   {
     this(10, 0);
   }
 
   public BZCAnswerSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCAnswerSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCAnswer";
     this.Columns = BZCAnswerSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCAnswer values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAnswer set ID=?,QuestionID=?,Content=?,IsBest=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAnswer  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAnswer  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCAnswerSet();
   }
 
   public boolean add(BZCAnswerSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCAnswerSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCAnswerSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCAnswerSchema get(int index) {
     BZCAnswerSchema tSchema = (BZCAnswerSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCAnswerSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCAnswerSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCAnswerSet
 * JD-Core Version:    0.5.3
 */