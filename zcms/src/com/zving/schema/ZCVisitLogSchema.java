 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCVisitLogSchema extends Schema
 {
   private String ID;
   private Long SiteID;
   private String URL;
   private String IP;
   private String OS;
   private String Browser;
   private String Screen;
   private String Referer;
   private String ColorDepth;
   private String CookieEnabled;
   private String FlashEnabled;
   private String FlashVersion;
   private String JavaEnabled;
   private String Language;
   private String District;
   private Date AddTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 1, 0, 50, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, false, false), 
     new SchemaColumn("URL", 1, 2, 500, 0, true, false), 
     new SchemaColumn("IP", 1, 3, 30, 0, false, false), 
     new SchemaColumn("OS", 1, 4, 50, 0, false, false), 
     new SchemaColumn("Browser", 1, 5, 50, 0, false, false), 
     new SchemaColumn("Screen", 1, 6, 10, 0, false, false), 
     new SchemaColumn("Referer", 1, 7, 500, 0, false, false), 
     new SchemaColumn("ColorDepth", 1, 8, 10, 0, false, false), 
     new SchemaColumn("CookieEnabled", 1, 9, 10, 0, false, false), 
     new SchemaColumn("FlashEnabled", 1, 10, 10, 0, false, false), 
     new SchemaColumn("FlashVersion", 1, 11, 30, 0, false, false), 
     new SchemaColumn("JavaEnabled", 1, 12, 10, 0, false, false), 
     new SchemaColumn("Language", 1, 13, 30, 0, false, false), 
     new SchemaColumn("District", 1, 14, 50, 0, false, false), 
     new SchemaColumn("AddTime", 0, 15, 0, 0, true, true) };
   public static final String _TableCode = "ZCVisitLog";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCVisitLog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCVisitLog set ID=?,SiteID=?,URL=?,IP=?,OS=?,Browser=?,Screen=?,Referer=?,ColorDepth=?,CookieEnabled=?,FlashEnabled=?,FlashVersion=?,JavaEnabled=?,Language=?,District=?,AddTime=? where ID=? and AddTime=?";
   protected static final String _DeleteSQL = "delete from ZCVisitLog  where ID=? and AddTime=?";
   protected static final String _FillAllSQL = "select * from ZCVisitLog  where ID=? and AddTime=?";
 
   public ZCVisitLogSchema()
   {
     this.TableCode = "ZCVisitLog";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCVisitLog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCVisitLog set ID=?,SiteID=?,URL=?,IP=?,OS=?,Browser=?,Screen=?,Referer=?,ColorDepth=?,CookieEnabled=?,FlashEnabled=?,FlashVersion=?,JavaEnabled=?,Language=?,District=?,AddTime=? where ID=? and AddTime=?";
     this.DeleteSQL = "delete from ZCVisitLog  where ID=? and AddTime=?";
     this.FillAllSQL = "select * from ZCVisitLog  where ID=? and AddTime=?";
     this.HasSetFlag = new boolean[16];
   }
 
   protected Schema newInstance() {
     return new ZCVisitLogSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCVisitLogSet();
   }
 
   public ZCVisitLogSet query() {
     return query(null, -1, -1);
   }
 
   public ZCVisitLogSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCVisitLogSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCVisitLogSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCVisitLogSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.ID = ((String)v); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { this.URL = ((String)v); return; }
     if (i == 3) { this.IP = ((String)v); return; }
     if (i == 4) { this.OS = ((String)v); return; }
     if (i == 5) { this.Browser = ((String)v); return; }
     if (i == 6) { this.Screen = ((String)v); return; }
     if (i == 7) { this.Referer = ((String)v); return; }
     if (i == 8) { this.ColorDepth = ((String)v); return; }
     if (i == 9) { this.CookieEnabled = ((String)v); return; }
     if (i == 10) { this.FlashEnabled = ((String)v); return; }
     if (i == 11) { this.FlashVersion = ((String)v); return; }
     if (i == 12) { this.JavaEnabled = ((String)v); return; }
     if (i == 13) { this.Language = ((String)v); return; }
     if (i == 14) { this.District = ((String)v); return; }
     if (i != 15) return; this.AddTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.URL;
     if (i == 3) return this.IP;
     if (i == 4) return this.OS;
     if (i == 5) return this.Browser;
     if (i == 6) return this.Screen;
     if (i == 7) return this.Referer;
     if (i == 8) return this.ColorDepth;
     if (i == 9) return this.CookieEnabled;
     if (i == 10) return this.FlashEnabled;
     if (i == 11) return this.FlashVersion;
     if (i == 12) return this.JavaEnabled;
     if (i == 13) return this.Language;
     if (i == 14) return this.District;
     if (i == 15) return this.AddTime;
     return null;
   }
 
   public String getID()
   {
     return this.ID;
   }
 
   public void setID(String iD)
   {
     this.ID = iD;
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
 
   public String getURL()
   {
     return this.URL;
   }
 
   public void setURL(String uRL)
   {
     this.URL = uRL;
   }
 
   public String getIP()
   {
     return this.IP;
   }
 
   public void setIP(String iP)
   {
     this.IP = iP;
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
 
   public String getReferer()
   {
     return this.Referer;
   }
 
   public void setReferer(String referer)
   {
     this.Referer = referer;
   }
 
   public String getColorDepth()
   {
     return this.ColorDepth;
   }
 
   public void setColorDepth(String colorDepth)
   {
     this.ColorDepth = colorDepth;
   }
 
   public String getCookieEnabled()
   {
     return this.CookieEnabled;
   }
 
   public void setCookieEnabled(String cookieEnabled)
   {
     this.CookieEnabled = cookieEnabled;
   }
 
   public String getFlashEnabled()
   {
     return this.FlashEnabled;
   }
 
   public void setFlashEnabled(String flashEnabled)
   {
     this.FlashEnabled = flashEnabled;
   }
 
   public String getFlashVersion()
   {
     return this.FlashVersion;
   }
 
   public void setFlashVersion(String flashVersion)
   {
     this.FlashVersion = flashVersion;
   }
 
   public String getJavaEnabled()
   {
     return this.JavaEnabled;
   }
 
   public void setJavaEnabled(String javaEnabled)
   {
     this.JavaEnabled = javaEnabled;
   }
 
   public String getLanguage()
   {
     return this.Language;
   }
 
   public void setLanguage(String language)
   {
     this.Language = language;
   }
 
   public String getDistrict()
   {
     return this.District;
   }
 
   public void setDistrict(String district)
   {
     this.District = district;
   }
 
   public Date getAddTime()
   {
     return this.AddTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.AddTime = addTime;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCVisitLogSchema
 * JD-Core Version:    0.5.3
 */