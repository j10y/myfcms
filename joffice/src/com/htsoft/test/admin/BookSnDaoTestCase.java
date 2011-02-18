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


 
 
 