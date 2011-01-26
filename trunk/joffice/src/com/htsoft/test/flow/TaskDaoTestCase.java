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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.TaskDaoTestCase
 * JD-Core Version:    0.5.4
 */