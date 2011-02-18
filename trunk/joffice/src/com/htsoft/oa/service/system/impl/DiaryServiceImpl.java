package com.htsoft.oa.service.system.impl;

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.system.DiaryDao;
import com.htsoft.oa.model.system.Diary;
import com.htsoft.oa.service.system.DiaryService;

public class DiaryServiceImpl extends BaseServiceImpl<Diary> implements DiaryService {
	private DiaryDao dao;

	public DiaryServiceImpl(DiaryDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Diary> getAllBySn(PagingBean pb) {
		return null;
	}

	public List<Diary> getSubDiary(String userIds, PagingBean pb) {
		return this.dao.getSubDiary(userIds, pb);
	}
}


 
 
 
 
 