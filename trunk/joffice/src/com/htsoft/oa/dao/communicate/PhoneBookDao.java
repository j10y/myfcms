package com.htsoft.oa.dao.communicate;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.communicate.PhoneBook;

public abstract interface PhoneBookDao extends BaseDao<PhoneBook> {
	public abstract List<PhoneBook> sharedPhoneBooks(String paramString1, String paramString2,
			PagingBean paramPagingBean);
}


 
 
 
 
 