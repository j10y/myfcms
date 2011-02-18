package com.htsoft.oa.dao.task;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.task.PlanAttend;

public abstract interface PlanAttendDao extends BaseDao<PlanAttend> {
	public abstract List<PlanAttend> FindPlanAttend(Long paramLong, Short paramShort1,
			Short paramShort2);
}


 
 
 
 