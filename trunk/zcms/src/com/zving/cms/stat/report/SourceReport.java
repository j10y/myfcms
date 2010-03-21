 package com.zving.cms.stat.report;
 
 import com.zving.framework.Page;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.NumberUtil;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import java.io.PrintStream;
 import java.util.Date;
 
 public class SourceReport extends Page
 {
   public void getHostChartData()
   {
     Date start = DateUtil.parse($V("StartDate"));
     Date end = DateUtil.parse($V("EndDate"));
     DataTable dt = getHostData(Application.getCurrentSiteID(), start, end);
     ReportUtil.prepareForPie3D(dt, 8);
     $S("Data", ChartUtil.getPie3DChart(dt));
   }
 
   public static void dgHostDataBind(DataGridAction dga) {
     String startDate = dga.getParam("StartDate");
     String endDate = dga.getParam("EndDate");
     Date start = null;
     Date end = null;
     if (StringUtil.isEmpty(startDate)) {
       start = new Date(System.currentTimeMillis() - 2592000000L);
       end = new Date();
     } else {
       start = DateUtil.parse(startDate);
       end = DateUtil.parse(endDate);
     }
     DataTable dt = getHostData(Application.getCurrentSiteID(), start, end);
     ReportUtil.computeRate(dt, "Host", "Rate");
     dt.sort("Rate");
     ReportUtil.addSuffix(dt, "Rate", "%");
     ReportUtil.addTrend(dt, "Source", "Host");
     dga.bindData(dt);
   }
 
   public void getSourceChartData() {
     Date start = DateUtil.parse($V("StartDate"));
     Date end = DateUtil.parse($V("EndDate"));
     DataTable src = getSourceData(Application.getCurrentSiteID(), start, end);
     DataTable dt = new DataTable();
     if (src.getRowCount() > 1) {
       dt.insertColumn("Item");
       dt.insertColumn("Count");
       dt.insertRow(new Object[] { "直接输入", src.getString(0, "Direct") });
       dt.insertRow(new Object[] { "搜索引擎", src.getString(0, "SearchEngine") });
       dt.insertRow(new Object[] { "其他网站", src.getString(0, "Referer") });
       ReportUtil.prepareForPie3D(dt, 8);
     }
     $S("Data", ChartUtil.getPie3DChart(dt));
   }
 
   public static void dgSourceDataBind(DataGridAction dga) {
     String startDate = dga.getParam("StartDate");
     String endDate = dga.getParam("EndDate");
     Date start = null;
     Date end = null;
     if (StringUtil.isEmpty(startDate)) {
       start = new Date(System.currentTimeMillis() - 2592000000L);
       end = new Date();
     } else {
       start = DateUtil.parse(startDate);
       end = DateUtil.parse(endDate);
     }
     DataTable dt = getSourceData(Application.getCurrentSiteID(), start, end);
     dga.bindData(dt);
   }
 
   public void getSearchEngineChartData() {
     Date start = DateUtil.parse($V("StartDate"));
     Date end = DateUtil.parse($V("EndDate"));
     DataTable dt = getSearchEngineSourceData(Application.getCurrentSiteID(), start, end);
     ReportUtil.prepareForPie3D(dt, 8);
     $S("Data", ChartUtil.getPie3DChart(dt));
   }
 
   public static void dgSearchEngineDataBind(DataGridAction dga) {
     String startDate = dga.getParam("StartDate");
     String endDate = dga.getParam("EndDate");
     Date start = null;
     Date end = null;
     if (StringUtil.isEmpty(startDate)) {
       start = new Date(System.currentTimeMillis() - 2592000000L);
       end = new Date();
     } else {
       start = DateUtil.parse(startDate);
       end = DateUtil.parse(endDate);
     }
     DataTable dt = getSearchEngineSourceData(Application.getCurrentSiteID(), start, end);
     ReportUtil.computeRate(dt, "SearchEngine", "Rate");
     dt.sort("Rate");
     ReportUtil.addSuffix(dt, "Rate", "%");
     ReportUtil.addTrend(dt, "Source", "SearchEngine");
     dga.bindData(dt);
   }
 
   public void getRefererChartData() {
     Date start = DateUtil.parse($V("StartDate"));
     Date end = DateUtil.parse($V("EndDate"));
     DataTable dt = getRefererSourceData(Application.getCurrentSiteID(), start, end);
     ReportUtil.prepareForPie3D(dt, 8);
     $S("Data", ChartUtil.getPie3DChart(dt));
   }
 
   public static void dgRefererDataBind(DataGridAction dga) {
     String startDate = dga.getParam("StartDate");
     String endDate = dga.getParam("EndDate");
     Date start = null;
     Date end = null;
     if (StringUtil.isEmpty(startDate)) {
       start = new Date(System.currentTimeMillis() - 2592000000L);
       end = new Date();
     } else {
       start = DateUtil.parse(startDate);
       end = DateUtil.parse(endDate);
     }
     DataTable dt = getRefererSourceData(Application.getCurrentSiteID(), start, end);
     ReportUtil.computeRate(dt, "Referer", "Rate");
     dt.sort("Rate");
     ReportUtil.addSuffix(dt, "Rate", "%");
     ReportUtil.addTrend(dt, "Source", "Referer");
     dga.bindData(dt);
   }
 
   public void getKeywordChartData() {
     Date start = DateUtil.parse($V("StartDate"));
     Date end = DateUtil.parse($V("EndDate"));
     DataTable dt = getKeywordData(Application.getCurrentSiteID(), start, end);
     ReportUtil.prepareForPie3D(dt, 8);
     $S("Data", ChartUtil.getPie3DChart(dt));
   }
 
   public static void dgKeywordDataBind(DataGridAction dga) {
     String startDate = dga.getParam("StartDate");
     String endDate = dga.getParam("EndDate");
     Date start = null;
     Date end = null;
     if (StringUtil.isEmpty(startDate)) {
       start = new Date(System.currentTimeMillis() - 2592000000L);
       end = new Date();
     } else {
       start = DateUtil.parse(startDate);
       end = DateUtil.parse(endDate);
     }
     DataTable dt = getKeywordData(Application.getCurrentSiteID(), start, end);
     dt.insertColumn("Rate");
     int total = 0;
     for (int i = 0; i < dt.getRowCount(); ++i) {
       total += Integer.parseInt(dt.getString(i, 1));
     }
     for (i = 0; i < dt.getRowCount(); ++i) {
       int count = Integer.parseInt(dt.getString(i, 1));
       dt.set(i, "Rate", new Double(NumberUtil.round(count * 100.0D / total, 2)));
     }
     dt.sort("Rate");
     for (i = 0; i < dt.getRowCount(); ++i) {
       String r = dt.getString(i, "Rate");
       if (StringUtil.isNotEmpty(r)) {
         dt.set(i, "Rate", r + "%");
       }
     }
     ReportUtil.addTrend(dt, "Source", "Keyword");
     dga.bindData(dt);
   }
 
   public static DataTable getSourceData(long siteID, Date start, Date end)
   {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type='Source' and SubType in ('Direct','SearchEngine','Referer') and Period>=? and Period<=? order by Period desc");
     qb.add(siteID);
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     return ReportUtil.toDateTable(dt, start, end);
   }
 
   public static DataTable getDirectSourceData(long siteID, Date start, Date end)
   {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type='Source' and SubType='Direct' and Period>=? and Period<=? order by Period desc");
     qb.add(siteID);
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     return ReportUtil.toDateTable(dt, start, end);
   }
 
   public static DataTable getSearchEngineSourceData(long siteID, Date start, Date end)
   {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type='Source' and SubType='SearchEngine' and Period>=? and Period<=? order by Period desc");
     qb.add(siteID);
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     return ReportUtil.toItemTable(dt, start, end, true);
   }
 
   public static DataTable getRefererSourceData(long siteID, Date start, Date end)
   {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type='Source' and SubType='Referer' and Period>=? and Period<=? order by Period desc");
     qb.add(siteID);
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     return ReportUtil.toItemTable(dt, start, end, true);
   }
 
   public static DataTable getKeywordData(long siteID, Date start, Date end)
   {
     return getKeywordData(siteID, start, end, -1);
   }
 
   public static DataTable getKeywordData(long siteID, Date start, Date end, int count) {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type='Source' and SubType='Keyword' and Period>=? and Period<=? order by Period desc");
     qb.add(siteID);
     qb.add(period1);
     qb.add(period2);
 
     if (count < 1) {
       count = 2147483647;
     }
     DataTable dt = qb.executePagedDataTable(count, 0);
     return ReportUtil.toItemTable(dt, start, end, true);
   }
 
   public static DataTable getHostData(long siteID, Date start, Date end)
   {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type='Source' and SubType='Host' and Period>=? and Period<=? order by Period desc");
     qb.add(siteID);
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     return ReportUtil.toItemTable(dt, start, end, true);
   }
 
   public static void main(String[] args) {
     Date start = new Date(System.currentTimeMillis() - 2592000000L);
     Date end = new Date();
     System.out.println(getSourceData(206L, start, end));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.report.SourceReport
 * JD-Core Version:    0.5.3
 */