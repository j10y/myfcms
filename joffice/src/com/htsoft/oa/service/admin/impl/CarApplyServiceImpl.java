package com.htsoft.oa.service.admin.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.CarApplyDao;
import com.htsoft.oa.model.admin.CarApply;
import com.htsoft.oa.service.admin.CarApplyService;

public class CarApplyServiceImpl extends BaseServiceImpl<CarApply> implements CarApplyService {
	private CarApplyDao dao;

	public CarApplyServiceImpl(CarApplyDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 