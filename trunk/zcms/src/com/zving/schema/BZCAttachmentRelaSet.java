 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCAttachmentRelaSet extends SchemaSet
 {
   public BZCAttachmentRelaSet()
   {
     this(10, 0);
   }
 
   public BZCAttachmentRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCAttachmentRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCAttachmentRela";
     this.Columns = BZCAttachmentRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCAttachmentRela values(?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAttachmentRela set ID=?,RelaID=?,RelaType=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and RelaID=? and RelaType=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAttachmentRela  where ID=? and RelaID=? and RelaType=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAttachmentRela  where ID=? and RelaID=? and RelaType=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCAttachmentRelaSet();
   }
 
   public boolean add(BZCAttachmentRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCAttachmentRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCAttachmentRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCAttachmentRelaSchema get(int index) {
     BZCAttachmentRelaSchema tSchema = (BZCAttachmentRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCAttachmentRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCAttachmentRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCAttachmentRelaSet
 * JD-Core Version:    0.5.3
 */