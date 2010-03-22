 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCCustomTableColumnSet extends SchemaSet
 {
   public BZCCustomTableColumnSet()
   {
     this(10, 0);
   }
 
   public BZCCustomTableColumnSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCCustomTableColumnSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCCustomTableColumn";
     this.Columns = BZCCustomTableColumnSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCCustomTableColumn values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCCustomTableColumn set ID=?,TableID=?,Code=?,Name=?,DataType=?,Length=?,isPrimaryKey=?,isMandatory=?,DefaultValue=?,InputType=?,CSSStyle=?,MaxLength=?,ListOptions=?,isAutoID=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCCustomTableColumn  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCCustomTableColumn  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCCustomTableColumnSet();
   }
 
   public boolean add(BZCCustomTableColumnSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCCustomTableColumnSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCCustomTableColumnSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCCustomTableColumnSchema get(int index) {
     BZCCustomTableColumnSchema tSchema = (BZCCustomTableColumnSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCCustomTableColumnSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCCustomTableColumnSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCCustomTableColumnSet
 * JD-Core Version:    0.5.3
 */