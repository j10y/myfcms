package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.FunUrl;

public abstract interface FunUrlService extends BaseService<FunUrl> {
	public abstract FunUrl getByPathFunId(String paramString, Long paramLong);
}


 
 
 
 
 