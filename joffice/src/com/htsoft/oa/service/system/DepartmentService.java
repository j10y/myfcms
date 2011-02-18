package com.htsoft.oa.service.system;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.Department;

public abstract interface DepartmentService extends BaseService<Department> {
	public abstract List<Department> findByParentId(Long paramLong);

	public abstract List<Department> findByDepName(String paramString);

	public abstract List<Department> findByPath(String paramString);
}


 
 
 
 
 