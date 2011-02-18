package com.htsoft.oa.service.admin.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.BookTypeDao;
import com.htsoft.oa.model.admin.BookType;
import com.htsoft.oa.service.admin.BookTypeService;

public class BookTypeServiceImpl extends BaseServiceImpl<BookType> implements BookTypeService {
	private BookTypeDao dao;

	public BookTypeServiceImpl(BookTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 