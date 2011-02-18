package com.htsoft.oa.service.customer.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.customer.ContractConfigDao;
import com.htsoft.oa.model.customer.ContractConfig;
import com.htsoft.oa.service.customer.ContractConfigService;

public class ContractConfigServiceImpl extends BaseServiceImpl<ContractConfig> implements
		ContractConfigService {
	private ContractConfigDao dao;

	public ContractConfigServiceImpl(ContractConfigDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 