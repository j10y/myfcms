 package com.zving.framework;
 
 import com.zving.framework.extend.ExtendManager;
 import com.zving.framework.schedule.CronManager;
 import com.zving.framework.utility.Mapx;
 import java.beans.Introspector;
 import javax.servlet.ServletContext;
 import javax.servlet.ServletContextEvent;
 import javax.servlet.ServletContextListener;
 import org.apache.commons.logging.LogFactory;
 import org.apache.log4j.LogManager;
 
 public class MainContextListener
   implements ServletContextListener
 {
   private CronManager manager;
 
   public void contextDestroyed(ServletContextEvent arg0)
   {
     if (this.manager != null) {
       this.manager.destory();
     }
     SessionListener.clear();
 
     LogManager.shutdown();
     LogFactory.releaseAll();
     Introspector.flushCaches();
   }
 
   public void contextInitialized(ServletContextEvent arg0)
   {
     ServletContext sc = arg0.getServletContext();
     Config.configMap.put("System.ContainerInfo", sc.getServerInfo());
     Config.getJBossInfo();
     Config.init();
     this.manager = CronManager.getInstance();
     ExtendManager.loadConfig();
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.MainContextListener
 * JD-Core Version:    0.5.3
 */