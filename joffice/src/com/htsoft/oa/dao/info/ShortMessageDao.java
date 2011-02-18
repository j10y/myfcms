package com.htsoft.oa.dao.info;

import java.util.Date;
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.ShortMessage;

public abstract interface ShortMessageDao extends BaseDao<ShortMessage> {
	public abstract List<ShortMessage> findAll(Long paramLong, PagingBean paramPagingBean);

	public abstract List<ShortMessage> findByUser(Long paramLong);

	public abstract List searchShortMessage(Long paramLong, ShortMessage paramShortMessage,
			Date paramDate1, Date paramDate2, PagingBean paramPagingBean);
}


 
 
 
 