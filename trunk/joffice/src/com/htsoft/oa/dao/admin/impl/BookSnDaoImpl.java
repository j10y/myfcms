package com.htsoft.oa.dao.admin.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.admin.BookSnDao;
import com.htsoft.oa.model.admin.BookSn;

public class BookSnDaoImpl extends BaseDaoImpl<BookSn> implements BookSnDao {
	public BookSnDaoImpl() {
		super(BookSn.class);
	}

	public List<BookSn> findByBookId(Long bookId) {
		String hql = "from BookSn b where b.book.bookId=?";
		Object[] params = { bookId };
		return findByHql("from BookSn b where b.book.bookId=?", params);
	}

	public List<BookSn> findBorrowByBookId(Long bookId) {
		String hql = "from BookSn b where b.book.bookId=? and b.status=0";
		Object[] params = { bookId };
		return findByHql("from BookSn b where b.book.bookId=? and b.status=0", params);
	}

	public List<BookSn> findReturnByBookId(Long bookId) {
		String hql = "from BookSn b where b.book.bookId=? and b.status=1";
		Object[] params = { bookId };
		return findByHql("from BookSn b where b.book.bookId=? and b.status=1", params);
	}
}


 
 
 
 
 