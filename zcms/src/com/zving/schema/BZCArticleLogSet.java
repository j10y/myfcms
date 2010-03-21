 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCArticleLogSet extends SchemaSet
 {
   public BZCArticleLogSet()
   {
     this(10, 0);
   }
 
   public BZCArticleLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCArticleLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCArticleLog";
     this.Columns = BZCArticleLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCArticleLog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCArticleLog set ID=?,ArticleID=?,Action=?,ActionDetail=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCArticleLog  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCArticleLog  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCArticleLogSet();
   }
 
   public boolean add(BZCArticleLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCArticleLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCArticleLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCArticleLogSchema get(int index) {
     BZCArticleLogSchema tSchema = (BZCArticleLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCArticleLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCArticleLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCArticleLogSet
 * JD-Core Version:    0.5.3
 */