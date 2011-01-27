 package com.htsoft.oa.model.hrm;
 
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class EmpProfile extends BaseModel
 {
   public static short CHECK_FLAG_NONE = 0;
   public static short CHECK_FLAG_PASS = 1;
   public static short CHECK_FLAG_NOT_PASS = 2;
 
   public static short DELETE_FLAG_NOT = 0;
   public static short DELETE_FLAG_HAD = 1;
   protected Long profileId;
   protected String profileNo;
   protected String fullname;
   protected String address;
   protected Date birthday;
   protected String homeZip;
   protected String sex;
   protected String marriage;
   protected String designation;
   protected String position;
   protected String phone;
   protected String mobile;
   protected String openBank;
   protected String bankNo;
   protected String qq;
   protected String email;
   protected String hobby;
   protected String religion;
   protected String party;
   protected String nationality;
   protected String race;
   protected String birthPlace;
   protected String eduDegree;
   protected String eduMajor;
   protected String eduCollege;
   protected Date startWorkDate;
   protected String eduCase;
   protected String awardPunishCase;
   protected String trainingCase;
   protected String workCase;
   protected String idCard;
   protected String photo;
   protected String standardMiNo;
   protected BigDecimal standardMoney;
   protected String standardName;
   protected String creator;
   protected Date createtime;
   protected String checkName;
   protected Date checktime;
   protected Short approvalStatus;
   protected String memo;
   protected String depName;
   protected Long depId;
   protected Short delFlag;
   protected String opprovalOpinion;
   protected Long userId;
   protected Long jobId;
   protected StandSalary standSalary;
 
   public Long getJobId()
   {
     return this.jobId;
   }
 
   public void setJobId(Long jobId) {
     this.jobId = jobId;
   }
 
   public EmpProfile()
   {
   }
 
   public EmpProfile(Long in_profileId)
   {
     setProfileId(in_profileId);
   }
 
   public StandSalary getStandSalary()
   {
     return this.standSalary;
   }
 
   public void setStandSalary(StandSalary in_standSalary) {
     this.standSalary = in_standSalary;
   }
 
   public Long getProfileId()
   {
     return this.profileId;
   }
 
   public void setProfileId(Long aValue)
   {
     this.profileId = aValue;
   }
 
   public String getProfileNo()
   {
     return this.profileNo;
   }
 
   public void setProfileNo(String aValue)
   {
     this.profileNo = aValue;
   }
 
   public String getFullname()
   {
     return this.fullname;
   }
 
   public void setFullname(String aValue)
   {
     this.fullname = aValue;
   }
 
   public String getAddress()
   {
     return this.address;
   }
 
   public void setAddress(String aValue)
   {
     this.address = aValue;
   }
 
   public Date getBirthday()
   {
     return this.birthday;
   }
 
   public void setBirthday(Date aValue)
   {
     this.birthday = aValue;
   }
 
   public String getHomeZip()
   {
     return this.homeZip;
   }
 
   public void setHomeZip(String aValue)
   {
     this.homeZip = aValue;
   }
 
   public String getSex()
   {
     return this.sex;
   }
 
   public void setSex(String aValue)
   {
     this.sex = aValue;
   }
 
   public String getMarriage()
   {
     return this.marriage;
   }
 
   public void setMarriage(String aValue)
   {
     this.marriage = aValue;
   }
 
   public String getDesignation()
   {
     return this.designation;
   }
 
   public void setDesignation(String aValue)
   {
     this.designation = aValue;
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
 
   public String getOpenBank()
   {
     return this.openBank;
   }
 
   public void setOpenBank(String aValue)
   {
     this.openBank = aValue;
   }
 
   public String getBankNo()
   {
     return this.bankNo;
   }
 
   public void setBankNo(String aValue)
   {
     this.bankNo = aValue;
   }
 
   public String getQq()
   {
     return this.qq;
   }
 
   public void setQq(String aValue)
   {
     this.qq = aValue;
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
 
   public String getEduCollege()
   {
     return this.eduCollege;
   }
 
   public void setEduCollege(String aValue)
   {
     this.eduCollege = aValue;
   }
 
   public Date getStartWorkDate()
   {
     return this.startWorkDate;
   }
 
   public void setStartWorkDate(Date aValue)
   {
     this.startWorkDate = aValue;
   }
 
   public String getEduCase()
   {
     return this.eduCase;
   }
 
   public void setEduCase(String aValue)
   {
     this.eduCase = aValue;
   }
 
   public String getAwardPunishCase()
   {
     return this.awardPunishCase;
   }
 
   public void setAwardPunishCase(String aValue)
   {
     this.awardPunishCase = aValue;
   }
 
   public String getTrainingCase()
   {
     return this.trainingCase;
   }
 
   public void setTrainingCase(String aValue)
   {
     this.trainingCase = aValue;
   }
 
   public String getWorkCase()
   {
     return this.workCase;
   }
 
   public void setWorkCase(String aValue)
   {
     this.workCase = aValue;
   }
 
   public String getIdCard()
   {
     return this.idCard;
   }
 
   public void setIdCard(String aValue)
   {
     this.idCard = aValue;
   }
 
   public String getPhoto()
   {
     return this.photo;
   }
 
   public void setPhoto(String aValue)
   {
     this.photo = aValue;
   }
 
   public String getStandardMiNo()
   {
     return this.standardMiNo;
   }
 
   public void setStandardMiNo(String aValue)
   {
     this.standardMiNo = aValue;
   }
 
   public BigDecimal getStandardMoney()
   {
     return this.standardMoney;
   }
 
   public void setStandardMoney(BigDecimal aValue)
   {
     this.standardMoney = aValue;
   }
 
   public String getStandardName()
   {
     return this.standardName;
   }
 
   public void setStandardName(String aValue)
   {
     this.standardName = aValue;
   }
 
   public Long getStandardId()
   {
     return (getStandSalary() == null) ? null : getStandSalary().getStandardId();
   }
 
   public void setStandardId(Long aValue)
   {
     if (aValue == null) {
       this.standSalary = null;
     } else if (this.standSalary == null) {
       this.standSalary = new StandSalary(aValue);
       this.standSalary.setVersion(new Integer(0));
     } else {
       this.standSalary.setStandardId(aValue);
     }
   }
 
   public String getCreator()
   {
     return this.creator;
   }
 
   public void setCreator(String aValue)
   {
     this.creator = aValue;
   }
 
   public Date getCreatetime()
   {
     return this.createtime;
   }
 
   public void setCreatetime(Date aValue)
   {
     this.createtime = aValue;
   }
 
   public String getCheckName()
   {
     return this.checkName;
   }
 
   public void setCheckName(String aValue)
   {
     this.checkName = aValue;
   }
 
   public Date getChecktime()
   {
     return this.checktime;
   }
 
   public void setChecktime(Date aValue)
   {
     this.checktime = aValue;
   }
 
   public Short getApprovalStatus()
   {
     return this.approvalStatus;
   }
 
   public void setApprovalStatus(Short aValue)
   {
     this.approvalStatus = aValue;
   }
 
   public String getMemo()
   {
     return this.memo;
   }
 
   public void setMemo(String aValue)
   {
     this.memo = aValue;
   }
 
   public String getDepName()
   {
     return this.depName;
   }
 
   public void setDepName(String aValue)
   {
     this.depName = aValue;
   }
 
   public Long getDepId()
   {
     return this.depId;
   }
 
   public void setDepId(Long aValue)
   {
     this.depId = aValue;
   }
 
   public Short getDelFlag()
   {
     return this.delFlag;
   }
 
   public void setDelFlag(Short aValue)
   {
     this.delFlag = aValue;
   }
 
   public String getOpprovalOpinion() {
     return this.opprovalOpinion;
   }
 
   public void setOpprovalOpinion(String opprovalOpinion) {
     this.opprovalOpinion = opprovalOpinion;
   }
 
   public Long getUserId() {
     return this.userId;
   }
 
   public void setUserId(Long userId) {
     this.userId = userId;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof EmpProfile)) {
       return false;
     }
     EmpProfile rhs = (EmpProfile)object;
     return new EqualsBuilder()
       .append(this.profileId, rhs.profileId)
       .append(this.profileNo, rhs.profileNo)
       .append(this.fullname, rhs.fullname)
       .append(this.address, rhs.address)
       .append(this.birthday, rhs.birthday)
       .append(this.homeZip, rhs.homeZip)
       .append(this.sex, rhs.sex)
       .append(this.marriage, rhs.marriage)
       .append(this.designation, rhs.designation)
       .append(this.position, rhs.position)
       .append(this.phone, rhs.phone)
       .append(this.mobile, rhs.mobile)
       .append(this.openBank, rhs.openBank)
       .append(this.bankNo, rhs.bankNo)
       .append(this.qq, rhs.qq)
       .append(this.email, rhs.email)
       .append(this.hobby, rhs.hobby)
       .append(this.religion, rhs.religion)
       .append(this.party, rhs.party)
       .append(this.nationality, rhs.nationality)
       .append(this.race, rhs.race)
       .append(this.birthPlace, rhs.birthPlace)
       .append(this.eduDegree, rhs.eduDegree)
       .append(this.eduMajor, rhs.eduMajor)
       .append(this.eduCollege, rhs.eduCollege)
       .append(this.startWorkDate, rhs.startWorkDate)
       .append(this.eduCase, rhs.eduCase)
       .append(this.awardPunishCase, rhs.awardPunishCase)
       .append(this.trainingCase, rhs.trainingCase)
       .append(this.workCase, rhs.workCase)
       .append(this.idCard, rhs.idCard)
       .append(this.photo, rhs.photo)
       .append(this.standardMiNo, rhs.standardMiNo)
       .append(this.standardMoney, rhs.standardMoney)
       .append(this.standardName, rhs.standardName)
       .append(this.creator, rhs.creator)
       .append(this.createtime, rhs.createtime)
       .append(this.checkName, rhs.checkName)
       .append(this.checktime, rhs.checktime)
       .append(this.approvalStatus, rhs.approvalStatus)
       .append(this.opprovalOpinion, rhs.opprovalOpinion)
       .append(this.memo, rhs.memo)
       .append(this.depName, rhs.depName)
       .append(this.depId, rhs.depId)
       .append(this.delFlag, rhs.delFlag)
       .append(this.userId, rhs.userId)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.profileId)
       .append(this.profileNo)
       .append(this.fullname)
       .append(this.address)
       .append(this.birthday)
       .append(this.homeZip)
       .append(this.sex)
       .append(this.marriage)
       .append(this.designation)
       .append(this.position)
       .append(this.phone)
       .append(this.mobile)
       .append(this.openBank)
       .append(this.bankNo)
       .append(this.qq)
       .append(this.email)
       .append(this.hobby)
       .append(this.religion)
       .append(this.party)
       .append(this.nationality)
       .append(this.race)
       .append(this.birthPlace)
       .append(this.eduDegree)
       .append(this.eduMajor)
       .append(this.eduCollege)
       .append(this.startWorkDate)
       .append(this.eduCase)
       .append(this.awardPunishCase)
       .append(this.trainingCase)
       .append(this.workCase)
       .append(this.idCard)
       .append(this.photo)
       .append(this.standardMiNo)
       .append(this.standardMoney)
       .append(this.standardName)
       .append(this.creator)
       .append(this.createtime)
       .append(this.checkName)
       .append(this.checktime)
       .append(this.approvalStatus)
       .append(this.memo)
       .append(this.depName)
       .append(this.depId)
       .append(this.delFlag)
       .append(this.opprovalOpinion)
       .append(this.userId)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("profileId", this.profileId)
       .append("profileNo", this.profileNo)
       .append("fullname", this.fullname)
       .append("address", this.address)
       .append("birthday", this.birthday)
       .append("homeZip", this.homeZip)
       .append("sex", this.sex)
       .append("marriage", this.marriage)
       .append("designation", this.designation)
       .append("position", this.position)
       .append("phone", this.phone)
       .append("mobile", this.mobile)
       .append("openBank", this.openBank)
       .append("bankNo", this.bankNo)
       .append("qq", this.qq)
       .append("email", this.email)
       .append("hobby", this.hobby)
       .append("religion", this.religion)
       .append("party", this.party)
       .append("nationality", this.nationality)
       .append("race", this.race)
       .append("birthPlace", this.birthPlace)
       .append("eduDegree", this.eduDegree)
       .append("eduMajor", this.eduMajor)
       .append("eduCollege", this.eduCollege)
       .append("startWorkDate", this.startWorkDate)
       .append("eduCase", this.eduCase)
       .append("awardPunishCase", this.awardPunishCase)
       .append("trainingCase", this.trainingCase)
       .append("workCase", this.workCase)
       .append("idCard", this.idCard)
       .append("photo", this.photo)
       .append("standardMiNo", this.standardMiNo)
       .append("standardMoney", this.standardMoney)
       .append("standardName", this.standardName)
       .append("creator", this.creator)
       .append("createtime", this.createtime)
       .append("checkName", this.checkName)
       .append("checktime", this.checktime)
       .append("approvalStatus", this.approvalStatus)
       .append("memo", this.memo)
       .append("depName", this.depName)
       .append("depId", this.depId)
       .append("delFlag", this.delFlag)
       .append("opprovalOpinion", this.opprovalOpinion)
       .append("userId", this.userId)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.hrm.EmpProfile
 * JD-Core Version:    0.5.4
 */