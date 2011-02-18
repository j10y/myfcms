package com.htsoft.oa.service.hrm.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.hrm.EmpProfileDao;
import com.htsoft.oa.model.hrm.EmpProfile;
import com.htsoft.oa.service.hrm.EmpProfileService;

public class EmpProfileServiceImpl extends BaseServiceImpl<EmpProfile> implements EmpProfileService {
	private EmpProfileDao dao;

	public EmpProfileServiceImpl(EmpProfileDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean checkProfileNo(String profileNo) {
		return this.dao.checkProfileNo(profileNo);
	}
}


 
 
 
 
 