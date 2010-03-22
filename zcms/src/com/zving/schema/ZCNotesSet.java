 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCNotesSet extends SchemaSet
 {
   public ZCNotesSet()
   {
     this(10, 0);
   }
 
   public ZCNotesSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCNotesSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCNotes";
     this.Columns = ZCNotesSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCNotes values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCNotes set ID=?,Title=?,Content=?,NoteTime=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCNotes  where ID=?";
     this.DeleteSQL = "delete from ZCNotes  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCNotesSet();
   }
 
   public boolean add(ZCNotesSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCNotesSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCNotesSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCNotesSchema get(int index) {
     ZCNotesSchema tSchema = (ZCNotesSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCNotesSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCNotesSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCNotesSet
 * JD-Core Version:    0.5.3
 */