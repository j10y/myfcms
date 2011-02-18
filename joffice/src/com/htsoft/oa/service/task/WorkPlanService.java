package com.htsoft.oa.service.task;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.task.WorkPlan;

public abstract interface WorkPlanService extends BaseService<WorkPlan> {
	public abstract List<WorkPlan> findByDepartment(WorkPlan paramWorkPlan, AppUser paramAppUser,
			PagingBean paramPagingBean);
}


 
 
 
 
 