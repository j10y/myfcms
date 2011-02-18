package com.htsoft.oa.dao.hrm;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.hrm.StandSalaryItem;

public abstract interface StandSalaryItemDao extends BaseDao<StandSalaryItem> {
	public abstract List<StandSalaryItem> getAllByStandardId(Long paramLong);
}


 
 
 
 
 