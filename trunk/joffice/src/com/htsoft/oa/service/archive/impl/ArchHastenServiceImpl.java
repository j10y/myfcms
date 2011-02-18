package com.htsoft.oa.service.archive.impl;

import java.util.Date;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchHastenDao;
import com.htsoft.oa.model.archive.ArchHasten;
import com.htsoft.oa.service.archive.ArchHastenService;

public class ArchHastenServiceImpl extends BaseServiceImpl<ArchHasten> implements ArchHastenService {
	private ArchHastenDao dao;

	public ArchHastenServiceImpl(ArchHastenDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Date getLeastRecordByUser(Long archivesId) {
		return this.dao.getLeastRecordByUser(archivesId);
	}
}


 
 
 
 
 