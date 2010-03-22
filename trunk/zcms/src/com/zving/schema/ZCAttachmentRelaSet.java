 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCAttachmentRelaSet extends SchemaSet
 {
   public ZCAttachmentRelaSet()
   {
     this(10, 0);
   }
 
   public ZCAttachmentRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCAttachmentRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCAttachmentRela";
     this.Columns = ZCAttachmentRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCAttachmentRela values(?,?,?)";
     this.UpdateAllSQL = "update ZCAttachmentRela set ID=?,RelaID=?,RelaType=? where ID=? and RelaID=? and RelaType=?";
     this.FillAllSQL = "select * from ZCAttachmentRela  where ID=? and RelaID=? and RelaType=?";
     this.DeleteSQL = "delete from ZCAttachmentRela  where ID=? and RelaID=? and RelaType=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCAttachmentRelaSet();
   }
 
   public boolean add(ZCAttachmentRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCAttachmentRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCAttachmentRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCAttachmentRelaSchema get(int index) {
     ZCAttachmentRelaSchema tSchema = (ZCAttachmentRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCAttachmentRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCAttachmentRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCAttachmentRelaSet
 * JD-Core Version:    0.5.3
 */