package com.htsoft.oa.dao.system;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.Department;

public abstract interface DepartmentDao extends BaseDao<Department> {
	public abstract List<Department> findByParentId(Long paramLong);

	public abstract List<Department> findByVo(Department paramDepartment, PagingBean paramPagingBean);

	public abstract List<Department> findByDepName(String paramString);

	public abstract List<Department> findByPath(String paramString);
}


 
 
 
 