package com.htsoft.oa.dao.task.impl;

import java.util.ArrayList;
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.task.PlanAttendDao;
import com.htsoft.oa.model.task.PlanAttend;

public class PlanAttendDaoImpl extends BaseDaoImpl<PlanAttend> implements PlanAttendDao {
	public PlanAttendDaoImpl() {
		super(PlanAttend.class);
	}

	public List<PlanAttend> FindPlanAttend(Long planId, Short isDep, Short isPrimary) {
		StringBuffer hql = new StringBuffer("from PlanAttend vo where vo.workPlan.planId=?");
		ArrayList list = new ArrayList();
		list.add(planId);
		if (isDep != null) {
			hql.append(" and vo.isDep=?");
			list.add(isDep);
		}
		if (isPrimary != null) {
			hql.append(" and vo.isPrimary=?");
			list.add(isPrimary);
		}
		return findByHql(hql.toString(), list.toArray());
	}
}


 
 
 
 
 