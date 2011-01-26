 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.BookSnDao;
 import com.htsoft.oa.model.admin.BookSn;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class BookSnDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private BookSnDao bookSnDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     BookSn bookSn = new BookSn();
 
     this.bookSnDao.save(bookSn);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.BookSnDaoTestCase
 * JD-Core Version:    0.5.4
 */