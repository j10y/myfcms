package com.htsoft.oa.service.admin.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.admin.BookBorRetDao;
import com.htsoft.oa.model.admin.BookBorRet;
import com.htsoft.oa.service.admin.BookBorRetService;

public class BookBorRetServiceImpl extends BaseServiceImpl<BookBorRet> implements BookBorRetService {
	private BookBorRetDao dao;

	public BookBorRetServiceImpl(BookBorRetDao dao) {
		super(dao);
		this.dao = dao;
	}

	public BookBorRet getByBookSnId(Long bookSnId) {
		return this.dao.getByBookSnId(bookSnId);
	}

	public List getBorrowInfo(PagingBean pb) {
		return this.dao.getBorrowInfo(pb);
	}

	public List getReturnInfo(PagingBean pb) {
		return this.dao.getReturnInfo(pb);
	}

	public Long getBookBorRetId(Long snId) {
		return this.dao.getBookBorRetId(snId);
	}
}


 
 
 
 
 