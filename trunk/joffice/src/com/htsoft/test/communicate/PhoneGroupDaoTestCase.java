 package com.htsoft.test.communicate;
 
 import com.htsoft.oa.dao.communicate.PhoneGroupDao;
 import com.htsoft.oa.model.communicate.PhoneGroup;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class PhoneGroupDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private PhoneGroupDao phoneGroupDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     PhoneGroup phoneGroup = new PhoneGroup();
 
     this.phoneGroupDao.save(phoneGroup);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.communicate.PhoneGroupDaoTestCase
 * JD-Core Version:    0.5.4
 */