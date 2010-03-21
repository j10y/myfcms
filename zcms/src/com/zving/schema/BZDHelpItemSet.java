 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDHelpItemSet extends SchemaSet
 {
   public BZDHelpItemSet()
   {
     this(10, 0);
   }
 
   public BZDHelpItemSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDHelpItemSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDHelpItem";
     this.Columns = BZDHelpItemSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDHelpItem values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDHelpItem set ID=?,Code=?,Name=?,Type=?,Content=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDHelpItem  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDHelpItem  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDHelpItemSet();
   }
 
   public boolean add(BZDHelpItemSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDHelpItemSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDHelpItemSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDHelpItemSchema get(int index) {
     BZDHelpItemSchema tSchema = (BZDHelpItemSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDHelpItemSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDHelpItemSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDHelpItemSet
 * JD-Core Version:    0.5.3
 */