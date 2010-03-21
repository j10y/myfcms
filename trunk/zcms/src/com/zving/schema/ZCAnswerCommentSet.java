 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCAnswerCommentSet extends SchemaSet
 {
   public ZCAnswerCommentSet()
   {
     this(10, 0);
   }
 
   public ZCAnswerCommentSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCAnswerCommentSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCAnswerComment";
     this.Columns = ZCAnswerCommentSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCAnswerComment values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAnswerComment set ID=?,QuestionID=?,Content=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
     this.FillAllSQL = "select * from ZCAnswerComment  where ID=?";
     this.DeleteSQL = "delete from ZCAnswerComment  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCAnswerCommentSet();
   }
 
   public boolean add(ZCAnswerCommentSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCAnswerCommentSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCAnswerCommentSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCAnswerCommentSchema get(int index) {
     ZCAnswerCommentSchema tSchema = (ZCAnswerCommentSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCAnswerCommentSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCAnswerCommentSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCAnswerCommentSet
 * JD-Core Version:    0.5.3
 */