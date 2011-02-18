package com.htsoft.oa.service.archive.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchivesDepDao;
import com.htsoft.oa.model.archive.ArchivesDep;
import com.htsoft.oa.service.archive.ArchivesDepService;

public class ArchivesDepServiceImpl extends BaseServiceImpl<ArchivesDep> implements
		ArchivesDepService {
	private ArchivesDepDao dao;

	public ArchivesDepServiceImpl(ArchivesDepDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 