package com.htsoft.oa.dao.flow;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.ProUserAssign;

public abstract interface ProUserAssignDao extends BaseDao<ProUserAssign> {
	public abstract List<ProUserAssign> getByDeployId(String paramString);

	public abstract ProUserAssign getByDeployIdActivityName(String paramString1, String paramString2);
}


 
 
 
 
 