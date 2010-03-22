 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDMemberPersonInfoSchema extends Schema
 {
   private String UserName;
   private String NickName;
   private String Birthday;
   private String QQ;
   private String MSN;
   private String Tel;
   private String Mobile;
   private String Address;
   private String ZipCode;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("UserName", 1, 0, 50, 0, true, true), 
     new SchemaColumn("NickName", 1, 1, 100, 0, false, false), 
     new SchemaColumn("Birthday", 1, 2, 20, 0, false, false), 
     new SchemaColumn("QQ", 1, 3, 10, 0, false, false), 
     new SchemaColumn("MSN", 1, 4, 50, 0, false, false), 
     new SchemaColumn("Tel", 1, 5, 20, 0, false, false), 
     new SchemaColumn("Mobile", 1, 6, 20, 0, false, false), 
     new SchemaColumn("Address", 1, 7, 100, 0, false, false), 
     new SchemaColumn("ZipCode", 1, 8, 10, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 9, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 10, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 11, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 12, 50, 0, false, false) };
   public static final String _TableCode = "BZDMemberPersonInfo";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDMemberPersonInfo values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDMemberPersonInfo set UserName=?,NickName=?,Birthday=?,QQ=?,MSN=?,Tel=?,Mobile=?,Address=?,ZipCode=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDMemberPersonInfo  where UserName=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDMemberPersonInfo  where UserName=? and BackupNo=?";
 
   public BZDMemberPersonInfoSchema()
   {
     this.TableCode = "BZDMemberPersonInfo";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDMemberPersonInfo values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDMemberPersonInfo set UserName=?,NickName=?,Birthday=?,QQ=?,MSN=?,Tel=?,Mobile=?,Address=?,ZipCode=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where UserName=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDMemberPersonInfo  where UserName=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDMemberPersonInfo  where UserName=? and BackupNo=?";
     this.HasSetFlag = new boolean[13];
   }
 
   protected Schema newInstance() {
     return new BZDMemberPersonInfoSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDMemberPersonInfoSet();
   }
 
   public BZDMemberPersonInfoSet query() {
     return query(null, -1, -1);
   }
 
   public BZDMemberPersonInfoSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDMemberPersonInfoSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDMemberPersonInfoSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDMemberPersonInfoSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.UserName = ((String)v); return; }
     if (i == 1) { this.NickName = ((String)v); return; }
     if (i == 2) { this.Birthday = ((String)v); return; }
     if (i == 3) { this.QQ = ((String)v); return; }
     if (i == 4) { this.MSN = ((String)v); return; }
     if (i == 5) { this.Tel = ((String)v); return; }
     if (i == 6) { this.Mobile = ((String)v); return; }
     if (i == 7) { this.Address = ((String)v); return; }
     if (i == 8) { this.ZipCode = ((String)v); return; }
     if (i == 9) { this.BackupNo = ((String)v); return; }
     if (i == 10) { this.BackupOperator = ((String)v); return; }
     if (i == 11) { this.BackupTime = ((Date)v); return; }
     if (i != 12) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.UserName;
     if (i == 1) return this.NickName;
     if (i == 2) return this.Birthday;
     if (i == 3) return this.QQ;
     if (i == 4) return this.MSN;
     if (i == 5) return this.Tel;
     if (i == 6) return this.Mobile;
     if (i == 7) return this.Address;
     if (i == 8) return this.ZipCode;
     if (i == 9) return this.BackupNo;
     if (i == 10) return this.BackupOperator;
     if (i == 11) return this.BackupTime;
     if (i == 12) return this.BackupMemo;
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
 
   public String getNickName()
   {
     return this.NickName;
   }
 
   public void setNickName(String nickName)
   {
     this.NickName = nickName;
   }
 
   public String getBirthday()
   {
     return this.Birthday;
   }
 
   public void setBirthday(String birthday)
   {
     this.Birthday = birthday;
   }
 
   public String getQQ()
   {
     return this.QQ;
   }
 
   public void setQQ(String qQ)
   {
     this.QQ = qQ;
   }
 
   public String getMSN()
   {
     return this.MSN;
   }
 
   public void setMSN(String mSN)
   {
     this.MSN = mSN;
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
 
   public String getZipCode()
   {
     return this.ZipCode;
   }
 
   public void setZipCode(String zipCode)
   {
     this.ZipCode = zipCode;
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
 * Qualified Name:     com.zving.schema.BZDMemberPersonInfoSchema
 * JD-Core Version:    0.5.3
 */