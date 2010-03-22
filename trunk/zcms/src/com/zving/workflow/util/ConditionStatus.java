 package com.zving.workflow.util;
 
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.schema.ZWCurrentStepSchema;
 import com.zving.workflow.Condition;
 import java.util.Map;
 
 public class ConditionStatus
   implements Condition
 {
   public boolean passesCondition(Map transientVars, Map args)
   {
     String status = (String)args.get("status");
     int stepId = 0;
     Object stepIdVal = args.get("stepId");
     ZWCurrentStepSchema currentStepSchema = (ZWCurrentStepSchema)transientVars.get("currentStepSchema");
     if (stepIdVal == null) {
       return currentStepSchema.getStatus().equals(status);
     }
     stepId = Integer.parseInt(stepIdVal.toString());
     QueryBuilder qb = new QueryBuilder("select ''  from ZWCurrentStep where entryID = ? and StepID=? and status = ?");
     qb.add(currentStepSchema.getEntryID());
     qb.add(stepId);
     qb.add(args.get("status"));
     DataTable dt = qb.executeDataTable();
     if ((dt != null) && (dt.getRowCount() > 0)) {
       return true;
     }
     QueryBuilder qb2 = new QueryBuilder("select '' from zwhistorystep where entryID = ? and StepID=? and status = ?");
     qb2.add(currentStepSchema.getEntryID());
     qb2.add(stepId);
     qb2.add(args.get("status"));
 
     dt = qb2.executeDataTable();
 
     return ((dt != null) && (dt.getRowCount() > 0));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.util.ConditionStatus
 * JD-Core Version:    0.5.3
 */