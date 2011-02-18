package com.htsoft.oa.service.archive.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.DocHistoryDao;
import com.htsoft.oa.model.archive.DocHistory;
import com.htsoft.oa.service.archive.DocHistoryService;

public class DocHistoryServiceImpl extends BaseServiceImpl<DocHistory> implements DocHistoryService {
	private DocHistoryDao dao;

	public DocHistoryServiceImpl(DocHistoryDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 