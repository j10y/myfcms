 package com.zving.cms.stat.report;
 
 import com.zving.cms.dataservice.Advertise;
 import com.zving.cms.pub.CatalogUtil;
 import com.zving.framework.Page;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 
 public class TopReport extends Page
 {
   public static void dgArticleDataBind(DataGridAction dga)
   {
     String id = dga.getParam("ID");
     String code = null;
     if (StringUtil.isEmpty(id))
       code = "";
     else {
       code = CatalogUtil.getInnerCode(id);
     }
     dga.bindData(getTopArticleHitData(code, dga.getPageSize(), dga.getPageIndex()));
   }
 
   public static void dgImageDataBind(DataGridAction dga) {
     String id = dga.getParam("ID");
     String code = null;
     if (StringUtil.isEmpty(id))
       code = "";
     else {
       code = CatalogUtil.getInnerCode(id);
     }
     dga.bindData(getTopImageHitData(code, dga.getPageSize(), dga.getPageIndex()));
   }
 
   public static void dgVideoDataBind(DataGridAction dga) {
     String id = dga.getParam("ID");
     String code = null;
     if (StringUtil.isEmpty(id))
       code = "";
     else {
       code = CatalogUtil.getInnerCode(id);
     }
     dga.bindData(getTopVideoHitData(code, dga.getPageSize(), dga.getPageIndex()));
   }
 
   public static void dgADDataBind(DataGridAction dga) {
     dga.bindData(getTopADData());
   }
 
   public static DataTable getTopArticleHitData(String catalogInnerCode, int pageSize, int pageIndex)
   {
     QueryBuilder qb = 
       new QueryBuilder("select ID,CatalogInnerCode,Title,HitCount,StickTime,AddUser from ZCArticle where SiteID=? and CatalogInnerCode like '" + 
       catalogInnerCode + "%' order by HitCount desc", Application.getCurrentSiteID());
     DataTable dt = qb.executePagedDataTable(pageSize, pageIndex);
     dt.insertColumn("CatalogInnerCodeName");
     for (int i = 0; i < dt.getRowCount(); ++i) {
       dt.set(i, "CatalogInnerCodeName", CatalogUtil.getNameByInnerCode(dt.getString(i, "CatalogInnerCode")));
     }
     return dt;
   }
 
   public static DataTable getTopImageHitData(String catalogInnerCode, int pageSize, int pageIndex)
   {
     QueryBuilder qb = 
       new QueryBuilder("select ID,CatalogInnerCode,Name,HitCount,StickTime,AddUser from ZCImage where SiteID=? and CatalogInnerCode like '" + 
       catalogInnerCode + "%' order by HitCount desc", Application.getCurrentSiteID());
     DataTable dt = qb.executePagedDataTable(pageSize, pageIndex);
     dt.insertColumn("CatalogInnerCodeName");
     for (int i = 0; i < dt.getRowCount(); ++i) {
       dt.set(i, "CatalogInnerCodeName", CatalogUtil.getNameByInnerCode(dt.getString(i, "CatalogInnerCode")));
     }
     return dt;
   }
 
   public static DataTable getTopVideoHitData(String catalogInnerCode, int pageSize, int pageIndex)
   {
     QueryBuilder qb = 
       new QueryBuilder("select ID,CatalogInnerCode,Name,HitCount,StickTime,AddUser from ZCVideo where SiteID=? and CatalogInnerCode like '" + 
       catalogInnerCode + "%' order by HitCount desc", Application.getCurrentSiteID());
     DataTable dt = qb.executePagedDataTable(pageSize, pageIndex);
     dt.insertColumn("CatalogInnerCodeName");
     for (int i = 0; i < dt.getRowCount(); ++i) {
       dt.set(i, "CatalogInnerCodeName", CatalogUtil.getNameByInnerCode(dt.getString(i, "CatalogInnerCode")));
     }
     return dt;
   }
 
   public static DataTable getTopADData()
   {
     QueryBuilder qb = 
       new QueryBuilder("select ID,PositionID,ADType,ADName,HitCount from ZCAdvertisement where SiteID=? order by HitCount desc");
     qb.add(Application.getCurrentSiteID());
     DataTable dt = qb.executeDataTable();
     if (dt.getRowCount() > 0) {
       dt.decodeColumn("ADType", Advertise.ADTypes);
       Mapx map = 
         new QueryBuilder("select ID,PositionName from ZCADPosition where SiteID=?", Application.getCurrentSiteID()).executeDataTable().toMapx(0, 1);
       dt.decodeColumn("PositionID", map);
     }
     return dt;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.report.TopReport
 * JD-Core Version:    0.5.3
 */