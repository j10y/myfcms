 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZWWorkflowEntrySet extends SchemaSet
 {
   public ZWWorkflowEntrySet()
   {
     this(10, 0);
   }
 
   public ZWWorkflowEntrySet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZWWorkflowEntrySet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZWWorkflowEntry";
     this.Columns = ZWWorkflowEntrySchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZWWorkflowEntry values(?,?,?,?)";
     this.UpdateAllSQL = "update ZWWorkflowEntry set ID=?,WorkflowDefID=?,State=?,Memo=? where ID=?";
     this.FillAllSQL = "select * from ZWWorkflowEntry  where ID=?";
     this.DeleteSQL = "delete from ZWWorkflowEntry  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZWWorkflowEntrySet();
   }
 
   public boolean add(ZWWorkflowEntrySchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZWWorkflowEntrySet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZWWorkflowEntrySchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZWWorkflowEntrySchema get(int index) {
     ZWWorkflowEntrySchema tSchema = (ZWWorkflowEntrySchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZWWorkflowEntrySchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZWWorkflowEntrySet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZWWorkflowEntrySet
 * JD-Core Version:    0.5.3
 */