 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCTemplateSet extends SchemaSet
 {
   public BZCTemplateSet()
   {
     this(10, 0);
   }
 
   public BZCTemplateSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCTemplateSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCTemplate";
     this.Columns = BZCTemplateSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCTemplate values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCTemplate set ID=?,Code=?,SiteID=?,Name=?,FileName=?,Type=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCTemplate  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCTemplate  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCTemplateSet();
   }
 
   public boolean add(BZCTemplateSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCTemplateSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCTemplateSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCTemplateSchema get(int index) {
     BZCTemplateSchema tSchema = (BZCTemplateSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCTemplateSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCTemplateSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCTemplateSet
 * JD-Core Version:    0.5.3
 */