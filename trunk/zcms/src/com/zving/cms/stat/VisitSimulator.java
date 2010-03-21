 package com.zving.cms.stat;
 
 import com.zving.cms.stat.impl.GlobalStat;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.NumberUtil;
 import com.zving.framework.utility.Queuex;
 import com.zving.framework.utility.StringUtil;
 import java.util.Date;
 
 public class VisitSimulator
 {
   private static String[] DistrictCodes;
   private static Object[] IPRanges;
   static String[] referers = { 
     "", 
     "", 
     "", 
     "", 
     "", 
     "http://www.baidu.com/baidu?wd=%B4%B4%D2%B5&tn=monline_dg", 
     "http://www.google.cn/search?q=%E9%9D%92%E6%AA%AC%E5%A4%9C%E6%A0%A1&ie=utf-8&oe=utf-8&aq=t&rls=org.mozilla:zh-CN:official&client=firefox-a", 
     "http://search.cn.yahoo.com/search?v=web&ei=gbk&searchFlag=&fr=fp-tab-web-ycn&pid=ysearch&source=yahoo_yhp_0706_search_button&p=%CC%F4%D5%BD%B1%AD", 
     "http://www.baidu.com/baidu?wd=%C7%E0%C3%CA%D2%B9%D0%A3&tn=monline_dg", 
     "http://www.baidu.com/baidu?wd=%CC%F4%D5%BD%B1%AD&tn=monline_dg", "http://www.zving.com/index.shtml", 
     "http://www.bjyouth.net/index.shtml", "http://www.beijing2008.com/index.shtml", 
     "http://www.bjyouth.gov.cn/test" };
 
   static String[] useragents = { "Mozilla/4.0 (compatible; MSIE 8.0; zh-cn; Windows NT 6.0) ", 
     "Mozilla/4.0 (compatible; MSIE 6.0; zh-cn; Windows NT 5.0) ", 
     "Mozilla/4.0 (compatible; MSIE 7.0; zh_cn; Windows NT 5.0) ", 
     "Mozilla/4.0 (compatible; MSIE 7.0; zh-cn; Windows NT 5.1) ", 
     "Mozilla/5.0 (compatible; MSIE 8.0; zh-cn; Windows NT 5.1) ", 
     "Mozilla/4.0 (compatible; MSIE 6.0; zh-cn; Windows NT 5.1) ", 
     "Mozilla/4.0 (compatible; MSIE 6.0; zh-cn; Windows NT 5.1) ", 
     "Mozilla/4.0 (compatible; MSIE 6.0; zh-cn; Windows NT 5.1) ", 
     "Mozilla/5.0 (Macintosh; U; PPC Mac OS X; de-de) AppleWebKit/85.7 (KHTML, like Gecko) Safari/85.5", 
     "Opera/9.51 (Windows NT 5.1; U; zh_cn) ", "http://www.baidu.com/A", "http://www.baidu.com/A", 
     "Mozilla/5.0 (Windows; U; Linux 1.0; zh_tw; rv:1.7.5) Gecko/20041108 Firefox/1.0", 
     "Mozilla/5.0 (iPhone; U; iPhone 1.0; sv-SE; rv:1.7.5) Gecko/20041108 Firefox/1.0", 
     "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13" };
 
   static String[] hosts = { "www.cybj.cn", "202.99.8.4", "http://driver.bv2008.cn" };
   static DataTable leafTable;
   static Queuex idQueue = new Queuex(5000);
 
   static Queuex ipQueue = new Queuex(5000);
 
   static String[] ces = { "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0" };
 
   static String[] fvs = { "10.0", "10.0", "10.0", "10.0", "9.0r94", "9.0r94", "9.0r94", "9.0r94", "9.0r94", "9.0r94", "9.0r94", "9.0r94", 
     "9.0r124", "9.0r125", "8.0", "7.0" };
 
   static String[] jes = { "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0" };
 
   static String[] srs = { "1024*768", "1024*768", "1024*768", "1024*768", "1024*768", "1024*768", "1024*768", "1024*768", "1024*768", 
     "1024*768", "1024*768", "1024*768", "1440*900", "1440*900", "1440*900", "1280*800", "1280*800", "800*600", 
     "1650*1050" };
 
   static String[] cds = { "32-bit", "32-bit", "32-bit", "32-bit", "32-bit", "32-bit", "32-bit", "32-bit", "32-bit", "32-bit", "32-bit", 
     "24-bit", "24-bit", "16-bit", "8-bit" };
 
   static String[] fqs = { "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "2", "2", "2", "2", "2", "2", "2", "2", "2", "3", "3", "3", 
     "3", "3", "3", "3", "3", "4", "4", "4", "4", "4", "4", "4", "5", "5", "5", "5", "5", "5", "6", "6", "6", "6", 
     "6", "7", "7", "7", "7", "8", "8", "8", "9", "9", "10", "11", "12" };
 
   private static void initIPRanges()
   {
     if (DistrictCodes == null)
       synchronized (StatUtil.class) {
         if (DistrictCodes == null) {
           DataTable dt = new QueryBuilder("select IPRanges,DistrictCode from ZDIPRange").executeDataTable();
           String[] codes = new String[dt.getRowCount()];
           IPRanges = new Object[dt.getRowCount()];
           for (int i = 0; i < dt.getRowCount(); ++i) {
             String code = dt.getString(i, 1);
             String ranges = dt.getString(i, 0);
             codes[i] = code;
             String[] arr = StringUtil.splitEx(ranges, ",");
             long[] r = new long[arr.length * 2];
             for (int j = 0; j < arr.length; ++j) {
               String[] arr2 = StringUtil.splitEx(arr[j], "+");
               r[(2 * j)] = Long.parseLong(arr2[0]);
               r[(2 * j + 1)] = (r[(2 * j)] + Long.parseLong(arr2[1]));
             }
             IPRanges[i] = r;
           }
           DistrictCodes = codes;
         }
       }
   }
 
   public static String getIP()
   {
     initIPRanges();
     int r1 = NumberUtil.getRandomInt(DistrictCodes.length - 1);
     long[] arr = (long[])IPRanges[r1];
     int r2 = NumberUtil.getRandomInt(arr.length / 2);
     long i1 = arr[(r2 * 2)];
     long i2 = arr[(r2 * 2 + 1)];
     int diff = (int)(i2 - i1);
     int r3 = NumberUtil.getRandomInt(diff + 1);
     long ip = i1 + r3 + 1L;
     long s1 = ip / 16777216L;
     long s2 = ip % 16777216L / 65536L;
     long s3 = ip % 65536L / 256L;
     long s4 = ip % 256L;
     return s1 + "." + s2 + "." + s3 + "." + s4;
   }
 
   private static String getReferer()
   {
     return referers[NumberUtil.getRandomInt(referers.length)];
   }
 
   private static String getUserAgent()
   {
     return useragents[NumberUtil.getRandomInt(useragents.length)];
   }
 
   private static String getHost()
   {
     return hosts[NumberUtil.getRandomInt(hosts.length)];
   }
 
   private static long getSiteID() {
     return 206L;
   }
 
   private static void initMap(Mapx map)
   {
     if (leafTable == null) {
       synchronized (VisitSimulator.class) {
         if (leafTable == null) {
           QueryBuilder qb = 
             new QueryBuilder("select ID,CatalogInnerCode,'Article' as Type from ZCArticle where SiteID=?", getSiteID());
           DataTable dt = qb.executeDataTable();
           qb = new QueryBuilder("select ID,CatalogInnerCode,'Image' as Type from ZCImage where SiteID=?", getSiteID());
           dt.union(qb.executeDataTable());
           qb = new QueryBuilder("select ID,CatalogInnerCode,'Video' as Type from ZCVideo where SiteID=?", getSiteID());
           dt.union(qb.executeDataTable());
           leafTable = dt;
         }
       }
     }
     int r1 = NumberUtil.getRandomInt(leafTable.getRowCount());
     int r2 = NumberUtil.getRandomInt(10);
     if (r2 == 5) {
       map.put("LeafID", "0");
       map.put("URL", "http://www.cybj.cn/" + leafTable.get(r1, "CatalogInnerCode") + ".shtml");
     } else {
       map.put("LeafID", leafTable.get(r1, "ID"));
       map.put("URL", "http://www.cybj.cn/" + leafTable.get(r1, "CatalogInnerCode") + "_" + leafTable.get(r1, "ID") + 
         ".shtml");
     }
     map.put("CatalogInnerCode", leafTable.get(r1, "CatalogInnerCode"));
     map.put("Type", leafTable.get(r1, "Type"));
 
     if (r1 % 2 == 0) {
       map.put("StickTime", 4 + r1 % 15);
       map.put("Event", "Unload");
     }
   }
 
   private static void dealClient(Mapx map)
   {
     map.put("ce", ces[NumberUtil.getRandomInt(ces.length)]);
     map.put("fv", fvs[NumberUtil.getRandomInt(fvs.length)]);
     map.put("je", jes[NumberUtil.getRandomInt(jes.length)]);
     map.put("sr", srs[NumberUtil.getRandomInt(srs.length)]);
     map.put("cd", cds[NumberUtil.getRandomInt(cds.length)]);
     map.put("fq", fqs[NumberUtil.getRandomInt(fqs.length)]);
   }
 
   public static void simulateOneVisit(String ip, String uniqueID, boolean RVFlag, long current) {
     Mapx map = new Mapx();
 
     map.put("SiteID", getSiteID());
     initMap(map);
 
     map.put("Referer", getReferer());
     map.put("UserAgent", getUserAgent());
     map.put("Host", getHost());
     map.put("UniqueID", uniqueID);
     map.put("IP", ip);
     map.put("RVFlag", (RVFlag) ? "1" : "0");
 
     Visit v = new Visit();
     v.UserAgent = map.getString("UserAgent");
     v.SiteID = Long.parseLong(map.getString("SiteID"));
     v.CatalogInnerCode = map.getString("CatalogInnerCode");
     v.Host = map.getString("Host").toLowerCase();
     v.Type = map.getString("Type");
     v.Event = map.getString("Event");
     v.LeafID = ((StringUtil.isNotEmpty(map.getString("LeafID"))) ? Long.parseLong(map.getString("LeafID")) : 0L);
     v.IP = map.getString("IP");
     v.VisitTime = current;
     v.Referer = map.getString("Referer");
     v.URL = map.getString("URL");
 
     if (!("Unload".equalsIgnoreCase(map.getString("Event")))) {
       v.UniqueID = map.getString("UniqueID");
       v.RVFlag = ("1".equals(map.getString("RVFlag")));
       GlobalStat.dealUniqueID(v);
 
       dealClient(map);
       v.CookieEnabled = ("1".equals(map.getString("ce")));
       v.FlashVersion = map.getString("fv");
       v.FlashEnabled = StringUtil.isEmpty(v.FlashVersion);
       v.JavaEnabled = ("1".equals(map.getString("je")));
       v.Language = StatUtil.getLanguage(v.UserAgent);
       v.OS = StatUtil.getOS(v.UserAgent);
       v.Browser = StatUtil.getBrowser(v.UserAgent);
       v.Referer = map.getString("Referer");
       v.Screen = map.getString("sr");
       v.ColorDepth = map.getString("cd");
       v.District = StatUtil.getDistrictCode(v.IP);
       v.IPFlag = GlobalStat.isTodayVisited(String.valueOf(v.SiteID), v.IP, v.VisitTime);
       v.Frequency = Integer.parseInt(map.getString("fq"));
     } else {
       v.StickTime = Integer.parseInt(map.getString("StickTime"));
     }
 
     VisitHandler.deal(v);
   }
 
   public static void main(String[] args)
   {
     VisitHandler.setSimulating(true);
     new QueryBuilder("truncate table zcstatitem").executeNoQuery();
     Date start = DateUtil.parse(DateUtil.getCurrentDate());
     start = DateUtil.addDay(start, 1);
     int dayCount = 33;
     int threadCount = 10;
     for (int k = dayCount; k >= 0; --k) {
       long current = DateUtil.addDay(start, -k).getTime();
       VisitThread[] arr = new VisitThread[threadCount];
       for (int i = 0; i < arr.length; ++i) {
         int count = (dayCount - k) * 10 + NumberUtil.getRandomInt(400);
         VisitThread t = new VisitThread(current, count);
         arr[i] = t;
         t.start(); }
       boolean flag;
       do {
         try {
           Thread.sleep(1000L);
         } catch (InterruptedException e) {
           e.printStackTrace();
         }
         flag = false;
         for (int i = 0; i < arr.length; ++i)
           if (!(arr[i].isCompleted)) {
             flag = true;
             break;
           }
       }
       while (flag);
     }
   }
 
   static class VisitThread extends Thread
   {
     public boolean isCompleted = false;
     private int count;
     private long current;
 
     public VisitThread(long current, int count)
     {
       this.count = count;
       this.current = current;
     }
 
     public void run() {
       String UniqueID = StatUtil.getUniqueID();
       String IP = VisitSimulator.getIP();
       boolean RVFlag = true;
       for (int j = 0; j < this.count; ++j) {
         if (NumberUtil.getRandomInt(6) == 1) {
           UniqueID = StatUtil.getUniqueID();
           if (NumberUtil.getRandomInt(5) != 1) {
             IP = VisitSimulator.getIP();
           }
           if (NumberUtil.getRandomInt(4) != 1)
             RVFlag = true;
           else {
             RVFlag = false;
           }
         }
         VisitSimulator.simulateOneVisit(IP, UniqueID, RVFlag, this.current + 86400000L * j / this.count);
       }
       this.isCompleted = true;
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.VisitSimulator
 * JD-Core Version:    0.5.3
 */