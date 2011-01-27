 package com.htsoft.oa.model.communicate;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.AppUser;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class PhoneBook extends BaseModel
 {
 
   @Expose
   protected Long phoneId;
 
   @Expose
   protected String fullname;
 
   @Expose
   protected String title;
 
   @Expose
   protected Date birthday;
 
   @Expose
   protected String nickName;
 
   @Expose
   protected String duty;
 
   @Expose
   protected String spouse;
 
   @Expose
   protected String childs;
 
   @Expose
   protected String companyName;
 
   @Expose
   protected String companyAddress;
 
   @Expose
   protected String companyPhone;
 
   @Expose
   protected String companyFax;
 
   @Expose
   protected String homeAddress;
 
   @Expose
   protected String homeZip;
 
   @Expose
   protected String mobile;
 
   @Expose
   protected String phone;
 
   @Expose
   protected String email;
 
   @Expose
   protected String qqNumber;
 
   @Expose
   protected String msn;
 
   @Expose
   protected String note;
 
   @Expose
   protected Short isShared;
   protected AppUser appUser;
 
   @Expose
   protected PhoneGroup phoneGroup;
 
   public PhoneBook()
   {
   }
 
   public PhoneBook(Long in_phoneId)
   {
     setPhoneId(in_phoneId);
   }
 
   public AppUser getAppUser()
   {
     return this.appUser;
   }
 
   public void setAppUser(AppUser in_appUser) {
     this.appUser = in_appUser;
   }
 
   public PhoneGroup getPhoneGroup() {
     return this.phoneGroup;
   }
 
   public void setPhoneGroup(PhoneGroup in_phoneGroup) {
     this.phoneGroup = in_phoneGroup;
   }
 
   public Long getPhoneId()
   {
     return this.phoneId;
   }
 
   public void setPhoneId(Long aValue)
   {
     this.phoneId = aValue;
   }
 
   public String getFullname()
   {
     return this.fullname;
   }
 
   public void setFullname(String aValue)
   {
     this.fullname = aValue;
   }
 
   public String getTitle()
   {
     return this.title;
   }
 
   public void setTitle(String aValue)
   {
     this.title = aValue;
   }
 
   public Date getBirthday()
   {
     return this.birthday;
   }
 
   public void setBirthday(Date aValue)
   {
     this.birthday = aValue;
   }
 
   public String getNickName()
   {
     return this.nickName;
   }
 
   public void setNickName(String aValue)
   {
     this.nickName = aValue;
   }
 
   public String getDuty()
   {
     return this.duty;
   }
 
   public void setDuty(String aValue)
   {
     this.duty = aValue;
   }
 
   public String getSpouse()
   {
     return this.spouse;
   }
 
   public void setSpouse(String aValue)
   {
     this.spouse = aValue;
   }
 
   public String getChilds()
   {
     return this.childs;
   }
 
   public void setChilds(String aValue)
   {
     this.childs = aValue;
   }
 
   public String getCompanyName()
   {
     return this.companyName;
   }
 
   public void setCompanyName(String aValue)
   {
     this.companyName = aValue;
   }
 
   public String getCompanyAddress()
   {
     return this.companyAddress;
   }
 
   public void setCompanyAddress(String aValue)
   {
     this.companyAddress = aValue;
   }
 
   public String getCompanyPhone()
   {
     return this.companyPhone;
   }
 
   public void setCompanyPhone(String aValue)
   {
     this.companyPhone = aValue;
   }
 
   public String getCompanyFax()
   {
     return this.companyFax;
   }
 
   public void setCompanyFax(String aValue)
   {
     this.companyFax = aValue;
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
 
   public String getMobile()
   {
     return this.mobile;
   }
 
   public void setMobile(String aValue)
   {
     this.mobile = aValue;
   }
 
   public String getPhone()
   {
     return this.phone;
   }
 
   public void setPhone(String aValue)
   {
     this.phone = aValue;
   }
 
   public String getEmail()
   {
     return this.email;
   }
 
   public void setEmail(String aValue)
   {
     this.email = aValue;
   }
 
   public String getQqNumber()
   {
     return this.qqNumber;
   }
 
   public void setQqNumber(String aValue)
   {
     this.qqNumber = aValue;
   }
 
   public String getMsn()
   {
     return this.msn;
   }
 
   public void setMsn(String aValue)
   {
     this.msn = aValue;
   }
 
   public String getNote()
   {
     return this.note;
   }
 
   public void setNote(String aValue)
   {
     this.note = aValue;
   }
 
   public Long getUserId()
   {
     return (getAppUser() == null) ? null : getAppUser().getUserId();
   }
 
   public void setUserId(Long aValue)
   {
     if (aValue == null) {
       this.appUser = null;
     } else if (this.appUser == null) {
       this.appUser = new AppUser(aValue);
       this.appUser.setVersion(new Integer(0));
     } else {
       this.appUser.setUserId(aValue);
     }
   }
 
   public Long getGroupId()
   {
     return (getPhoneGroup() == null) ? null : getPhoneGroup().getGroupId();
   }
 
   public void setGroupId(Long aValue)
   {
     if (aValue == null) {
       this.phoneGroup = null;
     } else if (this.phoneGroup == null) {
       this.phoneGroup = new PhoneGroup(aValue);
       this.phoneGroup.setVersion(new Integer(0));
     } else {
       this.phoneGroup.setGroupId(aValue);
     }
   }
 
   public Short getIsShared()
   {
     return this.isShared;
   }
 
   public void setIsShared(Short aValue)
   {
     this.isShared = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof PhoneBook)) {
       return false;
     }
     PhoneBook rhs = (PhoneBook)object;
     return new EqualsBuilder()
       .append(this.phoneId, rhs.phoneId)
       .append(this.fullname, rhs.fullname)
       .append(this.title, rhs.title)
       .append(this.birthday, rhs.birthday)
       .append(this.nickName, rhs.nickName)
       .append(this.duty, rhs.duty)
       .append(this.spouse, rhs.spouse)
       .append(this.childs, rhs.childs)
       .append(this.companyName, rhs.companyName)
       .append(this.companyAddress, rhs.companyAddress)
       .append(this.companyPhone, rhs.companyPhone)
       .append(this.companyFax, rhs.companyFax)
       .append(this.homeAddress, rhs.homeAddress)
       .append(this.homeZip, rhs.homeZip)
       .append(this.mobile, rhs.mobile)
       .append(this.phone, rhs.phone)
       .append(this.email, rhs.email)
       .append(this.qqNumber, rhs.qqNumber)
       .append(this.msn, rhs.msn)
       .append(this.note, rhs.note)
       .append(this.isShared, rhs.isShared)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.phoneId)
       .append(this.fullname)
       .append(this.title)
       .append(this.birthday)
       .append(this.nickName)
       .append(this.duty)
       .append(this.spouse)
       .append(this.childs)
       .append(this.companyName)
       .append(this.companyAddress)
       .append(this.companyPhone)
       .append(this.companyFax)
       .append(this.homeAddress)
       .append(this.homeZip)
       .append(this.mobile)
       .append(this.phone)
       .append(this.email)
       .append(this.qqNumber)
       .append(this.msn)
       .append(this.note)
       .append(this.isShared)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("phoneId", this.phoneId)
       .append("fullname", this.fullname)
       .append("title", this.title)
       .append("birthday", this.birthday)
       .append("nickName", this.nickName)
       .append("duty", this.duty)
       .append("spouse", this.spouse)
       .append("childs", this.childs)
       .append("companyName", this.companyName)
       .append("companyAddress", this.companyAddress)
       .append("companyPhone", this.companyPhone)
       .append("companyFax", this.companyFax)
       .append("homeAddress", this.homeAddress)
       .append("homeZip", this.homeZip)
       .append("mobile", this.mobile)
       .append("phone", this.phone)
       .append("email", this.email)
       .append("qqNumber", this.qqNumber)
       .append("msn", this.msn)
       .append("note", this.note)
       .append("isShared", this.isShared)
       .toString();
   }
 
   public String getFirstKeyColumnName()
   {
     return "phoneId";
   }
 
   public Long getId()
   {
     return this.phoneId;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.communicate.PhoneBook
 * JD-Core Version:    0.5.4
 */