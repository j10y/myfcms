 package com.htsoft.test.system;
 
 import com.htsoft.oa.dao.system.AppFunctionDao;
 import com.htsoft.oa.model.system.AppFunction;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class AppFunctionDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private AppFunctionDao appFunctionDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     AppFunction appFunction = new AppFunction();
 
     this.appFunctionDao.save(appFunction);
   }
 }


 
 
 