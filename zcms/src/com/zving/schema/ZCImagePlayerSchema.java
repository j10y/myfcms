 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCImagePlayerSchema extends Schema
 {
   private Long ID;
   private String Name;
   private String Code;
   private Long SiteID;
   private String DisplayType;
   private String ImageSource;
   private Long Height;
   private Long Width;
   private Long DisplayCount;
   private String Prop1;
   private String Prop2;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("Name", 1, 1, 100, 0, true, false), 
     new SchemaColumn("Code", 1, 2, 100, 0, true, false), 
     new SchemaColumn("SiteID", 7, 3, 0, 0, true, false), 
     new SchemaColumn("DisplayType", 1, 4, 2, 0, true, false), 
     new SchemaColumn("ImageSource", 1, 5, 2, 0, true, false), 
     new SchemaColumn("Height", 7, 6, 0, 0, true, false), 
     new SchemaColumn("Width", 7, 7, 0, 0, true, false), 
     new SchemaColumn("DisplayCount", 7, 8, 0, 0, false, false), 
     new SchemaColumn("Prop1", 1, 9, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 10, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 11, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 12, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 13, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 14, 0, 0, false, false) };
   public static final String _TableCode = "ZCImagePlayer";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCImagePlayer values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCImagePlayer set ID=?,Name=?,Code=?,SiteID=?,DisplayType=?,ImageSource=?,Height=?,Width=?,DisplayCount=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCImagePlayer  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCImagePlayer  where ID=?";
 
   public ZCImagePlayerSchema()
   {
     this.TableCode = "ZCImagePlayer";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCImagePlayer values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCImagePlayer set ID=?,Name=?,Code=?,SiteID=?,DisplayType=?,ImageSource=?,Height=?,Width=?,DisplayCount=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCImagePlayer  where ID=?";
     this.FillAllSQL = "select * from ZCImagePlayer  where ID=?";
     this.HasSetFlag = new boolean[15];
   }
 
   protected Schema newInstance() {
     return new ZCImagePlayerSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCImagePlayerSet();
   }
 
   public ZCImagePlayerSet query() {
     return query(null, -1, -1);
   }
 
   public ZCImagePlayerSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCImagePlayerSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCImagePlayerSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCImagePlayerSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.Name = ((String)v); return; }
     if (i == 2) { this.Code = ((String)v); return; }
     if (i == 3) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 4) { this.DisplayType = ((String)v); return; }
     if (i == 5) { this.ImageSource = ((String)v); return; }
     if (i == 6) { if (v == null) this.Height = null; else this.Height = new Long(v.toString()); return; }
     if (i == 7) { if (v == null) this.Width = null; else this.Width = new Long(v.toString()); return; }
     if (i == 8) { if (v == null) this.DisplayCount = null; else this.DisplayCount = new Long(v.toString()); return; }
     if (i == 9) { this.Prop1 = ((String)v); return; }
     if (i == 10) { this.Prop2 = ((String)v); return; }
     if (i == 11) { this.AddUser = ((String)v); return; }
     if (i == 12) { this.AddTime = ((Date)v); return; }
     if (i == 13) { this.ModifyUser = ((String)v); return; }
     if (i != 14) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.Name;
     if (i == 2) return this.Code;
     if (i == 3) return this.SiteID;
     if (i == 4) return this.DisplayType;
     if (i == 5) return this.ImageSource;
     if (i == 6) return this.Height;
     if (i == 7) return this.Width;
     if (i == 8) return this.DisplayCount;
     if (i == 9) return this.Prop1;
     if (i == 10) return this.Prop2;
     if (i == 11) return this.AddUser;
     if (i == 12) return this.AddTime;
     if (i == 13) return this.ModifyUser;
     if (i == 14) return this.ModifyTime;
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
 
   public String getName()
   {
     return this.Name;
   }
 
   public void setName(String name)
   {
     this.Name = name;
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
 
   public String getDisplayType()
   {
     return this.DisplayType;
   }
 
   public void setDisplayType(String displayType)
   {
     this.DisplayType = displayType;
   }
 
   public String getImageSource()
   {
     return this.ImageSource;
   }
 
   public void setImageSource(String imageSource)
   {
     this.ImageSource = imageSource;
   }
 
   public long getHeight()
   {
     if (this.Height == null) return 0L;
     return this.Height.longValue();
   }
 
   public void setHeight(long height)
   {
     this.Height = new Long(height);
   }
 
   public void setHeight(String height)
   {
     if (height == null) {
       this.Height = null;
       return;
     }
     this.Height = new Long(height);
   }
 
   public long getWidth()
   {
     if (this.Width == null) return 0L;
     return this.Width.longValue();
   }
 
   public void setWidth(long width)
   {
     this.Width = new Long(width);
   }
 
   public void setWidth(String width)
   {
     if (width == null) {
       this.Width = null;
       return;
     }
     this.Width = new Long(width);
   }
 
   public long getDisplayCount()
   {
     if (this.DisplayCount == null) return 0L;
     return this.DisplayCount.longValue();
   }
 
   public void setDisplayCount(long displayCount)
   {
     this.DisplayCount = new Long(displayCount);
   }
 
   public void setDisplayCount(String displayCount)
   {
     if (displayCount == null) {
       this.DisplayCount = null;
       return;
     }
     this.DisplayCount = new Long(displayCount);
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
 * Qualified Name:     com.zving.schema.ZCImagePlayerSchema
 * JD-Core Version:    0.5.3
 */