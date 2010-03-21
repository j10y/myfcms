 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDCodeSet extends SchemaSet
 {
   public BZDCodeSet()
   {
     this(10, 0);
   }
 
   public BZDCodeSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDCodeSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDCode";
     this.Columns = BZDCodeSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDCode values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDCode set CodeType=?,ParentCode=?,CodeValue=?,CodeName=?,CodeOrder=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where CodeType=? and ParentCode=? and CodeValue=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDCode  where CodeType=? and ParentCode=? and CodeValue=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDCode  where CodeType=? and ParentCode=? and CodeValue=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDCodeSet();
   }
 
   public boolean add(BZDCodeSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDCodeSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDCodeSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDCodeSchema get(int index) {
     BZDCodeSchema tSchema = (BZDCodeSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDCodeSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDCodeSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDCodeSet
 * JD-Core Version:    0.5.3
 */