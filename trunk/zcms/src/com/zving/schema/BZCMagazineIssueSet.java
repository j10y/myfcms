 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCMagazineIssueSet extends SchemaSet
 {
   public BZCMagazineIssueSet()
   {
     this(10, 0);
   }
 
   public BZCMagazineIssueSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCMagazineIssueSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCMagazineIssue";
     this.Columns = BZCMagazineIssueSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCMagazineIssue values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCMagazineIssue set ID=?,MagazineID=?,Year=?,PeriodNum=?,CoverImage=?,CoverTemplate=?,PublishDate=?,Status=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCMagazineIssue  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCMagazineIssue  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCMagazineIssueSet();
   }
 
   public boolean add(BZCMagazineIssueSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCMagazineIssueSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCMagazineIssueSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCMagazineIssueSchema get(int index) {
     BZCMagazineIssueSchema tSchema = (BZCMagazineIssueSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCMagazineIssueSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCMagazineIssueSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCMagazineIssueSet
 * JD-Core Version:    0.5.3
 */