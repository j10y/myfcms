 package com.htsoft.oa.model.flow;
 
 import com.htsoft.core.jbpm.pv.ParamField;
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 import org.apache.commons.lang.time.DateUtils;
 import org.apache.commons.logging.Log;
 
 public class FormData extends BaseModel
 {
   public static final Short SHOWED = Short.valueOf(1);
 
   public static final Short UNSHOWED = Short.valueOf(0);
   protected Long dataId;
   protected String fieldLabel;
   protected String fieldName;
   protected Integer intValue;
   protected Long longValue;
   protected BigDecimal decValue;
   protected Date dateValue;
   protected String strValue;
   protected String blobValue;
   protected Short boolValue;
   protected String textValue;
   protected Short isShowed = Short.valueOf(1);
   protected String fieldType;
   protected ProcessForm processForm;
 
   public FormData(ParamField pf)
   {
     copyValue(pf);
   }
 
   public void copyValue(ParamField pf) {
     this.fieldLabel = pf.getLabel();
     this.fieldName = pf.getName();
     this.fieldType = pf.getType();
     this.isShowed = pf.getIsShowed();
 
     setValue(pf.getValue(), pf.getType());
   }
 
   public Object getValue()
   {
     if (this.strValue != null) return this.strValue;
     if (this.intValue != null) return this.intValue;
     if (this.longValue != null) return this.longValue;
     if (this.decValue != null) return this.decValue;
     if (this.dateValue != null) return this.dateValue;
     if (this.boolValue != null) return this.boolValue;
     if (this.textValue != null) return this.textValue;
     return null;
   }
 
   public String getVal()
   {
     if ("varchar".equals(this.fieldType)) {
       return this.strValue;
     }
 
     if ("date".equals(this.fieldType)) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       return (this.dateValue == null) ? null : sdf.format(this.dateValue);
     }
 
     if ("datetime".equals(this.fieldType)) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return (this.dateValue == null) ? null : sdf.format(this.dateValue);
     }
 
     if ("int".equals(this.fieldType)) {
       return (this.intValue == null) ? null : this.intValue.toString();
     }
 
     if ("long".equals(this.fieldType)) {
       return (this.longValue == null) ? null : this.longValue.toString();
     }
 
     if ("decimal".equals(this.fieldType)) {
       return (this.decValue == null) ? null : this.decValue.toString();
     }
 
     if ("text".equals(this.fieldType)) {
       return this.textValue;
     }
 
     if ("file".equals(this.fieldType)) {
       return this.strValue;
     }
 
     if ("bool".equals(this.fieldType)) {
       return (this.boolValue.shortValue() == 1) ? "是" : "否";
     }
 
     return null;
   }
 
   public void setValue(String val, String type)
   {
     if (val == null) return;
     try
     {
       if ("varchar".equals(type))
         this.strValue = val;
       else if ("bool".equals(type))
         this.boolValue = Short.valueOf(("1".equals(val)) ? 1 : 0);
       else if (("date".equals(type)) || ("datetime".equals(type)))
         this.dateValue = DateUtils.parseDate(val, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" });
       else if ("decimal".equals(type))
         this.decValue = new BigDecimal(val);
       else if ("int".equals(type))
         this.intValue = new Integer(val);
       else if ("long".equals(type))
         this.longValue = new Long(val);
       else if ("text".equals(type))
         this.textValue = val;
       else if ("file".equals(type))
         this.strValue = val;
     }
     catch (Exception ex) {
       this.logger.warn("setValue error:" + ex.getMessage());
     }
   }
 
   public FormData()
   {
   }
 
   public FormData(Long in_dataId)
   {
     setDataId(in_dataId);
   }
 
   public ProcessForm getProcessForm()
   {
     return this.processForm;
   }
 
   public void setProcessForm(ProcessForm in_processForm) {
     this.processForm = in_processForm;
   }
 
   public Long getDataId()
   {
     return this.dataId;
   }
 
   public void setDataId(Long aValue)
   {
     this.dataId = aValue;
   }
 
   public Long getFormId()
   {
     return (getProcessForm() == null) ? null : getProcessForm().getFormId();
   }
 
   public Short getBoolValue() {
     return this.boolValue;
   }
 
   public void setBoolValue(Short boolValue) {
     this.boolValue = boolValue;
   }
 
   public void setFormId(Long aValue)
   {
     if (aValue == null) {
       this.processForm = null;
     } else if (this.processForm == null) {
       this.processForm = new ProcessForm(aValue);
       this.processForm.setVersion(new Integer(0));
     } else {
       this.processForm.setFormId(aValue);
     }
   }
 
   public String getFieldLabel()
   {
     return this.fieldLabel;
   }
 
   public void setFieldLabel(String aValue)
   {
     this.fieldLabel = aValue;
   }
 
   public String getFieldName()
   {
     return this.fieldName;
   }
 
   public void setFieldName(String aValue)
   {
     this.fieldName = aValue;
   }
 
   public Integer getIntValue()
   {
     return this.intValue;
   }
 
   public void setIntValue(Integer aValue)
   {
     this.intValue = aValue;
   }
 
   public BigDecimal getDecValue()
   {
     return this.decValue;
   }
 
   public void setDecValue(BigDecimal aValue)
   {
     this.decValue = aValue;
   }
 
   public Date getDateValue()
   {
     return this.dateValue;
   }
 
   public void setDateValue(Date aValue)
   {
     this.dateValue = aValue;
   }
 
   public String getStrValue()
   {
     return this.strValue;
   }
 
   public void setStrValue(String aValue)
   {
     this.strValue = aValue;
   }
 
   public String getBlobValue()
   {
     return this.blobValue;
   }
 
   public void setBlobValue(String aValue)
   {
     this.blobValue = aValue;
   }
 
   public Short getIsShowed()
   {
     return this.isShowed;
   }
 
   public void setIsShowed(Short aValue)
   {
     this.isShowed = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof FormData) {
       return false;
     }
     FormData rhs = (FormData)object;
     return new EqualsBuilder()
       .append(this.dataId, rhs.dataId)
       .append(this.fieldLabel, rhs.fieldLabel)
       .append(this.fieldName, rhs.fieldName)
       .append(this.intValue, rhs.intValue)
       .append(this.decValue, rhs.decValue)
       .append(this.dateValue, rhs.dateValue)
       .append(this.strValue, rhs.strValue)
       .append(this.blobValue, rhs.blobValue)
       .append(this.isShowed, rhs.isShowed)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.dataId)
       .append(this.fieldLabel)
       .append(this.fieldName)
       .append(this.intValue)
       .append(this.decValue)
       .append(this.dateValue)
       .append(this.strValue)
       .append(this.blobValue)
       .append(this.isShowed)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("dataId", this.dataId)
       .append("fieldLabel", this.fieldLabel)
       .append("fieldName", this.fieldName)
       .append("intValue", this.intValue)
       .append("decValue", this.decValue)
       .append("dateValue", this.dateValue)
       .append("strValue", this.strValue)
       .append("blobValue", this.blobValue)
       .append("isShowed", this.isShowed)
       .toString();
   }
 
   public Long getLongValue() {
     return this.longValue;
   }
 
   public void setLongValue(Long longValue) {
     this.longValue = longValue;
   }
 
   public String getTextValue() {
     return this.textValue;
   }
 
   public void setTextValue(String textValue) {
     this.textValue = textValue;
   }
 
   public String getFieldType() {
     return this.fieldType;
   }
 
   public void setFieldType(String fieldType) {
     this.fieldType = fieldType;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.FormData
 * JD-Core Version:    0.5.4
 */