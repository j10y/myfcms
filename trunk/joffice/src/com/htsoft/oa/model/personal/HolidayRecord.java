 package com.htsoft.oa.model.personal;
 
 import com.htsoft.core.model.BaseModel;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class HolidayRecord extends BaseModel
 {
   protected Long recordId;
   protected Date startTime;
   protected Date endTime;
   protected String descp;
   protected Long userId;
   protected String fullname;
   protected Short isAll;
   public static final Short IS_ALL_HOLIDAY = Short.valueOf(1);
 
   public static final Short IS_PERSONAL_HOLIDAY = Short.valueOf(0);
 
   public HolidayRecord()
   {
   }
 
   public HolidayRecord(Long in_recordId)
   {
     setRecordId(in_recordId);
   }
 
   public String getDescp()
   {
     return this.descp;
   }
 
   public void setDescp(String descp) {
     this.descp = descp;
   }
 
   public String getFullname()
   {
     return this.fullname;
   }
 
   public void setFullname(String fullname) {
     this.fullname = fullname;
   }
 
   public Long getRecordId()
   {
     return this.recordId;
   }
 
   public void setRecordId(Long aValue)
   {
     this.recordId = aValue;
   }
 
   public Date getStartTime()
   {
     return this.startTime;
   }
 
   public void setStartTime(Date aValue)
   {
     this.startTime = aValue;
   }
 
   public Date getEndTime()
   {
     return this.endTime;
   }
 
   public void setEndTime(Date aValue)
   {
     this.endTime = aValue;
   }
 
   public Long getUserId()
   {
     return this.userId;
   }
 
   public void setUserId(Long aValue)
   {
     this.userId = aValue;
   }
 
   public Short getIsAll()
   {
     return this.isAll;
   }
 
   public void setIsAll(Short aValue)
   {
     this.isAll = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof HolidayRecord) {
       return false;
     }
     HolidayRecord rhs = (HolidayRecord)object;
     return new EqualsBuilder()
       .append(this.recordId, rhs.recordId)
       .append(this.startTime, rhs.startTime)
       .append(this.endTime, rhs.endTime)
       .append(this.userId, rhs.userId)
       .append(this.isAll, rhs.isAll)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.recordId)
       .append(this.startTime)
       .append(this.endTime)
       .append(this.userId)
       .append(this.isAll)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("recordId", this.recordId)
       .append("startTime", this.startTime)
       .append("endTime", this.endTime)
       .append("userId", this.userId)
       .append("isAll", this.isAll)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.personal.HolidayRecord
 * JD-Core Version:    0.5.4
 */