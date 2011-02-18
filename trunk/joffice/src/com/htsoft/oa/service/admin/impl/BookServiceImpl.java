package com.htsoft.oa.service.admin.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.admin.BookDao;
import com.htsoft.oa.model.admin.Book;
import com.htsoft.oa.service.admin.BookService;

public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {
	private BookDao dao;

	public BookServiceImpl(BookDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Book> findByTypeId(Long typeId, PagingBean pb) {
		return this.dao.findByTypeId(typeId, pb);
	}
}


 
 
 
 
 