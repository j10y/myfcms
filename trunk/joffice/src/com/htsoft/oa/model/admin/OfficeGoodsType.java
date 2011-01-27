 package com.htsoft.oa.model.admin;
 
 import com.htsoft.core.model.BaseModel;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class OfficeGoodsType extends BaseModel
 {
   protected Long typeId;
   protected String typeName;
 
   public OfficeGoodsType()
   {
   }
 
   public OfficeGoodsType(Long in_typeId)
   {
     setTypeId(in_typeId);
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
 
   public boolean equals(Object object)
   {
     if (!(object instanceof OfficeGoodsType)) {
       return false;
     }
     OfficeGoodsType rhs = (OfficeGoodsType)object;
     return new EqualsBuilder()
       .append(this.typeId, rhs.typeId)
       .append(this.typeName, rhs.typeName)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.typeId)
       .append(this.typeName)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("typeId", this.typeId)
       .append("typeName", this.typeName)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.OfficeGoodsType
 * JD-Core Version:    0.5.4
 */