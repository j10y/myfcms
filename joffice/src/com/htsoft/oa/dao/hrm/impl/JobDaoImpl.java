package com.htsoft.oa.dao.hrm.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.hrm.JobDao;
import com.htsoft.oa.model.hrm.Job;

public class JobDaoImpl extends BaseDaoImpl<Job> implements JobDao {
	public JobDaoImpl() {
		super(Job.class);
	}

	public List<Job> findByDep(Long depId) {
		String hql = "from Job vo where vo.department.depId=?";
		Object[] objs = { depId };
		return findByHql(hql, objs);
	}
}


 
 
 
 