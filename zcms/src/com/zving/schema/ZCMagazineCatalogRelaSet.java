 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCMagazineCatalogRelaSet extends SchemaSet
 {
   public ZCMagazineCatalogRelaSet()
   {
     this(10, 0);
   }
 
   public ZCMagazineCatalogRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCMagazineCatalogRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCMagazineCatalogRela";
     this.Columns = ZCMagazineCatalogRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCMagazineCatalogRela values(?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCMagazineCatalogRela set MagazineID=?,CatalogID=?,IssueID=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where MagazineID=? and CatalogID=?";
     this.FillAllSQL = "select * from ZCMagazineCatalogRela  where MagazineID=? and CatalogID=?";
     this.DeleteSQL = "delete from ZCMagazineCatalogRela  where MagazineID=? and CatalogID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCMagazineCatalogRelaSet();
   }
 
   public boolean add(ZCMagazineCatalogRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCMagazineCatalogRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCMagazineCatalogRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCMagazineCatalogRelaSchema get(int index) {
     ZCMagazineCatalogRelaSchema tSchema = (ZCMagazineCatalogRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCMagazineCatalogRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCMagazineCatalogRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCMagazineCatalogRelaSet
 * JD-Core Version:    0.5.3
 */