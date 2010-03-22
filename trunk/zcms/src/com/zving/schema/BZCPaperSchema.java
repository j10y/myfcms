 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCPaperSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private String Name;
   private String Alias;
   private String CoverImage;
   private String Period;
   private Long OpenFlag;
   private Long Total;
   private String CurrentYear;
   private String CurrentPeriodNum;
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
     new SchemaColumn("SiteID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Name", 1, 2, 100, 0, true, false), 
     new SchemaColumn("Alias", 1, 3, 100, 0, true, false), 
     new SchemaColumn("CoverImage", 1, 4, 100, 0, false, false), 
     new SchemaColumn("Period", 1, 5, 10, 0, false, false), 
     new SchemaColumn("OpenFlag", 7, 6, 0, 0, false, false), 
     new SchemaColumn("Total", 7, 7, 0, 0, false, false), 
     new SchemaColumn("CurrentYear", 1, 8, 50, 0, false, false), 
     new SchemaColumn("CurrentPeriodNum", 1, 9, 50, 0, false, false), 
     new SchemaColumn("Memo", 1, 10, 1000, 0, false, false), 
     new SchemaColumn("Prop1", 1, 11, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 12, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 13, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 14, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 15, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 16, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 17, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 18, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 19, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 20, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 21, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 22, 50, 0, false, false) };
   public static final String _TableCode = "BZCPaper";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCPaper values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCPaper set ID=?,SiteID=?,Name=?,Alias=?,CoverImage=?,Period=?,OpenFlag=?,Total=?,CurrentYear=?,CurrentPeriodNum=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCPaper  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCPaper  where ID=? and BackupNo=?";
 
   public BZCPaperSchema()
   {
     this.TableCode = "BZCPaper";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCPaper values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCPaper set ID=?,SiteID=?,Name=?,Alias=?,CoverImage=?,Period=?,OpenFlag=?,Total=?,CurrentYear=?,CurrentPeriodNum=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCPaper  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCPaper  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[23];
   }
 
   protected Schema newInstance() {
     return new BZCPaperSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCPaperSet();
   }
 
   public BZCPaperSet query() {
     return query(null, -1, -1);
   }
 
   public BZCPaperSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCPaperSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCPaperSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCPaperSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { this.Name = ((String)v); return; }
     if (i == 3) { this.Alias = ((String)v); return; }
     if (i == 4) { this.CoverImage = ((String)v); return; }
     if (i == 5) { this.Period = ((String)v); return; }
     if (i == 6) { if (v == null) this.OpenFlag = null; else this.OpenFlag = new Long(v.toString()); return; }
     if (i == 7) { if (v == null) this.Total = null; else this.Total = new Long(v.toString()); return; }
     if (i == 8) { this.CurrentYear = ((String)v); return; }
     if (i == 9) { this.CurrentPeriodNum = ((String)v); return; }
     if (i == 10) { this.Memo = ((String)v); return; }
     if (i == 11) { this.Prop1 = ((String)v); return; }
     if (i == 12) { this.Prop2 = ((String)v); return; }
     if (i == 13) { this.Prop3 = ((String)v); return; }
     if (i == 14) { this.Prop4 = ((String)v); return; }
     if (i == 15) { this.AddUser = ((String)v); return; }
     if (i == 16) { this.AddTime = ((Date)v); return; }
     if (i == 17) { this.ModifyUser = ((String)v); return; }
     if (i == 18) { this.ModifyTime = ((Date)v); return; }
     if (i == 19) { this.BackupNo = ((String)v); return; }
     if (i == 20) { this.BackupOperator = ((String)v); return; }
     if (i == 21) { this.BackupTime = ((Date)v); return; }
     if (i != 22) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.Name;
     if (i == 3) return this.Alias;
     if (i == 4) return this.CoverImage;
     if (i == 5) return this.Period;
     if (i == 6) return this.OpenFlag;
     if (i == 7) return this.Total;
     if (i == 8) return this.CurrentYear;
     if (i == 9) return this.CurrentPeriodNum;
     if (i == 10) return this.Memo;
     if (i == 11) return this.Prop1;
     if (i == 12) return this.Prop2;
     if (i == 13) return this.Prop3;
     if (i == 14) return this.Prop4;
     if (i == 15) return this.AddUser;
     if (i == 16) return this.AddTime;
     if (i == 17) return this.ModifyUser;
     if (i == 18) return this.ModifyTime;
     if (i == 19) return this.BackupNo;
     if (i == 20) return this.BackupOperator;
     if (i == 21) return this.BackupTime;
     if (i == 22) return this.BackupMemo;
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
 
   public String getAlias()
   {
     return this.Alias;
   }
 
   public void setAlias(String alias)
   {
     this.Alias = alias;
   }
 
   public String getCoverImage()
   {
     return this.CoverImage;
   }
 
   public void setCoverImage(String coverImage)
   {
     this.CoverImage = coverImage;
   }
 
   public String getPeriod()
   {
     return this.Period;
   }
 
   public void setPeriod(String period)
   {
     this.Period = period;
   }
 
   public long getOpenFlag()
   {
     if (this.OpenFlag == null) return 0L;
     return this.OpenFlag.longValue();
   }
 
   public void setOpenFlag(long openFlag)
   {
     this.OpenFlag = new Long(openFlag);
   }
 
   public void setOpenFlag(String openFlag)
   {
     if (openFlag == null) {
       this.OpenFlag = null;
       return;
     }
     this.OpenFlag = new Long(openFlag);
   }
 
   public long getTotal()
   {
     if (this.Total == null) return 0L;
     return this.Total.longValue();
   }
 
   public void setTotal(long total)
   {
     this.Total = new Long(total);
   }
 
   public void setTotal(String total)
   {
     if (total == null) {
       this.Total = null;
       return;
     }
     this.Total = new Long(total);
   }
 
   public String getCurrentYear()
   {
     return this.CurrentYear;
   }
 
   public void setCurrentYear(String currentYear)
   {
     this.CurrentYear = currentYear;
   }
 
   public String getCurrentPeriodNum()
   {
     return this.CurrentPeriodNum;
   }
 
   public void setCurrentPeriodNum(String currentPeriodNum)
   {
     this.CurrentPeriodNum = currentPeriodNum;
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
 * Qualified Name:     com.zving.schema.BZCPaperSchema
 * JD-Core Version:    0.5.3
 */