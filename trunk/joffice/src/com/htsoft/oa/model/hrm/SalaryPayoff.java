 package com.htsoft.oa.model.hrm;
 
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class SalaryPayoff extends BaseModel
 {
   public static short CHECK_FLAG_NONE = 0;
   public static short CHECK_FLAG_PASS = 1;
   public static short CHECK_FLAG_NOT_PASS = 2;
   protected Long recordId;
   protected String fullname;
   protected Long userId;
   protected String profileNo;
   protected String idNo;
   protected BigDecimal standAmount;
   protected BigDecimal encourageAmount;
   protected BigDecimal deductAmount;
   protected BigDecimal achieveAmount;
   protected String encourageDesc;
   protected String deductDesc;
   protected String memo;
   protected BigDecimal acutalAmount;
   protected Date regTime;
   protected String register;
   protected String checkName;
   protected Date checkTime;
   protected Short checkStatus;
   protected Date startTime;
   protected Date endTime;
   protected String checkOpinion;
   protected Long standardId;
 
   public SalaryPayoff()
   {
   }
 
   public SalaryPayoff(Long in_recordId)
   {
     setRecordId(in_recordId);
   }
 
   public Long getRecordId()
   {
     return this.recordId;
   }
 
   public void setRecordId(Long aValue)
   {
     this.recordId = aValue;
   }
 
   public String getFullname()
   {
     return this.fullname;
   }
 
   public void setFullname(String aValue)
   {
     this.fullname = aValue;
   }
 
   public Long getUserId()
   {
     return this.userId;
   }
 
   public void setUserId(Long aValue)
   {
     this.userId = aValue;
   }
 
   public String getProfileNo()
   {
     return this.profileNo;
   }
 
   public void setProfileNo(String aValue)
   {
     this.profileNo = aValue;
   }
 
   public String getIdNo()
   {
     return this.idNo;
   }
 
   public void setIdNo(String aValue)
   {
     this.idNo = aValue;
   }
 
   public BigDecimal getStandAmount()
   {
     return this.standAmount;
   }
 
   public void setStandAmount(BigDecimal aValue)
   {
     this.standAmount = aValue;
   }
 
   public BigDecimal getEncourageAmount()
   {
     return this.encourageAmount;
   }
 
   public void setEncourageAmount(BigDecimal aValue)
   {
     this.encourageAmount = aValue;
   }
 
   public BigDecimal getDeductAmount()
   {
     return this.deductAmount;
   }
 
   public void setDeductAmount(BigDecimal aValue)
   {
     this.deductAmount = aValue;
   }
 
   public BigDecimal getAchieveAmount()
   {
     return this.achieveAmount;
   }
 
   public void setAchieveAmount(BigDecimal aValue)
   {
     this.achieveAmount = aValue;
   }
 
   public String getEncourageDesc()
   {
     return this.encourageDesc;
   }
 
   public void setEncourageDesc(String aValue)
   {
     this.encourageDesc = aValue;
   }
 
   public String getDeductDesc()
   {
     return this.deductDesc;
   }
 
   public void setDeductDesc(String aValue)
   {
     this.deductDesc = aValue;
   }
 
   public String getMemo()
   {
     return this.memo;
   }
 
   public void setMemo(String aValue)
   {
     this.memo = aValue;
   }
 
   public BigDecimal getAcutalAmount()
   {
     return this.acutalAmount;
   }
 
   public void setAcutalAmount(BigDecimal aValue)
   {
     this.acutalAmount = aValue;
   }
 
   public Date getRegTime()
   {
     return this.regTime;
   }
 
   public void setRegTime(Date aValue)
   {
     this.regTime = aValue;
   }
 
   public String getRegister()
   {
     return this.register;
   }
 
   public void setRegister(String aValue)
   {
     this.register = aValue;
   }
 
   public String getCheckName()
   {
     return this.checkName;
   }
 
   public void setCheckName(String aValue)
   {
     this.checkName = aValue;
   }
 
   public Date getCheckTime()
   {
     return this.checkTime;
   }
 
   public void setCheckTime(Date aValue)
   {
     this.checkTime = aValue;
   }
 
   public Short getCheckStatus()
   {
     return this.checkStatus;
   }
 
   public void setCheckStatus(Short aValue)
   {
     this.checkStatus = aValue;
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
 
   public String getCheckOpinion() {
     return this.checkOpinion;
   }
 
   public void setCheckOpinion(String checkOpinion) {
     this.checkOpinion = checkOpinion;
   }
 
   public Long getStandardId() {
     return this.standardId;
   }
 
   public void setStandardId(Long standardId) {
     this.standardId = standardId;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof SalaryPayoff)) {
       return false;
     }
     SalaryPayoff rhs = (SalaryPayoff)object;
     return new EqualsBuilder()
       .append(this.recordId, rhs.recordId)
       .append(this.fullname, rhs.fullname)
       .append(this.userId, rhs.userId)
       .append(this.profileNo, rhs.profileNo)
       .append(this.idNo, rhs.idNo)
       .append(this.standAmount, rhs.standAmount)
       .append(this.encourageAmount, rhs.encourageAmount)
       .append(this.deductAmount, rhs.deductAmount)
       .append(this.achieveAmount, rhs.achieveAmount)
       .append(this.encourageDesc, rhs.encourageDesc)
       .append(this.deductDesc, rhs.deductDesc)
       .append(this.memo, rhs.memo)
       .append(this.acutalAmount, rhs.acutalAmount)
       .append(this.regTime, rhs.regTime)
       .append(this.register, rhs.register)
       .append(this.checkName, rhs.checkName)
       .append(this.checkTime, rhs.checkTime)
       .append(this.checkStatus, rhs.checkStatus)
       .append(this.startTime, rhs.startTime)
       .append(this.endTime, rhs.endTime)
       .append(this.checkOpinion, rhs.checkOpinion)
       .append(this.standardId, rhs.standardId)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.recordId)
       .append(this.fullname)
       .append(this.userId)
       .append(this.profileNo)
       .append(this.idNo)
       .append(this.standAmount)
       .append(this.encourageAmount)
       .append(this.deductAmount)
       .append(this.achieveAmount)
       .append(this.encourageDesc)
       .append(this.deductDesc)
       .append(this.memo)
       .append(this.acutalAmount)
       .append(this.regTime)
       .append(this.register)
       .append(this.checkName)
       .append(this.checkTime)
       .append(this.checkStatus)
       .append(this.startTime)
       .append(this.endTime)
       .append(this.checkOpinion)
       .append(this.standardId)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("recordId", this.recordId)
       .append("fullname", this.fullname)
       .append("userId", this.userId)
       .append("profileNo", this.profileNo)
       .append("idNo", this.idNo)
       .append("standAmount", this.standAmount)
       .append("encourageAmount", this.encourageAmount)
       .append("deductAmount", this.deductAmount)
       .append("achieveAmount", this.achieveAmount)
       .append("encourageDesc", this.encourageDesc)
       .append("deductDesc", this.deductDesc)
       .append("memo", this.memo)
       .append("acutalAmount", this.acutalAmount)
       .append("regTime", this.regTime)
       .append("register", this.register)
       .append("checkName", this.checkName)
       .append("checkTime", this.checkTime)
       .append("checkStatus", this.checkStatus)
       .append("startTime", this.startTime)
       .append("endTime", this.endTime)
       .append("checkOpinion", this.checkOpinion)
       .append("standardId", this.standardId)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.hrm.SalaryPayoff
 * JD-Core Version:    0.5.4
 */