 package com.zving.platform.pub;
 
 import com.zving.framework.Config;
 import java.io.IOException;
 import java.io.PrintWriter;
 import javax.servlet.Servlet;
 import javax.servlet.ServletConfig;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 
 public class MaxNoServlet
   implements Servlet
 {
   public void destroy()
   {
   }
 
   public ServletConfig getServletConfig()
   {
     return null;
   }
 
   public String getServletInfo() {
     return null;
   }
 
   public void init(ServletConfig arg0) throws ServletException {
   }
 
   public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
     if (!("true".equals(Config.getValue("App.isMaxNoServer")))) {
       return;
     }
     String NoType = arg0.getParameter("NoType");
     String SubType = arg0.getParameter("SubType");
     String NoType2 = arg0.getParameter("NoType2");
     if ((NoType2 == null) || (NoType2.equals(""))) {
       NoType2 = "ID";
     }
     if ((SubType == null) || (SubType.equals(""))) {
       SubType = "SN";
     }
     if ("ID".equals(NoType2))
       arg1.getWriter().print(NoUtil.getMaxIDLocal(NoType));
     else
       arg1.getWriter().print(NoUtil.getMaxIDLocal(NoType));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.pub.MaxNoServlet
 * JD-Core Version:    0.5.3
 */