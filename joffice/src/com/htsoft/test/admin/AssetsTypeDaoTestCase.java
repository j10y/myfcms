 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.AssetsTypeDao;
 import com.htsoft.oa.model.admin.AssetsType;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class AssetsTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private AssetsTypeDao assetsTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     AssetsType assetsType = new AssetsType();
 
     this.assetsTypeDao.save(assetsType);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.AssetsTypeDaoTestCase
 * JD-Core Version:    0.5.4
 */