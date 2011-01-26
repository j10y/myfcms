package com.htsoft.oa.service.admin;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.admin.BookSn;
import java.util.List;

public abstract interface BookSnService extends BaseService<BookSn>
{
  public abstract List<BookSn> findByBookId(Long paramLong);

  public abstract List<BookSn> findBorrowByBookId(Long paramLong);

  public abstract List<BookSn> findReturnByBookId(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.BookSnService
 * JD-Core Version:    0.5.4
 */