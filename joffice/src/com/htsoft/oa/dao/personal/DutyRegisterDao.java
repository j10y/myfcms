package com.htsoft.oa.dao.personal;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.personal.DutyRegister;

public abstract interface DutyRegisterDao extends BaseDao<DutyRegister> {
	public abstract DutyRegister getTodayUserRegister(Long paramLong1, Short paramShort,
			Long paramLong2);
}


 
 
 
 
 