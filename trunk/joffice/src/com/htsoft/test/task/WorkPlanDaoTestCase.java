 package com.htsoft.test.task;
 
 import com.htsoft.oa.dao.task.WorkPlanDao;
 import com.htsoft.oa.model.task.WorkPlan;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class WorkPlanDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private WorkPlanDao workPlanDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     WorkPlan workPlan = new WorkPlan();
 
     this.workPlanDao.save(workPlan);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.task.WorkPlanDaoTestCase
 * JD-Core Version:    0.5.4
 */