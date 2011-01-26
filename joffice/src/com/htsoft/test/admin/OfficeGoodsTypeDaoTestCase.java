 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.OfficeGoodsTypeDao;
 import com.htsoft.oa.model.admin.OfficeGoodsType;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class OfficeGoodsTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private OfficeGoodsTypeDao officeGoodsTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     OfficeGoodsType officeGoodsType = new OfficeGoodsType();
 
     this.officeGoodsTypeDao.save(officeGoodsType);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.OfficeGoodsTypeDaoTestCase
 * JD-Core Version:    0.5.4
 */