package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.FunUrl;

public abstract interface FunUrlDao extends BaseDao<FunUrl> {
	public abstract FunUrl getByPathFunId(String paramString, Long paramLong);
}


 
 
 
 