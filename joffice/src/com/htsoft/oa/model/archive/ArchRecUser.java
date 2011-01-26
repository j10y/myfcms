 package com.htsoft.oa.model.archive;
 
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.Department;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class ArchRecUser extends BaseModel
 {
   protected Long archRecId;
   protected Long userId;
   protected String fullname;
   protected Long depId;
   protected String depName;
   protected Department department;
 
   public Department getDepartment()
   {
     return this.department;
   }
 
   public void setDepartment(Department department) {
     this.department = department;
   }
 
   public ArchRecUser()
   {
   }
 
   public ArchRecUser(Long in_archRecId)
   {
     setArchRecId(in_archRecId);
   }
 
   public Long getArchRecId()
   {
     return this.archRecId;
   }
 
   public void setArchRecId(Long aValue)
   {
     this.archRecId = aValue;
   }
 
   public Long getUserId()
   {
     return this.userId;
   }
 
   public void setUserId(Long aValue)
   {
     this.userId = aValue;
   }
 
   public String getFullname()
   {
     return this.fullname;
   }
 
   public void setFullname(String aValue)
   {
     this.fullname = aValue;
   }
 
   public Long getDepId()
   {
     return this.depId;
   }
 
   public void setDepId(Long aValue)
   {
     this.depId = aValue;
   }
 
   public String getDepName()
   {
     return this.depName;
   }
 
   public void setDepName(String aValue)
   {
     this.depName = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof ArchRecUser) {
       return false;
     }
     ArchRecUser rhs = (ArchRecUser)object;
     return new EqualsBuilder()
       .append(this.archRecId, rhs.archRecId)
       .append(this.userId, rhs.userId)
       .append(this.fullname, rhs.fullname)
       .append(this.depName, rhs.depName)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.archRecId)
       .append(this.userId)
       .append(this.fullname)
       .append(this.depName)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("archRecId", this.archRecId)
       .append("userId", this.userId)
       .append("fullname", this.fullname)
       .append("depName", this.depName)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.archive.ArchRecUser
 * JD-Core Version:    0.5.4
 */