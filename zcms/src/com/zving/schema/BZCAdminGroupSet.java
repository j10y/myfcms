 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZCAdminGroupSet extends SchemaSet
 {
   public BZCAdminGroupSet()
   {
     this(10, 0);
   }
 
   public BZCAdminGroupSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZCAdminGroupSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZCAdminGroup";
     this.Columns = BZCAdminGroupSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZCAdminGroup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAdminGroup set GroupID=?,SiteID=?,AllowEditUser=?,AllowForbidUser=?,AllowEditForum=?,AllowVerfyPost=?,AllowDel=?,AllowEdit=?,Hide=?,RemoveTheme=?,MoveTheme=?,TopTheme=?,BrightTheme=?,UpOrDownTheme=?,BestTheme=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where GroupID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAdminGroup  where GroupID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAdminGroup  where GroupID=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZCAdminGroupSet();
   }
 
   public boolean add(BZCAdminGroupSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZCAdminGroupSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZCAdminGroupSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZCAdminGroupSchema get(int index) {
     BZCAdminGroupSchema tSchema = (BZCAdminGroupSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZCAdminGroupSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZCAdminGroupSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCAdminGroupSet
 * JD-Core Version:    0.5.3
 */