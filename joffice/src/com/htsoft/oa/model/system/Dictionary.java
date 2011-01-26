 package com.htsoft.oa.model.system;
 
 import com.htsoft.core.model.BaseModel;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class Dictionary extends BaseModel
 {
   protected Long dicId;
   protected String itemName;
   protected String itemValue;
   protected String descp;
 
   public Dictionary()
   {
   }
 
   public Dictionary(Long in_dicId)
   {
     setDicId(in_dicId);
   }
 
   public Long getDicId()
   {
     return this.dicId;
   }
 
   public void setDicId(Long aValue)
   {
     this.dicId = aValue;
   }
 
   public String getItemName()
   {
     return this.itemName;
   }
 
   public void setItemName(String aValue)
   {
     this.itemName = aValue;
   }
 
   public String getItemValue()
   {
     return this.itemValue;
   }
 
   public void setItemValue(String aValue)
   {
     this.itemValue = aValue;
   }
 
   public String getDescp()
   {
     return this.descp;
   }
 
   public void setDescp(String aValue)
   {
     this.descp = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof Dictionary) {
       return false;
     }
     Dictionary rhs = (Dictionary)object;
     return new EqualsBuilder()
       .append(this.dicId, rhs.dicId)
       .append(this.itemName, rhs.itemName)
       .append(this.itemValue, rhs.itemValue)
       .append(this.descp, rhs.descp)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.dicId)
       .append(this.itemName)
       .append(this.itemValue)
       .append(this.descp)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("dicId", this.dicId)
       .append("itemName", this.itemName)
       .append("itemValue", this.itemValue)
       .append("descp", this.descp)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.system.Dictionary
 * JD-Core Version:    0.5.4
 */