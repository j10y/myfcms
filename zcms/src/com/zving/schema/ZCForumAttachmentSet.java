 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCForumAttachmentSet extends SchemaSet
 {
   public ZCForumAttachmentSet()
   {
     this(10, 0);
   }
 
   public ZCForumAttachmentSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCForumAttachmentSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCForumAttachment";
     this.Columns = ZCForumAttachmentSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCForumAttachment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCForumAttachment set ID=?,PostID=?,SiteID=?,Name=?,Path=?,Type=?,Suffix=?,AttSize=?,DownCount=?,Note=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCForumAttachment  where ID=?";
     this.DeleteSQL = "delete from ZCForumAttachment  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCForumAttachmentSet();
   }
 
   public boolean add(ZCForumAttachmentSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCForumAttachmentSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCForumAttachmentSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCForumAttachmentSchema get(int index) {
     ZCForumAttachmentSchema tSchema = (ZCForumAttachmentSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCForumAttachmentSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCForumAttachmentSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCForumAttachmentSet
 * JD-Core Version:    0.5.3
 */