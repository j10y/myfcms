package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.ProDefinition;

public abstract interface ProDefinitionService extends BaseService<ProDefinition> {
	public abstract ProDefinition getByDeployId(String paramString);

	public abstract ProDefinition getByName(String paramString);
}


 
 
 
 
 