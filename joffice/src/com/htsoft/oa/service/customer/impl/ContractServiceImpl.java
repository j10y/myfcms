package com.htsoft.oa.service.customer.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.customer.ContractDao;
import com.htsoft.oa.model.customer.Contract;
import com.htsoft.oa.service.customer.ContractService;

public class ContractServiceImpl extends BaseServiceImpl<Contract> implements ContractService {
	private ContractDao dao;

	public ContractServiceImpl(ContractDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 