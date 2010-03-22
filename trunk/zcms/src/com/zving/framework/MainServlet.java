 package com.zving.framework;
 
 import com.zving.framework.data.DataCollection;
 import com.zving.framework.extend.ExtendManager;
 import java.io.IOException;
 import java.io.PrintWriter;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 
 public class MainServlet extends HttpServlet
 {
   private static final long serialVersionUID = 1L;
 
   public void service(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     try
     {
       response.setHeader("Pragma", "No-Cache");
       response.setHeader("Cache-Control", "No-Cache");
       response.setDateHeader("Expires", 0L);
 
       response.setContentType("text/xml");
 
       if ((Config.ServletMajorVersion == 2) && (Config.ServletMinorVersion == 3))
         response.setContentType("text/xml;charset=utf-8");
       else {
         response.setCharacterEncoding("UTF-8");
       }
       request.setCharacterEncoding("UTF-8");
 
       String method = request.getParameter("_ZVING_METHOD");
       String url = request.getParameter("_ZVING_URL");
       if (("".equals(url)) || ("/".equals(url))) {
         url = "/Index.jsp";
       }
 
       Current.init(request, response, method);
 
       String className = method.substring(0, method.lastIndexOf("."));
       Class c = Class.forName(className);
 
       String LoginClass = Config.getValue("App.LoginClass");
       DataCollection dcResponse;
       if ((!(Ajax.class.isAssignableFrom(c))) && (!(className.equals("com.zving.framework.Framework"))) && 
         (!(className.equals(LoginClass))) && (!(User.isLogin()))) {
         dcResponse = new DataCollection();
         dcResponse.put("_ZVING_SCRIPT", "window.top.location='" + Config.getContextPath() + 
           Config.getValue("App.LoginPage") + "';");
         response.getWriter().write(dcResponse.toXML());
         return;
       }
 
       if ((!(className.equals(LoginClass))) && (!(SessionCheck.check(c)))) {
         dcResponse = new DataCollection();
         dcResponse.put("_ZVING_MESSAGE", "不允许越权访问!");
         response.getWriter().write(dcResponse.toXML());
         return;
       }
 
       if (ExtendManager.hasAction("BeforePageMethodInvoke")) {
         ExtendManager.executeAll("BeforePageMethodInvoke", new Object[] { method });
       }
 
       Current.invokeMethod(method, null);
 
       if (ExtendManager.hasAction("AfterPageMethodInvoke")) {
         ExtendManager.executeAll("AfterPageMethodInvoke", new Object[] { method });
       }
 
       response.getWriter().write(Current.getResponse().toXML());
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.MainServlet
 * JD-Core Version:    0.5.3
 */