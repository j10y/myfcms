package com.htsoft.oa.service.system.impl;

import java.util.HashMap;
import java.util.Set;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.AppRoleDao;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.service.system.AppRoleService;

public class AppRoleServiceImpl extends BaseServiceImpl<AppRole> implements AppRoleService {
	private AppRoleDao dao;

	public AppRoleServiceImpl(AppRoleDao dao) {
		super(dao);
		this.dao = dao;
	}

	public AppRole getByRoleName(String roleName) {
		return this.dao.getByRoleName(roleName);
	}

	public HashMap<String, Set<String>> getSecurityDataSource() {
		return this.dao.getSecurityDataSource();
	}
}


 
 
 
 
 