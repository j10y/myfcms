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


 
 
 