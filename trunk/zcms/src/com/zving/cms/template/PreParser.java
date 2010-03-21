 package com.zving.cms.template;
 
 import com.zving.cms.dataservice.AdvertiseLayout;
 import com.zving.framework.User;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCAdPositionSchema;
 import com.zving.schema.ZCImagePlayerSchema;
 import java.util.Date;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class PreParser
 {
   private static final Pattern cmsAD = Pattern.compile("<cms:ad\\s(.*?)(/>|>(.*?)</cms:ad>)", 34);
 
   private static final Pattern cmsImagePlayer = Pattern.compile("<cms:imageplayer\\s(.*?)(/>|>(.*?)</cms:imageplayer>)", 34);
 
   private static final Pattern pAttr1 = Pattern.compile("\\s*?(\\w+?)\\s*?=\\s*?(\\\"|\\')(.*?)\\2", 34);
 
   private static final Pattern pAttr2 = Pattern.compile("\\s*?(\\w+?)\\s*?=\\s*?([^\\'\\\"\\s]+)", 34);
   private String templateFileName;
   private String content;
   private long siteID;
 
   public PreParser()
   {
     this.siteID = Application.getCurrentSiteID();
   }
 
   public boolean parse() {
     this.content = FileUtil.readText(this.templateFileName);
     if (StringUtil.isEmpty(this.content)) {
       return true;
     }
 
     return ((parseAD()) && (parseImagePlayer()));
   }
 
   private boolean parseAD()
   {
     Matcher m = cmsAD.matcher(this.content);
     int lastEndIndex = 0;
     Transaction trans = new Transaction();
     while (m.find(lastEndIndex)) {
       lastEndIndex = m.end();
       Mapx map = getAttrMap(m.group(1));
       String name = (String)map.get("name");
       String type = (String)map.get("type");
       String size = (String)map.get("size");
       String description = (String)map.get("description");
 
       ZCAdPositionSchema ad = new ZCAdPositionSchema();
       int NameCount = new QueryBuilder("select count(*) from zcadposition where PositionName = ? and siteid=?", name, this.siteID).executeInt();
       if (NameCount > 0) {
         continue;
       }
       ad.setID(NoUtil.getMaxID("AdPositionID"));
       ad.setCode(ad.getID());
       ad.setAddUser(User.getUserName());
       ad.setAddTime(new Date());
       ad.setJsName(AdvertiseLayout.createJS("add", ad));
 
       ad.setSiteID(this.siteID);
       ad.setPositionName(name);
       ad.setDescription(description);
       ad.setPositionType(type);
       if (StringUtil.isNotEmpty(size))
       {
         String[] arr = size.split("\\*");
         ad.setPositionWidth(arr[0]);
         ad.setPositionHeight(arr[1]);
       }
       trans.add(ad, 1);
     }
 
     return (trans.commit());
   }
 
   private boolean parseImagePlayer()
   {
     Matcher m = cmsImagePlayer.matcher(this.content);
     int lastEndIndex = 0;
     Transaction trans = new Transaction();
     while (m.find(lastEndIndex)) {
       lastEndIndex = m.end();
       Mapx map = getAttrMap(m.group(1));
       String name = (String)map.get("name");
       if (StringUtil.isEmpty(name)) {
         name = (String)map.get("code");
       }
       String code = name;
       String width = (String)map.get("width");
       if (StringUtil.isEmpty(width)) {
         width = "100";
       }
       String height = (String)map.get("height");
       if (StringUtil.isEmpty(height)) {
         height = "100";
       }
       String count = (String)map.get("count");
       if (StringUtil.isEmpty(count)) {
         count = "5";
       }
 
       ZCImagePlayerSchema imagePlayer = new ZCImagePlayerSchema();
       int NameCount = new QueryBuilder("select count(*) from ZCImagePlayer where name = ? and siteid=?", name, this.siteID).executeInt();
       int CodeCount = new QueryBuilder("select count(*) from ZCImagePlayer where code = ? and siteid=?", code, this.siteID).executeInt();
       if (NameCount > 0) continue; if (CodeCount > 0) {
         continue;
       }
       imagePlayer.setID(NoUtil.getMaxID("ImagePlayerID"));
       imagePlayer.setCode(code);
       imagePlayer.setName(name);
       imagePlayer.setImageSource("0");
       imagePlayer.setProp1("N");
       imagePlayer.setAddUser(User.getUserName());
       imagePlayer.setAddTime(new Date());
       imagePlayer.setSiteID(this.siteID);
       imagePlayer.setDisplayType("0");
       imagePlayer.setWidth(width);
       imagePlayer.setHeight(height);
       imagePlayer.setDisplayCount(count);
 
       trans.add(imagePlayer, 1);
     }
 
     return (trans.commit());
   }
 
   private static Mapx getAttrMap(String str)
   {
     Mapx map = new Mapx();
     Matcher m = pAttr1.matcher(str);
     int lastEndIndex = 0;
     String value;
     while (m.find(lastEndIndex)) {
       value = m.group(3);
       if (value != null) {
         value = value.trim();
       }
       map.put(m.group(1).toLowerCase(), value);
       lastEndIndex = m.end();
     }
 
     m = pAttr2.matcher(str);
     lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       value = m.group(2);
       if (value != null) {
         value = value.trim();
       }
       map.put(m.group(1).toLowerCase(), value);
       lastEndIndex = m.end();
     }
     return map;
   }
 
   public String getTemplateFileName() {
     return this.templateFileName;
   }
 
   public void setTemplateFileName(String templateFileName) {
     this.templateFileName = templateFileName;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.template.PreParser
 * JD-Core Version:    0.5.3
 */