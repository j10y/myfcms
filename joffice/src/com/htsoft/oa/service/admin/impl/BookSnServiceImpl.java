 package com.htsoft.oa.service.admin.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.admin.BookSnDao;
 import com.htsoft.oa.model.admin.BookSn;
 import com.htsoft.oa.service.admin.BookSnService;
 import java.util.List;
 
 public class BookSnServiceImpl extends BaseServiceImpl<BookSn>
   implements BookSnService
 {
   private BookSnDao dao;
 
   public BookSnServiceImpl(BookSnDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<BookSn> findByBookId(Long bookId)
   {
     return this.dao.findByBookId(bookId);
   }
 
   public List<BookSn> findBorrowByBookId(Long bookId)
   {
     return this.dao.findBorrowByBookId(bookId);
   }
 
   public List<BookSn> findReturnByBookId(Long bookId)
   {
     return this.dao.findReturnByBookId(bookId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.BookSnServiceImpl
 * JD-Core Version:    0.5.4
 */