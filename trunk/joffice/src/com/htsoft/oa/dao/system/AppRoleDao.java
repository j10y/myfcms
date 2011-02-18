package com.htsoft.oa.dao.system;

import java.util.HashMap;
import java.util.Set;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.AppRole;

public abstract interface AppRoleDao extends BaseDao<AppRole> {
	public abstract AppRole getByRoleName(String paramString);

	public abstract HashMap<String, Set<String>> getSecurityDataSource();
}


 
 
 
 