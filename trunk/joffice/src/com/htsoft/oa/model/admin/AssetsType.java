 package com.htsoft.oa.model.admin;
 
 import com.htsoft.core.model.BaseModel;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class AssetsType extends BaseModel
 {
   protected Long assetsTypeId;
   protected String typeName;
 
   public AssetsType()
   {
   }
 
   public AssetsType(Long in_assetsTypeId)
   {
     setAssetsTypeId(in_assetsTypeId);
   }
 
   public Long getAssetsTypeId()
   {
     return this.assetsTypeId;
   }
 
   public void setAssetsTypeId(Long aValue)
   {
     this.assetsTypeId = aValue;
   }
 
   public String getTypeName()
   {
     return this.typeName;
   }
 
   public void setTypeName(String aValue)
   {
     this.typeName = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof AssetsType) {
       return false;
     }
     AssetsType rhs = (AssetsType)object;
     return new EqualsBuilder()
       .append(this.assetsTypeId, rhs.assetsTypeId)
       .append(this.typeName, rhs.typeName)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.assetsTypeId)
       .append(this.typeName)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("assetsTypeId", this.assetsTypeId)
       .append("typeName", this.typeName)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.AssetsType
 * JD-Core Version:    0.5.4
 */