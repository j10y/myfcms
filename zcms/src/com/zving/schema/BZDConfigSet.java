 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDConfigSet extends SchemaSet
 {
   public BZDConfigSet()
   {
     this(10, 0);
   }
 
   public BZDConfigSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDConfigSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDConfig";
     this.Columns = BZDConfigSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDConfig set Type=?,Name=?,Value=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where Type=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDConfig  where Type=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDConfig  where Type=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDConfigSet();
   }
 
   public boolean add(BZDConfigSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDConfigSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDConfigSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDConfigSchema get(int index) {
     BZDConfigSchema tSchema = (BZDConfigSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDConfigSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDConfigSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDConfigSet
 * JD-Core Version:    0.5.3
 */