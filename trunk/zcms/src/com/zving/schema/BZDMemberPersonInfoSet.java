 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDMemberPersonInfoSet extends SchemaSet
 {
   public BZDMemberPersonInfoSet()
   {
     this(10, 0);
   }
 
   public BZDMemberPersonInfoSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDMemberPersonInfoSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDMemberPersonInfo";
     this.Columns = BZDMemberPersonInfoSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDMemberPersonInfo values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDMemberPersonInfo set UserName=?,NickName=?,Birthday=?,QQ=?,MSN=?,Tel=?,Mobile=?,Address=?,ZipCode=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDMemberPersonInfo  where UserName=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDMemberPersonInfo  where UserName=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDMemberPersonInfoSet();
   }
 
   public boolean add(BZDMemberPersonInfoSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDMemberPersonInfoSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDMemberPersonInfoSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDMemberPersonInfoSchema get(int index) {
     BZDMemberPersonInfoSchema tSchema = (BZDMemberPersonInfoSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDMemberPersonInfoSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDMemberPersonInfoSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDMemberPersonInfoSet
 * JD-Core Version:    0.5.3
 */