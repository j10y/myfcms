 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.DepreTypeDao;
 import com.htsoft.oa.model.admin.DepreType;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DepreTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DepreTypeDao depreTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     DepreType depreType = new DepreType();
 
     this.depreTypeDao.save(depreType);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.DepreTypeDaoTestCase
 * JD-Core Version:    0.5.4
 */