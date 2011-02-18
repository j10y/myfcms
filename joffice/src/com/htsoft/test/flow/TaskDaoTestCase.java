 package com.htsoft.test.flow;
 
 import com.htsoft.oa.dao.flow.TaskDao;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class TaskDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private TaskDao taskDao;
 
   @Test
   public void testPersonTask()
   {
     String userId = "1";
   }
 }


 
 
 