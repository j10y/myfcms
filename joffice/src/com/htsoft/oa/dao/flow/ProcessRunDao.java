package com.htsoft.oa.dao.flow;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.ProcessRun;

public abstract interface ProcessRunDao extends BaseDao<ProcessRun> {
	public abstract ProcessRun getByPiId(String paramString);

	public abstract List<ProcessRun> getByDefId(Long paramLong, PagingBean paramPagingBean);

	public abstract List<ProcessRun> getByUserIdSubject(Long paramLong, String paramString,
			PagingBean paramPagingBean);
}


 
 
 
 