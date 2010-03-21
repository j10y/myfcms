 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDFavoriteSet extends SchemaSet
 {
   public BZDFavoriteSet()
   {
     this(10, 0);
   }
 
   public BZDFavoriteSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDFavoriteSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDFavorite";
     this.Columns = BZDFavoriteSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDFavorite values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDFavorite set UserName=?,DocID=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and DocID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDFavorite  where UserName=? and DocID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDFavorite  where UserName=? and DocID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDFavoriteSet();
   }
 
   public boolean add(BZDFavoriteSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDFavoriteSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDFavoriteSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDFavoriteSchema get(int index) {
     BZDFavoriteSchema tSchema = (BZDFavoriteSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDFavoriteSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDFavoriteSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDFavoriteSet
 * JD-Core Version:    0.5.3
 */