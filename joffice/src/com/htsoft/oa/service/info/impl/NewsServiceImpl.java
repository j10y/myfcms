package com.htsoft.oa.service.info.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.info.NewsDao;
import com.htsoft.oa.model.info.News;
import com.htsoft.oa.service.info.NewsService;

public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {
	private NewsDao newsDao;

	public NewsServiceImpl(NewsDao dao) {
		super(dao);
		this.newsDao = dao;
	}

	public List<News> findByTypeId(Long typeId, PagingBean pb) {
		return this.newsDao.findByTypeId(typeId, pb);
	}

	public List<News> findBySearch(String searchContent, PagingBean pb) {
		return this.newsDao.findBySearch(searchContent, pb);
	}

	public List<News> findImageNews(PagingBean pb) {
		return this.newsDao.findImageNews(pb);
	}
}


 
 
 
 
 