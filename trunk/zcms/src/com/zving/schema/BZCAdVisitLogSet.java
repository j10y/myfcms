 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCAdVisitLogSet extends SchemaSet
 {
   public BZCAdVisitLogSet()
   {
     this(10, 0);
   }
 
   public BZCAdVisitLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCAdVisitLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCAdVisitLog";
     this.Columns = BZCAdVisitLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCAdVisitLog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAdVisitLog set ID=?,AdID=?,ServerTime=?,ClientTime=?,IP=?,Address=?,OS=?,Browser=?,Screen=?,Depth=?,Referer=?,CurrentPage=?,Visitor=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAdVisitLog  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAdVisitLog  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCAdVisitLogSet();
   }
 
   public boolean add(BZCAdVisitLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCAdVisitLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCAdVisitLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCAdVisitLogSchema get(int index) {
     BZCAdVisitLogSchema tSchema = (BZCAdVisitLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCAdVisitLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCAdVisitLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCAdVisitLogSet
 * JD-Core Version:    0.5.3
 */