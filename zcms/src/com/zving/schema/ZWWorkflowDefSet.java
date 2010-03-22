 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZWWorkflowDefSet extends SchemaSet
 {
   public ZWWorkflowDefSet()
   {
     this(10, 0);
   }
 
   public ZWWorkflowDefSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZWWorkflowDefSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZWWorkflowDef";
     this.Columns = ZWWorkflowDefSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZWWorkflowDef values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZWWorkflowDef set ID=?,Name=?,Definition=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
     this.FillAllSQL = "select * from ZWWorkflowDef  where ID=?";
     this.DeleteSQL = "delete from ZWWorkflowDef  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZWWorkflowDefSet();
   }
 
   public boolean add(ZWWorkflowDefSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZWWorkflowDefSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZWWorkflowDefSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZWWorkflowDefSchema get(int index) {
     ZWWorkflowDefSchema tSchema = (ZWWorkflowDefSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZWWorkflowDefSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZWWorkflowDefSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZWWorkflowDefSet
 * JD-Core Version:    0.5.3
 */