package com.htsoft.oa.dao.archive;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.archive.ArchDispatch;
import com.htsoft.oa.model.system.AppUser;

public abstract interface ArchDispatchDao extends BaseDao<ArchDispatch> {
	public abstract List<ArchDispatch> findByUser(AppUser paramAppUser, PagingBean paramPagingBean);

	public abstract List<ArchDispatch> findRecordByArc(Long paramLong);
}


 
 
 
 
 