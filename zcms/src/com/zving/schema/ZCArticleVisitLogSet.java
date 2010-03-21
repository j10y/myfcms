 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCArticleVisitLogSet extends SchemaSet
 {
   public ZCArticleVisitLogSet()
   {
     this(10, 0);
   }
 
   public ZCArticleVisitLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCArticleVisitLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCArticleVisitLog";
     this.Columns = ZCArticleVisitLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCArticleVisitLog values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCArticleVisitLog set ID=?,ArticleID=?,ArticleTitle=?,UserName=?,RealName=?,Prop1=?,Prop2=?,Prop3=?,IP=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCArticleVisitLog  where ID=?";
     this.DeleteSQL = "delete from ZCArticleVisitLog  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCArticleVisitLogSet();
   }
 
   public boolean add(ZCArticleVisitLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCArticleVisitLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCArticleVisitLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCArticleVisitLogSchema get(int index) {
     ZCArticleVisitLogSchema tSchema = (ZCArticleVisitLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCArticleVisitLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCArticleVisitLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCArticleVisitLogSet
 * JD-Core Version:    0.5.3
 */