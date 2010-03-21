 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDFavoriteSet extends SchemaSet
 {
   public ZDFavoriteSet()
   {
     this(10, 0);
   }
 
   public ZDFavoriteSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDFavoriteSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDFavorite";
     this.Columns = ZDFavoriteSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDFavorite values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDFavorite set UserName=?,DocID=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where UserName=? and DocID=?";
     this.FillAllSQL = "select * from ZDFavorite  where UserName=? and DocID=?";
     this.DeleteSQL = "delete from ZDFavorite  where UserName=? and DocID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDFavoriteSet();
   }
 
   public boolean add(ZDFavoriteSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDFavoriteSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDFavoriteSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDFavoriteSchema get(int index) {
     ZDFavoriteSchema tSchema = (ZDFavoriteSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDFavoriteSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDFavoriteSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDFavoriteSet
 * JD-Core Version:    0.5.3
 */