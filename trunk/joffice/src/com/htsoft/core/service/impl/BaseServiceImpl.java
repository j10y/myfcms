package com.htsoft.core.service.impl;

import com.htsoft.core.dao.GenericDao;
import com.htsoft.core.service.BaseService;

public class BaseServiceImpl<T> extends GenericServiceImpl<T, Long> implements BaseService<T> {
	public BaseServiceImpl(GenericDao dao) {
		super(dao);
	}
}
