package com.htsoft.oa.dao.task.impl;

import java.util.ArrayList;
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.task.AppointmentDao;
import com.htsoft.oa.model.task.Appointment;

public class AppointmentDaoImpl extends BaseDaoImpl<Appointment> implements AppointmentDao {
	public AppointmentDaoImpl() {
		super(Appointment.class);
	}

	public List showAppointmentByUserId(Long userId, PagingBean pb) {
		ArrayList paramList = new ArrayList();
		StringBuffer hql = new StringBuffer(
				"select vo from Appointment vo where vo.appUser.userId=?");
		paramList.add(userId);
		hql.append(" order by vo.appointId desc");

		return findByHql(hql.toString(), paramList.toArray(), pb);
	}
}


 
 
 
 
 