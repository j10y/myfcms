package com.htsoft.oa.dao.archive;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.archive.ArchRecUser;

public abstract interface ArchRecUserDao extends BaseDao<ArchRecUser> {
	public abstract List findDepAll();

	public abstract ArchRecUser getByDepId(Long paramLong);
}


 
 
 
 
 