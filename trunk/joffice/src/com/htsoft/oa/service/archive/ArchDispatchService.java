package com.htsoft.oa.service.archive;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.archive.ArchDispatch;
import com.htsoft.oa.model.system.AppUser;

public abstract interface ArchDispatchService extends BaseService<ArchDispatch> {
	public abstract List<ArchDispatch> findByUser(AppUser paramAppUser, PagingBean paramPagingBean);

	public abstract int countArchDispatch(Long paramLong);
}


 
 
 
 
 