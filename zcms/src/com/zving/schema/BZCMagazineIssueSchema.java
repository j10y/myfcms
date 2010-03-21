 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCMagazineIssueSchema extends Schema
 {
   private Long ID;
   private Long MagazineID;
   private String Year;
   private String PeriodNum;
   private String CoverImage;
   private String CoverTemplate;
   private Date PublishDate;
   private Long Status;
   private String Memo;
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
     new SchemaColumn("MagazineID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Year", 1, 2, 50, 0, false, false), 
     new SchemaColumn("PeriodNum", 1, 3, 50, 0, true, false), 
     new SchemaColumn("CoverImage", 1, 4, 100, 0, false, false), 
     new SchemaColumn("CoverTemplate", 1, 5, 100, 0, false, false), 
     new SchemaColumn("PublishDate", 0, 6, 0, 0, false, false), 
     new SchemaColumn("Status", 7, 7, 0, 0, false, false), 
     new SchemaColumn("Memo", 1, 8, 1000, 0, false, false), 
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
   public static final String _TableCode = "BZCMagazineIssue";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCMagazineIssue values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCMagazineIssue set ID=?,MagazineID=?,Year=?,PeriodNum=?,CoverImage=?,CoverTemplate=?,PublishDate=?,Status=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCMagazineIssue  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCMagazineIssue  where ID=? and BackupNo=?";
 
   public BZCMagazineIssueSchema()
   {
     this.TableCode = "BZCMagazineIssue";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCMagazineIssue values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCMagazineIssue set ID=?,MagazineID=?,Year=?,PeriodNum=?,CoverImage=?,CoverTemplate=?,PublishDate=?,Status=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCMagazineIssue  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCMagazineIssue  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[21];
   }
 
   protected Schema newInstance() {
     return new BZCMagazineIssueSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCMagazineIssueSet();
   }
 
   public BZCMagazineIssueSet query() {
     return query(null, -1, -1);
   }
 
   public BZCMagazineIssueSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCMagazineIssueSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCMagazineIssueSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCMagazineIssueSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.MagazineID = null; else this.MagazineID = new Long(v.toString()); return; }
     if (i == 2) { this.Year = ((String)v); return; }
     if (i == 3) { this.PeriodNum = ((String)v); return; }
     if (i == 4) { this.CoverImage = ((String)v); return; }
     if (i == 5) { this.CoverTemplate = ((String)v); return; }
     if (i == 6) { this.PublishDate = ((Date)v); return; }
     if (i == 7) { if (v == null) this.Status = null; else this.Status = new Long(v.toString()); return; }
     if (i == 8) { this.Memo = ((String)v); return; }
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
     if (i == 1) return this.MagazineID;
     if (i == 2) return this.Year;
     if (i == 3) return this.PeriodNum;
     if (i == 4) return this.CoverImage;
     if (i == 5) return this.CoverTemplate;
     if (i == 6) return this.PublishDate;
     if (i == 7) return this.Status;
     if (i == 8) return this.Memo;
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
 
   public long getMagazineID()
   {
     if (this.MagazineID == null) return 0L;
     return this.MagazineID.longValue();
   }
 
   public void setMagazineID(long magazineID)
   {
     this.MagazineID = new Long(magazineID);
   }
 
   public void setMagazineID(String magazineID)
   {
     if (magazineID == null) {
       this.MagazineID = null;
       return;
     }
     this.MagazineID = new Long(magazineID);
   }
 
   public String getYear()
   {
     return this.Year;
   }
 
   public void setYear(String year)
   {
     this.Year = year;
   }
 
   public String getPeriodNum()
   {
     return this.PeriodNum;
   }
 
   public void setPeriodNum(String periodNum)
   {
     this.PeriodNum = periodNum;
   }
 
   public String getCoverImage()
   {
     return this.CoverImage;
   }
 
   public void setCoverImage(String coverImage)
   {
     this.CoverImage = coverImage;
   }
 
   public String getCoverTemplate()
   {
     return this.CoverTemplate;
   }
 
   public void setCoverTemplate(String coverTemplate)
   {
     this.CoverTemplate = coverTemplate;
   }
 
   public Date getPublishDate()
   {
     return this.PublishDate;
   }
 
   public void setPublishDate(Date publishDate)
   {
     this.PublishDate = publishDate;
   }
 
   public long getStatus()
   {
     if (this.Status == null) return 0L;
     return this.Status.longValue();
   }
 
   public void setStatus(long status)
   {
     this.Status = new Long(status);
   }
 
   public void setStatus(String status)
   {
     if (status == null) {
       this.Status = null;
       return;
     }
     this.Status = new Long(status);
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
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
 * Qualified Name:     com.zving.schema.BZCMagazineIssueSchema
 * JD-Core Version:    0.5.3
 */