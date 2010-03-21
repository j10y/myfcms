 package com.zving.framework;
 
 import com.zving.framework.data.DataCollection;
 import com.zving.framework.data.DataTable;
 import java.lang.reflect.Method;
 
 public class Framework extends Page
 {
   public void getCodeData()
   {
     String CodeType = this.Request.getString("_ZVING_CODETYPE");
     String className = Config.getValue("App.CodeSource");
     String methodName = className.substring(className.lastIndexOf(".") + 1);
     className = className.substring(0, className.lastIndexOf("."));
     try {
       Class c = Class.forName(className);
       Method m = c.getMethod(methodName, new Class[] { String.class, DataCollection.class });
       Object d = m.invoke(null, new Object[] { CodeType, this.Request });
       if (d != null)
         this.Response.put("CodeData", (DataTable)d);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.Framework
 * JD-Core Version:    0.5.3
 */