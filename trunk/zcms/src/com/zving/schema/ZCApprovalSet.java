 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCApprovalSet extends SchemaSet
 {
   public ZCApprovalSet()
   {
     this(10, 0);
   }
 
   public ZCApprovalSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCApprovalSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCApproval";
     this.Columns = ZCApprovalSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCApproval values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCApproval set ID=?,ApproveUser=?,ArticleID=?,Memo=?,Status=?,ApprovalDate=? where ID=?";
     this.FillAllSQL = "select * from ZCApproval  where ID=?";
     this.DeleteSQL = "delete from ZCApproval  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCApprovalSet();
   }
 
   public boolean add(ZCApprovalSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCApprovalSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCApprovalSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCApprovalSchema get(int index) {
     ZCApprovalSchema tSchema = (ZCApprovalSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCApprovalSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCApprovalSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCApprovalSet
 * JD-Core Version:    0.5.3
 */