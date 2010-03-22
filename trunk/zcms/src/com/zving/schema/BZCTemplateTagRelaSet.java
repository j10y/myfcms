 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCTemplateTagRelaSet extends SchemaSet
 {
   public BZCTemplateTagRelaSet()
   {
     this(10, 0);
   }
 
   public BZCTemplateTagRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCTemplateTagRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCTemplateTagRela";
     this.Columns = BZCTemplateTagRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCTemplateTagRela values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCTemplateTagRela set ID=?,TemplateID=?,SiteID=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and TemplateID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCTemplateTagRela  where ID=? and TemplateID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCTemplateTagRela  where ID=? and TemplateID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCTemplateTagRelaSet();
   }
 
   public boolean add(BZCTemplateTagRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCTemplateTagRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCTemplateTagRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCTemplateTagRelaSchema get(int index) {
     BZCTemplateTagRelaSchema tSchema = (BZCTemplateTagRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCTemplateTagRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCTemplateTagRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCTemplateTagRelaSet
 * JD-Core Version:    0.5.3
 */