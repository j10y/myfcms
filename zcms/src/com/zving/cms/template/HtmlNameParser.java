 package com.zving.cms.template;
 
 import com.zving.cms.pub.CatalogUtil;
 import com.zving.framework.Constant;
 import com.zving.framework.data.DataColumn;
 import com.zving.framework.data.DataRow;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.util.Date;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class HtmlNameParser
 {
   private String nameRule;
   private DataRow site;
   private DataRow catalog;
   private DataRow document;
   private static Mapx cacheMap = new Mapx(1000);
 
   public HtmlNameParser(DataRow site, DataRow catalog, DataRow document, String nameRule) {
     this.site = site;
     if ((catalog == null) && (document != null))
     {
       long catalogID = document.getLong("CatalogID");
       DataColumn[] types = new DataColumn[2];
       types[0] = new DataColumn("SingleFlag", 1);
       types[1] = new DataColumn("ID", 7);
 
       Object[] values = { CatalogUtil.getSingleFlag(catalogID), catalogID };
       this.catalog = new DataRow(types, values);
     } else {
       this.catalog = catalog;
     }
 
     this.document = document;
     this.nameRule = nameRule;
   }
 
   public HtmlNameRule getNameRule() {
     HtmlNameRule rule = (HtmlNameRule)cacheMap.get(this.catalog.getString("id") + this.nameRule);
     if (rule == null) {
       rule = parse();
       if (!(StringUtil.isEmpty(this.nameRule))) {
         cacheMap.put(this.catalog.getString("id") + this.nameRule, rule);
       }
     }
     return rule;
   }
 
   private HtmlNameRule parse() {
     StringBuffer sb = new StringBuffer();
     String fullPath = null; String dirPath = null; String fileName = null;
     if ("Y".equals(this.catalog.getString("SingleFlag"))) {
       fullPath = CatalogUtil.getPath(this.catalog.getString("ID")) + "/" + "index.shtml";
     }
     else if (StringUtil.isNotEmpty(this.nameRule)) {
       Date date = null;
       long timestamp;
       if (this.document.get("PublishDate") != null) {
         date = this.document.getDate("PublishDate");
         timestamp = date.getTime();
       } else {
         date = new Date();
         timestamp = System.currentTimeMillis();
       }
 
       Matcher m = Constant.PatternField.matcher(this.nameRule);
       int lastEndIndex = 0;
       while (m.find(lastEndIndex)) {
         sb.append(this.nameRule.substring(lastEndIndex, m.start()));
         String field = m.group(1);
 
         if (field != null) {
           field = field.toLowerCase();
         }
 
         if ("year".equals(field))
           sb.append(DateUtil.toString(date, "yyyy"));
         else if ("month".equals(field))
           sb.append(DateUtil.toString(date, "MM"));
         else if ("day".equals(field))
           sb.append(DateUtil.toString(date, "dd"));
         else if ("timestamp".equals(field))
           sb.append(timestamp);
         else if ("timestamp".equals(field))
           sb.append(timestamp);
         else if ("catalogpath".equals(field)) {
           sb.append(CatalogUtil.getPath(this.catalog.getString("id")));
         }
         lastEndIndex = m.end();
       }
       sb.append(this.nameRule.substring(lastEndIndex));
 
       this.nameRule = sb.toString();
       sb = new StringBuffer();
       m = Constant.PatternPropField.matcher(this.nameRule);
       lastEndIndex = 0;
       while (m.find(lastEndIndex)) {
         sb.append(this.nameRule.substring(lastEndIndex, m.start()));
         String table = m.group(1);
         String field = m.group(2);
 
         if (table != null) {
           table = table.toLowerCase();
         }
         if (field != null) {
           field = field.toLowerCase();
         }
 
         DataRow row = null;
         if ("site".equals(table))
           row = this.site;
         else if ("catalog".equals(table))
           row = this.catalog;
         else if ("document".equals(table)) {
           row = this.document;
         }
 
         if (row != null) {
           sb.append(row.getString(field));
         }
 
         lastEndIndex = m.end();
       }
       sb.append(this.nameRule.substring(lastEndIndex));
 
       fullPath = sb.toString();
     }
     else {
       fullPath = CatalogUtil.getPath(this.catalog.getString("id")) + "/" + this.document.getString("ID") + ".shtml";
     }
 
     fullPath = fullPath.replaceAll("///", "/");
     fullPath = fullPath.replaceAll("//", "/");
     if (fullPath.startsWith("/")) {
       fullPath = fullPath.substring(1);
     }
 
     int level = StringUtil.count(fullPath, "/");
     int index = fullPath.lastIndexOf("/");
 
     if (index != -1) {
       dirPath = fullPath.substring(0, index);
       fileName = fullPath.substring(index + 1);
     } else {
       fileName = fullPath;
     }
 
     HtmlNameRule h = new HtmlNameRule();
     h.setFullPath(fullPath);
     h.setLevel(level);
     h.setDirPath(dirPath);
     h.setFileName(fileName);
 
     return h;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.template.HtmlNameParser
 * JD-Core Version:    0.5.3
 */