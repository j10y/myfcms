package com.htsoft.oa.dao.info;

import java.util.Date;
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.InMessage;
import com.htsoft.oa.model.info.ShortMessage;

public abstract interface InMessageDao extends BaseDao<InMessage> {
	public abstract InMessage findByRead(Long paramLong);

	public abstract Integer findByReadFlag(Long paramLong);

	public abstract List<InMessage> findAll(Long paramLong, PagingBean paramPagingBean);

	public abstract List<InMessage> findByShortMessage(ShortMessage paramShortMessage,
			PagingBean paramPagingBean);

	public abstract List findByUser(Long paramLong, PagingBean paramPagingBean);

	public abstract List findByUser(Long paramLong);

	public abstract List searchInMessage(Long paramLong, InMessage paramInMessage,
			ShortMessage paramShortMessage, Date paramDate1, Date paramDate2,
			PagingBean paramPagingBean);

	public abstract InMessage findLatest(Long paramLong);
}


 
 
 
 