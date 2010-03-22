 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCAttachmentSchema extends Schema
 {
   private Long ID;
   private String Name;
   private String OldName;
   private Long SiteID;
   private Long CatalogID;
   private String CatalogInnerCode;
   private String BranchInnerCode;
   private String Path;
   private String FileName;
   private String SrcFileName;
   private String Suffix;
   private String Info;
   private String FileSize;
   private String System;
   private Date PublishDate;
   private Long Integral;
   private String IsLocked;
   private String Password;
   private String SourceURL;
   private Long OrderFlag;
   private String ImagePath;
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
     new SchemaColumn("SiteID", 7, 3, 0, 0, true, false), 
     new SchemaColumn("CatalogID", 7, 4, 0, 0, true, false), 
     new SchemaColumn("CatalogInnerCode", 1, 5, 100, 0, true, false), 
     new SchemaColumn("BranchInnerCode", 1, 6, 50, 0, false, false), 
     new SchemaColumn("Path", 1, 7, 100, 0, true, false), 
     new SchemaColumn("FileName", 1, 8, 100, 0, true, false), 
     new SchemaColumn("SrcFileName", 1, 9, 100, 0, true, false), 
     new SchemaColumn("Suffix", 1, 10, 10, 0, true, false), 
     new SchemaColumn("Info", 1, 11, 500, 0, false, false), 
     new SchemaColumn("FileSize", 1, 12, 20, 0, false, false), 
     new SchemaColumn("System", 1, 13, 20, 0, false, false), 
     new SchemaColumn("PublishDate", 0, 14, 0, 0, false, false), 
     new SchemaColumn("Integral", 7, 15, 0, 0, false, false), 
     new SchemaColumn("IsLocked", 1, 16, 5, 0, true, false), 
     new SchemaColumn("Password", 1, 17, 50, 0, false, false), 
     new SchemaColumn("SourceURL", 1, 18, 200, 0, false, false), 
     new SchemaColumn("OrderFlag", 7, 19, 0, 0, true, false), 
     new SchemaColumn("ImagePath", 1, 20, 100, 0, false, false), 
     new SchemaColumn("Prop1", 1, 21, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 22, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 23, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 24, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 25, 50, 0, true, false), 
     new SchemaColumn("AddTime", 0, 26, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 27, 50, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 28, 0, 0, false, false) };
   public static final String _TableCode = "ZCAttachment";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCAttachment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCAttachment set ID=?,Name=?,OldName=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Path=?,FileName=?,SrcFileName=?,Suffix=?,Info=?,FileSize=?,System=?,PublishDate=?,Integral=?,IsLocked=?,Password=?,SourceURL=?,OrderFlag=?,ImagePath=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCAttachment  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCAttachment  where ID=?";
 
   public ZCAttachmentSchema()
   {
     this.TableCode = "ZCAttachment";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCAttachment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCAttachment set ID=?,Name=?,OldName=?,SiteID=?,CatalogID=?,CatalogInnerCode=?,BranchInnerCode=?,Path=?,FileName=?,SrcFileName=?,Suffix=?,Info=?,FileSize=?,System=?,PublishDate=?,Integral=?,IsLocked=?,Password=?,SourceURL=?,OrderFlag=?,ImagePath=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCAttachment  where ID=?";
     this.FillAllSQL = "select * from ZCAttachment  where ID=?";
     this.HasSetFlag = new boolean[29];
   }
 
   protected Schema newInstance() {
     return new ZCAttachmentSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCAttachmentSet();
   }
 
   public ZCAttachmentSet query() {
     return query(null, -1, -1);
   }
 
   public ZCAttachmentSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCAttachmentSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCAttachmentSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCAttachmentSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.Name = ((String)v); return; }
     if (i == 2) { this.OldName = ((String)v); return; }
     if (i == 3) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 4) { if (v == null) this.CatalogID = null; else this.CatalogID = new Long(v.toString()); return; }
     if (i == 5) { this.CatalogInnerCode = ((String)v); return; }
     if (i == 6) { this.BranchInnerCode = ((String)v); return; }
     if (i == 7) { this.Path = ((String)v); return; }
     if (i == 8) { this.FileName = ((String)v); return; }
     if (i == 9) { this.SrcFileName = ((String)v); return; }
     if (i == 10) { this.Suffix = ((String)v); return; }
     if (i == 11) { this.Info = ((String)v); return; }
     if (i == 12) { this.FileSize = ((String)v); return; }
     if (i == 13) { this.System = ((String)v); return; }
     if (i == 14) { this.PublishDate = ((Date)v); return; }
     if (i == 15) { if (v == null) this.Integral = null; else this.Integral = new Long(v.toString()); return; }
     if (i == 16) { this.IsLocked = ((String)v); return; }
     if (i == 17) { this.Password = ((String)v); return; }
     if (i == 18) { this.SourceURL = ((String)v); return; }
     if (i == 19) { if (v == null) this.OrderFlag = null; else this.OrderFlag = new Long(v.toString()); return; }
     if (i == 20) { this.ImagePath = ((String)v); return; }
     if (i == 21) { this.Prop1 = ((String)v); return; }
     if (i == 22) { this.Prop2 = ((String)v); return; }
     if (i == 23) { this.Prop3 = ((String)v); return; }
     if (i == 24) { this.Prop4 = ((String)v); return; }
     if (i == 25) { this.AddUser = ((String)v); return; }
     if (i == 26) { this.AddTime = ((Date)v); return; }
     if (i == 27) { this.ModifyUser = ((String)v); return; }
     if (i != 28) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.Name;
     if (i == 2) return this.OldName;
     if (i == 3) return this.SiteID;
     if (i == 4) return this.CatalogID;
     if (i == 5) return this.CatalogInnerCode;
     if (i == 6) return this.BranchInnerCode;
     if (i == 7) return this.Path;
     if (i == 8) return this.FileName;
     if (i == 9) return this.SrcFileName;
     if (i == 10) return this.Suffix;
     if (i == 11) return this.Info;
     if (i == 12) return this.FileSize;
     if (i == 13) return this.System;
     if (i == 14) return this.PublishDate;
     if (i == 15) return this.Integral;
     if (i == 16) return this.IsLocked;
     if (i == 17) return this.Password;
     if (i == 18) return this.SourceURL;
     if (i == 19) return this.OrderFlag;
     if (i == 20) return this.ImagePath;
     if (i == 21) return this.Prop1;
     if (i == 22) return this.Prop2;
     if (i == 23) return this.Prop3;
     if (i == 24) return this.Prop4;
     if (i == 25) return this.AddUser;
     if (i == 26) return this.AddTime;
     if (i == 27) return this.ModifyUser;
     if (i == 28) return this.ModifyTime;
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
 
   public String getInfo()
   {
     return this.Info;
   }
 
   public void setInfo(String info)
   {
     this.Info = info;
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
 
   public String getIsLocked()
   {
     return this.IsLocked;
   }
 
   public void setIsLocked(String isLocked)
   {
     this.IsLocked = isLocked;
   }
 
   public String getPassword()
   {
     return this.Password;
   }
 
   public void setPassword(String password)
   {
     this.Password = password;
   }
 
   public String getSourceURL()
   {
     return this.SourceURL;
   }
 
   public void setSourceURL(String sourceURL)
   {
     this.SourceURL = sourceURL;
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
 
   public String getImagePath()
   {
     return this.ImagePath;
   }
 
   public void setImagePath(String imagePath)
   {
     this.ImagePath = imagePath;
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
 * Qualified Name:     com.zving.schema.ZCAttachmentSchema
 * JD-Core Version:    0.5.3
 */