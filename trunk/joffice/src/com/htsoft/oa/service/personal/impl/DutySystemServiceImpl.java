package com.htsoft.oa.service.personal.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.personal.DutySystemDao;
import com.htsoft.oa.model.personal.DutySystem;
import com.htsoft.oa.service.personal.DutySystemService;

public class DutySystemServiceImpl extends BaseServiceImpl<DutySystem> implements DutySystemService {
	private DutySystemDao dao;

	public DutySystemServiceImpl(DutySystemDao dao) {
		super(dao);
		this.dao = dao;
	}

	public DutySystem save(DutySystem duty) {
		if (DutySystem.DEFAULT.equals(duty.getIsDefault())) {
			this.dao.updateForNotDefult();
		}
		this.dao.save(duty);
		return duty;
	}

	public DutySystem getDefaultDutySystem() {
		List list = this.dao.getDefaultDutySystem();
		if (list.size() > 0) {
			return (DutySystem) list.get(0);
		}
		return null;
	}
}


 
 
 
 
 