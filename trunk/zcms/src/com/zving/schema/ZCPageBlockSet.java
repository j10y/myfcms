 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCPageBlockSet extends SchemaSet
 {
   public ZCPageBlockSet()
   {
     this(10, 0);
   }
 
   public ZCPageBlockSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCPageBlockSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCPageBlock";
     this.Columns = ZCPageBlockSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCPageBlock values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCPageBlock set ID=?,SiteID=?,CatalogID=?,Name=?,Code=?,Type=?,SortField=?,Memo=?,FileName=?,Template=?,Content=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCPageBlock  where ID=?";
     this.DeleteSQL = "delete from ZCPageBlock  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCPageBlockSet();
   }
 
   public boolean add(ZCPageBlockSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCPageBlockSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCPageBlockSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCPageBlockSchema get(int index) {
     ZCPageBlockSchema tSchema = (ZCPageBlockSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCPageBlockSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCPageBlockSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCPageBlockSet
 * JD-Core Version:    0.5.3
 */