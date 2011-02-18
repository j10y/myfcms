package com.htsoft.oa.service.task.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.task.WorkPlanDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.task.WorkPlan;
import com.htsoft.oa.service.task.WorkPlanService;

public class WorkPlanServiceImpl extends BaseServiceImpl<WorkPlan> implements WorkPlanService {
	private WorkPlanDao dao;

	public WorkPlanServiceImpl(WorkPlanDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<WorkPlan> findByDepartment(WorkPlan workPlan, AppUser user, PagingBean pb) {
		return this.dao.findByDepartment(workPlan, user, pb);
	}
}


 
 
 
 
 