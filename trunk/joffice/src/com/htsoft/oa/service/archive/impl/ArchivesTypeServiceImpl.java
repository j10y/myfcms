package com.htsoft.oa.service.archive.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchivesTypeDao;
import com.htsoft.oa.model.archive.ArchivesType;
import com.htsoft.oa.service.archive.ArchivesTypeService;

public class ArchivesTypeServiceImpl extends BaseServiceImpl<ArchivesType> implements
		ArchivesTypeService {
	private ArchivesTypeDao dao;

	public ArchivesTypeServiceImpl(ArchivesTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 