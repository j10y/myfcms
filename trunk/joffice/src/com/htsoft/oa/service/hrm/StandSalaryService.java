package com.htsoft.oa.service.hrm;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.hrm.StandSalary;

public abstract interface StandSalaryService extends BaseService<StandSalary> {
	public abstract boolean checkStandNo(String paramString);

	public abstract List<StandSalary> findByPassCheck();
}


 
 
 
 
 