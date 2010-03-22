 package com.zving.framework.extend;
 
 public abstract class AfterPageMethodInvokeAction
   implements IExtendAction
 {
   public static final String Type = "AfterPageMethodInvoke";
 
   public String getTarget()
   {
     return "AfterPageMethodInvoke";
   }
 
   public void execute(Object[] args)
   {
     execute((String)args[0]);
   }
 
   public abstract void execute(String paramString);
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.extend.AfterPageMethodInvokeAction
 * JD-Core Version:    0.5.3
 */