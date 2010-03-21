 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCPostSet extends SchemaSet
 {
   public BZCPostSet()
   {
     this(10, 0);
   }
 
   public BZCPostSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCPostSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCPost";
     this.Columns = BZCPostSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCPost values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCPost set ID=?,SiteID=?,ForumID=?,ThemeID=?,First=?,Subject=?,Message=?,IP=?,Status=?,IsHidden=?,Invisible=?,VerifyFlag=?,Layer=?,ApplyDel=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCPost  where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCPost  where ID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCPostSet();
   }
 
   public boolean add(BZCPostSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCPostSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCPostSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCPostSchema get(int index) {
     BZCPostSchema tSchema = (BZCPostSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCPostSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCPostSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCPostSet
 * JD-Core Version:    0.5.3
 */