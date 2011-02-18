package com.htsoft.oa.service.flow;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.FormDef;

public abstract interface FormDefService extends BaseService<FormDef> {
	public abstract List<FormDef> getByDeployId(String paramString);

	public abstract FormDef getByDeployIdActivityName(String paramString1, String paramString2);
}


 
 
 
 
 