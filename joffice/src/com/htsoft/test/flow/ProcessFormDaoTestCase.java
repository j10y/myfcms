 package com.htsoft.test.flow;
 
 import com.htsoft.oa.dao.flow.ProcessFormDao;
 import com.htsoft.oa.model.flow.ProcessForm;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ProcessFormDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ProcessFormDao processFormDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ProcessForm processForm = new ProcessForm();
 
     this.processFormDao.save(processForm);
   }
 }


 
 
 