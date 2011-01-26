 package com.htsoft.test.customer;
 
 import com.htsoft.oa.dao.customer.CustomerDao;
 import com.htsoft.oa.model.customer.Customer;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class CustomerDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private CustomerDao customerDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Customer customer = new Customer();
     customer.setCustomerName("Customer1");
     this.customerDao.save(customer);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.customer.CustomerDaoTestCase
 * JD-Core Version:    0.5.4
 */