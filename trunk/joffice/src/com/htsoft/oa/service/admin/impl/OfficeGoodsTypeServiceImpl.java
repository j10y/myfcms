package com.htsoft.oa.service.admin.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.OfficeGoodsTypeDao;
import com.htsoft.oa.model.admin.OfficeGoodsType;
import com.htsoft.oa.service.admin.OfficeGoodsTypeService;

public class OfficeGoodsTypeServiceImpl extends BaseServiceImpl<OfficeGoodsType> implements
		OfficeGoodsTypeService {
	private OfficeGoodsTypeDao dao;

	public OfficeGoodsTypeServiceImpl(OfficeGoodsTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 