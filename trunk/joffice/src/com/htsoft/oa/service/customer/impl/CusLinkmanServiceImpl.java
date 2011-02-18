package com.htsoft.oa.service.customer.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.customer.CusLinkmanDao;
import com.htsoft.oa.model.customer.CusLinkman;
import com.htsoft.oa.service.customer.CusLinkmanService;

public class CusLinkmanServiceImpl extends BaseServiceImpl<CusLinkman> implements CusLinkmanService {
	private CusLinkmanDao dao;

	public CusLinkmanServiceImpl(CusLinkmanDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean checkMainCusLinkman(Long customerId, Long linkmanId) {
		return this.dao.checkMainCusLinkman(customerId, linkmanId);
	}
}


 
 
 
 
 