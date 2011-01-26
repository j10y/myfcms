 package com.htsoft.test.flow;
 
 import com.htsoft.oa.dao.flow.ProTypeDao;
 import com.htsoft.oa.model.flow.ProType;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ProTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ProTypeDao proTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ProType proType = new ProType();
 
     this.proTypeDao.save(proType);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.ProTypeDaoTestCase
 * JD-Core Version:    0.5.4
 */