package com.htsoft.oa.service.hrm.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.hrm.SalaryItemDao;
import com.htsoft.oa.model.hrm.SalaryItem;
import com.htsoft.oa.service.hrm.SalaryItemService;

public class SalaryItemServiceImpl extends BaseServiceImpl<SalaryItem> implements SalaryItemService {
	private SalaryItemDao dao;

	public SalaryItemServiceImpl(SalaryItemDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<SalaryItem> getAllExcludeId(String excludeIds, PagingBean pb) {
		return this.dao.getAllExcludeId(excludeIds, pb);
	}
}


 
 
 
 
 