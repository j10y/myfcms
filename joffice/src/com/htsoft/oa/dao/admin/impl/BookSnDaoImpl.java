 package com.htsoft.oa.dao.admin.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.admin.BookSnDao;
 import com.htsoft.oa.model.admin.BookSn;
 import java.util.List;
 
 public class BookSnDaoImpl extends BaseDaoImpl<BookSn>
   implements BookSnDao
 {
   public BookSnDaoImpl()
   {
     super(BookSn.class);
   }
 
   public List<BookSn> findByBookId(Long bookId)
   {
     String hql = "from BookSn b where b.book.bookId=?";
     Object[] params = { bookId };
     return findByHql("from BookSn b where b.book.bookId=?", params);
   }
 
   public List<BookSn> findBorrowByBookId(Long bookId)
   {
     String hql = "from BookSn b where b.book.bookId=? and b.status=0";
     Object[] params = { bookId };
     return findByHql("from BookSn b where b.book.bookId=? and b.status=0", params);
   }
 
   public List<BookSn> findReturnByBookId(Long bookId)
   {
     String hql = "from BookSn b where b.book.bookId=? and b.status=1";
     Object[] params = { bookId };
     return findByHql("from BookSn b where b.book.bookId=? and b.status=1", params);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.impl.BookSnDaoImpl
 * JD-Core Version:    0.5.4
 */