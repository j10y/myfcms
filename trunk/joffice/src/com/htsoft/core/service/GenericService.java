package com.htsoft.core.service;

import java.io.Serializable;
import java.util.List;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.paging.PagingBean;

public abstract interface GenericService<T, PK extends Serializable> {
	public abstract T save(T paramT);

	public abstract T merge(T paramT);

	public abstract void evict(T paramT);

	public abstract T get(PK paramPK);

	public abstract List<T> getAll();

	public abstract List<T> getAll(PagingBean paramPagingBean);

	public abstract List<T> getAll(QueryFilter paramQueryFilter);

	public abstract void remove(PK paramPK);

	public abstract void remove(T paramT);

	public abstract void flush();
}
