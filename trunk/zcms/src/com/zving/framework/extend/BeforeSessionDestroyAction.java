 package com.zving.framework.extend;
 
 public class BeforeSessionDestroyAction
   implements IExtendAction
 {
   public static final String Type = "BeforeSessionDestroy";
 
   public String getTarget()
   {
     return "BeforeSessionDestroy";
   }
 
   public void execute(Object[] args)
   {
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.extend.BeforeSessionDestroyAction
 * JD-Core Version:    0.5.3
 */