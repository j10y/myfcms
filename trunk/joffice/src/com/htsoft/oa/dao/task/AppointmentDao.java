package com.htsoft.oa.dao.task;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.task.Appointment;

public abstract interface AppointmentDao extends BaseDao<Appointment> {
	public abstract List showAppointmentByUserId(Long paramLong, PagingBean paramPagingBean);
}


 
 
 
 