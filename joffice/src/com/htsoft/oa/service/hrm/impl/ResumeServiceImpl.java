package com.htsoft.oa.service.hrm.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.hrm.ResumeDao;
import com.htsoft.oa.model.hrm.Resume;
import com.htsoft.oa.service.hrm.ResumeService;

public class ResumeServiceImpl extends BaseServiceImpl<Resume> implements ResumeService {
	private ResumeDao dao;

	public ResumeServiceImpl(ResumeDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 