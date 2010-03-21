 package com.zving.cms.stat.impl;
 
 import com.zving.cms.stat.AbstractStat;
 import com.zving.cms.stat.StatUtil;
 import com.zving.cms.stat.Visit;
 import com.zving.cms.stat.VisitCount;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.Mapx;
 import java.util.Date;
 
 public class GlobalStat extends AbstractStat
 {
   private static final String[] avgSubTypes = { "StickTime", "VisitDepth", "StickTotalTime" };
   private static final String Type = "Global";
   private static Mapx ipMap = new Mapx();
   private static long lastIPClearTime;
   private static Mapx idMap = new Mapx();
   private static long lastIDClearTime;
   private static long lastOnlineCheckTime;
 
   public String getStatType()
   {
     return "Global";
   }
 
   public String[] getAverageSubTypes() {
     return avgSubTypes;
   }
 
   public void deal(Visit v) {
     if ("Unload".equals(v.Event)) {
       VisitCount.getInstance().addAverage(v.SiteID, "Global", "StickTime", "0", v.StickTime);
     } else {
       VisitCount.getInstance().add(v.SiteID, "Global", "PV", "0");
       if (v.UVFlag) {
         VisitCount.getInstance().add(v.SiteID, "Global", "UV", "0");
         if (v.RVFlag)
           VisitCount.getInstance().add(v.SiteID, "Global", "NewVisitor", "0");
         else {
           VisitCount.getInstance().add(v.SiteID, "Global", "ReturnVisitor", "0");
         }
         if (v.Frequency > 0) {
           String item = v.Frequency;
           if (v.Frequency > 30) {
             item = "&gt;30";
           }
           VisitCount.getInstance().add(v.SiteID, "Global", "VisitFreq", item);
         }
       }
       if (v.IPFlag)
         VisitCount.getInstance().add(v.SiteID, "Global", "IP", "0");
     }
   }
 
   public static boolean isTodayVisited(String type, String strIP, long current)
   {
     Long ip = new Long(StatUtil.convertIP(strIP));
     synchronized (GlobalStat.class) {
       if (!(ipMap.containsKey(type))) {
         ipMap.put(type, new Mapx(100000));
       }
     }
     Mapx map = (Mapx)ipMap.get(type);
     synchronized (map) {
       Object obj = map.get(ip);
       if (obj != null) {
         long t = ((Long)obj).longValue();
         if (current - t < 86400000L) {
           return true;
         }
         map.remove(ip);
         map.put(ip, new Long(current));
         return false;
       }
 
       if (current - lastIPClearTime > 900000L) {
         Object[] ks = map.keyArray();
         Object[] vs = map.valueArray();
         for (int i = 0; i < ks.length; ++i) {
           long t = ((Long)vs[i]).longValue();
           if (current - t < 86400000L) {
             break;
           }
           map.remove(ks[i]);
         }
 
         lastIPClearTime = current;
       }
       map.put(ip, new Long(current));
     }
 
     return false;
   }
 
   public static void dealUniqueID(Visit v)
   {
     Long site = new Long(v.SiteID);
     synchronized (GlobalStat.class) {
       if (!(idMap.containsKey(site))) {
         idMap.put(site, new Mapx());
       }
     }
     Mapx map = (Mapx)idMap.get(site);
     long time = v.VisitTime;
     synchronized (map) {
       if (time - lastOnlineCheckTime >= 60000L) {
         int c15 = 0; int c10 = 0; int c5 = 0;
         Object[] vs = map.valueArray();
         for (int i = 0; i < vs.length; ++i) {
           VisitHistory vh = (VisitHistory)vs[i];
           if (time - vh.LastTime < 900000L) {
             ++c15;
             if (time - vh.LastTime < 600000L) {
               ++c10;
               if (time - vh.LastTime < 300000L) {
                 ++c5;
               }
             }
           }
         }
         String h = DateUtil.toString(new Date(time), "HH");
         long m15 = VisitCount.getInstance().get(v.SiteID, "Hour", "15Min", h);
         long m10 = VisitCount.getInstance().get(v.SiteID, "Hour", "10Min", h);
         long m5 = VisitCount.getInstance().get(v.SiteID, "Hour", "5Min", h);
 
         if (m15 < c15) {
           VisitCount.getInstance().set(v.SiteID, "Hour", "15Min", h, c15);
         }
         if (m10 < c10) {
           VisitCount.getInstance().set(v.SiteID, "Hour", "10Min", h, c10);
         }
         if (m5 < c5) {
           VisitCount.getInstance().set(v.SiteID, "Hour", "5Min", h, c5);
         }
         lastOnlineCheckTime = time;
       }
       Object obj = map.get(v.UniqueID);
       if (obj == null) {
         map.put(v.UniqueID, new VisitHistory(time, time, 1, v.URL));
         v.UVFlag = true;
         if (time - lastIDClearTime >= 900000L) {
           Object[] ks = map.keyArray();
           Object[] vs = map.valueArray();
           for (int i = 0; i < ks.length; ++i) {
             VisitHistory vh = (VisitHistory)vs[i];
             if (time - vh.LastTime < 900000L) {
               break;
             }
             long avgStickTime = VisitCount.getInstance().get(time, "Global", "StickTime", "0");
             String item = vh.Depth;
             if (vh.Depth > 30) {
               item = "&gt;30";
             }
             VisitCount.getInstance().add(v.SiteID, "Global", "VisitDepth", item);
             VisitCount.getInstance().addAverage(v.SiteID, "Global", "StickTotalTime", "0", 
               avgStickTime + (vh.LastTime - vh.StartTime) / 1000L);
             VisitCount.getInstance().add(v.SiteID, "URL", "Exit", vh.URL);
             map.remove(ks[i]);
           }
 
           lastIDClearTime = time;
         }
       } else {
         VisitHistory vh = (VisitHistory)obj;
         if (time - vh.LastTime < 900000L) {
           vh.LastTime = time;
           vh.Depth += 1;
           vh.URL = v.URL;
           v.UVFlag = false;
         } else {
           long avgStickTime = VisitCount.getInstance().get(time, "Global", "StickTime", "0");
           String item = vh.Depth;
           if (vh.Depth > 30) {
             item = "&gt;30";
           }
           VisitCount.getInstance().add(v.SiteID, "Global", "VisitDepth", item);
           VisitCount.getInstance().addAverage(v.SiteID, "Global", "StickTotalTime", "0", 
             avgStickTime + (vh.LastTime - vh.StartTime) / 1000L);
           VisitCount.getInstance().add(v.SiteID, "URL", "Exit", vh.URL);
           vh.StartTime = time;
           vh.LastTime = time;
           vh.Depth = 1;
           vh.URL = v.URL;
           v.UVFlag = true;
         }
       }
     }
   }
 
   static class VisitHistory
   {
     public long StartTime;
     public long LastTime;
     public int Depth;
     public String URL;
 
     public VisitHistory(long startTime, long lastTime, int depth, String url)
     {
       this.StartTime = startTime;
       this.LastTime = lastTime;
       this.Depth = depth;
       this.URL = url;
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.impl.GlobalStat
 * JD-Core Version:    0.5.3
 */