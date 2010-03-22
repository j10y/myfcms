 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCCatalogConfigSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private Long CatalogID;
   private String CatalogInnerCode;
   private String CronExpression;
   private String PlanType;
   private Date StartTime;
   private String IsUsing;
   private String HotWordFlag;
   private String AllowStatus;
   private String AfterEditStatus;
   private String Encoding;
   private String ArchiveTime;
   private String AttachDownFlag;
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
     new SchemaColumn("CatalogID", 7, 2, 0, 0, false, false), 
     new SchemaColumn("CatalogInnerCode", 1, 3, 100, 0, false, false), 
     new SchemaColumn("CronExpression", 1, 4, 100, 0, false, false), 
     new SchemaColumn("PlanType", 1, 5, 10, 0, false, false), 
     new SchemaColumn("StartTime", 0, 6, 0, 0, false, false), 
     new SchemaColumn("IsUsing", 1, 7, 2, 0, true, false), 
     new SchemaColumn("HotWordFlag", 1, 8, 2, 0, true, false), 
     new SchemaColumn("AllowStatus", 1, 9, 50, 0, false, false), 
     new SchemaColumn("AfterEditStatus", 1, 10, 50, 0, false, false), 
     new SchemaColumn("Encoding", 1, 11, 20, 0, false, false), 
     new SchemaColumn("ArchiveTime", 1, 12, 10, 0, false, false), 
     new SchemaColumn("AttachDownFlag", 1, 13, 2, 0, false, false), 
     new SchemaColumn("Prop1", 1, 14, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 15, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 16, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 17, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 18, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 19, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 20, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 21, 0, 0, false, false) };
   public static final String _TableCode = "ZCCatalogConfig";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCCatalogConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCCatalogConfig set ID=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,CronExpression=?,PlanType=?,StartTime=?,IsUsing=?,HotWordFlag=?,AllowStatus=?,AfterEditStatus=?,Encoding=?,ArchiveTime=?,AttachDownFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCCatalogConfig  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCCatalogConfig  where ID=?";
 
   public ZCCatalogConfigSchema()
   {
     this.TableCode = "ZCCatalogConfig";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCCatalogConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCCatalogConfig set ID=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,CronExpression=?,PlanType=?,StartTime=?,IsUsing=?,HotWordFlag=?,AllowStatus=?,AfterEditStatus=?,Encoding=?,ArchiveTime=?,AttachDownFlag=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCCatalogConfig  where ID=?";
     this.FillAllSQL = "select * from ZCCatalogConfig  where ID=?";
     this.HasSetFlag = new boolean[22];
   }
 
   protected Schema newInstance() {
     return new ZCCatalogConfigSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCCatalogConfigSet();
   }
 
   public ZCCatalogConfigSet query() {
     return query(null, -1, -1);
   }
 
   public ZCCatalogConfigSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCCatalogConfigSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCCatalogConfigSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCCatalogConfigSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.CatalogID = null; else this.CatalogID = new Long(v.toString()); return; }
     if (i == 3) { this.CatalogInnerCode = ((String)v); return; }
     if (i == 4) { this.CronExpression = ((String)v); return; }
     if (i == 5) { this.PlanType = ((String)v); return; }
     if (i == 6) { this.StartTime = ((Date)v); return; }
     if (i == 7) { this.IsUsing = ((String)v); return; }
     if (i == 8) { this.HotWordFlag = ((String)v); return; }
     if (i == 9) { this.AllowStatus = ((String)v); return; }
     if (i == 10) { this.AfterEditStatus = ((String)v); return; }
     if (i == 11) { this.Encoding = ((String)v); return; }
     if (i == 12) { this.ArchiveTime = ((String)v); return; }
     if (i == 13) { this.AttachDownFlag = ((String)v); return; }
     if (i == 14) { this.Prop1 = ((String)v); return; }
     if (i == 15) { this.Prop2 = ((String)v); return; }
     if (i == 16) { this.Prop3 = ((String)v); return; }
     if (i == 17) { this.Prop4 = ((String)v); return; }
     if (i == 18) { this.AddUser = ((String)v); return; }
     if (i == 19) { this.AddTime = ((Date)v); return; }
     if (i == 20) { this.ModifyUser = ((String)v); return; }
     if (i != 21) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.CatalogID;
     if (i == 3) return this.CatalogInnerCode;
     if (i == 4) return this.CronExpression;
     if (i == 5) return this.PlanType;
     if (i == 6) return this.StartTime;
     if (i == 7) return this.IsUsing;
     if (i == 8) return this.HotWordFlag;
     if (i == 9) return this.AllowStatus;
     if (i == 10) return this.AfterEditStatus;
     if (i == 11) return this.Encoding;
     if (i == 12) return this.ArchiveTime;
     if (i == 13) return this.AttachDownFlag;
     if (i == 14) return this.Prop1;
     if (i == 15) return this.Prop2;
     if (i == 16) return this.Prop3;
     if (i == 17) return this.Prop4;
     if (i == 18) return this.AddUser;
     if (i == 19) return this.AddTime;
     if (i == 20) return this.ModifyUser;
     if (i == 21) return this.ModifyTime;
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
 
   public String getCatalogInnerCode()
   {
     return this.CatalogInnerCode;
   }
 
   public void setCatalogInnerCode(String catalogInnerCode)
   {
     this.CatalogInnerCode = catalogInnerCode;
   }
 
   public String getCronExpression()
   {
     return this.CronExpression;
   }
 
   public void setCronExpression(String cronExpression)
   {
     this.CronExpression = cronExpression;
   }
 
   public String getPlanType()
   {
     return this.PlanType;
   }
 
   public void setPlanType(String planType)
   {
     this.PlanType = planType;
   }
 
   public Date getStartTime()
   {
     return this.StartTime;
   }
 
   public void setStartTime(Date startTime)
   {
     this.StartTime = startTime;
   }
 
   public String getIsUsing()
   {
     return this.IsUsing;
   }
 
   public void setIsUsing(String isUsing)
   {
     this.IsUsing = isUsing;
   }
 
   public String getHotWordFlag()
   {
     return this.HotWordFlag;
   }
 
   public void setHotWordFlag(String hotWordFlag)
   {
     this.HotWordFlag = hotWordFlag;
   }
 
   public String getAllowStatus()
   {
     return this.AllowStatus;
   }
 
   public void setAllowStatus(String allowStatus)
   {
     this.AllowStatus = allowStatus;
   }
 
   public String getAfterEditStatus()
   {
     return this.AfterEditStatus;
   }
 
   public void setAfterEditStatus(String afterEditStatus)
   {
     this.AfterEditStatus = afterEditStatus;
   }
 
   public String getEncoding()
   {
     return this.Encoding;
   }
 
   public void setEncoding(String encoding)
   {
     this.Encoding = encoding;
   }
 
   public String getArchiveTime()
   {
     return this.ArchiveTime;
   }
 
   public void setArchiveTime(String archiveTime)
   {
     this.ArchiveTime = archiveTime;
   }
 
   public String getAttachDownFlag()
   {
     return this.AttachDownFlag;
   }
 
   public void setAttachDownFlag(String attachDownFlag)
   {
     this.AttachDownFlag = attachDownFlag;
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
 * Qualified Name:     com.zving.schema.ZCCatalogConfigSchema
 * JD-Core Version:    0.5.3
 */