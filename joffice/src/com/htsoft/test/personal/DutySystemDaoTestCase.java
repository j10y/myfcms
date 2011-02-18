 package com.htsoft.test.personal;
 
 import com.htsoft.oa.dao.personal.DutySystemDao;
 import com.htsoft.oa.model.personal.DutySystem;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DutySystemDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DutySystemDao dutySystemDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     DutySystem dutySystem = new DutySystem();
 
     this.dutySystemDao.save(dutySystem);
   }
 }


 
 
 