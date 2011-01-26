 package com.htsoft.test.personal;
 
 import com.htsoft.oa.dao.personal.DutySystemDao;
 import com.htsoft.oa.model.personal.DutySystem;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DutySystemDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DutySystemDao dutySystemDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     DutySystem dutySystem = new DutySystem();
 
     this.dutySystemDao.save(dutySystem);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.personal.DutySystemDaoTestCase
 * JD-Core Version:    0.5.4
 */