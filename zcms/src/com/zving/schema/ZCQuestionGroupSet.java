 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCQuestionGroupSet extends SchemaSet
 {
   public ZCQuestionGroupSet()
   {
     this(10, 0);
   }
 
   public ZCQuestionGroupSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCQuestionGroupSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCQuestionGroup";
     this.Columns = ZCQuestionGroupSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCQuestionGroup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCQuestionGroup set InnerCode=?,ParentInnerCode=?,Name=?,TreeLevel=?,IsLeaf=?,OrderFlag=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where InnerCode=?";
     this.FillAllSQL = "select * from ZCQuestionGroup  where InnerCode=?";
     this.DeleteSQL = "delete from ZCQuestionGroup  where InnerCode=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCQuestionGroupSet();
   }
 
   public boolean add(ZCQuestionGroupSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCQuestionGroupSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCQuestionGroupSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCQuestionGroupSchema get(int index) {
     ZCQuestionGroupSchema tSchema = (ZCQuestionGroupSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCQuestionGroupSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCQuestionGroupSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCQuestionGroupSet
 * JD-Core Version:    0.5.3
 */