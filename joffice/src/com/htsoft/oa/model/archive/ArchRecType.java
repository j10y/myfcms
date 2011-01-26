 package com.htsoft.oa.model.archive;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.Department;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class ArchRecType extends BaseModel
 {
 
   @Expose
   protected Long recTypeId;
 
   @Expose
   protected String typeName;
 
   @Expose
   protected Department department;
   protected Set archivesDeps = new HashSet();
 
   protected Set archives = new HashSet();
 
   public ArchRecType()
   {
   }
 
   public ArchRecType(Long in_typeId)
   {
     setRecTypeId(in_typeId);
   }
 
   public Set getArchives()
   {
     return this.archives;
   }
 
   public void setArchives(Set archives) {
     this.archives = archives;
   }
 
   public Department getDepartment() {
     return this.department;
   }
 
   public void setDepartment(Department in_department) {
     this.department = in_department;
   }
 
   public Set getArchivesDeps() {
     return this.archivesDeps;
   }
 
   public void setArchivesDeps(Set in_archivesDeps) {
     this.archivesDeps = in_archivesDeps;
   }
 
   public Long getRecTypeId()
   {
     return this.recTypeId;
   }
 
   public void setRecTypeId(Long aValue)
   {
     this.recTypeId = aValue;
   }
 
   public String getTypeName()
   {
     return this.typeName;
   }
 
   public void setTypeName(String aValue)
   {
     this.typeName = aValue;
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
 
   public boolean equals(Object object)
   {
     if (!object instanceof ArchRecType) {
       return false;
     }
     ArchRecType rhs = (ArchRecType)object;
     return new EqualsBuilder()
       .append(this.recTypeId, rhs.recTypeId)
       .append(this.typeName, rhs.typeName)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.recTypeId)
       .append(this.typeName)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("typeId", this.recTypeId)
       .append("typeName", this.typeName)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.archive.ArchRecType
 * JD-Core Version:    0.5.4
 */