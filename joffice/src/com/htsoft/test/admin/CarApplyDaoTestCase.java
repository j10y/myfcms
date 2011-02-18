 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.CarApplyDao;
 import com.htsoft.oa.model.admin.CarApply;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class CarApplyDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private CarApplyDao carApplyDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     CarApply carApply = new CarApply();
 
     this.carApplyDao.save(carApply);
   }
 }


 
 
 