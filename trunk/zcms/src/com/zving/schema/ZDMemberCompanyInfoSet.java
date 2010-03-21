 package com.zving.schema;
 
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDMemberCompanyInfoSet extends SchemaSet
 {
   public ZDMemberCompanyInfoSet()
   {
     this(10, 0);
   }
 
   public ZDMemberCompanyInfoSet(int initialCapacity) {
     this(initialCapacity, 0);
   }
 
   public ZDMemberCompanyInfoSet(int initialCapacity, int capacityIncrement) {
     super(initialCapacity, capacityIncrement);
     this.TableCode = "ZDMemberCompanyInfo";
     this.Columns = ZDMemberCompanyInfoSchema._Columns;
     this.NameSpace = "com.zving.schema";
     this.InsertAllSQL = "insert into ZDMemberCompanyInfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDMemberCompanyInfo set UserName=?,CompanyName=?,Scale=?,BusinessType=?,Products=?,CompanySite=?,Tel=?,Fax=?,LinkMan=?,Mobile=?,Email=?,Address=?,ZipCode=?,Pic=?,Intro=? where UserName=?";
     this.FillAllSQL = "select * from ZDMemberCompanyInfo  where UserName=?";
     this.DeleteSQL = "delete from ZDMemberCompanyInfo  where UserName=?";
   }
 
   protected SchemaSet newInstance() {
     return new ZDMemberCompanyInfoSet();
   }
 
   public boolean add(ZDMemberCompanyInfoSchema aSchema) {
     return super.add(aSchema);
   }
 
   public boolean add(ZDMemberCompanyInfoSet aSet) {
     return super.add(aSet);
   }
 
   public boolean remove(ZDMemberCompanyInfoSchema aSchema) {
     return super.remove(aSchema);
   }
 
   public ZDMemberCompanyInfoSchema get(int index) {
     ZDMemberCompanyInfoSchema tSchema = (ZDMemberCompanyInfoSchema)super.getObject(index);
     return tSchema;
   }
 
   public boolean set(int index, ZDMemberCompanyInfoSchema aSchema) {
     return super.set(index, aSchema);
   }
 
   public boolean set(ZDMemberCompanyInfoSet aSet) {
     return super.set(aSet);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDMemberCompanyInfoSet
 * JD-Core Version:    0.5.3
 */