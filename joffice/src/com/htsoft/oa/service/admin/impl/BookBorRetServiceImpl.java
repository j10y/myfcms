 package com.htsoft.oa.service.admin.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.admin.BookBorRetDao;
 import com.htsoft.oa.model.admin.BookBorRet;
 import com.htsoft.oa.service.admin.BookBorRetService;
 import java.util.List;
 
 public class BookBorRetServiceImpl extends BaseServiceImpl<BookBorRet>
   implements BookBorRetService
 {
   private BookBorRetDao dao;
 
   public BookBorRetServiceImpl(BookBorRetDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public BookBorRet getByBookSnId(Long bookSnId)
   {
     return this.dao.getByBookSnId(bookSnId);
   }
 
   public List getBorrowInfo(PagingBean pb)
   {
     return this.dao.getBorrowInfo(pb);
   }
 
   public List getReturnInfo(PagingBean pb)
   {
     return this.dao.getReturnInfo(pb);
   }
 
   public Long getBookBorRetId(Long snId)
   {
     return this.dao.getBookBorRetId(snId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.BookBorRetServiceImpl
 * JD-Core Version:    0.5.4
 */