 package com.htsoft.oa.model.hrm;
 
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.FileAttach;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class Resume extends BaseModel
 {
   public static String PASS = "通过";
   public static String NOTPASS = "不通过";
   public static String READY_INTERVIEW = "准备安排面试";
   public static String PASS_INTERVIEW = "通过面试";
   protected Long resumeId;
   protected String fullname;
   protected Integer age;
   protected Date birthday;
   protected String address;
   protected String zip;
   protected String sex;
   protected String position;
   protected String phone;
   protected String mobile;
   protected String email;
   protected String hobby;
   protected String religion;
   protected String party;
   protected String nationality;
   protected String race;
   protected String birthPlace;
   protected String eduCollege;
   protected String eduDegree;
   protected String eduMajor;
   protected Date startWorkDate;
   protected String idNo;
   protected String photo;
   protected String status;
   protected String memo;
   protected String registor;
   protected Date regTime;
   protected String workCase;
   protected String trainCase;
   protected String projectCase;
   protected Set<FileAttach> resumeFiles = new HashSet();
 
   public Resume()
   {
   }
 
   public Resume(Long in_resumeId)
   {
     setResumeId(in_resumeId);
   }
 
   public Set getResumeFiles()
   {
     return this.resumeFiles;
   }
 
   public void setResumeFiles(Set in_resumeFiles) {
     this.resumeFiles = in_resumeFiles;
   }
 
   public Long getResumeId()
   {
     return this.resumeId;
   }
 
   public void setResumeId(Long aValue)
   {
     this.resumeId = aValue;
   }
 
   public String getFullname()
   {
     return this.fullname;
   }
 
   public void setFullname(String aValue)
   {
     this.fullname = aValue;
   }
 
   public Integer getAge()
   {
     return this.age;
   }
 
   public void setAge(Integer aValue)
   {
     this.age = aValue;
   }
 
   public Date getBirthday()
   {
     return this.birthday;
   }
 
   public void setBirthday(Date aValue)
   {
     this.birthday = aValue;
   }
 
   public String getAddress()
   {
     return this.address;
   }
 
   public void setAddress(String aValue)
   {
     this.address = aValue;
   }
 
   public String getZip()
   {
     return this.zip;
   }
 
   public void setZip(String aValue)
   {
     this.zip = aValue;
   }
 
   public String getSex()
   {
     return this.sex;
   }
 
   public void setSex(String aValue)
   {
     this.sex = aValue;
   }
 
   public String getPosition()
   {
     return this.position;
   }
 
   public void setPosition(String aValue)
   {
     this.position = aValue;
   }
 
   public String getPhone()
   {
     return this.phone;
   }
 
   public void setPhone(String aValue)
   {
     this.phone = aValue;
   }
 
   public String getMobile()
   {
     return this.mobile;
   }
 
   public void setMobile(String aValue)
   {
     this.mobile = aValue;
   }
 
   public String getEmail()
   {
     return this.email;
   }
 
   public void setEmail(String aValue)
   {
     this.email = aValue;
   }
 
   public String getHobby()
   {
     return this.hobby;
   }
 
   public void setHobby(String aValue)
   {
     this.hobby = aValue;
   }
 
   public String getReligion()
   {
     return this.religion;
   }
 
   public void setReligion(String aValue)
   {
     this.religion = aValue;
   }
 
   public String getParty()
   {
     return this.party;
   }
 
   public void setParty(String aValue)
   {
     this.party = aValue;
   }
 
   public String getNationality()
   {
     return this.nationality;
   }
 
   public void setNationality(String aValue)
   {
     this.nationality = aValue;
   }
 
   public String getRace()
   {
     return this.race;
   }
 
   public void setRace(String aValue)
   {
     this.race = aValue;
   }
 
   public String getBirthPlace()
   {
     return this.birthPlace;
   }
 
   public void setBirthPlace(String aValue)
   {
     this.birthPlace = aValue;
   }
 
   public String getEduCollege()
   {
     return this.eduCollege;
   }
 
   public void setEduCollege(String aValue)
   {
     this.eduCollege = aValue;
   }
 
   public String getEduDegree()
   {
     return this.eduDegree;
   }
 
   public void setEduDegree(String aValue)
   {
     this.eduDegree = aValue;
   }
 
   public String getEduMajor()
   {
     return this.eduMajor;
   }
 
   public void setEduMajor(String aValue)
   {
     this.eduMajor = aValue;
   }
 
   public Date getStartWorkDate()
   {
     return this.startWorkDate;
   }
 
   public void setStartWorkDate(Date aValue)
   {
     this.startWorkDate = aValue;
   }
 
   public String getIdNo()
   {
     return this.idNo;
   }
 
   public void setIdNo(String aValue)
   {
     this.idNo = aValue;
   }
 
   public String getPhoto()
   {
     return this.photo;
   }
 
   public void setPhoto(String aValue)
   {
     this.photo = aValue;
   }
 
   public String getStatus()
   {
     return this.status;
   }
 
   public void setStatus(String aValue)
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
 
   public String getRegistor()
   {
     return this.registor;
   }
 
   public void setRegistor(String aValue)
   {
     this.registor = aValue;
   }
 
   public Date getRegTime()
   {
     return this.regTime;
   }
 
   public void setRegTime(Date aValue)
   {
     this.regTime = aValue;
   }
 
   public String getWorkCase()
   {
     return this.workCase;
   }
 
   public void setWorkCase(String aValue)
   {
     this.workCase = aValue;
   }
 
   public String getTrainCase()
   {
     return this.trainCase;
   }
 
   public void setTrainCase(String aValue)
   {
     this.trainCase = aValue;
   }
 
   public String getProjectCase()
   {
     return this.projectCase;
   }
 
   public void setProjectCase(String aValue)
   {
     this.projectCase = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof Resume)) {
       return false;
     }
     Resume rhs = (Resume)object;
     return new EqualsBuilder()
       .append(this.resumeId, rhs.resumeId)
       .append(this.fullname, rhs.fullname)
       .append(this.age, rhs.age)
       .append(this.birthday, rhs.birthday)
       .append(this.address, rhs.address)
       .append(this.zip, rhs.zip)
       .append(this.sex, rhs.sex)
       .append(this.position, rhs.position)
       .append(this.phone, rhs.phone)
       .append(this.mobile, rhs.mobile)
       .append(this.email, rhs.email)
       .append(this.hobby, rhs.hobby)
       .append(this.religion, rhs.religion)
       .append(this.party, rhs.party)
       .append(this.nationality, rhs.nationality)
       .append(this.race, rhs.race)
       .append(this.birthPlace, rhs.birthPlace)
       .append(this.eduCollege, rhs.eduCollege)
       .append(this.eduDegree, rhs.eduDegree)
       .append(this.eduMajor, rhs.eduMajor)
       .append(this.startWorkDate, rhs.startWorkDate)
       .append(this.idNo, rhs.idNo)
       .append(this.photo, rhs.photo)
       .append(this.status, rhs.status)
       .append(this.memo, rhs.memo)
       .append(this.registor, rhs.registor)
       .append(this.regTime, rhs.regTime)
       .append(this.workCase, rhs.workCase)
       .append(this.trainCase, rhs.trainCase)
       .append(this.projectCase, rhs.projectCase)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.resumeId)
       .append(this.fullname)
       .append(this.age)
       .append(this.birthday)
       .append(this.address)
       .append(this.zip)
       .append(this.sex)
       .append(this.position)
       .append(this.phone)
       .append(this.mobile)
       .append(this.email)
       .append(this.hobby)
       .append(this.religion)
       .append(this.party)
       .append(this.nationality)
       .append(this.race)
       .append(this.birthPlace)
       .append(this.eduCollege)
       .append(this.eduDegree)
       .append(this.eduMajor)
       .append(this.startWorkDate)
       .append(this.idNo)
       .append(this.photo)
       .append(this.status)
       .append(this.memo)
       .append(this.registor)
       .append(this.regTime)
       .append(this.workCase)
       .append(this.trainCase)
       .append(this.projectCase)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("resumeId", this.resumeId)
       .append("fullname", this.fullname)
       .append("age", this.age)
       .append("birthday", this.birthday)
       .append("address", this.address)
       .append("zip", this.zip)
       .append("sex", this.sex)
       .append("position", this.position)
       .append("phone", this.phone)
       .append("mobile", this.mobile)
       .append("email", this.email)
       .append("hobby", this.hobby)
       .append("religion", this.religion)
       .append("party", this.party)
       .append("nationality", this.nationality)
       .append("race", this.race)
       .append("birthPlace", this.birthPlace)
       .append("eduCollege", this.eduCollege)
       .append("eduDegree", this.eduDegree)
       .append("eduMajor", this.eduMajor)
       .append("startWorkDate", this.startWorkDate)
       .append("idNo", this.idNo)
       .append("photo", this.photo)
       .append("status", this.status)
       .append("memo", this.memo)
       .append("registor", this.registor)
       .append("regTime", this.regTime)
       .append("workCase", this.workCase)
       .append("trainCase", this.trainCase)
       .append("projectCase", this.projectCase)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.hrm.Resume
 * JD-Core Version:    0.5.4
 */