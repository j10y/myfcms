 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCAdVisitLogSchema extends Schema
 {
   private Long ID;
   private Long AdID;
   private Date ServerTime;
   private Date ClientTime;
   private String IP;
   private String Address;
   private String OS;
   private String Browser;
   private String Screen;
   private String Depth;
   private String Referer;
   private String CurrentPage;
   private String Visitor;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("AdID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("ServerTime", 0, 2, 0, 0, false, false), 
     new SchemaColumn("ClientTime", 0, 3, 0, 0, false, false), 
     new SchemaColumn("IP", 1, 4, 50, 0, false, false), 
     new SchemaColumn("Address", 1, 5, 200, 0, false, false), 
     new SchemaColumn("OS", 1, 6, 50, 0, false, false), 
     new SchemaColumn("Browser", 1, 7, 50, 0, false, false), 
     new SchemaColumn("Screen", 1, 8, 50, 0, false, false), 
     new SchemaColumn("Depth", 1, 9, 50, 0, false, false), 
     new SchemaColumn("Referer", 1, 10, 250, 0, false, false), 
     new SchemaColumn("CurrentPage", 1, 11, 250, 0, false, false), 
     new SchemaColumn("Visitor", 1, 12, 50, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 13, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 14, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 15, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 16, 50, 0, false, false) };
   public static final String _TableCode = "BZCAdVisitLog";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCAdVisitLog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCAdVisitLog set ID=?,AdID=?,ServerTime=?,ClientTime=?,IP=?,Address=?,OS=?,Browser=?,Screen=?,Depth=?,Referer=?,CurrentPage=?,Visitor=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCAdVisitLog  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCAdVisitLog  where ID=? and BackupNo=?";
 
   public BZCAdVisitLogSchema()
   {
     this.TableCode = "BZCAdVisitLog";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCAdVisitLog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAdVisitLog set ID=?,AdID=?,ServerTime=?,ClientTime=?,IP=?,Address=?,OS=?,Browser=?,Screen=?,Depth=?,Referer=?,CurrentPage=?,Visitor=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAdVisitLog  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAdVisitLog  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[17];
   }
 
   protected Schema newInstance() {
     return new BZCAdVisitLogSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCAdVisitLogSet();
   }
 
   public BZCAdVisitLogSet query() {
     return query(null, -1, -1);
   }
 
   public BZCAdVisitLogSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCAdVisitLogSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCAdVisitLogSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCAdVisitLogSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.AdID = null; else this.AdID = new Long(v.toString()); return; }
     if (i == 2) { this.ServerTime = ((Date)v); return; }
     if (i == 3) { this.ClientTime = ((Date)v); return; }
     if (i == 4) { this.IP = ((String)v); return; }
     if (i == 5) { this.Address = ((String)v); return; }
     if (i == 6) { this.OS = ((String)v); return; }
     if (i == 7) { this.Browser = ((String)v); return; }
     if (i == 8) { this.Screen = ((String)v); return; }
     if (i == 9) { this.Depth = ((String)v); return; }
     if (i == 10) { this.Referer = ((String)v); return; }
     if (i == 11) { this.CurrentPage = ((String)v); return; }
     if (i == 12) { this.Visitor = ((String)v); return; }
     if (i == 13) { this.BackupNo = ((String)v); return; }
     if (i == 14) { this.BackupOperator = ((String)v); return; }
     if (i == 15) { this.BackupTime = ((Date)v); return; }
     if (i != 16) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.AdID;
     if (i == 2) return this.ServerTime;
     if (i == 3) return this.ClientTime;
     if (i == 4) return this.IP;
     if (i == 5) return this.Address;
     if (i == 6) return this.OS;
     if (i == 7) return this.Browser;
     if (i == 8) return this.Screen;
     if (i == 9) return this.Depth;
     if (i == 10) return this.Referer;
     if (i == 11) return this.CurrentPage;
     if (i == 12) return this.Visitor;
     if (i == 13) return this.BackupNo;
     if (i == 14) return this.BackupOperator;
     if (i == 15) return this.BackupTime;
     if (i == 16) return this.BackupMemo;
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
 
   public long getAdID()
   {
     if (this.AdID == null) return 0L;
     return this.AdID.longValue();
   }
 
   public void setAdID(long adID)
   {
     this.AdID = new Long(adID);
   }
 
   public void setAdID(String adID)
   {
     if (adID == null) {
       this.AdID = null;
       return;
     }
     this.AdID = new Long(adID);
   }
 
   public Date getServerTime()
   {
     return this.ServerTime;
   }
 
   public void setServerTime(Date serverTime)
   {
     this.ServerTime = serverTime;
   }
 
   public Date getClientTime()
   {
     return this.ClientTime;
   }
 
   public void setClientTime(Date clientTime)
   {
     this.ClientTime = clientTime;
   }
 
   public String getIP()
   {
     return this.IP;
   }
 
   public void setIP(String iP)
   {
     this.IP = iP;
   }
 
   public String getAddress()
   {
     return this.Address;
   }
 
   public void setAddress(String address)
   {
     this.Address = address;
   }
 
   public String getOS()
   {
     return this.OS;
   }
 
   public void setOS(String oS)
   {
     this.OS = oS;
   }
 
   public String getBrowser()
   {
     return this.Browser;
   }
 
   public void setBrowser(String browser)
   {
     this.Browser = browser;
   }
 
   public String getScreen()
   {
     return this.Screen;
   }
 
   public void setScreen(String screen)
   {
     this.Screen = screen;
   }
 
   public String getDepth()
   {
     return this.Depth;
   }
 
   public void setDepth(String depth)
   {
     this.Depth = depth;
   }
 
   public String getReferer()
   {
     return this.Referer;
   }
 
   public void setReferer(String referer)
   {
     this.Referer = referer;
   }
 
   public String getCurrentPage()
   {
     return this.CurrentPage;
   }
 
   public void setCurrentPage(String currentPage)
   {
     this.CurrentPage = currentPage;
   }
 
   public String getVisitor()
   {
     return this.Visitor;
   }
 
   public void setVisitor(String visitor)
   {
     this.Visitor = visitor;
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
 * Qualified Name:     com.zving.schema.BZCAdVisitLogSchema
 * JD-Core Version:    0.5.3
 */