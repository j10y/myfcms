package com.htsoft.oa.service.admin;

import java.util.Date;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.admin.DepreRecord;

public abstract interface DepreRecordService extends BaseService<DepreRecord> {
	public abstract Date findMaxDate(Long paramLong);
}


 
 
 
 
 