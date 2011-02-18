package com.htsoft.oa.dao.system.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.system.DiaryDao;
import com.htsoft.oa.model.system.Diary;

public class DiaryDaoImpl extends BaseDaoImpl<Diary> implements DiaryDao {
	public DiaryDaoImpl() {
		super(Diary.class);
	}

	public List<Diary> getSubDiary(String userIds, PagingBean pb) {
		String hql = "from Diary vo where vo.appUser.userId in (" + userIds
				+ ") and vo.diaryType=1";
		return findByHql(hql, null, pb);
	}
}


 
 
 
 
 