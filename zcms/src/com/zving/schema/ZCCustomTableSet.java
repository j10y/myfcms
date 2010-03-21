 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCCustomTableSet extends SchemaSet
 {
   public ZCCustomTableSet()
   {
     this(10, 0);
   }
 
   public ZCCustomTableSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCCustomTableSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCCustomTable";
     this.Columns = ZCCustomTableSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCCustomTable values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCCustomTable set ID=?,SiteID=?,Code=?,Name=?,Type=?,DatabaseID=?,OldCode=?,FormContent=?,Memo=?,AllowView=?,AllowModify=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCCustomTable  where ID=?";
     this.DeleteSQL = "delete from ZCCustomTable  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCCustomTableSet();
   }
 
   public boolean add(ZCCustomTableSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCCustomTableSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCCustomTableSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCCustomTableSchema get(int index) {
     ZCCustomTableSchema tSchema = (ZCCustomTableSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCCustomTableSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCCustomTableSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCCustomTableSet
 * JD-Core Version:    0.5.3
 */