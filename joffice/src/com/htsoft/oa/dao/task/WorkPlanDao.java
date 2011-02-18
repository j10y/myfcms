package com.htsoft.oa.dao.task;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.task.WorkPlan;

public abstract interface WorkPlanDao extends BaseDao<WorkPlan> {
	public abstract List<WorkPlan> findByDepartment(WorkPlan paramWorkPlan, AppUser paramAppUser,
			PagingBean paramPagingBean);
}


 
 
 
 