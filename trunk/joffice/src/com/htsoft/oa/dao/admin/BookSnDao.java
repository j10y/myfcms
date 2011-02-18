package com.htsoft.oa.dao.admin;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.BookSn;

public abstract interface BookSnDao extends BaseDao<BookSn> {
	public abstract List<BookSn> findByBookId(Long paramLong);

	public abstract List<BookSn> findBorrowByBookId(Long paramLong);

	public abstract List<BookSn> findReturnByBookId(Long paramLong);
}


 
 
 
 