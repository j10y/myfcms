 package com.htsoft.test.flow;
 
 import com.htsoft.oa.dao.flow.ProcessRunDao;
 import com.htsoft.oa.model.flow.ProcessRun;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ProcessRunDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ProcessRunDao processRunDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ProcessRun processRun = new ProcessRun();
 
     this.processRunDao.save(processRun);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.ProcessRunDaoTestCase
 * JD-Core Version:    0.5.4
 */