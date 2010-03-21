 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCCustomTableColumnSet extends SchemaSet
 {
   public ZCCustomTableColumnSet()
   {
     this(10, 0);
   }
 
   public ZCCustomTableColumnSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCCustomTableColumnSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCCustomTableColumn";
     this.Columns = ZCCustomTableColumnSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCCustomTableColumn values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCCustomTableColumn set ID=?,TableID=?,Code=?,Name=?,DataType=?,Length=?,isPrimaryKey=?,isMandatory=?,DefaultValue=?,InputType=?,CSSStyle=?,MaxLength=?,ListOptions=?,isAutoID=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCCustomTableColumn  where ID=?";
     this.DeleteSQL = "delete from ZCCustomTableColumn  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCCustomTableColumnSet();
   }
 
   public boolean add(ZCCustomTableColumnSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCCustomTableColumnSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCCustomTableColumnSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCCustomTableColumnSchema get(int index) {
     ZCCustomTableColumnSchema tSchema = (ZCCustomTableColumnSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCCustomTableColumnSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCCustomTableColumnSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCCustomTableColumnSet
 * JD-Core Version:    0.5.3
 */