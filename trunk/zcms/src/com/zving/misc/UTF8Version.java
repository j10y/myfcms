 package com.zving.misc;
 
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.StringUtil;
 import java.io.File;
 import java.io.PrintStream;
 import java.io.UnsupportedEncodingException;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class UTF8Version
 {
   static Pattern p1 = Pattern.compile("charset\\s*\\=\\s*gbk", 34);
   static Pattern p2 = Pattern.compile("charset\\s*\\=\\s*gb2312", 34);
 
   public static void main(String[] args) {
     long t = System.currentTimeMillis();
 
     convertDir("F:/WorkSpace_Product/ZCMS/Java");
     convertDir("F:/WorkSpace_Platform/Framework/Java");
     convertDir("F:/WorkSpace_Product/ZCMS/Clean/");
     System.out.println(System.currentTimeMillis() - t);
   }
 
   public static void convertDir(String src) {
     File f = new File(src);
     File[] fs = f.listFiles();
 
     for (int i = 0; (fs != null) && (i < fs.length); ++i) {
       f = fs[i];
       String name = f.getName().toLowerCase();
       String dest = f.getAbsolutePath();
       dest = StringUtil.replaceEx(dest, "WorkSpace_Product\\ZCMS\\Clean", "WorkSpace_Product\\ZCMS_UTF8\\UI");
       dest = StringUtil.replaceEx(dest, "WorkSpace_Product\\ZCMS\\", "WorkSpace_Product\\ZCMS_UTF8\\");
       dest = StringUtil.replaceEx(dest, "WorkSpace_Platform\\Framework", "WorkSpace_Product\\ZCMS_UTF8");
       if (name.equals(".svn")) {
         continue;
       }
       if (f.isDirectory()) {
         FileUtil.mkdir(dest);
         if (name.equals("classes")) {
           continue;
         }
         if ((dest.indexOf("wwwroot") > 0) && (!(dest.endsWith("wwwroot"))) && 
           (dest.indexOf("ZCMSDemo") < 0)) {
           continue;
         }
 
         convertDir(f.getAbsolutePath());
       }
       else {
         if ((dest.indexOf("ZCMSDemo") > 0) && (dest.endsWith(".shtml"))) {
           continue;
         }
         if ((name.endsWith(".java")) || (name.endsWith(".xml"))) {
           String txt = FileUtil.readText(f, "GBK");
           if ((name.endsWith(".xml")) && 
             (txt.indexOf("UTF-8") > 0)) {
             txt = FileUtil.readText(f, "UTF-8");
           }
 
           if (name.equals("web.xml")) {
             txt = StringUtil.replaceEx(txt, "<page-encoding>GBK</page-encoding>", 
               "<page-encoding>UTF-8</page-encoding>");
           }
           if (name.equals("constant.java"))
             txt = StringUtil.replaceEx(txt, "GlobalCharset = \"GBK\";", "GlobalCharset = \"UTF-8\";");
           try
           {
             txt = new String(StringUtil.GBKToUTF8(txt), "UTF-8");
           } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
           }
           FileUtil.writeText(dest, txt, "UTF-8", name.endsWith(".java"));
         } else if ((name.endsWith(".html")) || (name.endsWith(".shtml")) || (name.endsWith(".htm")) || 
           (name.endsWith(".jsp")) || (name.endsWith(".js")) || (name.endsWith(".css"))) {
           byte[] bs = FileUtil.readByte(f);
           if (StringUtil.isUTF8(bs)) {
             FileUtil.copy(f, dest);
           } else {
             String txt = null;
             try {
               txt = new String(bs, "GBK");
             } catch (UnsupportedEncodingException e1) {
               e1.printStackTrace();
             }
             txt = p1.matcher(txt).replaceAll("charset=utf-8");
             txt = p2.matcher(txt).replaceAll("charset=utf-8");
             txt = StringUtil.replaceEx(txt, "\"GBK\"", "\"UTF-8\"");
             try {
               txt = new String(StringUtil.GBKToUTF8(txt), "UTF-8");
             } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
             }
             FileUtil.writeText(dest, txt, "UTF-8");
           }
         } else {
           FileUtil.copy(f, dest);
         }
         System.out.println(dest);
       }
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.misc.UTF8Version
 * JD-Core Version:    0.5.3
 */