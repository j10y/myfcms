package com.htsoft.oa.service.system.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.IndexDisplayDao;
import com.htsoft.oa.model.system.IndexDisplay;
import com.htsoft.oa.service.system.IndexDisplayService;

public class IndexDisplayServiceImpl extends BaseServiceImpl<IndexDisplay> implements
		IndexDisplayService {
	private IndexDisplayDao dao;

	public IndexDisplayServiceImpl(IndexDisplayDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<IndexDisplay> findByUser(Long userId) {
		return this.dao.findByUser(userId);
	}
}


 
 
 
 
 