package com.htsoft.oa.dao.hrm;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.hrm.Job;

public abstract interface JobDao extends BaseDao<Job> {
	public abstract List<Job> findByDep(Long paramLong);
}


 
 
 
 