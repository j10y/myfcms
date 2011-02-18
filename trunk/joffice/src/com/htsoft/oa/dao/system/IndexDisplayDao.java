package com.htsoft.oa.dao.system;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.IndexDisplay;

public abstract interface IndexDisplayDao extends BaseDao<IndexDisplay> {
	public abstract List<IndexDisplay> findByUser(Long paramLong);
}


 
 
 
 
 