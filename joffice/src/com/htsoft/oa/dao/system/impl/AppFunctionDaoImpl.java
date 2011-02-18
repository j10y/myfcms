package com.htsoft.oa.dao.system.impl;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.AppFunctionDao;
import com.htsoft.oa.model.system.AppFunction;

public class AppFunctionDaoImpl extends BaseDaoImpl<AppFunction> implements AppFunctionDao {
	public AppFunctionDaoImpl() {
		super(AppFunction.class);
	}

	public AppFunction getByKey(String key) {
		String hql = "from AppFunction af where af.funKey=?";
		return (AppFunction) findUnique(hql, new String[] { key });
	}
}


 
 
 
 
 