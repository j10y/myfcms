 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCMagazineCatalogRelaSet extends SchemaSet
 {
   public BZCMagazineCatalogRelaSet()
   {
     this(10, 0);
   }
 
   public BZCMagazineCatalogRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCMagazineCatalogRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCMagazineCatalogRela";
     this.Columns = BZCMagazineCatalogRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCMagazineCatalogRela values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCMagazineCatalogRela set MagazineID=?,CatalogID=?,IssueID=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where MagazineID=? and CatalogID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCMagazineCatalogRela  where MagazineID=? and CatalogID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCMagazineCatalogRela  where MagazineID=? and CatalogID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCMagazineCatalogRelaSet();
   }
 
   public boolean add(BZCMagazineCatalogRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCMagazineCatalogRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCMagazineCatalogRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCMagazineCatalogRelaSchema get(int index) {
     BZCMagazineCatalogRelaSchema tSchema = (BZCMagazineCatalogRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCMagazineCatalogRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCMagazineCatalogRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCMagazineCatalogRelaSet
 * JD-Core Version:    0.5.3
 */