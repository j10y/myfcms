package com.htsoft.oa.dao.system.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.ReportParamDao;
import com.htsoft.oa.model.system.ReportParam;

public class ReportParamDaoImpl extends BaseDaoImpl<ReportParam> implements ReportParamDao {
	public ReportParamDaoImpl() {
		super(ReportParam.class);
	}

	public List<ReportParam> findByRepTemp(Long reportId) {
		String hql = "from ReportParam vo where vo.reportTemplate.reportId=? order by vo.sn asc";
		Object[] objs = { reportId };
		return findByHql(hql, objs);
	}
}


 
 
 
 
 