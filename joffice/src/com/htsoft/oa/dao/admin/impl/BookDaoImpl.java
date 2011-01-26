 package com.htsoft.oa.dao.admin.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.admin.BookDao;
 import com.htsoft.oa.model.admin.Book;
 import java.util.List;
 
 public class BookDaoImpl extends BaseDaoImpl<Book>
   implements BookDao
 {
   public BookDaoImpl()
   {
     super(Book.class);
   }
 
   public List<Book> findByTypeId(Long typeId, PagingBean pb)
   {
     String hql = "from Book b where b.bookType.typeId=?";
     Object[] params = { typeId };
     return findByHql("from Book b where b.bookType.typeId=?", params, pb);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.impl.BookDaoImpl
 * JD-Core Version:    0.5.4
 */