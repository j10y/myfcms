 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCForumAttachmentSet extends SchemaSet
 {
   public BZCForumAttachmentSet()
   {
     this(10, 0);
   }
 
   public BZCForumAttachmentSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCForumAttachmentSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCForumAttachment";
     this.Columns = BZCForumAttachmentSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCForumAttachment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCForumAttachment set ID=?,PostID=?,SiteID=?,Name=?,Path=?,Type=?,Suffix=?,AttSize=?,DownCount=?,Note=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCForumAttachment  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCForumAttachment  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCForumAttachmentSet();
   }
 
   public boolean add(BZCForumAttachmentSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCForumAttachmentSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCForumAttachmentSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCForumAttachmentSchema get(int index) {
     BZCForumAttachmentSchema tSchema = (BZCForumAttachmentSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCForumAttachmentSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCForumAttachmentSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCForumAttachmentSet
 * JD-Core Version:    0.5.3
 */