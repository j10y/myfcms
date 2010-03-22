 package com.zving.misc;
 
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.StringUtil;
 import java.io.File;
 
 public class TrimEmptyLine
 {
   public static void main(String[] args)
   {
     String path = "F:/Workspace_Platform/Platform/UI/Search/";
     File[] fs = new File(path).listFiles();
     for (int i = 0; i < fs.length; ++i) {
       File f = fs[i];
       if (!(f.getName().endsWith("jsp"))) {
         continue;
       }
       String content = FileUtil.readText(f);
       String[] arr = content.split("\\n");
       StringBuffer sb = new StringBuffer();
       for (int j = 0; j < arr.length; ++j) {
         if (StringUtil.isEmpty(arr[j].trim())) {
           if ((j < arr.length - 1) && (StringUtil.isEmpty(arr[(j + 1)].trim()))) {
             sb.append(arr[j].trim());
             sb.append("\n");
           }
         } else {
           sb.append(StringUtil.rightTrim(arr[j]));
           sb.append("\n");
         }
       }
       FileUtil.writeText(f.getAbsolutePath(), sb.toString());
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.misc.TrimEmptyLine
 * JD-Core Version:    0.5.3
 */