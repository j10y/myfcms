 package com.htsoft.oa.model.system;
 
 import com.htsoft.core.model.BaseModel;
 import java.util.Date;
 
 public class Company extends BaseModel
 {
   private Long companyId;
   private String companyNo;
   private String companyName;
   private String companyDesc;
   private String legalPerson;
   private Date setup;
   private String phone;
   private String fax;
   private String site;
   private String logo;
 
   public Long getCompanyId()
   {
     return this.companyId;
   }
 
   public void setCompanyId(Long companyId) {
     this.companyId = companyId;
   }
 
   public String getCompanyNo() {
     return this.companyNo;
   }
 
   public void setCompanyNo(String companyNo) {
     this.companyNo = companyNo;
   }
 
   public String getCompanyName() {
     return this.companyName;
   }
 
   public void setCompanyName(String companyName) {
     this.companyName = companyName;
   }
 
   public String getCompanyDesc() {
     return this.companyDesc;
   }
 
   public void setCompanyDesc(String companyDesc) {
     this.companyDesc = companyDesc;
   }
 
   public String getLegalPerson() {
     return this.legalPerson;
   }
 
   public void setLegalPerson(String legalPerson) {
     this.legalPerson = legalPerson;
   }
 
   public Date getSetup() {
     return this.setup;
   }
 
   public void setSetup(Date setup) {
     this.setup = setup;
   }
 
   public String getPhone() {
     return this.phone;
   }
 
   public void setPhone(String phone) {
     this.phone = phone;
   }
 
   public String getFax() {
     return this.fax;
   }
 
   public void setFax(String fax) {
     this.fax = fax;
   }
 
   public String getSite() {
     return this.site;
   }
 
   public void setSite(String site) {
     this.site = site;
   }
 
   public String getLogo() {
     return this.logo;
   }
 
   public void setLogo(String logo) {
     this.logo = logo;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.system.Company
 * JD-Core Version:    0.5.4
 */