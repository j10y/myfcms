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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.ProcessFormDaoTestCase
 * JD-Core Version:    0.5.4
 */