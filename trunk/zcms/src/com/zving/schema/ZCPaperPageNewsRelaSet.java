 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCPaperPageNewsRelaSet extends SchemaSet
 {
   public ZCPaperPageNewsRelaSet()
   {
     this(10, 0);
   }
 
   public ZCPaperPageNewsRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCPaperPageNewsRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCPaperPageNewsRela";
     this.Columns = ZCPaperPageNewsRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCPaperPageNewsRela values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCPaperPageNewsRela set PageID=?,ArticleID=?,Coords=?,Link=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where PageID=? and ArticleID=?";
     this.FillAllSQL = "select * from ZCPaperPageNewsRela  where PageID=? and ArticleID=?";
     this.DeleteSQL = "delete from ZCPaperPageNewsRela  where PageID=? and ArticleID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCPaperPageNewsRelaSet();
   }
 
   public boolean add(ZCPaperPageNewsRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCPaperPageNewsRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCPaperPageNewsRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCPaperPageNewsRelaSchema get(int index) {
     ZCPaperPageNewsRelaSchema tSchema = (ZCPaperPageNewsRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCPaperPageNewsRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCPaperPageNewsRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCPaperPageNewsRelaSet
 * JD-Core Version:    0.5.3
 */