package com.htsoft.oa.dao.customer;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.customer.CusLinkman;

public abstract interface CusLinkmanDao extends BaseDao<CusLinkman> {
	public abstract boolean checkMainCusLinkman(Long paramLong1, Long paramLong2);
}


 
 
 
 
 