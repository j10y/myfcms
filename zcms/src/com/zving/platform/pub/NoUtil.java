 package com.zving.platform.pub;
 
 import com.zving.framework.Config;
 import com.zving.framework.utility.LogUtil;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZDMaxNoSchema;
 import com.zving.schema.ZDMaxNoSet;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.net.URL;
 import org.apache.commons.logging.Log;
 
 public class NoUtil
 {
   private static ZDMaxNoSet MaxNoSet;
 
   public static String getMaxNo(String noType)
   {
     return getMaxNo(noType, "SN");
   }
 
   public static String getMaxNoLoal(String noType) {
     return getMaxNoLocal(noType, "SN");
   }
 
   public static long getMaxID(String noType, String subType) {
     if (Config.isDebugMode()) {
       return getMaxIDUseLock(noType, subType);
     }
     if ("true".equals(Config.getValue("App.UseMaxNoServer"))) {
       String str = getRemoteMaxNo(noType, subType, "ID");
       if ((str == null) || (str.equals(""))) {
         throw new RuntimeException("获取远程ID失败");
       }
       LogUtil.getLogger().info("Get Remote MaxID Succ!");
       return Long.parseLong(str);
     }
     return getMaxIDLocal(noType, subType);
   }
 
   public static long getMaxIDUseLock(String noType, String subType)
   {
     ZDMaxNoSchema maxno = new ZDMaxNoSchema();
     maxno.setNoType(noType);
     maxno.setNoSubType(subType);
     if (maxno.fill()) {
       long t = maxno.getMaxValue() + 1L;
       maxno.setMaxValue(t);
       if (maxno.update()) {
         return t;
       }
       throw new RuntimeException("获取最大号时发生错误!");
     }
 
     maxno.setMaxValue(1L);
     maxno.setLength(10L);
     if (maxno.insert()) {
       return 1L;
     }
     throw new RuntimeException("获取最大号时发生错误!");
   }
 
   public static String getMaxNo(String noType, int length)
   {
     long t = getMaxID(noType, "SN");
     String no = String.valueOf(t);
     if (no.length() > length) {
       return no.substring(0, length);
     }
     return StringUtil.leftPad(no, '0', length);
   }
 
   public static String getMaxNo(String noType, String prefix, int length)
   {
     long t = getMaxID(noType, prefix);
     String no = String.valueOf(t);
     if (no.length() > length) {
       return no.substring(0, length);
     }
     return prefix + StringUtil.leftPad(no, '0', length);
   }
 
   public static String getMaxNoUseLock(String noType, String subType) {
     ZDMaxNoSchema maxno = new ZDMaxNoSchema();
     maxno.setNoType(noType);
     maxno.setNoSubType(subType);
     if (maxno.fill()) {
       long t = maxno.getMaxValue() + 1L;
       maxno.setMaxValue(t);
       if (maxno.update()) {
         if (maxno.getLength() <= 0L) {
           return String.valueOf(maxno.getMaxValue());
         }
         String no = String.valueOf(maxno.getMaxValue());
         return StringUtil.leftPad(no, '0', (int)maxno.getLength());
       }
       throw new RuntimeException("获取最大号时发生错误!");
     }
 
     maxno.setMaxValue(1L);
     maxno.setLength(10L);
     if (maxno.insert()) {
       return "0000000001";
     }
     throw new RuntimeException("获取最大号时发生错误!");
   }
 
   public static long getMaxIDLocal(String noType, String subType)
   {
     if (MaxNoSet == null) {
       init();
     }
     ZDMaxNoSchema maxno = null;
     if (MaxNoSet != null) {
       for (int i = 0; i < MaxNoSet.size(); ++i) {
         maxno = MaxNoSet.get(i);
         if ((maxno.getNoType().equals(noType)) && (maxno.getNoSubType().equals(subType)))
           synchronized (NoUtil.class) {
             maxno.setMaxValue(maxno.getMaxValue() + 1L);
             if (!(maxno.update())) {
               throw new RuntimeException("生成最大号错误,MaxNoType=" + noType + ",MaxSubType=" + subType);
             }
             return maxno.getMaxValue();
           }
       }
     }
     else {
       synchronized (NoUtil.class) {
         MaxNoSet = new ZDMaxNoSet();
         maxno = new ZDMaxNoSchema();
         maxno.setNoType(noType);
         maxno.setNoSubType(subType);
         maxno.setLength(0L);
         maxno.setMaxValue(1L);
         maxno.insert();
         MaxNoSet.add(maxno);
         return 1L;
       }
     }
 
     synchronized (NoUtil.class) {
       maxno = new ZDMaxNoSchema();
       maxno.setNoType(noType);
       maxno.setNoSubType(subType);
       maxno.setLength(10L);
       maxno.setMaxValue(1L);
       maxno.insert();
       MaxNoSet.add(maxno);
       return 1L;
     }
   }
 
   public static long getMaxID(String noType) {
     return getMaxID(noType, "ID");
   }
 
   public static long getMaxIDLocal(String noType) {
     return getMaxIDLocal(noType, "ID");
   }
 
   public static String getMaxNo(String noType, String subType) {
     if (Config.isDebugMode()) {
       return getMaxNoUseLock(noType, subType);
     }
     if ("true".equals(Config.getValue("App.UseMaxNoServer"))) {
       String str = getRemoteMaxNo(noType, subType, "NO");
       if ((str == null) || (str.equals(""))) {
         throw new RuntimeException("获取远程ID失败");
       }
       return str;
     }
     return getMaxNoLocal(noType, subType);
   }
 
   public static String getMaxNoLocal(String noType, String subType)
   {
     if (MaxNoSet == null) {
       init();
     }
     ZDMaxNoSchema maxno = null;
     if (MaxNoSet != null) {
       for (int i = 0; i < MaxNoSet.size(); ++i) {
         maxno = MaxNoSet.get(i);
         if ((maxno.getNoType().equals(noType)) && (maxno.getNoSubType().equals(subType)))
           synchronized (NoUtil.class) {
             maxno.setMaxValue(maxno.getMaxValue() + 1L);
             if (!(maxno.update())) {
               throw new RuntimeException("生成最大号错误,NoType=" + noType + ",MaxSubType=" + subType);
             }
             if (maxno.getLength() <= 0L) {
               return String.valueOf(maxno.getMaxValue());
             }
             return StringUtil.leftPad(String.valueOf(maxno.getMaxValue()), '0', (int)maxno.getLength());
           }
       }
     }
     else {
       synchronized (NoUtil.class) {
         MaxNoSet = new ZDMaxNoSet();
         maxno = new ZDMaxNoSchema();
         maxno.setNoType(noType);
         maxno.setNoSubType(subType);
         maxno.setLength(10L);
         maxno.setMaxValue(1L);
         maxno.insert();
         MaxNoSet.add(maxno);
         return "0000000001";
       }
     }
 
     synchronized (NoUtil.class) {
       maxno = new ZDMaxNoSchema();
       maxno.setNoType(noType);
       maxno.setNoSubType(subType);
       maxno.setLength(10L);
       maxno.setMaxValue(1L);
       maxno.insert();
       MaxNoSet.add(maxno);
       return "0000000001";
     }
   }
 
   private static synchronized void init() {
     if (MaxNoSet != null) {
       return;
     }
     ZDMaxNoSchema maxno = new ZDMaxNoSchema();
     MaxNoSet = maxno.query();
   }
 
   public static String getRemoteMaxNo(String NoType, String SubType, String NoType2) {
     String strurl = Config.getValue("App.MaxNoServerURL");
     if ((strurl == null) || (strurl.equals(""))) {
       return null;
     }
     StringBuffer sb = new StringBuffer();
     try {
       URL url = new URL(strurl + "?NoType=" + NoType + "&SubType=" + SubType + "&NoType2=" + NoType2);
       BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
       String s = null;
       while ((s = br.readLine()) != null) {
         sb.append(s);
         sb.append("\n");
       }
       br.close();
       return sb.toString().trim();
     } catch (Exception e) {
       e.printStackTrace(); }
     return null;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.pub.NoUtil
 * JD-Core Version:    0.5.3
 */