package com.htsoft.oa.service.system;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.IndexDisplay;

public abstract interface IndexDisplayService extends BaseService<IndexDisplay> {
	public abstract List<IndexDisplay> findByUser(Long paramLong);
}


 
 
 
 
 