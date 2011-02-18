 package com.htsoft.test.system;
 
 import com.htsoft.oa.dao.system.FunUrlDao;
 import com.htsoft.oa.model.system.FunUrl;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class FunUrlDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private FunUrlDao funUrlDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     FunUrl funUrl = new FunUrl();
 
     this.funUrlDao.save(funUrl);
   }
 }


 
 
 