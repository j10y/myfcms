 package com.zving.framework.plugin;
 
 public class PluginManager
 {
   public static final int INSTALL_SUCCESS = 0;
   public static final int INSTALL_FAIL = 0;
   public static final int UNINSTALL_SUCCESS = 0;
   public static final int UNINSTALL_FAIL = 0;
 
   public static void onStartup()
   {
   }
 
   public static void install(String pluginFileName)
   {
   }
 
   public static IPlugin getPlugin(String pluginID)
   {
     return null;
   }
 
   public static void pause(String pluginID)
   {
   }
 
   public static void reuse(String pluginID)
   {
   }
 
   public static void uninstall(String pluginID)
   {
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.plugin.PluginManager
 * JD-Core Version:    0.5.3
 */