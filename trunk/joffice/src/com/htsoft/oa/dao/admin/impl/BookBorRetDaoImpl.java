 package com.htsoft.oa.dao.admin.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.admin.BookBorRetDao;
 import com.htsoft.oa.model.admin.BookBorRet;
 import java.util.List;
 
 public class BookBorRetDaoImpl extends BaseDaoImpl<BookBorRet>
   implements BookBorRetDao
 {
   public BookBorRetDaoImpl()
   {
     super(BookBorRet.class);
   }
 
   public BookBorRet getByBookSnId(Long bookSnId)
   {
     String hql = "from BookBorRet bookBorRet where bookBorRet.bookSn.bookSnId=?";
     Object[] params = { bookSnId };
     return (BookBorRet)findByHql(hql, params).get(0);
   }
 
   public List<BookBorRet> getBorrowInfo(PagingBean pb)
   {
     String hql = "select bookBorRet from BookBorRet bookBorRet,BookSn bookSn where bookBorRet.bookSn.bookSnId=bookSn.bookSnId and bookSn.status=1";
     return findByHql(hql, null, pb);
   }
 
   public List<BookBorRet> getReturnInfo(PagingBean pb)
   {
     String hql = "select bookBorRet from BookBorRet bookBorRet,BookSn bookSn where bookBorRet.bookSn.bookSnId=bookSn.bookSnId and bookSn.status=0";
     return findByHql(hql, null, pb);
   }
 
   public Long getBookBorRetId(Long snId)
   {
     String hql = "from BookBorRet vo where vo.bookSn.bookSnId=?";
     Object[] objs = { snId };
     List list = findByHql(hql, objs);
     if (list.size() == 1) {
       return ((BookBorRet)list.get(0)).getRecordId();
     }
     return null;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.impl.BookBorRetDaoImpl
 * JD-Core Version:    0.5.4
 */