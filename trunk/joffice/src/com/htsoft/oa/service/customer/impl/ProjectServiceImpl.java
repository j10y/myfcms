package com.htsoft.oa.service.customer.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.customer.ProjectDao;
import com.htsoft.oa.model.customer.Project;
import com.htsoft.oa.service.customer.ProjectService;

public class ProjectServiceImpl extends BaseServiceImpl<Project> implements ProjectService {
	private ProjectDao dao;

	public ProjectServiceImpl(ProjectDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean checkProjectNo(String projectNo) {
		return this.dao.checkProjectNo(projectNo);
	}
}


 
 
 
 
 