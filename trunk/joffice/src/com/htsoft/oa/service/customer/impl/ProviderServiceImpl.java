package com.htsoft.oa.service.customer.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.customer.ProviderDao;
import com.htsoft.oa.model.customer.Provider;
import com.htsoft.oa.service.customer.ProviderService;

public class ProviderServiceImpl extends BaseServiceImpl<Provider> implements ProviderService {
	private ProviderDao dao;

	public ProviderServiceImpl(ProviderDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 