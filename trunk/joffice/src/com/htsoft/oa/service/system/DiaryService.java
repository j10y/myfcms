package com.htsoft.oa.service.system;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.Diary;

public abstract interface DiaryService extends BaseService<Diary> {
	public abstract List<Diary> getAllBySn(PagingBean paramPagingBean);

	public abstract List<Diary> getSubDiary(String paramString, PagingBean paramPagingBean);
}


 
 
 
 
 