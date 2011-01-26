 package com.htsoft.oa.service.task.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.task.WorkPlanDao;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.task.WorkPlan;
 import com.htsoft.oa.service.task.WorkPlanService;
 import java.util.List;
 
 public class WorkPlanServiceImpl extends BaseServiceImpl<WorkPlan>
   implements WorkPlanService
 {
   private WorkPlanDao dao;
 
   public WorkPlanServiceImpl(WorkPlanDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<WorkPlan> findByDepartment(WorkPlan workPlan, AppUser user, PagingBean pb)
   {
     return this.dao.findByDepartment(workPlan, user, pb);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.task.impl.WorkPlanServiceImpl
 * JD-Core Version:    0.5.4
 */