 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCArticlePageSet extends SchemaSet
 {
   public BZCArticlePageSet()
   {
     this(10, 0);
   }
 
   public BZCArticlePageSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCArticlePageSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCArticlePage";
     this.Columns = BZCArticlePageSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCArticlePage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCArticlePage set ID=?,ArticleID=?,PageNum=?,Content=?,FileName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCArticlePage  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCArticlePage  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCArticlePageSet();
   }
 
   public boolean add(BZCArticlePageSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCArticlePageSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCArticlePageSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCArticlePageSchema get(int index) {
     BZCArticlePageSchema tSchema = (BZCArticlePageSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCArticlePageSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCArticlePageSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCArticlePageSet
 * JD-Core Version:    0.5.3
 */