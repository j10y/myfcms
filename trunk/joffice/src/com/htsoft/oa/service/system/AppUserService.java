package com.htsoft.oa.service.system;

import java.util.List;
import java.util.Set;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.AppUser;

public abstract interface AppUserService extends BaseService<AppUser> {
	public abstract AppUser findByUserName(String paramString);

	public abstract List findByDepartment(String paramString, PagingBean paramPagingBean);

	public abstract List findByRole(Long paramLong, PagingBean paramPagingBean);

	public abstract List findByRoleId(Long paramLong);

	public abstract List<AppUser> findSubAppUser(String paramString, Set<Long> paramSet,
			PagingBean paramPagingBean);

	public abstract List<AppUser> findSubAppUserByRole(Long paramLong, Set<Long> paramSet,
			PagingBean paramPagingBean);

	public abstract List<AppUser> findByDepId(Long paramLong);
}


 
 
 
 
 