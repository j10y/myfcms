package com.htsoft.oa.service.flow;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.ProUserAssign;

public abstract interface ProUserAssignService extends BaseService<ProUserAssign> {
	public abstract List<ProUserAssign> getByDeployId(String paramString);

	public abstract ProUserAssign getByDeployIdActivityName(String paramString1, String paramString2);
}


 
 
 
 
 