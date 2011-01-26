 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.DepreRecordDao;
 import com.htsoft.oa.model.admin.DepreRecord;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DepreRecordDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DepreRecordDao depreRecordDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     DepreRecord depreRecord = new DepreRecord();
 
     this.depreRecordDao.save(depreRecord);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.DepreRecordDaoTestCase
 * JD-Core Version:    0.5.4
 */