 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.CarDao;
 import com.htsoft.oa.model.admin.Car;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class CarDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private CarDao carDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Car car = new Car();
 
     this.carDao.save(car);
   }
 }


 
 
 