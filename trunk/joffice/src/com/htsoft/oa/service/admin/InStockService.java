package com.htsoft.oa.service.admin;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.admin.InStock;

public abstract interface InStockService extends BaseService<InStock> {
	public abstract Integer findInCountByBuyId(Long paramLong);
}


 
 
 
 
 