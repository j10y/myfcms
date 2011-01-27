 package com.htsoft.oa.model.admin;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class CarApply extends BaseModel
 {
 
   @Expose
   protected Long applyId;
 
   @Expose
   protected String department;
 
   @Expose
   protected String userFullname;
 
   @Expose
   protected Date applyDate;
 
   @Expose
   protected String reason;
 
   @Expose
   protected Date startTime;
 
   @Expose
   protected Date endTime;
 
   @Expose
   protected String proposer;
 
   @Expose
   protected Long userId;
 
   @Expose
   protected BigDecimal mileage;
 
   @Expose
   protected BigDecimal oilUse;
 
   @Expose
   protected String notes;
 
   @Expose
   protected Short approvalStatus;
 
   @Expose
   protected Car car;
 
   public CarApply()
   {
   }
 
   public CarApply(Long in_applyId)
   {
     setApplyId(in_applyId);
   }
 
   public Long getUserId()
   {
     return this.userId;
   }
 
   public void setUserId(Long userId) {
     this.userId = userId;
   }
 
   public Car getCar() {
     return this.car;
   }
 
   public void setCar(Car in_car) {
     this.car = in_car;
   }
 
   public Long getApplyId()
   {
     return this.applyId;
   }
 
   public void setApplyId(Long aValue)
   {
     this.applyId = aValue;
   }
 
   public Long getCarId()
   {
     return (getCar() == null) ? null : getCar().getCarId();
   }
 
   public void setCarId(Long aValue)
   {
     if (aValue == null) {
       this.car = null;
     } else if (this.car == null) {
       this.car = new Car(aValue);
       this.car.setVersion(new Integer(0));
     } else {
       this.car.setCarId(aValue);
     }
   }
 
   public String getDepartment()
   {
     return this.department;
   }
 
   public void setDepartment(String aValue)
   {
     this.department = aValue;
   }
 
   public String getUserFullname()
   {
     return this.userFullname;
   }
 
   public void setUserFullname(String aValue)
   {
     this.userFullname = aValue;
   }
 
   public Date getApplyDate()
   {
     return this.applyDate;
   }
 
   public void setApplyDate(Date aValue)
   {
     this.applyDate = aValue;
   }
 
   public String getReason()
   {
     return this.reason;
   }
 
   public void setReason(String aValue)
   {
     this.reason = aValue;
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
 
   public String getProposer()
   {
     return this.proposer;
   }
 
   public void setProposer(String aValue)
   {
     this.proposer = aValue;
   }
 
   public BigDecimal getMileage()
   {
     return this.mileage;
   }
 
   public void setMileage(BigDecimal aValue)
   {
     this.mileage = aValue;
   }
 
   public BigDecimal getOilUse()
   {
     return this.oilUse;
   }
 
   public void setOilUse(BigDecimal aValue)
   {
     this.oilUse = aValue;
   }
 
   public String getNotes()
   {
     return this.notes;
   }
 
   public void setNotes(String aValue)
   {
     this.notes = aValue;
   }
 
   public Short getApprovalStatus()
   {
     return this.approvalStatus;
   }
 
   public void setApprovalStatus(Short aValue)
   {
     this.approvalStatus = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof CarApply)) {
       return false;
     }
     CarApply rhs = (CarApply)object;
     return new EqualsBuilder()
       .append(this.applyId, rhs.applyId)
       .append(this.department, rhs.department)
       .append(this.userFullname, rhs.userFullname)
       .append(this.applyDate, rhs.applyDate)
       .append(this.reason, rhs.reason)
       .append(this.startTime, rhs.startTime)
       .append(this.endTime, rhs.endTime)
       .append(this.proposer, rhs.proposer)
       .append(this.userId, rhs.userId)
       .append(this.mileage, rhs.mileage)
       .append(this.oilUse, rhs.oilUse)
       .append(this.notes, rhs.notes)
       .append(this.approvalStatus, rhs.approvalStatus)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.applyId)
       .append(this.department)
       .append(this.userFullname)
       .append(this.applyDate)
       .append(this.reason)
       .append(this.startTime)
       .append(this.endTime)
       .append(this.proposer)
       .append(this.userId)
       .append(this.mileage)
       .append(this.oilUse)
       .append(this.notes)
       .append(this.approvalStatus)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("applyId", this.applyId)
       .append("department", this.department)
       .append("userFullname", this.userFullname)
       .append("applyDate", this.applyDate)
       .append("reason", this.reason)
       .append("startTime", this.startTime)
       .append("endTime", this.endTime)
       .append("proposer", this.proposer)
       .append("userId", this.userId)
       .append("mileage", this.mileage)
       .append("oilUse", this.oilUse)
       .append("notes", this.notes)
       .append("approvalStatus", this.approvalStatus)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.CarApply
 * JD-Core Version:    0.5.4
 */