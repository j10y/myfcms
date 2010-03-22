 package com.zving.cms.stat.impl;
 
 import com.zving.cms.stat.AbstractStat;
 import com.zving.cms.stat.Visit;
 import com.zving.cms.stat.VisitCount;
 import com.zving.framework.utility.StringUtil;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class ClientStat extends AbstractStat
 {
   private static final String[] avgSubTypes = { "StickTime" };
   private static final String Type = "Client";
   Pattern srPattern = Pattern.compile("\\d{2,4}x\\d{2,4}", 34);
 
   public String getStatType()
   {
     return "Client";
   }
 
   public String[] getAverageSubTypes() {
     return avgSubTypes;
   }
 
   public void deal(Visit v)
   {
     if ("Unload".equals(v.Event)) {
       return;
     }
     if (StringUtil.isEmpty(v.FlashVersion))
       v.FlashVersion = "其他";
     else {
       v.FlashVersion = v.FlashVersion.replaceAll("(\\%20)+", " ");
     }
     String cd = v.ColorDepth;
     if (StringUtil.isEmpty(cd)) {
       cd = "其他";
     } else {
       cd = cd.replaceAll("\\D", "");
       if ((cd.equals("8")) || (cd.equals("16")) || (cd.equals("24")) || (cd.equals("32")))
         cd = cd + "-bit";
       else {
         cd = "其他";
       }
     }
     v.ColorDepth = cd;
 
     String sr = v.Screen;
     if (!(this.srPattern.matcher(sr).matches())) {
       sr = "其他";
     }
 
     VisitCount.getInstance().add(v.SiteID, "Client", "ColorDepth", v.ColorDepth);
     VisitCount.getInstance().add(v.SiteID, "Client", "OS", v.OS);
     VisitCount.getInstance().add(v.SiteID, "Client", "Browser", v.Browser);
     VisitCount.getInstance().add(v.SiteID, "Client", "District", v.District);
     VisitCount.getInstance().add(v.SiteID, "Client", "Language", v.Language);
     VisitCount.getInstance().add(v.SiteID, "Client", "FlashVersion", v.FlashVersion);
     VisitCount.getInstance().add(v.SiteID, "Client", "JavaEnabled", String.valueOf(v.JavaEnabled));
     VisitCount.getInstance().add(v.SiteID, "Client", "CookieEnabled", String.valueOf(v.CookieEnabled));
     VisitCount.getInstance().add(v.SiteID, "Client", "Screen", v.Screen);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.impl.ClientStat
 * JD-Core Version:    0.5.3
 */