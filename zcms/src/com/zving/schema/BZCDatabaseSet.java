 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCDatabaseSet extends SchemaSet
 {
   public BZCDatabaseSet()
   {
     this(10, 0);
   }
 
   public BZCDatabaseSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCDatabaseSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCDatabase";
     this.Columns = BZCDatabaseSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCDatabase values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCDatabase set ID=?,SiteID=?,Name=?,ServerType=?,Address=?,Port=?,UserName=?,Password=?,DBName=?,TestTable=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCDatabase  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCDatabase  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCDatabaseSet();
   }
 
   public boolean add(BZCDatabaseSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCDatabaseSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCDatabaseSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCDatabaseSchema get(int index) {
     BZCDatabaseSchema tSchema = (BZCDatabaseSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCDatabaseSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCDatabaseSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCDatabaseSet
 * JD-Core Version:    0.5.3
 */