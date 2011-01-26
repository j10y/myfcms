package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.Department;
import java.util.List;

public abstract interface DepartmentDao extends BaseDao<Department>
{
  public abstract List<Department> findByParentId(Long paramLong);

  public abstract List<Department> findByVo(Department paramDepartment, PagingBean paramPagingBean);

  public abstract List<Department> findByDepName(String paramString);

  public abstract List<Department> findByPath(String paramString);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.DepartmentDao
 * JD-Core Version:    0.5.4
 */