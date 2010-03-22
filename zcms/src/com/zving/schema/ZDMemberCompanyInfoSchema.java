 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDMemberCompanyInfoSchema extends Schema
 {
   private String UserName;
   private String CompanyName;
   private String Scale;
   private String BusinessType;
   private String Products;
   private String CompanySite;
   private String Tel;
   private String Fax;
   private String LinkMan;
   private String Mobile;
   private String Email;
   private String Address;
   private String ZipCode;
   private String Pic;
   private String Intro;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("UserName", 1, 0, 50, 0, true, true), 
     new SchemaColumn("CompanyName", 1, 1, 100, 0, false, false), 
     new SchemaColumn("Scale", 1, 2, 10, 0, false, false), 
     new SchemaColumn("BusinessType", 1, 3, 10, 0, false, false), 
     new SchemaColumn("Products", 1, 4, 50, 0, false, false), 
     new SchemaColumn("CompanySite", 1, 5, 50, 0, false, false), 
     new SchemaColumn("Tel", 1, 6, 20, 0, false, false), 
     new SchemaColumn("Fax", 1, 7, 20, 0, false, false), 
     new SchemaColumn("LinkMan", 1, 8, 20, 0, false, false), 
     new SchemaColumn("Mobile", 1, 9, 20, 0, false, false), 
     new SchemaColumn("Email", 1, 10, 50, 0, false, false), 
     new SchemaColumn("Address", 1, 11, 100, 0, false, false), 
     new SchemaColumn("ZipCode", 1, 12, 10, 0, false, false), 
     new SchemaColumn("Pic", 1, 13, 50, 0, false, false), 
     new SchemaColumn("Intro", 10, 14, 0, 0, false, false) };
   public static final String _TableCode = "ZDMemberCompanyInfo";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDMemberCompanyInfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDMemberCompanyInfo set UserName=?,CompanyName=?,Scale=?,BusinessType=?,Products=?,CompanySite=?,Tel=?,Fax=?,LinkMan=?,Mobile=?,Email=?,Address=?,ZipCode=?,Pic=?,Intro=? where UserName=?";
   protected static final String _DeleteSQL = "delete from ZDMemberCompanyInfo  where UserName=?";
   protected static final String _FillAllSQL = "select * from ZDMemberCompanyInfo  where UserName=?";
 
   public ZDMemberCompanyInfoSchema()
   {
     this.TableCode = "ZDMemberCompanyInfo";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDMemberCompanyInfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDMemberCompanyInfo set UserName=?,CompanyName=?,Scale=?,BusinessType=?,Products=?,CompanySite=?,Tel=?,Fax=?,LinkMan=?,Mobile=?,Email=?,Address=?,ZipCode=?,Pic=?,Intro=? where UserName=?";
     this.DeleteSQL = "delete from ZDMemberCompanyInfo  where UserName=?";
     this.FillAllSQL = "select * from ZDMemberCompanyInfo  where UserName=?";
     this.HasSetFlag = new boolean[15];
   }
 
   protected Schema newInstance() {
     return new ZDMemberCompanyInfoSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDMemberCompanyInfoSet();
   }
 
   public ZDMemberCompanyInfoSet query() {
     return query(null, -1, -1);
   }
 
   public ZDMemberCompanyInfoSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDMemberCompanyInfoSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDMemberCompanyInfoSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDMemberCompanyInfoSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.UserName = ((String)v); return; }
     if (i == 1) { this.CompanyName = ((String)v); return; }
     if (i == 2) { this.Scale = ((String)v); return; }
     if (i == 3) { this.BusinessType = ((String)v); return; }
     if (i == 4) { this.Products = ((String)v); return; }
     if (i == 5) { this.CompanySite = ((String)v); return; }
     if (i == 6) { this.Tel = ((String)v); return; }
     if (i == 7) { this.Fax = ((String)v); return; }
     if (i == 8) { this.LinkMan = ((String)v); return; }
     if (i == 9) { this.Mobile = ((String)v); return; }
     if (i == 10) { this.Email = ((String)v); return; }
     if (i == 11) { this.Address = ((String)v); return; }
     if (i == 12) { this.ZipCode = ((String)v); return; }
     if (i == 13) { this.Pic = ((String)v); return; }
     if (i != 14) return; this.Intro = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.UserName;
     if (i == 1) return this.CompanyName;
     if (i == 2) return this.Scale;
     if (i == 3) return this.BusinessType;
     if (i == 4) return this.Products;
     if (i == 5) return this.CompanySite;
     if (i == 6) return this.Tel;
     if (i == 7) return this.Fax;
     if (i == 8) return this.LinkMan;
     if (i == 9) return this.Mobile;
     if (i == 10) return this.Email;
     if (i == 11) return this.Address;
     if (i == 12) return this.ZipCode;
     if (i == 13) return this.Pic;
     if (i == 14) return this.Intro;
     return null;
   }
 
   public String getUserName()
   {
     return this.UserName;
   }
 
   public void setUserName(String userName)
   {
     this.UserName = userName;
   }
 
   public String getCompanyName()
   {
     return this.CompanyName;
   }
 
   public void setCompanyName(String companyName)
   {
     this.CompanyName = companyName;
   }
 
   public String getScale()
   {
     return this.Scale;
   }
 
   public void setScale(String scale)
   {
     this.Scale = scale;
   }
 
   public String getBusinessType()
   {
     return this.BusinessType;
   }
 
   public void setBusinessType(String businessType)
   {
     this.BusinessType = businessType;
   }
 
   public String getProducts()
   {
     return this.Products;
   }
 
   public void setProducts(String products)
   {
     this.Products = products;
   }
 
   public String getCompanySite()
   {
     return this.CompanySite;
   }
 
   public void setCompanySite(String companySite)
   {
     this.CompanySite = companySite;
   }
 
   public String getTel()
   {
     return this.Tel;
   }
 
   public void setTel(String tel)
   {
     this.Tel = tel;
   }
 
   public String getFax()
   {
     return this.Fax;
   }
 
   public void setFax(String fax)
   {
     this.Fax = fax;
   }
 
   public String getLinkMan()
   {
     return this.LinkMan;
   }
 
   public void setLinkMan(String linkMan)
   {
     this.LinkMan = linkMan;
   }
 
   public String getMobile()
   {
     return this.Mobile;
   }
 
   public void setMobile(String mobile)
   {
     this.Mobile = mobile;
   }
 
   public String getEmail()
   {
     return this.Email;
   }
 
   public void setEmail(String email)
   {
     this.Email = email;
   }
 
   public String getAddress()
   {
     return this.Address;
   }
 
   public void setAddress(String address)
   {
     this.Address = address;
   }
 
   public String getZipCode()
   {
     return this.ZipCode;
   }
 
   public void setZipCode(String zipCode)
   {
     this.ZipCode = zipCode;
   }
 
   public String getPic()
   {
     return this.Pic;
   }
 
   public void setPic(String pic)
   {
     this.Pic = pic;
   }
 
   public String getIntro()
   {
     return this.Intro;
   }
 
   public void setIntro(String intro)
   {
     this.Intro = intro;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDMemberCompanyInfoSchema
 * JD-Core Version:    0.5.3
 */