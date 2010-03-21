 package com.zving.cms.template;
 
 import com.zving.cms.dataservice.Advertise;
 import com.zving.cms.dataservice.ColumnUtil;
 import com.zving.cms.dataservice.CustomTableUtil;
 import com.zving.cms.dataservice.Form;
 import com.zving.cms.dataservice.Vote;
 import com.zving.cms.pub.CatalogUtil;
 import com.zving.cms.pub.PubFun;
 import com.zving.cms.pub.SiteUtil;
 import com.zving.cms.stat.report.SourceReport;
 import com.zving.framework.Config;
 import com.zving.framework.controls.HtmlP;
 import com.zving.framework.controls.TreeAction;
 import com.zving.framework.data.DataRow;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZCArticleSchema;
 import com.zving.schema.ZCArticleSet;
 import com.zving.schema.ZCCatalogSchema;
 import com.zving.schema.ZCCatalogSet;
 import com.zving.schema.ZCCustomTableSchema;
 import com.zving.schema.ZCCustomTableSet;
 import com.zving.schema.ZCImagePlayerSchema;
 import com.zving.schema.ZCImagePlayerSet;
 import com.zving.schema.ZCSiteSchema;
 import com.zving.schema.ZCVoteSchema;
 import com.zving.schema.ZCVoteSet;
 import com.zving.statical.TemplateData;
 import java.io.File;
 import java.lang.reflect.Method;
 import java.util.Date;
 
 public class CmsTemplateData extends TemplateData
 {
   private static final String SEPARATE = "|";
   private DataRow block;
   private DataRow doc;
   private DataRow catalog;
   private DataRow site;
   private int level;
   private String levelStr;
 
   public void setSite(ZCSiteSchema site)
   {
     this.site = site.toDataRow();
   }
 
   public void setCatalog(ZCCatalogSchema catalog) {
     setCatalog(catalog.toDataRow());
   }
 
   public void setDoc(Schema schema) {
     this.doc = schema.toDataRow();
   }
 
   public DataRow getDoc() {
     return this.doc;
   }
 
   public void setDoc(DataRow doc) {
     this.doc = doc;
   }
 
   public DataRow getCatalog() {
     return this.catalog;
   }
 
   public void setCatalog(DataRow catalog) {
     this.catalog = catalog;
     ColumnUtil.extendCatalogColumnData(this.catalog, this.site.getLong("ID"), getLevelStr());
   }
 
   public DataRow getSite() {
     return this.site;
   }
 
   public void setSite(DataRow site) {
     this.site = site;
   }
 
   public DataTable getList(String item, String name, String displayLevel, String count) {
     return getCatalogList(item, "article", name, displayLevel, count, null);
   }
 
   public DataTable getList(String item, String name, String displayLevel, String count, String condition) {
     return getCatalogList(item, "article", name, displayLevel, count, condition);
   }
 
   public DataTable getCatalogList(String item, String catalogType, String name, String displayLevel, String count, String condition)
   {
     DataTable dt = null;
 
     String key = item + "_" + catalogType + "_" + displayLevel + "_" + name + "_" + count + "_" + condition;
     dt = TemplateCache.getDataTable(key);
     if (dt != null) {
       return dt;
     }
 
     if ((count == null) || ("".equals(count)) || ("null".equals(count))) {
       count = "20";
     }
     int pageSize = Integer.parseInt(count);
     if ("catalog".equalsIgnoreCase(item)) {
       String typeSql = null;
       if ("article".equals(catalogType))
         typeSql = " and type =1";
       else if ("image".equals(catalogType))
         typeSql = " and type =4";
       else if ("video".equals(catalogType))
         typeSql = " and type =5";
       else if ("audio".equals(catalogType))
         typeSql = " and type =6";
       else if ("attachment".equals(catalogType))
         typeSql = " and type =7";
       else if ("magazine".equals(catalogType))
         typeSql = " and type =3";
       else if ("goods".equals(catalogType))
         typeSql = " and type =9";
       else if ("brand".equals(catalogType))
         typeSql = " and type =10";
       else {
         typeSql = " and type =1";
       }
 
       String parentID = null;
 
       if ((StringUtil.isNotEmpty(name)) && (!("null".equalsIgnoreCase(name))) && 
         (this.site != null)) {
         if (name.indexOf("/") != -1)
           parentID = CatalogUtil.getIDByNames(this.site.getString("ID"), name);
         else {
           parentID = CatalogUtil.getIDByName(this.site.getString("ID"), name);
         }
 
         if ((StringUtil.isEmpty(parentID)) && (StringUtil.isDigit(name))) {
           parentID = name;
         }
 
       }
 
       String parentSql = "";
       if ("root".equalsIgnoreCase(displayLevel)) {
         if (StringUtil.isEmpty(parentID)) {
           parentID = "0";
         }
         parentSql = " and parentid=" + parentID;
       } else if ("current".equalsIgnoreCase(displayLevel)) {
         if ((StringUtil.isEmpty(parentID)) && 
           (this.catalog != null)) {
           parentID = this.catalog.getString("ParentID");
         }
 
         parentSql = " and parentid=" + parentID;
       } else if ("child".equalsIgnoreCase(displayLevel)) {
         if ((StringUtil.isEmpty(parentID)) && 
           (this.catalog != null))
         {
           if ("1".equals(this.catalog.getString("isLeaf")))
             parentID = this.catalog.getString("ParentID");
           else {
             parentID = this.catalog.getString("ID");
           }
         }
 
         parentSql = " and parentid=" + parentID;
       } else if ("all".equalsIgnoreCase(displayLevel)) {
         if ((StringUtil.isEmpty(parentID)) && 
           (this.catalog != null))
         {
           if ("1".equals(this.catalog.getString("isLeaf")))
             parentID = this.catalog.getString("ParentID");
           else {
             parentID = this.catalog.getString("ID");
           }
         }
 
         parentSql = " and innercode like '" + CatalogUtil.getInnerCode(parentID) + "%'";
       } else {
         if ((StringUtil.isEmpty(parentID)) && 
           (this.catalog != null))
         {
           if ("1".equals(this.catalog.getString("isLeaf")))
             parentID = this.catalog.getString("ParentID");
           else {
             parentID = this.catalog.getString("ID");
           }
         }
 
         parentSql = " and parentid=" + parentID;
       }
 
       String sql = "";
       if (StringUtil.isEmpty(parentID)) {
         return null;
       }
       sql = "select * from zccatalog where siteID=? " + parentSql + typeSql + " Order by orderflag,id ";
       QueryBuilder qb = new QueryBuilder(sql);
       qb.add(this.site.getString("ID"));
       dt = qb.executePagedDataTable(pageSize, 0);
 
       String levelString = getLevelStr();
       dt.insertColumn("Link");
       for (int i = 0; i < dt.getRowCount(); ++i) {
         dt.set(i, "ImagePath", levelString + dt.getString(i, "ImagePath"));
         String url = levelString + CatalogUtil.getPath(dt.getString(i, "ID"));
         if ((dt.getString(i, "url").startsWith("http")) || (dt.getString(i, "url").startsWith("/"))) {
           url = dt.getString(i, "url");
         }
         dt.set(i, "Link", url);
       }
       ColumnUtil.extendCatalogColumnData(dt, this.site.getLong("ID"), levelString);
     }
     TemplateCache.setDataTable(key, dt);
 
     return dt;
   }
 
   public DataTable getDocList(String item, String catalogName, String displayLevel, String type, String count) {
     return getDocList(item, catalogName, displayLevel, null, type, count, "");
   }
 
   public DataTable getDocList(String item, String catalogName, String type, String count) {
     return getDocList(item, catalogName, "all", null, type, count, "");
   }
 
   public DataTable getDocList(String item, String catalogName, String displayLevel, String orderType, String count, String condition)
   {
     return getDocList(item, catalogName, displayLevel, "", orderType, count, condition);
   }
 
   public DataTable getDocList(String item, String catalogName, String displayLevel, String hasAttribute, String orderType, String count, String condition)
   {
     DataTable dt = null;
     String key = item + "_" + catalogName + "_" + displayLevel + "_" + hasAttribute + "_" + orderType + "_" + count + 
       "_" + condition;
 
     if (!("relate".equalsIgnoreCase(orderType))) {
       dt = TemplateCache.getDataTable(key);
       if (dt != null) {
         return dt;
       }
     }
 
     if ((count == null) || ("".equals(count)) || ("null".equals(count))) {
       count = "50";
     }
 
     String typeStr = "";
     if ("article".equalsIgnoreCase(item)) {
       if (("relate".equalsIgnoreCase(orderType)) && (this.doc != null)) {
         String relateID = this.doc.getString("RelativeArticle");
         String keyword = this.doc.getString("keyword");
         if (StringUtil.isNotEmpty(relateID)) {
           typeStr = typeStr + " and id in (" + relateID + ") ";
         } else if ((keyword != null) && (!("".equalsIgnoreCase(keyword)))) {
           String[] keywordArr = keyword.split(",");
           for (int i = 0; i < keywordArr.length; ++i) {
             typeStr = typeStr + " and keyword like '%" + keywordArr[i] + "%'";
           }
         }
         typeStr = typeStr + " order by id desc,topflag desc,orderflag desc";
       } else if ("hitcount".equalsIgnoreCase(orderType)) {
         typeStr = typeStr + " order by hitcount desc,topflag desc,orderflag desc";
       } else if ("top".equalsIgnoreCase(orderType)) {
         typeStr = typeStr + " and topflag='1' order by orderflag desc";
       } else if ("recent".equalsIgnoreCase(orderType)) {
         typeStr = typeStr + " order by publishdate desc,orderflag desc, id desc";
       } else {
         typeStr = typeStr + " order by topflag desc,orderflag desc,publishdate desc, id desc";
       }
 
       if ((StringUtil.isNotEmpty(hasAttribute)) && (!("null".equals(hasAttribute)))) {
         String[] attribute = hasAttribute.split(",");
         if (attribute.length > 0) {
           String attributeSql = "";
           for (int i = 0; i < attribute.length; ++i) {
             String attr = attribute[i].trim();
             if (StringUtil.isNotEmpty(attr)) {
               attributeSql = attributeSql + " and attribute like '%" + attribute[i] + "%'";
             }
           }
           typeStr = attributeSql + typeStr;
         }
       }
     }
     else if ("recent".equalsIgnoreCase(orderType)) {
       typeStr = typeStr + " order by id desc";
     } else {
       typeStr = typeStr + " order by id desc";
     }
 
     String catalogID = null;
     if ((StringUtil.isNotEmpty(catalogName)) && (!("null".equalsIgnoreCase(catalogName)))) {
       if (catalogName.indexOf("/") != -1)
         catalogID = CatalogUtil.getIDByNames(this.site.getString("ID"), catalogName);
       else if (catalogName.indexOf(",") != -1)
         catalogID = CatalogUtil.getIDsByName(this.site.getString("ID"), catalogName);
       else {
         catalogID = CatalogUtil.getIDByName(this.site.getString("ID"), catalogName);
       }
 
       if ((StringUtil.isEmpty(catalogID)) && (StringUtil.isDigit(catalogName)))
         catalogID = catalogName;
     }
     else if ("relate".equalsIgnoreCase(orderType)) {
       displayLevel = "root";
     } else {
       if (this.catalog != null) {
         catalogID = this.catalog.getString("ID");
       }
       if (this.doc != null) {
         catalogID = this.doc.getString("CatalogID");
       }
 
     }
 
     if ((!("root".equalsIgnoreCase(displayLevel))) && (StringUtil.isEmpty(catalogID))) {
       return null;
     }
 
     String parentInnerCode = "";
     if ((StringUtil.isNotEmpty(catalogID)) && (catalogID.indexOf(",") != -1)) {
       String[] catalogIDs = catalogID.split(",");
       for (int m = 0; m < catalogIDs.length; ++m) {
         parentInnerCode = parentInnerCode + CatalogUtil.getInnerCode(catalogIDs[m]) + ",";
       }
 
       if (StringUtil.isNotEmpty(parentInnerCode))
         parentInnerCode = parentInnerCode.substring(0, parentInnerCode.length() - 1);
     }
     else {
       parentInnerCode = CatalogUtil.getInnerCode(catalogID);
     }
     String catalogStr = " 1=1 ";
     QueryBuilder qb = new QueryBuilder();
 
     if ("root".equalsIgnoreCase(displayLevel)) {
       catalogStr = " siteid=?";
       qb.add(this.site.getLong("id"));
     } else if ("current".equalsIgnoreCase(displayLevel)) {
       if ((StringUtil.isNotEmpty(catalogID)) && (catalogID.indexOf(",") != -1)) {
         catalogStr = " catalogid in (";
         catalogStr = catalogStr + catalogID;
         catalogStr = catalogStr + ")";
       } else {
         catalogStr = " catalogid=?";
         qb.add(catalogID);
       }
     }
     else
     {
       String[] innercodes;
       int m;
       if ("child".equalsIgnoreCase(displayLevel)) {
         if ((StringUtil.isNotEmpty(catalogID)) && (catalogID.indexOf(",") != -1)) {
           catalogStr = " catalogid not in (";
           catalogStr = catalogStr + catalogID;
           catalogStr = catalogStr + ")";
           innercodes = parentInnerCode.split(",");
           for (m = 0; m < innercodes.length; ++m)
             if (m == 0) {
               catalogStr = catalogStr + " and (cataloginnercode like '";
               catalogStr = catalogStr + innercodes[m] + "%'";
             } else if (m == innercodes.length - 1) {
               catalogStr = catalogStr + " or cataloginnercode like '";
               catalogStr = catalogStr + innercodes[m] + "%')";
             } else {
               catalogStr = catalogStr + " or cataloginnercode like '";
               catalogStr = catalogStr + innercodes[m] + "%'";
             }
         }
         else {
           catalogStr = " catalogid <> ?";
           qb.add(catalogID);
           catalogStr = catalogStr + " and cataloginnercode like ?";
           qb.add(parentInnerCode + "%");
         }
       } else if ("all".equalsIgnoreCase(displayLevel)) {
         if ((StringUtil.isNotEmpty(catalogID)) && (catalogID.indexOf(",") != -1)) {
           innercodes = parentInnerCode.split(",");
           for (m = 0; m < innercodes.length; ++m)
             if (m == 0) {
               catalogStr = catalogStr + " and (cataloginnercode like '";
               catalogStr = catalogStr + innercodes[m] + "%'";
             } else if (m == innercodes.length - 1) {
               catalogStr = catalogStr + " or cataloginnercode like '";
               catalogStr = catalogStr + innercodes[m] + "%')";
             } else {
               catalogStr = catalogStr + " or cataloginnercode like '";
               catalogStr = catalogStr + innercodes[m] + "%'";
             }
         }
         else {
           catalogStr = catalogStr + " and cataloginnercode like ?";
           qb.add(parentInnerCode + "%");
         }
       }
       else if ((StringUtil.isNotEmpty(catalogID)) && (catalogID.indexOf(",") != -1)) {
         innercodes = parentInnerCode.split(",");
         for (m = 0; m < innercodes.length; ++m)
           if (m == 0) {
             catalogStr = catalogStr + " and (cataloginnercode like '";
             catalogStr = catalogStr + innercodes[m] + "%'";
           } else if (m == innercodes.length - 1) {
             catalogStr = catalogStr + " or cataloginnercode like '";
             catalogStr = catalogStr + innercodes[m] + "%')";
           } else {
             catalogStr = catalogStr + " or cataloginnercode like '";
             catalogStr = catalogStr + innercodes[m] + "%'";
           }
       }
       else {
         catalogStr = catalogStr + " and cataloginnercode like ?";
         qb.add(parentInnerCode + "%");
       }
     }
 
     String statusStr = "";
     if ("article".equals(item)) {
       String allowStatus = "20,30";
       if (catalogName.indexOf(",") == -1) {
         if ((StringUtil.isNotEmpty(catalogID)) && (StringUtil.isNotEmpty(CatalogUtil.getWorkflow(catalogID))))
           allowStatus = "20,30";
         else if ((StringUtil.isNotEmpty(catalogID)) && 
           (StringUtil.isNotEmpty(CatalogUtil.getAllowStatus(catalogID))))
           allowStatus = CatalogUtil.getAllowStatus(catalogID);
         else if ((StringUtil.isNotEmpty(this.site.getString("ID"))) && 
           (StringUtil.isNotEmpty(SiteUtil.getAllowStatus(this.site.getString("ID"))))) {
           allowStatus = SiteUtil.getAllowStatus(this.site.getString("ID"));
         }
       }
 
       statusStr = "status in(" + allowStatus + ") and publishdate<='" + DateUtil.getCurrentDateTime() + "' and";
     }
     if ((StringUtil.isEmpty(condition)) || ("null".equals(condition))) {
       condition = "1=1";
     }
     String sql = "select * from zc" + item + " where " + condition + " and " + statusStr + catalogStr + typeStr;
 
     int pageSize = Integer.parseInt(count);
     qb.setSQL(sql);
     dt = qb.executePagedDataTable(pageSize, 0);
 
     if (dt == null) {
       return null;
     }
 
     dt.insertColumn("ArticleLink");
     dt.insertColumn("_RowNo");
 
     String levelString = getLevelStr();
     String[] columnValue = new String[dt.getRowCount()];
     String[] catalogValue = new String[dt.getRowCount()];
     String[] catalogLink = new String[dt.getRowCount()];
     if (dt.getRowCount() > 0) {
       ColumnUtil.extendDocColumnData(dt, dt.getString(0, "catalogID"));
       dt.insertColumn("filePath");
     }
 
     for (int i = 0; i < dt.getRowCount(); ++i) {
       dt.set(i, "_RowNo", i + 1);
       String docID = dt.getString(i, "ID");
       String docCatalogID = dt.getString(i, "catalogID");
 
       catalogValue[i] = CatalogUtil.getName(docCatalogID);
       catalogLink[i] = levelString + CatalogUtil.getPath(docCatalogID);
       String nameRule;
       HtmlNameParser nameParser;
       HtmlNameRule h;
       if ("article".equalsIgnoreCase(item)) {
         if ("4".equals((String)dt.get(i, "Type"))) {
           columnValue[i] = ((String)dt.get(i, "RedirectURL"));
         } else {
           nameRule = CatalogUtil.getTemplateRule(docCatalogID);
           if (StringUtil.isNotEmpty(nameRule)) {
             nameParser = new HtmlNameParser(this.site, null, dt.get(i), nameRule);
             h = nameParser.getNameRule();
             columnValue[i] = levelString + h.getFullPath();
           } else {
             columnValue[i] = levelString + CatalogUtil.getPath(docCatalogID) + docID + ".shtml";
           }
 
           String imagePath = Config.getContextPath() + Config.getValue("UploadDir") + "/" + 
             SiteUtil.getAlias(this.site.getLong("ID")) + "/";
           imagePath = imagePath.replaceAll("///", "/");
           imagePath = imagePath.replaceAll("//", "/");
 
           dt.set(i, "Content", dt.getString(i, "Content").replaceAll(imagePath, 
             CatalogUtil.getLevelStr(docCatalogID)));
         }
       } else if ("image".equalsIgnoreCase(item))
       {
         DataTable imageRelaTable = new QueryBuilder("select a.id as imageID,b.id,b.catalogid,b.publishdate from zcimagerela a ,zcarticle b where a.relatype='ArticleImage' and a.relaid=b.id and a.id=?", 
           docID).executeDataTable();
         if ((imageRelaTable != null) && (imageRelaTable.getRowCount() > 0)) {
           String articleCatalogID = imageRelaTable.getString(0, "catalogid");
           String articleID = imageRelaTable.getString(0, "id");
           String nameRule = CatalogUtil.getTemplateRule(articleCatalogID);
           if (StringUtil.isNotEmpty(nameRule)) {
             HtmlNameParser nameParser = new HtmlNameParser(this.site, null, imageRelaTable.get(0), nameRule);
             HtmlNameRule h = nameParser.getNameRule();
             dt.set(i, "articlelink", levelString + h.getFullPath());
           } else {
             String path = levelString + CatalogUtil.getPath(articleCatalogID) + articleID + ".shtml";
             dt.set(i, "articlelink", path);
           }
         } else {
           dt.set(i, "articlelink", "#");
         }
 
         columnValue[i] = levelString + CatalogUtil.getPath(docCatalogID) + docID + ".shtml";
       } else if ("video".equalsIgnoreCase(item)) {
         int vCount = Integer.parseInt(dt.getString(i, "Count"));
         String path = "";
         for (int n = 0; n < vCount; ++n) {
           if (n != 0) {
             path = path + "|";
           }
           path = path + dt.getString(i, "path") + n + "_" + dt.getString(i, "FileName");
         }
         dt.set(i, "filePath", path);
         String nameRule = CatalogUtil.getTemplateRule(docCatalogID);
         if (StringUtil.isNotEmpty(nameRule)) {
           HtmlNameParser nameParser = new HtmlNameParser(this.site, null, dt.get(i), nameRule);
           HtmlNameRule h = nameParser.getNameRule();
           columnValue[i] = levelString + h.getFullPath();
         } else {
           columnValue[i] = levelString + CatalogUtil.getPath(docCatalogID) + docID + ".shtml";
         }
       } else if ("attachment".equalsIgnoreCase(item)) {
         columnValue[i] = Config.getValue("ServicesContext") + "/AttachDownLoad.jsp?id=" + docID;
       } else {
         nameRule = CatalogUtil.getTemplateRule(docCatalogID);
         if (StringUtil.isNotEmpty(nameRule)) {
           nameParser = new HtmlNameParser(this.site, null, dt.get(i), nameRule);
           h = nameParser.getNameRule();
           columnValue[i] = levelString + h.getFullPath();
         } else {
           columnValue[i] = levelString + CatalogUtil.getPath(docCatalogID) + docID + ".shtml";
         }
       }
     }
 
     dt.insertColumn("Link", columnValue);
     dt.insertColumn("CatalogName", catalogValue);
     dt.insertColumn("CatalogLink", catalogLink);
 
     TemplateCache.setDataTable(key, dt);
     return dt;
   }
 
   public DataTable getMagazineList(String item, String name, String count) {
     return getMagazineList(item, null, name, count, null);
   }
 
   public DataTable getMagazineList(String item, String name, String count, String condition) {
     return getMagazineList(item, null, name, count, condition);
   }
 
   public DataTable getMagazineList(String item, String type, String name, String count, String condition)
   {
     DataTable dt = null;
 
     String key = item + "_" + type + "_" + name + "_" + count + "_" + condition;
     dt = TemplateCache.getDataTable(key);
     if (dt != null) {
       return dt;
     }
 
     if ((count == null) || ("".equals(count)) || ("null".equals(count))) {
       count = "20";
     }
     int pageSize = Integer.parseInt(count);
     if ("magazine".equalsIgnoreCase(item)) {
       String sql = "";
       QueryBuilder qb;
       if (StringUtil.isEmpty(name)) {
         sql = "select * from ZCMagazine where siteID=?";
         qb = new QueryBuilder(sql);
         qb.add(this.site.getString("ID"));
         dt = qb.executePagedDataTable(pageSize, 0);
       } else {
         sql = "select * from ZCMagazine where siteID=? and name=?";
         qb = new QueryBuilder(sql);
         qb.add(this.site.getString("ID"));
         qb.add(name);
         dt = qb.executePagedDataTable(pageSize, 0);
       }
     }
     TemplateCache.setDataTable(key, dt);
 
     return dt;
   }
 
   public DataTable getMagazineIssueList(String item, String name, String type, String count, String condition)
   {
     DataTable dt = null;
 
     String key = item + "_" + name + "_" + type + "_" + count + "_" + condition;
     dt = TemplateCache.getDataTable(key);
     if (dt != null) {
       return dt;
     }
 
     if ((count == null) || ("".equals(count)) || ("null".equals(count))) {
       count = "20";
     }
     String staticPath = Config.getContextPath() + Config.getValue("Statical.TargetDir") + "/" + 
       SiteUtil.getAlias(this.site.getLong("ID")) + "/";
     int pageSize = Integer.parseInt(count);
     if ("magazineissue".equalsIgnoreCase(item)) {
       String sql = "";
       QueryBuilder qb = new QueryBuilder();
       if (StringUtil.isEmpty(name)) {
         sql = "select * from ZCMagazine where siteID=?";
         qb.setSQL(sql);
         qb.add(this.site.getString("ID"));
       } else {
         sql = "select * from ZCMagazine where siteID=? and name=?";
         qb.setSQL(sql);
         qb.add(this.site.getString("ID"));
         qb.add(name);
       }
       DataTable magazineDT = qb.executeDataTable();
 
       if (magazineDT.getRowCount() > 0) {
         sql = "select * from ZCMagazineIssue where MagazineID=?";
         String catalogID = "";
         if (this.catalog != null) {
           catalogID = this.catalog.getString("ID");
           String catalogName = CatalogUtil.getName(catalogID);
           int catalogType = (int)CatalogUtil.getCatalogType(catalogID);
           if ((catalogType == 3) && 
             (catalogName.indexOf("年第") != -1)) {
             String currentYear = catalogName.substring(0, catalogName.indexOf("年第"));
             String currentPeriodNum = catalogName.substring(catalogName.indexOf("年第") + 2, catalogName
               .indexOf("期"));
             sql = "select * from ZCMagazineIssue where MagazineID=? and Year='" + currentYear + 
               "' and PeriodNum='" + currentPeriodNum + "'";
           }
         }
 
         sql = sql + " order by ID desc";
 
         qb = new QueryBuilder(sql);
         qb.add(magazineDT.getString(0, "ID"));
         dt = qb.executePagedDataTable(pageSize, 0);
         dt.insertColumn("PrevLink");
         dt.insertColumn("NextLink");
         dt.insertColumn("Link");
 
         if (dt.getRowCount() > 0) {
           for (int i = 0; i < dt.getRowCount() - 1; ++i) {
             String prevCatalogID = CatalogUtil.getIDByName(this.site.getString("ID"), dt.getString(i, "Year") + 
               "年第" + dt.getString(i, "PeriodNum") + "期");
             dt.set(i, "PrevLink", staticPath + CatalogUtil.getPath(prevCatalogID));
           }
           String nextCatalogID;
           for (i = 1; i < dt.getRowCount(); ++i) {
             nextCatalogID = CatalogUtil.getIDByName(this.site.getString("ID"), dt
               .getString(i - 1, "Year") + 
               "年第" + dt.getString(i - 1, "PeriodNum") + "期");
             dt.set(0, "NextLink", staticPath + CatalogUtil.getPath(nextCatalogID));
           }
 
           DataTable magazineIssueDT = new QueryBuilder(
             "select * from ZCMagazineIssue where MagazineID=? and ID>? order by id", magazineDT
             .getString(0, "ID"), dt.getString(0, "ID")).executeDataTable();
           if (magazineIssueDT.getRowCount() > 0) {
             nextCatalogID = CatalogUtil.getIDByName(this.site.getString("ID"), magazineIssueDT.getString(
               0, "Year") + 
               "年第" + magazineIssueDT.getString(0, "PeriodNum") + "期");
             dt.set(0, "NextLink", staticPath + CatalogUtil.getPath(nextCatalogID));
           } else {
             dt.set(0, "NextLink", "#");
           }
 
           magazineIssueDT = new QueryBuilder(
             "select * from ZCMagazineIssue where MagazineID=? and ID<? order by id desc", magazineDT
             .getString(0, "ID"), dt.getString(0, "ID")).executeDataTable();
           if (magazineIssueDT.getRowCount() > 0) {
             nextCatalogID = CatalogUtil.getIDByName(this.site.getString("ID"), magazineIssueDT.getString(
               0, "Year") + 
               "年第" + magazineIssueDT.getString(0, "PeriodNum") + "期");
             dt.set(dt.getRowCount() - 1, "PrevLink", staticPath + CatalogUtil.getPath(nextCatalogID));
           } else {
             dt.set(dt.getRowCount() - 1, "PrevLink", "#");
           }
 
           for (int m = 0; m < dt.getRowCount(); ++m) {
             String ID = CatalogUtil.getIDByName(this.site.getString("ID"), dt.getString(m, "Year") + "年第" + 
               dt.getString(m, "PeriodNum") + "期");
             dt.set(m, "Link", staticPath + CatalogUtil.getPath(ID));
           }
           break label1149:
         }
 
         return null;
       }
 
       return null;
     }
 
     label1149: TemplateCache.setDataTable(key, dt);
 
     return dt;
   }
 
   public DataTable getFriendLinkList(String item, String group, String count, String condition)
   {
     DataTable dt = new DataTable();
     if ((count == null) || ("".equalsIgnoreCase(count))) {
       count = "20";
     }
     String sql = "select * from zclink where siteID = " + this.site.getString("ID") + " and LinkGroupID = " + 
       "(select id from zclinkgroup where siteID = " + this.site.getString("ID") + 
       " and name=?) order by OrderFlag,id desc";
 
     int pageSize = Integer.parseInt(count);
     dt = new QueryBuilder(sql, group).executePagedDataTable(pageSize, 0);
     return dt;
   }
 
   public DataTable getBlockList(String item, String count, String condition)
   {
     DataTable dt = new DataTable();
     if ((count == null) || ("".equalsIgnoreCase(count))) {
       count = "20";
     }
     String blockid = this.block.getString("ID");
     String blockType = this.block.getString("Type");
     String sortField = this.block.getString("SortField");
     String catalogID = this.block.getString("CatalogID");
     String siteID = this.block.getString("SiteID");
 
     String sql = "";
     QueryBuilder qb = new QueryBuilder();
     if ("1".equalsIgnoreCase(blockType)) {
       if ((catalogID != null) && (!("".equalsIgnoreCase(catalogID)))) {
         sql = "select * from zcarticle where catalogid = ? order by " + sortField + " desc";
         qb.add(catalogID);
       } else {
         sql = "select * from zcarticle where exists(select id from zccatalog where siteid = ? and id=zcarticle.catalogid) order by " + 
           sortField + " desc";
         qb.add(siteID);
       }
     } else if ("2".equalsIgnoreCase(blockType)) {
       sql = "select * from zcpageblockitem where blockid = ? order by id";
       qb.add(blockid);
     }
     qb.setSQL(sql);
 
     int pageSize = Integer.parseInt(count);
     dt = qb.executePagedDataTable(pageSize, 0);
 
     String levelString = "";
     for (int i = 0; i < this.level; ++i) {
       levelString = levelString + "../";
     }
 
     int size = dt.getRowCount();
     String[] columnValue = new String[dt.getRowCount()];
     for (int i = 0; i < size; ++i) {
       if ("1".equalsIgnoreCase(blockType)) {
         long articleID = ((Long)dt.get(i, "ID")).longValue();
         long catalog = ((Long)dt.get(i, "catalogID")).longValue();
         String publishDate = DateUtil.toString((Date)dt.get(i, "PublishDate"));
         columnValue[i] = CatalogUtil.getPath(catalog) + publishDate.substring(0, publishDate.lastIndexOf("-")) + 
           "/" + publishDate.substring(publishDate.lastIndexOf("-") + 1) + "/" + articleID + ".shtml";
       } else if ("2".equalsIgnoreCase(blockType)) {
         columnValue[i] = ((String)dt.get(i, "URL"));
       }
     }
 
     dt.insertColumn("Link", columnValue);
     return dt;
   }
 
   public String getTree(String id, String method, String tagBody, String style, String levelStr) {
     TreeAction ta = new TreeAction();
 
     ta.setTagBody(tagBody);
     ta.setMethod(method);
     ta.setID(id);
 
     int level = Integer.parseInt(levelStr);
     if (level <= 0) {
       level = 999;
     }
     ta.setLevel(level);
     ta.setStyle(style);
 
     HtmlP p = new HtmlP();
     try {
       p.parseHtml(ta.getTagBody());
 
       ta.setTemplate(p);
 
       int index = method.lastIndexOf(46);
       String className = method.substring(0, index);
       method = method.substring(index + 1);
       Class c = Class.forName(className);
       Method m = c.getMethod(method, new Class[] { TreeAction.class });
       m.invoke(null, new Object[] { ta });
     }
     catch (Exception e) {
       e.printStackTrace();
     }
 
     return ta.getHtml();
   }
 
   public String getAD(String name) {
     StringBuffer sb = new StringBuffer();
     if ((StringUtil.isEmpty(name)) || (name == null)) {
       sb.append("广告的代码为空，请修改模板");
     } else {
       DataTable positionDT = new QueryBuilder("select * from zcadposition where PositionName = ? and siteid=?", 
         name, this.site.getString("ID")).executeDataTable();
       if (positionDT.getRowCount() <= 0) {
         sb.append("广告版位代码：" + name + "没有找到");
       } else {
         String jsName = positionDT.getString(0, "jsname");
         sb.append("<script language='javascript' src='" + getLevelStr() + jsName + "'></script>");
 
         if (!(new File(new StringBuffer(String.valueOf(Config.getContextRealPath())).append(Config.getValue("Statical.TargetDir")).append("/")
           .append(positionDT.getString(0, "SiteID")).append("/").toString().replaceAll("//", "/") + 
           positionDT.getString(0, "ID") + ".js").exists())) {
           Advertise.CreateJSCode(positionDT.getString(0, "ID"));
         }
       }
     }
     return sb.toString();
   }
 
   public String getForm(String code) {
     StringBuffer sb = new StringBuffer();
     if (StringUtil.isEmpty(code)) {
       sb.append("表单ID为空，请修改模板");
     } else {
       ZCCustomTableSet set = new ZCCustomTableSchema().query(new QueryBuilder("where Code=?", code));
       if ((code == null) || (set.size() <= 0)) {
         sb.append("表单：" + code + "没有找到");
       } else {
         String parseContent = Form.getRuntimeFormContent(set.get(0));
         sb.append(parseContent);
         sb.append("</form>");
       }
     }
     return sb.toString();
   }
 
   public String getVote(String name, String id)
   {
     StringBuffer sb = new StringBuffer();
     ZCVoteSchema vote = new ZCVoteSchema();
     QueryBuilder qb = null;
     if ((StringUtil.isNotEmpty(name)) && (!("null".equals(name))))
       qb = new QueryBuilder(" where title=? and siteid=?", name, this.site.getString("ID"));
     else if ((StringUtil.isNotEmpty(id)) && (StringUtil.isDigit(id)))
     {
       qb = new QueryBuilder(" where title=? and siteid=?", id, this.site.getString("ID"));
     }
     else return "<font color=red>没有找到对应的调查。</font>";
 
     ZCVoteSet set = vote.query(qb);
     if (set.size() < 1) {
       return "<font color=red>没有找到对应的调查。</font>";
     }
     vote = set.get(0);
     sb.append("<!--" + vote.getTitle() + "-->\n");
     sb.append("<script language='javascript' src='" + 
       new StringBuffer(String.valueOf(getLevelStr())).append("js/vote_").append(vote.getID()).toString().replaceAll("//", "/") + ".js'></script>");
 
     if (!(new File(new StringBuffer(String.valueOf(Config.getContextRealPath())).append(Config.getValue("Statical.TargetDir")).append("/")
       .append(SiteUtil.getAlias(vote.getSiteID())).append("/js/").toString().replaceAll("//", "/") + 
       "vote_" + vote.getID() + ".js").exists())) {
       Vote.generateJS(vote.getID());
     }
     return sb.toString();
   }
 
   public DataRow getVoteData(String name, String id) {
     ZCVoteSchema vote = new ZCVoteSchema();
     QueryBuilder qb = null;
     if ((StringUtil.isNotEmpty(name)) && (!("null".equals(name))))
       qb = new QueryBuilder(" where title=? and siteid=?", name, this.site.getString("ID"));
     else if ((StringUtil.isNotEmpty(id)) && (StringUtil.isDigit(id)))
       qb = new QueryBuilder(" where id=? and siteid=?", id, this.site.getString("ID"));
     else {
       return null;
     }
 
     ZCVoteSet set = vote.query(qb);
     if (set.size() < 1) {
       return null;
     }
     vote = set.get(0);
     return vote.toDataRow();
   }
 
   public DataTable getVoteSubjects(String voteID, int count) {
     QueryBuilder qb = new QueryBuilder("select * from zcvoteSubject where VoteID = ? order by ID", voteID);
     return qb.executePagedDataTable(count, 0);
   }
 
   public DataTable getVoteItems(String subjectID, String inputType, int count) {
     QueryBuilder qb = new QueryBuilder("select * from zcvoteitem where subjectID = ? order by ID", subjectID);
     DataTable dt = qb.executePagedDataTable(count, 0);
     dt.insertColumn("html");
 
     if ("Y".equals(inputType))
       inputType = "checkbox";
     else {
       inputType = "radio";
     }
     for (int i = 0; i < dt.getRowCount(); ++i) {
       String html = "";
       if ("0".equals(dt.getString(i, "ItemType")))
         html = "<label><input name='Subject_" + subjectID + "' type='" + inputType + "' value='" + 
           dt.getString(i, "id") + "' />" + dt.getString(i, "item") + "</label>\n";
       else {
         html = "<label><input name='Subject_" + subjectID + "' type='" + inputType + "' value='" + 
           dt.getString(i, "id") + "' id='Subject_" + subjectID + "_Item_" + dt.getString(i, "id") + 
           "_Button'/>" + dt.getString(i, "item") + "</label><input id='Subject_" + subjectID + "_Item_" + 
           dt.getString(i, "id") + "' name='Subject_" + subjectID + "_Item_" + dt.getString(i, "id") + 
           "' type='text' value='' onClick=\\\"clickInput('Subject_" + subjectID + "_Item_" + 
           dt.getString(i, "id") + "');\\\"/>\n";
       }
       dt.set(i, "html", html);
     }
     return dt;
   }
 
   public String getComment(String count) {
     return "<script src=\"" + Config.getValue("ServicesContext") + Config.getValue("CommentListJS") + "?RelaID=" + 
       this.doc.getString("ID") + "&CatalogID=" + this.doc.getString("CatalogID") + "&CatalogType=" + 
       this.doc.getString("Type") + "&SiteID=" + this.site.getString("id") + "&Count=" + count + "\"></script>";
   }
 
   public String getImagePlayer(String name, String width, String height, String count)
   {
     StringBuffer sb = new StringBuffer();
     ZCImagePlayerSchema imagePlayer = new ZCImagePlayerSchema();
     imagePlayer.setName(name);
     imagePlayer.setSiteID(this.site.getString("id"));
     ZCImagePlayerSet set = imagePlayer.query();
     if (set.size() > 0) {
       imagePlayer = set.get(0);
     } else {
       imagePlayer = new ZCImagePlayerSchema();
       imagePlayer.setCode(name);
       imagePlayer.setSiteID(this.site.getString("id"));
       set = imagePlayer.query();
       if (set.size() > 0) {
         imagePlayer = set.get(0);
       } else {
         sb.append("没有" + name + "对应的图片播放器，请检查" + name + "是否正确。");
         return sb.toString();
       }
     }
     String levelStr = getLevelStr();
 
     QueryBuilder qb = new QueryBuilder("select b.* from ZCImageRela a,zcimage b where a.id = b.id  and a.RelaID=? and a.RelaType=? order by a.orderflag desc, a.addtime desc", 
       imagePlayer.getID(), 
       "ImagePlayerImage");
 
     int imageCount = 20;
     if (StringUtil.isDigit(count)) {
       imageCount = Integer.parseInt(count);
     }
 
     DataTable dt = qb.executePagedDataTable(imageCount, 0);
     StringBuffer pics = new StringBuffer();
     StringBuffer links = new StringBuffer();
     StringBuffer texts = new StringBuffer();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       if (i != 0) {
         pics.append("|");
         links.append("|");
         texts.append("|");
       }
       pics.append(levelStr + dt.getString(i, "path") + "1_" + dt.getString(i, "FileName"));
       if (StringUtil.isNotEmpty(levelStr + dt.getString(i, "LinkURL")))
         links.append(dt.getString(i, "LinkURL"));
       if (StringUtil.isNotEmpty(dt.getString(i, "LinkText"))) {
         texts.append(dt.getString(i, "LinkText"));
       }
     }
     int pWidth = 320; int pHeight = 240;
     if (!(StringUtil.isDigit(width)))
       pWidth = (int)imagePlayer.getWidth();
     else {
       pWidth = Integer.parseInt(width);
     }
 
     if (!(StringUtil.isDigit(height)))
       pHeight = (int)imagePlayer.getHeight();
     else {
       pHeight = Integer.parseInt(height);
     }
 
     int imgHeight = pHeight;
     if ("Y".equals(imagePlayer.getProp1())) {
       imgHeight = pHeight - 22;
     }
     String showText = "";
     if ("Y".equals(imagePlayer.getProp1()))
       showText = "&show_text=1&textheight=22";
     else {
       showText = "&show_text=0";
     }
 
     sb.append("<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" width=\"" + pWidth + "\" height=\"" + 
       pHeight + "\">");
     sb.append("<param name=\"movie\" value=\"" + getLevelStr() + "images/ImagePlayer.swf\">");
     sb.append("<param name=\"quality\" value=\"high\">");
     sb.append("<param name=\"wmode\" value=\"transparent\">");
     sb.append("<param name=\"FlashVars\" value=\"pics=" + pics.toString() + "&links=" + links.toString() + 
       "&texts=" + texts.toString() + "&borderwidth=" + pWidth + "&borderheight=" + imgHeight + showText + 
       "&overtxtcolor=FFFF00&txtcolor=FFFF00\"/>");
     sb.append("<embed src=\"" + getLevelStr() + 
       "images/ImagePlayer.swf\" type=\"application/x-shockwave-flash\" wmode=\"transparent\" " + 
       "FlashVars=\"wmode=transparent&pics=" + pics.toString() + "&links=" + links.toString() + "&texts=" + 
       texts.toString() + "&borderwidth=" + pWidth + "&borderheight=" + imgHeight + showText);
 
     sb.append("&button_pos=4&overtxtcolor=FFFF00&txtcolor=FFFF00\" width=\"" + pWidth + "\" height=\"" + pHeight + 
       "\">");
     sb.append("</embed>");
     sb.append("</object>");
 
     return sb.toString();
   }
 
   public String getLinkURL(String type, String code, String name, String spliter) {
     StringBuffer sb = new StringBuffer();
     String levelString;
     int i;
     if ("catalog".equalsIgnoreCase(type)) {
       levelString = "";
       for (i = 0; i < this.level; ++i)
         levelString = levelString + "../";
       ZCCatalogSchema catalog;
       ZCCatalogSet set;
       if ((code != null) && (!("".equals(code))) && (!("null".equals(code)))) {
         catalog = new ZCCatalogSchema();
         catalog.setAlias(code);
         catalog.setSiteID(Long.parseLong(this.site.getString("ID")));
         set = catalog.query();
         if (set.size() < 1) {
           return "#";
         }
         catalog = set.get(0);
         return levelString + CatalogUtil.getCatalogIndex(catalog.getID());
       }
       if ((name != null) && (!("".equals(name))) && (!("null".equals(name)))) {
         catalog = new ZCCatalogSchema();
         if (StringUtil.isDigit(name)) {
           catalog.setID(Long.parseLong(name));
         }
         else if (name.indexOf("/") != -1) {
           String id = CatalogUtil.getIDByNames(this.site.getString("ID"), name);
 
           if (StringUtil.isNotEmpty(id))
             catalog.setID(id);
           else
             catalog.setName(name);
         }
         else {
           catalog.setName(name);
         }
 
         catalog.setSiteID(Long.parseLong(this.site.getString("ID")));
         set = catalog.query();
         if (set.size() < 1) {
           return "#";
         }
         catalog = set.get(0);
         return levelString + CatalogUtil.getCatalogIndex(catalog.getID());
       }
     }
     else if ("article".equalsIgnoreCase(type)) {
       levelString = "";
       for (i = 0; i < this.level; ++i) {
         levelString = levelString + "../";
       }
       if ((name != null) && (!("".equals(name))) && (!("null".equals(name)))) {
         ZCArticleSchema article = new ZCArticleSchema();
         if (StringUtil.isDigit(name)) {
           article.setID(Long.parseLong(name));
         }
         else if (name.indexOf("@") != -1) {
           String title = name.substring(0, name.indexOf("@"));
           name = name.substring(name.indexOf("@") + 1);
           String catlaogID = null;
           if (name.indexOf("/") != -1)
             catlaogID = CatalogUtil.getIDByNames(this.site.getString("ID"), name);
           else {
             catlaogID = CatalogUtil.getIDByName(this.site.getString("ID"), name);
           }
           if (StringUtil.isNotEmpty(catlaogID)) {
             article.setCatalogID(catlaogID);
           }
           article.setTitle(title);
         } else {
           article.setTitle(name);
         }
 
         ZCArticleSet set = article.query();
         if ((set != null) && (set.size() > 0)) {
           article = set.get(0);
           sb.append(levelString + PubFun.getArticleURL(article.getID()));
         }
       }
     } else if ("CurrentPosition".equalsIgnoreCase(type)) {
       if ((spliter == null) || ("".equals(spliter)) || ("null".equals(spliter))) {
         spliter = ">";
       }
 
       Object obj = this.catalog.get("ID");
       long catalogID = (obj == null) ? 0L : ((Long)obj).longValue();
       sb.append(PubFun.getCurrentPage(catalogID, this.level, spliter, "_self"));
     } else if ("HomeURL".equalsIgnoreCase(type)) {
       sb.append("<a href='" + getLevelStr() + "'>首页</a>");
     }
     return sb.toString();
   }
 
   public DataRow getCatalog(String type, String name) {
     DataRow docRow = null;
     if ("catalog".equalsIgnoreCase(type)) {
       String levelString = "";
       for (int i = 0; i < this.level; ++i) {
         levelString = levelString + "../";
       }
       if ((name != null) && (!("".equals(name))) && (!("null".equals(name)))) {
         ZCCatalogSchema catalog = new ZCCatalogSchema();
         String id;
         if (name.indexOf("/") != -1)
           id = CatalogUtil.getIDByNames(this.site.getString("ID"), name);
         else {
           id = CatalogUtil.getIDByName(this.site.getString("ID"), name);
         }
 
         if ((StringUtil.isEmpty(id)) && (StringUtil.isDigit(name))) {
           id = name;
         }
         catalog.setID(Long.parseLong(id));
 
         if (!(catalog.fill())) {
           return null;
         }
         docRow = catalog.toDataRow();
         docRow.insertColumn("link", levelString + CatalogUtil.getPath(catalog.getID()));
       }
 
       ColumnUtil.extendCatalogColumnData(docRow, this.site.getLong("ID"), getLevelStr());
     }
     return docRow;
   }
 
   public DataRow getDocument(String type, String name) {
     DataRow docRow = null;
     if ("article".equalsIgnoreCase(type)) {
       String levelString = "";
       for (int i = 0; i < this.level; ++i) {
         levelString = levelString + "../";
       }
       if ((name != null) && (!("".equals(name))) && (!("null".equals(name)))) {
         ZCArticleSchema article = new ZCArticleSchema();
         if (StringUtil.isDigit(name)) {
           article.setID(Long.parseLong(name));
         }
         else if (name.indexOf("@") != -1) {
           String title = name.substring(0, name.indexOf("@"));
           name = name.substring(name.indexOf("@") + 1);
 
           String catlaogID = null;
           if (name.indexOf("/") != -1)
             catlaogID = CatalogUtil.getIDByNames(this.site.getString("ID"), name);
           else {
             catlaogID = CatalogUtil.getIDByName(this.site.getString("ID"), name);
           }
           if (StringUtil.isNotEmpty(catlaogID)) {
             article.setCatalogID(catlaogID);
           }
 
           article.setTitle(title);
         } else {
           article.setTitle(name);
         }
 
         ZCArticleSet set = article.query();
         if ((set != null) && (set.size() > 0)) {
           article = set.get(0);
           docRow = article.toDataRow();
           docRow.insertColumn("link", levelString + CatalogUtil.getPath(article.getCatalogID()) + 
             article.getID() + ".shtml");
         }
       }
     }
     return docRow;
   }
 
   public String getPage(String type, String value, String name, String target) {
     StringBuffer sb = new StringBuffer();
     if ("currentpage".equalsIgnoreCase(type)) {
       if ((value == null) || ("".equals(value)) || ("null".equals(value))) {
         value = ">";
       }
 
       if ((target == null) || ("".equals(target)) || ("null".equals(target))) {
         value = "_self";
       }
 
       Object obj = this.catalog.get("ID");
       long catalogID = (obj == null) ? 0L : ((Long)obj).longValue();
       sb.append(PubFun.getCurrentPage(catalogID, this.level, value, target));
     } else if ("index".equalsIgnoreCase(type)) {
       sb.append(getLevelStr() + "index.shtml");
     }
     return sb.toString();
   }
 
   public DataTable getPagedDataTable(DataTable dt, int type) {
     int count = this.PageSize;
     if ((this.PageIndex + 1) * this.PageSize > this.Total) {
       count = this.Total - (this.PageIndex * this.PageSize);
     }
     Object[][] values = new Object[count][dt.getColCount()];
     for (int i = 0; i < count; ++i) {
       values[i] = dt.getDataRow(this.PageIndex * this.PageSize + i).getDataValues();
     }
     return new DataTable(dt.getDataColumns(), values);
   }
 
   public String getLevelStr() {
     if (StringUtil.isEmpty(this.levelStr)) {
       StringBuffer sb = new StringBuffer();
       for (int i = 0; i < this.level; ++i) {
         sb.append("../");
       }
       this.levelStr = sb.toString();
     }
 
     return this.levelStr;
   }
 
   public DataTable getCustomData(String tableName, String CountStr, String condition) {
     int count = 20;
     if (StringUtil.isDigit(CountStr)) {
       count = Integer.parseInt(CountStr);
     }
     String wherePart = "where 1=1 ";
     if (StringUtil.isNotEmpty(condition)) {
       wherePart = wherePart + condition;
     }
     return CustomTableUtil.getData(tableName, new QueryBuilder(wherePart), count, 0);
   }
 
   public DataTable getKeywords(String type, String CountStr)
   {
     int count = 20;
     if (StringUtil.isDigit(CountStr)) {
       count = Integer.parseInt(CountStr);
     }
     if (StringUtil.isEmpty(type)) {
       type = type + "week";
     }
 
     Date today = new Date();
 
     int days = -1;
     if ("day".equals(type))
       days = -1;
     else if ("week".equals(type))
       days = -7;
     else if ("month".equals(type))
       days = -31;
     else if ("season".equals(type))
       days = -90;
     else if ("year".equals(type)) {
       days = -365;
     }
     Date startDate = DateUtil.addDay(today, days);
     return SourceReport.getKeywordData(this.site.getLong("id"), startDate, today, count);
   }
 
   public DataRow getBlock()
   {
     return this.block;
   }
 
   public void setBlock(DataRow block) {
     this.block = block;
   }
 
   public int getLevel() {
     return this.level;
   }
 
   public void setLevel(int level) {
     this.level = level;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.template.CmsTemplateData
 * JD-Core Version:    0.5.3
 */