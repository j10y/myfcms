 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDColumnRelaSet extends SchemaSet
 {
   public BZDColumnRelaSet()
   {
     this(10, 0);
   }
 
   public BZDColumnRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDColumnRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDColumnRela";
     this.Columns = BZDColumnRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDColumnRela values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDColumnRela set ID=?,ColumnID=?,ColumnCode=?,RelaType=?,RelaID=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDColumnRela  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDColumnRela  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDColumnRelaSet();
   }
 
   public boolean add(BZDColumnRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDColumnRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDColumnRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDColumnRelaSchema get(int index) {
     BZDColumnRelaSchema tSchema = (BZDColumnRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDColumnRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDColumnRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDColumnRelaSet
 * JD-Core Version:    0.5.3
 */