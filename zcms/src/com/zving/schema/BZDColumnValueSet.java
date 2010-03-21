 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDColumnValueSet extends SchemaSet
 {
   public BZDColumnValueSet()
   {
     this(10, 0);
   }
 
   public BZDColumnValueSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDColumnValueSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDColumnValue";
     this.Columns = BZDColumnValueSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDColumnValue values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDColumnValue set ID=?,ColumnID=?,ColumnCode=?,TextValue=?,RelaType=?,RelaID=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDColumnValue  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDColumnValue  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDColumnValueSet();
   }
 
   public boolean add(BZDColumnValueSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDColumnValueSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDColumnValueSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDColumnValueSchema get(int index) {
     BZDColumnValueSchema tSchema = (BZDColumnValueSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDColumnValueSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDColumnValueSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDColumnValueSet
 * JD-Core Version:    0.5.3
 */