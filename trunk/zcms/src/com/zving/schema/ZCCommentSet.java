 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCCommentSet extends SchemaSet
 {
   public ZCCommentSet()
   {
     this(10, 0);
   }
 
   public ZCCommentSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCCommentSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCComment";
     this.Columns = ZCCommentSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCComment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCComment set ID=?,RelaID=?,CatalogID=?,CatalogType=?,SiteID=?,Title=?,Content=?,AddUser=?,AddUserIP=?,AddTime=?,VerifyFlag=?,VerifyUser=?,VerifyTime=?,Prop1=?,Prop2=?,SupporterCount=?,AntiCount=?,SupportAntiIP=? where ID=?";
     this.FillAllSQL = "select * from ZCComment  where ID=?";
     this.DeleteSQL = "delete from ZCComment  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCCommentSet();
   }
 
   public boolean add(ZCCommentSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCCommentSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCCommentSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCCommentSchema get(int index) {
     ZCCommentSchema tSchema = (ZCCommentSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCCommentSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCCommentSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCCommentSet
 * JD-Core Version:    0.5.3
 */