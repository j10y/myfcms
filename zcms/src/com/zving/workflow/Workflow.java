 package com.zving.workflow;
 
 import com.zving.framework.User;
 import com.zving.framework.data.DataAccess;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZWCurrentStepPrevSchema;
 import com.zving.schema.ZWCurrentStepPrevSet;
 import com.zving.schema.ZWCurrentStepSchema;
 import com.zving.schema.ZWCurrentStepSet;
 import com.zving.schema.ZWHistoryStepPrevSchema;
 import com.zving.schema.ZWHistoryStepPrevSet;
 import com.zving.schema.ZWHistoryStepSchema;
 import com.zving.schema.ZWHistoryStepSet;
 import com.zving.schema.ZWWorkflowEntrySchema;
 import java.io.PrintStream;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 
 public class Workflow
 {
   public static final int CREATED = 0;
   public static final int ACTIVATED = 1;
   public static final int SUSPENDED = 2;
   public static final int KILLED = 3;
   public static final int COMPLETED = 4;
   public static final int UNKNOWN = -1;
   private WorkflowDescriptor workflowDescriptor;
   private ZWWorkflowEntrySchema workflowEntrySchema;
   private ZWCurrentStepSchema currentStepSchema;
   private Map transientVars = new HashMap();
   private DataAccess mDataAccess;
   private String owner;
   private boolean isCompleted = false;
 
   public static void main(String[] args) throws SQLException
   {
   }
 
   public Workflow(String workflowDefID, DataAccess dataAccess) {
     this.mDataAccess = dataAccess;
     this.workflowDescriptor = WorkFlowUtil.getWorkflow(workflowDefID, false);
   }
 
   public long initialize(String workflowDefID, int initialID) {
     return initialize(workflowDefID, initialID, null);
   }
 
   public long initialize(String workflowDefID, int initialID, String owner)
   {
     this.owner = owner;
     populateTransientMap();
 
     ActionDescriptor initialAction = this.workflowDescriptor.getInitialAction(initialID);
 
     if (!(isActionAvailable(initialAction, -1L))) {
       System.out.println(workflowDefID + "工作流初始化失败！");
       return -1L;
     }
 
     ZWWorkflowEntrySchema entry = createEntry(workflowDefID);
 
     transitionWorkflow(initialAction, null);
 
     executeFunctions(initialAction.getPostFunctions());
     if (this.isCompleted) {
       completeEntry(initialAction, 4);
     }
     else {
       checkImplicitFinish(initialAction, entry.getID());
     }
     return entry.getID();
   }
 
   public ZWWorkflowEntrySchema createEntry(String workflowDefID)
   {
     this.workflowEntrySchema = new ZWWorkflowEntrySchema();
     this.workflowEntrySchema.setID(NoUtil.getMaxID("WorkflowEntryID"));
     this.workflowEntrySchema.setWorkflowDefID(workflowDefID);
     this.workflowEntrySchema.setState(0);
     this.workflowEntrySchema.setDataAccess(this.mDataAccess);
     this.workflowEntrySchema.insert();
     return this.workflowEntrySchema;
   }
 
   public boolean doAction(long entryId, long actionId) {
     return doAction(entryId, actionId, null);
   }
 
   public boolean doAction(long entryId, long actionId, String owner)
   {
     if (!(findEntry(entryId))) {
       return false;
     }
 
     this.owner = owner;
 
     ZWCurrentStepSet currentStepSet = findCurrentSteps(entryId);
     ActionDescriptor action = null;
     boolean validAction = false;
 
     for (int i = 0; i < currentStepSet.size(); ++i) {
       this.currentStepSchema = currentStepSet.get(i);
       StepDescriptor step = this.workflowDescriptor.getStep(this.currentStepSchema.getStepID());
 
       for (Iterator iterator = step.getActions().iterator(); (!(validAction)) && (iterator.hasNext()); ) {
         ActionDescriptor actionDesc = (ActionDescriptor)iterator.next();
 
         if (actionDesc.getId() == actionId) {
           action = actionDesc;
           populateTransientMap();
           if (isActionAvailable(action, step.getId())) {
             validAction = true;
           }
         }
       }
     }
 
     if (!(validAction)) {
       System.err.println("没有找到动作ID:" + actionId);
       return false;
     }
 
     if (!(transitionWorkflow(action, currentStepSet))) {
       return false;
     }
 
     if (this.isCompleted) {
       return completeEntry(action, 4);
     }
 
     return checkImplicitFinish(action, entryId);
   }
 
   public boolean transitionWorkflow(ActionDescriptor action, ZWCurrentStepSet currentStepSet)
   {
     this.currentStepSchema = getCurrentStep(action.getId(), currentStepSet);
 
     if (this.currentStepSchema != null) {
       List stepPostFunctions = this.workflowDescriptor.getStep(this.currentStepSchema.getStepID()).getPostFunctions();
       if (!(executeFunctions(stepPostFunctions))) {
         return false;
       }
 
     }
 
     List preFunctions = action.getPreFunctions();
     if (!(executeFunctions(preFunctions))) {
       return false;
     }
 
     List conditionalResults = action.getConditionalResults();
     List extraPreFunctions = null;
     List extraPostFunctions = null;
     ResultDescriptor[] theResults = new ResultDescriptor[1];
 
     for (Iterator iterator = conditionalResults.iterator(); iterator.hasNext(); ) {
       ConditionalResultDescriptor conditionalResult = (ConditionalResultDescriptor)iterator.next();
 
       if (!(passesConditions(null, conditionalResult.getConditions(), 
         (this.currentStepSchema != null) ? this.currentStepSchema.getStepID() : -1))) continue;
       theResults[0] = conditionalResult;
       extraPreFunctions = theResults[0].getPreFunctions();
       extraPostFunctions = theResults[0].getPostFunctions();
       break;
     }
 
     if (theResults[0] == null) {
       theResults[0] = action.getUnconditionalResult();
       extraPreFunctions = theResults[0].getPreFunctions();
       extraPostFunctions = theResults[0].getPostFunctions();
     }
 
     if ((extraPreFunctions != null) && (extraPreFunctions.size() > 0) && 
       (!(executeFunctions(extraPreFunctions)))) {
       return false;
     }
 
     if (theResults[0].getSplit() != 0) {
       if (!(executeSlitResult(action, theResults[0])))
         return false;
     }
     else if (theResults[0].getJoin() != 0) {
       if (!(executeJoinResult(action, theResults[0]))) {
         return false;
       }
     }
     else if (!(executeResult(action, theResults[0]))) {
       return false;
     }
 
     if (!(executeFunctions(extraPostFunctions))) {
       return false;
     }
 
     return (executeFunctions(action.getPostFunctions()));
   }
 
   public boolean executeSlitResult(ActionDescriptor action, ResultDescriptor result)
   {
     SplitDescriptor splitDesc = this.workflowDescriptor.getSplit(result.getSplit());
     List splitPreFunctions = new ArrayList();
     List splitPostFunctions = new ArrayList();
 
     Collection results = splitDesc.getResults();
 
     for (Iterator iterator = results.iterator(); iterator.hasNext(); ) {
       ResultDescriptor resultDescriptor = (ResultDescriptor)iterator.next();
       splitPreFunctions.addAll(resultDescriptor.getPreFunctions());
       splitPostFunctions.addAll(resultDescriptor.getPostFunctions());
     }
 
     if ((splitPreFunctions.size() != 0) && 
       (!(executeFunctions(splitPreFunctions)))) {
       return false;
     }
 
     if (!(action.isFinish()))
     {
       boolean moveFirst = true;
 
       ResultDescriptor[] theResults = new ResultDescriptor[results.size()];
       results.toArray(theResults);
 
       for (Iterator iterator = results.iterator(); iterator.hasNext(); ) {
         ResultDescriptor resultDescriptor = (ResultDescriptor)iterator.next();
 
         if (moveFirst) {
           if ((!(markFinished(this.currentStepSchema, action.getId(), new Date(), resultDescriptor.getOldStatus()))) || 
             (!(moveToHistory(this.currentStepSchema)))) {
             return false;
           }
           moveFirst = false;
         }
 
         long[] previousIds = (long[])null;
 
         if (this.currentStepSchema != null) {
           previousIds = new long[] { this.currentStepSchema.getID() };
         }
 
         if (!(createNewCurrentStep(resultDescriptor, previousIds)))
           return false;
       }
     }
     else {
       this.isCompleted = true;
     }
 
     return ((splitPostFunctions.size() == 0) || 
       (executeFunctions(splitPostFunctions)));
   }
 
   public boolean executeJoinResult(ActionDescriptor action, ResultDescriptor result)
   {
     if ((this.currentStepSchema != null) && ((
       (!(markFinished(this.currentStepSchema, action.getId(), new Date(), result.getOldStatus()))) || 
       (!(moveToHistory(this.currentStepSchema)))))) {
       return false;
     }
 
     JoinDescriptor joinDesc = this.workflowDescriptor.getJoin(result.getJoin());
     List joinConditions = joinDesc.getConditions();
 
     for (Iterator iter = joinConditions.iterator(); iter.hasNext(); ) {
       ConditionsDescriptor conditions = (ConditionsDescriptor)iter.next();
       if (!(passesConditions(conditions, this.currentStepSchema.getStepID()))) {
         return false;
       }
     }
 
     List previousList = new ArrayList();
     StepDescriptor stepDesc = this.workflowDescriptor.getStep(this.currentStepSchema.getStepID());
 
     if (stepDesc.resultsInJoin(result.getJoin())) {
       previousList.add(new Long(this.currentStepSchema.getID()));
     }
 
     ZWHistoryStepSet historySteps = findHistorySteps(this.workflowEntrySchema.getID());
 
     for (int i = 0; i < historySteps.size(); ++i) {
       ZWHistoryStepSchema historyStep = historySteps.get(i);
 
       if (historyStep.getID() != this.currentStepSchema.getID()) {
         stepDesc = this.workflowDescriptor.getStep(historyStep.getStepID());
         if (stepDesc.resultsInJoin(result.getJoin())) {
           previousList.add(new Long(historyStep.getID()));
         }
       }
     }
     long[] previousIds = new long[previousList.size()];
     for (int i = 0; i < previousIds.length; ++i) {
       previousIds[i] = Long.parseLong(previousList.get(i).toString());
     }
 
     ResultDescriptor resultDescriptor = joinDesc.getResult();
     if (!(executeFunctions(resultDescriptor.getPreFunctions()))) {
       return false;
     }
 
     if (!(action.isFinish())) {
       if (!(createNewCurrentStep(resultDescriptor, previousIds)))
         return false;
     }
     else {
       this.isCompleted = true;
     }
 
     return (executeFunctions(resultDescriptor.getPostFunctions()));
   }
 
   public boolean executeResult(ActionDescriptor action, ResultDescriptor result)
   {
     if ((this.currentStepSchema != null) && ((
       (!(markFinished(this.currentStepSchema, action.getId(), new Date(), result.getOldStatus()))) || 
       (!(moveToHistory(this.currentStepSchema)))))) {
       return false;
     }
 
     if (!(action.isFinish())) {
       if (!(createNewCurrentStep(result, new long[] { (this.currentStepSchema == null) ? -1L : this.currentStepSchema.getID() })))
         return false;
     }
     else {
       this.isCompleted = true;
     }
 
     return true;
   }
 
   public void populateTransientMap()
   {
     this.transientVars.put("workflow", this);
     this.transientVars.put("workflowDescriptor", this.workflowDescriptor);
     this.transientVars.put("workflowEntrySchema", this.workflowEntrySchema);
     this.transientVars.put("currentStepSchema", this.currentStepSchema);
   }
 
   public String translateVariables(String var) {
     return HtmlUtil.replacePlaceHolder(var, this.transientVars, false);
   }
 
   public boolean createNewCurrentStep(ResultDescriptor resultDescriptor, long[] previousIds)
   {
     ZWCurrentStepSchema currentStep = new ZWCurrentStepSchema();
     currentStep.setID(NoUtil.getMaxID("WorkflowStepID"));
     currentStep.setEntryID(this.workflowEntrySchema.getID());
     currentStep.setStepID(resultDescriptor.getStep());
 
     currentStep.setOwner((this.owner != null) ? this.owner : translateVariables(resultDescriptor.getOwner()));
     currentStep.setStartDate(new Date());
 
     currentStep.setStatus(resultDescriptor.getStatus());
     currentStep.setCaller(translateVariables(User.getUserName()));
     currentStep.setMemo("步骤：" + this.workflowDescriptor.getStep(currentStep.getStepID()).getName());
     currentStep.setDataAccess(this.mDataAccess);
     if (!(currentStep.insert())) {
       System.err.println("生成新步骤出错！");
       return false;
     }
     this.currentStepSchema = currentStep;
     return createPreviousSteps(currentStep.getID(), previousIds);
   }
 
   public boolean createPreviousSteps(long id, long[] previousIds) {
     if ((previousIds != null) && (previousIds.length > 0)) {
       ZWCurrentStepPrevSet CurrentStepPrevSet = new ZWCurrentStepPrevSet();
       for (int i = 0; i < previousIds.length; ++i) {
         ZWCurrentStepPrevSchema CURRENTSTEP_PREV = new ZWCurrentStepPrevSchema();
         CURRENTSTEP_PREV.setID(id);
         CURRENTSTEP_PREV.setPreviousID(previousIds[i]);
         CurrentStepPrevSet.add(CURRENTSTEP_PREV);
       }
       CurrentStepPrevSet.setDataAccess(this.mDataAccess);
       if (!(CurrentStepPrevSet.insert())) {
         System.err.println("生成PreviousSteps出错！");
         return false;
       }
     }
     return true;
   }
 
   public boolean markFinished(ZWCurrentStepSchema currentStep, int actionId, Date finishDate, String status) {
     currentStep.setDataAccess(this.mDataAccess);
     currentStep.setActionID(actionId);
     currentStep.setFinishDate(finishDate);
     currentStep.setStatus(status);
     if (!(currentStep.update())) {
       System.err.println("更新当前步骤失败！步骤ID：" + actionId);
       return false;
     }
     return true;
   }
 
   public boolean completeEntry(ActionDescriptor action, int state)
   {
     this.workflowEntrySchema.setState(state);
     this.workflowEntrySchema.setDataAccess(this.mDataAccess);
     if (!(this.workflowEntrySchema.update())) {
       System.err.println("更新工作流实例出错，实例ID：" + this.workflowEntrySchema.getID());
       return false;
     }
 
     String oldStatus = (action != null) ? action.getUnconditionalResult().getOldStatus() : "Finished";
     ZWCurrentStepSet currentSteps = findCurrentSteps(this.workflowEntrySchema.getID());
     for (int i = 0; i < currentSteps.size(); ++i) {
       if (!(markFinished(currentSteps.get(i), action.getId(), new Date(), oldStatus))) {
         return false;
       }
       if (!(moveToHistory(currentSteps.get(i)))) {
         return false;
       }
     }
     return true;
   }
 
   public boolean checkImplicitFinish(ActionDescriptor action, long id)
   {
     ZWCurrentStepSet currentSteps = findCurrentSteps(id);
 
     boolean isCompleted = true;
 
     for (int i = 0; i < currentSteps.size(); ++i) {
       ZWCurrentStepSchema tmpCurrentStep = currentSteps.get(i);
       StepDescriptor stepDes = this.workflowDescriptor.getStep(tmpCurrentStep.getStepID());
 
       if (stepDes.getActions().size() > 0) {
         isCompleted = false;
         break;
       }
     }
 
     if (isCompleted) {
       return completeEntry(action, 4);
     }
     return true;
   }
 
   public boolean moveToHistory(ZWCurrentStepSchema currentStep) {
     ZWHistoryStepSchema historyStep = new ZWHistoryStepSchema();
     historyStep.setID(currentStep.getID());
     historyStep.setEntryID(currentStep.getEntryID());
     historyStep.setStepID(currentStep.getStepID());
     historyStep.setActionID(currentStep.getActionID());
     historyStep.setOwner(currentStep.getOwner());
     historyStep.setStartDate(currentStep.getStartDate());
     historyStep.setFinishDate(currentStep.getFinishDate());
     historyStep.setDueDate(currentStep.getDueDate());
     historyStep.setStatus(currentStep.getStatus());
     historyStep.setCaller(currentStep.getCaller());
     StepDescriptor step = this.workflowDescriptor.getStep(historyStep.getStepID());
     if (step != null) {
       historyStep.setMemo("步骤：" + step.getName());
       ActionDescriptor action = step.getAction(historyStep.getActionID());
       if (action != null) {
         historyStep.setMemo(historyStep.getMemo() + 
           "---动作：" + action.getName());
       }
     }
 
     currentStep.setDataAccess(this.mDataAccess);
     if (!(currentStep.delete())) {
       System.err.println("删除当前步骤失败，步骤ID:" + currentStep.getID());
       return false;
     }
 
     historyStep.setDataAccess(this.mDataAccess);
     if (!(historyStep.insert())) {
       System.err.println("插入历史步骤失败，步骤ID:" + historyStep.getID());
       return false;
     }
 
     ZWCurrentStepPrevSchema tempCurrentStep = new ZWCurrentStepPrevSchema();
     tempCurrentStep.setDataAccess(this.mDataAccess);
     ZWCurrentStepPrevSet CurrentStepPrevSet = tempCurrentStep.query(new QueryBuilder("where id = ?", historyStep.getID()));
     CurrentStepPrevSet.setDataAccess(this.mDataAccess);
     if (!(CurrentStepPrevSet.delete())) {
       System.err.println("删除ZWCurrentStepPrev失败，步骤ID:" + historyStep.getID());
       return false;
     }
 
     ZWHistoryStepPrevSet HistoryStepPrevSet = new ZWHistoryStepPrevSet();
     for (int i = 0; i < CurrentStepPrevSet.size(); ++i) {
       ZWHistoryStepPrevSchema historyStepPrev = new ZWHistoryStepPrevSchema();
       historyStepPrev.setID(CurrentStepPrevSet.get(i).getID());
       historyStepPrev.setPreviousID(CurrentStepPrevSet.get(i).getPreviousID());
       HistoryStepPrevSet.add(historyStepPrev);
     }
     HistoryStepPrevSet.setDataAccess(this.mDataAccess);
     if (!(HistoryStepPrevSet.insert())) {
       System.err.println("插入HistoryStepPrev失败，步骤ID:" + historyStep.getID());
       return false;
     }
     return true;
   }
 
   public boolean findEntry(long entryId)
   {
     if (this.workflowEntrySchema == null) {
       this.workflowEntrySchema = new ZWWorkflowEntrySchema();
     }
     this.workflowEntrySchema.setID(entryId);
     if (!(this.workflowEntrySchema.fill())) {
       System.out.println(entryId + "没有找到工作流实例");
       return false;
     }
     return true;
   }
 
   public ZWCurrentStepSet findCurrentSteps(long entryId)
   {
     ZWCurrentStepSchema currentStep = new ZWCurrentStepSchema();
     currentStep.setDataAccess(this.mDataAccess);
     currentStep.setEntryID(entryId);
     ZWCurrentStepSet currentStepSet = currentStep.query();
     return currentStepSet;
   }
 
   public ZWHistoryStepSet findHistorySteps(long entryId) {
     ZWHistoryStepSchema historyStep = new ZWHistoryStepSchema();
     historyStep.setDataAccess(this.mDataAccess);
     historyStep.setEntryID(entryId);
     ZWHistoryStepSet historyStepSet = historyStep.query();
     return historyStepSet;
   }
 
   public ZWCurrentStepSchema getCurrentStep(int actionId, ZWCurrentStepSet currentSteps) {
     if (currentSteps == null) {
       return null;
     }
     if (currentSteps.size() == 1) {
       this.currentStepSchema = currentSteps.get(0);
     }
 
     for (int i = 0; i < currentSteps.size(); ++i) {
       ZWCurrentStepSchema step = currentSteps.get(i);
       ActionDescriptor action = this.workflowDescriptor.getStep(step.getStepID()).getAction(actionId);
 
       if (isActionAvailable(action, step.getStepID())) {
         this.currentStepSchema = step;
       }
     }
 
     return this.currentStepSchema;
   }
 
   public ZWWorkflowEntrySchema getEntry() {
     return this.workflowEntrySchema;
   }
 
   public long getState() {
     return this.workflowEntrySchema.getState();
   }
 
   public ActionDescriptor[] getAvailableActions(long stepId)
   {
     List list = this.workflowDescriptor.getStep(stepId).getActions();
     List tmp = new ArrayList();
     for (int i = 0; i < list.size(); ++i) {
       ActionDescriptor action = (ActionDescriptor)list.get(i);
       if (isActionAvailable(action, stepId)) {
         tmp.add(action);
       }
     }
     return ((ActionDescriptor[])tmp.toArray(new ActionDescriptor[tmp.size()]));
   }
 
   public boolean isActionAvailable(ActionDescriptor action, long stepId) {
     if (action == null) {
       return false;
     }
     RestrictionDescriptor restriction = action.getRestriction();
     ConditionsDescriptor conditions = null;
 
     if (restriction != null) {
       conditions = restriction.getConditionsDescriptor();
     }
 
     return passesConditions(conditions, stepId);
   }
 
   public boolean passesCondition(ConditionDescriptor conditionDesc, long currentStepID)
   {
     String type = conditionDesc.getType();
     if (!("class".equals(type))) {
       return true;
     }
 
     Map args = conditionDesc.getArgs();
     String className = (String)args.get("class.name");
 
     if (className == null) {
       throw new RuntimeException("没有指定类名");
     }
     try
     {
       Object o = Class.forName(className).newInstance();
       Condition condition = null;
       if (o instanceof Condition)
         condition = (Condition)o;
       else {
         throw new RuntimeException(className + "没有实现Condition接口");
       }
       return condition.passesCondition(this.transientVars, args);
     }
     catch (InstantiationException e) {
       e.printStackTrace();
     }
     catch (IllegalAccessException e) {
       e.printStackTrace();
     }
     catch (ClassNotFoundException e) {
       e.printStackTrace();
     }
     return false;
   }
 
   public boolean passesConditions(ConditionsDescriptor descriptor, long currentStepId) {
     if (descriptor == null) {
       return true;
     }
 
     return passesConditions(descriptor.getType(), descriptor.getConditions(), currentStepId);
   }
 
   public boolean passesConditions(String conditionType, List conditions, long currentStepId) {
     if ((conditions == null) || (conditions.size() == 0)) {
       return true;
     }
 
     boolean and = "AND".equals(conditionType);
     boolean or = !(and);
 
     for (Iterator iterator = conditions.iterator(); iterator.hasNext(); ) {
       AbstractDescriptor descriptor = (AbstractDescriptor)iterator.next();
       boolean result;
       if (descriptor instanceof ConditionsDescriptor) {
         ConditionsDescriptor conditionsDescriptor = (ConditionsDescriptor)descriptor;
         result = passesConditions(conditionsDescriptor.getType(), conditionsDescriptor.getConditions(), 
           currentStepId);
       } else {
         result = passesCondition((ConditionDescriptor)descriptor, currentStepId);
       }
 
       if ((and) && (!(result)))
         return false;
       if ((or) && (result)) {
         return true;
       }
     }
 
     if (and) {
       return true;
     }
     return (!(or));
   }
 
   public boolean executeFunctions(List listFunction)
   {
     if ((listFunction == null) || (listFunction.size() <= 0)) {
       return true;
     }
     for (Iterator iter = listFunction.iterator(); iter.hasNext(); ) {
       FunctionDescriptor functionDesc = (FunctionDescriptor)iter.next();
       if (!(executeFunction(functionDesc))) {
         return false;
       }
     }
     return true;
   }
 
   public boolean executeFunction(FunctionDescriptor functionDesc) {
     String type = functionDesc.getType();
     if (!("class".equals(type))) {
       System.out.println("Function 的type 不是class");
       return false;
     }
 
     Map args = functionDesc.getArgs();
     String className = (String)args.get("class.name");
     if (className == null) {
       System.out.println("没有指定类名");
       return false;
     }
     try
     {
       Object o = Class.forName(className).newInstance();
       Function function = null;
       if (o instanceof Function) {
         function = (Function)o;
       } else {
         System.out.println(className + "没有实现Condition接口");
         return false;
       }
       if (!(function.execute(this.transientVars, args))) {
         System.err.println("执行类" + className + "失败！");
         return false;
       }
     }
     catch (InstantiationException e) {
       e.printStackTrace();
       return false;
     }
     catch (IllegalAccessException e) {
       e.printStackTrace();
       return false;
     }
     catch (ClassNotFoundException e) {
       e.printStackTrace();
       return false;
     }
     return true;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.Workflow
 * JD-Core Version:    0.5.3
 */