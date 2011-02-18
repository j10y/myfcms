package com.htsoft.oa.service.system.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.DepartmentDao;
import com.htsoft.oa.model.system.Department;
import com.htsoft.oa.service.system.DepartmentService;

public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {
	private DepartmentDao dao;

	public DepartmentServiceImpl(DepartmentDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Department> findByParentId(Long parentId) {
		return this.dao.findByParentId(parentId);
	}

	public List<Department> findByDepName(String depName) {
		return this.dao.findByDepName(depName);
	}

	public List<Department> findByPath(String path) {
		return this.dao.findByPath(path);
	}
}


 
 
 
 
 