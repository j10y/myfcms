 package com.zving.workflow.util;
 
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZWHistoryStepSchema;
 import com.zving.schema.ZWHistoryStepSet;
 import com.zving.schema.ZWWorkflowEntrySchema;
 import com.zving.workflow.Function;
 import com.zving.workflow.Workflow;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.List;
 import java.util.Map;
 
 public class FunctionMostRecentOwner
   implements Function
 {
   public boolean execute(Map transientVars, Map args)
   {
     String stepIdString = (String)args.get("stepId");
     if (stepIdString == null) {
       System.err.println("This function expects a stepId!");
       return false;
     }
 
     List stepIds = Arrays.asList(stepIdString.split(","));
 
     ZWWorkflowEntrySchema workflowEntrySchema = (ZWWorkflowEntrySchema)transientVars.get("workflowEntrySchema");
     Workflow workflow = (Workflow)transientVars.get("workflow");
     ZWHistoryStepSet historyStepSet = workflow.findHistorySteps(workflowEntrySchema.getID());
 
     for (int i = 0; i < historyStepSet.size(); ++i) {
       ZWHistoryStepSchema historyStep = historyStepSet.get(i);
 
       if ((stepIds.contains(String.valueOf(historyStep.getStepID()))) && (StringUtil.isNotEmpty(historyStep.getOwner()))) {
         transientVars.put("mostRecentOwner", historyStep.getOwner());
         break;
       }
     }
     return true;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.util.FunctionMostRecentOwner
 * JD-Core Version:    0.5.3
 */