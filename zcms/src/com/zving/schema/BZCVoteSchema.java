 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCVoteSchema extends Schema
 {
   private Long ID;
   private String Code;
   private Long SiteID;
   private String Title;
   private Long Total;
   private Date StartTime;
   private Date EndTime;
   private String IPLimit;
   private String VerifyFlag;
   private Integer Width;
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
     new SchemaColumn("Code", 1, 1, 100, 0, false, false), 
     new SchemaColumn("SiteID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Title", 1, 3, 100, 0, true, false), 
     new SchemaColumn("Total", 7, 4, 0, 0, true, false), 
     new SchemaColumn("StartTime", 0, 5, 0, 0, true, false), 
     new SchemaColumn("EndTime", 0, 6, 0, 0, false, false), 
     new SchemaColumn("IPLimit", 1, 7, 1, 0, true, false), 
     new SchemaColumn("VerifyFlag", 1, 8, 1, 0, true, false), 
     new SchemaColumn("Width", 8, 9, 0, 0, false, false), 
     new SchemaColumn("Prop1", 1, 10, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 11, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 12, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 13, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 14, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 15, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 16, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 17, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 18, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 19, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 20, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 21, 50, 0, false, false) };
   public static final String _TableCode = "BZCVote";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCVote values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCVote set ID=?,Code=?,SiteID=?,Title=?,Total=?,StartTime=?,EndTime=?,IPLimit=?,VerifyFlag=?,Width=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCVote  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCVote  where ID=? and BackupNo=?";
 
   public BZCVoteSchema()
   {
     this.TableCode = "BZCVote";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCVote values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCVote set ID=?,Code=?,SiteID=?,Title=?,Total=?,StartTime=?,EndTime=?,IPLimit=?,VerifyFlag=?,Width=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCVote  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCVote  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[22];
   }
 
   protected Schema newInstance() {
     return new BZCVoteSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCVoteSet();
   }
 
   public BZCVoteSet query() {
     return query(null, -1, -1);
   }
 
   public BZCVoteSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCVoteSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCVoteSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCVoteSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.Code = ((String)v); return; }
     if (i == 2) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 3) { this.Title = ((String)v); return; }
     if (i == 4) { if (v == null) this.Total = null; else this.Total = new Long(v.toString()); return; }
     if (i == 5) { this.StartTime = ((Date)v); return; }
     if (i == 6) { this.EndTime = ((Date)v); return; }
     if (i == 7) { this.IPLimit = ((String)v); return; }
     if (i == 8) { this.VerifyFlag = ((String)v); return; }
     if (i == 9) { if (v == null) this.Width = null; else this.Width = new Integer(v.toString()); return; }
     if (i == 10) { this.Prop1 = ((String)v); return; }
     if (i == 11) { this.Prop2 = ((String)v); return; }
     if (i == 12) { this.Prop3 = ((String)v); return; }
     if (i == 13) { this.Prop4 = ((String)v); return; }
     if (i == 14) { this.AddUser = ((String)v); return; }
     if (i == 15) { this.AddTime = ((Date)v); return; }
     if (i == 16) { this.ModifyUser = ((String)v); return; }
     if (i == 17) { this.ModifyTime = ((Date)v); return; }
     if (i == 18) { this.BackupNo = ((String)v); return; }
     if (i == 19) { this.BackupOperator = ((String)v); return; }
     if (i == 20) { this.BackupTime = ((Date)v); return; }
     if (i != 21) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.Code;
     if (i == 2) return this.SiteID;
     if (i == 3) return this.Title;
     if (i == 4) return this.Total;
     if (i == 5) return this.StartTime;
     if (i == 6) return this.EndTime;
     if (i == 7) return this.IPLimit;
     if (i == 8) return this.VerifyFlag;
     if (i == 9) return this.Width;
     if (i == 10) return this.Prop1;
     if (i == 11) return this.Prop2;
     if (i == 12) return this.Prop3;
     if (i == 13) return this.Prop4;
     if (i == 14) return this.AddUser;
     if (i == 15) return this.AddTime;
     if (i == 16) return this.ModifyUser;
     if (i == 17) return this.ModifyTime;
     if (i == 18) return this.BackupNo;
     if (i == 19) return this.BackupOperator;
     if (i == 20) return this.BackupTime;
     if (i == 21) return this.BackupMemo;
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
 
   public String getCode()
   {
     return this.Code;
   }
 
   public void setCode(String code)
   {
     this.Code = code;
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
 
   public String getTitle()
   {
     return this.Title;
   }
 
   public void setTitle(String title)
   {
     this.Title = title;
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
 
   public Date getStartTime()
   {
     return this.StartTime;
   }
 
   public void setStartTime(Date startTime)
   {
     this.StartTime = startTime;
   }
 
   public Date getEndTime()
   {
     return this.EndTime;
   }
 
   public void setEndTime(Date endTime)
   {
     this.EndTime = endTime;
   }
 
   public String getIPLimit()
   {
     return this.IPLimit;
   }
 
   public void setIPLimit(String iPLimit)
   {
     this.IPLimit = iPLimit;
   }
 
   public String getVerifyFlag()
   {
     return this.VerifyFlag;
   }
 
   public void setVerifyFlag(String verifyFlag)
   {
     this.VerifyFlag = verifyFlag;
   }
 
   public int getWidth()
   {
     if (this.Width == null) return 0;
     return this.Width.intValue();
   }
 
   public void setWidth(int width)
   {
     this.Width = new Integer(width);
   }
 
   public void setWidth(String width)
   {
     if (width == null) {
       this.Width = null;
       return;
     }
     this.Width = new Integer(width);
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
 * Qualified Name:     com.zving.schema.BZCVoteSchema
 * JD-Core Version:    0.5.3
 */