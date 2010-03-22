 package com.zving.framework.plugin;
 
 import java.io.PrintStream;
 
 public class DefaultPluginUninstaller
   implements IPluginUninstaller
 {
   private String pluginID;
 
   public DefaultPluginUninstaller(String pluginID)
   {
     this.pluginID = pluginID;
   }
 
   public int uninstall()
   {
     System.out.println(this.pluginID);
     return 0;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.plugin.DefaultPluginUninstaller
 * JD-Core Version:    0.5.3
 */