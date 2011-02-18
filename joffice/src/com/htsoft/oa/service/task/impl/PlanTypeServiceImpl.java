package com.htsoft.oa.service.task.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.task.PlanTypeDao;
import com.htsoft.oa.model.task.PlanType;
import com.htsoft.oa.service.task.PlanTypeService;

public class PlanTypeServiceImpl extends BaseServiceImpl<PlanType> implements PlanTypeService {
	private PlanTypeDao dao;

	public PlanTypeServiceImpl(PlanTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 