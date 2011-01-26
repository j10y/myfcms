 package com.htsoft.oa.model.customer;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class Provider extends BaseModel
 {
 
   @Expose
   protected Long providerId;
 
   @Expose
   protected String providerName;
 
   @Expose
   protected String contactor;
 
   @Expose
   protected String phone;
 
   @Expose
   protected String fax;
 
   @Expose
   protected String site;
 
   @Expose
   protected String email;
 
   @Expose
   protected String address;
 
   @Expose
   protected String zip;
 
   @Expose
   protected String openBank;
 
   @Expose
   protected String account;
 
   @Expose
   protected String notes;
 
   @Expose
   protected Integer rank;
   protected Set products = new HashSet();
 
   public Provider()
   {
   }
 
   public Provider(Long in_providerId)
   {
     setProviderId(in_providerId);
   }
 
   public Set getProducts()
   {
     return this.products;
   }
 
   public void setProducts(Set in_products) {
     this.products = in_products;
   }
 
   public Long getProviderId()
   {
     return this.providerId;
   }
 
   public void setProviderId(Long aValue)
   {
     this.providerId = aValue;
   }
 
   public String getProviderName()
   {
     return this.providerName;
   }
 
   public void setProviderName(String aValue)
   {
     this.providerName = aValue;
   }
 
   public String getContactor()
   {
     return this.contactor;
   }
 
   public void setContactor(String aValue)
   {
     this.contactor = aValue;
   }
 
   public String getPhone()
   {
     return this.phone;
   }
 
   public void setPhone(String aValue)
   {
     this.phone = aValue;
   }
 
   public String getFax()
   {
     return this.fax;
   }
 
   public void setFax(String aValue)
   {
     this.fax = aValue;
   }
 
   public String getSite()
   {
     return this.site;
   }
 
   public void setSite(String aValue)
   {
     this.site = aValue;
   }
 
   public String getEmail()
   {
     return this.email;
   }
 
   public void setEmail(String aValue)
   {
     this.email = aValue;
   }
 
   public String getAddress()
   {
     return this.address;
   }
 
   public void setAddress(String aValue)
   {
     this.address = aValue;
   }
 
   public String getZip()
   {
     return this.zip;
   }
 
   public void setZip(String aValue)
   {
     this.zip = aValue;
   }
 
   public String getOpenBank()
   {
     return this.openBank;
   }
 
   public void setOpenBank(String aValue)
   {
     this.openBank = aValue;
   }
 
   public String getAccount()
   {
     return this.account;
   }
 
   public void setAccount(String aValue)
   {
     this.account = aValue;
   }
 
   public String getNotes()
   {
     return this.notes;
   }
 
   public void setNotes(String aValue)
   {
     this.notes = aValue;
   }
 
   public Integer getRank()
   {
     return this.rank;
   }
 
   public void setRank(Integer aValue)
   {
     this.rank = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof Provider) {
       return false;
     }
     Provider rhs = (Provider)object;
     return new EqualsBuilder()
       .append(this.providerId, rhs.providerId)
       .append(this.providerName, rhs.providerName)
       .append(this.contactor, rhs.contactor)
       .append(this.phone, rhs.phone)
       .append(this.fax, rhs.fax)
       .append(this.site, rhs.site)
       .append(this.email, rhs.email)
       .append(this.address, rhs.address)
       .append(this.zip, rhs.zip)
       .append(this.openBank, rhs.openBank)
       .append(this.account, rhs.account)
       .append(this.notes, rhs.notes)
       .append(this.rank, rhs.rank)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.providerId)
       .append(this.providerName)
       .append(this.contactor)
       .append(this.phone)
       .append(this.fax)
       .append(this.site)
       .append(this.email)
       .append(this.address)
       .append(this.zip)
       .append(this.openBank)
       .append(this.account)
       .append(this.notes)
       .append(this.rank)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("providerId", this.providerId)
       .append("providerName", this.providerName)
       .append("contactor", this.contactor)
       .append("phone", this.phone)
       .append("fax", this.fax)
       .append("site", this.site)
       .append("email", this.email)
       .append("address", this.address)
       .append("zip", this.zip)
       .append("openBank", this.openBank)
       .append("account", this.account)
       .append("notes", this.notes)
       .append("rank", this.rank)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.customer.Provider
 * JD-Core Version:    0.5.4
 */