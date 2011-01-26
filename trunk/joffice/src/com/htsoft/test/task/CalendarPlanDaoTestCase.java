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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.task.CalendarPlanDaoTestCase
 * JD-Core Version:    0.5.4
 */