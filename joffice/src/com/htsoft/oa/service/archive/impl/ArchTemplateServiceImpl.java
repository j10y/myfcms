package com.htsoft.oa.service.archive.impl;

import javax.annotation.Resource;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchTemplateDao;
import com.htsoft.oa.model.archive.ArchTemplate;
import com.htsoft.oa.service.archive.ArchTemplateService;
import com.htsoft.oa.service.system.FileAttachService;

public class ArchTemplateServiceImpl extends BaseServiceImpl<ArchTemplate> implements
		ArchTemplateService {
	private ArchTemplateDao dao;

	@Resource
	FileAttachService fileAttachService;

	public ArchTemplateServiceImpl(ArchTemplateDao dao) {
		super(dao);
		this.dao = dao;
	}

	public void remove(Long id) {
		ArchTemplate template = (ArchTemplate) this.dao.get(id);
		remove(template);
		this.fileAttachService.removeByPath(template.getTempPath());
	}
}


 
 
 
 
 