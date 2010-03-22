 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCPageBlockSet extends SchemaSet
 {
   public BZCPageBlockSet()
   {
     this(10, 0);
   }
 
   public BZCPageBlockSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCPageBlockSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCPageBlock";
     this.Columns = BZCPageBlockSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCPageBlock values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCPageBlock set ID=?,SiteID=?,CatalogID=?,Name=?,Code=?,Type=?,SortField=?,Memo=?,FileName=?,Template=?,Content=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCPageBlock  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCPageBlock  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCPageBlockSet();
   }
 
   public boolean add(BZCPageBlockSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCPageBlockSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCPageBlockSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCPageBlockSchema get(int index) {
     BZCPageBlockSchema tSchema = (BZCPageBlockSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCPageBlockSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCPageBlockSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCPageBlockSet
 * JD-Core Version:    0.5.3
 */