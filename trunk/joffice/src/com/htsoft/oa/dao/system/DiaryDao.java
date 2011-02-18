package com.htsoft.oa.dao.system;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.Diary;

public abstract interface DiaryDao extends BaseDao<Diary> {
	public abstract List<Diary> getSubDiary(String paramString, PagingBean paramPagingBean);
}


 
 
 
 