package com.htsoft.oa.service.document;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.document.DocPrivilege;
import com.htsoft.oa.model.system.AppUser;
import java.util.List;

public abstract interface DocPrivilegeService extends BaseService<DocPrivilege>
{
  public abstract List<DocPrivilege> getAll(DocPrivilege paramDocPrivilege, Long paramLong, PagingBean paramPagingBean);

  public abstract List<Integer> getRightsByFolder(AppUser paramAppUser, Long paramLong);

  public abstract Integer getRightsByDocument(AppUser paramAppUser, Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.document.DocPrivilegeService
 * JD-Core Version:    0.5.4
 */