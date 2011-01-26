 package com.htsoft.oa.model.flow;
 
 import com.htsoft.core.model.BaseModel;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class ProUserAssign extends BaseModel
 {
   protected Long assignId;
   protected String deployId;
   protected String activityName;
   protected String roleId;
   protected String roleName;
   protected String userId;
   protected String username;
   protected Short isSigned;
 
   public ProUserAssign()
   {
   }
 
   public ProUserAssign(Long in_assignId)
   {
     setAssignId(in_assignId);
   }
 
   public Short getIsSigned() {
     return this.isSigned;
   }
 
   public void setIsSigned(Short isSigned) {
     this.isSigned = isSigned;
   }
 
   public Long getAssignId()
   {
     return this.assignId;
   }
 
   public void setAssignId(Long aValue)
   {
     this.assignId = aValue;
   }
 
   public String getDeployId()
   {
     return this.deployId;
   }
 
   public void setDeployId(String aValue)
   {
     this.deployId = aValue;
   }
 
   public String getActivityName()
   {
     return this.activityName;
   }
 
   public void setActivityName(String aValue)
   {
     this.activityName = aValue;
   }
 
   public String getRoleId()
   {
     return this.roleId;
   }
 
   public void setRoleId(String aValue)
   {
     this.roleId = aValue;
   }
 
   public String getUserId()
   {
     return this.userId;
   }
 
   public void setUserId(String aValue)
   {
     this.userId = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof ProUserAssign) {
       return false;
     }
     ProUserAssign rhs = (ProUserAssign)object;
     return new EqualsBuilder()
       .append(this.assignId, rhs.assignId)
       .append(this.deployId, rhs.deployId)
       .append(this.activityName, rhs.activityName)
       .append(this.roleId, rhs.roleId)
       .append(this.userId, rhs.userId)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.assignId)
       .append(this.deployId)
       .append(this.activityName)
       .append(this.roleId)
       .append(this.userId)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("assignId", this.assignId)
       .append("deployId", this.deployId)
       .append("activityName", this.activityName)
       .append("roleId", this.roleId)
       .append("userId", this.userId)
       .toString();
   }
 
   public String getFirstKeyColumnName()
   {
     return "assignId";
   }
 
   public Long getId()
   {
     return this.assignId;
   }
 
   public String getRoleName() {
     return this.roleName;
   }
 
   public void setRoleName(String roleName) {
     this.roleName = roleName;
   }
 
   public String getUsername() {
     return this.username;
   }
 
   public void setUsername(String username) {
     this.username = username;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.ProUserAssign
 * JD-Core Version:    0.5.4
 */