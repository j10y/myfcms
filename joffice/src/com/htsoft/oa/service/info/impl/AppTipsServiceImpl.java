package com.htsoft.oa.service.info.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.info.AppTipsDao;
import com.htsoft.oa.model.info.AppTips;
import com.htsoft.oa.service.info.AppTipsService;

public class AppTipsServiceImpl extends BaseServiceImpl<AppTips> implements AppTipsService {
	private AppTipsDao dao;

	public AppTipsServiceImpl(AppTipsDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 