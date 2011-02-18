package com.htsoft.oa.service.personal.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.personal.ErrandsRegisterDao;
import com.htsoft.oa.model.personal.ErrandsRegister;
import com.htsoft.oa.service.personal.ErrandsRegisterService;

public class ErrandsRegisterServiceImpl extends BaseServiceImpl<ErrandsRegister> implements
		ErrandsRegisterService {
	private ErrandsRegisterDao dao;

	public ErrandsRegisterServiceImpl(ErrandsRegisterDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 
 