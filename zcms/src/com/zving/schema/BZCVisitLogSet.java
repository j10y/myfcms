 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCVisitLogSet extends SchemaSet
 {
   public BZCVisitLogSet()
   {
     this(10, 0);
   }
 
   public BZCVisitLogSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCVisitLogSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCVisitLog";
     this.Columns = BZCVisitLogSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCVisitLog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCVisitLog set ID=?,SiteID=?,URL=?,IP=?,OS=?,Browser=?,Screen=?,Referer=?,ColorDepth=?,CookieEnabled=?,FlashEnabled=?,FlashVersion=?,JavaEnabled=?,Language=?,District=?,AddTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and AddTime=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCVisitLog  where ID=? and AddTime=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCVisitLog  where ID=? and AddTime=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCVisitLogSet();
   }
 
   public boolean add(BZCVisitLogSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCVisitLogSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCVisitLogSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCVisitLogSchema get(int index) {
     BZCVisitLogSchema tSchema = (BZCVisitLogSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCVisitLogSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCVisitLogSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCVisitLogSet
 * JD-Core Version:    0.5.3
 */