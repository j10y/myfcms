package com.htsoft.oa.service.communicate;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.communicate.MailBox;

public abstract interface MailBoxService extends BaseService<MailBox> {
	public abstract Long CountByFolderId(Long paramLong);

	public abstract List<MailBox> findByFolderId(Long paramLong);

	public abstract List<MailBox> findBySearch(String paramString, PagingBean paramPagingBean);
}


 
 
 
 
 