package com.htsoft.oa.service.flow.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.flow.ProTypeDao;
import com.htsoft.oa.model.flow.ProType;
import com.htsoft.oa.service.flow.ProTypeService;

public class ProTypeServiceImpl extends BaseServiceImpl<ProType> implements ProTypeService {
	private ProTypeDao dao;

	public ProTypeServiceImpl(ProTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 