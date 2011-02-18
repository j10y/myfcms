package com.htsoft.oa.service.hrm.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.hrm.SalaryPayoffDao;
import com.htsoft.oa.model.hrm.SalaryPayoff;
import com.htsoft.oa.service.hrm.SalaryPayoffService;

public class SalaryPayoffServiceImpl extends BaseServiceImpl<SalaryPayoff> implements
		SalaryPayoffService {
	private SalaryPayoffDao dao;

	public SalaryPayoffServiceImpl(SalaryPayoffDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 