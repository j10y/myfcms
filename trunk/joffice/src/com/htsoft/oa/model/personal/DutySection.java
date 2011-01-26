 package com.htsoft.oa.model.personal;
 
 import com.htsoft.core.model.BaseModel;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 import org.apache.commons.lang.time.DateUtils;
 
 public class DutySection extends BaseModel
 {
   protected Long sectionId;
   protected String sectionName;
   protected Date startSignin;
   protected Date dutyStartTime;
   protected Date endSignin;
   protected Date earlyOffTime;
   protected Date dutyEndTime;
   protected Date signOutTime;
   protected String startSignin1;
   protected String dutyStartTime1;
   protected String endSignin1;
   protected String earlyOffTime1;
   protected String dutyEndTime1;
   protected String signOutTime1;
   public final String datePattern = "yyyy-MM-dd HH:mm:ss";
 
   private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
 
   public DutySection()
   {
   }
 
   public DutySection(Long in_sectionId)
   {
     setSectionId(in_sectionId);
   }
 
   public Long getSectionId()
   {
     return this.sectionId;
   }
 
   public void setSectionId(Long aValue)
   {
     this.sectionId = aValue;
   }
 
   public Date getStartSignin()
   {
     return this.startSignin;
   }
 
   public void setStartSignin(Date aValue)
   {
     this.startSignin = aValue;
   }
 
   public void setStartSignin1(String inVal)
   {
     this.startSignin1 = inVal;
     String finalVal = "1900-01-01 " + inVal + ":00";
     try {
       this.startSignin = DateUtils.parseDate(finalVal, new String[] { "yyyy-MM-dd HH:mm:ss" });
     } catch (Exception ex) {
       ex.printStackTrace();
     }
   }
 
   public String getStartSignin1() {
     return this.sdf.format(this.startSignin);
   }
 
   public String getDutyStartTime1() {
     return this.sdf.format(this.dutyStartTime);
   }
 
   public String getEndSignin1() {
     return this.sdf.format(this.endSignin);
   }
 
   public String getEarlyOffTime1() {
     return this.sdf.format(this.earlyOffTime);
   }
 
   public String getDutyEndTime1() {
     return this.sdf.format(this.dutyEndTime);
   }
 
   public String getSignOutTime1() {
     return this.sdf.format(this.signOutTime);
   }
 
   public Date getDutyStartTime()
   {
     return this.dutyStartTime;
   }
 
   public void setDutyStartTime(Date aValue)
   {
     this.dutyStartTime = aValue;
   }
 
   public String getSectionName() {
     return this.sectionName;
   }
 
   public void setSectionName(String sectionName) {
     this.sectionName = sectionName;
   }
 
   public void setDutyStartTime1(String inVal)
   {
     this.dutyStartTime1 = inVal;
     String finalVal = "1900-01-01 " + inVal + ":00";
     try {
       this.dutyStartTime = DateUtils.parseDate(finalVal, new String[] { "yyyy-MM-dd HH:mm:ss" });
     } catch (Exception ex) {
       ex.printStackTrace();
     }
   }
 
   public Date getEndSignin()
   {
     return this.endSignin;
   }
 
   public void setEndSignin(Date aValue)
   {
     this.endSignin = aValue;
   }
 
   public void setEndSignin1(String inVal)
   {
     this.endSignin1 = inVal;
     String finalVal = "1900-01-01 " + inVal + ":00";
     try {
       this.endSignin = DateUtils.parseDate(finalVal, new String[] { "yyyy-MM-dd HH:mm:ss" });
     } catch (Exception ex) {
       ex.printStackTrace();
     }
   }
 
   public Date getEarlyOffTime()
   {
     return this.earlyOffTime;
   }
 
   public void setEarlyOffTime(Date aValue)
   {
     this.earlyOffTime = aValue;
   }
 
   public void setEarlyOffTime1(String inVal)
   {
     this.earlyOffTime1 = inVal;
     String finalVal = "1900-01-01 " + inVal + ":00";
     try {
       this.earlyOffTime = DateUtils.parseDate(finalVal, new String[] { "yyyy-MM-dd HH:mm:ss" });
     } catch (Exception ex) {
       ex.printStackTrace();
     }
   }
 
   public Date getDutyEndTime()
   {
     return this.dutyEndTime;
   }
 
   public void setDutyEndTime(Date aValue)
   {
     this.dutyEndTime = aValue;
   }
 
   public void setDutyEndTime1(String inVal)
   {
     this.dutyEndTime1 = inVal;
     String finalVal = "1900-01-01 " + inVal + ":00";
     try {
       this.dutyEndTime = DateUtils.parseDate(finalVal, new String[] { "yyyy-MM-dd HH:mm:ss" });
     } catch (Exception ex) {
       ex.printStackTrace();
     }
   }
 
   public Date getSignOutTime()
   {
     return this.signOutTime;
   }
 
   public void setSignOutTime(Date aValue)
   {
     this.signOutTime = aValue;
   }
 
   public void setSignOutTime1(String inVal)
   {
     this.signOutTime1 = inVal;
     String finalVal = "1900-01-01 " + inVal + ":00";
     try {
       this.signOutTime = DateUtils.parseDate(finalVal, new String[] { "yyyy-MM-dd HH:mm:ss" });
     } catch (Exception ex) {
       ex.printStackTrace();
     }
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof DutySection) {
       return false;
     }
     DutySection rhs = (DutySection)object;
     return new EqualsBuilder()
       .append(this.sectionId, rhs.sectionId)
       .append(this.startSignin, rhs.startSignin)
       .append(this.dutyStartTime, rhs.dutyStartTime)
       .append(this.endSignin, rhs.endSignin)
       .append(this.earlyOffTime, rhs.earlyOffTime)
       .append(this.dutyEndTime, rhs.dutyEndTime)
       .append(this.signOutTime, rhs.signOutTime)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.sectionId)
       .append(this.startSignin)
       .append(this.dutyStartTime)
       .append(this.endSignin)
       .append(this.earlyOffTime)
       .append(this.dutyEndTime)
       .append(this.signOutTime)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("sectionId", this.sectionId)
       .append("startSignin", this.startSignin)
       .append("dutyStartTime", this.dutyStartTime)
       .append("endSignin", this.endSignin)
       .append("earlyOffTime", this.earlyOffTime)
       .append("dutyEndTime", this.dutyEndTime)
       .append("signOutTime", this.signOutTime)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.personal.DutySection
 * JD-Core Version:    0.5.4
 */