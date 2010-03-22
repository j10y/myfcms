 package com.zving.framework.plugin;
 
 public abstract class AbstractPlugin
   implements IPlugin
 {
   public String[] getExtendClasses()
   {
     return null;
   }
 
   public String[] getCronTaskClases() {
     return null;
   }
 
   public String[] getCacheClasses() {
     return null;
   }
 
   public IPluginInstaller getInstaller() {
     return new DefaultPluginInstaller(getPluginID());
   }
 
   public IPluginUninstaller getUninstaller() {
     return new DefaultPluginUninstaller(getPluginID());
   }
 
   public void onPause()
   {
   }
 
   public void onReuse()
   {
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.plugin.AbstractPlugin
 * JD-Core Version:    0.5.3
 */