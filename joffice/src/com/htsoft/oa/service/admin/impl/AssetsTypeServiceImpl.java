package com.htsoft.oa.service.admin.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.AssetsTypeDao;
import com.htsoft.oa.model.admin.AssetsType;
import com.htsoft.oa.service.admin.AssetsTypeService;

public class AssetsTypeServiceImpl extends BaseServiceImpl<AssetsType> implements AssetsTypeService {
	private AssetsTypeDao dao;

	public AssetsTypeServiceImpl(AssetsTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 