 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCImageSchema extends Schema
 {
   private Long ID;
   private String Name;
   private String OldName;
   private String Tag;
   private Long SiteID;
   private Long CatalogID;
   private String CatalogInnerCode;
   private String BranchInnerCode;
   private String Path;
   private String FileName;
   private String SrcFileName;
   private String Suffix;
   private Long Width;
   private Long Height;
   private Long Count;
   private String Info;
   private String IsOriginal;
   private String FileSize;
   private String System;
   private String LinkURL;
   private String LinkText;
   private Date ProduceTime;
   private String Author;
   private Date PublishDate;
   private Long Integral;
   private Long OrderFlag;
   private Long HitCount;
   private Long StickTime;
   private String SourceURL;
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
     new SchemaColumn("Name", 1, 1, 100, 0, true, false), 
     new SchemaColumn("OldName", 1, 2, 100, 0, true, false), 
     new SchemaColumn("Tag", 1, 3, 100, 0, false, false), 
     new SchemaColumn("SiteID", 7, 4, 0, 0, true, false), 
     new SchemaColumn("CatalogID", 7, 5, 0, 0, true, false), 
     new SchemaColumn("CatalogInnerCode", 1, 6, 100, 0, true, false), 
     new SchemaColumn("BranchInnerCode", 1, 7, 50, 0, false, false), 
     new SchemaColumn("Path", 1, 8, 100, 0, true, false), 
     new SchemaColumn("FileName", 1, 9, 100, 0, true, false), 
     new SchemaColumn("SrcFileName", 1, 10, 100, 0, true, false), 
     new SchemaColumn("Suffix", 1, 11, 10, 0, true, false), 
     new SchemaColumn("Width", 7, 12, 0, 0, true, false), 
     new SchemaColumn("Height", 7, 13, 0, 0, true, false), 
     new SchemaColumn("Count", 7, 14, 0, 0, true, false), 
     new SchemaColumn("Info", 1, 15, 500, 0, false, false), 
     new SchemaColumn("IsOriginal", 1, 16, 1, 0, false, false), 
     new SchemaColumn("FileSize", 1, 17, 20, 0, false, false), 
     new SchemaColumn("System", 1, 18, 20, 0, false, false), 
     new SchemaColumn("LinkURL", 1, 19, 100, 0, false, false), 
     new SchemaColumn("LinkText", 1, 20, 100, 0, false, false), 
     new SchemaColumn("ProduceTime", 0, 21, 0, 0, false, false), 
     new SchemaColumn("Author", 1, 22, 50, 0, false, false), 
     new SchemaColumn("PublishDate", 0, 23, 0, 0, false, false), 
     new SchemaColumn("Integral", 7, 24, 0, 0, false, false), 
     new SchemaColumn("OrderFlag", 7, 25, 0, 0, true, false), 
     new SchemaColumn("HitCount", 7, 26, 0, 0, true, false), 
     new SchemaColumn("StickTime", 7, 27, 0, 0, true, false), 
     new SchemaColumn("SourceURL", 1, 28, 500, 0, false, false), 
     new SchemaColumn("Prop1", 1, 29, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 30, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 31, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 32, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 33, 50, 0, true, false), 
     new SchemaColumn("AddTime", 0, 34, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 35, 50, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 36, 0, 0, false, false) };
   public static final String _TableCode = "ZCImage";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCImage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCImage set ID=?,Name=?,OldName=?,Tag=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Path=?,FileName=?,SrcFileName=?,Suffix=?,Width=?,Height=?,Count=?,Info=?,IsOriginal=?,FileSize=?,System=?,LinkURL=?,LinkText=?,ProduceTime=?,Author=?,PublishDate=?,Integral=?,OrderFlag=?,HitCount=?,StickTime=?,SourceURL=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCImage  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCImage  where ID=?";
 
   public ZCImageSchema()
   {
     this.TableCode = "ZCImage";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCImage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCImage set ID=?,Name=?,OldName=?,Tag=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Path=?,FileName=?,SrcFileName=?,Suffix=?,Width=?,Height=?,Count=?,Info=?,IsOriginal=?,FileSize=?,System=?,LinkURL=?,LinkText=?,ProduceTime=?,Author=?,PublishDate=?,Integral=?,OrderFlag=?,HitCount=?,StickTime=?,SourceURL=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCImage  where ID=?";
     this.FillAllSQL = "select * from ZCImage  where ID=?";
     this.HasSetFlag = new boolean[37];
   }
 
   protected Schema newInstance() {
     return new ZCImageSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCImageSet();
   }
 
   public ZCImageSet query() {
     return query(null, -1, -1);
   }
 
   public ZCImageSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCImageSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCImageSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCImageSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.Name = ((String)v); return; }
     if (i == 2) { this.OldName = ((String)v); return; }
     if (i == 3) { this.Tag = ((String)v); return; }
     if (i == 4) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 5) { if (v == null) this.CatalogID = null; else this.CatalogID = new Long(v.toString()); return; }
     if (i == 6) { this.CatalogInnerCode = ((String)v); return; }
     if (i == 7) { this.BranchInnerCode = ((String)v); return; }
     if (i == 8) { this.Path = ((String)v); return; }
     if (i == 9) { this.FileName = ((String)v); return; }
     if (i == 10) { this.SrcFileName = ((String)v); return; }
     if (i == 11) { this.Suffix = ((String)v); return; }
     if (i == 12) { if (v == null) this.Width = null; else this.Width = new Long(v.toString()); return; }
     if (i == 13) { if (v == null) this.Height = null; else this.Height = new Long(v.toString()); return; }
     if (i == 14) { if (v == null) this.Count = null; else this.Count = new Long(v.toString()); return; }
     if (i == 15) { this.Info = ((String)v); return; }
     if (i == 16) { this.IsOriginal = ((String)v); return; }
     if (i == 17) { this.FileSize = ((String)v); return; }
     if (i == 18) { this.System = ((String)v); return; }
     if (i == 19) { this.LinkURL = ((String)v); return; }
     if (i == 20) { this.LinkText = ((String)v); return; }
     if (i == 21) { this.ProduceTime = ((Date)v); return; }
     if (i == 22) { this.Author = ((String)v); return; }
     if (i == 23) { this.PublishDate = ((Date)v); return; }
     if (i == 24) { if (v == null) this.Integral = null; else this.Integral = new Long(v.toString()); return; }
     if (i == 25) { if (v == null) this.OrderFlag = null; else this.OrderFlag = new Long(v.toString()); return; }
     if (i == 26) { if (v == null) this.HitCount = null; else this.HitCount = new Long(v.toString()); return; }
     if (i == 27) { if (v == null) this.StickTime = null; else this.StickTime = new Long(v.toString()); return; }
     if (i == 28) { this.SourceURL = ((String)v); return; }
     if (i == 29) { this.Prop1 = ((String)v); return; }
     if (i == 30) { this.Prop2 = ((String)v); return; }
     if (i == 31) { this.Prop3 = ((String)v); return; }
     if (i == 32) { this.Prop4 = ((String)v); return; }
     if (i == 33) { this.AddUser = ((String)v); return; }
     if (i == 34) { this.AddTime = ((Date)v); return; }
     if (i == 35) { this.ModifyUser = ((String)v); return; }
     if (i != 36) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.Name;
     if (i == 2) return this.OldName;
     if (i == 3) return this.Tag;
     if (i == 4) return this.SiteID;
     if (i == 5) return this.CatalogID;
     if (i == 6) return this.CatalogInnerCode;
     if (i == 7) return this.BranchInnerCode;
     if (i == 8) return this.Path;
     if (i == 9) return this.FileName;
     if (i == 10) return this.SrcFileName;
     if (i == 11) return this.Suffix;
     if (i == 12) return this.Width;
     if (i == 13) return this.Height;
     if (i == 14) return this.Count;
     if (i == 15) return this.Info;
     if (i == 16) return this.IsOriginal;
     if (i == 17) return this.FileSize;
     if (i == 18) return this.System;
     if (i == 19) return this.LinkURL;
     if (i == 20) return this.LinkText;
     if (i == 21) return this.ProduceTime;
     if (i == 22) return this.Author;
     if (i == 23) return this.PublishDate;
     if (i == 24) return this.Integral;
     if (i == 25) return this.OrderFlag;
     if (i == 26) return this.HitCount;
     if (i == 27) return this.StickTime;
     if (i == 28) return this.SourceURL;
     if (i == 29) return this.Prop1;
     if (i == 30) return this.Prop2;
     if (i == 31) return this.Prop3;
     if (i == 32) return this.Prop4;
     if (i == 33) return this.AddUser;
     if (i == 34) return this.AddTime;
     if (i == 35) return this.ModifyUser;
     if (i == 36) return this.ModifyTime;
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
 
   public String getOldName()
   {
     return this.OldName;
   }
 
   public void setOldName(String oldName)
   {
     this.OldName = oldName;
   }
 
   public String getTag()
   {
     return this.Tag;
   }
 
   public void setTag(String tag)
   {
     this.Tag = tag;
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
 
   public String getBranchInnerCode()
   {
     return this.BranchInnerCode;
   }
 
   public void setBranchInnerCode(String branchInnerCode)
   {
     this.BranchInnerCode = branchInnerCode;
   }
 
   public String getPath()
   {
     return this.Path;
   }
 
   public void setPath(String path)
   {
     this.Path = path;
   }
 
   public String getFileName()
   {
     return this.FileName;
   }
 
   public void setFileName(String fileName)
   {
     this.FileName = fileName;
   }
 
   public String getSrcFileName()
   {
     return this.SrcFileName;
   }
 
   public void setSrcFileName(String srcFileName)
   {
     this.SrcFileName = srcFileName;
   }
 
   public String getSuffix()
   {
     return this.Suffix;
   }
 
   public void setSuffix(String suffix)
   {
     this.Suffix = suffix;
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
 
   public long getCount()
   {
     if (this.Count == null) return 0L;
     return this.Count.longValue();
   }
 
   public void setCount(long count)
   {
     this.Count = new Long(count);
   }
 
   public void setCount(String count)
   {
     if (count == null) {
       this.Count = null;
       return;
     }
     this.Count = new Long(count);
   }
 
   public String getInfo()
   {
     return this.Info;
   }
 
   public void setInfo(String info)
   {
     this.Info = info;
   }
 
   public String getIsOriginal()
   {
     return this.IsOriginal;
   }
 
   public void setIsOriginal(String isOriginal)
   {
     this.IsOriginal = isOriginal;
   }
 
   public String getFileSize()
   {
     return this.FileSize;
   }
 
   public void setFileSize(String fileSize)
   {
     this.FileSize = fileSize;
   }
 
   public String getSystem()
   {
     return this.System;
   }
 
   public void setSystem(String system)
   {
     this.System = system;
   }
 
   public String getLinkURL()
   {
     return this.LinkURL;
   }
 
   public void setLinkURL(String linkURL)
   {
     this.LinkURL = linkURL;
   }
 
   public String getLinkText()
   {
     return this.LinkText;
   }
 
   public void setLinkText(String linkText)
   {
     this.LinkText = linkText;
   }
 
   public Date getProduceTime()
   {
     return this.ProduceTime;
   }
 
   public void setProduceTime(Date produceTime)
   {
     this.ProduceTime = produceTime;
   }
 
   public String getAuthor()
   {
     return this.Author;
   }
 
   public void setAuthor(String author)
   {
     this.Author = author;
   }
 
   public Date getPublishDate()
   {
     return this.PublishDate;
   }
 
   public void setPublishDate(Date publishDate)
   {
     this.PublishDate = publishDate;
   }
 
   public long getIntegral()
   {
     if (this.Integral == null) return 0L;
     return this.Integral.longValue();
   }
 
   public void setIntegral(long integral)
   {
     this.Integral = new Long(integral);
   }
 
   public void setIntegral(String integral)
   {
     if (integral == null) {
       this.Integral = null;
       return;
     }
     this.Integral = new Long(integral);
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
 
   public String getSourceURL()
   {
     return this.SourceURL;
   }
 
   public void setSourceURL(String sourceURL)
   {
     this.SourceURL = sourceURL;
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
 * Qualified Name:     com.zving.schema.ZCImageSchema
 * JD-Core Version:    0.5.3
 */