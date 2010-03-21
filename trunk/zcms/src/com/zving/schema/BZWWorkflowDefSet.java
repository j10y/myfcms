 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZWWorkflowDefSet extends SchemaSet
 {
   public BZWWorkflowDefSet()
   {
     this(10, 0);
   }
 
   public BZWWorkflowDefSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZWWorkflowDefSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZWWorkflowDef";
     this.Columns = BZWWorkflowDefSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZWWorkflowDef values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZWWorkflowDef set ID=?,Name=?,Definition=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZWWorkflowDef  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZWWorkflowDef  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZWWorkflowDefSet();
   }
 
   public boolean add(BZWWorkflowDefSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZWWorkflowDefSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZWWorkflowDefSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZWWorkflowDefSchema get(int index) {
     BZWWorkflowDefSchema tSchema = (BZWWorkflowDefSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZWWorkflowDefSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZWWorkflowDefSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZWWorkflowDefSet
 * JD-Core Version:    0.5.3
 */