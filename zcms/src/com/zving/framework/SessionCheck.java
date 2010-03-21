 package com.zving.framework;
 
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 
 public class SessionCheck
 {
   public static void check(HttpServletRequest request, HttpServletResponse response)
   {
   }
 
   public static boolean check(Class c)
   {
     if (!(Ajax.class.isAssignableFrom(c))) {
       if (!(User.isLogin())) {
         return false;
       }
       if (!(User.isManager())) {
         return false;
       }
     }
     return true;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.SessionCheck
 * JD-Core Version:    0.5.3
 */