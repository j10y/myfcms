 package com.htsoft.oa.model.archive;
 
 import com.htsoft.core.model.BaseModel;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class ArchDispatch extends BaseModel
 {
   public static Short HAVE_READ = 1;
   public static Short NOT_READ = 0;
   public static Short IS_UNDERTAKE = 1;
   public static Short IS_READER = 0;
   public static Short IS_DISPATCH = 2;
   protected Long dispatchId;
   protected Date dispatchTime;
   protected Long userId;
   protected String fullname;
   protected Short isRead;
   protected String subject;
   protected String readFeedback;
   protected Short archUserType;
   protected Long disRoleId;
   protected String disRoleName;
   protected Archives archives;
 
   public ArchDispatch()
   {
   }
 
   public Long getDisRoleId()
   {
     return this.disRoleId;
   }
 
   public void setDisRoleId(Long disRoleId)
   {
     this.disRoleId = disRoleId;
   }
 
   public String getDisRoleName()
   {
     return this.disRoleName;
   }
 
   public void setDisRoleName(String disRoleName)
   {
     this.disRoleName = disRoleName;
   }
 
   public ArchDispatch(Long in_dispatchId)
   {
     setDispatchId(in_dispatchId);
   }
 
   public Archives getArchives()
   {
     return this.archives;
   }
 
   public void setArchives(Archives in_archives) {
     this.archives = in_archives;
   }
 
   public Long getDispatchId()
   {
     return this.dispatchId;
   }
 
   public void setDispatchId(Long aValue)
   {
     this.dispatchId = aValue;
   }
 
   public Long getArchivesId()
   {
     return (getArchives() == null) ? null : getArchives().getArchivesId();
   }
 
   public void setArchivesId(Long aValue)
   {
     if (aValue == null) {
       this.archives = null;
     } else if (this.archives == null) {
       this.archives = new Archives(aValue);
       this.archives.setVersion(new Integer(0));
     } else {
       this.archives.setArchivesId(aValue);
     }
   }
 
   public Date getDispatchTime()
   {
     return this.dispatchTime;
   }
 
   public void setDispatchTime(Date aValue)
   {
     this.dispatchTime = aValue;
   }
 
   public Long getUserId()
   {
     return this.userId;
   }
 
   public void setUserId(Long aValue)
   {
     this.userId = aValue;
   }
 
   public String getFullname()
   {
     return this.fullname;
   }
 
   public void setFullname(String aValue)
   {
     this.fullname = aValue;
   }
 
   public Short getIsRead()
   {
     return this.isRead;
   }
 
   public void setIsRead(Short aValue)
   {
     this.isRead = aValue;
   }
 
   public String getSubject()
   {
     return this.subject;
   }
 
   public void setSubject(String aValue)
   {
     this.subject = aValue;
   }
 
   public String getReadFeedback()
   {
     return this.readFeedback;
   }
 
   public void setReadFeedback(String aValue)
   {
     this.readFeedback = aValue;
   }
 
   public Short getArchUserType()
   {
     return this.archUserType;
   }
 
   public void setArchUserType(Short aValue)
   {
     this.archUserType = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof ArchDispatch)) {
       return false;
     }
     ArchDispatch rhs = (ArchDispatch)object;
     return new EqualsBuilder()
       .append(this.dispatchId, rhs.dispatchId)
       .append(this.dispatchTime, rhs.dispatchTime)
       .append(this.userId, rhs.userId)
       .append(this.fullname, rhs.fullname)
       .append(this.isRead, rhs.isRead)
       .append(this.subject, rhs.subject)
       .append(this.readFeedback, rhs.readFeedback)
       .append(this.archUserType, rhs.archUserType)
       .append(this.disRoleId, rhs.disRoleId)
       .append(this.disRoleName, rhs.disRoleName)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.dispatchId)
       .append(this.dispatchTime)
       .append(this.userId)
       .append(this.fullname)
       .append(this.isRead)
       .append(this.subject)
       .append(this.readFeedback)
       .append(this.archUserType)
       .append(this.disRoleId)
       .append(this.disRoleName)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("dispatchId", this.dispatchId)
       .append("dispatchTime", this.dispatchTime)
       .append("userId", this.userId)
       .append("fullname", this.fullname)
       .append("isRead", this.isRead)
       .append("subject", this.subject)
       .append("readFeedback", this.readFeedback)
       .append("isUndertake", this.archUserType)
       .append("disRoleId", this.disRoleId)
       .append("disRoleName", this.disRoleName)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.archive.ArchDispatch
 * JD-Core Version:    0.5.4
 */