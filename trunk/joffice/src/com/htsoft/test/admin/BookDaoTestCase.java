 package com.htsoft.test.admin;
 
 import com.google.gson.Gson;
 import com.htsoft.oa.dao.admin.BookDao;
 import com.htsoft.oa.model.admin.Book;
 import com.htsoft.test.BaseTestCase;
 import java.io.PrintStream;
 import java.util.List;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class BookDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private BookDao bookDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Book book = new Book();
 
     this.bookDao.save(book);
   }
   @Test
   public void Testt() {
     List list = this.bookDao.getAll();
     Gson gson = new Gson();
     System.out.println(gson.toJson(list));
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.BookDaoTestCase
 * JD-Core Version:    0.5.4
 */