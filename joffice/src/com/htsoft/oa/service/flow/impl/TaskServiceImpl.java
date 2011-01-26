 package com.htsoft.oa.service.flow.impl;
 
 import com.htsoft.core.jbpm.pv.TaskInfo;
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.flow.TaskDao;
 import com.htsoft.oa.model.flow.JbpmTask;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.model.flow.ProcessRun;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.flow.ProcessRunService;
 import com.htsoft.oa.service.flow.TaskService;
 import com.htsoft.oa.service.system.AppUserService;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import org.jbpm.api.ExecutionService;
 import org.jbpm.pvm.internal.task.TaskImpl;
 
 public class TaskServiceImpl extends BaseServiceImpl<TaskImpl>
   implements TaskService
 {
 
   @Resource
   private ExecutionService executionService;
 
   @Resource
   private ProcessRunService processRunService;
   private TaskDao dao;
 
   @Resource
   private AppUserService appUserService;
 
   public TaskServiceImpl(TaskDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<TaskImpl> getTasksByUserId(String userId, PagingBean pb)
   {
     return this.dao.getTasksByUserId(userId, pb);
   }
 
   public List<TaskInfo> getTaskInfosByUserId(String userId, PagingBean pb)
   {
     List list = getTasksByUserId(userId, pb);
     List taskInfoList = new ArrayList();
     for (TaskImpl taskImpl : list) {
       TaskInfo taskInfo = new TaskInfo(taskImpl);
       if (taskImpl.getAssignee() != null) {
         AppUser user = (AppUser)this.appUserService.get(new Long(taskImpl.getAssignee()));
         taskInfo.setAssignee(user.getFullname());
       }
 
       ProcessRun processRun = this.processRunService.getByPiId(taskImpl.getExecutionId());
       if (processRun != null) {
         taskInfo.setTaskName(processRun.getProDefinition().getName() + "--" + taskImpl.getActivityName());
         taskInfo.setActivityName(taskImpl.getActivityName());
       }
 
       taskInfoList.add(taskInfo);
     }
     return taskInfoList;
   }
 
   public Set<Long> getHastenByActivityNameVarKeyLongVal(String activityName, String varKey, Long value)
   {
     List jtasks = this.dao.getByActivityNameVarKeyLongVal(activityName, varKey, value);
     Set userIds = new HashSet();
     for (JbpmTask jtask : jtasks) {
       if (jtask.getAssignee() == null) {
         List userlist = this.dao.getUserIdByTask(jtask.getTaskId());
         userIds.addAll(userlist);
         List groupList = this.dao.getGroupByTask(jtask.getTaskId());
         for (Long l : groupList) {
           List uList = this.appUserService.findByRoleId(l);
           List idList = new ArrayList();
           for (AppUser appUser : uList) {
             idList.add(appUser.getUserId());
           }
           userIds.addAll(idList);
         }
       } else {
         userIds.add(new Long(jtask.getAssignee()));
       }
     }
     return userIds;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.TaskServiceImpl
 * JD-Core Version:    0.5.4
 */