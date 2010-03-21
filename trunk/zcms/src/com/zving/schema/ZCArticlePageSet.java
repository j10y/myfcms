 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCArticlePageSet extends SchemaSet
 {
   public ZCArticlePageSet()
   {
     this(10, 0);
   }
 
   public ZCArticlePageSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZCArticlePageSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZCArticlePage";
     this.Columns = ZCArticlePageSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZCArticlePage values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCArticlePage set ID=?,ArticleID=?,PageNum=?,Content=?,FileName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.FillAllSQL = "select * from ZCArticlePage  where ID=?";
     this.DeleteSQL = "delete from ZCArticlePage  where ID=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZCArticlePageSet();
   }
 
   public boolean add(ZCArticlePageSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZCArticlePageSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZCArticlePageSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZCArticlePageSchema get(int index) {
     ZCArticlePageSchema tSchema = (ZCArticlePageSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZCArticlePageSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZCArticlePageSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCArticlePageSet
 * JD-Core Version:    0.5.3
 */