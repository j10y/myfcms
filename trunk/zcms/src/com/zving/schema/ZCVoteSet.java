 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCVoteSet extends SchemaSet
 {
   public ZCVoteSet()
   {
     this(10, 0);
   }
 
   public ZCVoteSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCVoteSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCVote";
     this.Columns = ZCVoteSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCVote values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCVote set ID=?,Code=?,SiteID=?,Title=?,Total=?,StartTime=?,EndTime=?,IPLimit=?,VerifyFlag=?,Width=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCVote  where ID=?";
     this.DeleteSQL = "delete from ZCVote  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCVoteSet();
   }
 
   public boolean add(ZCVoteSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCVoteSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCVoteSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCVoteSchema get(int index) {
     ZCVoteSchema tSchema = (ZCVoteSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCVoteSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCVoteSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCVoteSet
 * JD-Core Version:    0.5.3
 */