package com.htsoft.oa.dao.system;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.Region;

public abstract interface RegionDao extends BaseDao<Region> {
	public abstract List<Region> getProvince();

	public abstract List<Region> getCity(Long paramLong);
}


 
 
 
 