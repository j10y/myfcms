 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCVoteLogSet extends SchemaSet
 {
   public ZCVoteLogSet()
   {
     this(10, 0);
   }
 
   public ZCVoteLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCVoteLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCVoteLog";
     this.Columns = ZCVoteLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCVoteLog values(?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCVoteLog set ID=?,VoteID=?,IP=?,Result=?,Prop1=?,Prop2=?,addUser=?,addTime=? where ID=?";
     this.FillAllSQL = "select * from ZCVoteLog  where ID=?";
     this.DeleteSQL = "delete from ZCVoteLog  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCVoteLogSet();
   }
 
   public boolean add(ZCVoteLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCVoteLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCVoteLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCVoteLogSchema get(int index) {
     ZCVoteLogSchema tSchema = (ZCVoteLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCVoteLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCVoteLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCVoteLogSet
 * JD-Core Version:    0.5.3
 */