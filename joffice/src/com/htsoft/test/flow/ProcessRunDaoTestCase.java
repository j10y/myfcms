 package com.htsoft.test.flow;
 
 import com.htsoft.oa.dao.flow.ProcessRunDao;
 import com.htsoft.oa.model.flow.ProcessRun;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ProcessRunDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ProcessRunDao processRunDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ProcessRun processRun = new ProcessRun();
 
     this.processRunDao.save(processRun);
   }
 }


 
 
 