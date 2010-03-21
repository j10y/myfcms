 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCQuestionSet extends SchemaSet
 {
   public ZCQuestionSet()
   {
     this(10, 0);
   }
 
   public ZCQuestionSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCQuestionSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCQuestion";
     this.Columns = ZCQuestionSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCQuestion values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCQuestion set ID=?,QuestionInnerCode=?,Title=?,Content=?,ReplyCount=?,Status=?,IsCommend=?,EndTime=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
     this.FillAllSQL = "select * from ZCQuestion  where ID=?";
     this.DeleteSQL = "delete from ZCQuestion  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCQuestionSet();
   }
 
   public boolean add(ZCQuestionSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCQuestionSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCQuestionSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCQuestionSchema get(int index) {
     ZCQuestionSchema tSchema = (ZCQuestionSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCQuestionSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCQuestionSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCQuestionSet
 * JD-Core Version:    0.5.3
 */