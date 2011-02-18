package com.htsoft.oa.service.hrm;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.hrm.SalaryItem;

public abstract interface SalaryItemService extends BaseService<SalaryItem> {
	public abstract List<SalaryItem> getAllExcludeId(String paramString, PagingBean paramPagingBean);
}


 
 
 
 
 