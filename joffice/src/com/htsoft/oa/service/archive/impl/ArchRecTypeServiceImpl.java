package com.htsoft.oa.service.archive.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchRecTypeDao;
import com.htsoft.oa.model.archive.ArchRecType;
import com.htsoft.oa.service.archive.ArchRecTypeService;

public class ArchRecTypeServiceImpl extends BaseServiceImpl<ArchRecType> implements
		ArchRecTypeService {
	private ArchRecTypeDao dao;

	public ArchRecTypeServiceImpl(ArchRecTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 