 package com.htsoft.oa.model.task;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.FileAttach;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class WorkPlan extends BaseModel
 {
 
   @Expose
   protected Long planId;
 
   @Expose
   protected String planName;
 
   @Expose
   protected String planContent;
 
   @Expose
   protected Date startTime;
 
   @Expose
   protected Date endTime;
 
   @Expose
   protected String issueScope;
 
   @Expose
   protected String participants;
 
   @Expose
   protected String principal;
 
   @Expose
   protected String note;
 
   @Expose
   protected Short status;
 
   @Expose
   protected Short isPersonal;
 
   @Expose
   protected String icon;
 
   @Expose
   protected PlanType planType;
 
   @Expose
   protected AppUser appUser;
 
   @Expose
   protected Set<FileAttach> planFiles = new HashSet();
   protected Set<PlanAttend> planAttends = new HashSet();
 
   public WorkPlan()
   {
   }
 
   public WorkPlan(Long in_planId)
   {
     setPlanId(in_planId);
   }
 
   public Set<PlanAttend> getPlanAttends()
   {
     return this.planAttends;
   }
 
   public void setPlanAttends(Set<PlanAttend> planAttends) {
     this.planAttends = planAttends;
   }
 
   public PlanType getPlanType() {
     return this.planType;
   }
 
   public void setPlanType(PlanType in_planType) {
     this.planType = in_planType;
   }
 
   public AppUser getAppUser() {
     return this.appUser;
   }
 
   public void setAppUser(AppUser in_appUser) {
     this.appUser = in_appUser;
   }
 
   public Set<FileAttach> getPlanFiles() {
     return this.planFiles;
   }
 
   public void setPlanFiles(Set<FileAttach> in_planFiles) {
     this.planFiles = in_planFiles;
   }
 
   public Long getPlanId()
   {
     return this.planId;
   }
 
   public void setPlanId(Long aValue)
   {
     this.planId = aValue;
   }
 
   public String getPlanName()
   {
     return this.planName;
   }
 
   public void setPlanName(String aValue)
   {
     this.planName = aValue;
   }
 
   public String getPlanContent()
   {
     return this.planContent;
   }
 
   public void setPlanContent(String aValue)
   {
     this.planContent = aValue;
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
 
   public Long getTypeId()
   {
     return (getPlanType() == null) ? null : getPlanType().getTypeId();
   }
 
   public void setTypeId(Long aValue)
   {
     if (aValue == null) {
       this.planType = null;
     } else if (this.planType == null) {
       this.planType = new PlanType(aValue);
       this.planType.setVersion(new Integer(0));
     } else {
       this.planType.setTypeId(aValue);
     }
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
 
   public String getIssueScope()
   {
     return this.issueScope;
   }
 
   public void setIssueScope(String aValue)
   {
     this.issueScope = aValue;
   }
 
   public String getParticipants()
   {
     return this.participants;
   }
 
   public void setParticipants(String aValue)
   {
     this.participants = aValue;
   }
 
   public String getPrincipal()
   {
     return this.principal;
   }
 
   public void setPrincipal(String aValue)
   {
     this.principal = aValue;
   }
 
   public String getNote()
   {
     return this.note;
   }
 
   public void setNote(String aValue)
   {
     this.note = aValue;
   }
 
   public Short getStatus()
   {
     return this.status;
   }
 
   public void setStatus(Short aValue)
   {
     this.status = aValue;
   }
 
   public Short getIsPersonal()
   {
     return this.isPersonal;
   }
 
   public void setIsPersonal(Short aValue)
   {
     this.isPersonal = aValue;
   }
 
   public String getIcon()
   {
     return this.icon;
   }
 
   public void setIcon(String aValue)
   {
     this.icon = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof WorkPlan) {
       return false;
     }
     WorkPlan rhs = (WorkPlan)object;
     return new EqualsBuilder()
       .append(this.planId, rhs.planId)
       .append(this.planName, rhs.planName)
       .append(this.planContent, rhs.planContent)
       .append(this.startTime, rhs.startTime)
       .append(this.endTime, rhs.endTime)
       .append(this.issueScope, rhs.issueScope)
       .append(this.participants, rhs.participants)
       .append(this.principal, rhs.principal)
       .append(this.note, rhs.note)
       .append(this.status, rhs.status)
       .append(this.isPersonal, rhs.isPersonal)
       .append(this.icon, rhs.icon)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.planId)
       .append(this.planName)
       .append(this.planContent)
       .append(this.startTime)
       .append(this.endTime)
       .append(this.issueScope)
       .append(this.participants)
       .append(this.principal)
       .append(this.note)
       .append(this.status)
       .append(this.isPersonal)
       .append(this.icon)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("planId", this.planId)
       .append("planName", this.planName)
       .append("planContent", this.planContent)
       .append("startTime", this.startTime)
       .append("endTime", this.endTime)
       .append("issueScope", this.issueScope)
       .append("participants", this.participants)
       .append("principal", this.principal)
       .append("note", this.note)
       .append("status", this.status)
       .append("isPersonal", this.isPersonal)
       .append("icon", this.icon)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.task.WorkPlan
 * JD-Core Version:    0.5.4
 */