 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCAdvertisementSchema extends Schema
 {
   private Long ID;
   private Long PositionID;
   private String PositionCode;
   private Long SiteID;
   private String AdName;
   private String AdType;
   private String AdContent;
   private Long OrderFlag;
   private String IsHitCount;
   private Long HitCount;
   private Long StickTime;
   private String IsOpen;
   private Date StartTime;
   private Date EndTime;
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
     new SchemaColumn("PositionID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("PositionCode", 1, 2, 50, 0, true, false), 
     new SchemaColumn("SiteID", 7, 3, 0, 0, true, false), 
     new SchemaColumn("AdName", 1, 4, 100, 0, true, false), 
     new SchemaColumn("AdType", 1, 5, 20, 0, true, false), 
     new SchemaColumn("AdContent", 10, 6, 0, 0, false, false), 
     new SchemaColumn("OrderFlag", 7, 7, 0, 0, false, false), 
     new SchemaColumn("IsHitCount", 1, 8, 2, 0, false, false), 
     new SchemaColumn("HitCount", 7, 9, 0, 0, false, false), 
     new SchemaColumn("StickTime", 7, 10, 0, 0, false, false), 
     new SchemaColumn("IsOpen", 1, 11, 2, 0, false, false), 
     new SchemaColumn("StartTime", 0, 12, 0, 0, false, false), 
     new SchemaColumn("EndTime", 0, 13, 0, 0, false, false), 
     new SchemaColumn("Prop1", 1, 14, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 15, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 16, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 17, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 18, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 19, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 20, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 21, 0, 0, false, false) };
   public static final String _TableCode = "ZCAdvertisement";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCAdvertisement values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCAdvertisement set ID=?,PositionID=?,PositionCode=?,SiteID=?,AdName=?,AdType=?,AdContent=?,OrderFlag=?,IsHitCount=?,HitCount=?,StickTime=?,IsOpen=?,StartTime=?,EndTime=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCAdvertisement  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCAdvertisement  where ID=?";
 
   public ZCAdvertisementSchema()
   {
     this.TableCode = "ZCAdvertisement";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCAdvertisement values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAdvertisement set ID=?,PositionID=?,PositionCode=?,SiteID=?,AdName=?,AdType=?,AdContent=?,OrderFlag=?,IsHitCount=?,HitCount=?,StickTime=?,IsOpen=?,StartTime=?,EndTime=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCAdvertisement  where ID=?";
     this.FillAllSQL = "select * from ZCAdvertisement  where ID=?";
     this.HasSetFlag = new boolean[22];
   }
 
   protected Schema newInstance() {
     return new ZCAdvertisementSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCAdvertisementSet();
   }
 
   public ZCAdvertisementSet query() {
     return query(null, -1, -1);
   }
 
   public ZCAdvertisementSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCAdvertisementSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCAdvertisementSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCAdvertisementSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.PositionID = null; else this.PositionID = new Long(v.toString()); return; }
     if (i == 2) { this.PositionCode = ((String)v); return; }
     if (i == 3) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 4) { this.AdName = ((String)v); return; }
     if (i == 5) { this.AdType = ((String)v); return; }
     if (i == 6) { this.AdContent = ((String)v); return; }
     if (i == 7) { if (v == null) this.OrderFlag = null; else this.OrderFlag = new Long(v.toString()); return; }
     if (i == 8) { this.IsHitCount = ((String)v); return; }
     if (i == 9) { if (v == null) this.HitCount = null; else this.HitCount = new Long(v.toString()); return; }
     if (i == 10) { if (v == null) this.StickTime = null; else this.StickTime = new Long(v.toString()); return; }
     if (i == 11) { this.IsOpen = ((String)v); return; }
     if (i == 12) { this.StartTime = ((Date)v); return; }
     if (i == 13) { this.EndTime = ((Date)v); return; }
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
     if (i == 1) return this.PositionID;
     if (i == 2) return this.PositionCode;
     if (i == 3) return this.SiteID;
     if (i == 4) return this.AdName;
     if (i == 5) return this.AdType;
     if (i == 6) return this.AdContent;
     if (i == 7) return this.OrderFlag;
     if (i == 8) return this.IsHitCount;
     if (i == 9) return this.HitCount;
     if (i == 10) return this.StickTime;
     if (i == 11) return this.IsOpen;
     if (i == 12) return this.StartTime;
     if (i == 13) return this.EndTime;
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
 
   public long getPositionID()
   {
     if (this.PositionID == null) return 0L;
     return this.PositionID.longValue();
   }
 
   public void setPositionID(long positionID)
   {
     this.PositionID = new Long(positionID);
   }
 
   public void setPositionID(String positionID)
   {
     if (positionID == null) {
       this.PositionID = null;
       return;
     }
     this.PositionID = new Long(positionID);
   }
 
   public String getPositionCode()
   {
     return this.PositionCode;
   }
 
   public void setPositionCode(String positionCode)
   {
     this.PositionCode = positionCode;
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
 
   public String getAdName()
   {
     return this.AdName;
   }
 
   public void setAdName(String adName)
   {
     this.AdName = adName;
   }
 
   public String getAdType()
   {
     return this.AdType;
   }
 
   public void setAdType(String adType)
   {
     this.AdType = adType;
   }
 
   public String getAdContent()
   {
     return this.AdContent;
   }
 
   public void setAdContent(String adContent)
   {
     this.AdContent = adContent;
   }
 
   public long getOrderFlag()
   {
     if (this.OrderFlag == null) return 0L;
     return this.OrderFlag.longValue();
   }
 
   public void setOrderFlag(long orderFlag)
   {
     this.OrderFlag = new Long(orderFlag);
   }
 
   public void setOrderFlag(String orderFlag)
   {
     if (orderFlag == null) {
       this.OrderFlag = null;
       return;
     }
     this.OrderFlag = new Long(orderFlag);
   }
 
   public String getIsHitCount()
   {
     return this.IsHitCount;
   }
 
   public void setIsHitCount(String isHitCount)
   {
     this.IsHitCount = isHitCount;
   }
 
   public long getHitCount()
   {
     if (this.HitCount == null) return 0L;
     return this.HitCount.longValue();
   }
 
   public void setHitCount(long hitCount)
   {
     this.HitCount = new Long(hitCount);
   }
 
   public void setHitCount(String hitCount)
   {
     if (hitCount == null) {
       this.HitCount = null;
       return;
     }
     this.HitCount = new Long(hitCount);
   }
 
   public long getStickTime()
   {
     if (this.StickTime == null) return 0L;
     return this.StickTime.longValue();
   }
 
   public void setStickTime(long stickTime)
   {
     this.StickTime = new Long(stickTime);
   }
 
   public void setStickTime(String stickTime)
   {
     if (stickTime == null) {
       this.StickTime = null;
       return;
     }
     this.StickTime = new Long(stickTime);
   }
 
   public String getIsOpen()
   {
     return this.IsOpen;
   }
 
   public void setIsOpen(String isOpen)
   {
     this.IsOpen = isOpen;
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
 * Qualified Name:     com.zving.schema.ZCAdvertisementSchema
 * JD-Core Version:    0.5.3
 */