 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.BookTypeDao;
 import com.htsoft.oa.model.admin.BookType;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class BookTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private BookTypeDao bookTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     BookType bookType = new BookType();
 
     this.bookTypeDao.save(bookType);
   }
 }


 
 
 