package com.zving.framework.plugin;

import java.util.Date;

public abstract interface IPlugin
{
  public abstract String getVesion();

  public abstract Date getPublishDate();

  public abstract String getName();

  public abstract String getAuthor();

  public abstract String getPluginID();

  public abstract PluginMenu[] getMenus();

  public abstract String[] getExtendClasses();

  public abstract String[] getCronTaskClases();

  public abstract PluginTable[] getTables();

  public abstract String[] getCacheClasses();

  public abstract String[] getInstallFileNames();

  public abstract String[] getUninstallFileNames();

  public abstract IPluginInstaller getInstaller();

  public abstract IPluginUninstaller getUninstaller();

  public abstract void onPause();

  public abstract void onReuse();
}

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.plugin.IPlugin
 * JD-Core Version:    0.5.3
 */