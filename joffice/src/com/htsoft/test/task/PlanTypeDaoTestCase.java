 package com.htsoft.test.task;
 
 import com.htsoft.oa.dao.task.PlanTypeDao;
 import com.htsoft.oa.model.task.PlanType;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class PlanTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private PlanTypeDao planTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     PlanType planType = new PlanType();
 
     this.planTypeDao.save(planType);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.task.PlanTypeDaoTestCase
 * JD-Core Version:    0.5.4
 */