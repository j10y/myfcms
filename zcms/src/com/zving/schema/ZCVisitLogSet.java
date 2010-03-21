 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCVisitLogSet extends SchemaSet
 {
   public ZCVisitLogSet()
   {
     this(10, 0);
   }
 
   public ZCVisitLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCVisitLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCVisitLog";
     this.Columns = ZCVisitLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCVisitLog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCVisitLog set ID=?,SiteID=?,URL=?,IP=?,OS=?,Browser=?,Screen=?,Referer=?,ColorDepth=?,CookieEnabled=?,FlashEnabled=?,FlashVersion=?,JavaEnabled=?,Language=?,District=?,AddTime=? where ID=? and AddTime=?";
     this.FillAllSQL = "select * from ZCVisitLog  where ID=? and AddTime=?";
     this.DeleteSQL = "delete from ZCVisitLog  where ID=? and AddTime=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCVisitLogSet();
   }
 
   public boolean add(ZCVisitLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCVisitLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCVisitLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCVisitLogSchema get(int index) {
     ZCVisitLogSchema tSchema = (ZCVisitLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCVisitLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCVisitLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCVisitLogSet
 * JD-Core Version:    0.5.3
 */