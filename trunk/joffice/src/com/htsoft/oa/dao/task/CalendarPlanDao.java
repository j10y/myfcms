package com.htsoft.oa.dao.task;

import java.util.Date;
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.task.CalendarPlan;

public abstract interface CalendarPlanDao extends BaseDao<CalendarPlan> {
	public abstract List<CalendarPlan> getTodayPlans(Long paramLong, PagingBean paramPagingBean);

	public abstract List<CalendarPlan> getByPeriod(Long paramLong, Date paramDate1, Date paramDate2);

	public abstract List showCalendarPlanByUserId(Long paramLong, PagingBean paramPagingBean);
}


 
 
 
 