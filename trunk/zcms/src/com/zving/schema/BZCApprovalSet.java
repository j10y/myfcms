 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCApprovalSet extends SchemaSet
 {
   public BZCApprovalSet()
   {
     this(10, 0);
   }
 
   public BZCApprovalSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCApprovalSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCApproval";
     this.Columns = BZCApprovalSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCApproval values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCApproval set ID=?,ApproveUser=?,ArticleID=?,Memo=?,Status=?,ApprovalDate=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCApproval  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCApproval  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCApprovalSet();
   }
 
   public boolean add(BZCApprovalSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCApprovalSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCApprovalSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCApprovalSchema get(int index) {
     BZCApprovalSchema tSchema = (BZCApprovalSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCApprovalSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCApprovalSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCApprovalSet
 * JD-Core Version:    0.5.3
 */