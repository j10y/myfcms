package com.htsoft.oa.service.archive;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.archive.ArchDispatch;
import com.htsoft.oa.model.system.AppUser;
import java.util.List;

public abstract interface ArchDispatchService extends BaseService<ArchDispatch>
{
  public abstract List<ArchDispatch> findByUser(AppUser paramAppUser, PagingBean paramPagingBean);

  public abstract int countArchDispatch(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.ArchDispatchService
 * JD-Core Version:    0.5.4
 */