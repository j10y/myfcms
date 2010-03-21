 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class BZDMemberCompanyInfoSet extends SchemaSet
 {
   public BZDMemberCompanyInfoSet()
   {
     this(10, 0);
   }
 
   public BZDMemberCompanyInfoSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public BZDMemberCompanyInfoSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "BZDMemberCompanyInfo";
     this.Columns = BZDMemberCompanyInfoSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into BZDMemberCompanyInfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDMemberCompanyInfo set UserName=?,CompanyName=?,Scale=?,BusinessType=?,Products=?,CompanySite=?,Tel=?,Fax=?,LinkMan=?,Mobile=?,Email=?,Address=?,ZipCode=?,Pic=?,Intro=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDMemberCompanyInfo  where UserName=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDMemberCompanyInfo  where UserName=? and BackupNo=?";
   }
 
   protected SchemaSet newInstance() {
     return new BZDMemberCompanyInfoSet();
   }
 
   public boolean add(BZDMemberCompanyInfoSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(BZDMemberCompanyInfoSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(BZDMemberCompanyInfoSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public BZDMemberCompanyInfoSchema get(int index) {
     BZDMemberCompanyInfoSchema tSchema = (BZDMemberCompanyInfoSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, BZDMemberCompanyInfoSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(BZDMemberCompanyInfoSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDMemberCompanyInfoSet
 * JD-Core Version:    0.5.3
 */