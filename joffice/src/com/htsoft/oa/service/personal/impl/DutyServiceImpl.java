package com.htsoft.oa.service.personal.impl;

import java.util.Date;
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.personal.DutyDao;
import com.htsoft.oa.model.personal.Duty;
import com.htsoft.oa.service.personal.DutyService;

public class DutyServiceImpl extends BaseServiceImpl<Duty> implements DutyService {
	private DutyDao dao;

	public DutyServiceImpl(DutyDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean isExistDutyForUser(Long userId, Date startTime, Date endTime) {
		List dutyList = this.dao.getUserDutyByTime(userId, startTime, endTime);
		return dutyList.size() > 0;
	}

	public Duty getCurUserDuty(Long userId) {
		List dutyList = this.dao.getCurUserDuty(userId);
		if (dutyList.size() > 0) {
			return (Duty) dutyList.get(0);
		}
		return null;
	}
}


 
 
 
 
 