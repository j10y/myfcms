 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDColumnSet extends SchemaSet
 {
   public BZDColumnSet()
   {
     this(10, 0);
   }
 
   public BZDColumnSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDColumnSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDColumn";
     this.Columns = BZDColumnSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDColumn values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDColumn set ID=?,SiteID=?,Name=?,Code=?,VerifyType=?,MaxLength=?,InputType=?,DefaultValue=?,ListOption=?,HTML=?,IsMandatory=?,OrderFlag=?,RowSize=?,ColSize=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDColumn  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDColumn  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDColumnSet();
   }
 
   public boolean add(BZDColumnSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDColumnSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDColumnSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDColumnSchema get(int index) {
     BZDColumnSchema tSchema = (BZDColumnSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDColumnSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDColumnSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDColumnSet
 * JD-Core Version:    0.5.3
 */