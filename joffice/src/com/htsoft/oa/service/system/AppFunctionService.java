package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.AppFunction;

public abstract interface AppFunctionService extends BaseService<AppFunction> {
	public abstract AppFunction getByKey(String paramString);
}


 
 
 
 
 