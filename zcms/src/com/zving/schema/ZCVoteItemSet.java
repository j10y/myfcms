 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCVoteItemSet extends SchemaSet
 {
   public ZCVoteItemSet()
   {
     this(10, 0);
   }
 
   public ZCVoteItemSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCVoteItemSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCVoteItem";
     this.Columns = ZCVoteItemSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCVoteItem values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCVoteItem set ID=?,SubjectID=?,VoteID=?,Item=?,Score=?,ItemType=? where ID=?";
     this.FillAllSQL = "select * from ZCVoteItem  where ID=?";
     this.DeleteSQL = "delete from ZCVoteItem  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCVoteItemSet();
   }
 
   public boolean add(ZCVoteItemSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCVoteItemSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCVoteItemSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCVoteItemSchema get(int index) {
     ZCVoteItemSchema tSchema = (ZCVoteItemSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCVoteItemSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCVoteItemSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCVoteItemSet
 * JD-Core Version:    0.5.3
 */