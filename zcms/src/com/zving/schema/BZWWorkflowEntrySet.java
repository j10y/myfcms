 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZWWorkflowEntrySet extends SchemaSet
 {
   public BZWWorkflowEntrySet()
   {
     this(10, 0);
   }
 
   public BZWWorkflowEntrySet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZWWorkflowEntrySet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZWWorkflowEntry";
     this.Columns = BZWWorkflowEntrySchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZWWorkflowEntry values(?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZWWorkflowEntry set ID=?,WorkflowDefID=?,State=?,Memo=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZWWorkflowEntry  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZWWorkflowEntry  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZWWorkflowEntrySet();
   }
 
   public boolean add(BZWWorkflowEntrySchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZWWorkflowEntrySet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZWWorkflowEntrySchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZWWorkflowEntrySchema get(int index) {
     BZWWorkflowEntrySchema tSchema = (BZWWorkflowEntrySchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZWWorkflowEntrySchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZWWorkflowEntrySet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZWWorkflowEntrySet
 * JD-Core Version:    0.5.3
 */