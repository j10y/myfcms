 package com.htsoft.test.personal;
 
 import com.htsoft.oa.dao.personal.DutySectionDao;
 import com.htsoft.oa.model.personal.DutySection;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DutySectionDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DutySectionDao dutySectionDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     DutySection dutySection = new DutySection();
 
     this.dutySectionDao.save(dutySection);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.personal.DutySectionDaoTestCase
 * JD-Core Version:    0.5.4
 */