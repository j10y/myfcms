 package com.zving.cms.dataservice;
 
 import com.zving.framework.Page;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import java.util.Date;
 
 public class PublishColumn extends Page
 {
   public static Mapx initSearch(Mapx params)
   {
     String dateStr = DateUtil.toString(new Date(), "yyyy-MM-dd");
     Mapx map = new Mapx();
     map.put("today", dateStr);
     return map;
   }
 
   public static void dg1DataBind(DataGridAction dga)
   {
     StringBuffer condition = new StringBuffer("");
     String startDate = dga.getParam("startDate");
     String endDate = dga.getParam("endDate");
     if ((StringUtil.isNotEmpty(startDate)) && (StringUtil.isNotEmpty(endDate))) {
       endDate = DateUtil.toString(DateUtil.addDay(DateUtil.parse(endDate, "yyyy-MM-dd"), 1), "yyyy-MM-dd");
       condition.append(" and AddTime>'" + startDate + "' and AddTime<'" + endDate + "'");
     }
 
     String sqlData = "select a.Name as CatalogName,a.ID,a.InnerCode,a.TreeLevel,a.SingleFlag,a.OrderFlag,b.*  from zccatalog a left outer join  (select CatalogID,CatalogInnerCode,sum(1) as ArticleCount, sum(case when Status in (4,5) then 1 else 0 end) as PublishCount,  sum(case when Status =0 then 1 else 0 end) as DraftCount,  sum(case when Status =3 then 1 else 0 end) as ToSignCount,  sum(case when Status =5 then 1 else 0 end) as OfflineCount from zcarticle where SiteID=? and Type='1' " + 
       condition + " group by CatalogID,CatalogInnerCode) b" + 
       " on a.ID=b.CatalogID where a.SiteID=? and a.Type not in (4,5,6,7)";
     String sqlTotal = "select count(*) from zccatalog where SiteID=? and Type not in (4,5,6,7)";
     DataTable dt = new QueryBuilder(sqlData + condition + "  order by InnerCode", 
       Application.getCurrentSiteID(), Application.getCurrentSiteID()).executeDataTable();
     statDataTable(dt);
     dt.insertColumn("Padding");
     for (int i = 0; i < dt.getRowCount(); ++i) {
       String treelevel = dt.getString(i, "TreeLevel");
       int level = Integer.parseInt(treelevel);
       dt.set(i, "Padding", (level - 1) * 20);
     }
 
     int total = new QueryBuilder(sqlTotal, Application.getCurrentSiteID()).executeInt();
     dga.setTotal(total);
     dga.bindData(dt);
   }
 
   public static void statDataTable(DataTable dt)
   {
     if (dt == null) {
       return;
     }
     int rowCount = dt.getRowCount();
     for (int i = 0; i < rowCount; ++i) {
       String articleCount = (dt.get(i, "ArticleCount") == null) ? null : dt.getString(i, "ArticleCount");
       String publishCount = (dt.get(i, "PublishCount") == null) ? null : dt.getString(i, "PublishCount");
 
       String DraftCount = (dt.get(i, "DraftCount") == null) ? null : dt.getString(i, "DraftCount");
       String ToSignCount = (dt.get(i, "ToSignCount") == null) ? null : dt.getString(i, "ToSignCount");
       String OfflineCount = (dt.get(i, "OfflineCount") == null) ? null : dt.getString(i, "OfflineCount");
       String[] arr = getArticleCount(dt, i);
       int columnArticleCount = Integer.parseInt(arr[0]);
       int columnPublishCount = Integer.parseInt(arr[1]);
 
       int columnDraftCount = Integer.parseInt(arr[2]);
       int columnToSignCount = Integer.parseInt(arr[3]);
       int columnOfflineCount = Integer.parseInt(arr[4]);
       if (StringUtil.isEmpty(articleCount)) {
         dt.set(i, "ArticleCount", columnArticleCount);
       }
       if (StringUtil.isEmpty(publishCount)) {
         dt.set(i, "PublishCount", columnPublishCount);
       }
 
       if (StringUtil.isEmpty(DraftCount)) {
         dt.set(i, "DraftCount", columnDraftCount);
       }
       if (StringUtil.isEmpty(ToSignCount)) {
         dt.set(i, "ToSignCount", columnToSignCount);
       }
       if (StringUtil.isEmpty(OfflineCount))
         dt.set(i, "OfflineCount", columnOfflineCount);
     }
   }
 
   public static String[] getArticleCount(DataTable dt, int index)
   {
     if (dt == null) {
       return new String[] { "0", "0" };
     }
     int columnArticleCount = 0;
     int columnPublishCount = 0;
 
     int columnDraftCount = 0;
     int columnToSignCount = 0;
     int columnOfflineCount = 0;
 
     int rowCount = dt.getRowCount();
     String innerCode = dt.getString(index, "InnerCode");
     for (int i = index + 1; i < rowCount; ++i) {
       String code = dt.getString(i, "InnerCode");
       String articleCount = (dt.get(i, "ArticleCount") == null) ? null : dt.getString(i, "ArticleCount");
       String publishCount = (dt.get(i, "PublishCount") == null) ? null : dt.getString(i, "PublishCount");
       String DraftCount = (dt.get(i, "DraftCount") == null) ? null : dt.getString(i, "DraftCount");
       String ToSignCount = (dt.get(i, "ToSignCount") == null) ? null : dt.getString(i, "ToSignCount");
       String OfflineCount = (dt.get(i, "OfflineCount") == null) ? null : dt.getString(i, "OfflineCount");
 
       if (!(code.startsWith(innerCode)))
         break;
       int count;
       if (StringUtil.isNotEmpty(articleCount)) {
         count = Integer.parseInt(articleCount);
         columnArticleCount += count;
       }
       if (StringUtil.isNotEmpty(publishCount)) {
         count = Integer.parseInt(publishCount);
         columnPublishCount += count;
       }
 
       if (StringUtil.isNotEmpty(DraftCount)) {
         count = Integer.parseInt(DraftCount);
         columnDraftCount += count;
       }
       if (StringUtil.isNotEmpty(ToSignCount)) {
         count = Integer.parseInt(ToSignCount);
         columnToSignCount += count;
       }
       if (StringUtil.isNotEmpty(OfflineCount)) {
         count = Integer.parseInt(OfflineCount);
         columnOfflineCount += count;
       }
 
     }
 
     return new String[] { String.valueOf(columnArticleCount)
    		 , String.valueOf(columnPublishCount)
    		 , String.valueOf(columnDraftCount)
    		 , String.valueOf(columnToSignCount)
    		 , String.valueOf(columnOfflineCount) };
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.dataservice.PublishColumn
 * JD-Core Version:    0.5.3
 */