 package com.htsoft.oa.model.hrm;
 
 import com.htsoft.core.model.BaseModel;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class HireIssue extends BaseModel
 {
   public static Short PASS_CHECK = Short.valueOf(1);
   public static Short NOTPASS_CHECK = Short.valueOf(2);
   public static Short NOTYETPASS_CHECK = Short.valueOf(0);
   protected Long hireId;
   protected String title;
   protected Date startDate;
   protected Date endDate;
   protected Integer hireCount;
   protected String jobName;
   protected String jobCondition;
   protected String regFullname;
   protected Date regDate;
   protected String modifyFullname;
   protected Date modifyDate;
   protected String checkFullname;
   protected String checkOpinion;
   protected Date checkDate;
   protected Short status;
   protected String memo;
 
   public HireIssue()
   {
   }
 
   public HireIssue(Long in_hireId)
   {
     setHireId(in_hireId);
   }
 
   public Long getHireId()
   {
     return this.hireId;
   }
 
   public void setHireId(Long aValue)
   {
     this.hireId = aValue;
   }
 
   public String getTitle()
   {
     return this.title;
   }
 
   public void setTitle(String aValue)
   {
     this.title = aValue;
   }
 
   public Date getStartDate()
   {
     return this.startDate;
   }
 
   public void setStartDate(Date aValue)
   {
     this.startDate = aValue;
   }
 
   public Date getEndDate()
   {
     return this.endDate;
   }
 
   public void setEndDate(Date aValue)
   {
     this.endDate = aValue;
   }
 
   public Integer getHireCount()
   {
     return this.hireCount;
   }
 
   public void setHireCount(Integer aValue)
   {
     this.hireCount = aValue;
   }
 
   public String getJobName()
   {
     return this.jobName;
   }
 
   public void setJobName(String aValue)
   {
     this.jobName = aValue;
   }
 
   public String getJobCondition()
   {
     return this.jobCondition;
   }
 
   public void setJobCondition(String aValue)
   {
     this.jobCondition = aValue;
   }
 
   public String getRegFullname()
   {
     return this.regFullname;
   }
 
   public void setRegFullname(String aValue)
   {
     this.regFullname = aValue;
   }
 
   public Date getRegDate()
   {
     return this.regDate;
   }
 
   public void setRegDate(Date aValue)
   {
     this.regDate = aValue;
   }
 
   public String getModifyFullname()
   {
     return this.modifyFullname;
   }
 
   public void setModifyFullname(String aValue)
   {
     this.modifyFullname = aValue;
   }
 
   public Date getModifyDate()
   {
     return this.modifyDate;
   }
 
   public void setModifyDate(Date aValue)
   {
     this.modifyDate = aValue;
   }
 
   public String getCheckFullname()
   {
     return this.checkFullname;
   }
 
   public void setCheckFullname(String aValue)
   {
     this.checkFullname = aValue;
   }
 
   public String getCheckOpinion()
   {
     return this.checkOpinion;
   }
 
   public void setCheckOpinion(String aValue)
   {
     this.checkOpinion = aValue;
   }
 
   public Date getCheckDate()
   {
     return this.checkDate;
   }
 
   public void setCheckDate(Date aValue)
   {
     this.checkDate = aValue;
   }
 
   public Short getStatus()
   {
     return this.status;
   }
 
   public void setStatus(Short aValue)
   {
     this.status = aValue;
   }
 
   public String getMemo()
   {
     return this.memo;
   }
 
   public void setMemo(String aValue)
   {
     this.memo = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof HireIssue) {
       return false;
     }
     HireIssue rhs = (HireIssue)object;
     return new EqualsBuilder()
       .append(this.hireId, rhs.hireId)
       .append(this.title, rhs.title)
       .append(this.startDate, rhs.startDate)
       .append(this.endDate, rhs.endDate)
       .append(this.hireCount, rhs.hireCount)
       .append(this.jobName, rhs.jobName)
       .append(this.jobCondition, rhs.jobCondition)
       .append(this.regFullname, rhs.regFullname)
       .append(this.regDate, rhs.regDate)
       .append(this.modifyFullname, rhs.modifyFullname)
       .append(this.modifyDate, rhs.modifyDate)
       .append(this.checkFullname, rhs.checkFullname)
       .append(this.checkOpinion, rhs.checkOpinion)
       .append(this.checkDate, rhs.checkDate)
       .append(this.status, rhs.status)
       .append(this.memo, rhs.memo)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.hireId)
       .append(this.title)
       .append(this.startDate)
       .append(this.endDate)
       .append(this.hireCount)
       .append(this.jobName)
       .append(this.jobCondition)
       .append(this.regFullname)
       .append(this.regDate)
       .append(this.modifyFullname)
       .append(this.modifyDate)
       .append(this.checkFullname)
       .append(this.checkOpinion)
       .append(this.checkDate)
       .append(this.status)
       .append(this.memo)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("hireId", this.hireId)
       .append("title", this.title)
       .append("startDate", this.startDate)
       .append("endDate", this.endDate)
       .append("hireCount", this.hireCount)
       .append("jobName", this.jobName)
       .append("jobCondition", this.jobCondition)
       .append("regFullname", this.regFullname)
       .append("regDate", this.regDate)
       .append("modifyFullname", this.modifyFullname)
       .append("modifyDate", this.modifyDate)
       .append("checkFullname", this.checkFullname)
       .append("checkOpinion", this.checkOpinion)
       .append("checkDate", this.checkDate)
       .append("status", this.status)
       .append("memo", this.memo)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.hrm.HireIssue
 * JD-Core Version:    0.5.4
 */