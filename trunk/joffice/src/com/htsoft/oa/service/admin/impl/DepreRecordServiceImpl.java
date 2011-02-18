package com.htsoft.oa.service.admin.impl;

import java.util.Date;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.DepreRecordDao;
import com.htsoft.oa.model.admin.DepreRecord;
import com.htsoft.oa.service.admin.DepreRecordService;

public class DepreRecordServiceImpl extends BaseServiceImpl<DepreRecord> implements
		DepreRecordService {
	private DepreRecordDao dao;

	public DepreRecordServiceImpl(DepreRecordDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Date findMaxDate(Long assetsId) {
		return this.dao.findMaxDate(assetsId);
	}
}


 
 
 
 
 