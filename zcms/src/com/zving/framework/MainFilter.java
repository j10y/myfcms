 package com.zving.framework;
 
 import com.zving.framework.extend.ExtendManager;
 import com.zving.framework.utility.Errorx;
 import com.zving.framework.utility.LogUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.io.IOException;
 import javax.servlet.Filter;
 import javax.servlet.FilterChain;
 import javax.servlet.FilterConfig;
 import javax.servlet.RequestDispatcher;
 import javax.servlet.ServletContext;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.Cookie;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.apache.commons.logging.Log;
 
 public class MainFilter
   implements Filter
 {
   private long uptime = 0L;
   private static String[] NoFilterPaths;
 
   public void init(FilterConfig config)
     throws ServletException
   {
     LogUtil.info("----" + Config.getAppCode() + "(" + Config.getAppName() + "): MainFilter Initialized----");
     ServletContext sc = config.getServletContext();
     Config.configMap.put("System.ContainerInfo", sc.getServerInfo());
     Config.getJBossInfo();
     Config.ServletMajorVersion = sc.getMajorVersion();
     Config.ServletMinorVersion = sc.getMinorVersion();
 
     this.uptime = System.currentTimeMillis();
     Config.setValue("App.Uptime", this.uptime);
 
     String paths = Config.getValue("App.NoFilterPath");
     if (StringUtil.isNotEmpty(paths)) {
       String[] arr = paths.split(",");
       for (int i = 0; i < arr.length; ++i) {
         String path = arr[i];
         if (!(path.startsWith("/"))) {
           path = "/" + path;
         }
         if (!(path.endsWith("/")))
           path = path + "/";
       }
     }
   }
 
   public boolean isNoFilterPath(String url)
   {
     if (NoFilterPaths == null) {
       return false;
     }
     url = url + "/";
     for (int i = 0; i < NoFilterPaths.length; ++i) {
       if (url.indexOf(NoFilterPaths[i]) >= 0) {
         return true;
       }
     }
     return false;
   }
 
   public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException
   {
     HttpServletRequest request = (HttpServletRequest)req;
     HttpServletResponse response = (HttpServletResponse)rep;
     String url = request.getServletPath();
     if (isNoFilterPath(url)) {
       chain.doFilter(request, response);
       return;
     }
 
     request.setCharacterEncoding(Constant.GlobalCharset);
     if ((Config.ServletMajorVersion == 2) && (Config.ServletMinorVersion == 3))
       response.setContentType("text/html;charset=" + Constant.GlobalCharset);
     else {
       response.setCharacterEncoding(Constant.GlobalCharset);
     }
 
     Current.clear();
 
     HttpSession session = request.getSession();
     User u = (User)session.getAttribute("_ZVING_USER");
     if (u == null) {
       boolean flag = true;
       if (Config.isDebugMode()) {
         Cookie[] cs = request.getCookies();
         if (cs != null) {
           for (int i = 0; i < cs.length; ++i) {
             if (cs[i].getName().equals("JSESSIONID")) {
               u = User.getCachedUser(cs[i].getValue());
               if (u != null) {
                 flag = false;
                 break;
               }
             }
           }
         }
       }
       if (flag) {
         u = new User();
       }
       u.SessionID = session.getId();
       session.setAttribute("_ZVING_USER", u);
     }
     User.setCurrent(u);
 
     String contextPath = request.getContextPath();
     if (!(contextPath.endsWith("/"))) {
       contextPath = contextPath + "/";
     }
     if (Config.isComplexDepolyMode()) {
       User.setValue("App.ContextPath", contextPath);
     }
     Config.setValue("App.ContextPath", contextPath);
     RequestDispatcher rd;
     if ((!(Config.isDatabaseConfiged)) && (url.indexOf("Install.jsp") < 0) && (url.indexOf("MainServlet.jsp") < 0)) {
       rd = request.getRequestDispatcher("/Install.jsp");
       rd.forward(req, rep);
       return;
     }
 
     Errorx.init();
     if (Config.isDebugMode()) {
       User.cacheUser(u);
     }
 
     if ((url != null) && (url.indexOf("/MainServlet.jsp") > 0) && (!(url.equals("/MainServlet.jsp")))) {
       rd = request.getRequestDispatcher("/MainServlet.jsp");
       rd.forward(req, rep);
       return;
     }
     SessionCheck.check(request, response);
 
     if (!(Errorx.hasDealed())) {
       LogUtil.getLogger().warn("严重，发现未处理的错误！");
       Errorx.printString();
     }
 
     if (ExtendManager.hasAction("AfterMainFilter")) {
       ExtendManager.executeAll("AfterMainFilter", new Object[] { request, response, chain });
     }
 
     chain.doFilter(request, response);
 
     if (Current.getPage() != null)
       Current.getPage().getCookie().writeToResponse(request, response);
   }
 
   public void destroy()
   {
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.MainFilter
 * JD-Core Version:    0.5.3
 */