 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCPaperIssueSet extends SchemaSet
 {
   public BZCPaperIssueSet()
   {
     this(10, 0);
   }
 
   public BZCPaperIssueSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCPaperIssueSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCPaperIssue";
     this.Columns = BZCPaperIssueSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCPaperIssue values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCPaperIssue set ID=?,PaperID=?,Year=?,PeriodNum=?,CoverImage=?,CoverTemplate=?,PublishDate=?,Status=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCPaperIssue  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCPaperIssue  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCPaperIssueSet();
   }
 
   public boolean add(BZCPaperIssueSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCPaperIssueSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCPaperIssueSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCPaperIssueSchema get(int index) {
     BZCPaperIssueSchema tSchema = (BZCPaperIssueSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCPaperIssueSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCPaperIssueSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCPaperIssueSet
 * JD-Core Version:    0.5.3
 */