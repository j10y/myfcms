 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCWebGatherSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private String Name;
   private String Intro;
   private String Type;
   private String ConfigXML;
   private String ProxyFlag;
   private String ProxyHost;
   private Long ProxyPort;
   private String ProxyUserName;
   private String ProxyPassword;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Name", 1, 2, 50, 0, true, false), 
     new SchemaColumn("Intro", 1, 3, 255, 0, false, false), 
     new SchemaColumn("Type", 1, 4, 2, 0, true, false), 
     new SchemaColumn("ConfigXML", 10, 5, 0, 0, true, false), 
     new SchemaColumn("ProxyFlag", 1, 6, 2, 0, false, false), 
     new SchemaColumn("ProxyHost", 1, 7, 255, 0, false, false), 
     new SchemaColumn("ProxyPort", 7, 8, 0, 0, false, false), 
     new SchemaColumn("ProxyUserName", 1, 9, 50, 0, false, false), 
     new SchemaColumn("ProxyPassword", 1, 10, 100, 0, false, false), 
     new SchemaColumn("Prop1", 1, 11, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 12, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 13, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 14, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 15, 50, 0, true, false), 
     new SchemaColumn("AddTime", 0, 16, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 17, 50, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 18, 0, 0, false, false) };
   public static final String _TableCode = "ZCWebGather";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCWebGather values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCWebGather set ID=?,SiteID=?,Name=?,Intro=?,Type=?,ConfigXML=?,ProxyFlag=?,ProxyHost=?,ProxyPort=?,ProxyUserName=?,ProxyPassword=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCWebGather  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCWebGather  where ID=?";
 
   public ZCWebGatherSchema()
   {
     this.TableCode = "ZCWebGather";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCWebGather values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCWebGather set ID=?,SiteID=?,Name=?,Intro=?,Type=?,ConfigXML=?,ProxyFlag=?,ProxyHost=?,ProxyPort=?,ProxyUserName=?,ProxyPassword=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCWebGather  where ID=?";
     this.FillAllSQL = "select * from ZCWebGather  where ID=?";
     this.HasSetFlag = new boolean[19];
   }
 
   protected Schema newInstance() {
     return new ZCWebGatherSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCWebGatherSet();
   }
 
   public ZCWebGatherSet query() {
     return query(null, -1, -1);
   }
 
   public ZCWebGatherSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCWebGatherSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCWebGatherSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCWebGatherSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { this.Name = ((String)v); return; }
     if (i == 3) { this.Intro = ((String)v); return; }
     if (i == 4) { this.Type = ((String)v); return; }
     if (i == 5) { this.ConfigXML = ((String)v); return; }
     if (i == 6) { this.ProxyFlag = ((String)v); return; }
     if (i == 7) { this.ProxyHost = ((String)v); return; }
     if (i == 8) { if (v == null) this.ProxyPort = null; else this.ProxyPort = new Long(v.toString()); return; }
     if (i == 9) { this.ProxyUserName = ((String)v); return; }
     if (i == 10) { this.ProxyPassword = ((String)v); return; }
     if (i == 11) { this.Prop1 = ((String)v); return; }
     if (i == 12) { this.Prop2 = ((String)v); return; }
     if (i == 13) { this.Prop3 = ((String)v); return; }
     if (i == 14) { this.Prop4 = ((String)v); return; }
     if (i == 15) { this.AddUser = ((String)v); return; }
     if (i == 16) { this.AddTime = ((Date)v); return; }
     if (i == 17) { this.ModifyUser = ((String)v); return; }
     if (i != 18) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.Name;
     if (i == 3) return this.Intro;
     if (i == 4) return this.Type;
     if (i == 5) return this.ConfigXML;
     if (i == 6) return this.ProxyFlag;
     if (i == 7) return this.ProxyHost;
     if (i == 8) return this.ProxyPort;
     if (i == 9) return this.ProxyUserName;
     if (i == 10) return this.ProxyPassword;
     if (i == 11) return this.Prop1;
     if (i == 12) return this.Prop2;
     if (i == 13) return this.Prop3;
     if (i == 14) return this.Prop4;
     if (i == 15) return this.AddUser;
     if (i == 16) return this.AddTime;
     if (i == 17) return this.ModifyUser;
     if (i == 18) return this.ModifyTime;
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
 
   public String getIntro()
   {
     return this.Intro;
   }
 
   public void setIntro(String intro)
   {
     this.Intro = intro;
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
   }
 
   public String getConfigXML()
   {
     return this.ConfigXML;
   }
 
   public void setConfigXML(String configXML)
   {
     this.ConfigXML = configXML;
   }
 
   public String getProxyFlag()
   {
     return this.ProxyFlag;
   }
 
   public void setProxyFlag(String proxyFlag)
   {
     this.ProxyFlag = proxyFlag;
   }
 
   public String getProxyHost()
   {
     return this.ProxyHost;
   }
 
   public void setProxyHost(String proxyHost)
   {
     this.ProxyHost = proxyHost;
   }
 
   public long getProxyPort()
   {
     if (this.ProxyPort == null) return 0L;
     return this.ProxyPort.longValue();
   }
 
   public void setProxyPort(long proxyPort)
   {
     this.ProxyPort = new Long(proxyPort);
   }
 
   public void setProxyPort(String proxyPort)
   {
     if (proxyPort == null) {
       this.ProxyPort = null;
       return;
     }
     this.ProxyPort = new Long(proxyPort);
   }
 
   public String getProxyUserName()
   {
     return this.ProxyUserName;
   }
 
   public void setProxyUserName(String proxyUserName)
   {
     this.ProxyUserName = proxyUserName;
   }
 
   public String getProxyPassword()
   {
     return this.ProxyPassword;
   }
 
   public void setProxyPassword(String proxyPassword)
   {
     this.ProxyPassword = proxyPassword;
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
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCWebGatherSchema
 * JD-Core Version:    0.5.3
 */