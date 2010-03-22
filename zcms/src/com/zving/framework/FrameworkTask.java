 package com.zving.framework;
 
 import com.zving.framework.schedule.GeneralTask;
 import com.zving.framework.utility.FileUtil;
 import java.io.File;
 
 public class FrameworkTask extends GeneralTask
 {
   public void execute()
   {
     File dir = new File(Config.getContextRealPath() + "WEB-INF/cache/");
     File[] fs = dir.listFiles();
     for (int i = 0; i < fs.length; ++i) {
       File f = fs[i];
       if (f.isFile()) {
         FileUtil.delete(f);
       }
     }
 
     dir = new File(Config.getContextRealPath() + "WEB-INF/data/backup/");
     fs = dir.listFiles();
     long cleanTime = System.currentTimeMillis() - 1440000L;
     for (int i = 0; i < fs.length; ++i) {
       File f = fs[i];
       if ((f.isFile()) && (!(f.getName().equalsIgnoreCase("install.dat"))) && (f.lastModified() < cleanTime))
         FileUtil.delete(f);
     }
   }
 
   public String getCronExpression()
   {
     return "30 10,16 * * *";
   }
 
   public long getID() {
     return 20080315112344L;
   }
 
   public String getName() {
     return "Framework定时任务";
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.FrameworkTask
 * JD-Core Version:    0.5.3
 */