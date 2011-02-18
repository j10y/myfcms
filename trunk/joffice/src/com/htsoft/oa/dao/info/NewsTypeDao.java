package com.htsoft.oa.dao.info;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.NewsType;

public abstract interface NewsTypeDao extends BaseDao<NewsType> {
	public abstract Short getTop();

	public abstract NewsType findBySn(Short paramShort);

	public abstract List<NewsType> getAllBySn();

	public abstract List<NewsType> getAllBySn(PagingBean paramPagingBean);

	public abstract List<NewsType> findBySearch(NewsType paramNewsType, PagingBean paramPagingBean);
}


 
 
 
 