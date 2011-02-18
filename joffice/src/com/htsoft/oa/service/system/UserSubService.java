package com.htsoft.oa.service.system;

import java.util.List;
import java.util.Set;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.UserSub;

public abstract interface UserSubService extends BaseService<UserSub> {
	public abstract Set<Long> findAllUpUser(Long paramLong);

	public abstract List<Long> subUsers(Long paramLong);

	public abstract List<Long> upUser(Long paramLong);
}


 
 
 
 
 