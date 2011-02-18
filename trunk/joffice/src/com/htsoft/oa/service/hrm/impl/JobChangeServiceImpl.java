package com.htsoft.oa.service.hrm.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.hrm.JobChangeDao;
import com.htsoft.oa.model.hrm.JobChange;
import com.htsoft.oa.service.hrm.JobChangeService;

public class JobChangeServiceImpl extends BaseServiceImpl<JobChange> implements JobChangeService {
	private JobChangeDao dao;

	public JobChangeServiceImpl(JobChangeDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 