 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.BookBorRetDao;
 import com.htsoft.oa.model.admin.BookBorRet;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class BookBorRetDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private BookBorRetDao bookBorRetDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     BookBorRet bookBorRet = new BookBorRet();
 
     this.bookBorRetDao.save(bookBorRet);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.BookBorRetDaoTestCase
 * JD-Core Version:    0.5.4
 */