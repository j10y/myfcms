package com.htsoft.oa.service.archive.impl;

import java.util.List;
import java.util.Set;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.archive.ArchivesDao;
import com.htsoft.oa.model.archive.Archives;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.service.archive.ArchivesService;

public class ArchivesServiceImpl extends BaseServiceImpl<Archives> implements ArchivesService {
	private ArchivesDao dao;

	public ArchivesServiceImpl(ArchivesDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Archives> findByUserOrRole(Long userId, Set<AppRole> roles, PagingBean pb) {
		return this.dao.findByUserOrRole(userId, roles, pb);
	}
}


 
 
 
 
 