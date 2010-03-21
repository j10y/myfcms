 package com.zving.framework;
 
 import com.zving.framework.data.DataCollection;
 import com.zving.framework.utility.LogUtil;
 import com.zving.framework.utility.ServletUtil;
 import com.zving.framework.utility.StringUtil;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.lang.reflect.Method;
 import java.lang.reflect.Modifier;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.logging.Log;
 
 public class Current
 {
   private static ThreadLocal current = new ThreadLocal();
 
   protected static void clear() {
     if (current.get() != null)
       current.set(null);
   }
 
   public static void init(HttpServletRequest request, HttpServletResponse response, String method)
   {
     if (current.get() != null)
       return;
     try
     {
       int index = method.lastIndexOf(46);
       String className = method.substring(0, index);
 
       Class c = Class.forName(className);
       Object o = c.newInstance();
       Page page = (Page)o;
 
       String data = request.getParameter("_ZVING_DATA");
       RequestImpl dc = new RequestImpl();
       if (StringUtil.isNotEmpty(data)) {
         data = StringUtil.htmlDecode(data);
         dc.setURL(request.getParameter("_ZVING_URL"));
         dc.putAll(ServletUtil.getParameterMap(request));
         dc.remove("_ZVING_DATA");
         dc.remove("_ZVING_URL");
         dc.remove("_ZVING_METHOD");
         dc.parseXML(data);
       } else {
         dc.setURL(request.getPathInfo());
         dc.putAll(ServletUtil.getParameterMap(request));
       }
       dc.setClientIP(request.getRemoteAddr());
       dc.setClassName(className);
       dc.setServerName(request.getServerName());
       dc.setPort(request.getServerPort());
       dc.setScheme(request.getScheme());
 
       CookieImpl cookie = new CookieImpl(request);
       page.setCookie(cookie);
       CookieImpl.CookieObject[] ks = page.getCookie().getArray();
       for (int i = 0; i < ks.length; ++i) {
         dc.put("Cookie." + ks[i].name, ks[i].value);
       }
 
       dc.put("Header.UserAgent", request.getHeader("User-Agent"));
       dc.put("Header.Host", request.getHeader("Host"));
       dc.put("Header.Protocol", request.getProtocol());
 
       page.setRequest(dc);
       current.set(page);
     } catch (Exception e) {
       DataCollection dcResponse = new DataCollection();
       dcResponse.put("_ZVING_STATUS", 0);
       String msg = "系统发生内部错误，操作失败:" + method;
       LogUtil.getLogger().warn(msg);
       e.printStackTrace();
       dcResponse.put("_ZVING_MESSAGE", msg);
       try {
         response.getWriter().write(dcResponse.toXML());
       } catch (IOException e2) {
         e2.printStackTrace();
       }
     }
   }
 
   public static Object invokeMethod(String method, Object[] args)
   {
     try
     {
       int index = method.lastIndexOf(46);
       String className = method.substring(0, index);
       method = method.substring(index + 1);
 
       Page p = getPage();
       if (!(p.getClass().getName().equals(className))) {
         Class c = Class.forName(className);
         Page p2 = (Page)c.newInstance();
         p2.Request = p.Request;
         p2.Response = p.Response;
         p2.Cookie = p.Cookie;
         p = p2;
       }
       Method[] ms = p.getClass().getMethods();
       Method m = null;
       boolean flag = false;
       for (int i = 0; i < ms.length; ++i) {
         m = ms[i];
         if (m.getName().equals(method)) {
           Class[] cs = m.getParameterTypes();
           if ((args != null) && 
             (args.length == cs.length)) {
             for (int j = 0; j < cs.length; ++j) {
               if (!(cs[j].isInstance(args[j]))) {
                 break;
               }
             }
             flag = true;
             break;
           }
 
           if ((args == null) && (((cs == null) || (cs.length == 0)))) {
             flag = true;
             break;
           }
         }
       }
       if (!(flag)) {
         throw new RuntimeException("没有找到合适的方法，请检查参数是否正确!" + className + "#" + method);
       }
 
       if (!(Modifier.isStatic(m.getModifiers()))) {
         return m.invoke(p, args);
       }
       return m.invoke(null, args);
     }
     catch (Throwable e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static Page getPage()
   {
     return ((Page)current.get());
   }
 
   public static RequestImpl getRequest()
   {
     Page p = getPage();
     if (p == null) {
       return null;
     }
     return p.Request;
   }
 
   public static ResponseImpl getResponse()
   {
     Page p = getPage();
     if (p == null) {
       return null;
     }
     return p.Response;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.Current
 * JD-Core Version:    0.5.3
 */