 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCCommentSet extends SchemaSet
 {
   public BZCCommentSet()
   {
     this(10, 0);
   }
 
   public BZCCommentSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCCommentSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCComment";
     this.Columns = BZCCommentSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCComment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCComment set ID=?,RelaID=?,CatalogID=?,CatalogType=?,SiteID=?,Title=?,Content=?,AddUser=?,AddUserIP=?,AddTime=?,VerifyFlag=?,VerifyUser=?,VerifyTime=?,Prop1=?,Prop2=?,SupporterCount=?,AntiCount=?,SupportAntiIP=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCComment  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCComment  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCCommentSet();
   }
 
   public boolean add(BZCCommentSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCCommentSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCCommentSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCCommentSchema get(int index) {
     BZCCommentSchema tSchema = (BZCCommentSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCCommentSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCCommentSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCCommentSet
 * JD-Core Version:    0.5.3
 */