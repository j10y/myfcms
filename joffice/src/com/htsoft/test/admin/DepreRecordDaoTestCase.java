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


 
 
 