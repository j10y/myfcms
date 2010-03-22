 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCPageBlockItemSet extends SchemaSet
 {
   public BZCPageBlockItemSet()
   {
     this(10, 0);
   }
 
   public BZCPageBlockItemSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCPageBlockItemSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCPageBlockItem";
     this.Columns = BZCPageBlockItemSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCPageBlockItem values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCPageBlockItem set ID=?,BlockID=?,Title=?,URL=?,Image=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCPageBlockItem  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCPageBlockItem  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCPageBlockItemSet();
   }
 
   public boolean add(BZCPageBlockItemSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCPageBlockItemSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCPageBlockItemSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCPageBlockItemSchema get(int index) {
     BZCPageBlockItemSchema tSchema = (BZCPageBlockItemSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCPageBlockItemSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCPageBlockItemSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCPageBlockItemSet
 * JD-Core Version:    0.5.3
 */