package com.htsoft.oa.dao.system;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.ReportParam;

public abstract interface ReportParamDao extends BaseDao<ReportParam> {
	public abstract List<ReportParam> findByRepTemp(Long paramLong);
}


 
 
 
 
 