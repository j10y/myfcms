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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.customer.CusLinkmanDaoTestCase
 * JD-Core Version:    0.5.4
 */