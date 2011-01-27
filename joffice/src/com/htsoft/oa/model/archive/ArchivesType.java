 package com.htsoft.oa.model.archive;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class ArchivesType extends BaseModel
 {
 
   @Expose
   protected Long typeId;
 
   @Expose
   protected String typeName;
 
   @Expose
   protected String typeDesc;
   protected Set archTemplates = new HashSet();
 
   public ArchivesType()
   {
   }
 
   public ArchivesType(Long in_typeId)
   {
     setTypeId(in_typeId);
   }
 
   public Set getArchTemplates()
   {
     return this.archTemplates;
   }
 
   public void setArchTemplates(Set in_archTemplates) {
     this.archTemplates = in_archTemplates;
   }
 
   public Long getTypeId()
   {
     return this.typeId;
   }
 
   public void setTypeId(Long aValue)
   {
     this.typeId = aValue;
   }
 
   public String getTypeName()
   {
     return this.typeName;
   }
 
   public void setTypeName(String aValue)
   {
     this.typeName = aValue;
   }
 
   public String getTypeDesc()
   {
     return this.typeDesc;
   }
 
   public void setTypeDesc(String aValue)
   {
     this.typeDesc = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof ArchivesType)) {
       return false;
     }
     ArchivesType rhs = (ArchivesType)object;
     return new EqualsBuilder()
       .append(this.typeId, rhs.typeId)
       .append(this.typeName, rhs.typeName)
       .append(this.typeDesc, rhs.typeDesc)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.typeId)
       .append(this.typeName)
       .append(this.typeDesc)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("typeId", this.typeId)
       .append("typeName", this.typeName)
       .append("typeDesc", this.typeDesc)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.archive.ArchivesType
 * JD-Core Version:    0.5.4
 */