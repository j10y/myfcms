 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCVoteSubjectSet extends SchemaSet
 {
   public ZCVoteSubjectSet()
   {
     this(10, 0);
   }
 
   public ZCVoteSubjectSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCVoteSubjectSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCVoteSubject";
     this.Columns = ZCVoteSubjectSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCVoteSubject values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCVoteSubject set ID=?,VoteID=?,Type=?,Subject=?,Prop1=?,Prop2=? where ID=?";
     this.FillAllSQL = "select * from ZCVoteSubject  where ID=?";
     this.DeleteSQL = "delete from ZCVoteSubject  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCVoteSubjectSet();
   }
 
   public boolean add(ZCVoteSubjectSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCVoteSubjectSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCVoteSubjectSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCVoteSubjectSchema get(int index) {
     ZCVoteSubjectSchema tSchema = (ZCVoteSubjectSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCVoteSubjectSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCVoteSubjectSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCVoteSubjectSet
 * JD-Core Version:    0.5.3
 */