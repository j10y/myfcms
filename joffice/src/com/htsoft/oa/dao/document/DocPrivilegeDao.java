package com.htsoft.oa.dao.document;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.document.DocPrivilege;
import com.htsoft.oa.model.system.AppUser;

public abstract interface DocPrivilegeDao extends BaseDao<DocPrivilege> {
	public abstract List<DocPrivilege> getAll(DocPrivilege paramDocPrivilege, Long paramLong,
			PagingBean paramPagingBean);

	public abstract List<DocPrivilege> getByPublic(DocPrivilege paramDocPrivilege, Long paramLong);

	public abstract List<Integer> getRightsByFolder(AppUser paramAppUser, Long paramLong);

	public abstract Integer getRightsByDocument(AppUser paramAppUser, Long paramLong);

	public abstract Integer countPrivilege();
}


 
 
 
 
 