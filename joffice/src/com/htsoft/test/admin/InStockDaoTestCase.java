 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.InStockDao;
 import com.htsoft.test.BaseTestCase;
 import java.io.PrintStream;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class InStockDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private InStockDao inStockDao;
 
   @Test
   public void test1()
   {
     Integer inCount = this.inStockDao.findInCountByBuyId(Long.valueOf(1L));
     System.out.println(inCount);
   }
 }


 
 
 