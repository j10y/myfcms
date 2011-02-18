package com.htsoft.oa.service.system.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.ReportParamDao;
import com.htsoft.oa.model.system.ReportParam;
import com.htsoft.oa.service.system.ReportParamService;

public class ReportParamServiceImpl extends BaseServiceImpl<ReportParam> implements
		ReportParamService {
	private ReportParamDao dao;

	public ReportParamServiceImpl(ReportParamDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<ReportParam> findByRepTemp(Long reportId) {
		return this.dao.findByRepTemp(reportId);
	}
}


 
 
 
 
 