package com.htsoft.oa.service.hrm.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.hrm.JobDao;
import com.htsoft.oa.model.hrm.Job;
import com.htsoft.oa.service.hrm.JobService;

public class JobServiceImpl extends BaseServiceImpl<Job> implements JobService {
	private JobDao dao;

	public JobServiceImpl(JobDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Job> findByDep(Long depId) {
		return this.dao.findByDep(depId);
	}
}


 
 
 
 
 