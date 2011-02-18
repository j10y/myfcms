package com.htsoft.oa.service.admin;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.admin.Book;

public abstract interface BookService extends BaseService<Book> {
	public abstract List<Book> findByTypeId(Long paramLong, PagingBean paramPagingBean);
}


 
 
 
 
 