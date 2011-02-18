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


 
 
 