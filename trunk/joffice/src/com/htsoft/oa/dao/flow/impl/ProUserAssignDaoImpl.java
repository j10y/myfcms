package com.htsoft.oa.dao.flow.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.flow.ProUserAssignDao;
import com.htsoft.oa.model.flow.ProUserAssign;

public class ProUserAssignDaoImpl extends BaseDaoImpl<ProUserAssign> implements ProUserAssignDao {
	public ProUserAssignDaoImpl() {
		super(ProUserAssign.class);
	}

	public List<ProUserAssign> getByDeployId(String deployId) {
		String hql = "from ProUserAssign pua where pua.deployId=?";
		return findByHql(hql, new Object[] { deployId });
	}

	public ProUserAssign getByDeployIdActivityName(String deployId, String activityName) {
		String hql = "from ProUserAssign pua where pua.deployId=? and pua.activityName=?";
		return (ProUserAssign) findUnique(hql, new Object[] { deployId, activityName });
	}
}


 
 
 
 
 