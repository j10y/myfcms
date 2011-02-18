package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.AppFunction;

public abstract interface AppFunctionDao extends BaseDao<AppFunction> {
	public abstract AppFunction getByKey(String paramString);
}


 
 
 
 
 