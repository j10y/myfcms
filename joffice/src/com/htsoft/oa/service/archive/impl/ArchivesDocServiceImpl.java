package com.htsoft.oa.service.archive.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchivesDocDao;
import com.htsoft.oa.model.archive.ArchivesDoc;
import com.htsoft.oa.service.archive.ArchivesDocService;

public class ArchivesDocServiceImpl extends BaseServiceImpl<ArchivesDoc> implements
		ArchivesDocService {
	private ArchivesDocDao dao;

	public ArchivesDocServiceImpl(ArchivesDocDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<ArchivesDoc> findByAid(Long archivesId) {
		return this.dao.findByAid(archivesId);
	}
}


 
 
 
 
 