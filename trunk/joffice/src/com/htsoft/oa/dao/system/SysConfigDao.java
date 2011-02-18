package com.htsoft.oa.dao.system;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.SysConfig;

public abstract interface SysConfigDao extends BaseDao<SysConfig> {
	public abstract SysConfig findByKey(String paramString);

	public abstract List<SysConfig> findConfigByTypeName(String paramString);

	public abstract List findTypeNames();
}


 
 
 
 