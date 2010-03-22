 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCTagSet extends SchemaSet
 {
   public BZCTagSet()
   {
     this(10, 0);
   }
 
   public BZCTagSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCTagSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCTag";
     this.Columns = BZCTagSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCTag values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCTag set ID=?,Code=?,Name=?,SiteID=?,Type=?,TagText=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCTag  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCTag  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCTagSet();
   }
 
   public boolean add(BZCTagSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCTagSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCTagSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCTagSchema get(int index) {
     BZCTagSchema tSchema = (BZCTagSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCTagSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCTagSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCTagSet
 * JD-Core Version:    0.5.3
 */