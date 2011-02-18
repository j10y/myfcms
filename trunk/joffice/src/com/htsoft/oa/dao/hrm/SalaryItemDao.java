package com.htsoft.oa.dao.hrm;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.hrm.SalaryItem;

public abstract interface SalaryItemDao extends BaseDao<SalaryItem> {
	public abstract List<SalaryItem> getAllExcludeId(String paramString, PagingBean paramPagingBean);
}


 
 
 
 