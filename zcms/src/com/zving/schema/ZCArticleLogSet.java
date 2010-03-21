 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCArticleLogSet extends SchemaSet
 {
   public ZCArticleLogSet()
   {
     this(10, 0);
   }
 
   public ZCArticleLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCArticleLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCArticleLog";
     this.Columns = ZCArticleLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCArticleLog values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCArticleLog set ID=?,ArticleID=?,Action=?,ActionDetail=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=? where ID=?";
     this.FillAllSQL = "select * from ZCArticleLog  where ID=?";
     this.DeleteSQL = "delete from ZCArticleLog  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCArticleLogSet();
   }
 
   public boolean add(ZCArticleLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCArticleLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCArticleLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCArticleLogSchema get(int index) {
     ZCArticleLogSchema tSchema = (ZCArticleLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCArticleLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCArticleLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCArticleLogSet
 * JD-Core Version:    0.5.3
 */