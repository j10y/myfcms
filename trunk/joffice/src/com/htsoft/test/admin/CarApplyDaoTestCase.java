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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.CarApplyDaoTestCase
 * JD-Core Version:    0.5.4
 */