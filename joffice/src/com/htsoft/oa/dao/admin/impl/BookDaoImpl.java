package com.htsoft.oa.dao.admin.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.admin.BookDao;
import com.htsoft.oa.model.admin.Book;

public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {
	public BookDaoImpl() {
		super(Book.class);
	}

	public List<Book> findByTypeId(Long typeId, PagingBean pb) {
		String hql = "from Book b where b.bookType.typeId=?";
		Object[] params = { typeId };
		return findByHql("from Book b where b.bookType.typeId=?", params, pb);
	}
}


 
 
 
 
 