package com.htsoft.oa.service.admin;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.admin.BookBorRet;
import java.util.List;

public abstract interface BookBorRetService extends BaseService<BookBorRet>
{
  public abstract BookBorRet getByBookSnId(Long paramLong);

  public abstract List getBorrowInfo(PagingBean paramPagingBean);

  public abstract List getReturnInfo(PagingBean paramPagingBean);

  public abstract Long getBookBorRetId(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.BookBorRetService
 * JD-Core Version:    0.5.4
 */