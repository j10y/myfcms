 package com.zving.cms.stat.report;
 
 import com.zving.framework.data.DataColumn;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.NumberUtil;
 import com.zving.framework.utility.StringFormat;
 import com.zving.framework.utility.StringUtil;
 import java.util.Date;
 import org.apache.commons.lang.ArrayUtils;
 
 public class ReportUtil
 {
   private static Mapx StartDateMap = new Mapx();
 
   public static int getTotalStatDays(long siteID) {
     int c = new Double(Math.ceil((System.currentTimeMillis() - getStartStatDate(siteID).getTime()) * 1.0D / 
       86400000.0D)).intValue();
     if (c == 0) {
       c = 1;
     }
     return c;
   }
 
   public static Date getStartStatDate(long siteID) {
     Date date = (Date)StartDateMap.get(new Long(siteID));
     if (date == null) {
       QueryBuilder qb = new QueryBuilder(
         "select * from ZCStatItem where SiteID=? and Type='Global' and SubType='PV' order by Period", 
         siteID);
       DataTable dt = qb.executePagedDataTable(1, 0);
       if (dt.getRowCount() == 0) {
         return new Date();
       }
       for (int i = 5; i < dt.getColCount(); ++i) {
         if (dt.getInt(0, i) != 0) {
           i -= 4;
           date = DateUtil.parse(dt.getString(0, "Period") + ((i > 9) ? i : new StringBuffer("0").append(i).toString()), "yyyyMMdd");
           StartDateMap.put(new Long(siteID), date);
           break;
         }
       }
     }
 
     return date;
   }
 
   public static DataTable toDateTable(DataTable src, Date start, Date end)
   {
     if (src == null) {
       return null;
     }
     if (start.getTime() > end.getTime()) {
       Date tmp = start;
       start = end;
       end = tmp;
     }
 
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     int day1 = Integer.parseInt(DateUtil.toString(start, "dd"));
     int day2 = Integer.parseInt(DateUtil.toString(end, "dd"));
 
     Mapx map = new Mapx();
     for (int i = 0; i < src.getRowCount(); ++i) {
       String subtype = src.getString(i, "SubType");
       if (!(map.containsKey(subtype))) {
         map.put(subtype, "");
       }
     }
     Object[] subtypes = map.keyArray();
     DataTable dt = new DataTable();
     dt.insertColumn("Date");
     for (int i = 0; i < subtypes.length; ++i) {
       dt.insertColumn(subtypes[i].toString());
     }
 
     map = new Mapx();
     int j;
     for (i = 0; i < src.getRowCount(); ++i) {
       String period = src.getString(i, "Period");
       String subtype = src.getString(i, "SubType");
       int colStart;
       int d;
       String key;
       Mapx m;
       if (period.equals(period1)) {
         colStart = src.getColCount() - 1;
         if (period.equals(period2)) {
           colStart = 4 + day2;
         }
         if (colStart > DateUtil.getMaxDayOfMonth(start) + 4) {
           colStart = DateUtil.getMaxDayOfMonth(start) + 4;
         }
         for (j = colStart; j >= 4 + day1; --j) {
           d = j - 4;
           key = period + ((d > 9) ? d : new StringBuffer("0").append(d).toString());
           if (!(map.containsKey(key))) {
             map.put(key, new Mapx());
           }
           m = (Mapx)map.get(key);
           if (m.containsKey(subtype))
             m.put(subtype, m.getInt(subtype) + src.getInt(i, j));
           else
             m.put(subtype, src.getInt(i, j));
         }
       }
       else if (period.equals(period2)) {
         int colEnd = 5;
         if (period.equals(period1)) {
           colEnd = day1;
         }
         int colStart = 4 + day2;
         if (colStart > DateUtil.getMaxDayOfMonth(end) + 4) {
           colStart = DateUtil.getMaxDayOfMonth(end) + 4;
         }
         for (int j = colStart; j >= colEnd; --j) {
           int d = j - 4;
           String key = period + ((d > 9) ? d : new StringBuffer("0").append(d).toString());
           if (!(map.containsKey(key))) {
             map.put(key, new Mapx());
           }
           Mapx m = (Mapx)map.get(key);
           if (m.containsKey(subtype))
             m.put(subtype, m.getInt(subtype) + src.getInt(i, j));
           else
             m.put(subtype, src.getInt(i, j));
         }
       }
       else {
         colStart = src.getColCount() - 1;
         if (colStart > DateUtil.getMaxDayOfMonth(DateUtil.parse(period, "yyyyMM")) + 4) {
           colStart = DateUtil.getMaxDayOfMonth(DateUtil.parse(period, "yyyyMM")) + 4;
         }
         for (j = colStart; j > 4; --j) {
           d = j - 4;
           key = period + ((d > 9) ? d : new StringBuffer("0").append(d).toString());
           if (!(map.containsKey(key))) {
             map.put(key, new Mapx());
           }
           m = (Mapx)map.get(key);
           if (m.containsKey(subtype))
             m.put(subtype, m.getInt(subtype) + src.getInt(i, j));
           else {
             m.put(subtype, src.getInt(i, j));
           }
         }
       }
     }
     Object[] ks = map.keyArray();
     int[] totals = new int[subtypes.length];
     for (int i = 0; i < ks.length; ++i) {
       dt.insertRow(null);
       String date = ks[i].toString();
       dt.set(i, "Date", date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6));
       for (j = 0; j < subtypes.length; ++j) {
         String count = ((Mapx)map.get(date)).getString(subtypes[j]);
         if (StringUtil.isNotEmpty(count)) {
           dt.set(i, subtypes[j].toString(), count);
           totals[j] += Integer.parseInt(count);
         } else {
           dt.set(i, subtypes[j].toString(), "0");
         }
       }
     }
     dt.insertRow(null, 0);
     dt.set(0, "Date", "总计");
     for (int j = 0; j < subtypes.length; ++j) {
       dt.set(0, subtypes[j].toString(), totals[j]);
     }
 
     return dt;
   }
 
   public static DataTable toItemTable(DataTable src, Date start, Date end)
   {
     return toItemTable(src, start, end, false);
   }
 
   public static DataTable toItemTable(DataTable src, Date start, Date end, boolean trimEmpty) {
     String period1 = DateUtil.toString(start, "yyyyMM");
     String period2 = DateUtil.toString(end, "yyyyMM");
     int day1 = Integer.parseInt(DateUtil.toString(start, "dd"));
     int day2 = Integer.parseInt(DateUtil.toString(end, "dd"));
 
     Mapx itemMap = new Mapx();
     Mapx subtypeMap = new Mapx();
     for (int i = 0; i < src.getRowCount(); ++i) {
       String subtype = src.getString(i, "SubType");
       if (!(subtypeMap.containsKey(subtype))) {
         subtypeMap.put(subtype, "");
       }
     }
     Object[] subtypes = subtypeMap.keyArray();
     for (int i = 0; i < src.getRowCount(); ++i) {
       String item = src.getString(i, "Item");
       if (!(itemMap.containsKey(item))) {
         Mapx map = new Mapx();
         for (int j = 0; j < subtypes.length; ++j) {
           map.put(subtypes[j], new Integer(0));
         }
         itemMap.put(item, map);
       }
     }
     DataTable dt = new DataTable();
     dt.insertColumn("Item");
     for (int i = 0; i < subtypes.length; ++i) {
       dt.insertColumn(subtypes[i].toString());
     }
 
     for (i = 0; i < src.getRowCount(); ++i) {
       String period = src.getString(i, "Period");
       String subtype = src.getString(i, "SubType");
       String item = src.getString(i, "Item");
       int colStart;
       int j;
       int count;
       Mapx map;
       if (period.equals(period1)) {
         colStart = src.getColCount() - 1;
         if (period.equals(period2)) {
           colStart = 4 + day2;
         }
         for (j = colStart; j >= 4 + day1; --j) {
           count = Integer.parseInt(src.getString(i, j));
           map = (Mapx)itemMap.get(item);
           map.put(subtype, new Integer(((Integer)map.get(subtype)).intValue() + count));
         }
       } else if (period.equals(period2)) {
         int colEnd = 5;
         if (period.equals(period1)) {
           colEnd = day1;
         }
         int colStart = 4 + day2;
         for (int j = colStart; j >= colEnd; --j) {
           int count = Integer.parseInt(src.getString(i, j));
           Mapx map = (Mapx)itemMap.get(item);
           map.put(subtype, new Integer(((Integer)map.get(subtype)).intValue() + count));
         }
       } else {
         colStart = src.getColCount() - 1;
         for (j = colStart; j > 4; --j) {
           count = Integer.parseInt(src.getString(i, j));
           map = (Mapx)itemMap.get(item);
           map.put(subtype, new Integer(((Integer)map.get(subtype)).intValue() + count));
         }
       }
     }
     Object[] ks = itemMap.keyArray();
     for (int i = 0; i < ks.length; ++i) {
       String item = ks[i].toString();
       Mapx map = (Mapx)itemMap.get(item);
       if ((subtypes.length == 1) && (trimEmpty)) {
         int count = map.getInt(subtypes[0]);
         if (count == 0) {
           continue;
         }
       }
       dt.insertRow(null);
       dt.set(dt.getRowCount() - 1, "Item", item);
       for (int j = 0; j < subtypes.length; ++j) {
         int count = map.getInt(subtypes[j]);
         dt.set(dt.getRowCount() - 1, subtypes[j].toString(), new Integer(count));
       }
     }
     return dt;
   }
 
   public static void prepareForPie3D(DataTable dt, int count, double rate)
   {
     if ((dt == null) || (dt.getRowCount() == 0)) {
       return;
     }
     int total = 0;
     for (int i = 0; i < dt.getRowCount(); ++i) {
       total += dt.getInt(i, 1);
     }
     int other = 0;
     for (int i = dt.getRowCount() - 1; i >= 0; --i) {
       if (dt.getInt(i, 1) * 100.0D / total < rate) {
         other += dt.getInt(i, 1);
         dt.deleteRow(i);
       }
     }
     dt.sort(dt.getDataColumn(1).getColumnName());
     for (i = dt.getRowCount() - 1; i >= count; --i) {
       other += dt.getInt(i, 1);
       dt.deleteRow(i);
     }
     dt.insertRow(new Object[] { "其他", new Integer(other) });
   }
 
   public static void prepareForPie3D(DataTable dt, double rate)
   {
     prepareForPie3D(dt, 2147483647, rate);
   }
 
   public static void prepareForPie3D(DataTable dt, int itemCount)
   {
     prepareForPie3D(dt, itemCount, 0.0D);
   }
 
   public static void computeRate(DataTable dt, String columName, String destColumnName)
   {
     dt.insertColumn(destColumnName);
     int total = 0;
     for (int i = 0; i < dt.getRowCount(); ++i) {
       total += Integer.parseInt(dt.getString(i, columName));
     }
     for (i = 0; i < dt.getRowCount(); ++i) {
       int count = Integer.parseInt(dt.getString(i, columName));
       dt.set(i, destColumnName, new Double(NumberUtil.round(count * 100.0D / total, 2)));
     }
   }
 
   public static void addSuffix(DataTable dt, String columName, String suffix)
   {
     for (int i = 0; i < dt.getRowCount(); ++i) {
       String r = dt.getString(i, columName);
       if (StringUtil.isNotEmpty(r))
         dt.set(i, columName, r + suffix);
     }
   }
 
   public static void addTotal(DataTable dt, String[] digitColumns)
   {
     for (int i = digitColumns.length - 1; i >= 0; --i) {
       if (dt.getDataColumn(digitColumns[i]) == null) {
         digitColumns = (String[])ArrayUtils.remove(digitColumns, i);
       }
     }
     int[] totals = new int[digitColumns.length];
     for (int i = 0; i < dt.getRowCount(); ++i) {
       for (int j = 0; j < digitColumns.length; ++j) {
         totals[j] += dt.getInt(i, digitColumns[j]);
       }
     }
     if (dt.getRowCount() > 0) {
       dt.insertRow(null, 0);
       dt.set(0, 0, "总计");
       for (int j = 0; j < digitColumns.length; ++j)
         dt.set(0, digitColumns[j], totals[j]);
     }
   }
 
   public static void addTrend(DataTable dt, String type, String subtype)
   {
     dt.insertColumn("Trend");
     for (int i = 0; i < dt.getRowCount(); ++i) {
       StringFormat sf = new StringFormat(
         "<a href='javascript:void(0);' onclick=\"showTrend('?','?','?')\">时间趋势</a>");
       sf.add(type);
       sf.add(subtype);
       sf.add(dt.getString(i, "Item"));
       dt.set(i, "Trend", sf.toString());
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.report.ReportUtil
 * JD-Core Version:    0.5.3
 */