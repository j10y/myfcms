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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.InStockDaoTestCase
 * JD-Core Version:    0.5.4
 */