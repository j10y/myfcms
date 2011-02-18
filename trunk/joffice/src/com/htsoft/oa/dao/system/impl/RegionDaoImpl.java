package com.htsoft.oa.dao.system.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.RegionDao;
import com.htsoft.oa.model.system.Region;

public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {
	public RegionDaoImpl() {
		super(Region.class);
	}

	public List<Region> getProvince() {
		Long parentId = Long.valueOf(0L);
		String hql = "from Region r where r.parentId = ?";
		return findByHql(hql, new Object[] { parentId });
	}

	public List<Region> getCity(Long regionId) {
		String hql = "from Region r where r.parentId = ?";
		return findByHql(hql, new Object[] { regionId });
	}
}


 
 
 
 
 