 package com.htsoft.test.personal;
 
 import com.htsoft.oa.dao.personal.DutyDao;
 import com.htsoft.oa.model.personal.Duty;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DutyDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DutyDao dutyDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Duty duty = new Duty();
 
     this.dutyDao.save(duty);
   }
 }


 
 
 