package com.htsoft.oa.service.archive;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.archive.Archives;
import com.htsoft.oa.model.system.AppRole;
import java.util.List;
import java.util.Set;

public abstract interface ArchivesService extends BaseService<Archives>
{
  public abstract List<Archives> findByUserOrRole(Long paramLong, Set<AppRole> paramSet, PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.ArchivesService
 * JD-Core Version:    0.5.4
 */