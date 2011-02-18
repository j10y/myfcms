package com.htsoft.oa.dao.info;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.News;

public abstract interface NewsDao extends BaseDao<News> {
	public abstract List<News> findByTypeId(Long paramLong, PagingBean paramPagingBean);

	public abstract List<News> findBySearch(String paramString, PagingBean paramPagingBean);

	public abstract List<News> findImageNews(PagingBean paramPagingBean);
}


 
 
 
 