package com.htsoft.oa.service.hrm.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.hrm.HireIssueDao;
import com.htsoft.oa.model.hrm.HireIssue;
import com.htsoft.oa.service.hrm.HireIssueService;

public class HireIssueServiceImpl extends BaseServiceImpl<HireIssue> implements HireIssueService {
	private HireIssueDao dao;

	public HireIssueServiceImpl(HireIssueDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 