 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCFullTextSet extends SchemaSet
 {
   public BZCFullTextSet()
   {
     this(10, 0);
   }
 
   public BZCFullTextSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCFullTextSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCFullText";
     this.Columns = BZCFullTextSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCFullText values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCFullText set ID=?,SiteID=?,Code=?,Name=?,Type=?,Memo=?,RelaText=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCFullText  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCFullText  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCFullTextSet();
   }
 
   public boolean add(BZCFullTextSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCFullTextSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCFullTextSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCFullTextSchema get(int index) {
     BZCFullTextSchema tSchema = (BZCFullTextSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCFullTextSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCFullTextSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCFullTextSet
 * JD-Core Version:    0.5.3
 */