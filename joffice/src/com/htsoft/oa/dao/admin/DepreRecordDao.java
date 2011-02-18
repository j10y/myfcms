package com.htsoft.oa.dao.admin;

import java.util.Date;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.DepreRecord;

public abstract interface DepreRecordDao extends BaseDao<DepreRecord> {
	public abstract Date findMaxDate(Long paramLong);
}


 
 
 
 