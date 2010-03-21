 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCForumSet extends SchemaSet
 {
   public ZCForumSet()
   {
     this(10, 0);
   }
 
   public ZCForumSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCForumSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCForum";
     this.Columns = ZCForumSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCForum values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCForum set ID=?,SiteID=?,ParentID=?,Type=?,Name=?,Status=?,Pic=?,Visible=?,Info=?,ThemeCount=?,Verify=?,Locked=?,AllowTheme=?,EditPost=?,ReplyPost=?,Recycle=?,AllowHTML=?,AllowFace=?,AnonyPost=?,URL=?,Image=?,Password=?,Word=?,PostCount=?,ForumAdmin=?,TodayPostCount=?,LastThemeID=?,LastPost=?,LastPoster=?,OrderFlag=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCForum  where ID=?";
     this.DeleteSQL = "delete from ZCForum  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCForumSet();
   }
 
   public boolean add(ZCForumSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCForumSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCForumSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCForumSchema get(int index) {
     ZCForumSchema tSchema = (ZCForumSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCForumSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCForumSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCForumSet
 * JD-Core Version:    0.5.3
 */