package com.htsoft.oa.dao.admin;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.admin.BookBorRet;
import java.util.List;

public abstract interface BookBorRetDao extends BaseDao<BookBorRet>
{
  public abstract BookBorRet getByBookSnId(Long paramLong);

  public abstract List getBorrowInfo(PagingBean paramPagingBean);

  public abstract List getReturnInfo(PagingBean paramPagingBean);

  public abstract Long getBookBorRetId(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.BookBorRetDao
 * JD-Core Version:    0.5.4
 */