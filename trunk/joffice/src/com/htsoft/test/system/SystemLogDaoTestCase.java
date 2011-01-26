 package com.htsoft.test.system;
 
 import com.htsoft.oa.dao.system.SystemLogDao;
 import com.htsoft.oa.model.system.SystemLog;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class SystemLogDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private SystemLogDao systemLogDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     SystemLog systemLog = new SystemLog();
 
     this.systemLogDao.save(systemLog);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.system.SystemLogDaoTestCase
 * JD-Core Version:    0.5.4
 */