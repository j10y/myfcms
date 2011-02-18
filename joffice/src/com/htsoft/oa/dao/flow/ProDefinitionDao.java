package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.ProDefinition;

public abstract interface ProDefinitionDao extends BaseDao<ProDefinition> {
	public abstract ProDefinition getByDeployId(String paramString);

	public abstract ProDefinition getByName(String paramString);
}


 
 
 
 
 