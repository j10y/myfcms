 package com.htsoft.oa.model.task;
 
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.Department;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class PlanAttend extends BaseModel
 {
   protected Long attendId;
   protected Short isDep;
   protected Short isPrimary;
   protected WorkPlan workPlan;
   protected Department department;
   protected AppUser appUser;
 
   public PlanAttend()
   {
   }
 
   public PlanAttend(Long in_attendId)
   {
     setAttendId(in_attendId);
   }
 
   public WorkPlan getWorkPlan()
   {
     return this.workPlan;
   }
 
   public void setWorkPlan(WorkPlan in_workPlan) {
     this.workPlan = in_workPlan;
   }
 
   public Department getDepartment() {
     return this.department;
   }
 
   public void setDepartment(Department in_department) {
     this.department = in_department;
   }
 
   public AppUser getAppUser() {
     return this.appUser;
   }
 
   public void setAppUser(AppUser in_appUser) {
     this.appUser = in_appUser;
   }
 
   public Long getAttendId()
   {
     return this.attendId;
   }
 
   public void setAttendId(Long aValue)
   {
     this.attendId = aValue;
   }
 
   public Long getDepId()
   {
     return (getDepartment() == null) ? null : getDepartment().getDepId();
   }
 
   public void setDepId(Long aValue)
   {
     if (aValue == null) {
       this.department = null;
     } else if (this.department == null) {
       this.department = new Department(aValue);
       this.department.setVersion(new Integer(0));
     } else {
       this.department.setDepId(aValue);
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
 
   public Long getPlanId()
   {
     return (getWorkPlan() == null) ? null : getWorkPlan().getPlanId();
   }
 
   public void setPlanId(Long aValue)
   {
     if (aValue == null) {
       this.workPlan = null;
     } else if (this.workPlan == null) {
       this.workPlan = new WorkPlan(aValue);
       this.workPlan.setVersion(new Integer(0));
     } else {
       this.workPlan.setPlanId(aValue);
     }
   }
 
   public Short getIsDep()
   {
     return this.isDep;
   }
 
   public void setIsDep(Short aValue)
   {
     this.isDep = aValue;
   }
 
   public Short getIsPrimary()
   {
     return this.isPrimary;
   }
 
   public void setIsPrimary(Short aValue)
   {
     this.isPrimary = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof PlanAttend) {
       return false;
     }
     PlanAttend rhs = (PlanAttend)object;
     return new EqualsBuilder()
       .append(this.attendId, rhs.attendId)
       .append(this.isDep, rhs.isDep)
       .append(this.isPrimary, rhs.isPrimary)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.attendId)
       .append(this.isDep)
       .append(this.isPrimary)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("attendId", this.attendId)
       .append("isDep", this.isDep)
       .append("isPrimary", this.isPrimary)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.task.PlanAttend
 * JD-Core Version:    0.5.4
 */