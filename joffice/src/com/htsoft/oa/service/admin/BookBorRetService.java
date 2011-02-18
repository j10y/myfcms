package com.htsoft.oa.service.admin;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.admin.BookBorRet;

public abstract interface BookBorRetService extends BaseService<BookBorRet> {
	public abstract BookBorRet getByBookSnId(Long paramLong);

	public abstract List getBorrowInfo(PagingBean paramPagingBean);

	public abstract List getReturnInfo(PagingBean paramPagingBean);

	public abstract Long getBookBorRetId(Long paramLong);
}


 
 
 
 
 