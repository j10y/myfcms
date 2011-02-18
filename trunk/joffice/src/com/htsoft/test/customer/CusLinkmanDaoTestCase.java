 package com.htsoft.test.customer;
 
 import com.htsoft.oa.dao.customer.CusLinkmanDao;
 import com.htsoft.oa.model.customer.CusLinkman;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class CusLinkmanDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private CusLinkmanDao cusLinkmanDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     CusLinkman cusLinkman = new CusLinkman();
 
     this.cusLinkmanDao.save(cusLinkman);
   }
 }


 
 
 