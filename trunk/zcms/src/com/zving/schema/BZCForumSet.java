 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCForumSet extends SchemaSet
 {
   public BZCForumSet()
   {
     this(10, 0);
   }
 
   public BZCForumSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCForumSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCForum";
     this.Columns = BZCForumSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCForum values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCForum set ID=?,SiteID=?,ParentID=?,Type=?,Name=?,Status=?,Pic=?,Visible=?,Info=?,ThemeCount=?,Verify=?,Locked=?,AllowTheme=?,EditPost=?,ReplyPost=?,Recycle=?,AllowHTML=?,AllowFace=?,AnonyPost=?,URL=?,Image=?,Password=?,Word=?,PostCount=?,ForumAdmin=?,TodayPostCount=?,LastThemeID=?,LastPost=?,LastPoster=?,OrderFlag=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCForum  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCForum  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCForumSet();
   }
 
   public boolean add(BZCForumSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCForumSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCForumSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCForumSchema get(int index) {
     BZCForumSchema tSchema = (BZCForumSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCForumSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCForumSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCForumSet
 * JD-Core Version:    0.5.3
 */