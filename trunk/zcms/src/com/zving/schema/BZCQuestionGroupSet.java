 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCQuestionGroupSet extends SchemaSet
 {
   public BZCQuestionGroupSet()
   {
     this(10, 0);
   }
 
   public BZCQuestionGroupSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCQuestionGroupSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCQuestionGroup";
     this.Columns = BZCQuestionGroupSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCQuestionGroup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCQuestionGroup set InnerCode=?,ParentInnerCode=?,Name=?,TreeLevel=?,IsLeaf=?,OrderFlag=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where InnerCode=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCQuestionGroup  where InnerCode=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCQuestionGroup  where InnerCode=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCQuestionGroupSet();
   }
 
   public boolean add(BZCQuestionGroupSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCQuestionGroupSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCQuestionGroupSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCQuestionGroupSchema get(int index) {
     BZCQuestionGroupSchema tSchema = (BZCQuestionGroupSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCQuestionGroupSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCQuestionGroupSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCQuestionGroupSet
 * JD-Core Version:    0.5.3
 */