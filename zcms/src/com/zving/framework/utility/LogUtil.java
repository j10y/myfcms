 package com.zving.framework.utility;
 
 import com.zving.framework.Config;
 import java.io.OutputStream;
 import java.io.PrintStream;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.apache.log4j.PropertyConfigurator;
 
 public class LogUtil
 {
   private static boolean initFlag = false;
 
   private static Log zvinglog = null;
 
   public static void init() {
     PrintStream syserr = System.err;
     try {
       Log4jErrorPrintStream errStream = new Log4jErrorPrintStream(System.err);
       System.setErr(errStream);
       String fileName = Config.getClassesPath() + "log4j.config";
       String txt = FileUtil.readText(fileName);
       txt = StringUtil.replaceEx(txt, "%{ContextRealPath}", Config.getContextRealPath());
       FileUtil.writeText(fileName, txt);
       PropertyConfigurator.configure(fileName);
       zvinglog = LogFactory.getLog("ZVING");
     } catch (Exception e) {
       System.setErr(syserr);
       e.printStackTrace();
     }
   }
 
   public static Log getLogger() {
     if (!(initFlag)) {
       init();
       initFlag = true;
     }
     return zvinglog;
   }
 
   public static void info(Object obj) {
     getLogger().info(obj);
   }
 
   public static void debug(Object obj) {
     getLogger().debug(obj);
   }
 
   public static void warn(Object obj) {
     getLogger().warn(obj);
   }
 
   public static void error(Object obj) {
     getLogger().error(obj);
   }
 
   public static void fatal(Object obj) {
     zvinglog.fatal(obj);
   }
 
   static class Log4jErrorPrintStream extends PrintStream {
     Log4jErrorPrintStream(OutputStream out) {
       super(out, true);
     }
 
     public void println(String str) {
       LogUtil.zvinglog.error(str);
     }
 
     public void println(Object obj) {
       LogUtil.zvinglog.error(obj);
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.LogUtil
 * JD-Core Version:    0.5.3
 */