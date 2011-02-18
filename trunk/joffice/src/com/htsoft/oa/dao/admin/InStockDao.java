package com.htsoft.oa.dao.admin;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.InStock;

public abstract interface InStockDao extends BaseDao<InStock> {
	public abstract Integer findInCountByBuyId(Long paramLong);
}


 
 
 
 