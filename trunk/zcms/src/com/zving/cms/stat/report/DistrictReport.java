 package com.zving.cms.stat.report;
 
 import com.zving.framework.Page;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import java.util.Date;
 
 public class DistrictReport extends Page
 {
   public void getChartData()
   {
     String startDate = $V("StartDate");
     String endDate = $V("EndDate");
     Date start = null;
     Date end = null;
     if (StringUtil.isEmpty(startDate)) {
       start = new Date(System.currentTimeMillis() - 2592000000L);
       end = new Date();
     } else {
       start = DateUtil.parse(startDate);
       end = DateUtil.parse(endDate);
     }
     String code = $V("Code");
     String type = $V("Type");
     DataTable dt = null;
     if ("C".equals(type)) {
       dt = getCountryTable(Application.getCurrentSiteID(), start, end, false);
     }
     else if ((StringUtil.isEmpty(code)) || (code.equals("null")) || (code.equals("000000")))
       dt = getProvinceTable(Application.getCurrentSiteID(), start, end, false);
     else {
       dt = getCityTable(Application.getCurrentSiteID(), code, start, end, false);
     }
 
     dt.sort("District");
     dt.deleteColumn("Trend");
     String xml = ChartUtil.getPie3DChart(dt, 8, 1.0D);
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
     String code = dga.getParam("Code");
     String type = dga.getParam("Type");
     DataTable dt = null;
     if ("C".equals(type)) {
       dt = getCountryTable(Application.getCurrentSiteID(), start, end, true);
     }
     else if ((StringUtil.isEmpty(code)) || (code.equals("null")) || (code.equals("000000")))
       dt = getProvinceTable(Application.getCurrentSiteID(), start, end, true);
     else {
       dt = getCityTable(Application.getCurrentSiteID(), code, start, end, true);
     }
 
     ReportUtil.computeRate(dt, "District", "Rate");
     dt.sort("Rate");
     ReportUtil.addSuffix(dt, "Rate", "%");
     dga.bindData(dt);
   }
 
   public static DataTable getDistrictTable(long siteID, Date start, Date end)
   {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     QueryBuilder qb = new QueryBuilder(
       "select * from ZCStatItem where SiteID=? and Type='Client' and SubType='District' and Period>=? and Period<=?");
     qb.add(siteID);
     qb.add(period1);
     qb.add(period2);
     DataTable dt = qb.executeDataTable();
     dt = ReportUtil.toItemTable(dt, start, end, true);
     return dt;
   }
 
   public static DataTable getCountryTable(long siteID, Date start, Date end, boolean chartFlag) {
     DataTable src = getDistrictTable(siteID, start, end);
     Mapx map = src.toMapx("Item", "District");
     Object[] ks = map.keyArray();
     Object[] vs = map.valueArray();
     for (int i = map.size() - 1; i >= 0; --i) {
       String district = ks[i].toString();
       if ((!(district.equals("000000"))) && (!(district.startsWith("000")))) {
         map.put("000000", map.getInt("000000") + map.getInt(district));
         map.remove(district);
       }
     }
     DataTable dt = new DataTable(src.getDataColumns(), null);
     ks = map.keyArray();
     vs = map.valueArray();
     for (int i = 0; i < map.size(); ++i) {
       dt.insertRow(new Object[] { ks[i], vs[i] });
     }
     ReportUtil.addTrend(dt, "Client", "District");
     map = new QueryBuilder("select code,name from ZDDistrict where TreeLevel<3").executeDataTable().toMapx(0, 1);
     map.put("000999", "未知区域");
     for (i = 0; i < dt.getRowCount(); ++i) {
       String item = dt.getString(i, "Item");
       if ((chartFlag) && (item.equals("000000")))
         item = "<a href='District.jsp?Type=P&Code=000000'>" + map.getString(item) + "</a>";
       else {
         item = map.getString(item);
       }
       dt.set(i, "Item", item);
     }
     return dt;
   }
 
   public static DataTable getProvinceTable(long siteID, Date start, Date end, boolean chartFlag) {
     DataTable src = getDistrictTable(siteID, start, end);
     Mapx map = src.toMapx("Item", "District");
     Object[] ks = map.keyArray();
     Mapx nMap = new Mapx();
     for (int i = map.size() - 1; i >= 0; --i) {
       String district = ks[i].toString();
       if ((!(district.equals("000000"))) && (!(district.startsWith("000")))) {
         int count = map.getInt(district);
         String code = district.substring(0, 2) + "0000";
         nMap.put(code, count + nMap.getInt(code));
       }
     }
     if (map.getInt("000000") > 0) {
       nMap.put("000999", map.getInt("000000"));
     }
     DataTable dt = new DataTable(src.getDataColumns(), null);
     if (src.getRowCount() > 0) {
       ks = nMap.keyArray();
       Object[] vs = nMap.valueArray();
       for (int i = 0; i < nMap.size(); ++i) {
         dt.insertRow(new Object[] { ks[i], vs[i] });
       }
       ReportUtil.addTrend(dt, "Client", "District");
       map = new QueryBuilder(
         "select code,name from ZDDistrict where code like '11%' or code like '12%' or code like '31%' or code like '50%' or TreeLevel<3")
         .executeDataTable().toMapx(0, 1);
       map.put("000999", "未知区域");
       for (i = 0; i < dt.getRowCount(); ++i) {
         String item = dt.getString(i, "Item");
         if (chartFlag)
           item = "<a href='District.jsp?Type=P&Code=" + item + "'>" + map.getString(item) + "</a>";
         else {
           item = map.getString(item);
         }
         dt.set(i, "Item", item);
       }
     }
     return dt;
   }
 
   public static DataTable getCityTable(long siteID, String province, Date start, Date end, boolean chartFlag) {
     DataTable src = getDistrictTable(siteID, start, end);
     Mapx map = src.toMapx("Item", "District");
     Object[] ks = map.keyArray();
     Object[] vs = map.valueArray();
     String prefix = province.substring(0, 2);
     for (int i = map.size() - 1; i >= 0; --i) {
       String district = ks[i].toString();
       if (!(district.startsWith(prefix))) {
         map.remove(district);
       }
     }
     map.put("000999", map.getInt(prefix + "0000"));
     map.remove(prefix + "0000");
     DataTable dt = new DataTable(src.getDataColumns(), null);
     ks = map.keyArray();
     vs = map.valueArray();
     for (int i = 0; i < map.size(); ++i) {
       dt.insertRow(new Object[] { ks[i], vs[i] });
     }
     ReportUtil.addTrend(dt, "Client", "District");
     map = new QueryBuilder(
       "select code,name from ZDDistrict where code like '11%' or code like '12%' or code like '31%' or code like '50%' or TreeLevel<3")
       .executeDataTable().toMapx(0, 1);
     map.put("000999", "未知区域");
     for (i = 0; i < dt.getRowCount(); ++i) {
       String item = dt.getString(i, "Item");
       item = map.getString(item);
       dt.set(i, "Item", item);
     }
     return dt;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.report.DistrictReport
 * JD-Core Version:    0.5.3
 */