 package com.htsoft.test.hrm;
 
 import com.htsoft.oa.dao.hrm.EmpProfileDao;
 import com.htsoft.oa.model.hrm.EmpProfile;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class EmpProfileDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private EmpProfileDao empProfileDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     EmpProfile empProfile = new EmpProfile();
 
     this.empProfileDao.save(empProfile);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.hrm.EmpProfileDaoTestCase
 * JD-Core Version:    0.5.4
 */