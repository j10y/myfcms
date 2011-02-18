package com.htsoft.oa.service.system;

import java.util.Map;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.SysConfig;

public abstract interface SysConfigService extends BaseService<SysConfig> {
	public abstract SysConfig findByKey(String paramString);

	public abstract Map findByType();
}


 
 
 
 
 