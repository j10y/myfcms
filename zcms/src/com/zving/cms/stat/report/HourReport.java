 package com.zving.cms.stat.report;
 
 import com.zving.framework.Page;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import java.io.PrintStream;
 import java.util.Date;
 
 public class HourReport extends Page
 {
   public void getChartData()
   {
     Date start = DateUtil.parse($V("StartDate"));
     Date end = DateUtil.parse($V("EndDate"));
     DataTable dt = getHourData(Application.getCurrentSiteID(), start, end);
     dt.deleteColumn("NewVisitor");
     dt.deleteColumn("ReturnVisitor");
     dt.deleteColumn("StickTime");
     dt.sort("Item");
     String xml = ChartUtil.getLine2DChart(dt, 24);
     $S("Data", xml);
   }
 
   public static void dg1DataBind(DataGridAction dga) {
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
     DataTable dt = getHourData(Application.getCurrentSiteID(), start, end);
     dt.sort("Item");
     for (int i = 0; i < dt.getRowCount(); ++i) {
       String str = dt.getString(i, "Item");
       int item = Integer.parseInt(str);
       str = item + ":00 — " + (item + 1) + ":00";
       dt.set(i, "Item", str);
     }
     if (dt.getRowCount() > 0) {
       ReportUtil.computeRate(dt, "PV", "Rate");
       ReportUtil.addSuffix(dt, "Rate", "%");
       ReportUtil.addTotal(dt, new String[] { "PV", "UV", "IP", "ReturnVisitor" });
       dt.set(0, "Rate", "100.00%");
     }
     dga.bindData(dt);
   }
 
   public static DataTable getHourData(long siteID, Date start, Date end) {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = 
       new QueryBuilder("select * from ZCStatItem where SiteID=? and Type='Hour' and SubType in ('PV','IP','UV','ReturnVisitor') and Period>=? and Period<=?");
     qb.add(siteID);
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     return ReportUtil.toItemTable(dt, start, end);
   }
 
   public static void main(String[] args) {
     Date start = new Date(System.currentTimeMillis() - 2592000000L);
     Date end = new Date();
     System.out.println(getHourData(206L, start, end));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.report.HourReport
 * JD-Core Version:    0.5.3
 */