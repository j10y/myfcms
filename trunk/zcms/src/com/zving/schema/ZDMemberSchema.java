 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZDMemberSchema extends Schema
 {
   private String UserName;
   private String Password;
   private String Name;
   private String Email;
   private String Gender;
   private String Type;
   private Long SiteID;
   private String Logo;
   private String Status;
   private String Score;
   private String Rank;
   private String MemberLevel;
   private String PWQuestion;
   private String PWAnswer;
   private String LastLoginIP;
   private Date LastLoginTime;
   private Date RegTime;
   private String RegIP;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private String Prop5;
   private String Prop6;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("UserName", 1, 0, 50, 0, true, true), 
     new SchemaColumn("Password", 1, 1, 32, 0, true, false), 
     new SchemaColumn("Name", 1, 2, 100, 0, true, false), 
     new SchemaColumn("Email", 1, 3, 100, 0, true, false), 
     new SchemaColumn("Gender", 1, 4, 1, 0, false, false), 
     new SchemaColumn("Type", 1, 5, 10, 0, false, false), 
     new SchemaColumn("SiteID", 7, 6, 20, 0, false, false), 
     new SchemaColumn("Logo", 1, 7, 100, 0, false, false), 
     new SchemaColumn("Status", 1, 8, 1, 0, true, false), 
     new SchemaColumn("Score", 1, 9, 20, 0, false, false), 
     new SchemaColumn("Rank", 1, 10, 50, 0, false, false), 
     new SchemaColumn("MemberLevel", 1, 11, 10, 0, false, false), 
     new SchemaColumn("PWQuestion", 1, 12, 100, 0, false, false), 
     new SchemaColumn("PWAnswer", 1, 13, 100, 0, false, false), 
     new SchemaColumn("LastLoginIP", 1, 14, 16, 0, false, false), 
     new SchemaColumn("LastLoginTime", 0, 15, 0, 0, false, false), 
     new SchemaColumn("RegTime", 0, 16, 0, 0, false, false), 
     new SchemaColumn("RegIP", 1, 17, 16, 0, false, false), 
     new SchemaColumn("Prop1", 1, 18, 100, 0, false, false), 
     new SchemaColumn("Prop2", 1, 19, 100, 0, false, false), 
     new SchemaColumn("Prop3", 1, 20, 100, 0, false, false), 
     new SchemaColumn("Prop4", 1, 21, 100, 0, false, false), 
     new SchemaColumn("Prop5", 1, 22, 100, 0, false, false), 
     new SchemaColumn("Prop6", 1, 23, 100, 0, false, false) };
   public static final String _TableCode = "ZDMember";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDMember values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDMember set UserName=?,Password=?,Name=?,Email=?,Gender=?,Type=?,SiteID=?,Logo=?,Status=?,Score=?,Rank=?,MemberLevel=?,PWQuestion=?,PWAnswer=?,LastLoginIP=?,LastLoginTime=?,RegTime=?,RegIP=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Prop5=?,Prop6=? where UserName=?";
   protected static final String _DeleteSQL = "delete from ZDMember  where UserName=?";
   protected static final String _FillAllSQL = "select * from ZDMember  where UserName=?";
 
   public ZDMemberSchema()
   {
     this.TableCode = "ZDMember";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDMember values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDMember set UserName=?,Password=?,Name=?,Email=?,Gender=?,Type=?,SiteID=?,Logo=?,Status=?,Score=?,Rank=?,MemberLevel=?,PWQuestion=?,PWAnswer=?,LastLoginIP=?,LastLoginTime=?,RegTime=?,RegIP=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Prop5=?,Prop6=? where UserName=?";
     this.DeleteSQL = "delete from ZDMember  where UserName=?";
     this.FillAllSQL = "select * from ZDMember  where UserName=?";
     this.HasSetFlag = new boolean[24];
   }
 
   protected Schema newInstance() {
     return new ZDMemberSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDMemberSet();
   }
 
   public ZDMemberSet query() {
     return query(null, -1, -1);
   }
 
   public ZDMemberSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDMemberSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDMemberSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDMemberSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.UserName = ((String)v); return; }
     if (i == 1) { this.Password = ((String)v); return; }
     if (i == 2) { this.Name = ((String)v); return; }
     if (i == 3) { this.Email = ((String)v); return; }
     if (i == 4) { this.Gender = ((String)v); return; }
     if (i == 5) { this.Type = ((String)v); return; }
     if (i == 6) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 7) { this.Logo = ((String)v); return; }
     if (i == 8) { this.Status = ((String)v); return; }
     if (i == 9) { this.Score = ((String)v); return; }
     if (i == 10) { this.Rank = ((String)v); return; }
     if (i == 11) { this.MemberLevel = ((String)v); return; }
     if (i == 12) { this.PWQuestion = ((String)v); return; }
     if (i == 13) { this.PWAnswer = ((String)v); return; }
     if (i == 14) { this.LastLoginIP = ((String)v); return; }
     if (i == 15) { this.LastLoginTime = ((Date)v); return; }
     if (i == 16) { this.RegTime = ((Date)v); return; }
     if (i == 17) { this.RegIP = ((String)v); return; }
     if (i == 18) { this.Prop1 = ((String)v); return; }
     if (i == 19) { this.Prop2 = ((String)v); return; }
     if (i == 20) { this.Prop3 = ((String)v); return; }
     if (i == 21) { this.Prop4 = ((String)v); return; }
     if (i == 22) { this.Prop5 = ((String)v); return; }
     if (i != 23) return; this.Prop6 = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.UserName;
     if (i == 1) return this.Password;
     if (i == 2) return this.Name;
     if (i == 3) return this.Email;
     if (i == 4) return this.Gender;
     if (i == 5) return this.Type;
     if (i == 6) return this.SiteID;
     if (i == 7) return this.Logo;
     if (i == 8) return this.Status;
     if (i == 9) return this.Score;
     if (i == 10) return this.Rank;
     if (i == 11) return this.MemberLevel;
     if (i == 12) return this.PWQuestion;
     if (i == 13) return this.PWAnswer;
     if (i == 14) return this.LastLoginIP;
     if (i == 15) return this.LastLoginTime;
     if (i == 16) return this.RegTime;
     if (i == 17) return this.RegIP;
     if (i == 18) return this.Prop1;
     if (i == 19) return this.Prop2;
     if (i == 20) return this.Prop3;
     if (i == 21) return this.Prop4;
     if (i == 22) return this.Prop5;
     if (i == 23) return this.Prop6;
     return null;
   }
 
   public String getUserName()
   {
     return this.UserName;
   }
 
   public void setUserName(String userName)
   {
     this.UserName = userName;
   }
 
   public String getPassword()
   {
     return this.Password;
   }
 
   public void setPassword(String password)
   {
     this.Password = password;
   }
 
   public String getName()
   {
     return this.Name;
   }
 
   public void setName(String name)
   {
     this.Name = name;
   }
 
   public String getEmail()
   {
     return this.Email;
   }
 
   public void setEmail(String email)
   {
     this.Email = email;
   }
 
   public String getGender()
   {
     return this.Gender;
   }
 
   public void setGender(String gender)
   {
     this.Gender = gender;
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
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
 
   public String getLogo()
   {
     return this.Logo;
   }
 
   public void setLogo(String logo)
   {
     this.Logo = logo;
   }
 
   public String getStatus()
   {
     return this.Status;
   }
 
   public void setStatus(String status)
   {
     this.Status = status;
   }
 
   public String getScore()
   {
     return this.Score;
   }
 
   public void setScore(String score)
   {
     this.Score = score;
   }
 
   public String getRank()
   {
     return this.Rank;
   }
 
   public void setRank(String rank)
   {
     this.Rank = rank;
   }
 
   public String getMemberLevel()
   {
     return this.MemberLevel;
   }
 
   public void setMemberLevel(String memberLevel)
   {
     this.MemberLevel = memberLevel;
   }
 
   public String getPWQuestion()
   {
     return this.PWQuestion;
   }
 
   public void setPWQuestion(String pWQuestion)
   {
     this.PWQuestion = pWQuestion;
   }
 
   public String getPWAnswer()
   {
     return this.PWAnswer;
   }
 
   public void setPWAnswer(String pWAnswer)
   {
     this.PWAnswer = pWAnswer;
   }
 
   public String getLastLoginIP()
   {
     return this.LastLoginIP;
   }
 
   public void setLastLoginIP(String lastLoginIP)
   {
     this.LastLoginIP = lastLoginIP;
   }
 
   public Date getLastLoginTime()
   {
     return this.LastLoginTime;
   }
 
   public void setLastLoginTime(Date lastLoginTime)
   {
     this.LastLoginTime = lastLoginTime;
   }
 
   public Date getRegTime()
   {
     return this.RegTime;
   }
 
   public void setRegTime(Date regTime)
   {
     this.RegTime = regTime;
   }
 
   public String getRegIP()
   {
     return this.RegIP;
   }
 
   public void setRegIP(String regIP)
   {
     this.RegIP = regIP;
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
 
   public String getProp5()
   {
     return this.Prop5;
   }
 
   public void setProp5(String prop5)
   {
     this.Prop5 = prop5;
   }
 
   public String getProp6()
   {
     return this.Prop6;
   }
 
   public void setProp6(String prop6)
   {
     this.Prop6 = prop6;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDMemberSchema
 * JD-Core Version:    0.5.3
 */