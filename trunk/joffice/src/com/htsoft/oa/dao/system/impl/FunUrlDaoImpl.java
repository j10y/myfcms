package com.htsoft.oa.dao.system.impl;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.FunUrlDao;
import com.htsoft.oa.model.system.FunUrl;

public class FunUrlDaoImpl extends BaseDaoImpl<FunUrl> implements FunUrlDao {
	public FunUrlDaoImpl() {
		super(FunUrl.class);
	}

	public FunUrl getByPathFunId(String path, Long funId) {
		String hql = "from FunUrl fu where fu.urlPath=? and fu.appFunction.functionId=? ";
		return (FunUrl) findUnique(hql, new Object[] { path, funId });
	}
}


 
 
 
 
 