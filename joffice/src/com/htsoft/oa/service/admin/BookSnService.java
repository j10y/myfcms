package com.htsoft.oa.service.admin;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.admin.BookSn;

public abstract interface BookSnService extends BaseService<BookSn> {
	public abstract List<BookSn> findByBookId(Long paramLong);

	public abstract List<BookSn> findBorrowByBookId(Long paramLong);

	public abstract List<BookSn> findReturnByBookId(Long paramLong);
}


 
 
 
 
 