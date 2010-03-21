 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCBadWordSet extends SchemaSet
 {
   public BZCBadWordSet()
   {
     this(10, 0);
   }
 
   public BZCBadWordSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCBadWordSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCBadWord";
     this.Columns = BZCBadWordSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCBadWord values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCBadWord set ID=?,TreeLevel=?,Word=?,ReplaceWord=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCBadWord  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCBadWord  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCBadWordSet();
   }
 
   public boolean add(BZCBadWordSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCBadWordSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCBadWordSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCBadWordSchema get(int index) {
     BZCBadWordSchema tSchema = (BZCBadWordSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCBadWordSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCBadWordSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCBadWordSet
 * JD-Core Version:    0.5.3
 */