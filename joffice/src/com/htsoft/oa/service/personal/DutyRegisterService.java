package com.htsoft.oa.service.personal;

import java.util.Date;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.personal.DutyRegister;
import com.htsoft.oa.model.system.AppUser;

public abstract interface DutyRegisterService extends BaseService<DutyRegister> {
	public abstract void signInOff(Long paramLong, Short paramShort, AppUser paramAppUser,
			Date paramDate);

	public abstract DutyRegister getTodayUserRegister(Long paramLong1, Short paramShort,
			Long paramLong2);
}


 
 
 
 
 