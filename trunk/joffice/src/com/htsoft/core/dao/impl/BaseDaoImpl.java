package com.htsoft.core.dao.impl;

import com.htsoft.core.dao.BaseDao;

public class BaseDaoImpl<T> extends GenericDaoImpl<T, Long> implements BaseDao<T> {
	public BaseDaoImpl(Class persistType) {
		super(persistType);
	}
}

