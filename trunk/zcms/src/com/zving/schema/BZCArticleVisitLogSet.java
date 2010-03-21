 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCArticleVisitLogSet extends SchemaSet
 {
   public BZCArticleVisitLogSet()
   {
     this(10, 0);
   }
 
   public BZCArticleVisitLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCArticleVisitLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCArticleVisitLog";
     this.Columns = BZCArticleVisitLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCArticleVisitLog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCArticleVisitLog set ID=?,ArticleID=?,ArticleTitle=?,UserName=?,RealName=?,Prop1=?,Prop2=?,Prop3=?,IP=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCArticleVisitLog  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCArticleVisitLog  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCArticleVisitLogSet();
   }
 
   public boolean add(BZCArticleVisitLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCArticleVisitLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCArticleVisitLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCArticleVisitLogSchema get(int index) {
     BZCArticleVisitLogSchema tSchema = (BZCArticleVisitLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCArticleVisitLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCArticleVisitLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCArticleVisitLogSet
 * JD-Core Version:    0.5.3
 */