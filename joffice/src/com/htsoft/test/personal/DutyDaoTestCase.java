 package com.htsoft.test.personal;
 
 import com.htsoft.oa.dao.personal.DutyDao;
 import com.htsoft.oa.model.personal.Duty;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DutyDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DutyDao dutyDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Duty duty = new Duty();
 
     this.dutyDao.save(duty);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.personal.DutyDaoTestCase
 * JD-Core Version:    0.5.4
 */