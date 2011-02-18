package com.htsoft.oa.service.info;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.NewsType;

public abstract interface NewsTypeService extends BaseService<NewsType> {
	public abstract Short getTop();

	public abstract NewsType findBySn(Short paramShort);

	public abstract List<NewsType> getAllBySn();

	public abstract List<NewsType> getAllBySn(PagingBean paramPagingBean);

	public abstract List<NewsType> findBySearch(NewsType paramNewsType, PagingBean paramPagingBean);
}


 
 
 
 
 