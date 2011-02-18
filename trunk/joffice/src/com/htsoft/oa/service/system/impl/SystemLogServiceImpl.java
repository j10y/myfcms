package com.htsoft.oa.service.system.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.SystemLogDao;
import com.htsoft.oa.model.system.SystemLog;
import com.htsoft.oa.service.system.SystemLogService;

public class SystemLogServiceImpl extends BaseServiceImpl<SystemLog> implements SystemLogService {
	private SystemLogDao dao;

	public SystemLogServiceImpl(SystemLogDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 