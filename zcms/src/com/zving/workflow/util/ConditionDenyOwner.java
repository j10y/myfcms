 package com.zving.workflow.util;
 
 import com.zving.framework.User;
 import com.zving.schema.ZWCurrentStepSchema;
 import com.zving.workflow.Condition;
 import java.util.Map;
 
 public class ConditionDenyOwner
   implements Condition
 {
   public boolean passesCondition(Map transientVars, Map args)
   {
     ZWCurrentStepSchema step = (ZWCurrentStepSchema)transientVars.get("currentStepSchema");
     return (!(User.getUserName().equals(step.getOwner())));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.util.ConditionDenyOwner
 * JD-Core Version:    0.5.3
 */