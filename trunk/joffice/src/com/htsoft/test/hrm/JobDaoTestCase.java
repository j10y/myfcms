 package com.htsoft.test.hrm;
 
 import com.htsoft.oa.dao.hrm.JobDao;
 import com.htsoft.oa.model.hrm.Job;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class JobDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private JobDao jobDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Job job = new Job();
 
     this.jobDao.save(job);
   }
 }


 
 
 