 package com.htsoft.oa.model.customer;
 
 import com.htsoft.core.model.BaseModel;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class CusLinkman extends BaseModel
 {
   protected Long linkmanId;
   protected String fullname;
   protected Short sex;
   protected String position;
   protected String phone;
   protected String mobile;
   protected String email;
   protected String msn;
   protected String qq;
   protected String fax;
   protected Date birthday;
   protected String homeAddress;
   protected String homeZip;
   protected String homePhone;
   protected String hobby;
   protected Short isPrimary;
   protected String notes;
   protected Customer customer;
 
   public CusLinkman()
   {
   }
 
   public CusLinkman(Long in_linkmanId)
   {
     setLinkmanId(in_linkmanId);
   }
 
   public Customer getCustomer()
   {
     return this.customer;
   }
 
   public void setCustomer(Customer in_customer) {
     this.customer = in_customer;
   }
 
   public Long getLinkmanId()
   {
     return this.linkmanId;
   }
 
   public void setLinkmanId(Long aValue)
   {
     this.linkmanId = aValue;
   }
 
   public Long getCustomerId()
   {
     return (getCustomer() == null) ? null : getCustomer().getCustomerId();
   }
 
   public void setCustomerId(Long aValue)
   {
     if (aValue == null) {
       this.customer = null;
     } else if (this.customer == null) {
       this.customer = new Customer(aValue);
       this.customer.setVersion(new Integer(0));
     } else {
       this.customer.setCustomerId(aValue);
     }
   }
 
   public String getFullname()
   {
     return this.fullname;
   }
 
   public void setFullname(String aValue)
   {
     this.fullname = aValue;
   }
 
   public Short getSex()
   {
     return this.sex;
   }
 
   public void setSex(Short aValue)
   {
     this.sex = aValue;
   }
 
   public String getPosition()
   {
     return this.position;
   }
 
   public void setPosition(String aValue)
   {
     this.position = aValue;
   }
 
   public String getPhone()
   {
     return this.phone;
   }
 
   public void setPhone(String aValue)
   {
     this.phone = aValue;
   }
 
   public String getMobile()
   {
     return this.mobile;
   }
 
   public void setMobile(String aValue)
   {
     this.mobile = aValue;
   }
 
   public String getEmail()
   {
     return this.email;
   }
 
   public void setEmail(String aValue)
   {
     this.email = aValue;
   }
 
   public String getMsn()
   {
     return this.msn;
   }
 
   public void setMsn(String msn) {
     this.msn = msn;
   }
 
   public Date getBirthday()
   {
     return this.birthday;
   }
 
   public String getQq() {
     return this.qq;
   }
 
   public void setQq(String qq) {
     this.qq = qq;
   }
 
   public String getFax() {
     return this.fax;
   }
 
   public void setFax(String fax) {
     this.fax = fax;
   }
 
   public void setBirthday(Date aValue)
   {
     this.birthday = aValue;
   }
 
   public String getHomeAddress()
   {
     return this.homeAddress;
   }
 
   public void setHomeAddress(String aValue)
   {
     this.homeAddress = aValue;
   }
 
   public String getHomeZip()
   {
     return this.homeZip;
   }
 
   public void setHomeZip(String aValue)
   {
     this.homeZip = aValue;
   }
 
   public String getHomePhone()
   {
     return this.homePhone;
   }
 
   public void setHomePhone(String aValue)
   {
     this.homePhone = aValue;
   }
 
   public String getHobby()
   {
     return this.hobby;
   }
 
   public void setHobby(String aValue)
   {
     this.hobby = aValue;
   }
 
   public Short getIsPrimary()
   {
     return this.isPrimary;
   }
 
   public void setIsPrimary(Short aValue)
   {
     this.isPrimary = aValue;
   }
 
   public String getNotes()
   {
     return this.notes;
   }
 
   public void setNotes(String aValue)
   {
     this.notes = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof CusLinkman) {
       return false;
     }
     CusLinkman rhs = (CusLinkman)object;
     return new EqualsBuilder()
       .append(this.linkmanId, rhs.linkmanId)
       .append(this.fullname, rhs.fullname)
       .append(this.sex, rhs.sex)
       .append(this.position, rhs.position)
       .append(this.phone, rhs.phone)
       .append(this.mobile, rhs.mobile)
       .append(this.email, rhs.email)
       .append(this.msn, rhs.msn)
       .append(this.qq, rhs.qq)
       .append(this.fax, rhs.fax)
       .append(this.birthday, rhs.birthday)
       .append(this.homeAddress, rhs.homeAddress)
       .append(this.homeZip, rhs.homeZip)
       .append(this.homePhone, rhs.homePhone)
       .append(this.hobby, rhs.hobby)
       .append(this.isPrimary, rhs.isPrimary)
       .append(this.notes, rhs.notes)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.linkmanId)
       .append(this.fullname)
       .append(this.sex)
       .append(this.position)
       .append(this.phone)
       .append(this.mobile)
       .append(this.email)
       .append(this.msn)
       .append(this.qq)
       .append(this.fax)
       .append(this.birthday)
       .append(this.homeAddress)
       .append(this.homeZip)
       .append(this.homePhone)
       .append(this.hobby)
       .append(this.isPrimary)
       .append(this.notes)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("linkmanId", this.linkmanId)
       .append("fullname", this.fullname)
       .append("sex", this.sex)
       .append("position", this.position)
       .append("phone", this.phone)
       .append("mobile", this.mobile)
       .append("email", this.email)
       .append("msn", this.msn)
       .append("qq", this.qq)
       .append("fax", this.fax)
       .append("birthday", this.birthday)
       .append("homeAddress", this.homeAddress)
       .append("homeZip", this.homeZip)
       .append("homePhone", this.homePhone)
       .append("hobby", this.hobby)
       .append("isPrimary", this.isPrimary)
       .append("notes", this.notes)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.customer.CusLinkman
 * JD-Core Version:    0.5.4
 */