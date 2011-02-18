package com.htsoft.oa.service.personal;

import java.util.Date;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.personal.Duty;

public abstract interface DutyService extends BaseService<Duty> {
	public abstract boolean isExistDutyForUser(Long paramLong, Date paramDate1, Date paramDate2);

	public abstract Duty getCurUserDuty(Long paramLong);
}


 
 
 
 
 