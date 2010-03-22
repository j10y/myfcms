 package com.zving.cms.template;
 
 import com.zving.framework.Config;
 import com.zving.framework.script.EvalException;
 import com.zving.framework.utility.Errorx;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.LogUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.statical.TemplateParser;
 import java.io.File;
 
 public class ParserCache
 {
   private static Mapx parserMap;
   private static Mapx lastModifyMap;
 
   public static TemplateParser get(long siteID, String template, int level, boolean isPageBlock)
   {
     template = template.trim();
     if (parserMap == null) {
       parserMap = new Mapx();
       lastModifyMap = new Mapx();
     }
 
     File templateFile = new File(template);
     template = templateFile.getPath().replace('\\', '/');
 
     String key = template + level;
 
     TemplateParser tp = null;
 
     if (parserMap.get(key) == null) {
       if ((!(templateFile.exists())) || (templateFile.isDirectory())) {
         Errorx.addError("模板：" + template + " 不存在");
         LogUtil.info("模板：" + template + " 不存在");
         return null;
       }
       update(siteID, templateFile, level, isPageBlock);
       tp = (TemplateParser)parserMap.get(template + level);
     } else {
       if ((!(templateFile.exists())) || (templateFile.isDirectory())) {
         LogUtil.info("模板：" + template + " 不存在");
         return null;
       }
       long lastModifyTime = ((Long)lastModifyMap.get(template + level)).longValue();
       if (lastModifyTime != templateFile.lastModified()) {
         update(siteID, templateFile, level, isPageBlock);
       }
       tp = (TemplateParser)parserMap.get(template + level);
     }
 
     Mapx map = new Mapx();
     String serviceUrl = Config.getValue("ServicesContext");
     String context = serviceUrl;
     if (serviceUrl.endsWith("/")) {
       context = serviceUrl.substring(0, serviceUrl.length() - 1);
     }
     int index = context.lastIndexOf(47);
     if (index != -1) {
       context = context.substring(0, index);
     }
 
     map.put("servicescontext", serviceUrl);
     map.put("searchaction", context + "/Search/Result.jsp");
     map.put("voteaction", serviceUrl + Config.getValue("Vote.ActionURL"));
     map.put("voteresult", serviceUrl + Config.getValue("Vote.ResultURL"));
     map.put("commentaction", serviceUrl + Config.getValue("CommentActionURL"));
     map.put("totalhitcount", "\n<script src=\"" + serviceUrl + "/Counter.jsp?Type=Total&ID=" + siteID + 
       "\" type=\"text/javascript\"></script>\n");
     map.put("todayhitcount", "\n<script src=\"" + serviceUrl + "/Counter.jsp?Type=Today&ID=" + siteID + 
       "\" type=\"text/javascript\"></script>\n");
     tp.define("system", map);
 
     return tp;
   }
 
   public static synchronized void update(long siteID, File templateFile, int level, boolean isPageBlock) {
     String content = FileUtil.readText(templateFile);
     String templatePath = templateFile.getPath().replace('\\', '/');
 
     LogUtil.info("模板更新" + templatePath);
 
     TagParser tagParser = new TagParser();
     tagParser.setSiteID(siteID);
     tagParser.setTemplateFileName(templatePath);
     tagParser.setPageBlock(isPageBlock);
     tagParser.setContent(content);
     tagParser.setDirLevel(level);
     tagParser.parse();
 
     TemplateParser tp = new TemplateParser();
     tp.setFileName(templateFile.getPath());
     tp.addClass("com.zving.cms.pub.CatalogUtil");
     tp.addClass("com.zving.cms.pub.SiteUtil");
     tp.addClass("com.zving.cms.pub.PubFun");
     tp.setTemplate(tagParser.getContent());
     try
     {
       tp.parse();
     } catch (EvalException e) {
       e.printStackTrace();
     }
 
     tp.define("PageSize", tagParser.getPageSize());
     tp.define("DisplayLevel", tagParser.getDisplayLevel());
 
     parserMap.put(templatePath + level, tp);
     lastModifyMap.put(templatePath + level, new Long(templateFile.lastModified()));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.template.ParserCache
 * JD-Core Version:    0.5.3
 */