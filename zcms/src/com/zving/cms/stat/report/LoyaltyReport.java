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
 
 public class LoyaltyReport extends Page
 {
   public void getVisitDepthChartData()
   {
     Date start = DateUtil.parse($V("StartDate"));
     Date end = DateUtil.parse($V("EndDate"));
     DataTable dt = getVisitDepthData(Application.getCurrentSiteID(), start, end);
     dt.sort("VisitDepth");
     ReportUtil.prepareForPie3D(dt, 12, 1.0D);
     ReportUtil.addSuffix(dt, "Item", "页");
     $S("Data", ChartUtil.getPie3DChart(dt, 12, 1.0D));
   }
 
   public static void dgVisitDepthDataBind(DataGridAction dga) {
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
     DataTable dt = getVisitDepthData(Application.getCurrentSiteID(), start, end);
     ReportUtil.computeRate(dt, "VisitDepth", "Rate");
     dt.sort("Item", "desc", true);
     ReportUtil.addSuffix(dt, "Rate", "%");
     ReportUtil.addTrend(dt, "Global", "VisitDepth");
     ReportUtil.addSuffix(dt, "Item", "页");
     dga.bindData(dt);
   }
 
   public void getVisitFreqChartData() {
     Date start = DateUtil.parse($V("StartDate"));
     Date end = DateUtil.parse($V("EndDate"));
     DataTable dt = getVisitFreqData(Application.getCurrentSiteID(), start, end);
     dt.sort("Item");
     ReportUtil.prepareForPie3D(dt, 12, 1.0D);
     ReportUtil.addSuffix(dt, "Item", "次");
     $S("Data", ChartUtil.getPie3DChart(dt, 12, 1.0D));
   }
 
   public static void dgVisitFreqDataBind(DataGridAction dga) {
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
     DataTable dt = getVisitFreqData(Application.getCurrentSiteID(), start, end);
     ReportUtil.computeRate(dt, "VisitFreq", "Rate");
     dt.sort("Item", "asc", true);
     ReportUtil.addSuffix(dt, "Rate", "%");
     ReportUtil.addTrend(dt, "Global", "VisitFreq");
     ReportUtil.addSuffix(dt, "Item", "次");
     dga.bindData(dt);
   }
 
   public void getOnlineVisitorChartData() {
     Date start = DateUtil.parse($V("StartDate"));
     Date end = DateUtil.parse($V("EndDate"));
     DataTable dt = getOnlineVisitorData(Application.getCurrentSiteID(), start, end);
     dt.sort("Item");
     String xml = ChartUtil.getLine2DChart(dt, 24);
     $S("Data", xml);
   }
 
   public static void dgOnlineVisitorDataBind(DataGridAction dga) {
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
     DataTable dt = getOnlineVisitorData(Application.getCurrentSiteID(), start, end);
     dt.sort("Item");
     for (int i = 0; i < dt.getRowCount(); ++i) {
       String str = dt.getString(i, "Item");
       int item = Integer.parseInt(str);
       str = item + ":00 — " + (item + 1) + ":00";
       dt.set(i, "Item", str);
     }
     dga.bindData(dt);
   }
 
   public void getReturningRateChartData() {
     Date start = DateUtil.parse($V("StartDate"));
     Date end = DateUtil.parse($V("EndDate"));
     DataTable dt = getReturningData(Application.getCurrentSiteID(), start, end);
     dt.deleteRow(0);
     dt.sort("Date");
     String xml = ChartUtil.getMixed2DChart(dt, "ReturningRate", 8);
     $S("Data", xml);
   }
 
   public static void dgReturningRateDataBind(DataGridAction dga) {
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
     DataTable dt = getReturningData(Application.getCurrentSiteID(), start, end);
     ReportUtil.addSuffix(dt, "ReturningRate", "%");
     dga.bindData(dt);
   }
 
   public void getStickTimeChartData() {
     Date start = DateUtil.parse($V("StartDate"));
     Date end = DateUtil.parse($V("EndDate"));
     DataTable dt = getSticktimeData(Application.getCurrentSiteID(), start, end);
     dt.deleteRow(0);
     dt.sort("Date");
     String xml = ChartUtil.getLine2DChart(dt, 8);
     $S("Data", xml);
   }
 
   public static void dgStickTimeDataBind(DataGridAction dga) {
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
     DataTable dt = getSticktimeData(Application.getCurrentSiteID(), start, end);
     dt.deleteRow(0);
     dga.bindData(dt);
   }
 
   public static DataTable getVisitDepthData(long siteID, Date start, Date end)
   {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type=? and SubType='VisitDepth' and Period>=? and Period<=?");
     qb.add(siteID);
     qb.add("Global");
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     dt = ReportUtil.toItemTable(dt, start, end, true);
     return dt;
   }
 
   public static DataTable getVisitFreqData(long siteID, Date start, Date end)
   {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type=? and SubType='VisitFreq' and Period>=? and Period<=?");
     qb.add(siteID);
     qb.add("Global");
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     dt = ReportUtil.toItemTable(dt, start, end, true);
     return dt;
   }
 
   public static DataTable getReturningData(long siteID, Date start, Date end)
   {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type=? and SubType in ('NewVisitor','ReturnVisitor') and  Period>=? and Period<=? order by Period desc");
     qb.add(siteID);
     qb.add("Global");
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     dt = ReportUtil.toDateTable(dt, start, end);
     dt.insertColumn("ReturningRate");
     for (int i = 0; i < dt.getRowCount(); ++i) {
       int nv = 0; int rv = 0;
       if (StringUtil.isNotEmpty(dt.getString(i, "NewVisitor"))) {
         nv = Integer.parseInt(dt.getString(i, "NewVisitor"));
       }
       if (StringUtil.isNotEmpty(dt.getString(i, "ReturnVisitor"))) {
         rv = Integer.parseInt(dt.getString(i, "ReturnVisitor"));
       }
       if (nv + rv == 0)
         dt.set(i, "ReturningRate", 0);
       else {
         dt.set(i, "ReturningRate", new Double(NumberUtil.round(rv * 100.0D / (rv + nv), 2)));
       }
     }
     return dt;
   }
 
   public static DataTable getSticktimeData(long siteID, Date start, Date end)
   {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type=? and SubType in ('StickTotalTime','StickTime') and Period>=? and Period<=? order by Period desc");
     qb.add(siteID);
     qb.add("Global");
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     return ReportUtil.toDateTable(dt, start, end);
   }
 
   public static DataTable getOnlineVisitorData(long siteID, Date start, Date end)
   {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type=? and SubType in ('15Min','10Min','5Min') and Period>=? and Period<=? order by Period desc");
     qb.add(siteID);
     qb.add("Hour");
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     return ReportUtil.toItemTable(dt, start, end);
   }
 
   public static void main(String[] args) {
     Date start = new Date(System.currentTimeMillis() - 2592000000L);
     Date end = new Date();
     System.out.println(getSticktimeData(206L, start, end));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.report.LoyaltyReport
 * JD-Core Version:    0.5.3
 */