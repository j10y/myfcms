 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCAttachmentSet extends SchemaSet
 {
   public BZCAttachmentSet()
   {
     this(10, 0);
   }
 
   public BZCAttachmentSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCAttachmentSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCAttachment";
     this.Columns = BZCAttachmentSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCAttachment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAttachment set ID=?,Name=?,OldName=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Path=?,FileName=?,SrcFileName=?,Suffix=?,Info=?,FileSize=?,System=?,PublishDate=?,Integral=?,IsLocked=?,Password=?,SourceURL=?,OrderFlag=?,ImagePath=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAttachment  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAttachment  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCAttachmentSet();
   }
 
   public boolean add(BZCAttachmentSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCAttachmentSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCAttachmentSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCAttachmentSchema get(int index) {
     BZCAttachmentSchema tSchema = (BZCAttachmentSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCAttachmentSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCAttachmentSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCAttachmentSet
 * JD-Core Version:    0.5.3
 */