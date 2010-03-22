 package com.zving.workflow.util;
 
 import com.zving.framework.User;
 import com.zving.workflow.Function;
 import java.util.Map;
 
 public class FunctionCaller
   implements Function
 {
   public boolean execute(Map transientVars, Map args)
   {
     transientVars.put("caller", User.getUserName());
     return true;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.util.FunctionCaller
 * JD-Core Version:    0.5.3
 */