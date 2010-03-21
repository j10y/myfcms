 package com.zving.framework.ssi;
 
 import javax.servlet.http.HttpServletRequest;
 
 public class SSIServletRequestUtil
 {
   public static String getRelativePath(HttpServletRequest request)
   {
     if (request.getAttribute("javax.servlet.include.request_uri") != null) {
       result = (String)request
         .getAttribute("javax.servlet.include.path_info");
       if (result == null)
         result = (String)request
           .getAttribute("javax.servlet.include.servlet_path");
       if ((result == null) || (result.equals(""))) result = "/";
       return result;
     }
 
     String result = request.getPathInfo();
     if (result == null) {
       result = request.getServletPath();
     }
     if ((result == null) || (result.equals(""))) {
       result = "/";
     }
     return RequestUtil.normalize(result);
   }
 

   public static String normalize(String path)
   {
     return RequestUtil.normalize(path);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.SSIServletRequestUtil
 * JD-Core Version:    0.5.3
 */