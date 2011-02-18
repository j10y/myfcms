package com.htsoft.oa.service.system;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.ReportParam;

public abstract interface ReportParamService extends BaseService<ReportParam> {
	public abstract List<ReportParam> findByRepTemp(Long paramLong);
}


 
 
 
 
 