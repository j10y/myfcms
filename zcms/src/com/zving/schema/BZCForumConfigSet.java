 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCForumConfigSet extends SchemaSet
 {
   public BZCForumConfigSet()
   {
     this(10, 0);
   }
 
   public BZCForumConfigSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCForumConfigSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCForumConfig";
     this.Columns = BZCForumConfigSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCForumConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCForumConfig set ID=?,Name=?,SiteID=?,Subdomains=?,Des=?,TempCloseFlag=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCForumConfig  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCForumConfig  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCForumConfigSet();
   }
 
   public boolean add(BZCForumConfigSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCForumConfigSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCForumConfigSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCForumConfigSchema get(int index) {
     BZCForumConfigSchema tSchema = (BZCForumConfigSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCForumConfigSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCForumConfigSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCForumConfigSet
 * JD-Core Version:    0.5.3
 */