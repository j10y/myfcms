package com.htsoft.oa.service.system.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.ReportTemplateDao;
import com.htsoft.oa.model.system.ReportTemplate;
import com.htsoft.oa.service.system.ReportTemplateService;

public class ReportTemplateServiceImpl extends BaseServiceImpl<ReportTemplate> implements
		ReportTemplateService {
	private ReportTemplateDao dao;

	public ReportTemplateServiceImpl(ReportTemplateDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 