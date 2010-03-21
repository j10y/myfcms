 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCCommentSchema extends Schema
 {
   private Long ID;
   private Long RelaID;
   private Long CatalogID;
   private Long CatalogType;
   private Long SiteID;
   private String Title;
   private String Content;
   private String AddUser;
   private String AddUserIP;
   private Date AddTime;
   private String VerifyFlag;
   private String VerifyUser;
   private Date VerifyTime;
   private String Prop1;
   private String Prop2;
   private Long SupporterCount;
   private Long AntiCount;
   private String SupportAntiIP;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("RelaID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("CatalogID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("CatalogType", 7, 3, 0, 0, true, false), 
     new SchemaColumn("SiteID", 7, 4, 0, 0, true, false), 
     new SchemaColumn("Title", 1, 5, 100, 0, true, false), 
     new SchemaColumn("Content", 1, 6, 1000, 0, true, false), 
     new SchemaColumn("AddUser", 1, 7, 200, 0, true, false), 
     new SchemaColumn("AddUserIP", 1, 8, 50, 0, true, false), 
     new SchemaColumn("AddTime", 0, 9, 0, 0, true, false), 
     new SchemaColumn("VerifyFlag", 1, 10, 1, 0, false, false), 
     new SchemaColumn("VerifyUser", 1, 11, 200, 0, false, false), 
     new SchemaColumn("VerifyTime", 0, 12, 0, 0, false, false), 
     new SchemaColumn("Prop1", 1, 13, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 14, 50, 0, false, false), 
     new SchemaColumn("SupporterCount", 7, 15, 0, 0, false, false), 
     new SchemaColumn("AntiCount", 7, 16, 0, 0, false, false), 
     new SchemaColumn("SupportAntiIP", 10, 17, 0, 0, false, false) };
   public static final String _TableCode = "ZCComment";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCComment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCComment set ID=?,RelaID=?,CatalogID=?,CatalogType=?,SiteID=?,Title=?,Content=?,AddUser=?,AddUserIP=?,AddTime=?,VerifyFlag=?,VerifyUser=?,VerifyTime=?,Prop1=?,Prop2=?,SupporterCount=?,AntiCount=?,SupportAntiIP=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCComment  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCComment  where ID=?";
 
   public ZCCommentSchema()
   {
     this.TableCode = "ZCComment";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCComment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCComment set ID=?,RelaID=?,CatalogID=?,CatalogType=?,SiteID=?,Title=?,Content=?,AddUser=?,AddUserIP=?,AddTime=?,VerifyFlag=?,VerifyUser=?,VerifyTime=?,Prop1=?,Prop2=?,SupporterCount=?,AntiCount=?,SupportAntiIP=? where ID=?";
     this.DeleteSQL = "delete from ZCComment  where ID=?";
     this.FillAllSQL = "select * from ZCComment  where ID=?";
     this.HasSetFlag = new boolean[18];
   }
 
   protected Schema newInstance() {
     return new ZCCommentSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCCommentSet();
   }
 
   public ZCCommentSet query() {
     return query(null, -1, -1);
   }
 
   public ZCCommentSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCCommentSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCCommentSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCCommentSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.RelaID = null; else this.RelaID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.CatalogID = null; else this.CatalogID = new Long(v.toString()); return; }
     if (i == 3) { if (v == null) this.CatalogType = null; else this.CatalogType = new Long(v.toString()); return; }
     if (i == 4) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 5) { this.Title = ((String)v); return; }
     if (i == 6) { this.Content = ((String)v); return; }
     if (i == 7) { this.AddUser = ((String)v); return; }
     if (i == 8) { this.AddUserIP = ((String)v); return; }
     if (i == 9) { this.AddTime = ((Date)v); return; }
     if (i == 10) { this.VerifyFlag = ((String)v); return; }
     if (i == 11) { this.VerifyUser = ((String)v); return; }
     if (i == 12) { this.VerifyTime = ((Date)v); return; }
     if (i == 13) { this.Prop1 = ((String)v); return; }
     if (i == 14) { this.Prop2 = ((String)v); return; }
     if (i == 15) { if (v == null) this.SupporterCount = null; else this.SupporterCount = new Long(v.toString()); return; }
     if (i == 16) { if (v == null) this.AntiCount = null; else this.AntiCount = new Long(v.toString()); return; }
     if (i != 17) return; this.SupportAntiIP = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.RelaID;
     if (i == 2) return this.CatalogID;
     if (i == 3) return this.CatalogType;
     if (i == 4) return this.SiteID;
     if (i == 5) return this.Title;
     if (i == 6) return this.Content;
     if (i == 7) return this.AddUser;
     if (i == 8) return this.AddUserIP;
     if (i == 9) return this.AddTime;
     if (i == 10) return this.VerifyFlag;
     if (i == 11) return this.VerifyUser;
     if (i == 12) return this.VerifyTime;
     if (i == 13) return this.Prop1;
     if (i == 14) return this.Prop2;
     if (i == 15) return this.SupporterCount;
     if (i == 16) return this.AntiCount;
     if (i == 17) return this.SupportAntiIP;
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
 
   public long getRelaID()
   {
     if (this.RelaID == null) return 0L;
     return this.RelaID.longValue();
   }
 
   public void setRelaID(long relaID)
   {
     this.RelaID = new Long(relaID);
   }
 
   public void setRelaID(String relaID)
   {
     if (relaID == null) {
       this.RelaID = null;
       return;
     }
     this.RelaID = new Long(relaID);
   }
 
   public long getCatalogID()
   {
     if (this.CatalogID == null) return 0L;
     return this.CatalogID.longValue();
   }
 
   public void setCatalogID(long catalogID)
   {
     this.CatalogID = new Long(catalogID);
   }
 
   public void setCatalogID(String catalogID)
   {
     if (catalogID == null) {
       this.CatalogID = null;
       return;
     }
     this.CatalogID = new Long(catalogID);
   }
 
   public long getCatalogType()
   {
     if (this.CatalogType == null) return 0L;
     return this.CatalogType.longValue();
   }
 
   public void setCatalogType(long catalogType)
   {
     this.CatalogType = new Long(catalogType);
   }
 
   public void setCatalogType(String catalogType)
   {
     if (catalogType == null) {
       this.CatalogType = null;
       return;
     }
     this.CatalogType = new Long(catalogType);
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
 
   public String getContent()
   {
     return this.Content;
   }
 
   public void setContent(String content)
   {
     this.Content = content;
   }
 
   public String getAddUser()
   {
     return this.AddUser;
   }
 
   public void setAddUser(String addUser)
   {
     this.AddUser = addUser;
   }
 
   public String getAddUserIP()
   {
     return this.AddUserIP;
   }
 
   public void setAddUserIP(String addUserIP)
   {
     this.AddUserIP = addUserIP;
   }
 
   public Date getAddTime()
   {
     return this.AddTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.AddTime = addTime;
   }
 
   public String getVerifyFlag()
   {
     return this.VerifyFlag;
   }
 
   public void setVerifyFlag(String verifyFlag)
   {
     this.VerifyFlag = verifyFlag;
   }
 
   public String getVerifyUser()
   {
     return this.VerifyUser;
   }
 
   public void setVerifyUser(String verifyUser)
   {
     this.VerifyUser = verifyUser;
   }
 
   public Date getVerifyTime()
   {
     return this.VerifyTime;
   }
 
   public void setVerifyTime(Date verifyTime)
   {
     this.VerifyTime = verifyTime;
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
 
   public long getSupporterCount()
   {
     if (this.SupporterCount == null) return 0L;
     return this.SupporterCount.longValue();
   }
 
   public void setSupporterCount(long supporterCount)
   {
     this.SupporterCount = new Long(supporterCount);
   }
 
   public void setSupporterCount(String supporterCount)
   {
     if (supporterCount == null) {
       this.SupporterCount = null;
       return;
     }
     this.SupporterCount = new Long(supporterCount);
   }
 
   public long getAntiCount()
   {
     if (this.AntiCount == null) return 0L;
     return this.AntiCount.longValue();
   }
 
   public void setAntiCount(long antiCount)
   {
     this.AntiCount = new Long(antiCount);
   }
 
   public void setAntiCount(String antiCount)
   {
     if (antiCount == null) {
       this.AntiCount = null;
       return;
     }
     this.AntiCount = new Long(antiCount);
   }
 
   public String getSupportAntiIP()
   {
     return this.SupportAntiIP;
   }
 
   public void setSupportAntiIP(String supportAntiIP)
   {
     this.SupportAntiIP = supportAntiIP;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCCommentSchema
 * JD-Core Version:    0.5.3
 */