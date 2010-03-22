 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCAuthorSchema extends Schema
 {
   private Long ID;
   private String Author;
   private String RealName;
   private String Sex;
   private String Email;
   private String Tel;
   private String Mobile;
   private String Address;
   private String Zipcode;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("Author", 1, 1, 100, 0, true, false), 
     new SchemaColumn("RealName", 1, 2, 100, 0, true, false), 
     new SchemaColumn("Sex", 1, 3, 10, 0, false, false), 
     new SchemaColumn("Email", 1, 4, 100, 0, false, false), 
     new SchemaColumn("Tel", 1, 5, 100, 0, false, false), 
     new SchemaColumn("Mobile", 1, 6, 100, 0, false, false), 
     new SchemaColumn("Address", 1, 7, 200, 0, false, false), 
     new SchemaColumn("Zipcode", 1, 8, 20, 0, false, false), 
     new SchemaColumn("Prop1", 1, 9, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 10, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 11, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 12, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 13, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 14, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 15, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 16, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 17, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 18, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 19, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 20, 50, 0, false, false) };
   public static final String _TableCode = "BZCAuthor";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCAuthor values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCAuthor set ID=?,Author=?,RealName=?,Sex=?,Email=?,Tel=?,Mobile=?,Address=?,Zipcode=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCAuthor  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCAuthor  where ID=? and BackupNo=?";
 
   public BZCAuthorSchema()
   {
     this.TableCode = "BZCAuthor";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCAuthor values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAuthor set ID=?,Author=?,RealName=?,Sex=?,Email=?,Tel=?,Mobile=?,Address=?,Zipcode=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAuthor  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAuthor  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[21];
   }
 
   protected Schema newInstance() {
     return new BZCAuthorSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCAuthorSet();
   }
 
   public BZCAuthorSet query() {
     return query(null, -1, -1);
   }
 
   public BZCAuthorSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCAuthorSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCAuthorSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCAuthorSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.Author = ((String)v); return; }
     if (i == 2) { this.RealName = ((String)v); return; }
     if (i == 3) { this.Sex = ((String)v); return; }
     if (i == 4) { this.Email = ((String)v); return; }
     if (i == 5) { this.Tel = ((String)v); return; }
     if (i == 6) { this.Mobile = ((String)v); return; }
     if (i == 7) { this.Address = ((String)v); return; }
     if (i == 8) { this.Zipcode = ((String)v); return; }
     if (i == 9) { this.Prop1 = ((String)v); return; }
     if (i == 10) { this.Prop2 = ((String)v); return; }
     if (i == 11) { this.Prop3 = ((String)v); return; }
     if (i == 12) { this.Prop4 = ((String)v); return; }
     if (i == 13) { this.AddUser = ((String)v); return; }
     if (i == 14) { this.AddTime = ((Date)v); return; }
     if (i == 15) { this.ModifyUser = ((String)v); return; }
     if (i == 16) { this.ModifyTime = ((Date)v); return; }
     if (i == 17) { this.BackupNo = ((String)v); return; }
     if (i == 18) { this.BackupOperator = ((String)v); return; }
     if (i == 19) { this.BackupTime = ((Date)v); return; }
     if (i != 20) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.Author;
     if (i == 2) return this.RealName;
     if (i == 3) return this.Sex;
     if (i == 4) return this.Email;
     if (i == 5) return this.Tel;
     if (i == 6) return this.Mobile;
     if (i == 7) return this.Address;
     if (i == 8) return this.Zipcode;
     if (i == 9) return this.Prop1;
     if (i == 10) return this.Prop2;
     if (i == 11) return this.Prop3;
     if (i == 12) return this.Prop4;
     if (i == 13) return this.AddUser;
     if (i == 14) return this.AddTime;
     if (i == 15) return this.ModifyUser;
     if (i == 16) return this.ModifyTime;
     if (i == 17) return this.BackupNo;
     if (i == 18) return this.BackupOperator;
     if (i == 19) return this.BackupTime;
     if (i == 20) return this.BackupMemo;
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
 
   public String getAuthor()
   {
     return this.Author;
   }
 
   public void setAuthor(String author)
   {
     this.Author = author;
   }
 
   public String getRealName()
   {
     return this.RealName;
   }
 
   public void setRealName(String realName)
   {
     this.RealName = realName;
   }
 
   public String getSex()
   {
     return this.Sex;
   }
 
   public void setSex(String sex)
   {
     this.Sex = sex;
   }
 
   public String getEmail()
   {
     return this.Email;
   }
 
   public void setEmail(String email)
   {
     this.Email = email;
   }
 
   public String getTel()
   {
     return this.Tel;
   }
 
   public void setTel(String tel)
   {
     this.Tel = tel;
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
 
   public String getZipcode()
   {
     return this.Zipcode;
   }
 
   public void setZipcode(String zipcode)
   {
     this.Zipcode = zipcode;
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
 
   public String getAddUser()
   {
     return this.AddUser;
   }
 
   public void setAddUser(String addUser)
   {
     this.AddUser = addUser;
   }
 
   public Date getAddTime()
   {
     return this.AddTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.AddTime = addTime;
   }
 
   public String getModifyUser()
   {
     return this.ModifyUser;
   }
 
   public void setModifyUser(String modifyUser)
   {
     this.ModifyUser = modifyUser;
   }
 
   public Date getModifyTime()
   {
     return this.ModifyTime;
   }
 
   public void setModifyTime(Date modifyTime)
   {
     this.ModifyTime = modifyTime;
   }
 
   public String getBackupNo()
   {
     return this.BackupNo;
   }
 
   public void setBackupNo(String backupNo)
   {
     this.BackupNo = backupNo;
   }
 
   public String getBackupOperator()
   {
     return this.BackupOperator;
   }
 
   public void setBackupOperator(String backupOperator)
   {
     this.BackupOperator = backupOperator;
   }
 
   public Date getBackupTime()
   {
     return this.BackupTime;
   }
 
   public void setBackupTime(Date backupTime)
   {
     this.BackupTime = backupTime;
   }
 
   public String getBackupMemo()
   {
     return this.BackupMemo;
   }
 
   public void setBackupMemo(String backupMemo)
   {
     this.BackupMemo = backupMemo;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCAuthorSchema
 * JD-Core Version:    0.5.3
 */