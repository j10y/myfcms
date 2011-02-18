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


 
 
 