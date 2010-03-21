 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCNotesSet extends SchemaSet
 {
   public BZCNotesSet()
   {
     this(10, 0);
   }
 
   public BZCNotesSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCNotesSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCNotes";
     this.Columns = BZCNotesSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCNotes values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCNotes set ID=?,Title=?,Content=?,NoteTime=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCNotes  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCNotes  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCNotesSet();
   }
 
   public boolean add(BZCNotesSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCNotesSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCNotesSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCNotesSchema get(int index) {
     BZCNotesSchema tSchema = (BZCNotesSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCNotesSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCNotesSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCNotesSet
 * JD-Core Version:    0.5.3
 */