package com.htsoft.oa.dao.communicate;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.communicate.MailBox;

public abstract interface MailBoxDao extends BaseDao<MailBox> {
	public abstract Long CountByFolderId(Long paramLong);

	public abstract List<MailBox> findByFolderId(Long paramLong);

	public abstract List<MailBox> findBySearch(String paramString, PagingBean paramPagingBean);
}


 
 
 
 
 