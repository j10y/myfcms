 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCCustomTableSet extends SchemaSet
 {
   public BZCCustomTableSet()
   {
     this(10, 0);
   }
 
   public BZCCustomTableSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCCustomTableSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCCustomTable";
     this.Columns = BZCCustomTableSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCCustomTable values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCCustomTable set ID=?,SiteID=?,Code=?,Name=?,Type=?,DatabaseID=?,OldCode=?,FormContent=?,Memo=?,AllowView=?,AllowModify=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCCustomTable  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCCustomTable  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCCustomTableSet();
   }
 
   public boolean add(BZCCustomTableSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCCustomTableSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCCustomTableSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCCustomTableSchema get(int index) {
     BZCCustomTableSchema tSchema = (BZCCustomTableSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCCustomTableSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCCustomTableSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCCustomTableSet
 * JD-Core Version:    0.5.3
 */