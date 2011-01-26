 package com.htsoft.oa.model.flow;
 
 import com.htsoft.core.model.BaseModel;
 import flexjson.JSON;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class ProDefinition extends BaseModel
 {
   public static final Short IS_DEFAULT = Short.valueOf(1);
   public static final Short IS_NOT_DEFAULT = Short.valueOf(0);
   protected Long defId;
   protected String name;
   protected String description;
   protected Date createtime;
   protected String deployId;
   protected String defXml;
   protected String drawDefXml;
   protected Short isDefault = IS_NOT_DEFAULT;
   protected ProType proType;
 
   @JSON
   public String getDefXml()
   {
     return this.defXml;
   }
 
   public void setDefXml(String defXml) {
     this.defXml = defXml;
   }
 
   public ProDefinition()
   {
   }
 
   public ProDefinition(Long in_defId)
   {
     setDefId(in_defId);
   }
 
   public ProType getProType()
   {
     return this.proType;
   }
 
   public void setProType(ProType in_proType) {
     this.proType = in_proType;
   }
 
   public void setProTypeId(Long proTypeId) {
     if (this.proType == null) {
       this.proType = new ProType();
     }
     this.proType.setTypeId(proTypeId);
   }
 
   public Long getProTypeId() {
     return (this.proType == null) ? null : this.proType.getTypeId();
   }
 
   public Long getDefId()
   {
     return this.defId;
   }
 
   public void setDefId(Long aValue)
   {
     this.defId = aValue;
   }
 
   public Long getTypeId()
   {
     return (getProType() == null) ? null : getProType().getTypeId();
   }
 
   public void setTypeId(Long aValue)
   {
     if (aValue == null)
       this.proType = null;
     else if (this.proType == null) {
       this.proType = new ProType(aValue);
     }
     else
       this.proType.setTypeId(aValue);
   }
 
   public String getName()
   {
     return this.name;
   }
 
   public void setName(String aValue)
   {
     this.name = aValue;
   }
 
   public String getDescription()
   {
     return this.description;
   }
 
   public void setDescription(String aValue)
   {
     this.description = aValue;
   }
 
   public Date getCreatetime()
   {
     return this.createtime;
   }
 
   public void setCreatetime(Date aValue)
   {
     this.createtime = aValue;
   }
 
   public String getDeployId()
   {
     return this.deployId;
   }
 
   public void setDeployId(String aValue)
   {
     this.deployId = aValue;
   }
 
   public String getDrawDefXml()
   {
     return this.drawDefXml;
   }
 
   public void setDrawDefXml(String drawDefXml) {
     this.drawDefXml = drawDefXml;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof ProDefinition) {
       return false;
     }
     ProDefinition rhs = (ProDefinition)object;
     return new EqualsBuilder()
       .append(this.defId, rhs.defId)
       .append(this.name, rhs.name)
       .append(this.description, rhs.description)
       .append(this.createtime, rhs.createtime)
       .append(this.deployId, rhs.deployId)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.defId).append(this.name)
       .append(this.description)
       .append(this.createtime)
       .append(this.deployId)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("defId", this.defId)
       .append("name", this.name)
       .append("description", this.description)
       .append("createtime", this.createtime)
       .append("deployId", this.deployId)
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
 * Qualified Name:     com.htsoft.oa.model.flow.ProDefinition
 * JD-Core Version:    0.5.4
 */