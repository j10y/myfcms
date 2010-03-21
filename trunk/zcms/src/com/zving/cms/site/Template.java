 package com.zving.cms.site;
 
 import com.zving.cms.datachannel.Deploy;
 import com.zving.cms.template.PreParser;
 import com.zving.framework.Config;
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.controls.TreeAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.LogUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.framework.utility.ZipUtil;
 import com.zving.platform.Application;
 import java.io.File;
 import java.io.FileFilter;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import org.apache.commons.io.filefilter.FileFilterUtils;
 import org.apache.commons.logging.Log;
 
 public class Template extends Page
 {
   private static final Pattern cssImgPattern = Pattern.compile("url\\(\\s*?(\\\"|\\'*)?(.*?)\\1\\)", 
     34);
 
   Pattern resourcePattern1 = Pattern.compile("(src|href|background|file|virtual)\\s*?=\\s*?([^\\\"\\'\\s]+)(\\s|>|/>)", 34);
 
   private static final Pattern resourcePattern2 = Pattern.compile(
     "(src|href|background|file|virtual)\\s*?=\\s*?(\"|')(.*?)\\2", 34);
 
   Map inlcudeMap = new HashMap();
   DirectoryFilter dFilter;
   int count;
   Map treeMap;
 
   public Template()
   {
     this.dFilter = new DirectoryFilter();
   }
 
   public Template(String filterName) {
     this.dFilter = new DirectoryFilter(filterName);
   }
 
   public static void treeDataBind(TreeAction ta) {
     Object obj = ta.getParams().get("SiteID");
     String siteID = Application.getCurrentSiteID();
     DataTable dt = new QueryBuilder("select ID,ParentID,Level,Name from ZCCatalog Where SiteID=?", siteID).executeDataTable();
     String siteName = new QueryBuilder("select name from ZCSite where id=?", siteID).executeString();
     ta.setRootText(siteName);
     ta.bindData(dt);
   }
 
   public boolean unzipFile(String zipFileName, String upzipPath, String siteCode)
   {
     String copyToPath = Config.getContextRealPath() + Config.getValue("Statical.TemplateDir") + "/" + siteCode;
     if (ZipUtil.unzip(zipFileName, upzipPath, false)) {
       FileUtil.delete(zipFileName);
     }
 
     ArrayList deployList = new ArrayList();
     File unzipFile = new File(upzipPath);
     File[] fileList = unzipFile.listFiles();
     for (int i = 0; i < fileList.length; ++i) {
       File file = fileList[i];
       String fileName = file.getName();
       String ext = (fileName.lastIndexOf(".") == -1) ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
       if (("html".equalsIgnoreCase(ext)) || ("htm".equalsIgnoreCase(ext)) || ("jsp".equalsIgnoreCase(ext)) || 
         ("php".equalsIgnoreCase(ext)) || ("jsp".equalsIgnoreCase(ext)) || ("asp".equalsIgnoreCase(ext)) || 
         ("css".equalsIgnoreCase(ext))) {
         String fileText = FileUtil.readText(file);
         fileText = dealResource(fileText);
         fileText = dealCssResource(fileText);
         String tplPath = copyToPath + "/template/";
 
         if ("css".equalsIgnoreCase(ext)) {
           tplPath = copyToPath + "/images/";
         }
 
         FileUtil.mkdir(tplPath);
         FileUtil.writeText(tplPath + fileName, fileText);
 
         deployList.add(tplPath + fileName);
 
         PreParser p = new PreParser();
         p.setTemplateFileName(tplPath + fileName);
         p.parse();
       } else {
         FileUtil.mkdir(copyToPath + "/images/");
         FileUtil.copy(file, copyToPath + "/images/" + fileName);
 
         deployList.add(copyToPath + "/images/" + fileName);
       }
 
     }
 
     FileUtil.delete(unzipFile);
 
     Deploy d = new Deploy();
     d.addJobs(Application.getCurrentSiteID(), deployList);
 
     return true;
   }
 
   public boolean processFile(String fileFullName, String siteCode)
   {
     ArrayList deployList = new ArrayList();
     File file = new File(fileFullName);
     if (!(file.exists())) {
       return false;
     }
     String fileName = file.getName();
 
     String copyToPath = Config.getContextRealPath() + Config.getValue("Statical.TemplateDir") + "/" + siteCode;
 
     String ext = (fileName.lastIndexOf(".") == -1) ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
     if (("html".equalsIgnoreCase(ext)) || ("htm".equalsIgnoreCase(ext)) || ("jsp".equalsIgnoreCase(ext)) || 
       ("js".equalsIgnoreCase(ext)) || ("php".equalsIgnoreCase(ext)) || ("jsp".equalsIgnoreCase(ext)) || 
       ("asp".equalsIgnoreCase(ext)) || ("css".equalsIgnoreCase(ext))) {
       String fileText = FileUtil.readText(file);
       fileText = dealResource(fileText);
       fileText = dealCssResource(fileText);
       String tplPath = copyToPath + "/template/";
 
       if ("css".equalsIgnoreCase(ext)) {
         tplPath = copyToPath + "/images/";
       }
 
       FileUtil.mkdir(tplPath);
       FileUtil.writeText(tplPath + fileName, fileText);
       deployList.add(tplPath + fileName);
 
       PreParser p = new PreParser();
       p.setTemplateFileName(tplPath + fileName);
       p.parse();
     } else {
       FileUtil.mkdir(copyToPath + "/images/");
       FileUtil.copy(file, copyToPath + "/images/" + fileName);
       deployList.add(copyToPath + "/images/" + fileName);
     }
 
     FileUtil.delete(file);
 
     Deploy d = new Deploy();
     d.addJobs(Application.getCurrentSiteID(), deployList);
 
     return true;
   }
 
   public String dealResource(String content)
   {
     Matcher m = this.resourcePattern1.matcher(content);
     StringBuffer sb = new StringBuffer();
     int lastEndIndex = 0;
     String dealPath;
     while (m.find(lastEndIndex)) {
       System.out.println("1===" + m.group(2));
       dealPath = processText(m.group(1), m.group(2));
       sb.append(content.substring(lastEndIndex, m.start()));
       sb.append(m.group(1) + "=\"" + dealPath + "\" ");
       lastEndIndex = m.end();
     }
     sb.append(content.substring(lastEndIndex));
 
     content = sb.toString();
     sb = new StringBuffer();
     m = resourcePattern2.matcher(content);
     lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       dealPath = processText(m.group(1), m.group(3));
       sb.append(content.substring(lastEndIndex, m.start()));
       sb.append(m.group(1) + "=\"" + dealPath + "\" ");
       lastEndIndex = m.end();
     }
     sb.append(content.substring(lastEndIndex));
     return sb.toString();
   }
 
   private String processText(String srcType, String resourcePath) {
     String strPath = "";
     if ("#".equals(resourcePath)) {
       strPath = resourcePath;
     } else if ((resourcePath.startsWith("${")) || (resourcePath.toLowerCase().startsWith("<cms:")) || 
       (resourcePath.toLowerCase().startsWith("http")) || (resourcePath.toLowerCase().startsWith("https")) || 
       (resourcePath.toLowerCase().startsWith("ftp"))) {
       strPath = resourcePath;
     }
     else if (resourcePath.indexOf("//") == -1)
     {
       String fileName = "";
       if (resourcePath.indexOf("/") != -1) {
         fileName = resourcePath.substring(resourcePath.lastIndexOf("/") + 1);
         if (resourcePath.length() - 1 == resourcePath.lastIndexOf("/"))
           fileName = resourcePath;
       }
       else {
         fileName = resourcePath;
       }
 
       String ext = (resourcePath.indexOf(".") == -1) ? "" : resourcePath.substring(resourcePath
         .lastIndexOf(".") + 1);
       ext = ext.toLowerCase().trim();
 
       if (("file".equalsIgnoreCase(srcType)) || ("virtual".equalsIgnoreCase(srcType))) {
         strPath = "../include/" + fileName;
         this.inlcudeMap.put(fileName, fileName);
       } else if (("html".equals(ext)) || ("htm".equals(ext)) || ("shtml".equals(ext)) || ("jsp".equals(ext)) || 
         ("php".equals(ext)) || ("jsp".equals(ext)) || ("asp".equals(ext)) || ("".equals(ext))) {
         strPath = resourcePath;
       } else {
         strPath = "../images/" + fileName;
       }
     }
 
     LogUtil.getLogger().info("原路径：" + resourcePath + ",处理后路径：" + strPath);
     return strPath;
   }
 
   public String dealCssResource(String content)
   {
     Matcher m = cssImgPattern.matcher(content);
     StringBuffer sb = new StringBuffer();
     int lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       int s = m.start();
       int e = m.end();
       sb.append(content.substring(lastEndIndex, s));
       System.out.println(m.group(2));
       String imagePath = m.group(2);
       if (imagePath.indexOf("//") == -1) {
         String fileName = (imagePath.indexOf("/") == -1) ? imagePath : imagePath.substring(imagePath
           .lastIndexOf("/") + 1);
         imagePath = fileName;
       }
       sb.append("url(\"" + imagePath + "\")");
 
       lastEndIndex = e;
     }
     sb.append(content.substring(lastEndIndex));
     return sb.toString();
   }
 
   public void preParse() {
     String path = $V("Path");
     if (StringUtil.isEmpty(path)) {
       this.Response.setStatus(0);
       this.Response.setMessage("模板路径为空!");
       return;
     }
 
     File file = new File(path);
     boolean flag = true;
     if (file.exists()) {
       PreParser p = new PreParser();
       File[] templates = file.listFiles(FileFilterUtils.makeSVNAware(FileFilterUtils.trueFileFilter()));
       for (int i = 0; i < templates.length; ++i) {
         p.setTemplateFileName(templates[i].getPath());
         if (!(p.parse()))
           flag = true;
       }
     }
     else {
       this.Response.setStatus(0);
       this.Response.setMessage("文件不存在!");
       return;
     }
 
     if (flag) {
       this.Response.setStatus(0);
       this.Response.setMessage("处理成功!");
     } else {
       this.Response.setStatus(1);
       this.Response.setMessage("处理失败!");
     }
   }
 
   class DirectoryFilter implements FileFilter
   {
     String filterName;
 
     public DirectoryFilter()
     {
     }
 
     public DirectoryFilter(String paramString) {
       this.filterName = paramString;
     }
 
     public boolean accept(File f) {
       if (f.isDirectory()) {
         if ((this.filterName == null) || ("".equals(this.filterName))) {
           return true;
         }
         return (this.filterName.equals(f.getName()));
       }
 
       return false;
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.site.Template
 * JD-Core Version:    0.5.3
 */