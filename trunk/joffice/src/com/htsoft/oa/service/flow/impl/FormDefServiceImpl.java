package com.htsoft.oa.service.flow.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.flow.FormDefDao;
import com.htsoft.oa.model.flow.FormDef;
import com.htsoft.oa.service.flow.FormDefService;

public class FormDefServiceImpl extends BaseServiceImpl<FormDef> implements FormDefService {
	private FormDefDao dao;

	public FormDefServiceImpl(FormDefDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<FormDef> getByDeployId(String deployId) {
		return this.dao.getByDeployId(deployId);
	}

	public FormDef getByDeployIdActivityName(String deployId, String activityName) {
		return this.dao.getByDeployIdActivityName(deployId, activityName);
	}
}


 
 
 
 
 