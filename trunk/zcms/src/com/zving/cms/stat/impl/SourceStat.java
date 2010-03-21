 package com.zving.cms.stat.impl;
 
 import com.zving.cms.stat.AbstractStat;
 import com.zving.cms.stat.StatUtil;
 import com.zving.cms.stat.Visit;
 import com.zving.cms.stat.VisitCount;
 import com.zving.framework.Constant;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.ServletUtil;
 import com.zving.framework.utility.StringUtil;
 import java.util.Date;
 
 public class SourceStat extends AbstractStat
 {
   private static final String Type = "Source";
   private Mapx siteMap = new Mapx();
 
   public String getStatType() {
     return "Source";
   }
 
   public void deal(Visit v) {
     if ("Unload".equals(v.Event)) {
       return;
     }
     if (!(this.siteMap.containsKey(new Long(v.SiteID)))) {
       VisitCount.getInstance().initLRUMap(v.SiteID, "Source", "Keyword", 1000, null);
       VisitCount.getInstance().initLRUMap(v.SiteID, "Source", "Referer", 1000, null);
       this.siteMap.put(new Long(v.SiteID), "");
     }
     VisitCount.getInstance().add(v.SiteID, "Source", "Host", v.Host);
     if ((StringUtil.isEmpty(v.Referer)) && (v.URL.indexOf("Result.jsp") < 0)) {
       VisitCount.getInstance().add(v.SiteID, "Source", "Direct", "0");
     } else {
       String[] se = getSearchEngine(v);
       if (se == null) {
         String domain = StatUtil.getDomain(v.Referer);
         if (!(domain.equalsIgnoreCase(v.Host)))
           VisitCount.getInstance().add(v.SiteID, "Source", "Referer", domain);
         else
           VisitCount.getInstance().add(v.SiteID, "Source", "Direct", "0");
       }
       else {
         VisitCount.getInstance().add(v.SiteID, "Source", "SearchEngine", se[0]);
         VisitCount.getInstance().add(v.SiteID, "Source", "Keyword", se[1]);
       }
     }
   }
 
   public void onPeriodChange(int type, long current) {
     if (type == 1) {
       String period = DateUtil.toString(new Date(current), "yyyyMMdd");
       if (period.endsWith("01")) {
         this.isNewMonth = true;
         VisitCount.getInstance().clearType(getStatType(), true);
       } else {
         VisitCount.getInstance().clearType(getStatType(), false);
         VisitCount.getInstance().clearSubType(getStatType(), "Keyword", true);
         VisitCount.getInstance().clearSubType(getStatType(), "Referer", true);
       }
     }
   }
 
   public void update(VisitCount vc, long current, boolean newMonthFlag, boolean isNewPeriod) {
     if (!(newMonthFlag)) {
       Date today = new Date(current);
       if (isNewPeriod) {
         today = DateUtil.addDay(today, -1);
       }
       String period = DateUtil.toString(today, "yyyyMM");
       long[] sites = vc.getSites();
       for (int i = 0; i < sites.length; ++i) {
         URLStat.dealNotNeedInsertItem(vc, period, sites[i], "Source", "Referer");
         URLStat.dealNotNeedInsertItem(vc, period, sites[i], "Source", "Keyword");
       }
     }
     super.update(vc, current, newMonthFlag, isNewPeriod);
   }
 
   public static String[] getSearchEngine(Visit v) {
     String url = v.URL;
 
     if (url.indexOf("Result.jsp") > 0) {
       Mapx map = ServletUtil.getMapFromQueryString(url);
       String keyword = StringUtil.urlDecode(map.getString("query"), Constant.GlobalCharset);
       return new String[] { "站内搜索", keyword };
     }
     url = v.Referer;
     String domain = StatUtil.getDomain(url);
     Mapx map = ServletUtil.getMapFromQueryString(url);
     String name = null;
     String keyword = null;
     if (domain.indexOf("baidu.") > 0) {
       keyword = StringUtil.urlDecode(map.getString("wd"), "GBK");
       name = "百度";
     }
     else
     {
       String charset;
       if (domain.indexOf("google.") > 0) {
         charset = map.getString("ie");
         if (StringUtil.isEmpty(charset)) {
           charset = "UTF-8";
         }
         keyword = StringUtil.urlDecode(map.getString("q"), charset);
         name = "谷歌";
       } else if (domain.indexOf("yahoo.") > 0) {
         charset = map.getString("ei");
         if (StringUtil.isEmpty(charset)) {
           charset = "UTF-8";
         }
         keyword = StringUtil.urlDecode(map.getString("p"), charset);
         name = "雅虎";
       } else if (domain.indexOf("msn.") > 0) {
         keyword = StringUtil.urlDecode(map.getString("q"), "UTF-8");
         name = "MSN";
       } else if (domain.indexOf("soso.") > 0) {
         keyword = StringUtil.urlDecode(map.getString("w"), "GBK");
         name = "搜搜";
       } else if (domain.indexOf("sogou.") > 0) {
         keyword = StringUtil.urlDecode(map.getString("query"), "GBK");
         name = "搜狗";
       } else if (domain.indexOf("zhongsou.") > 0) {
         keyword = StringUtil.urlDecode(map.getString("word"), "GBK");
         name = "中搜";
       } else if (domain.indexOf("youdao.") > 0) {
         charset = map.getString("ue");
         if (StringUtil.isEmpty(charset)) {
           charset = "UTF-8";
         }
         keyword = StringUtil.urlDecode(map.getString("q"), charset);
         name = "有道";
       } else if (domain.indexOf("live.") > 0) {
         keyword = StringUtil.urlDecode(map.getString("q"), "UTF-8");
         name = "Live.com"; }
     }
     if (StringUtil.isNotEmpty(keyword)) {
       return new String[] { name, keyword };
     }
     return null;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.impl.SourceStat
 * JD-Core Version:    0.5.3
 */