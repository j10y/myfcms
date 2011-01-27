 package com.htsoft.oa.model.system;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class UserSub extends BaseModel
 {
 
   @Expose
   protected Long subId;
 
   @Expose
   protected AppUser subAppUser;
 
   @Expose
   protected Long userId;
 
   public UserSub()
   {
   }
 
   public UserSub(Long in_subId)
   {
     setSubId(in_subId);
   }
 
   public AppUser getSubAppUser()
   {
     return this.subAppUser;
   }
 
   public void setSubAppUser(AppUser subAppUser) {
     this.subAppUser = subAppUser;
   }
 
   public Long getSubId()
   {
     return this.subId;
   }
 
   public void setSubId(Long aValue)
   {
     this.subId = aValue;
   }
 
   public Long getSubUserId()
   {
     return (getSubAppUser() == null) ? null : this.subAppUser.getUserId();
   }
 
   public void setSubUserId(Long aValue)
   {
     if (aValue == null) {
       this.subAppUser = null;
     } else if (this.subAppUser == null) {
       this.subAppUser = new AppUser(aValue);
       this.subAppUser.setVersion(new Integer(0));
     } else {
       this.subAppUser.setUserId(aValue);
     }
   }
 
   public Long getUserId()
   {
     return this.userId;
   }
 
   public void setUserId(Long aValue)
   {
     this.userId = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof UserSub)) {
       return false;
     }
     UserSub rhs = (UserSub)object;
     return new EqualsBuilder()
       .append(this.subId, rhs.subId)
       .append(this.userId, rhs.userId)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.subId)
       .append(this.userId)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("subId", this.subId)
       .append("userId", this.userId)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.system.UserSub
 * JD-Core Version:    0.5.4
 */