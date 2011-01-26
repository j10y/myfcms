 package com.htsoft.oa.service.flow.impl;
 
 import com.htsoft.core.jbpm.jpdl.Node;
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.model.flow.ProUserAssign;
 import com.htsoft.oa.model.flow.ProcessRun;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.flow.JbpmService;
 import com.htsoft.oa.service.flow.ProDefinitionService;
 import com.htsoft.oa.service.flow.ProUserAssignService;
 import com.htsoft.oa.service.flow.ProcessRunService;
 import com.htsoft.oa.service.system.UserSubService;
 import java.io.File;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import javax.annotation.Resource;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.dom4j.Attribute;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.dom4j.QName;
 import org.hibernate.Session;
 import org.jbpm.api.Execution;
 import org.jbpm.api.ExecutionService;
 import org.jbpm.api.HistoryService;
 import org.jbpm.api.NewDeployment;
 import org.jbpm.api.ProcessDefinition;
 import org.jbpm.api.ProcessDefinitionQuery;
 import org.jbpm.api.ProcessEngine;
 import org.jbpm.api.ProcessInstance;
 import org.jbpm.api.ProcessInstanceQuery;
 import org.jbpm.api.RepositoryService;
 import org.jbpm.api.TaskQuery;
 import org.jbpm.api.TaskService;
 import org.jbpm.api.history.HistoryProcessInstance;
 import org.jbpm.api.history.HistoryProcessInstanceQuery;
 import org.jbpm.api.task.Task;
 import org.jbpm.pvm.internal.env.Environment;
 import org.jbpm.pvm.internal.env.EnvironmentFactory;
 import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
 import org.jbpm.pvm.internal.model.Activity;
 import org.jbpm.pvm.internal.model.ActivityImpl;
 import org.jbpm.pvm.internal.model.ExecutionImpl;
 import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
 import org.jbpm.pvm.internal.model.Transition;
 import org.jbpm.pvm.internal.model.TransitionImpl;
 import org.jbpm.pvm.internal.svc.TaskServiceImpl;
 import org.jbpm.pvm.internal.task.TaskImpl;
 
 public class JbpmServiceImpl
   implements JbpmService
 {
   private static final Log logger = LogFactory.getLog(JbpmServiceImpl.class);
 
   @Resource
   private ProcessEngine processEngine;
 
   @Resource
   private RepositoryService repositoryService;
 
   @Resource
   private ExecutionService executionService;
 
   @Resource
   private ProDefinitionService proDefinitionService;
 
   @Resource
   private TaskService taskService;
 
   @Resource
   private HistoryService historyService;
 
   @Resource
   private ProUserAssignService proUserAssignService;
 
   @Resource
   private UserSubService userSubService;
 
   @Resource
   private ProcessRunService processRunService;
 
   public Task getTaskById(String taskId)
   {
     Task task = this.taskService.getTask(taskId);
 
     return task;
   }
 
   public void assignTask(String taskId, String userId)
   {
     this.taskService.assignTask(taskId, userId);
   }
 
   public void doUnDeployProDefinition(Long defId)
   {
     this.processRunService.removeByDefId(defId);
 
     ProDefinition pd = (ProDefinition)this.proDefinitionService.get(defId);
     if (pd == null)
       return;
     this.repositoryService.deleteDeploymentCascade(pd.getDeployId());
 
     this.proDefinitionService.remove(pd);
   }
 
   public ProDefinition saveOrUpdateDeploy(ProDefinition proDefinition)
   {
     if (proDefinition.getDeployId() == null) {
       if (logger.isDebugEnabled()) {
         logger.debug("deploy now===========");
       }
       String deployId = this.repositoryService.createDeployment().addResourceFromString("process.jpdl.xml", proDefinition.getDefXml()).deploy();
 
       proDefinition.setDeployId(deployId);
 
       this.proDefinitionService.save(proDefinition);
     }
     else
     {
       this.proDefinitionService.evict(proDefinition);
 
       ProDefinition proDef = (ProDefinition)this.proDefinitionService.get(proDefinition.getDefId());
 
       if (!proDef.getDefXml().equals(proDefinition.getDefXml())) {
         if (proDef.getDeployId() != null)
         {
           this.repositoryService.deleteDeployment(proDef.getDeployId());
         }
         String deployId = this.repositoryService.createDeployment().addResourceFromString("process.jpdl.xml", proDefinition.getDefXml()).deploy();
         proDefinition.setDeployId(deployId);
       }
 
       this.proDefinitionService.merge(proDefinition);
     }
 
     return proDefinition;
   }
 
   public ProcessDefinition getProcessDefinitionByKey(String processKey)
   {
     List list = this.repositoryService.createProcessDefinitionQuery()
       .processDefinitionKey(processKey).orderDesc("versionProperty.longValue").list();
     if ((list != null) && (list.size() > 0)) {
       return (ProcessDefinition)list.get(0);
     }
     return null;
   }
 
   public ProDefinition getProDefinitionByKey(String processKey)
   {
     ProcessDefinition processDefinition = getProcessDefinitionByKey(processKey);
     if (processDefinition != null) {
       ProDefinition proDef = this.proDefinitionService.getByDeployId(processDefinition.getDeploymentId());
       return proDef;
     }
     return null;
   }
 
   public String getDefinitionXmlByDefId(Long defId)
   {
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(defId);
     return proDefinition.getDefXml();
   }
 
   public String getDefinitionXmlByDpId(String deployId)
   {
     ProDefinition proDefintion = this.proDefinitionService.getByDeployId(deployId);
     return proDefintion.getDefXml();
   }
 
   public String getDefinitionXmlByExeId(String exeId)
   {
     String pdId = this.executionService.findExecutionById(exeId).getProcessDefinitionId();
     String deployId = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pdId).uniqueResult().getDeploymentId();
     return getDefinitionXmlByDpId(deployId);
   }
 
   public String getDefinitionXmlByPiId(String piId)
   {
     ProcessInstance pi = this.executionService.createProcessInstanceQuery().processInstanceId(piId).uniqueResult();
     ProcessDefinition pd = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pi.getProcessDefinitionId()).uniqueResult();
     return getDefinitionXmlByDpId(pd.getDeploymentId());
   }
 
   public ProcessDefinition getProcessDefinitionByTaskId(String taskId)
   {
     TaskImpl task = (TaskImpl)this.taskService.getTask(taskId);
     ProcessInstance pi = null;
     if (task.getSuperTask() != null)
       pi = task.getSuperTask().getProcessInstance();
     else {
       pi = task.getProcessInstance();
     }
     ProcessDefinition pd = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pi.getProcessDefinitionId()).uniqueResult();
     return pd;
   }
 
   public ProcessInstance getProcessInstance(String piId)
   {
     ProcessInstance pi = this.executionService.createProcessInstanceQuery().processInstanceId(piId).uniqueResult();
     return pi;
   }
 
   public List<Node> getTaskNodesByDefId(Long defId)
   {
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(defId);
     return getTaskNodesFromXml(proDefinition.getDefXml(), false, false);
   }
 
   public List<Node> getJumpNodesByDeployId(String deployId)
   {
     ProDefinition proDefinition = this.proDefinitionService.getByDeployId(deployId);
     return getTaskNodesFromXml(proDefinition.getDefXml(), false, true);
   }
 
   public List<Node> getFormNodes(Long defId)
   {
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(defId);
     return getTaskNodesFromXml(proDefinition.getDefXml(), true, false);
   }
 
   public String getStartNodeName(ProDefinition proDefinition)
   {
     String filePath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/" + proDefinition.getName() + "/开始.vm";
 
     File file = new File(filePath);
 
     if (file.exists())
       return "开始";
     try
     {
       Element root = DocumentHelper.parseText(proDefinition.getDefXml()).getRootElement();
       for (Element elem : root.elements()) {
         String tagName = elem.getName();
         if ("start".equals(tagName)) {
           Attribute nameAttr = elem.attribute("name");
           if (nameAttr != null)
             return nameAttr.getValue();
         }
       }
     }
     catch (Exception ex)
     {
       logger.error(ex.getMessage());
     }
     return "开始";
   }
 
   private List<Node> getTaskNodesFromXml(String xml, boolean includeStart, boolean includeEnd)
   {
     List nodes = new ArrayList();
     try {
       Element root = DocumentHelper.parseText(xml).getRootElement();
       for (Element elem : root.elements()) {
         String type = elem.getQName().getName();
         if ("task".equalsIgnoreCase(type)) {
           if (elem.attribute("name") != null) {
             Node node = new Node(elem.attribute("name").getValue(), "任务节点");
             nodes.add(node);
           }
         } else if ((includeStart) && ("start".equalsIgnoreCase(type))) {
           if (elem.attribute("name") != null) {
             Node node = new Node(elem.attribute("name").getValue(), "开始节点");
             nodes.add(node);
           }
         } else if ((includeEnd) && (type.startsWith("end"))) {
           Node node = new Node(elem.attribute("name").getValue(), "结束节点");
           nodes.add(node);
         }
       }
     } catch (Exception ex) {
       logger.error(ex.getMessage());
     }
     return nodes;
   }
 
   public String startProcess(String deployId, Map variables)
   {
     ProcessDefinition pd = this.repositoryService.createProcessDefinitionQuery().deploymentId(deployId).uniqueResult();
     clearSession();
 
     ProcessInstance pi = this.executionService.startProcessInstanceById(pd.getId(), variables);
     String assignId = (String)variables.get("flowAssignId");
 
     String signUserIds = (String)variables.get("signUserIds");
 
     if (StringUtils.isNotEmpty(signUserIds))
     {
       List newTasks = getTasksByPiId(pi.getId());
       Iterator localIterator = newTasks.iterator(); if (localIterator.hasNext()) { Task nTask = (Task)localIterator.next();
         newTask(nTask.getId(), signUserIds); }
     }
     else
     {
       assignTask(pi, pd, assignId, null);
     }
 
     return pi.getId();
   }
 
   public ProcessInstance getProcessInstanceByExeId(String executionId)
   {
     Execution execution = this.executionService.findExecutionById(executionId);
     return (ProcessInstance)execution.getProcessInstance();
   }
 
   public ProcessInstance getProcessInstanceByTaskId(String taskId) {
     TaskImpl taskImpl = (TaskImpl)this.taskService.getTask(taskId.toString());
     if (taskImpl.getSuperTask() != null) {
       taskImpl = taskImpl.getSuperTask();
     }
     return taskImpl.getProcessInstance();
   }
 
   public void assignTask(ProcessInstance pi, ProcessDefinition pd, String assignId, String taskName)
   {
     if (pd == null) {
       pd = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pi.getProcessDefinitionId()).uniqueResult();
     }
 
     List taskList = null;
 
     if (StringUtils.isNotEmpty(taskName)) {
       taskList = this.taskService.createTaskQuery().processInstanceId(pi.getId()).activityName(taskName).list();
     }
 
     if ((taskList == null) || (taskList.size() == 0)) {
       taskList = getTasksByPiId(pi.getId());
     }
 
     for (Task task : taskList)
     {
       if (StringUtils.isNotEmpty(assignId)) {
         this.taskService.assignTask(task.getId(), assignId);
       }
       else
       {
         ProUserAssign assign = this.proUserAssignService.getByDeployIdActivityName(pd.getDeploymentId(), task.getActivityName());
 
         if (assign != null)
         {
           if ("__start".equals(assign.getUserId()))
           {
             AppUser flowStartUser = (AppUser)this.executionService.getVariable(pi.getId(), "flowStartUser");
             if (flowStartUser != null)
               this.taskService.assignTask(task.getId(), flowStartUser.getUserId().toString());
           }
           else
           {
             StringBuffer upIds;
             Object localObject;
             Long userId;
             if ("__super".equals(assign.getUserId())) {
               AppUser flowStartUser = (AppUser)this.executionService.getVariable(pi.getId(), "flowStartUser");
 
               if (flowStartUser != null) {
                 List superUserIds = this.userSubService.upUser(flowStartUser.getUserId());
                 upIds = new StringBuffer();
                 for (localObject = superUserIds.iterator(); ((Iterator)localObject).hasNext(); ) { userId = (Long)((Iterator)localObject).next();
                   upIds.append(userId).append(","); }
 
                 if (superUserIds.size() > 0)
                   upIds.deleteCharAt(upIds.length() - 1);
                 else {
                   upIds.append(flowStartUser.getUserId());
                 }
                 this.taskService.addTaskParticipatingUser(task.getId(), upIds.toString(), "candidate");
               }
             } else if (StringUtils.isNotEmpty(assign.getUserId())) {
               String[] userIds = assign.getUserId().split("[,]");
 
               if ((userIds != null) && (userIds.length > 1)) {
                 userId = (localObject = userIds).length; for (upIds = 0; upIds < userId; ++upIds) { String uId = localObject[upIds];
                   this.taskService.addTaskParticipatingUser(task.getId(), uId, "candidate"); }
               }
               else {
                 this.taskService.assignTask(task.getId(), assign.getUserId());
               }
             }
           }
           if (StringUtils.isNotEmpty(assign.getRoleId()))
             this.taskService.addTaskParticipatingGroup(task.getId(), assign.getRoleId(), "candidate");
         }
         else
         {
           AppUser flowStartUser = (AppUser)this.executionService.getVariable(pi.getId(), "flowStartUser");
           if (flowStartUser != null)
             this.taskService.assignTask(task.getId(), flowStartUser.getUserId().toString());
         }
       }
     }
   }
 
   public List<Transition> getTransitionsForSignalProcess(String piId)
   {
     ProcessInstance pi = this.executionService.findProcessInstanceById(piId);
     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
     Environment env = environmentFactory.openEnvironment();
     try
     {
       ExecutionImpl executionImpl = (ExecutionImpl)pi;
       Activity activity = executionImpl.getActivity();
 
       return activity.getOutgoingTransitions();
     } finally {
       env.close();
     }
   }
 
   public List<Transition> getTransitionsByTaskId(String taskId)
   {
     TaskImpl task = (TaskImpl)this.taskService.getTask(taskId);
     if (task.getSuperTask() != null) {
       task = task.getSuperTask();
     }
     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
     Environment env = environmentFactory.openEnvironment();
     try {
       ProcessDefinitionImpl pd = task.getProcessInstance().getProcessDefinition();
       ActivityImpl activityFind = pd.findActivity(task.getActivityName());
 
       if (activityFind != null)
         return activityFind.getOutgoingTransitions();
     }
     finally {
       env.close();
     }
     return new ArrayList();
   }
 
   public void addOutTransition(ProcessDefinitionImpl pd, String sourceName, String destName)
   {
     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
     Environment env = null;
     try {
       env = environmentFactory.openEnvironment();
 
       ActivityImpl sourceActivity = pd.findActivity(sourceName);
 
       ActivityImpl destActivity = pd.findActivity(destName);
 
       TransitionImpl transition = sourceActivity.createOutgoingTransition();
       transition.setName("to" + destName);
       transition.setDestination(destActivity);
 
       sourceActivity.addOutgoingTransition(transition);
     }
     catch (Exception ex) {
       logger.error(ex.getMessage());
     } finally {
       if (env != null) env.close();
     }
   }
 
   public void removeOutTransition(ProcessDefinitionImpl pd, String sourceName, String destName)
   {
     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
     Environment env = null;
     try {
       env = environmentFactory.openEnvironment();
 
       ActivityImpl sourceActivity = pd.findActivity(sourceName);
 
       List trans = sourceActivity.getOutgoingTransitions();
       for (Transition tran : trans) {
         if (destName.equals(tran.getDestination().getName()))
           trans.remove(tran);
       }
     }
     catch (Exception ex)
     {
       logger.error(ex.getMessage());
     } finally {
       if (env != null) env.close();
     }
   }
 
   public List<Transition> getFreeTransitionsByTaskId(String taskId)
   {
     TaskImpl task = (TaskImpl)this.taskService.getTask(taskId);
 
     List outTrans = new ArrayList();
 
     if (task.getSuperTask() != null) {
       task = task.getSuperTask();
     }
     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
     Environment env = null;
     try {
       env = environmentFactory.openEnvironment();
       ProcessDefinitionImpl pd = task.getProcessInstance().getProcessDefinition();
       ActivityImpl curActivity = pd.findActivity(task.getActivityName());
 
       List allTaskNodes = getJumpNodesByDeployId(pd.getDeploymentId());
 
       for (Node taskNode : allTaskNodes) {
         if (taskNode.getName().equals(task.getActivityName()))
           continue;
         TransitionImpl transition = curActivity.createOutgoingTransition();
 
         transition.setName("to" + taskNode.getName());
         transition.setDestination(pd.findActivity(taskNode.getName()));
 
         curActivity.getOutgoingTransitions().remove(transition);
 
         outTrans.add(transition);
       }
     }
     catch (Exception ex) {
       logger.error(ex.getMessage());
     } finally {
       if (env != null) env.close();
     }
 
     return outTrans;
   }
 
   public String getProcessDefintionXMLByPiId(String piId)
   {
     ProcessRun processRun = this.processRunService.getByPiId(piId);
     return processRun.getProDefinition().getDefXml();
   }
 
   public List<Task> getTasksByPiId(String piId)
   {
     List taskList = this.taskService.createTaskQuery().processInstanceId(piId).list();
     return taskList;
   }
 
   public String getNodeType(String xml, String nodeName)
   {
     String type = "";
     try {
       Element root = DocumentHelper.parseText(xml).getRootElement();
       for (Element elem : root.elements())
         if (elem.attribute("name") != null) {
           String value = elem.attributeValue("name");
           if (value.equals(nodeName)) {
             type = elem.getQName().getName();
             return type;
           }
         }
     }
     catch (Exception ex) {
       logger.info(ex.getMessage());
     }
     return type;
   }
 
   protected void clearSession() {
     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
     Environment env = environmentFactory.openEnvironment();
     try {
       Session session = (Session)env.get(Session.class);
       session.clear();
     } finally {
       env.close();
     }
   }
 
   protected void flush() {
     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
     Environment env = environmentFactory.openEnvironment();
     try {
       Session session = (Session)env.get(Session.class);
       session.flush();
     } finally {
       env.close();
     }
   }
 
   public void completeTask(String taskId, String transitionName, String destName, Map variables)
   {
     TaskImpl taskImpl = (TaskImpl)this.taskService.getTask(taskId);
 
     String sourceName = taskImpl.getName();
 
     TaskImpl superTask = taskImpl.getSuperTask();
 
     ProcessDefinitionImpl pd = (ProcessDefinitionImpl)getProcessDefinitionByTaskId(taskId);
     ProcessInstance pi = null;
     String executionId = null;
 
     boolean isTransitionExist = false;
 
     List trans = getTransitionsByTaskId(taskId);
     for (Transition tran : trans) {
       if (tran.getDestination().getName().equals(destName)) {
         isTransitionExist = true;
         break;
       }
     }
 
     if (!isTransitionExist) {
       addOutTransition(pd, sourceName, destName);
     }
 
     if (superTask != null) {
       pi = superTask.getProcessInstance();
       executionId = superTask.getExecutionId();
       if (logger.isDebugEnabled()) {
         logger.debug("Super task is not null, task name is:" + superTask.getActivityName());
       }
 
       if (superTask.getSubTasks() != null)
       {
         if (superTask.getSubTasks().size() == 1) {
           this.taskService.setVariables(taskId, variables);
           clearSession();
 
           this.taskService.completeTask(taskId);
 
           this.taskService.completeTask(superTask.getId(), transitionName);
         } else {
           this.taskService.setVariables(taskId, variables);
           clearSession();
           this.taskService.completeTask(taskId);
 
           return;
         }
       }
     } else {
       pi = taskImpl.getProcessInstance();
       executionId = taskImpl.getExecutionId();
       this.taskService.setVariables(taskId, variables);
       flush();
       this.taskService.completeTask(taskId, transitionName);
     }
 
     if (!isTransitionExist) {
       removeOutTransition(pd, sourceName, destName);
     }
 
     boolean isEndProcess = isProcessInstanceEnd(executionId);
     if (isEndProcess) {
       ProcessRun processRun = this.processRunService.getByPiId(executionId);
       if (processRun != null) {
         processRun.setPiId(null);
         processRun.setRunStatus(ProcessRun.RUN_STATUS_FINISHED);
         this.processRunService.save(processRun);
       }
       return;
     }
 
     String signUserIds = (String)variables.get("signUserIds");
 
     if ((destName != null) && (StringUtils.isNotEmpty(signUserIds)))
     {
       List newTasks = getTasksByPiId(pi.getId());
       for (Task nTask : newTasks) {
         if (destName.equals(nTask.getName())) {
           newTask(nTask.getId(), signUserIds);
           break;
         }
       }
       return;
     }
     destName = null;
 
     String assignId = (String)variables.get("flowAssignId");
 
     assignTask(pi, null, assignId, destName);
   }
 
   protected boolean isProcessInstanceEnd(String executionId)
   {
     HistoryProcessInstance hpi = this.historyService.createHistoryProcessInstanceQuery().processInstanceId(executionId).uniqueResult();
     if (hpi != null) {
       String endActivityName = ((HistoryProcessInstanceImpl)hpi).getEndActivityName();
       if (endActivityName != null) {
         return true;
       }
     }
     return false;
   }
 
   public void newTask(String parentTaskId, String assignIds)
   {
     TaskServiceImpl taskServiceImpl = (TaskServiceImpl)this.taskService;
     Task parentTask = taskServiceImpl.getTask(parentTaskId);
 
     if (assignIds != null) {
       String[] userIds = assignIds.split("[,]");
       for (int i = 0; i < userIds.length; ++i) {
         TaskImpl task = (TaskImpl)taskServiceImpl.newTask(parentTaskId);
         task.setAssignee(userIds[i]);
         task.setName(parentTask.getName() + "-" + (i + 1));
         task.setActivityName(parentTask.getName());
         task.setDescription(parentTask.getDescription());
 
         taskServiceImpl.saveTask(task);
       }
     }
   }
 
   public void signalProcess(String executionId, String transitionName, Map<String, Object> variables)
   {
     this.executionService.setVariables(executionId, variables);
     this.executionService.signalExecutionById(executionId, transitionName);
   }
 
   public void endProcessInstance(String piId)
   {
     ExecutionService executionService = this.processEngine.getExecutionService();
     executionService.endProcessInstance(piId, "ended");
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.JbpmServiceImpl
 * JD-Core Version:    0.5.4
 */