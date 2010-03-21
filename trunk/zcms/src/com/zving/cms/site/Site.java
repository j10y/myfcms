 package com.zving.cms.site;
 
 import com.zving.cms.dataservice.FullText;
 import com.zving.cms.pub.CatalogUtil;
 import com.zving.cms.pub.SiteImporter;
 import com.zving.cms.pub.SiteTableRela;
 import com.zving.cms.pub.SiteTableRela.TableRela;
 import com.zving.cms.pub.SiteUtil;
 import com.zving.cms.resource.ConfigImageLib;
 import com.zving.framework.Config;
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataRow;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.license.LicenseInfo;
 import com.zving.framework.messages.LongTimeTask;
 import com.zving.framework.schedule.CronExpressionException;
 import com.zving.framework.schedule.CronMonitor;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.Filter;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.LogUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.platform.Priv;
 import com.zving.platform.RolePriv;
 import com.zving.platform.UserLog;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCArticleSchema;
 import com.zving.schema.ZCArticleSet;
 import com.zving.schema.ZCAudioSchema;
 import com.zving.schema.ZCAudioSet;
 import com.zving.schema.ZCCatalogConfigSchema;
 import com.zving.schema.ZCCatalogConfigSet;
 import com.zving.schema.ZCCatalogSchema;
 import com.zving.schema.ZCCatalogSet;
 import com.zving.schema.ZCImageSchema;
 import com.zving.schema.ZCImageSet;
 import com.zving.schema.ZCSiteSchema;
 import com.zving.schema.ZCSiteSet;
 import com.zving.schema.ZCVideoSchema;
 import com.zving.schema.ZCVideoSet;
 import com.zving.schema.ZDPrivilegeSchema;
 import com.zving.schema.ZDScheduleSchema;
 import java.io.File;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.fileupload.FileItem;
 import org.apache.commons.fileupload.disk.DiskFileItemFactory;
 import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
 public class Site extends Page
 {
   public static Mapx init(Mapx params)
   {
     ZCSiteSchema site = new ZCSiteSchema();
     site.setID(Application.getCurrentSiteID());
     site.fill();
 
     return site.toMapx();
   }
 
   public static Mapx initDialog(Mapx params) {
     Object o1 = params.get("ID");
     if (o1 != null) {
       long ID = Long.parseLong(params.get("ID").toString());
       ZCSiteSchema site = new ZCSiteSchema();
       site.setID(ID);
       site.fill();
 
       params.putAll(site.toMapx());
 
       String indexFlag = site.getAutoIndexFlag();
       if (StringUtil.isEmpty(indexFlag)) {
         indexFlag = "Y";
       }
       params.put("radioAutoIndexFlag", HtmlUtil.codeToRadios("AutoIndexFlag", "YesOrNo", indexFlag));
 
       String statFlag = site.getAutoStatFlag();
       if (StringUtil.isEmpty(statFlag)) {
         statFlag = "Y";
       }
       params.put("radioAutoStatFlag", HtmlUtil.codeToRadios("AutoStatFlag", "YesOrNo", statFlag));
 
       String auditFlag = site.getCommentAuditFlag();
       params.put("radioCommentAuditFlag", HtmlUtil.codeToRadios("CommentAuditFlag", "YesOrNo", auditFlag));
 
       return params;
     }
     params.put("URL", "http://");
     params.put("radioAutoIndexFlag", HtmlUtil.codeToRadios("AutoIndexFlag", "YesOrNo", "Y"));
     params.put("radioAutoStatFlag", HtmlUtil.codeToRadios("AutoStatFlag", "YesOrNo", "Y"));
     params.put("radioCommentAuditFlag", HtmlUtil.codeToRadios("CommentAuditFlag", "YesOrNo", "Y"));
     return params;
   }
 
   public void saveTemplate()
   {
     Transaction trans = new Transaction();
     ZCSiteSchema site = new ZCSiteSchema();
     site.setID(Long.parseLong($V("ID")));
     site.fill();
     site.setValue(this.Request);
     site.setModifyUser(User.getUserName());
     site.setModifyTime(new Date());
 
     trans.add(site, 2);
 
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("保存数据发生错误!");
     }
   }
 
   public static void dg1DataBind(DataGridAction dga) {
     String sql = "select * from ZCSite order by orderflag ";
     if (dga.getTotal() == 0) {
       String sql2 = "select count(*) from ZCSite";
       dga.setTotal(new QueryBuilder(sql2));
     }
     dga.bindData(new QueryBuilder(sql));
   }
 
   public void add()
   {
     Transaction trans = new Transaction();
     long id = NoUtil.getMaxID("SiteID");
     if ((!(add(trans, id))) || (!(trans.commit()))) {
       this.Response.setLogInfo(1, "新建站点失败");
     } else {
       updatePrivAndFile($V("Alias"));
 
       SiteUtil.update(id);
       this.Response.setLogInfo(1, "新建站点成功");
     }
   }
 
   public boolean add(Transaction trans, long siteID)
   {
     if ((LicenseInfo.getName().equals("TrailUser")) && 
       (new QueryBuilder("select count(*) from ZCSite").executeInt() >= 1)) {
       this.Response.setError("站点数超出限制，请联系泽元软件更换License！");
       return false;
     }
     ZCSiteSchema site = new ZCSiteSchema();
     site.setValue(this.Request);
     site.setID(siteID);
     site.setHitCount(0L);
     site.setChannelCount(0L);
     site.setSpecialCount(0L);
     site.setMagzineCount(0L);
     site.setArticleCount(0L);
     site.setImageLibCount(1L);
     site.setVideoLibCount(1L);
     site.setAudioLibCount(1L);
     site.setAttachmentLibCount(1L);
     site.setBranchInnerCode(User.getBranchInnerCode());
     site.setAddTime(new Date());
     site.setAddUser(User.getUserName());
     site.setConfigXML(ConfigImageLib.imageLibConfigDefault);
     trans.add(site, 1);
 
     addDefaultResourceLib(site.getID(), trans);
 
     addDefaultPriv(site.getID(), trans);
 
     initSiteConfig(site.getID(), trans);
     return true;
   }
 
   public static void updatePrivAndFile(String alias)
   {
     RolePriv.updateAllPriv("admin");
 
     String oldPath = Config.getContextRealPath() + "Images";
     String path = Config.getContextRealPath() + Config.getValue("UploadDir") + "/" + alias + "/upload/Image"
       .replaceAll("//", "/");
     File dir = new File(path);
     if (!(dir.exists())) {
       dir.mkdirs();
     }
     FileUtil.copy(oldPath + "/nocover.jpg", path + "/nocover.jpg");
     FileUtil.copy(oldPath + "/nophoto.jpg", path + "/nophoto.jpg");
     FileUtil.copy(oldPath + "/nopicture.jpg", path + "/nopicture.jpg");
     FileUtil.copy(oldPath + "/WaterMarkImage1.png", path + "/WaterMarkImage1.png");
     FileUtil.copy(oldPath + "/WaterMarkImage.png", path + "/WaterMarkImage.png");
 
     String templatePath = Config.getContextRealPath() + Config.getValue("Statical.TemplateDir") + "/" + alias + 
       "/template";
     dir = new File(templatePath);
     if (!(dir.exists())) {
       dir.mkdirs();
     }
 
     String imagePath = Config.getContextRealPath() + Config.getValue("Statical.TemplateDir") + "/" + alias + 
       "/images";
     dir = new File(imagePath);
     if (!(dir.exists())) {
       dir.mkdirs();
     }
 
     FileUtil.copy(Config.getContextRealPath() + "/Tools/ImagePlayer.swf", imagePath + "/ImagePlayer.swf");
     FileUtil.copy(Config.getContextRealPath() + "/Tools/player.swf", imagePath + "/player.swf");
     FileUtil.copy(Config.getContextRealPath() + "/Tools/Swfobject.js", imagePath + "/Swfobject.js");
 
     FileUtil.copy(Config.getContextRealPath() + "/Tools/vote.js", imagePath + "/vote.js");
     FileUtil.copy(Config.getContextRealPath() + "/Tools/vote.css", imagePath + "/vote.css");
   }
 
   public static void addDefaultCatalogLib(long siteID, Transaction trans, String catalogType, String[] catalogNames)
   {
     for (int i = 0; i < catalogNames.length; ++i) {
       ZCCatalogSchema catalog = new ZCCatalogSchema();
       catalog.setID(NoUtil.getMaxID("CatalogID"));
       catalog.setSiteID(siteID);
       catalog.setParentID(0L);
       catalog.setInnerCode(CatalogUtil.createCatalogInnerCode(""));
       catalog.setTreeLevel(1L);
       catalog.setName(catalogNames[i]);
       catalog.setURL("");
       catalog.setAlias(StringUtil.getChineseFirstAlpha(catalogNames[i]));
       catalog.setType(catalogType);
       catalog.setListTemplate("");
       catalog.setListNameRule("");
       catalog.setDetailTemplate("");
       catalog.setDetailNameRule("");
       catalog.setChildCount(0L);
       catalog.setIsLeaf(1L);
       catalog.setTotal(0L);
       catalog.setOrderFlag(Catalog.getCatalogOrderFlag(0L, catalog.getType()));
       catalog.setLogo("");
       catalog.setListPageSize(10L);
       catalog.setPublishFlag("Y");
       catalog.setHitCount(0L);
       catalog.setMeta_Keywords("");
       catalog.setMeta_Description("");
       catalog.setOrderColumn("");
       catalog.setAddUser(User.getUserName());
       catalog.setAddTime(new Date());
       trans.add(catalog, 1);
     }
   }
 
   public static void addDefaultResourceLib(long siteID, Transaction trans)
   {
     ZCCatalogSchema imageCatalog = new ZCCatalogSchema();
     imageCatalog.setID(NoUtil.getMaxID("CatalogID"));
     imageCatalog.setSiteID(siteID);
     imageCatalog.setParentID(0L);
     imageCatalog.setInnerCode(CatalogUtil.createCatalogInnerCode(""));
     imageCatalog.setTreeLevel(1L);
     imageCatalog.setName("默认图片");
     imageCatalog.setURL("");
     imageCatalog.setAlias("default");
     imageCatalog.setType(4L);
     imageCatalog.setListTemplate("");
     imageCatalog.setListNameRule("");
     imageCatalog.setDetailTemplate("");
     imageCatalog.setDetailNameRule("");
     imageCatalog.setChildCount(0L);
     imageCatalog.setIsLeaf(1L);
     imageCatalog.setTotal(0L);
     imageCatalog.setOrderFlag(Catalog.getCatalogOrderFlag(0L, imageCatalog.getType()));
     imageCatalog.setLogo("");
     imageCatalog.setListPageSize(10L);
     imageCatalog.setPublishFlag("Y");
     imageCatalog.setHitCount(0L);
     imageCatalog.setMeta_Keywords("");
     imageCatalog.setMeta_Description("");
     imageCatalog.setOrderColumn("");
     imageCatalog.setAddUser(User.getUserName());
     imageCatalog.setAddTime(new Date());
     trans.add(imageCatalog, 1);
 
     ZCCatalogSchema videoCatalog = (ZCCatalogSchema)imageCatalog.clone();
     videoCatalog.setID(NoUtil.getMaxID("CatalogID"));
     videoCatalog.setInnerCode(CatalogUtil.createCatalogInnerCode(""));
     videoCatalog.setType(5L);
     videoCatalog.setName("默认视频");
     videoCatalog.setAlias("default");
     trans.add(videoCatalog, 1);
 
     ZCCatalogSchema audioCatalog = (ZCCatalogSchema)imageCatalog.clone();
     audioCatalog.setID(NoUtil.getMaxID("CatalogID"));
     audioCatalog.setInnerCode(CatalogUtil.createCatalogInnerCode(""));
     audioCatalog.setType(6L);
     audioCatalog.setName("默认音频");
     audioCatalog.setAlias("default");
     trans.add(audioCatalog, 1);
 
     ZCCatalogSchema attachCatalog = (ZCCatalogSchema)imageCatalog.clone();
     attachCatalog.setID(NoUtil.getMaxID("CatalogID"));
     attachCatalog.setInnerCode(CatalogUtil.createCatalogInnerCode(""));
     attachCatalog.setType(7L);
     attachCatalog.setName("默认附件");
     attachCatalog.setAlias("default");
     trans.add(attachCatalog, 1);
   }
 
   public static void addDefaultPriv(long siteID, Transaction trans)
   {
     Set set = Priv.SITE_MAP.keySet();
     String code;
     ZDPrivilegeSchema priv;
     for (Iterator iter = set.iterator(); iter.hasNext(); ) {
       code = (String)iter.next();
       priv = new ZDPrivilegeSchema();
       priv.setOwnerType("R");
       priv.setOwner("admin");
       priv.setID(siteID);
       priv.setPrivType("site");
       priv.setCode(code);
       priv.setValue("1");
       trans.add(priv, 1);
     }
 
     set = Priv.ARTICLE_MAP.keySet();
     for (iter = set.iterator(); iter.hasNext(); ) {
       code = (String)iter.next();
       priv = new ZDPrivilegeSchema();
       priv.setOwnerType("R");
       priv.setOwner("admin");
       priv.setID(siteID);
       priv.setPrivType("site");
       priv.setCode(code);
       priv.setValue("1");
       trans.add(priv, 1);
     }
   }
 
   public static void initSiteConfig(long siteID, Transaction trans) {
     ZCCatalogConfigSchema config = new ZCCatalogConfigSchema();
     config.setID(NoUtil.getMaxID("PublishConfigID"));
     config.setAddTime(new Date());
     config.setAddUser(User.getUserName());
     config.setStartTime(new Date());
     config.setArchiveTime("12");
     config.setAfterEditStatus("30");
     config.setAllowStatus("0,30");
     config.setAttachDownFlag("Y");
     config.setHotWordFlag("N");
     config.setSiteID(siteID);
     config.setIsUsing("Y");
     config.setPlanType("Period");
     Calendar c = Calendar.getInstance();
     c.setTime(new Date());
     StringBuffer sb = new StringBuffer();
     int minute = c.get(12);
     int hour = c.get(11);
     int day = c.get(5);
     sb.append(minute);
     sb.append(" ");
     sb.append(hour);
     sb.append(" ");
     sb.append(day);
     sb.append("-");
     sb.append(day - 1);
     sb.append("/1");
     sb.append(" * *");
     config.setCronExpression(sb.toString());
     trans.add(config, 1);
 
     ZDScheduleSchema schedule = new ZDScheduleSchema();
     schedule.setID(NoUtil.getMaxID("ScheduleID"));
     schedule.setSourceID(config.getID());
     schedule.setTypeCode("Publisher");
     schedule.setProp1("Config");
     schedule.setAddTime(new Date());
     schedule.setAddUser(User.getUserName());
     schedule.setCronExpression(config.getCronExpression());
     schedule.setPlanType(config.getPlanType());
     schedule.setStartTime(config.getStartTime());
     schedule.setIsUsing(config.getIsUsing());
     trans.add(schedule, 1);
     try
     {
       CronMonitor.getNextRunTime(schedule.getCronExpression());
     } catch (CronExpressionException e) {
       e.printStackTrace();
     }
   }
 
   public void save() {
     ZCSiteSchema site = new ZCSiteSchema();
     site.setValue(this.Request);
     site.fill();
     site.setValue(this.Request);
     site.setModifyUser(User.getUserName());
     site.setModifyTime(new Date());
     if (site.update()) {
       SiteUtil.update(site.getID());
       this.Response.setLogInfo(1, "保存成功");
       String indexFlag = site.getAutoIndexFlag();
       long siteID = site.getID();
       new Thread(siteID, indexFlag) { private final long val$siteID;
         private final String val$indexFlag;
 
         public void run() { FullText.dealAutoIndex(this.val$siteID, "Y".equals(this.val$indexFlag)); }
       }
       .start();
     } else {
       this.Response.setLogInfo(0, "保存失败");
     }
   }
 
   public void del() {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
       this.Response.setLogInfo(0, "传入ID时发生错误");
       return;
     }
     Transaction trans = new Transaction();
     ZCSiteSchema site = new ZCSiteSchema();
     ZCSiteSet set = site.query(new QueryBuilder("where id in (" + ids + ")"));
     trans.add(set, 5);
 
     delSiteRela(ids, trans);
 
     StringBuffer siteLog = new StringBuffer("删除站点:");
 
     for (int i = 0; i < set.size(); ++i) {
       site = set.get(i);
       ZCCatalogSchema catalog = new ZCCatalogSchema();
       catalog.setSiteID(site.getID());
       ZCCatalogSet catalogSet = catalog.query();
       trans.add(catalogSet, 5);
 
       ZCCatalogConfigSchema config = new ZCCatalogConfigSchema();
       config.setSiteID(site.getID());
       ZCCatalogConfigSet configSet = config.query();
       trans.add(configSet, 5);
 
       siteLog.append(site.getName() + ",");
 
       for (int j = 0; j < catalogSet.size(); ++j) {
         catalog = catalogSet.get(j);
         ZCArticleSchema article = new ZCArticleSchema();
         article.setCatalogID(catalog.getID());
         ZCArticleSet articleSet = article.query();
         trans.add(articleSet, 5);
 
         ZCImageSchema image = new ZCImageSchema();
         image.setCatalogID(catalog.getID());
         ZCImageSet imageSet = image.query();
         trans.add(imageSet, 5);
 
         ZCAudioSchema audio = new ZCAudioSchema();
         audio.setCatalogID(catalog.getID());
         ZCAudioSet audioSet = audio.query();
         trans.add(audioSet, 5);
 
         ZCVideoSchema video = new ZCVideoSchema();
         video.setCatalogID(catalog.getID());
         ZCVideoSet videoSet = video.query();
         trans.add(videoSet, 5);
       }
     }
 
     if (trans.commit()) {
       SiteUtil.update(site.getID());
 
       if (ids.indexOf(Application.getCurrentSiteID()) >= 0) {
         DataTable dt = new QueryBuilder("select name,id from zcsite order by BranchInnerCode ,orderflag ,id")
           .executeDataTable();
         dt = dt.filter(new Filter() {
           public boolean filter(Object obj) {
             DataRow dr = (DataRow)obj;
             return Priv.getPriv(User.getUserName(), "site", dr.getString("ID"), "site_browse");
           }
         });
         if (dt.getRowCount() > 0)
           Application.setCurrentSiteID(dt.getString(0, 1));
         else {
           Application.setCurrentSiteID("");
         }
 
       }
 
       String sitePath = Config.getContextRealPath() + Config.getValue("Statical.TemplateDir") + "/" + 
         site.getAlias();
       FileUtil.delete(sitePath);
 
       UserLog.log("Site", "DelSite", siteLog + "成功", this.Request.getClientIP());
 
       this.Response.setLogInfo(1, "删除成功");
     }
     else {
       UserLog.log("Site", "DelSite", siteLog + "失败", this.Request.getClientIP());
       this.Response.setLogInfo(0, "删除失败");
     }
   }
 
   public void delSiteRela(String siteIDs, Transaction trans)
   {
     if (!(StringUtil.checkID(siteIDs))) {
       return;
     }
     String[] tables = SiteTableRela.getSiteIDTables();
     for (int i = 0; i < tables.length; ++i) {
       deleteTableData(tables[i], siteIDs, trans);
     }
     SiteTableRela.TableRela[] trs = SiteTableRela.getRelas();
     for (int i = 0; i < trs.length; ++i) {
       SiteTableRela.TableRela tr = trs[i];
       if (tr.isExportData)
         deleteRelaTableData(tr.TableCode, tr.KeyField, tr.RelaTable, tr.RelaField, siteIDs, trans);
     }
   }
 
   public void deleteTableData(String tableName, String siteIDs, Transaction trans)
   {
     String backupNO = String.valueOf(System.currentTimeMillis()).substring(1, 11);
     String backupOperator = User.getUserName();
     String backupTime = DateUtil.toString(new Date(), "yyyy-MM-dd HH:mm:ss");
     String backup = "insert into b" + tableName + " select " + tableName + ".*,'" + backupNO + "','" + 
       backupOperator + "','" + backupTime + "',null from " + tableName + " where SiteID in(" + siteIDs + 
       ")";
     String delete = "delete from " + tableName + " where SiteID in (" + siteIDs + ")";
     trans.add(new QueryBuilder(backup));
     trans.add(new QueryBuilder(delete));
   }
 
   public void deleteRelaTableData(String tableName, String foreinKey, String relaTableName, String relaField, String siteIDs, Transaction trans)
   {
     String wherePart = " where exists(select '' from " + relaTableName + " where " + relaField + "=" + tableName + 
       "." + foreinKey + " and SiteID in (" + siteIDs + "))";
     String backupNO = String.valueOf(System.currentTimeMillis()).substring(1, 11);
     String backupOperator = User.getUserName();
     String backupTime = DateUtil.toString(new Date(), "yyyy-MM-dd HH:mm:ss");
     String backup = "insert into b" + tableName + " select " + tableName + ".*,'" + backupNO + "','" + 
       backupOperator + "','" + backupTime + "',null from " + tableName + wherePart;
     String delete = "delete from " + tableName + wherePart;
     trans.add(new QueryBuilder(backup));
     trans.add(new QueryBuilder(delete));
   }
 
   public static void uploadSite(HttpServletRequest request, HttpServletResponse response)
   {
     try
     {
       DiskFileItemFactory fileFactory = new DiskFileItemFactory();
       ServletFileUpload fu = new ServletFileUpload(fileFactory);
       List fileItems = fu.parseRequest(request);
       fu.setHeaderEncoding("UTF-8");
       Iterator iter = fileItems.iterator();
       while (iter.hasNext()) {
         FileItem item = (FileItem)iter.next();
         if (!(item.isFormField())) {
           String OldFileName = item.getName();
           LogUtil.info("Upload Site FileName:" + OldFileName);
           long size = item.getSize();
           if ((((OldFileName == null) || (OldFileName.equals("")))) && (size == 0L)) {
             continue;
           }
           OldFileName = OldFileName.substring(OldFileName.lastIndexOf("\\") + 1);
           String ext = OldFileName.substring(OldFileName.lastIndexOf("."));
           if (!(ext.toLowerCase().equals(".dat"))) {
             response.sendRedirect("SiteImportStep1.jsp?Error=1");
             return;
           }
           String FileName = "SiteUpload_" + DateUtil.getCurrentDate("yyyyMMddHHmmss") + ".dat";
           String Path = Config.getContextRealPath() + "WEB-INF/data/backup/";
           item.write(new File(Path + FileName));
           response.sendRedirect("SiteImportStep2.jsp?FileName=" + FileName);
         }
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public void importStep2DataBind(DataGridAction dga)
   {
     String sql = "select * from ZCSite order by orderflag ";
     dga.setTotal(new QueryBuilder("select count(*) from ZCSite"));
     DataTable dt = new QueryBuilder(sql).executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
     dt.insertColumn("Type");
     for (int i = 0; i < dt.getRowCount(); ++i) {
       dt.set(i, "Type", "<font color='red'>替换站点</font>");
     }
     if (dga.getPageIndex() == 0) {
       dt.insertRow(null, 0);
       String Path = Config.getContextRealPath() + "WEB-INF/data/backup/";
       Mapx map = new Mapx();
       try {
         map = SiteImporter.getSiteInfo(Path + $V("FileName"));
       } catch (Exception e) {
         e.printStackTrace();
       }
       dt.set(0, "ID", "0");
       dt.set(0, "Type", "<font color='green'>新建站点</font>");
       dt.set(0, "Name", "<input class='inputText' style='width:150px' id='Name' value='" + map.getString("Name") + 
         "'>");
       dt.set(0, "Alias", "<input class='inputText' style='width:100px' id='Alias' value='" + 
         map.getString("Alias") + "'>");
       dt.set(0, "URL", "<input class='inputText' style='width:250px' id='URL' value='" + map.getString("URL") + 
         "'>");
     }
     dga.bindData(dt);
   }
 
   public void checkImportSite()
   {
     String ID = $V("ID");
     String Name = $V("Name");
     String Alias = $V("Alias");
     QueryBuilder qb = new QueryBuilder("select count(1) from ZCSite where Alias=? and ID<>?", Alias, ID);
     if (qb.executeInt() != 0) {
       this.Response.setError("别名 <font color='red'>" + Alias + "</font> 已经被占用，请修改!");
       return;
     }
     qb = new QueryBuilder("select count(1) from ZCSite where Name=? and ID<>?", Name, ID);
     if (qb.executeInt() != 0) {
       this.Response.setError("站点名称 <font color='red'>" + Name + "</font> 已经被占用，请修改称!");
       return;
     }
     LongTimeTask ltt = LongTimeTask.getInstanceByType("SiteImport");
     if (ltt != null) {
       this.Response.setError("目前有站点导入任务正在运行中，请等待！");
       return;
     }
     String Path = Config.getContextRealPath() + "WEB-INF/data/backup/" + $V("FileName");
     Mapx map = this.Request;
     ltt = new LongTimeTask(Path, map) { private final String val$Path;
       private final Mapx val$map;
 
       public void execute() { SiteImporter si = new SiteImporter(this.val$Path, this);
         si.setSiteInfo(this.val$map);
         si.importSite();
         setPercent(100);
       }
     };
     ltt.setType("SiteImport");
     ltt.setUser(User.getCurrent());
     ltt.start();
     $S("TaskID", ltt.getTaskID());
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.site.Site
 * JD-Core Version:    0.5.3
 */