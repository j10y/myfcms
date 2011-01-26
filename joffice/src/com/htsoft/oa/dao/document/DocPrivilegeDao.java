package com.htsoft.oa.dao.document;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.document.DocPrivilege;
import com.htsoft.oa.model.system.AppUser;
import java.util.List;

public abstract interface DocPrivilegeDao extends BaseDao<DocPrivilege>
{
  public abstract List<DocPrivilege> getAll(DocPrivilege paramDocPrivilege, Long paramLong, PagingBean paramPagingBean);

  public abstract List<DocPrivilege> getByPublic(DocPrivilege paramDocPrivilege, Long paramLong);

  public abstract List<Integer> getRightsByFolder(AppUser paramAppUser, Long paramLong);

  public abstract Integer getRightsByDocument(AppUser paramAppUser, Long paramLong);

  public abstract Integer countPrivilege();
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.document.DocPrivilegeDao
 * JD-Core Version:    0.5.4
 */