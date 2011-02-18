package com.htsoft.oa.service.personal.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.personal.DutySectionDao;
import com.htsoft.oa.model.personal.DutySection;
import com.htsoft.oa.service.personal.DutySectionService;

public class DutySectionServiceImpl extends BaseServiceImpl<DutySection> implements
		DutySectionService {
	private DutySectionDao dao;

	public DutySectionServiceImpl(DutySectionDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 