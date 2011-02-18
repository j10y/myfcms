package com.htsoft.oa.dao.info;

import java.util.Date;
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.Notice;

public abstract interface NoticeDao extends BaseDao<Notice> {
	public abstract List<Notice> findByNoticeId(Long paramLong, PagingBean paramPagingBean);

	public abstract List<Notice> findBySearch(Notice paramNotice, Date paramDate1, Date paramDate2,
			PagingBean paramPagingBean);

	public abstract List<Notice> findBySearch(String paramString, PagingBean paramPagingBean);
}


 
 
 
 