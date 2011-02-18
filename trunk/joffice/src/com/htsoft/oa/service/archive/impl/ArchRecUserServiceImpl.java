package com.htsoft.oa.service.archive.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchRecUserDao;
import com.htsoft.oa.model.archive.ArchRecUser;
import com.htsoft.oa.service.archive.ArchRecUserService;

public class ArchRecUserServiceImpl extends BaseServiceImpl<ArchRecUser> implements
		ArchRecUserService {
	private ArchRecUserDao dao;

	public ArchRecUserServiceImpl(ArchRecUserDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List findDepAll() {
		return this.dao.findDepAll();
	}

	public ArchRecUser getByDepId(Long depId) {
		return this.dao.getByDepId(depId);
	}
}


 
 
 
 
 