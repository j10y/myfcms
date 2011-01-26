 package com.htsoft.oa.model.communicate;
 
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.FileAttach;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class Mail extends BaseModel
 {
   protected Long mailId;
   protected String sender;
   protected Short importantFlag;
   protected Date sendTime;
   protected String content;
   protected String subject;
   protected String copyToNames;
   protected String copyToIDs;
   protected String recipientNames;
   protected String recipientIDs;
   protected Short mailStatus;
   protected AppUser appSender;
   protected String fileIds;
   protected String filenames;
   protected Set<FileAttach> mailAttachs = new HashSet();
   protected Set<MailBox> mailBoxs = new HashSet();
 
   public Mail()
   {
   }
 
   public Mail(Long in_mailId)
   {
     setMailId(in_mailId);
   }
 
   public String getFileIds()
   {
     return this.fileIds;
   }
 
   public void setFileIds(String fileIds) {
     this.fileIds = fileIds;
   }
 
   public String getFilenames() {
     return this.filenames;
   }
 
   public void setFilenames(String filenames) {
     this.filenames = filenames;
   }
 
   public AppUser getAppSender() {
     return this.appSender;
   }
 
   public void setAppSender(AppUser appSender) {
     this.appSender = appSender;
   }
 
   public Set<FileAttach> getMailAttachs() {
     return this.mailAttachs;
   }
 
   public void setMailAttachs(Set<FileAttach> mailAttachs) {
     this.mailAttachs = mailAttachs;
   }
 
   public Set<MailBox> getMailBoxs() {
     return this.mailBoxs;
   }
 
   public void setMailBoxs(Set<MailBox> mailBoxs) {
     this.mailBoxs = mailBoxs;
   }
 
   public Long getMailId()
   {
     return this.mailId;
   }
 
   public void setMailId(Long aValue)
   {
     this.mailId = aValue;
   }
 
   public String getSender()
   {
     return this.sender;
   }
 
   public void setSender(String sender) {
     this.sender = sender;
   }
 
   public Short getImportantFlag()
   {
     return this.importantFlag;
   }
 
   public void setImportantFlag(Short aValue)
   {
     this.importantFlag = aValue;
   }
 
   public Date getSendTime()
   {
     return this.sendTime;
   }
 
   public void setSendTime(Date aValue)
   {
     this.sendTime = aValue;
   }
 
   public String getContent()
   {
     return this.content;
   }
 
   public void setContent(String aValue)
   {
     this.content = aValue;
   }
 
   public String getSubject()
   {
     return this.subject;
   }
 
   public void setSubject(String aValue)
   {
     this.subject = aValue;
   }
 
   public String getCopyToNames()
   {
     return this.copyToNames;
   }
 
   public void setCopyToNames(String aValue)
   {
     this.copyToNames = aValue;
   }
 
   public String getCopyToIDs()
   {
     return this.copyToIDs;
   }
 
   public void setCopyToIDs(String aValue)
   {
     this.copyToIDs = aValue;
   }
 
   public String getRecipientNames()
   {
     return this.recipientNames;
   }
 
   public void setRecipientNames(String aValue)
   {
     this.recipientNames = aValue;
   }
 
   public String getRecipientIDs()
   {
     return this.recipientIDs;
   }
 
   public void setRecipientIDs(String aValue)
   {
     this.recipientIDs = aValue;
   }
 
   public Short getMailStatus()
   {
     return this.mailStatus;
   }
 
   public void setMailStatus(Short aValue)
   {
     this.mailStatus = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof Mail) {
       return false;
     }
     Mail rhs = (Mail)object;
     return new EqualsBuilder()
       .append(this.mailId, rhs.mailId)
       .append(this.sender, rhs.sender)
       .append(this.importantFlag, rhs.importantFlag)
       .append(this.sendTime, rhs.sendTime)
       .append(this.content, rhs.content)
       .append(this.subject, rhs.subject)
       .append(this.copyToNames, rhs.copyToNames)
       .append(this.copyToIDs, rhs.copyToIDs)
       .append(this.recipientNames, rhs.recipientNames)
       .append(this.recipientIDs, rhs.recipientIDs)
       .append(this.mailStatus, rhs.mailStatus)
       .append(this.fileIds, rhs.fileIds)
       .append(this.filenames, rhs.filenames)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.mailId)
       .append(this.sender)
       .append(this.importantFlag)
       .append(this.sendTime)
       .append(this.content)
       .append(this.subject)
       .append(this.copyToNames)
       .append(this.copyToIDs)
       .append(this.recipientNames)
       .append(this.recipientIDs)
       .append(this.mailStatus)
       .append(this.fileIds)
       .append(this.filenames)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("mailId", this.mailId)
       .append("sender", this.sender)
       .append("importantFlag", this.importantFlag)
       .append("sendTime", this.sendTime)
       .append("content", this.content)
       .append("subject", this.subject)
       .append("copyToNames", this.copyToNames)
       .append("copyToIDs", this.copyToIDs)
       .append("recipientNames", this.recipientNames)
       .append("recipientIDs", this.recipientIDs)
       .append("mailStatus", this.mailStatus)
       .append("fileIds", this.fileIds)
       .append("filenames", this.filenames)
       .toString();
   }
 
   public String getFirstKeyColumnName()
   {
     return "mailId";
   }
 
   public Long getId()
   {
     return this.mailId;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.communicate.Mail
 * JD-Core Version:    0.5.4
 */