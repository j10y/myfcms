 package com.htsoft.oa.model.hrm;
 
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class JobChange extends BaseModel
 {
   protected Long changeId;
   protected Long profileId;
   protected String profileNo;
   protected String userName;
   protected Long orgJobId;
   protected String orgJobName;
   protected Long newJobId;
   protected String newJobName;
   protected Long orgStandardId;
   protected Long newStandardId;
   protected String orgStandardNo;
   protected String orgStandardName;
   protected Long orgDepId;
   protected String orgDepName;
   protected BigDecimal orgTotalMoney;
   protected String newStandardNo;
   protected String newStandardName;
   protected Long newDepId;
   protected String newDepName;
   protected BigDecimal newTotalMoney;
   protected String changeReason;
   protected String regName;
   protected Date regTime;
   protected String checkName;
   protected Date checkTime;
   protected String checkOpinion;
   protected Short status;
   protected String memo;
 
   public JobChange()
   {
   }
 
   public JobChange(Long in_changeId)
   {
     setChangeId(in_changeId);
   }
 
   public Long getChangeId()
   {
     return this.changeId;
   }
 
   public Long getOrgStandardId()
   {
     return this.orgStandardId;
   }
 
   public void setOrgStandardId(Long orgStandardId) {
     this.orgStandardId = orgStandardId;
   }
 
   public Long getNewStandardId() {
     return this.newStandardId;
   }
 
   public void setNewStandardId(Long newStandardId) {
     this.newStandardId = newStandardId;
   }
 
   public void setChangeId(Long aValue)
   {
     this.changeId = aValue;
   }
 
   public Date getRegTime() {
     return this.regTime;
   }
 
   public void setRegTime(Date regTime) {
     this.regTime = regTime;
   }
 
   public Long getProfileId()
   {
     return this.profileId;
   }
 
   public void setProfileId(Long aValue)
   {
     this.profileId = aValue;
   }
 
   public String getProfileNo()
   {
     return this.profileNo;
   }
 
   public void setProfileNo(String aValue)
   {
     this.profileNo = aValue;
   }
 
   public String getUserName()
   {
     return this.userName;
   }
 
   public void setUserName(String aValue)
   {
     this.userName = aValue;
   }
 
   public Long getOrgJobId()
   {
     return this.orgJobId;
   }
 
   public void setOrgJobId(Long aValue)
   {
     this.orgJobId = aValue;
   }
 
   public String getOrgJobName()
   {
     return this.orgJobName;
   }
 
   public void setOrgJobName(String aValue)
   {
     this.orgJobName = aValue;
   }
 
   public Long getNewJobId()
   {
     return this.newJobId;
   }
 
   public void setNewJobId(Long aValue)
   {
     this.newJobId = aValue;
   }
 
   public String getNewJobName()
   {
     return this.newJobName;
   }
 
   public void setNewJobName(String aValue)
   {
     this.newJobName = aValue;
   }
 
   public String getOrgStandardNo()
   {
     return this.orgStandardNo;
   }
 
   public void setOrgStandardNo(String aValue)
   {
     this.orgStandardNo = aValue;
   }
 
   public String getOrgStandardName()
   {
     return this.orgStandardName;
   }
 
   public void setOrgStandardName(String aValue)
   {
     this.orgStandardName = aValue;
   }
 
   public Long getOrgDepId()
   {
     return this.orgDepId;
   }
 
   public void setOrgDepId(Long aValue)
   {
     this.orgDepId = aValue;
   }
 
   public String getOrgDepName()
   {
     return this.orgDepName;
   }
 
   public void setOrgDepName(String aValue)
   {
     this.orgDepName = aValue;
   }
 
   public BigDecimal getOrgTotalMoney()
   {
     return this.orgTotalMoney;
   }
 
   public void setOrgTotalMoney(BigDecimal aValue)
   {
     this.orgTotalMoney = aValue;
   }
 
   public String getNewStandardNo()
   {
     return this.newStandardNo;
   }
 
   public void setNewStandardNo(String aValue)
   {
     this.newStandardNo = aValue;
   }
 
   public String getNewStandardName()
   {
     return this.newStandardName;
   }
 
   public void setNewStandardName(String aValue)
   {
     this.newStandardName = aValue;
   }
 
   public Long getNewDepId()
   {
     return this.newDepId;
   }
 
   public void setNewDepId(Long aValue)
   {
     this.newDepId = aValue;
   }
 
   public String getNewDepName()
   {
     return this.newDepName;
   }
 
   public void setNewDepName(String aValue)
   {
     this.newDepName = aValue;
   }
 
   public BigDecimal getNewTotalMoney()
   {
     return this.newTotalMoney;
   }
 
   public void setNewTotalMoney(BigDecimal aValue)
   {
     this.newTotalMoney = aValue;
   }
 
   public String getChangeReason()
   {
     return this.changeReason;
   }
 
   public void setChangeReason(String aValue)
   {
     this.changeReason = aValue;
   }
 
   public String getRegName()
   {
     return this.regName;
   }
 
   public void setRegName(String aValue)
   {
     this.regName = aValue;
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
 
   public String getCheckOpinion()
   {
     return this.checkOpinion;
   }
 
   public void setCheckOpinion(String aValue)
   {
     this.checkOpinion = aValue;
   }
 
   public Short getStatus()
   {
     return this.status;
   }
 
   public void setStatus(Short aValue)
   {
     this.status = aValue;
   }
 
   public String getMemo()
   {
     return this.memo;
   }
 
   public void setMemo(String aValue)
   {
     this.memo = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof JobChange) {
       return false;
     }
     JobChange rhs = (JobChange)object;
     return new EqualsBuilder()
       .append(this.changeId, rhs.changeId)
       .append(this.profileId, rhs.profileId)
       .append(this.profileNo, rhs.profileNo)
       .append(this.userName, rhs.userName)
       .append(this.orgJobId, rhs.orgJobId)
       .append(this.orgStandardId, rhs.orgStandardId)
       .append(this.newStandardId, rhs.newStandardId)
       .append(this.orgJobName, rhs.orgJobName)
       .append(this.newJobId, rhs.newJobId)
       .append(this.newJobName, rhs.newJobName)
       .append(this.orgStandardNo, rhs.orgStandardNo)
       .append(this.orgStandardName, rhs.orgStandardName)
       .append(this.orgDepId, rhs.orgDepId)
       .append(this.orgDepName, rhs.orgDepName)
       .append(this.orgTotalMoney, rhs.orgTotalMoney)
       .append(this.newStandardNo, rhs.newStandardNo)
       .append(this.newStandardName, rhs.newStandardName)
       .append(this.newDepId, rhs.newDepId)
       .append(this.newDepName, rhs.newDepName)
       .append(this.newTotalMoney, rhs.newTotalMoney)
       .append(this.changeReason, rhs.changeReason)
       .append(this.regName, rhs.regName)
       .append(this.regTime, rhs.regTime)
       .append(this.checkName, rhs.checkName)
       .append(this.checkTime, rhs.checkTime)
       .append(this.checkOpinion, rhs.checkOpinion)
       .append(this.status, rhs.status)
       .append(this.memo, rhs.memo)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.changeId)
       .append(this.profileId)
       .append(this.profileNo)
       .append(this.userName)
       .append(this.orgJobId)
       .append(this.orgJobName)
       .append(this.newJobId)
       .append(this.newJobName)
       .append(this.orgStandardNo)
       .append(this.orgStandardName)
       .append(this.orgDepId)
       .append(this.orgStandardId)
       .append(this.newStandardId)
       .append(this.orgDepName)
       .append(this.orgTotalMoney)
       .append(this.newStandardNo)
       .append(this.newStandardName)
       .append(this.newDepId)
       .append(this.newDepName)
       .append(this.newTotalMoney)
       .append(this.changeReason)
       .append(this.regName)
       .append(this.regTime)
       .append(this.checkName)
       .append(this.checkTime)
       .append(this.checkOpinion)
       .append(this.status)
       .append(this.memo)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("changeId", this.changeId)
       .append("profileId", this.profileId)
       .append("profileNo", this.profileNo)
       .append("userName", this.userName)
       .append("orgJobId", this.orgJobId)
       .append("orgJobName", this.orgJobName)
       .append("newJobId", this.newJobId)
       .append("newJobName", this.newJobName)
       .append("orgStandardId", this.orgStandardId)
       .append("newStandardId", this.newStandardId)
       .append("orgStandardNo", this.orgStandardNo)
       .append("orgStandardName", this.orgStandardName)
       .append("orgDepId", this.orgDepId)
       .append("orgDepName", this.orgDepName)
       .append("orgTotalMoney", this.orgTotalMoney)
       .append("newStandardNo", this.newStandardNo)
       .append("newStandardName", this.newStandardName)
       .append("newDepId", this.newDepId)
       .append("newDepName", this.newDepName)
       .append("newTotalMoney", this.newTotalMoney)
       .append("changeReason", this.changeReason)
       .append("regName", this.regName)
       .append("regTime", this.regTime)
       .append("checkName", this.checkName)
       .append("checkTime", this.checkTime)
       .append("checkOpinion", this.checkOpinion)
       .append("status", this.status)
       .append("memo", this.memo)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.hrm.JobChange
 * JD-Core Version:    0.5.4
 */