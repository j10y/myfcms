 package com.zving.misc;
 
 import com.zving.framework.Config;
 import com.zving.framework.orm.DBExport;
 
 public class DBConvert
 {
   public static void main(String[] args)
   {
     new DBExport().exportDB(Config.getContextRealPath() + "WEB-INF/data/backup/install.dat");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.misc.DBConvert
 * JD-Core Version:    0.5.3
 */