 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCTemplateBlockRelaSet extends SchemaSet
 {
   public BZCTemplateBlockRelaSet()
   {
     this(10, 0);
   }
 
   public BZCTemplateBlockRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCTemplateBlockRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCTemplateBlockRela";
     this.Columns = BZCTemplateBlockRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCTemplateBlockRela values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCTemplateBlockRela set SiteID=?,FileName=?,BlockID=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where SiteID=? and FileName=? and BlockID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCTemplateBlockRela  where SiteID=? and FileName=? and BlockID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCTemplateBlockRela  where SiteID=? and FileName=? and BlockID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCTemplateBlockRelaSet();
   }
 
   public boolean add(BZCTemplateBlockRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCTemplateBlockRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCTemplateBlockRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCTemplateBlockRelaSchema get(int index) {
     BZCTemplateBlockRelaSchema tSchema = (BZCTemplateBlockRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCTemplateBlockRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCTemplateBlockRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCTemplateBlockRelaSet
 * JD-Core Version:    0.5.3
 */