 package com.htsoft.test.flow;
 
 import com.htsoft.oa.dao.flow.ProUserAssignDao;
 import com.htsoft.oa.model.flow.ProUserAssign;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ProUserAssignDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ProUserAssignDao proUserAssignDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ProUserAssign proUserAssign = new ProUserAssign();
 
     this.proUserAssignDao.save(proUserAssign);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.ProUserAssignDaoTestCase
 * JD-Core Version:    0.5.4
 */