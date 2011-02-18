package com.htsoft.oa.dao.personal.impl;

import java.util.Date;
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.personal.DutyDao;
import com.htsoft.oa.model.personal.Duty;

public class DutyDaoImpl extends BaseDaoImpl<Duty> implements DutyDao {
	public DutyDaoImpl() {
		super(Duty.class);
	}

	public List<Duty> getUserDutyByTime(Long userId, Date startTime, Date endTime) {
		String hql = "from Duty dy where dy.appUser.userId=? and ((dy.startTime<=? and dy.endTime>=?) or (dy.startTime<=? and dy.endTime>=?))";
		return findByHql(hql, new Object[] { userId, startTime, startTime, endTime, endTime });
	}

	public List<Duty> getCurUserDuty(Long userId) {
		String hql = "from Duty dy where dy.appUser.userId=? and dy.startTime<=? and dy.endTime>=?";
		Date curDate = new Date();
		return findByHql(hql, new Object[] { userId, curDate, curDate });
	}
}


 
 
 
 
 