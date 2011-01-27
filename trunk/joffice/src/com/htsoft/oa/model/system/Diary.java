 package com.htsoft.oa.model.system;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class Diary extends BaseModel
 {
 
   @Expose
   protected Long diaryId;
 
   @Expose
   protected Date dayTime;
 
   @Expose
   protected String content;
 
   @Expose
   protected Short diaryType;
 
   @Expose
   protected AppUser appUser;
 
   public Diary()
   {
   }
 
   public Diary(Long in_diaryId)
   {
     setDiaryId(in_diaryId);
   }
 
   public AppUser getAppUser()
   {
     return this.appUser;
   }
 
   public void setAppUser(AppUser appUser) {
     this.appUser = appUser;
   }
 
   public Long getDiaryId()
   {
     return this.diaryId;
   }
 
   public void setDiaryId(Long aValue)
   {
     this.diaryId = aValue;
   }
 
   public Date getDayTime()
   {
     return this.dayTime;
   }
 
   public void setDayTime(Date aValue)
   {
     this.dayTime = aValue;
   }
 
   public String getContent()
   {
     return this.content;
   }
 
   public void setContent(String aValue)
   {
     this.content = aValue;
   }
 
   public Short getDiaryType()
   {
     return this.diaryType;
   }
 
   public void setDiaryType(Short aValue)
   {
     this.diaryType = aValue;
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
 
   public boolean equals(Object object)
   {
     if (!(object instanceof Diary)) {
       return false;
     }
     Diary rhs = (Diary)object;
     return new EqualsBuilder()
       .append(this.diaryId, rhs.diaryId)
       .append(this.dayTime, rhs.dayTime)
       .append(this.content, rhs.content)
       .append(this.diaryType, rhs.diaryType)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.diaryId)
       .append(this.dayTime)
       .append(this.content)
       .append(this.diaryType)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("diaryId", this.diaryId)
       .append("dayTime", this.dayTime)
       .append("content", this.content)
       .append("diaryType", this.diaryType)
       .toString();
   }
 
   public String getFirstKeyColumnName()
   {
     return "diaryId";
   }
 
   public Long getId()
   {
     return this.diaryId;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.system.Diary
 * JD-Core Version:    0.5.4
 */