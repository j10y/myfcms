package com.htsoft.oa.service.archive.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchFlowConfDao;
import com.htsoft.oa.model.archive.ArchFlowConf;
import com.htsoft.oa.service.archive.ArchFlowConfService;

public class ArchFlowConfServiceImpl extends BaseServiceImpl<ArchFlowConf> implements
		ArchFlowConfService {
	private ArchFlowConfDao dao;

	public ArchFlowConfServiceImpl(ArchFlowConfDao dao) {
		super(dao);
		this.dao = dao;
	}

	public ArchFlowConf getByFlowType(Short archType) {
		return this.dao.getByFlowType(archType);
	}
}


 
 
 
 
 