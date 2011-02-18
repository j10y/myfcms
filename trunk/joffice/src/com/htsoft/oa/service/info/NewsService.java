package com.htsoft.oa.service.info;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.News;

public abstract interface NewsService extends BaseService<News> {
	public abstract List<News> findByTypeId(Long paramLong, PagingBean paramPagingBean);

	public abstract List<News> findBySearch(String paramString, PagingBean paramPagingBean);

	public abstract List<News> findImageNews(PagingBean paramPagingBean);
}


 
 
 
 