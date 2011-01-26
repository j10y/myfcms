 package com.htsoft.oa.model.archive;
 
 import com.htsoft.core.model.BaseModel;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class LeaderRead extends BaseModel
 {
   public static Short NOT_CHECK = Short.valueOf(0);
   public static Short IS_PASS = Short.valueOf(1);
   public static Short NOT_PASS = Short.valueOf(2);
   protected Long readId;
   protected String leaderName;
   protected Long userId;
   protected String leaderOpinion;
   protected Date createtime;
   protected Integer depLevel;
   protected String depName;
   protected Short isPass;
   protected String checkName;
   protected Archives archives;
 
   public LeaderRead()
   {
   }
 
   public LeaderRead(Long in_readId)
   {
     setReadId(in_readId);
   }
 
   public Archives getArchives()
   {
     return this.archives;
   }
 
   public void setArchives(Archives in_archives) {
     this.archives = in_archives;
   }
 
   public String getCheckName()
   {
     return this.checkName;
   }
 
   public void setCheckName(String checkName) {
     this.checkName = checkName;
   }
 
   public Long getReadId()
   {
     return this.readId;
   }
 
   public void setReadId(Long aValue)
   {
     this.readId = aValue;
   }
 
   public String getLeaderName()
   {
     return this.leaderName;
   }
 
   public void setLeaderName(String aValue)
   {
     this.leaderName = aValue;
   }
 
   public Long getUserId()
   {
     return this.userId;
   }
 
   public void setUserId(Long aValue)
   {
     this.userId = aValue;
   }
 
   public String getLeaderOpinion()
   {
     return this.leaderOpinion;
   }
 
   public void setLeaderOpinion(String aValue)
   {
     this.leaderOpinion = aValue;
   }
 
   public Date getCreatetime()
   {
     return this.createtime;
   }
 
   public void setCreatetime(Date aValue)
   {
     this.createtime = aValue;
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
 
   public Integer getDepLevel()
   {
     return this.depLevel;
   }
 
   public void setDepLevel(Integer aValue)
   {
     this.depLevel = aValue;
   }
 
   public String getDepName()
   {
     return this.depName;
   }
 
   public void setDepName(String aValue)
   {
     this.depName = aValue;
   }
 
   public Short getIsPass()
   {
     return this.isPass;
   }
 
   public void setIsPass(Short aValue)
   {
     this.isPass = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof LeaderRead) {
       return false;
     }
     LeaderRead rhs = (LeaderRead)object;
     return new EqualsBuilder()
       .append(this.readId, rhs.readId)
       .append(this.leaderName, rhs.leaderName)
       .append(this.userId, rhs.userId)
       .append(this.leaderOpinion, rhs.leaderOpinion)
       .append(this.createtime, rhs.createtime)
       .append(this.depLevel, rhs.depLevel)
       .append(this.depName, rhs.depName)
       .append(this.isPass, rhs.isPass)
       .append(this.checkName, rhs.checkName)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.readId)
       .append(this.leaderName)
       .append(this.userId)
       .append(this.leaderOpinion)
       .append(this.createtime)
       .append(this.depLevel)
       .append(this.depName)
       .append(this.isPass)
       .append(this.checkName)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("readId", this.readId)
       .append("leaderName", this.leaderName)
       .append("userId", this.userId)
       .append("leaderOpinion", this.leaderOpinion)
       .append("createtime", this.createtime)
       .append("depLevel", this.depLevel)
       .append("depName", this.depName)
       .append("isPass", this.isPass)
       .append("checkName", this.checkName)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.archive.LeaderRead
 * JD-Core Version:    0.5.4
 */