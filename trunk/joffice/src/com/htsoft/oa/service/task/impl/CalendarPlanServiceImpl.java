package com.htsoft.oa.service.task.impl;

import java.util.Date;
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.task.CalendarPlanDao;
import com.htsoft.oa.model.task.CalendarPlan;
import com.htsoft.oa.service.task.CalendarPlanService;

public class CalendarPlanServiceImpl extends BaseServiceImpl<CalendarPlan> implements
		CalendarPlanService {
	private CalendarPlanDao dao;

	public CalendarPlanServiceImpl(CalendarPlanDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<CalendarPlan> getTodayPlans(Long userId, PagingBean pb) {
		return this.dao.getTodayPlans(userId, pb);
	}

	public List<CalendarPlan> getByPeriod(Long userId, Date startTime, Date endTime) {
		return this.dao.getByPeriod(userId, startTime, endTime);
	}

	public List showCalendarPlanByUserId(Long userId, PagingBean pb) {
		return this.dao.showCalendarPlanByUserId(userId, pb);
	}
}


 
 
 
 
 