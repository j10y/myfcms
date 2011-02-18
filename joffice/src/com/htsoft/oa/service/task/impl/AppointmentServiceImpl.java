package com.htsoft.oa.service.task.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.task.AppointmentDao;
import com.htsoft.oa.model.task.Appointment;
import com.htsoft.oa.service.task.AppointmentService;

public class AppointmentServiceImpl extends BaseServiceImpl<Appointment> implements
		AppointmentService {
	private AppointmentDao dao;

	public AppointmentServiceImpl(AppointmentDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List showAppointmentByUserId(Long userId, PagingBean pb) {
		return this.dao.showAppointmentByUserId(userId, pb);
	}
}


 
 
 
 
 