 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCAuthorSet extends SchemaSet
 {
   public BZCAuthorSet()
   {
     this(10, 0);
   }
 
   public BZCAuthorSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCAuthorSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCAuthor";
     this.Columns = BZCAuthorSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCAuthor values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAuthor set ID=?,Author=?,RealName=?,Sex=?,Email=?,Tel=?,Mobile=?,Address=?,Zipcode=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAuthor  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAuthor  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCAuthorSet();
   }
 
   public boolean add(BZCAuthorSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCAuthorSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCAuthorSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCAuthorSchema get(int index) {
     BZCAuthorSchema tSchema = (BZCAuthorSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCAuthorSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCAuthorSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCAuthorSet
 * JD-Core Version:    0.5.3
 */