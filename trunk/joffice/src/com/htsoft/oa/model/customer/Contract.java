 package com.htsoft.oa.model.customer;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class Contract extends BaseModel
 {
 
   @Expose
   protected Long contractId;
 
   @Expose
   protected String contractNo;
 
   @Expose
   protected String subject;
 
   @Expose
   protected BigDecimal contractAmount;
 
   @Expose
   protected String mainItem;
 
   @Expose
   protected String salesAfterItem;
 
   @Expose
   protected Date validDate;
 
   @Expose
   protected Date expireDate;
 
   @Expose
   protected String serviceDep;
 
   @Expose
   protected String serviceMan;
 
   @Expose
   protected String signupUser;
 
   @Expose
   protected Date signupTime;
 
   @Expose
   protected String creator;
 
   @Expose
   protected Date createtime;
 
   @Expose
   protected String consignAddress;
 
   @Expose
   protected String consignee;
 
   @Expose
   protected Project project;
   protected Set contractConfigs = new HashSet();
 
   @Expose
   protected Set contractFiles = new HashSet();
 
   public Contract()
   {
   }
 
   public Contract(Long in_contractId)
   {
     setContractId(in_contractId);
   }
 
   public Project getProject()
   {
     return this.project;
   }
 
   public void setProject(Project in_project) {
     this.project = in_project;
   }
 
   public Set getContractConfigs() {
     return this.contractConfigs;
   }
 
   public void setContractConfigs(Set in_contractConfigs) {
     this.contractConfigs = in_contractConfigs;
   }
 
   public Set getContractFiles() {
     return this.contractFiles;
   }
 
   public void setContractFiles(Set in_contractFiles) {
     this.contractFiles = in_contractFiles;
   }
 
   public Long getContractId()
   {
     return this.contractId;
   }
 
   public void setContractId(Long aValue)
   {
     this.contractId = aValue;
   }
 
   public String getContractNo()
   {
     return this.contractNo;
   }
 
   public void setContractNo(String aValue)
   {
     this.contractNo = aValue;
   }
 
   public String getSubject()
   {
     return this.subject;
   }
 
   public void setSubject(String aValue)
   {
     this.subject = aValue;
   }
 
   public BigDecimal getContractAmount()
   {
     return this.contractAmount;
   }
 
   public void setContractAmount(BigDecimal aValue)
   {
     this.contractAmount = aValue;
   }
 
   public String getMainItem()
   {
     return this.mainItem;
   }
 
   public void setMainItem(String aValue)
   {
     this.mainItem = aValue;
   }
 
   public String getSalesAfterItem()
   {
     return this.salesAfterItem;
   }
 
   public void setSalesAfterItem(String aValue)
   {
     this.salesAfterItem = aValue;
   }
 
   public Date getValidDate()
   {
     return this.validDate;
   }
 
   public void setValidDate(Date aValue)
   {
     this.validDate = aValue;
   }
 
   public Date getExpireDate()
   {
     return this.expireDate;
   }
 
   public void setExpireDate(Date aValue)
   {
     this.expireDate = aValue;
   }
 
   public String getServiceDep()
   {
     return this.serviceDep;
   }
 
   public void setServiceDep(String aValue)
   {
     this.serviceDep = aValue;
   }
 
   public String getServiceMan()
   {
     return this.serviceMan;
   }
 
   public void setServiceMan(String aValue)
   {
     this.serviceMan = aValue;
   }
 
   public String getSignupUser()
   {
     return this.signupUser;
   }
 
   public void setSignupUser(String aValue)
   {
     this.signupUser = aValue;
   }
 
   public Date getSignupTime()
   {
     return this.signupTime;
   }
 
   public void setSignupTime(Date aValue)
   {
     this.signupTime = aValue;
   }
 
   public String getCreator()
   {
     return this.creator;
   }
 
   public void setCreator(String aValue)
   {
     this.creator = aValue;
   }
 
   public Date getCreatetime()
   {
     return this.createtime;
   }
 
   public void setCreatetime(Date aValue)
   {
     this.createtime = aValue;
   }
 
   public Long getProjectId()
   {
     return (getProject() == null) ? null : getProject().getProjectId();
   }
 
   public void setProjectId(Long aValue)
   {
     if (aValue == null) {
       this.project = null;
     } else if (this.project == null) {
       this.project = new Project(aValue);
       this.project.setVersion(new Integer(0));
     } else {
       this.project.setProjectId(aValue);
     }
   }
 
   public String getConsignAddress()
   {
     return this.consignAddress;
   }
 
   public void setConsignAddress(String aValue)
   {
     this.consignAddress = aValue;
   }
 
   public String getConsignee()
   {
     return this.consignee;
   }
 
   public void setConsignee(String aValue)
   {
     this.consignee = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof Contract) {
       return false;
     }
     Contract rhs = (Contract)object;
     return new EqualsBuilder()
       .append(this.contractId, rhs.contractId)
       .append(this.contractNo, rhs.contractNo)
       .append(this.subject, rhs.subject)
       .append(this.contractAmount, rhs.contractAmount)
       .append(this.mainItem, rhs.mainItem)
       .append(this.salesAfterItem, rhs.salesAfterItem)
       .append(this.validDate, rhs.validDate)
       .append(this.expireDate, rhs.expireDate)
       .append(this.serviceDep, rhs.serviceDep)
       .append(this.serviceMan, rhs.serviceMan)
       .append(this.signupUser, rhs.signupUser)
       .append(this.signupTime, rhs.signupTime)
       .append(this.creator, rhs.creator)
       .append(this.createtime, rhs.createtime)
       .append(this.consignAddress, rhs.consignAddress)
       .append(this.consignee, rhs.consignee)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.contractId)
       .append(this.contractNo)
       .append(this.subject)
       .append(this.contractAmount)
       .append(this.mainItem)
       .append(this.salesAfterItem)
       .append(this.validDate)
       .append(this.expireDate)
       .append(this.serviceDep)
       .append(this.serviceMan)
       .append(this.signupUser)
       .append(this.signupTime)
       .append(this.creator)
       .append(this.createtime)
       .append(this.consignAddress)
       .append(this.consignee)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("contactId", this.contractId)
       .append("contractNo", this.contractNo)
       .append("subject", this.subject)
       .append("contractAmount", this.contractAmount)
       .append("mainItem", this.mainItem)
       .append("salesAfterItem", this.salesAfterItem)
       .append("validDate", this.validDate)
       .append("expireDate", this.expireDate)
       .append("serviceDep", this.serviceDep)
       .append("serviceMan", this.serviceMan)
       .append("signupUser", this.signupUser)
       .append("signupTime", this.signupTime)
       .append("creator", this.creator)
       .append("createtime", this.createtime)
       .append("consignAddress", this.consignAddress)
       .append("consignee", this.consignee)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.customer.Contract
 * JD-Core Version:    0.5.4
 */