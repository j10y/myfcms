package com.htsoft.oa.dao.personal;

import java.util.Date;
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.personal.Duty;

public abstract interface DutyDao extends BaseDao<Duty> {
	public abstract List<Duty> getUserDutyByTime(Long paramLong, Date paramDate1, Date paramDate2);

	public abstract List<Duty> getCurUserDuty(Long paramLong);
}


 
 
 
 