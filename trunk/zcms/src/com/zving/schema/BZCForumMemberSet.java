 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCForumMemberSet extends SchemaSet
 {
   public BZCForumMemberSet()
   {
     this(10, 0);
   }
 
   public BZCForumMemberSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCForumMemberSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCForumMember";
     this.Columns = BZCForumMemberSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCForumMember values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCForumMember set UserName=?,SiteID=?,AdminID=?,UserGroupID=?,NickName=?,ThemeCount=?,ReplyCount=?,HeadImage=?,UseSelfImage=?,Status=?,ForumScore=?,ForumSign=?,LastLoginTime=?,LastLogoutTime=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCForumMember  where UserName=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCForumMember  where UserName=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCForumMemberSet();
   }
 
   public boolean add(BZCForumMemberSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCForumMemberSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCForumMemberSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCForumMemberSchema get(int index) {
     BZCForumMemberSchema tSchema = (BZCForumMemberSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCForumMemberSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCForumMemberSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCForumMemberSet
 * JD-Core Version:    0.5.3
 */