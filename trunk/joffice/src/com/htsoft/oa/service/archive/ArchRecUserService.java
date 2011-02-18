package com.htsoft.oa.service.archive;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.archive.ArchRecUser;

public abstract interface ArchRecUserService extends BaseService<ArchRecUser> {
	public abstract List findDepAll();

	public abstract ArchRecUser getByDepId(Long paramLong);
}


 
 
 
 
 