 package com.htsoft.test.task;
 
 import com.htsoft.oa.dao.task.CalendarPlanDao;
 import com.htsoft.oa.model.task.CalendarPlan;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class CalendarPlanDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private CalendarPlanDao calendarPlanDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     CalendarPlan calendarPlan = new CalendarPlan();
 
     this.calendarPlanDao.save(calendarPlan);
   }
 }


 
 
 