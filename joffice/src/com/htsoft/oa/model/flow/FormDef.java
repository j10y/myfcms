 package com.htsoft.oa.model.flow;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class FormDef extends BaseModel
 {
   public static final Integer DEFAULT_COLUMNS = Integer.valueOf(1);
 
   @Expose
   protected Long formDefId;
 
   @Expose
   protected String formName;
 
   @Expose
   protected Integer columns;
 
   @Expose
   protected Short isEnabled;
 
   @Expose
   protected String activityName;
 
   @Expose
   protected String extDef;
 
   @Expose
   protected String deployId;
 
   public FormDef()
   {
   }
 
   public FormDef(Long in_formDefId)
   {
     setFormDefId(in_formDefId);
   }
 
   public String getExtDef()
   {
     return this.extDef;
   }
 
   public void setExtDef(String extDef) {
     this.extDef = extDef;
   }
 
   public Long getFormDefId()
   {
     return this.formDefId;
   }
 
   public void setFormDefId(Long aValue)
   {
     this.formDefId = aValue;
   }
 
   public String getFormName()
   {
     return this.formName;
   }
 
   public void setFormName(String aValue)
   {
     this.formName = aValue;
   }
 
   public Integer getColumns()
   {
     return this.columns;
   }
 
   public void setColumns(Integer aValue)
   {
     this.columns = aValue;
   }
 
   public Short getIsEnabled()
   {
     return this.isEnabled;
   }
 
   public void setIsEnabled(Short aValue)
   {
     this.isEnabled = aValue;
   }
 
   public String getActivityName()
   {
     return this.activityName;
   }
 
   public void setActivityName(String aValue)
   {
     this.activityName = aValue;
   }
 
   public String getDeployId()
   {
     return this.deployId;
   }
 
   public void setDeployId(String aValue)
   {
     this.deployId = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof FormDef)) {
       return false;
     }
     FormDef rhs = (FormDef)object;
     return new EqualsBuilder()
       .append(this.formDefId, rhs.formDefId)
       .append(this.formName, rhs.formName)
       .append(this.columns, rhs.columns)
       .append(this.isEnabled, rhs.isEnabled)
       .append(this.activityName, rhs.activityName)
       .append(this.deployId, rhs.deployId)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.formDefId)
       .append(this.formName)
       .append(this.columns)
       .append(this.isEnabled)
       .append(this.activityName)
       .append(this.deployId)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("formDefId", this.formDefId)
       .append("formName", this.formName)
       .append("columns", this.columns)
       .append("isEnabled", this.isEnabled)
       .append("activityName", this.activityName)
       .append("deployId", this.deployId)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.FormDef
 * JD-Core Version:    0.5.4
 */