package com.htsoft.oa.service.customer.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.customer.CusConnectionDao;
import com.htsoft.oa.model.customer.CusConnection;
import com.htsoft.oa.service.customer.CusConnectionService;

public class CusConnectionServiceImpl extends BaseServiceImpl<CusConnection> implements
		CusConnectionService {
	private CusConnectionDao dao;

	public CusConnectionServiceImpl(CusConnectionDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 