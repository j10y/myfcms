package com.htsoft.oa.service.archive.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchivesAttendDao;
import com.htsoft.oa.model.archive.ArchivesAttend;
import com.htsoft.oa.service.archive.ArchivesAttendService;

public class ArchivesAttendServiceImpl extends BaseServiceImpl<ArchivesAttend> implements
		ArchivesAttendService {
	private ArchivesAttendDao dao;

	public ArchivesAttendServiceImpl(ArchivesAttendDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 