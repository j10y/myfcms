package com.htsoft.oa.service.hrm;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.hrm.Job;

public abstract interface JobService extends BaseService<Job> {
	public abstract List<Job> findByDep(Long paramLong);
}


 
 
 
 