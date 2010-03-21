 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCPaperPageNewsRelaSet extends SchemaSet
 {
   public BZCPaperPageNewsRelaSet()
   {
     this(10, 0);
   }
 
   public BZCPaperPageNewsRelaSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCPaperPageNewsRelaSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCPaperPageNewsRela";
     this.Columns = BZCPaperPageNewsRelaSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCPaperPageNewsRela values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCPaperPageNewsRela set PageID=?,ArticleID=?,Coords=?,Link=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where PageID=? and ArticleID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCPaperPageNewsRela  where PageID=? and ArticleID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCPaperPageNewsRela  where PageID=? and ArticleID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCPaperPageNewsRelaSet();
   }
 
   public boolean add(BZCPaperPageNewsRelaSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCPaperPageNewsRelaSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCPaperPageNewsRelaSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCPaperPageNewsRelaSchema get(int index) {
     BZCPaperPageNewsRelaSchema tSchema = (BZCPaperPageNewsRelaSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCPaperPageNewsRelaSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCPaperPageNewsRelaSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCPaperPageNewsRelaSet
 * JD-Core Version:    0.5.3
 */