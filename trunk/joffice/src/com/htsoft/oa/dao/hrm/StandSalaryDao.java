package com.htsoft.oa.dao.hrm;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.hrm.StandSalary;

public abstract interface StandSalaryDao extends BaseDao<StandSalary> {
	public abstract boolean checkStandNo(String paramString);

	public abstract List<StandSalary> findByPassCheck();
}


 
 
 
 