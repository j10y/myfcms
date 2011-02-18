package com.htsoft.oa.service.system.impl;

import java.util.List;
import java.util.Set;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.system.AppUserDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.system.AppUserService;

public class AppUserServiceImpl extends BaseServiceImpl<AppUser> implements AppUserService {
	private AppUserDao dao;

	public AppUserServiceImpl(AppUserDao dao) {
		super(dao);
		this.dao = dao;
	}

	public AppUser findByUserName(String username) {
		return this.dao.findByUserName(username);
	}

	public List findByDepartment(String path, PagingBean pb) {
		return this.dao.findByDepartment(path, pb);
	}

	public List findByRole(Long roleId, PagingBean pb) {
		return this.dao.findByRole(roleId, pb);
	}

	public List findByRoleId(Long roleId) {
		return this.dao.findByRole(roleId);
	}

	public List<AppUser> findSubAppUser(String path, Set<Long> userIds, PagingBean pb) {
		return this.dao.findSubAppUser(path, userIds, pb);
	}

	public List<AppUser> findSubAppUserByRole(Long roleId, Set<Long> userIds, PagingBean pb) {
		return this.dao.findSubAppUserByRole(roleId, userIds, pb);
	}

	public List<AppUser> findByDepId(Long depId) {
		return this.dao.findByDepId(depId);
	}
}


 
 
 
 
 