 package com.htsoft.oa.model.personal;
 
 import com.htsoft.core.model.BaseModel;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class DutySystem extends BaseModel
 {
   public static final Short DEFAULT = Short.valueOf(1);
 
   public static final Short NOT_DEFAULT = Short.valueOf(0);
   protected Long systemId;
   protected String systemName;
   protected String systemSetting;
   protected String systemDesc;
   protected Short isDefault;
 
   public DutySystem()
   {
   }
 
   public DutySystem(Long in_systemId)
   {
     setSystemId(in_systemId);
   }
 
   public Long getSystemId()
   {
     return this.systemId;
   }
 
   public void setSystemId(Long aValue)
   {
     this.systemId = aValue;
   }
 
   public String getSystemName()
   {
     return this.systemName;
   }
 
   public void setSystemName(String aValue)
   {
     this.systemName = aValue;
   }
 
   public String getSystemSetting()
   {
     return this.systemSetting;
   }
 
   public void setSystemSetting(String aValue)
   {
     this.systemSetting = aValue;
   }
 
   public String getSystemDesc()
   {
     return this.systemDesc;
   }
 
   public void setSystemDesc(String aValue)
   {
     this.systemDesc = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof DutySystem) {
       return false;
     }
     DutySystem rhs = (DutySystem)object;
     return new EqualsBuilder()
       .append(this.systemId, rhs.systemId)
       .append(this.systemName, rhs.systemName)
       .append(this.systemSetting, rhs.systemSetting)
       .append(this.systemDesc, rhs.systemDesc)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.systemId)
       .append(this.systemName)
       .append(this.systemSetting)
       .append(this.systemDesc)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("systemId", this.systemId)
       .append("systemName", this.systemName)
       .append("systemSetting", this.systemSetting)
       .append("systemDesc", this.systemDesc)
       .toString();
   }
 
   public Short getIsDefault() {
     return this.isDefault;
   }
 
   public void setIsDefault(Short isDefault) {
     this.isDefault = isDefault;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.personal.DutySystem
 * JD-Core Version:    0.5.4
 */