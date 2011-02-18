package com.htsoft.oa.service.archive.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.LeaderReadDao;
import com.htsoft.oa.model.archive.LeaderRead;
import com.htsoft.oa.service.archive.LeaderReadService;

public class LeaderReadServiceImpl extends BaseServiceImpl<LeaderRead> implements LeaderReadService {
	private LeaderReadDao dao;

	public LeaderReadServiceImpl(LeaderReadDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 