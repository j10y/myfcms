 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCForumGroupSet extends SchemaSet
 {
   public BZCForumGroupSet()
   {
     this(10, 0);
   }
 
   public BZCForumGroupSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCForumGroupSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCForumGroup";
     this.Columns = BZCForumGroupSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCForumGroup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCForumGroup set ID=?,SiteID=?,RadminID=?,Name=?,SystemName=?,Type=?,Color=?,Image=?,LowerScore=?,UpperScore=?,AllowTheme=?,AllowReply=?,AllowSearch=?,AllowUserInfo=?,AllowPanel=?,AllowNickName=?,AllowVisit=?,AllowHeadImage=?,AllowFace=?,AllowAutograph=?,Verify=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,OrderFlag=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCForumGroup  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCForumGroup  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCForumGroupSet();
   }
 
   public boolean add(BZCForumGroupSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCForumGroupSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCForumGroupSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCForumGroupSchema get(int index) {
     BZCForumGroupSchema tSchema = (BZCForumGroupSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCForumGroupSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCForumGroupSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCForumGroupSet
 * JD-Core Version:    0.5.3
 */