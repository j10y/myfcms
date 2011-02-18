package com.htsoft.oa.service.info.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.info.NewsCommentDao;
import com.htsoft.oa.model.info.NewsComment;
import com.htsoft.oa.service.info.NewsCommentService;

public class NewsCommentServiceImpl extends BaseServiceImpl<NewsComment> implements
		NewsCommentService {
	private NewsCommentDao dao;

	public NewsCommentServiceImpl(NewsCommentDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 