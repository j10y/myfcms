package com.htsoft.oa.service.hrm.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.hrm.StandSalaryDao;
import com.htsoft.oa.model.hrm.StandSalary;
import com.htsoft.oa.service.hrm.StandSalaryService;

public class StandSalaryServiceImpl extends BaseServiceImpl<StandSalary> implements
		StandSalaryService {
	private StandSalaryDao dao;

	public StandSalaryServiceImpl(StandSalaryDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean checkStandNo(String standardNo) {
		return this.dao.checkStandNo(standardNo);
	}

	public List<StandSalary> findByPassCheck() {
		return this.dao.findByPassCheck();
	}
}


 
 
 
 
 