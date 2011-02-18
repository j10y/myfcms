package com.htsoft.oa.service.system;

import java.util.HashMap;
import java.util.Set;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.AppRole;

public abstract interface AppRoleService extends BaseService<AppRole> {
	public abstract AppRole getByRoleName(String paramString);

	public abstract HashMap<String, Set<String>> getSecurityDataSource();
}


 
 
 
 
 