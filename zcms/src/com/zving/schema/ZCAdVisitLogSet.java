 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCAdVisitLogSet extends SchemaSet
 {
   public ZCAdVisitLogSet()
   {
     this(10, 0);
   }
 
   public ZCAdVisitLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCAdVisitLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCAdVisitLog";
     this.Columns = ZCAdVisitLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCAdVisitLog values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAdVisitLog set ID=?,AdID=?,ServerTime=?,ClientTime=?,IP=?,Address=?,OS=?,Browser=?,Screen=?,Depth=?,Referer=?,CurrentPage=?,Visitor=? where ID=?";
     this.FillAllSQL = "select * from ZCAdVisitLog  where ID=?";
     this.DeleteSQL = "delete from ZCAdVisitLog  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCAdVisitLogSet();
   }
 
   public boolean add(ZCAdVisitLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCAdVisitLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCAdVisitLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCAdVisitLogSchema get(int index) {
     ZCAdVisitLogSchema tSchema = (ZCAdVisitLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCAdVisitLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCAdVisitLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCAdVisitLogSet
 * JD-Core Version:    0.5.3
 */