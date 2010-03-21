 package com.zving.misc;
 
 import java.io.File;
 import java.io.PrintStream;
 
 public class MXIFileInfo
 {
   public static void main(String[] args)
   {
     File[] fs = new File("D:/Program Files/Adobe/Adobe Extension Manager CS4/Samples/Dreamweaver/ZCMS").listFiles();
     StringBuffer sb = new StringBuffer();
     for (int i = 0; i < fs.length; ++i) {
       String name = fs[i].getName();
       String prefix = name.substring(0, name.indexOf(46));
       System.out.println("<file source=\"" + name + "\" destination=\"$dreamweaver/configuration/Objects/ZCMS\" />");
       if (name.endsWith(".htm")) {
         sb.append("<button file=\"ZCMS/" + name + "\" id=\"ZCMS_Template_" + prefix + "\" image=\"ZCMS/" + prefix + 
           ".gif\" />\n");
       }
     }
     System.out.println("\n\n");
     System.out.println(sb);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.misc.MXIFileInfo
 * JD-Core Version:    0.5.3
 */