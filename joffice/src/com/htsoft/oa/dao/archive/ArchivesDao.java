package com.htsoft.oa.dao.archive;

import java.util.List;
import java.util.Set;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.archive.Archives;
import com.htsoft.oa.model.system.AppRole;

public abstract interface ArchivesDao extends BaseDao<Archives> {
	public abstract List<Archives> findByUserOrRole(Long paramLong, Set<AppRole> paramSet,
			PagingBean paramPagingBean);
}


 
 
 
 