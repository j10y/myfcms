package com.htsoft.oa.service.hrm.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.hrm.StandSalaryItemDao;
import com.htsoft.oa.model.hrm.StandSalaryItem;
import com.htsoft.oa.service.hrm.StandSalaryItemService;

public class StandSalaryItemServiceImpl extends BaseServiceImpl<StandSalaryItem> implements
		StandSalaryItemService {
	private StandSalaryItemDao dao;

	public StandSalaryItemServiceImpl(StandSalaryItemDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<StandSalaryItem> getAllByStandardId(Long standardId) {
		return this.dao.getAllByStandardId(standardId);
	}
}


 
 
 
 
 