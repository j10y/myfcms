 package com.htsoft.test.system;
 
 import com.htsoft.oa.dao.system.SystemLogDao;
 import com.htsoft.oa.model.system.SystemLog;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class SystemLogDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private SystemLogDao systemLogDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     SystemLog systemLog = new SystemLog();
 
     this.systemLogDao.save(systemLog);
   }
 }


 
 
 