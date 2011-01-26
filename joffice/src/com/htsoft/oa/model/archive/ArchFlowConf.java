 package com.htsoft.oa.model.archive;
 
 import com.htsoft.core.model.BaseModel;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class ArchFlowConf extends BaseModel
 {
   public static Short ARCH_SEND_TYPE = Short.valueOf(0);
   public static Short ARCH_REC_TYPE = Short.valueOf(1);
   protected Long configId;
   protected String processName;
   protected Long processDefId;
   protected Short archType;
 
   public ArchFlowConf()
   {
   }
 
   public ArchFlowConf(Long in_configId)
   {
     setConfigId(in_configId);
   }
 
   public Long getConfigId()
   {
     return this.configId;
   }
 
   public void setConfigId(Long aValue)
   {
     this.configId = aValue;
   }
 
   public String getProcessName()
   {
     return this.processName;
   }
 
   public void setProcessName(String aValue)
   {
     this.processName = aValue;
   }
 
   public Long getProcessDefId()
   {
     return this.processDefId;
   }
 
   public void setProcessDefId(Long aValue)
   {
     this.processDefId = aValue;
   }
 
   public Short getArchType()
   {
     return this.archType;
   }
 
   public void setArchType(Short aValue)
   {
     this.archType = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof ArchFlowConf) {
       return false;
     }
     ArchFlowConf rhs = (ArchFlowConf)object;
     return new EqualsBuilder()
       .append(this.configId, rhs.configId)
       .append(this.processName, rhs.processName)
       .append(this.processDefId, rhs.processDefId)
       .append(this.archType, rhs.archType)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.configId)
       .append(this.processName)
       .append(this.processDefId)
       .append(this.archType)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("configId", this.configId)
       .append("processName", this.processName)
       .append("processDefId", this.processDefId)
       .append("archType", this.archType)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.archive.ArchFlowConf
 * JD-Core Version:    0.5.4
 */