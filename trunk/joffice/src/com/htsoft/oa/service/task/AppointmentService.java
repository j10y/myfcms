package com.htsoft.oa.service.task;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.task.Appointment;

public abstract interface AppointmentService extends BaseService<Appointment> {
	public abstract List showAppointmentByUserId(Long paramLong, PagingBean paramPagingBean);
}


 
 
 
 
 