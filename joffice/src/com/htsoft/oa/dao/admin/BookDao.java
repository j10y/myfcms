package com.htsoft.oa.dao.admin;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.admin.Book;

public abstract interface BookDao extends BaseDao<Book> {
	public abstract List<Book> findByTypeId(Long paramLong, PagingBean paramPagingBean);
}


 
 
 
 