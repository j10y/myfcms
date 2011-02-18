package com.htsoft.oa.dao.flow;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.FormDef;

public abstract interface FormDefDao extends BaseDao<FormDef> {
	public abstract List<FormDef> getByDeployId(String paramString);

	public abstract FormDef getByDeployIdActivityName(String paramString1, String paramString2);
}


 
 
 
 