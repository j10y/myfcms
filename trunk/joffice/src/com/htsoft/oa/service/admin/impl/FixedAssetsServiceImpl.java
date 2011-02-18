package com.htsoft.oa.service.admin.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.FixedAssetsDao;
import com.htsoft.oa.model.admin.FixedAssets;
import com.htsoft.oa.service.admin.FixedAssetsService;

public class FixedAssetsServiceImpl extends BaseServiceImpl<FixedAssets> implements
		FixedAssetsService {
	private FixedAssetsDao dao;

	public FixedAssetsServiceImpl(FixedAssetsDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 