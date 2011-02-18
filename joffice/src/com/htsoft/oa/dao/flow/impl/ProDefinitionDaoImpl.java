package com.htsoft.oa.dao.flow.impl;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.flow.ProDefinitionDao;
import com.htsoft.oa.model.flow.ProDefinition;

public class ProDefinitionDaoImpl extends BaseDaoImpl<ProDefinition> implements ProDefinitionDao {
	public ProDefinitionDaoImpl() {
		super(ProDefinition.class);
	}

	public ProDefinition getByDeployId(String deployId) {
		String hql = "from ProDefinition pd where pd.deployId=?";
		return (ProDefinition) findUnique(hql, new Object[] { deployId });
	}

	public ProDefinition getByName(String name) {
		String hql = "from ProDefinition pd where pd.name=?";
		return (ProDefinition) findUnique(hql, new Object[] { name });
	}
}


 
 
 
 
 