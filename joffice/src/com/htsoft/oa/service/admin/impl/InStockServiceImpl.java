package com.htsoft.oa.service.admin.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.InStockDao;
import com.htsoft.oa.model.admin.InStock;
import com.htsoft.oa.service.admin.InStockService;

public class InStockServiceImpl extends BaseServiceImpl<InStock> implements InStockService {
	private InStockDao dao;

	public InStockServiceImpl(InStockDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Integer findInCountByBuyId(Long buyId) {
		return this.dao.findInCountByBuyId(buyId);
	}
}


 
 
 
 
 