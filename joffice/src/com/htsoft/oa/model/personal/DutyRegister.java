 package com.htsoft.oa.model.personal;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.AppUser;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class DutyRegister extends BaseModel
 {
   public static final Short SIGN_IN = 1;
 
   public static final Short SIGN_OFF = 2;
 
   public static final Short REG_FLAG_NORMAL = 1;
 
   public static final Short REG_FLAG_LATE = 2;
 
   public static final Short REG_FLAG_EARLY_OFF = 3;
 
   public static final Short REG_FLAG_RELAX = 4;
 
   public static final Short REG_FLAG_TRUANCY = 5;
 
   public static final Short REG_FLAG_HOLIDAY = 6;
 
   @Expose
   protected Long registerId;
 
   @Expose
   protected Date registerDate;
 
   @Expose
   protected Short regFlag;
 
   @Expose
   protected Integer regMins;
 
   @Expose
   protected String reason;
 
   @Expose
   protected Integer dayOfWeek;
 
   @Expose
   protected Short inOffFlag;
 
   @Expose
   protected String fullname;
   protected AppUser appUser;
   protected DutySection dutySection;
 
   public DutyRegister()
   {
   }
 
   public DutyRegister(Long in_registerId)
   {
     setRegisterId(in_registerId);
   }
 
   public AppUser getAppUser()
   {
     return this.appUser;
   }
 
   public void setAppUser(AppUser in_appUser) {
     this.appUser = in_appUser;
   }
 
   public Long getRegisterId()
   {
     return this.registerId;
   }
 
   public void setRegisterId(Long aValue)
   {
     this.registerId = aValue;
   }
 
   public Date getRegisterDate()
   {
     return this.registerDate;
   }
 
   public void setRegisterDate(Date aValue)
   {
     this.registerDate = aValue;
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
 
   public Short getRegFlag()
   {
     return this.regFlag;
   }
 
   public void setRegFlag(Short aValue)
   {
     this.regFlag = aValue;
   }
 
   public Integer getRegMins()
   {
     return this.regMins;
   }
 
   public void setRegMins(Integer aValue)
   {
     this.regMins = aValue;
   }
 
   public String getReason()
   {
     return this.reason;
   }
 
   public void setReason(String aValue)
   {
     this.reason = aValue;
   }
 
   public Integer getDayOfWeek()
   {
     return this.dayOfWeek;
   }
 
   public void setDayOfWeek(Integer aValue)
   {
     this.dayOfWeek = aValue;
   }
 
   public Short getInOffFlag()
   {
     return this.inOffFlag;
   }
 
   public void setInOffFlag(Short aValue)
   {
     this.inOffFlag = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof DutyRegister)) {
       return false;
     }
     DutyRegister rhs = (DutyRegister)object;
     return new EqualsBuilder()
       .append(this.registerId, rhs.registerId)
       .append(this.registerDate, rhs.registerDate)
       .append(this.regFlag, rhs.regFlag)
       .append(this.regMins, rhs.regMins)
       .append(this.reason, rhs.reason)
       .append(this.dayOfWeek, rhs.dayOfWeek)
       .append(this.inOffFlag, rhs.inOffFlag)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.registerId)
       .append(this.registerDate)
       .append(this.regFlag)
       .append(this.regMins)
       .append(this.reason)
       .append(this.dayOfWeek)
       .append(this.inOffFlag)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("registerId", this.registerId)
       .append("registerDate", this.registerDate)
       .append("regFlag", this.regFlag)
       .append("regMins", this.regMins)
       .append("reason", this.reason)
       .append("dayOfWeek", this.dayOfWeek)
       .append("inOffFlag", this.inOffFlag)
       .toString();
   }
 
   public DutySection getDutySection() {
     return this.dutySection;
   }
 
   public void setDutySection(DutySection dutySection) {
     this.dutySection = dutySection;
   }
 
   public String getRegisterTime() {
     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
     return sdf.format(this.registerDate);
   }
 
   public String getFullname() {
     return this.fullname;
   }
 
   public void setFullname(String fullname) {
     this.fullname = fullname;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.personal.DutyRegister
 * JD-Core Version:    0.5.4
 */