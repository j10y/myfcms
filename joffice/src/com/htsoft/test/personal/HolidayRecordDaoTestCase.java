 package com.htsoft.test.personal;
 
 import com.htsoft.oa.dao.personal.HolidayRecordDao;
 import com.htsoft.oa.model.personal.HolidayRecord;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class HolidayRecordDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private HolidayRecordDao holidayRecordDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     HolidayRecord holidayRecord = new HolidayRecord();
 
     this.holidayRecordDao.save(holidayRecord);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.personal.HolidayRecordDaoTestCase
 * JD-Core Version:    0.5.4
 */