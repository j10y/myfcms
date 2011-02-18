package com.htsoft.oa.service.flow.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.flow.ProUserAssignDao;
import com.htsoft.oa.model.flow.ProUserAssign;
import com.htsoft.oa.service.flow.ProUserAssignService;

public class ProUserAssignServiceImpl extends BaseServiceImpl<ProUserAssign> implements
		ProUserAssignService {
	private ProUserAssignDao dao;

	public ProUserAssignServiceImpl(ProUserAssignDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<ProUserAssign> getByDeployId(String deployId) {
		return this.dao.getByDeployId(deployId);
	}

	public ProUserAssign getByDeployIdActivityName(String deployId, String activityName) {
		return this.dao.getByDeployIdActivityName(deployId, activityName);
	}
}


 
 
 
 
 