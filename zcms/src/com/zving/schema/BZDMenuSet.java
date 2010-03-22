 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDMenuSet extends SchemaSet
 {
   public BZDMenuSet()
   {
     this(10, 0);
   }
 
   public BZDMenuSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDMenuSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDMenu";
     this.Columns = BZDMenuSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDMenu values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDMenu set ID=?,ParentID=?,Name=?,Type=?,URL=?,Visiable=?,Icon=?,OrderFlag=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDMenu  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDMenu  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDMenuSet();
   }
 
   public boolean add(BZDMenuSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDMenuSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDMenuSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDMenuSchema get(int index) {
     BZDMenuSchema tSchema = (BZDMenuSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDMenuSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDMenuSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDMenuSet
 * JD-Core Version:    0.5.3
 */