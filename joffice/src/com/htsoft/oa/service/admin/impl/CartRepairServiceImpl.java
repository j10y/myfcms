package com.htsoft.oa.service.admin.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.CartRepairDao;
import com.htsoft.oa.model.admin.CartRepair;
import com.htsoft.oa.service.admin.CartRepairService;

public class CartRepairServiceImpl extends BaseServiceImpl<CartRepair> implements CartRepairService {
	private CartRepairDao dao;

	public CartRepairServiceImpl(CartRepairDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 