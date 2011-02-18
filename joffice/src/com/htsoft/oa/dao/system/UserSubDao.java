package com.htsoft.oa.dao.system;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.UserSub;

public abstract interface UserSubDao extends BaseDao<UserSub> {
	public abstract List<Long> upUser(Long paramLong);

	public abstract List<Long> subUsers(Long paramLong);
}


 
 
 
 