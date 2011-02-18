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


 
 
 