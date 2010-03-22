 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCAttachmentSet extends SchemaSet
 {
   public ZCAttachmentSet()
   {
     this(10, 0);
   }
 
   public ZCAttachmentSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCAttachmentSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCAttachment";
     this.Columns = ZCAttachmentSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCAttachment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAttachment set ID=?,Name=?,OldName=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Path=?,FileName=?,SrcFileName=?,Suffix=?,Info=?,FileSize=?,System=?,PublishDate=?,Integral=?,IsLocked=?,Password=?,SourceURL=?,OrderFlag=?,ImagePath=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCAttachment  where ID=?";
     this.DeleteSQL = "delete from ZCAttachment  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCAttachmentSet();
   }
 
   public boolean add(ZCAttachmentSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCAttachmentSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCAttachmentSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCAttachmentSchema get(int index) {
     ZCAttachmentSchema tSchema = (ZCAttachmentSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCAttachmentSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCAttachmentSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCAttachmentSet
 * JD-Core Version:    0.5.3
 */