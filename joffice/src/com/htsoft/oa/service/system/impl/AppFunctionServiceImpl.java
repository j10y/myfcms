package com.htsoft.oa.service.system.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.AppFunctionDao;
import com.htsoft.oa.model.system.AppFunction;
import com.htsoft.oa.service.system.AppFunctionService;

public class AppFunctionServiceImpl extends BaseServiceImpl<AppFunction> implements
		AppFunctionService {
	private AppFunctionDao dao;

	public AppFunctionServiceImpl(AppFunctionDao dao) {
		super(dao);
		this.dao = dao;
	}

	public AppFunction getByKey(String key) {
		return this.dao.getByKey(key);
	}
}


 
 
 
 
 