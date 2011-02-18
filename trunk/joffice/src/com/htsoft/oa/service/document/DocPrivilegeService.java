package com.htsoft.oa.service.document;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.document.DocPrivilege;
import com.htsoft.oa.model.system.AppUser;

public abstract interface DocPrivilegeService extends BaseService<DocPrivilege> {
	public abstract List<DocPrivilege> getAll(DocPrivilege paramDocPrivilege, Long paramLong,
			PagingBean paramPagingBean);

	public abstract List<Integer> getRightsByFolder(AppUser paramAppUser, Long paramLong);

	public abstract Integer getRightsByDocument(AppUser paramAppUser, Long paramLong);
}


 
 
 
 
 