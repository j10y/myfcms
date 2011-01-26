 package com.htsoft.oa.service.task.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.task.PlanAttendDao;
 import com.htsoft.oa.model.task.PlanAttend;
 import com.htsoft.oa.service.task.PlanAttendService;
 import java.util.List;
 
 public class PlanAttendServiceImpl extends BaseServiceImpl<PlanAttend>
   implements PlanAttendService
 {
   private PlanAttendDao dao;
 
   public PlanAttendServiceImpl(PlanAttendDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public boolean deletePlanAttend(Long planId, Short isDep, Short isPrimary)
   {
     List list = this.dao.FindPlanAttend(planId, isDep, isPrimary);
     for (PlanAttend pa : list) {
       this.dao.remove(pa.getAttendId());
     }
     return true;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.task.impl.PlanAttendServiceImpl
 * JD-Core Version:    0.5.4
 */