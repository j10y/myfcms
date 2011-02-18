package com.htsoft.oa.service.hrm;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.hrm.StandSalaryItem;

public abstract interface StandSalaryItemService extends BaseService<StandSalaryItem> {
	public abstract List<StandSalaryItem> getAllByStandardId(Long paramLong);
}


 
 
 
 
 