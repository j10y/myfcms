 package com.htsoft.oa.model.flow;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.AppUser;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class ProcessRun extends BaseModel
 {
   public static final Short RUN_STATUS_INIT = Short.valueOf(0);
 
   public static final Short RUN_STATUS_RUNNING = Short.valueOf(1);
 
   public static final Short RUN_STATUS_FINISHED = Short.valueOf(2);
   protected Long runId;
 
   @Expose
   protected String subject;
 
   @Expose
   protected String creator;
 
   @Expose
   protected Date createtime;
 
   @Expose
   protected ProDefinition proDefinition;
 
   @Expose
   protected String piId;
 
   @Expose
   protected Short runStatus = RUN_STATUS_INIT;
   protected AppUser appUser;
   protected Set processForms = new HashSet();
 
   public ProcessRun()
   {
   }
 
   public ProcessRun(Long in_runId)
   {
     setRunId(in_runId);
   }
 
   public ProDefinition getProDefinition()
   {
     return this.proDefinition;
   }
 
   public void setProDefinition(ProDefinition proDefinition) {
     this.proDefinition = proDefinition;
   }
 
   public AppUser getAppUser() {
     return this.appUser;
   }
 
   public void setAppUser(AppUser in_appUser) {
     this.appUser = in_appUser;
   }
 
   public Set getProcessForms() {
     return this.processForms;
   }
 
   public void setProcessForms(Set in_processForms) {
     this.processForms = in_processForms;
   }
 
   public Long getRunId()
   {
     return this.runId;
   }
 
   public void setRunId(Long aValue)
   {
     this.runId = aValue;
   }
 
   public String getSubject()
   {
     return this.subject;
   }
 
   public void setSubject(String aValue)
   {
     this.subject = aValue;
   }
 
   public String getCreator()
   {
     return this.creator;
   }
 
   public void setCreator(String aValue)
   {
     this.creator = aValue;
   }
 
   public Long getUserId()
   {
     return (getAppUser() == null) ? null : getAppUser().getUserId();
   }
 
   public void setUserId(Long aValue)
   {
     if (aValue == null) {
       this.appUser = null;
     } else if (this.appUser == null) {
       this.appUser = new AppUser(aValue);
       this.appUser.setVersion(new Integer(0));
     } else {
       this.appUser.setUserId(aValue);
     }
   }
 
   public String getPiId()
   {
     return this.piId;
   }
 
   public void setPiId(String aValue)
   {
     this.piId = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof ProcessRun) {
       return false;
     }
     ProcessRun rhs = (ProcessRun)object;
     return new EqualsBuilder()
       .append(this.runId, rhs.runId)
       .append(this.subject, rhs.subject)
       .append(this.creator, rhs.creator)
       .append(this.piId, rhs.piId)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.runId)
       .append(this.subject)
       .append(this.creator)
       .append(this.piId)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("runId", this.runId)
       .append("subject", this.subject)
       .append("creator", this.creator)
       .append("piId", this.piId)
       .toString();
   }
 
   public Date getCreatetime() {
     return this.createtime;
   }
 
   public void setCreatetime(Date createtime) {
     this.createtime = createtime;
   }
 
   public Short getRunStatus() {
     return this.runStatus;
   }
 
   public void setRunStatus(Short runStatus) {
     this.runStatus = runStatus;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.ProcessRun
 * JD-Core Version:    0.5.4
 */