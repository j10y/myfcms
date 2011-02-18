package com.htsoft.oa.service.task;

import java.util.Date;
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.task.CalendarPlan;

public abstract interface CalendarPlanService extends BaseService<CalendarPlan> {
	public abstract List<CalendarPlan> getTodayPlans(Long paramLong, PagingBean paramPagingBean);

	public abstract List<CalendarPlan> getByPeriod(Long paramLong, Date paramDate1, Date paramDate2);

	public abstract List showCalendarPlanByUserId(Long paramLong, PagingBean paramPagingBean);
}


 
 
 
 
 