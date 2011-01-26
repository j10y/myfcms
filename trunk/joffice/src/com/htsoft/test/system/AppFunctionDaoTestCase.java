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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.system.AppFunctionDaoTestCase
 * JD-Core Version:    0.5.4
 */