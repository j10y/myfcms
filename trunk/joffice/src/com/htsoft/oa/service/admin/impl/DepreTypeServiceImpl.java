package com.htsoft.oa.service.admin.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.DepreTypeDao;
import com.htsoft.oa.model.admin.DepreType;
import com.htsoft.oa.service.admin.DepreTypeService;

public class DepreTypeServiceImpl extends BaseServiceImpl<DepreType> implements DepreTypeService {
	private DepreTypeDao dao;

	public DepreTypeServiceImpl(DepreTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 