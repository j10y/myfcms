 package com.htsoft.oa.model.personal;
 
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.AppUser;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class Duty extends BaseModel
 {
   protected Long dutyId;
   protected String fullname;
   protected Date startTime;
   protected Date endTime;
   protected DutySystem dutySystem;
   protected AppUser appUser;
 
   public Duty()
   {
   }
 
   public Duty(Long in_dutyId)
   {
     setDutyId(in_dutyId);
   }
 
   public DutySystem getDutySystem()
   {
     return this.dutySystem;
   }
 
   public void setDutySystem(DutySystem in_dutySystem) {
     this.dutySystem = in_dutySystem;
   }
 
   public AppUser getAppUser() {
     return this.appUser;
   }
 
   public void setAppUser(AppUser in_appUser) {
     this.appUser = in_appUser;
   }
 
   public Long getDutyId()
   {
     return this.dutyId;
   }
 
   public void setDutyId(Long aValue)
   {
     this.dutyId = aValue;
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
 
   public String getFullname()
   {
     return this.fullname;
   }
 
   public void setFullname(String aValue)
   {
     this.fullname = aValue;
   }
 
   public Long getSystemId()
   {
     return (getDutySystem() == null) ? null : getDutySystem().getSystemId();
   }
 
   public void setSystemId(Long aValue)
   {
   }
 
   public Date getStartTime()
   {
     return this.startTime;
   }
 
   public void setStartTime(Date aValue)
   {
     this.startTime = aValue;
   }
 
   public Date getEndTime()
   {
     return this.endTime;
   }
 
   public void setEndTime(Date aValue)
   {
     this.endTime = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof Duty) {
       return false;
     }
     Duty rhs = (Duty)object;
     return new EqualsBuilder()
       .append(this.dutyId, rhs.dutyId)
       .append(this.fullname, rhs.fullname)
       .append(this.startTime, rhs.startTime)
       .append(this.endTime, rhs.endTime)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.dutyId)
       .append(this.fullname)
       .append(this.startTime)
       .append(this.endTime)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("dutyId", this.dutyId)
       .append("fullname", this.fullname)
       .append("startTime", this.startTime)
       .append("endTime", this.endTime)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.personal.Duty
 * JD-Core Version:    0.5.4
 */