 package com.zving.framework.extend;
 
 import javax.servlet.http.HttpSession;
 
 public abstract class AfterSessionCreateAction
   implements IExtendAction
 {
   public static final String Type = "AfterSessionCreate";
 
   public String getTarget()
   {
     return "AfterSessionCreate";
   }
 
   public void execute(Object[] args)
   {
     HttpSession session = (HttpSession)args[0];
     execute(session);
   }
 
   public abstract void execute(HttpSession paramHttpSession);
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.extend.AfterSessionCreateAction
 * JD-Core Version:    0.5.3
 */