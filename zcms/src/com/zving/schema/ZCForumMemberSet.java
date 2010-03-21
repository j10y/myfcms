 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCForumMemberSet extends SchemaSet
 {
   public ZCForumMemberSet()
   {
     this(10, 0);
   }
 
   public ZCForumMemberSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCForumMemberSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCForumMember";
     this.Columns = ZCForumMemberSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCForumMember values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCForumMember set UserName=?,SiteID=?,AdminID=?,UserGroupID=?,NickName=?,ThemeCount=?,ReplyCount=?,HeadImage=?,UseSelfImage=?,Status=?,ForumScore=?,ForumSign=?,LastLoginTime=?,LastLogoutTime=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where UserName=?";
     this.FillAllSQL = "select * from ZCForumMember  where UserName=?";
     this.DeleteSQL = "delete from ZCForumMember  where UserName=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCForumMemberSet();
   }
 
   public boolean add(ZCForumMemberSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCForumMemberSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCForumMemberSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCForumMemberSchema get(int index) {
     ZCForumMemberSchema tSchema = (ZCForumMemberSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCForumMemberSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCForumMemberSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCForumMemberSet
 * JD-Core Version:    0.5.3
 */