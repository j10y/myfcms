 package com.zving.cms.stat.report;
 
 import com.zving.cms.stat.VisitHandler;
 import com.zving.framework.Page;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.controls.TreeAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.NumberUtil;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import java.util.Date;
 
 public class SummaryReport extends Page
 {
   public static Mapx init(Mapx params)
   {
     String startDate = DateUtil.toString(ReportUtil.getStartStatDate(Application.getCurrentSiteID()));
     int totalDays = ReportUtil.getTotalStatDays(Application.getCurrentSiteID());
     params.put("StartDate", "<font color='red'>" + startDate + "</font>");
     params.put("TotalDays", "<font color='red'>" + totalDays + "</font>");
     return params;
   }
 
   public static void treeDataBind(TreeAction ta) {
     DataTable dt = new DataTable();
     dt.insertColumn("ID");
     dt.insertColumn("ParentID");
     dt.insertColumn("Name");
     dt.insertColumn("Target");
 
     dt.insertRow(new Object[] { "1", "", "综合报告", "Summary.jsp" });
     dt.insertRow(new Object[] { "2", "", "最近访问记录", "LastVisit.jsp" });
     dt.insertRow(new Object[] { "3", "", "访问量分析", "" });
     dt.insertRow(new Object[] { "4", "3", "全站点击量", "Site.jsp" });
     dt.insertRow(new Object[] { "5", "3", "栏目点击排行", "Catalog.jsp" });
     dt.insertRow(new Object[] { "6", "3", "文章点击排行", "Article.jsp" });
     dt.insertRow(new Object[] { "7", "3", "图片点击排行", "Image.jsp" });
     dt.insertRow(new Object[] { "8", "3", "视频点击排行", "Video.jsp" });
     dt.insertRow(new Object[] { "9", "3", "广告点击排行", "AD.jsp" });
     dt.insertRow(new Object[] { "9", "3", "URL点击排行", "URL.jsp" });
     dt.insertRow(new Object[] { "19", "", "时段分布", "Hour.jsp" });
     dt.insertRow(new Object[] { "19", "", "入口分析", "Entrance.jsp" });
     dt.insertRow(new Object[] { "19", "", "出口分析", "Exit.jsp" });
     dt.insertRow(new Object[] { "19", "", "被访主机分析", "Host.jsp" });
     dt.insertRow(new Object[] { "19", "", "区域分布", "District.jsp" });
     dt.insertRow(new Object[] { "19", "", "在线人数", "OnlineVisitor.jsp" });
     dt.insertRow(new Object[] { "20", "", "忠诚度分析", "" });
     dt.insertRow(new Object[] { "21", "20", "访问深度", "VisitDepth.jsp" });
     dt.insertRow(new Object[] { "21", "20", "访问频度", "VisitFreq.jsp" });
     dt.insertRow(new Object[] { "22", "20", "回头率", "ReturningRate.jsp" });
     dt.insertRow(new Object[] { "24", "20", "停留时间", "StickTime.jsp" });
     dt.insertRow(new Object[] { "25", "", "点击量来源 ", "" });
     dt.insertRow(new Object[] { "26", "25", "来源组成", "Source.jsp" });
     dt.insertRow(new Object[] { "27", "25", "搜索引擎 ", "SearchEngine.jsp" });
     dt.insertRow(new Object[] { "28", "25", "来源网站 ", "Referer.jsp" });
     dt.insertRow(new Object[] { "29", "25", "关键字分析", "Keyword.jsp" });
     dt.insertRow(new Object[] { "10", "", "客户端情况", "" });
     dt.insertRow(new Object[] { "11", "10", "操作系统", "OS.jsp" });
     dt.insertRow(new Object[] { "12", "10", "浏览器", "Browser.jsp" });
     dt.insertRow(new Object[] { "13", "10", "语言", "Language.jsp" });
     dt.insertRow(new Object[] { "14", "10", "屏幕分辨率", "Screen.jsp" });
     dt.insertRow(new Object[] { "15", "10", "屏幕色深", "ColorDepth.jsp" });
     dt.insertRow(new Object[] { "16", "10", "是否支持Applet", "JavaEnabled.jsp" });
     dt.insertRow(new Object[] { "17", "10", "Flash版本", "FlashVersion.jsp" });
     dt.insertRow(new Object[] { "18", "10", "是否允许Cookie", "CookieEnabled.jsp" });
 
     ta.setRootText("统计分析");
     ta.bindData(dt);
   }
 
   public static void dg1DataBind(DataGridAction dga) {
     dga.bindData(getSummaryTable(Application.getCurrentSiteID()));
   }
 
   public static void dgSiteDataBind(DataGridAction dga) {
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
     DataTable dt = getDayHitData(Application.getCurrentSiteID(), start, end);
     dga.bindData(dt);
   }
 
   public void getChartData() {
     Date start = DateUtil.parse($V("StartDate"));
     Date end = DateUtil.parse($V("EndDate"));
     DataTable dt = getDayHitData(Application.getCurrentSiteID(), start, end);
     dt.deleteRow(0);
     dt.deleteColumn("ReturnVisitor");
     dt.sort("Date");
     String xml = ChartUtil.getLine2DChart(dt, 8);
     $S("Data", xml);
   }
 
   public static DataTable getDayHitData(long siteID, Date start, Date end)
   {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type=? and SubType in ('PV','UV','IP','ReturnVisitor') and Period>=? and Period<=? order by Period desc");
     qb.add(siteID);
     qb.add("Global");
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     return ReportUtil.toDateTable(dt, start, end);
   }
 
   public static DataTable getDateRangeTable(long siteID, String start, String end)
   {
     return getDayHitData(siteID, DateUtil.parse(start), DateUtil.parse(end));
   }
 
   public static DataTable getSummaryTable(long siteID)
   {
     VisitHandler.init(System.currentTimeMillis());
 
     DataTable dt = new DataTable();
     dt.insertColumn("Type");
     dt.insertColumn("PV");
     dt.insertColumn("UV");
     dt.insertColumn("IP");
     dt.insertColumn("ReturningRate");
     dt.insertColumn("StickTotalTime");
     dt.insertColumn("StickTime");
     dt.insertColumn("VisitDepth");
 
     int[] totals = new int[7];
     Object[] vs = new Object[dt.getColCount()];
 
     Date today = new Date();
     Date yesterday = DateUtil.addDay(today, -1);
 
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type='Global' order by period desc", siteID);
     DataTable src = qb.executeDataTable();
     src = ReportUtil.toDateTable(src, DateUtil.parse("1970-01-01"), today);
 
     String weekStart = null;
     String week = DateUtil.toString(today, "w");
     String monthPrefix = DateUtil.toString(today, "yyyy-MM");
     for (int i = 1; i < 7; ++i) {
       Date day = DateUtil.addDay(today, -i);
       if (!(week.equals(DateUtil.toString(day, "w")))) break;
       weekStart = DateUtil.toString(day);
     }
 
     int[] maxs = new int[3];
     String[] maxDates = new String[3];
     boolean weekFlag = false;
     boolean monthFlag = false;
     src.deleteRow(0);
 
     for (int i = 0; i < src.getRowCount(); ++i) {
       String period = src.getString(i, "Date");
 
       if ((!(monthFlag)) && (!(period.startsWith(monthPrefix)))) {
         vs = new Object[dt.getColCount()];
         vs[0] = "本月";
         vs[1] = new Integer(totals[0]);
         vs[2] = new Integer(totals[1]);
         vs[3] = new Integer(totals[2]);
         vs[4] = new Double(NumberUtil.round((totals[1] == 0) ? 0.0D : totals[3] * 100.0D / totals[1], 2));
         vs[5] = new Integer(totals[4]);
         vs[6] = new Integer(totals[5]);
         vs[7] = new Integer(totals[6]);
         dt.insertRow(vs);
         monthFlag = true;
       }
 
       int pv = Integer.parseInt(src.getString(i, "PV"));
       if (pv > maxs[0]) {
         maxs[0] = pv;
         maxDates[0] = period;
       }
       int uv = Integer.parseInt(src.getString(i, "UV"));
       if (uv > maxs[1]) {
         maxs[1] = uv;
         maxDates[1] = period;
       }
       int ip = Integer.parseInt(src.getString(i, "IP"));
       if (ip > maxs[2]) {
         maxs[2] = ip;
         maxDates[2] = period;
       }
       int returnVisitor = 0;
       if (StringUtil.isNotEmpty(src.getString(i, "ReturnVisitor"))) {
         returnVisitor = Integer.parseInt(src.getString(i, "ReturnVisitor"));
       }
       int stickTotalTime = 0;
       if (StringUtil.isNotEmpty(src.getString(i, "StickTotalTime"))) {
         stickTotalTime = Integer.parseInt(src.getString(i, "StickTotalTime"));
       }
       int stickTime = 0;
       if (StringUtil.isNotEmpty(src.getString(i, "StickTime"))) {
         stickTime = Integer.parseInt(src.getString(i, "StickTime"));
       }
       int visitDepth = 0;
       if (StringUtil.isNotEmpty(src.getString(i, "VisitDepth"))) {
         visitDepth = Integer.parseInt(src.getString(i, "VisitDepth"));
       }
 
       if (i == 0) {
         if (!(period.equals(DateUtil.toString(today)))) {
           vs = new Object[dt.getColCount()];
           vs[0] = "今日";
           vs[1] = new Integer(0);
           vs[2] = new Integer(0);
           vs[3] = new Integer(0);
           vs[4] = new Double(0.0D);
           vs[5] = new Integer(0);
           vs[6] = new Integer(0);
           vs[7] = new Integer(0);
           dt.insertRow(vs);
         } else {
           vs = new Object[dt.getColCount()];
           vs[0] = "今日";
           vs[1] = new Integer(pv);
           vs[2] = new Integer(uv);
           vs[3] = new Integer(ip);
           vs[4] = new Double(NumberUtil.round((uv == 0) ? 0.0D : returnVisitor * 100.0D / uv, 2));
           vs[5] = new Integer(stickTotalTime);
           vs[6] = new Integer(stickTime);
           vs[7] = new Integer(visitDepth);
           dt.insertRow(vs);
 
           int hour = Integer.parseInt(DateUtil.toString(today, "HH"));
           int minute = Integer.parseInt(DateUtil.toString(today, "mm"));
           qb = new QueryBuilder("select * from ZCStatItem where SiteID=? and Type='Hour' and period=?", 
             siteID, DateUtil.toString(yesterday, "yyyyMM"));
           DataTable dtHour = qb.executeDataTable();
           dtHour = ReportUtil.toItemTable(dtHour, yesterday, yesterday);
           dtHour.sort("Item");
           if ((hour != 0) && (dtHour.getRowCount() != 0)) {
             int gpv = 0; int guv = 0; int gip = 0;
             for (int j = 0; j < dtHour.getRowCount(); ++j) {
               int item = dtHour.getInt(j, "Item");
               if (item != hour) {
                 gpv += Integer.parseInt(dtHour.getString(j, "PV"));
                 guv += Integer.parseInt(dtHour.getString(j, "UV"));
                 gip += Integer.parseInt(dtHour.getString(j, "IP"));
               } else {
                 gpv += Integer.parseInt(dtHour.getString(j, "PV")) * minute / 60;
                 guv += Integer.parseInt(dtHour.getString(j, "UV")) * minute / 60;
                 gip += Integer.parseInt(dtHour.getString(j, "IP")) * minute / 60;
                 break;
               }
             }
 
             vs = new Object[dt.getColCount()];
             vs[0] = "昨日此时";
             vs[1] = new Integer(gpv);
             vs[2] = new Integer(guv);
             vs[3] = new Integer(gip);
             dt.insertRow(vs);
 
             int ypv = Integer.parseInt(src.getString(1, "PV"));
             int yuv = Integer.parseInt(src.getString(1, "UV"));
             int yip = Integer.parseInt(src.getString(1, "IP"));
 
             vs = new Object[dt.getColCount()];
             vs[0] = "今日预计";
             if (gpv != 0)
               vs[1] = new Integer(new Double(pv * 1.0D / gpv * ypv).intValue());
             else {
               vs[1] = new Integer(new Double(pv * 24 / hour).intValue());
             }
             if (guv != 0)
               vs[2] = new Integer(new Double(uv * 1.0D / guv * yuv).intValue());
             else {
               vs[2] = new Integer(new Double(uv * 24 / hour).intValue());
             }
             if (gip != 0)
               vs[3] = new Integer(new Double(ip * 1.0D / gip * yip).intValue());
             else {
               vs[3] = new Integer(new Double(ip * 24 / hour).intValue());
             }
             dt.insertRow(vs);
           }
         }
       }
 
       if (i == 1) {
         vs = new Object[dt.getColCount()];
         vs[0] = "昨日";
         vs[1] = new Integer(pv);
         vs[2] = new Integer(uv);
         vs[3] = new Integer(ip);
         vs[4] = new Double(NumberUtil.round((uv == 0) ? 0.0D : returnVisitor * 100.0D / uv, 2));
         vs[5] = new Integer(stickTotalTime);
         vs[6] = new Integer(stickTime);
         vs[7] = new Integer(visitDepth);
         dt.insertRow(vs);
       }
 
       totals[0] += pv;
       totals[1] += uv;
       totals[2] += ip;
       totals[3] += returnVisitor;
       if (uv + totals[1] == 0) {
         totals[4] = 0;
         totals[6] = 0;
       } else {
         totals[4] = ((stickTotalTime * uv + totals[4] * totals[1]) / (uv + totals[1]));
         totals[6] = ((visitDepth * uv + totals[6] * totals[1]) / (uv + totals[1]));
       }
       if (pv + totals[0] == 0)
         totals[5] = 0;
       else {
         totals[5] = ((stickTime * pv + totals[5] * totals[0]) / (pv + totals[0]));
       }
 
       if ((!(weekFlag)) && (period.equals(weekStart))) {
         vs = new Object[dt.getColCount()];
         vs[0] = "本周";
         vs[1] = new Integer(totals[0]);
         vs[2] = new Integer(totals[1]);
         vs[3] = new Integer(totals[2]);
         vs[4] = new Double(NumberUtil.round((totals[1] == 0) ? 0.0D : totals[3] * 100.0D / totals[1], 2));
         vs[5] = new Integer(totals[4]);
         vs[6] = new Integer(totals[5]);
         vs[7] = new Integer(totals[6]);
         dt.insertRow(vs);
         weekFlag = true;
       }
     }
 
     if (!(weekFlag)) {
       vs = new Object[dt.getColCount()];
       vs[0] = "本周";
       vs[1] = new Integer(totals[0]);
       vs[2] = new Integer(totals[1]);
       vs[3] = new Integer(totals[2]);
       vs[4] = new Double(NumberUtil.round((totals[1] == 0) ? 0.0D : totals[3] * 100.0D / totals[1], 2));
       vs[5] = new Integer(totals[4]);
       vs[6] = new Integer(totals[5]);
       vs[7] = new Integer(totals[6]);
       dt.insertRow(vs);
     }
 
     if (!(monthFlag)) {
       vs = new Object[dt.getColCount()];
       vs[0] = "本月";
       vs[1] = new Integer(totals[0]);
       vs[2] = new Integer(totals[1]);
       vs[3] = new Integer(totals[2]);
       vs[4] = new Double(NumberUtil.round((totals[1] == 0) ? 0.0D : totals[3] * 100.0D / totals[1], 2));
       vs[5] = new Integer(totals[4]);
       vs[6] = new Integer(totals[5]);
       vs[7] = new Integer(totals[6]);
       dt.insertRow(vs);
     }
 
     vs = new Object[dt.getColCount()];
     vs[0] = "全部";
     vs[1] = new Integer(totals[0]);
     vs[2] = new Integer(totals[1]);
     vs[3] = new Integer(totals[2]);
     vs[4] = new Double(NumberUtil.round((totals[1] == 0) ? 0.0D : totals[3] * 100.0D / totals[1], 2));
     vs[5] = new Integer(totals[4]);
     vs[6] = new Integer(totals[5]);
     vs[7] = new Integer(totals[6]);
     dt.insertRow(vs);
 
     int totalDyas = ReportUtil.getTotalStatDays(siteID);
     vs = new Object[dt.getColCount()];
     vs[0] = "平均";
     vs[1] = new Integer(totals[0] / totalDyas);
     vs[2] = new Integer(totals[1] / totalDyas);
     vs[3] = new Integer(totals[2] / totalDyas);
     dt.insertRow(vs);
 
     vs = new Object[dt.getColCount()];
     vs[0] = "最高";
     vs[1] = new Integer(maxs[0]);
     vs[2] = new Integer(maxs[1]);
     vs[3] = new Integer(maxs[2]);
     dt.insertRow(vs);
 
     vs = new Object[dt.getColCount()];
     vs[0] = "";
     vs[1] = "发生在:" + maxDates[0];
     vs[2] = "发生在:" + maxDates[1];
     vs[3] = "发生在:" + maxDates[2];
 
     dt.insertRow(vs);
 
     for (int i = 0; i < dt.getRowCount(); ++i) {
       String r = dt.getString(i, "ReturningRate");
       if (StringUtil.isNotEmpty(r)) {
         dt.set(i, "ReturningRate", r + "%");
       }
     }
 
     return dt;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.report.SummaryReport
 * JD-Core Version:    0.5.3
 */