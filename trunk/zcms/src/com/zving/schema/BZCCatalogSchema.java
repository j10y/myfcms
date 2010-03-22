 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCCatalogSchema extends Schema
 {
   private Long ID;
   private Long ParentID;
   private Long SiteID;
   private String Name;
   private String InnerCode;
   private String BranchInnerCode;
   private String Alias;
   private String URL;
   private String ImagePath;
   private Long Type;
   private String IndexTemplate;
   private String ListTemplate;
   private String ListNameRule;
   private String DetailTemplate;
   private String DetailNameRule;
   private String RssTemplate;
   private String RssNameRule;
   private String Workflow;
   private Long TreeLevel;
   private Long ChildCount;
   private Long IsLeaf;
   private Long IsDirty;
   private Long Total;
   private Long OrderFlag;
   private String Logo;
   private Long ListPageSize;
   private String PublishFlag;
   private String SingleFlag;
   private Long HitCount;
   private String Meta_Keywords;
   private String Meta_Description;
   private String OrderColumn;
   private Long Integral;
   private String KeywordFlag;
   private String KeywordSetting;
   private String AllowContribute;
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
     new SchemaColumn("ParentID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("SiteID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Name", 1, 3, 100, 0, true, false), 
     new SchemaColumn("InnerCode", 1, 4, 100, 0, true, false), 
     new SchemaColumn("BranchInnerCode", 1, 5, 100, 0, false, false), 
     new SchemaColumn("Alias", 1, 6, 100, 0, true, false), 
     new SchemaColumn("URL", 1, 7, 100, 0, false, false), 
     new SchemaColumn("ImagePath", 1, 8, 50, 0, false, false), 
     new SchemaColumn("Type", 7, 9, 0, 0, true, false), 
     new SchemaColumn("IndexTemplate", 1, 10, 200, 0, false, false), 
     new SchemaColumn("ListTemplate", 1, 11, 200, 0, false, false), 
     new SchemaColumn("ListNameRule", 1, 12, 200, 0, false, false), 
     new SchemaColumn("DetailTemplate", 1, 13, 200, 0, false, false), 
     new SchemaColumn("DetailNameRule", 1, 14, 200, 0, false, false), 
     new SchemaColumn("RssTemplate", 1, 15, 200, 0, false, false), 
     new SchemaColumn("RssNameRule", 1, 16, 200, 0, false, false), 
     new SchemaColumn("Workflow", 1, 17, 100, 0, false, false), 
     new SchemaColumn("TreeLevel", 7, 18, 0, 0, true, false), 
     new SchemaColumn("ChildCount", 7, 19, 0, 0, true, false), 
     new SchemaColumn("IsLeaf", 7, 20, 0, 0, true, false), 
     new SchemaColumn("IsDirty", 7, 21, 0, 0, false, false), 
     new SchemaColumn("Total", 7, 22, 0, 0, true, false), 
     new SchemaColumn("OrderFlag", 7, 23, 0, 0, true, false), 
     new SchemaColumn("Logo", 1, 24, 100, 0, false, false), 
     new SchemaColumn("ListPageSize", 7, 25, 0, 0, false, false), 
     new SchemaColumn("PublishFlag", 1, 26, 2, 0, true, false), 
     new SchemaColumn("SingleFlag", 1, 27, 2, 0, false, false), 
     new SchemaColumn("HitCount", 7, 28, 0, 0, false, false), 
     new SchemaColumn("Meta_Keywords", 1, 29, 200, 0, false, false), 
     new SchemaColumn("Meta_Description", 1, 30, 200, 0, false, false), 
     new SchemaColumn("OrderColumn", 1, 31, 20, 0, false, false), 
     new SchemaColumn("Integral", 7, 32, 0, 0, false, false), 
     new SchemaColumn("KeywordFlag", 1, 33, 2, 0, false, false), 
     new SchemaColumn("KeywordSetting", 1, 34, 50, 0, false, false), 
     new SchemaColumn("AllowContribute", 1, 35, 2, 0, false, false), 
     new SchemaColumn("Prop1", 1, 36, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 37, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 38, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 39, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 40, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 41, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 42, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 43, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 44, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 45, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 46, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 47, 50, 0, false, false) };
   public static final String _TableCode = "BZCCatalog";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCCatalog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCCatalog set ID=?,ParentID=?,SiteID=?,Name=?,InnerCode=?,BranchInnerCode=?,Alias=?,URL=?,ImagePath=?,Type=?,IndexTemplate=?,ListTemplate=?,ListNameRule=?,DetailTemplate=?,DetailNameRule=?,RssTemplate=?,RssNameRule=?,Workflow=?,TreeLevel=?,ChildCount=?,IsLeaf=?,IsDirty=?,Total=?,OrderFlag=?,Logo=?,ListPageSize=?,PublishFlag=?,SingleFlag=?,HitCount=?,Meta_Keywords=?,Meta_Description=?,OrderColumn=?,Integral=?,KeywordFlag=?,KeywordSetting=?,AllowContribute=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCCatalog  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCCatalog  where ID=? and BackupNo=?";
 
   public BZCCatalogSchema()
   {
     this.TableCode = "BZCCatalog";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCCatalog values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCCatalog set ID=?,ParentID=?,SiteID=?,Name=?,InnerCode=?,BranchInnerCode=?,Alias=?,URL=?,ImagePath=?,Type=?,IndexTemplate=?,ListTemplate=?,ListNameRule=?,DetailTemplate=?,DetailNameRule=?,RssTemplate=?,RssNameRule=?,Workflow=?,TreeLevel=?,ChildCount=?,IsLeaf=?,IsDirty=?,Total=?,OrderFlag=?,Logo=?,ListPageSize=?,PublishFlag=?,SingleFlag=?,HitCount=?,Meta_Keywords=?,Meta_Description=?,OrderColumn=?,Integral=?,KeywordFlag=?,KeywordSetting=?,AllowContribute=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCCatalog  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCCatalog  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[48];
   }
 
   protected Schema newInstance() {
     return new BZCCatalogSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCCatalogSet();
   }
 
   public BZCCatalogSet query() {
     return query(null, -1, -1);
   }
 
   public BZCCatalogSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCCatalogSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCCatalogSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCCatalogSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.ParentID = null; else this.ParentID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 3) { this.Name = ((String)v); return; }
     if (i == 4) { this.InnerCode = ((String)v); return; }
     if (i == 5) { this.BranchInnerCode = ((String)v); return; }
     if (i == 6) { this.Alias = ((String)v); return; }
     if (i == 7) { this.URL = ((String)v); return; }
     if (i == 8) { this.ImagePath = ((String)v); return; }
     if (i == 9) { if (v == null) this.Type = null; else this.Type = new Long(v.toString()); return; }
     if (i == 10) { this.IndexTemplate = ((String)v); return; }
     if (i == 11) { this.ListTemplate = ((String)v); return; }
     if (i == 12) { this.ListNameRule = ((String)v); return; }
     if (i == 13) { this.DetailTemplate = ((String)v); return; }
     if (i == 14) { this.DetailNameRule = ((String)v); return; }
     if (i == 15) { this.RssTemplate = ((String)v); return; }
     if (i == 16) { this.RssNameRule = ((String)v); return; }
     if (i == 17) { this.Workflow = ((String)v); return; }
     if (i == 18) { if (v == null) this.TreeLevel = null; else this.TreeLevel = new Long(v.toString()); return; }
     if (i == 19) { if (v == null) this.ChildCount = null; else this.ChildCount = new Long(v.toString()); return; }
     if (i == 20) { if (v == null) this.IsLeaf = null; else this.IsLeaf = new Long(v.toString()); return; }
     if (i == 21) { if (v == null) this.IsDirty = null; else this.IsDirty = new Long(v.toString()); return; }
     if (i == 22) { if (v == null) this.Total = null; else this.Total = new Long(v.toString()); return; }
     if (i == 23) { if (v == null) this.OrderFlag = null; else this.OrderFlag = new Long(v.toString()); return; }
     if (i == 24) { this.Logo = ((String)v); return; }
     if (i == 25) { if (v == null) this.ListPageSize = null; else this.ListPageSize = new Long(v.toString()); return; }
     if (i == 26) { this.PublishFlag = ((String)v); return; }
     if (i == 27) { this.SingleFlag = ((String)v); return; }
     if (i == 28) { if (v == null) this.HitCount = null; else this.HitCount = new Long(v.toString()); return; }
     if (i == 29) { this.Meta_Keywords = ((String)v); return; }
     if (i == 30) { this.Meta_Description = ((String)v); return; }
     if (i == 31) { this.OrderColumn = ((String)v); return; }
     if (i == 32) { if (v == null) this.Integral = null; else this.Integral = new Long(v.toString()); return; }
     if (i == 33) { this.KeywordFlag = ((String)v); return; }
     if (i == 34) { this.KeywordSetting = ((String)v); return; }
     if (i == 35) { this.AllowContribute = ((String)v); return; }
     if (i == 36) { this.Prop1 = ((String)v); return; }
     if (i == 37) { this.Prop2 = ((String)v); return; }
     if (i == 38) { this.Prop3 = ((String)v); return; }
     if (i == 39) { this.Prop4 = ((String)v); return; }
     if (i == 40) { this.AddUser = ((String)v); return; }
     if (i == 41) { this.AddTime = ((Date)v); return; }
     if (i == 42) { this.ModifyUser = ((String)v); return; }
     if (i == 43) { this.ModifyTime = ((Date)v); return; }
     if (i == 44) { this.BackupNo = ((String)v); return; }
     if (i == 45) { this.BackupOperator = ((String)v); return; }
     if (i == 46) { this.BackupTime = ((Date)v); return; }
     if (i != 47) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.ParentID;
     if (i == 2) return this.SiteID;
     if (i == 3) return this.Name;
     if (i == 4) return this.InnerCode;
     if (i == 5) return this.BranchInnerCode;
     if (i == 6) return this.Alias;
     if (i == 7) return this.URL;
     if (i == 8) return this.ImagePath;
     if (i == 9) return this.Type;
     if (i == 10) return this.IndexTemplate;
     if (i == 11) return this.ListTemplate;
     if (i == 12) return this.ListNameRule;
     if (i == 13) return this.DetailTemplate;
     if (i == 14) return this.DetailNameRule;
     if (i == 15) return this.RssTemplate;
     if (i == 16) return this.RssNameRule;
     if (i == 17) return this.Workflow;
     if (i == 18) return this.TreeLevel;
     if (i == 19) return this.ChildCount;
     if (i == 20) return this.IsLeaf;
     if (i == 21) return this.IsDirty;
     if (i == 22) return this.Total;
     if (i == 23) return this.OrderFlag;
     if (i == 24) return this.Logo;
     if (i == 25) return this.ListPageSize;
     if (i == 26) return this.PublishFlag;
     if (i == 27) return this.SingleFlag;
     if (i == 28) return this.HitCount;
     if (i == 29) return this.Meta_Keywords;
     if (i == 30) return this.Meta_Description;
     if (i == 31) return this.OrderColumn;
     if (i == 32) return this.Integral;
     if (i == 33) return this.KeywordFlag;
     if (i == 34) return this.KeywordSetting;
     if (i == 35) return this.AllowContribute;
     if (i == 36) return this.Prop1;
     if (i == 37) return this.Prop2;
     if (i == 38) return this.Prop3;
     if (i == 39) return this.Prop4;
     if (i == 40) return this.AddUser;
     if (i == 41) return this.AddTime;
     if (i == 42) return this.ModifyUser;
     if (i == 43) return this.ModifyTime;
     if (i == 44) return this.BackupNo;
     if (i == 45) return this.BackupOperator;
     if (i == 46) return this.BackupTime;
     if (i == 47) return this.BackupMemo;
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
 
   public long getParentID()
   {
     if (this.ParentID == null) return 0L;
     return this.ParentID.longValue();
   }
 
   public void setParentID(long parentID)
   {
     this.ParentID = new Long(parentID);
   }
 
   public void setParentID(String parentID)
   {
     if (parentID == null) {
       this.ParentID = null;
       return;
     }
     this.ParentID = new Long(parentID);
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
 
   public String getInnerCode()
   {
     return this.InnerCode;
   }
 
   public void setInnerCode(String innerCode)
   {
     this.InnerCode = innerCode;
   }
 
   public String getBranchInnerCode()
   {
     return this.BranchInnerCode;
   }
 
   public void setBranchInnerCode(String branchInnerCode)
   {
     this.BranchInnerCode = branchInnerCode;
   }
 
   public String getAlias()
   {
     return this.Alias;
   }
 
   public void setAlias(String alias)
   {
     this.Alias = alias;
   }
 
   public String getURL()
   {
     return this.URL;
   }
 
   public void setURL(String uRL)
   {
     this.URL = uRL;
   }
 
   public String getImagePath()
   {
     return this.ImagePath;
   }
 
   public void setImagePath(String imagePath)
   {
     this.ImagePath = imagePath;
   }
 
   public long getType()
   {
     if (this.Type == null) return 0L;
     return this.Type.longValue();
   }
 
   public void setType(long type)
   {
     this.Type = new Long(type);
   }
 
   public void setType(String type)
   {
     if (type == null) {
       this.Type = null;
       return;
     }
     this.Type = new Long(type);
   }
 
   public String getIndexTemplate()
   {
     return this.IndexTemplate;
   }
 
   public void setIndexTemplate(String indexTemplate)
   {
     this.IndexTemplate = indexTemplate;
   }
 
   public String getListTemplate()
   {
     return this.ListTemplate;
   }
 
   public void setListTemplate(String listTemplate)
   {
     this.ListTemplate = listTemplate;
   }
 
   public String getListNameRule()
   {
     return this.ListNameRule;
   }
 
   public void setListNameRule(String listNameRule)
   {
     this.ListNameRule = listNameRule;
   }
 
   public String getDetailTemplate()
   {
     return this.DetailTemplate;
   }
 
   public void setDetailTemplate(String detailTemplate)
   {
     this.DetailTemplate = detailTemplate;
   }
 
   public String getDetailNameRule()
   {
     return this.DetailNameRule;
   }
 
   public void setDetailNameRule(String detailNameRule)
   {
     this.DetailNameRule = detailNameRule;
   }
 
   public String getRssTemplate()
   {
     return this.RssTemplate;
   }
 
   public void setRssTemplate(String rssTemplate)
   {
     this.RssTemplate = rssTemplate;
   }
 
   public String getRssNameRule()
   {
     return this.RssNameRule;
   }
 
   public void setRssNameRule(String rssNameRule)
   {
     this.RssNameRule = rssNameRule;
   }
 
   public String getWorkflow()
   {
     return this.Workflow;
   }
 
   public void setWorkflow(String workflow)
   {
     this.Workflow = workflow;
   }
 
   public long getTreeLevel()
   {
     if (this.TreeLevel == null) return 0L;
     return this.TreeLevel.longValue();
   }
 
   public void setTreeLevel(long treeLevel)
   {
     this.TreeLevel = new Long(treeLevel);
   }
 
   public void setTreeLevel(String treeLevel)
   {
     if (treeLevel == null) {
       this.TreeLevel = null;
       return;
     }
     this.TreeLevel = new Long(treeLevel);
   }
 
   public long getChildCount()
   {
     if (this.ChildCount == null) return 0L;
     return this.ChildCount.longValue();
   }
 
   public void setChildCount(long childCount)
   {
     this.ChildCount = new Long(childCount);
   }
 
   public void setChildCount(String childCount)
   {
     if (childCount == null) {
       this.ChildCount = null;
       return;
     }
     this.ChildCount = new Long(childCount);
   }
 
   public long getIsLeaf()
   {
     if (this.IsLeaf == null) return 0L;
     return this.IsLeaf.longValue();
   }
 
   public void setIsLeaf(long isLeaf)
   {
     this.IsLeaf = new Long(isLeaf);
   }
 
   public void setIsLeaf(String isLeaf)
   {
     if (isLeaf == null) {
       this.IsLeaf = null;
       return;
     }
     this.IsLeaf = new Long(isLeaf);
   }
 
   public long getIsDirty()
   {
     if (this.IsDirty == null) return 0L;
     return this.IsDirty.longValue();
   }
 
   public void setIsDirty(long isDirty)
   {
     this.IsDirty = new Long(isDirty);
   }
 
   public void setIsDirty(String isDirty)
   {
     if (isDirty == null) {
       this.IsDirty = null;
       return;
     }
     this.IsDirty = new Long(isDirty);
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
 
   public String getLogo()
   {
     return this.Logo;
   }
 
   public void setLogo(String logo)
   {
     this.Logo = logo;
   }
 
   public long getListPageSize()
   {
     if (this.ListPageSize == null) return 0L;
     return this.ListPageSize.longValue();
   }
 
   public void setListPageSize(long listPageSize)
   {
     this.ListPageSize = new Long(listPageSize);
   }
 
   public void setListPageSize(String listPageSize)
   {
     if (listPageSize == null) {
       this.ListPageSize = null;
       return;
     }
     this.ListPageSize = new Long(listPageSize);
   }
 
   public String getPublishFlag()
   {
     return this.PublishFlag;
   }
 
   public void setPublishFlag(String publishFlag)
   {
     this.PublishFlag = publishFlag;
   }
 
   public String getSingleFlag()
   {
     return this.SingleFlag;
   }
 
   public void setSingleFlag(String singleFlag)
   {
     this.SingleFlag = singleFlag;
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
 
   public String getMeta_Keywords()
   {
     return this.Meta_Keywords;
   }
 
   public void setMeta_Keywords(String meta_Keywords)
   {
     this.Meta_Keywords = meta_Keywords;
   }
 
   public String getMeta_Description()
   {
     return this.Meta_Description;
   }
 
   public void setMeta_Description(String meta_Description)
   {
     this.Meta_Description = meta_Description;
   }
 
   public String getOrderColumn()
   {
     return this.OrderColumn;
   }
 
   public void setOrderColumn(String orderColumn)
   {
     this.OrderColumn = orderColumn;
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
 
   public String getKeywordFlag()
   {
     return this.KeywordFlag;
   }
 
   public void setKeywordFlag(String keywordFlag)
   {
     this.KeywordFlag = keywordFlag;
   }
 
   public String getKeywordSetting()
   {
     return this.KeywordSetting;
   }
 
   public void setKeywordSetting(String keywordSetting)
   {
     this.KeywordSetting = keywordSetting;
   }
 
   public String getAllowContribute()
   {
     return this.AllowContribute;
   }
 
   public void setAllowContribute(String allowContribute)
   {
     this.AllowContribute = allowContribute;
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
 * Qualified Name:     com.zving.schema.BZCCatalogSchema
 * JD-Core Version:    0.5.3
 */