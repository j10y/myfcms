package com.htsoft.oa.service.communicate;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.communicate.PhoneBook;

public abstract interface PhoneBookService extends BaseService<PhoneBook> {
	public abstract List<PhoneBook> sharedPhoneBooks(String paramString1, String paramString2,
			PagingBean paramPagingBean);
}


 
 
 
 
 