 package com.htsoft.oa.model.flow;
 
 import com.htsoft.core.model.BaseModel;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class ProcessForm extends BaseModel
 {
   protected Long formId;
   protected String activityName;
   protected Integer sn = Integer.valueOf(1);
   protected Date createtime;
   protected Long creatorId;
   protected String creatorName;
   protected ProcessRun processRun;
   protected Set formDatas = new HashSet();
   protected Set formFiles = new HashSet();
 
   public Date getCreatetime()
   {
     if (this.createtime == null) {
       this.createtime = new Date();
     }
     return this.createtime;
   }
 
   public void setCreatetime(Date createtime) {
     this.createtime = createtime;
   }
 
   public ProcessForm()
   {
   }
 
   public ProcessForm(Long in_formId)
   {
     setFormId(in_formId);
   }
 
   public ProcessRun getProcessRun()
   {
     return this.processRun;
   }
 
   public void setProcessRun(ProcessRun in_processRun) {
     this.processRun = in_processRun;
   }
 
   public Set getFormDatas() {
     return this.formDatas;
   }
 
   public void setFormDatas(Set in_formDatas) {
     this.formDatas = in_formDatas;
   }
 
   public Set getFormFiles() {
     return this.formFiles;
   }
 
   public void setFormFiles(Set in_formFiles) {
     this.formFiles = in_formFiles;
   }
 
   public Long getFormId()
   {
     return this.formId;
   }
 
   public void setFormId(Long aValue)
   {
     this.formId = aValue;
   }
 
   public Long getRunId()
   {
     return (getProcessRun() == null) ? null : getProcessRun().getRunId();
   }
 
   public void setRunId(Long aValue)
   {
     if (aValue == null) {
       this.processRun = null;
     } else if (this.processRun == null) {
       this.processRun = new ProcessRun(aValue);
       this.processRun.setVersion(new Integer(0));
     } else {
       this.processRun.setRunId(aValue);
     }
   }
 
   public String getActivityName()
   {
     return this.activityName;
   }
 
   public void setActivityName(String aValue)
   {
     this.activityName = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof ProcessForm)) {
       return false;
     }
     ProcessForm rhs = (ProcessForm)object;
     return new EqualsBuilder()
       .append(this.formId, rhs.formId)
       .append(this.activityName, rhs.activityName)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.formId)
       .append(this.activityName)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("formId", this.formId)
       .append("activityName", this.activityName)
       .toString();
   }
 
   public Integer getSn() {
     return this.sn;
   }
 
   public void setSn(Integer sn) {
     this.sn = sn;
   }
 
   public Long getCreatorId() {
     return this.creatorId;
   }
 
   public void setCreatorId(Long creatorId) {
     this.creatorId = creatorId;
   }
 
   public String getCreatorName() {
     return this.creatorName;
   }
 
   public void setCreatorName(String creatorName) {
     this.creatorName = creatorName;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.ProcessForm
 * JD-Core Version:    0.5.4
 */