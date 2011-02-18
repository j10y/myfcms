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


 
 
 