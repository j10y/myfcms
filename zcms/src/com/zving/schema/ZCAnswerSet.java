 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCAnswerSet extends SchemaSet
 {
   public ZCAnswerSet()
   {
     this(10, 0);
   }
 
   public ZCAnswerSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCAnswerSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCAnswer";
     this.Columns = ZCAnswerSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCAnswer values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAnswer set ID=?,QuestionID=?,Content=?,IsBest=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
     this.FillAllSQL = "select * from ZCAnswer  where ID=?";
     this.DeleteSQL = "delete from ZCAnswer  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCAnswerSet();
   }
 
   public boolean add(ZCAnswerSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCAnswerSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCAnswerSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCAnswerSchema get(int index) {
     ZCAnswerSchema tSchema = (ZCAnswerSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCAnswerSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCAnswerSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCAnswerSet
 * JD-Core Version:    0.5.3
 */