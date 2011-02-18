package com.htsoft.oa.service.system;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.Region;

public abstract interface RegionService extends BaseService<Region> {
	public abstract List<Region> getProvince();

	public abstract List<Region> getCity(Long paramLong);
}


 
 
 
 
 