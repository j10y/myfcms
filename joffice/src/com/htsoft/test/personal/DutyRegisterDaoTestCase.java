 package com.htsoft.test.personal;
 
 import com.htsoft.oa.dao.personal.DutyRegisterDao;
 import com.htsoft.oa.model.personal.DutyRegister;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DutyRegisterDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DutyRegisterDao dutyRegisterDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     DutyRegister dutyRegister = new DutyRegister();
 
     this.dutyRegisterDao.save(dutyRegister);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.personal.DutyRegisterDaoTestCase
 * JD-Core Version:    0.5.4
 */