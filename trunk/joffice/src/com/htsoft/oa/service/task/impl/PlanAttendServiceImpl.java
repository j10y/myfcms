package com.htsoft.oa.service.task.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.task.PlanAttendDao;
import com.htsoft.oa.model.task.PlanAttend;
import com.htsoft.oa.service.task.PlanAttendService;

public class PlanAttendServiceImpl extends BaseServiceImpl<PlanAttend> implements PlanAttendService {
	private PlanAttendDao dao;

	public PlanAttendServiceImpl(PlanAttendDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean deletePlanAttend(Long planId, Short isDep, Short isPrimary) {
		List<PlanAttend> list = this.dao.FindPlanAttend(planId, isDep, isPrimary);
		for (PlanAttend pa : list) {
			this.dao.remove(pa.getAttendId());
		}
		return true;
	}
}


 
 
 
 
 