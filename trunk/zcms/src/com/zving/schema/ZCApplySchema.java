 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCApplySchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private String Name;
   private String Gender;
   private Date BirthDate;
   private String Picture;
   private String Ethnicity;
   private String NativePlace;
   private String Political;
   private String CertNumber;
   private String Phone;
   private String Mobile;
   private String Address;
   private String Postcode;
   private String Email;
   private String ForeignLanguage;
   private String LanguageLevel;
   private String Authentification;
   private String PersonIntro;
   private String Honour;
   private String PracticeExperience;
   private String RegisteredPlace;
   private String EduLevel;
   private String University;
   private String Speacility;
   private String WillPosition;
   private String AuditUser;
   private String AuditStatus;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private Date AddTime;
   private String AddUser;
   private Date ModifyTime;
   private String ModifyUser;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, false, false), 
     new SchemaColumn("Name", 1, 2, 25, 0, false, false), 
     new SchemaColumn("Gender", 1, 3, 1, 0, false, false), 
     new SchemaColumn("BirthDate", 0, 4, 0, 0, false, false), 
     new SchemaColumn("Picture", 1, 5, 50, 0, false, false), 
     new SchemaColumn("Ethnicity", 1, 6, 3, 0, false, false), 
     new SchemaColumn("NativePlace", 1, 7, 10, 0, false, false), 
     new SchemaColumn("Political", 1, 8, 3, 0, false, false), 
     new SchemaColumn("CertNumber", 1, 9, 20, 0, false, false), 
     new SchemaColumn("Phone", 1, 10, 20, 0, false, false), 
     new SchemaColumn("Mobile", 1, 11, 20, 0, false, false), 
     new SchemaColumn("Address", 1, 12, 200, 0, false, false), 
     new SchemaColumn("Postcode", 1, 13, 10, 0, false, false), 
     new SchemaColumn("Email", 1, 14, 100, 0, false, false), 
     new SchemaColumn("ForeignLanguage", 1, 15, 50, 0, false, false), 
     new SchemaColumn("LanguageLevel", 1, 16, 50, 0, false, false), 
     new SchemaColumn("Authentification", 1, 17, 200, 0, false, false), 
     new SchemaColumn("PersonIntro", 1, 18, 1500, 0, false, false), 
     new SchemaColumn("Honour", 1, 19, 1500, 0, false, false), 
     new SchemaColumn("PracticeExperience", 1, 20, 2000, 0, false, false), 
     new SchemaColumn("RegisteredPlace", 1, 21, 10, 0, false, false), 
     new SchemaColumn("EduLevel", 1, 22, 3, 0, false, false), 
     new SchemaColumn("University", 1, 23, 40, 0, false, false), 
     new SchemaColumn("Speacility", 1, 24, 100, 0, false, false), 
     new SchemaColumn("WillPosition", 1, 25, 50, 0, false, false), 
     new SchemaColumn("AuditUser", 1, 26, 50, 0, false, false), 
     new SchemaColumn("AuditStatus", 1, 27, 5, 0, false, false), 
     new SchemaColumn("Prop1", 1, 28, 100, 0, false, false), 
     new SchemaColumn("Prop2", 1, 29, 100, 0, false, false), 
     new SchemaColumn("Prop3", 1, 30, 100, 0, false, false), 
     new SchemaColumn("Prop4", 1, 31, 100, 0, false, false), 
     new SchemaColumn("AddTime", 0, 32, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 33, 100, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 34, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 35, 100, 0, false, false) };
   public static final String _TableCode = "ZCApply";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCApply values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCApply set ID=?,SiteID=?,Name=?,Gender=?,BirthDate=?,Picture=?,Ethnicity=?,NativePlace=?,Political=?,CertNumber=?,Phone=?,Mobile=?,Address=?,Postcode=?,Email=?,ForeignLanguage=?,LanguageLevel=?,Authentification=?,PersonIntro=?,Honour=?,PracticeExperience=?,RegisteredPlace=?,EduLevel=?,University=?,Speacility=?,WillPosition=?,AuditUser=?,AuditStatus=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCApply  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCApply  where ID=?";
 
   public ZCApplySchema()
   {
     this.TableCode = "ZCApply";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCApply values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCApply set ID=?,SiteID=?,Name=?,Gender=?,BirthDate=?,Picture=?,Ethnicity=?,NativePlace=?,Political=?,CertNumber=?,Phone=?,Mobile=?,Address=?,Postcode=?,Email=?,ForeignLanguage=?,LanguageLevel=?,Authentification=?,PersonIntro=?,Honour=?,PracticeExperience=?,RegisteredPlace=?,EduLevel=?,University=?,Speacility=?,WillPosition=?,AuditUser=?,AuditStatus=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where ID=?";
     this.DeleteSQL = "delete from ZCApply  where ID=?";
     this.FillAllSQL = "select * from ZCApply  where ID=?";
     this.HasSetFlag = new boolean[36];
   }
 
   protected Schema newInstance() {
     return new ZCApplySchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCApplySet();
   }
 
   public ZCApplySet query() {
     return query(null, -1, -1);
   }
 
   public ZCApplySet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCApplySet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCApplySet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCApplySet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { this.Name = ((String)v); return; }
     if (i == 3) { this.Gender = ((String)v); return; }
     if (i == 4) { this.BirthDate = ((Date)v); return; }
     if (i == 5) { this.Picture = ((String)v); return; }
     if (i == 6) { this.Ethnicity = ((String)v); return; }
     if (i == 7) { this.NativePlace = ((String)v); return; }
     if (i == 8) { this.Political = ((String)v); return; }
     if (i == 9) { this.CertNumber = ((String)v); return; }
     if (i == 10) { this.Phone = ((String)v); return; }
     if (i == 11) { this.Mobile = ((String)v); return; }
     if (i == 12) { this.Address = ((String)v); return; }
     if (i == 13) { this.Postcode = ((String)v); return; }
     if (i == 14) { this.Email = ((String)v); return; }
     if (i == 15) { this.ForeignLanguage = ((String)v); return; }
     if (i == 16) { this.LanguageLevel = ((String)v); return; }
     if (i == 17) { this.Authentification = ((String)v); return; }
     if (i == 18) { this.PersonIntro = ((String)v); return; }
     if (i == 19) { this.Honour = ((String)v); return; }
     if (i == 20) { this.PracticeExperience = ((String)v); return; }
     if (i == 21) { this.RegisteredPlace = ((String)v); return; }
     if (i == 22) { this.EduLevel = ((String)v); return; }
     if (i == 23) { this.University = ((String)v); return; }
     if (i == 24) { this.Speacility = ((String)v); return; }
     if (i == 25) { this.WillPosition = ((String)v); return; }
     if (i == 26) { this.AuditUser = ((String)v); return; }
     if (i == 27) { this.AuditStatus = ((String)v); return; }
     if (i == 28) { this.Prop1 = ((String)v); return; }
     if (i == 29) { this.Prop2 = ((String)v); return; }
     if (i == 30) { this.Prop3 = ((String)v); return; }
     if (i == 31) { this.Prop4 = ((String)v); return; }
     if (i == 32) { this.AddTime = ((Date)v); return; }
     if (i == 33) { this.AddUser = ((String)v); return; }
     if (i == 34) { this.ModifyTime = ((Date)v); return; }
     if (i != 35) return; this.ModifyUser = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.Name;
     if (i == 3) return this.Gender;
     if (i == 4) return this.BirthDate;
     if (i == 5) return this.Picture;
     if (i == 6) return this.Ethnicity;
     if (i == 7) return this.NativePlace;
     if (i == 8) return this.Political;
     if (i == 9) return this.CertNumber;
     if (i == 10) return this.Phone;
     if (i == 11) return this.Mobile;
     if (i == 12) return this.Address;
     if (i == 13) return this.Postcode;
     if (i == 14) return this.Email;
     if (i == 15) return this.ForeignLanguage;
     if (i == 16) return this.LanguageLevel;
     if (i == 17) return this.Authentification;
     if (i == 18) return this.PersonIntro;
     if (i == 19) return this.Honour;
     if (i == 20) return this.PracticeExperience;
     if (i == 21) return this.RegisteredPlace;
     if (i == 22) return this.EduLevel;
     if (i == 23) return this.University;
     if (i == 24) return this.Speacility;
     if (i == 25) return this.WillPosition;
     if (i == 26) return this.AuditUser;
     if (i == 27) return this.AuditStatus;
     if (i == 28) return this.Prop1;
     if (i == 29) return this.Prop2;
     if (i == 30) return this.Prop3;
     if (i == 31) return this.Prop4;
     if (i == 32) return this.AddTime;
     if (i == 33) return this.AddUser;
     if (i == 34) return this.ModifyTime;
     if (i == 35) return this.ModifyUser;
     return null;
   }
 
   public long getID()
   {
     if (this.ID == null) return 0L;
     return this.ID.longValue();
   }
 
   public void setID(long iD)
   {
     this.ID = new Long(iD);
   }
 
   public void setID(String iD)
   {
     if (iD == null) {
       this.ID = null;
       return;
     }
     this.ID = new Long(iD);
   }
 
   public long getSiteID()
   {
     if (this.SiteID == null) return 0L;
     return this.SiteID.longValue();
   }
 
   public void setSiteID(long siteID)
   {
     this.SiteID = new Long(siteID);
   }
 
   public void setSiteID(String siteID)
   {
     if (siteID == null) {
       this.SiteID = null;
       return;
     }
     this.SiteID = new Long(siteID);
   }
 
   public String getName()
   {
     return this.Name;
   }
 
   public void setName(String name)
   {
     this.Name = name;
   }
 
   public String getGender()
   {
     return this.Gender;
   }
 
   public void setGender(String gender)
   {
     this.Gender = gender;
   }
 
   public Date getBirthDate()
   {
     return this.BirthDate;
   }
 
   public void setBirthDate(Date birthDate)
   {
     this.BirthDate = birthDate;
   }
 
   public String getPicture()
   {
     return this.Picture;
   }
 
   public void setPicture(String picture)
   {
     this.Picture = picture;
   }
 
   public String getEthnicity()
   {
     return this.Ethnicity;
   }
 
   public void setEthnicity(String ethnicity)
   {
     this.Ethnicity = ethnicity;
   }
 
   public String getNativePlace()
   {
     return this.NativePlace;
   }
 
   public void setNativePlace(String nativePlace)
   {
     this.NativePlace = nativePlace;
   }
 
   public String getPolitical()
   {
     return this.Political;
   }
 
   public void setPolitical(String political)
   {
     this.Political = political;
   }
 
   public String getCertNumber()
   {
     return this.CertNumber;
   }
 
   public void setCertNumber(String certNumber)
   {
     this.CertNumber = certNumber;
   }
 
   public String getPhone()
   {
     return this.Phone;
   }
 
   public void setPhone(String phone)
   {
     this.Phone = phone;
   }
 
   public String getMobile()
   {
     return this.Mobile;
   }
 
   public void setMobile(String mobile)
   {
     this.Mobile = mobile;
   }
 
   public String getAddress()
   {
     return this.Address;
   }
 
   public void setAddress(String address)
   {
     this.Address = address;
   }
 
   public String getPostcode()
   {
     return this.Postcode;
   }
 
   public void setPostcode(String postcode)
   {
     this.Postcode = postcode;
   }
 
   public String getEmail()
   {
     return this.Email;
   }
 
   public void setEmail(String email)
   {
     this.Email = email;
   }
 
   public String getForeignLanguage()
   {
     return this.ForeignLanguage;
   }
 
   public void setForeignLanguage(String foreignLanguage)
   {
     this.ForeignLanguage = foreignLanguage;
   }
 
   public String getLanguageLevel()
   {
     return this.LanguageLevel;
   }
 
   public void setLanguageLevel(String languageLevel)
   {
     this.LanguageLevel = languageLevel;
   }
 
   public String getAuthentification()
   {
     return this.Authentification;
   }
 
   public void setAuthentification(String authentification)
   {
     this.Authentification = authentification;
   }
 
   public String getPersonIntro()
   {
     return this.PersonIntro;
   }
 
   public void setPersonIntro(String personIntro)
   {
     this.PersonIntro = personIntro;
   }
 
   public String getHonour()
   {
     return this.Honour;
   }
 
   public void setHonour(String honour)
   {
     this.Honour = honour;
   }
 
   public String getPracticeExperience()
   {
     return this.PracticeExperience;
   }
 
   public void setPracticeExperience(String practiceExperience)
   {
     this.PracticeExperience = practiceExperience;
   }
 
   public String getRegisteredPlace()
   {
     return this.RegisteredPlace;
   }
 
   public void setRegisteredPlace(String registeredPlace)
   {
     this.RegisteredPlace = registeredPlace;
   }
 
   public String getEduLevel()
   {
     return this.EduLevel;
   }
 
   public void setEduLevel(String eduLevel)
   {
     this.EduLevel = eduLevel;
   }
 
   public String getUniversity()
   {
     return this.University;
   }
 
   public void setUniversity(String university)
   {
     this.University = university;
   }
 
   public String getSpeacility()
   {
     return this.Speacility;
   }
 
   public void setSpeacility(String speacility)
   {
     this.Speacility = speacility;
   }
 
   public String getWillPosition()
   {
     return this.WillPosition;
   }
 
   public void setWillPosition(String willPosition)
   {
     this.WillPosition = willPosition;
   }
 
   public String getAuditUser()
   {
     return this.AuditUser;
   }
 
   public void setAuditUser(String auditUser)
   {
     this.AuditUser = auditUser;
   }
 
   public String getAuditStatus()
   {
     return this.AuditStatus;
   }
 
   public void setAuditStatus(String auditStatus)
   {
     this.AuditStatus = auditStatus;
   }
 
   public String getProp1()
   {
     return this.Prop1;
   }
 
   public void setProp1(String prop1)
   {
     this.Prop1 = prop1;
   }
 
   public String getProp2()
   {
     return this.Prop2;
   }
 
   public void setProp2(String prop2)
   {
     this.Prop2 = prop2;
   }
 
   public String getProp3()
   {
     return this.Prop3;
   }
 
   public void setProp3(String prop3)
   {
     this.Prop3 = prop3;
   }
 
   public String getProp4()
   {
     return this.Prop4;
   }
 
   public void setProp4(String prop4)
   {
     this.Prop4 = prop4;
   }
 
   public Date getAddTime()
   {
     return this.AddTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.AddTime = addTime;
   }
 
   public String getAddUser()
   {
     return this.AddUser;
   }
 
   public void setAddUser(String addUser)
   {
     this.AddUser = addUser;
   }
 
   public Date getModifyTime()
   {
     return this.ModifyTime;
   }
 
   public void setModifyTime(Date modifyTime)
   {
     this.ModifyTime = modifyTime;
   }
 
   public String getModifyUser()
   {
     return this.ModifyUser;
   }
 
   public void setModifyUser(String modifyUser)
   {
     this.ModifyUser = modifyUser;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCApplySchema
 * JD-Core Version:    0.5.3
 */