 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCForumScoreSet extends SchemaSet
 {
   public BZCForumScoreSet()
   {
     this(10, 0);
   }
 
   public BZCForumScoreSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCForumScoreSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCForumScore";
     this.Columns = BZCForumScoreSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCForumScore values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCForumScore set ID=?,SiteID=?,InitScore=?,PublishTheme=?,DeleteTheme=?,PublishPost=?,DeletePost=?,Best=?,CancelBest=?,Bright=?,CancelBright=?,TopTheme=?,CancelTop=?,UpTheme=?,DownTheme=?,Upload=?,Download=?,Search=?,Vote=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCForumScore  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCForumScore  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCForumScoreSet();
   }
 
   public boolean add(BZCForumScoreSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCForumScoreSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCForumScoreSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCForumScoreSchema get(int index) {
     BZCForumScoreSchema tSchema = (BZCForumScoreSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCForumScoreSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCForumScoreSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCForumScoreSet
 * JD-Core Version:    0.5.3
 */