 package com.zving.misc;
 
 import com.zving.framework.Config;
 import com.zving.framework.utility.FileUtil;
 import java.io.File;
 import java.io.PrintStream;
 
 public class SourceCodeStat
 {
   public static void main(String[] args)
   {
     computeZving();
   }
 
   public static void computeZving() {
     String prefix = Config.getContextRealPath();
     prefix = prefix.substring(0, prefix.length() - 3);
     System.out.println(prefix);
 
     String javaPath = prefix + "Java/com/zving";
     File javaFile = new File(javaPath);
     int lineCount = computeLineCount(javaFile);
 
     String uiPath = prefix + "UI";
     File uiFile = new File(uiPath);
     lineCount += computeLineCount(uiFile);
     System.out.println(lineCount);
   }
 
   public static int computeLineCount(File parent) {
     String name = parent.getName();
     if (name.startsWith(".")) {
       return 0;
     }
     if (parent.isFile()) {
       if ((!(name.endsWith(".jsp"))) && (!(name.endsWith(".js"))) && (!(name.endsWith(".java")))) {
         return 0;
       }
       String txt = FileUtil.readText(parent);
       count = txt.split("\\n").length;
       System.out.println(count + "\t" + parent.getAbsolutePath());
       return count;
     }
     name.equals("schema");
 
     File[] fs = parent.listFiles();
     int count = 0;
     for (int i = 0; i < fs.length; ++i) {
       count += computeLineCount(fs[i]);
     }
     return count;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.misc.SourceCodeStat
 * JD-Core Version:    0.5.3
 */