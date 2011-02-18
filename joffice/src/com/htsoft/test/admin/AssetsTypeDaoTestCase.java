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


 
 
 