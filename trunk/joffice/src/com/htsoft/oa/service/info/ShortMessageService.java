package com.htsoft.oa.service.info;

import java.util.Date;
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.ShortMessage;

public abstract interface ShortMessageService extends BaseService<ShortMessage> {
	public abstract List<ShortMessage> findAll(Long paramLong, PagingBean paramPagingBean);

	public abstract List<ShortMessage> findByUser(Long paramLong);

	public abstract List searchShortMessage(Long paramLong, ShortMessage paramShortMessage,
			Date paramDate1, Date paramDate2, PagingBean paramPagingBean);

	public abstract ShortMessage save(Long paramLong, String paramString1, String paramString2,
			Short paramShort);
}


 
 
 
 
 